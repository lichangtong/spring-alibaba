package com.demo.alibaba;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 53 bits unique id:
 * <p>
 * |--------|--------|--------|--------|--------|--------|--------|--------|
 * |00000000|00011111|11111111|11111111|11111111|11111111|11111111|11111111|
 * |--------|---xxxxx|xxxxxxxx|xxxxxxxx|xxxxxxxx|xxx-----|--------|--------|
 * |--------|--------|--------|--------|--------|---xxxxx|xxxxxxxx|xxx-----|
 * |--------|--------|--------|--------|--------|--------|--------|---xxxxx|
 * <p>
 * Maximum ID = 11111_11111111_11111111_11111111_11111111_11111111_11111111
 * <p>
 * Maximum TS = 11111_11111111_11111111_11111111_111
 * <p>
 * Maximum NT = ----- -------- -------- -------- ---11111_11111111_111 = 65535
 * <p>
 * Maximum SH = ----- -------- -------- -------- -------- -------- ---11111 = 31
 * <p>
 * It can generate 64k unique id per IP and up to 2106-02-07T06:28:15Z.
 */
@Slf4j
public final class IdUtil {

	private static final Pattern PATTERN_LONG_ID = Pattern.compile("^([0-9]{15})([0-9a-f]{32})([0-9a-f]{3})$");

	private static final Pattern PATTERN_HOSTNAME = Pattern.compile("^.*\\D+([0-9]+)$");

	private static final long OFFSET = LocalDate.of(2000, 1, 1).atStartOfDay(ZoneId.of("Z")).toEpochSecond();

	private static final long MAX_NEXT = 0b11111_11111111_111L;

	private static final long SHARD_ID = getServerIdAsLong();

	private static long offset = 0;

	private static long lastEpoch = 0;

	public static long nextId() {
		return nextId(System.currentTimeMillis());
	}

	public static String nextStringId() {
		return new StringBuffer().append(getNextId(DateUtils.geOrderPrefixTime())).append(getNextId(String.valueOf(nextId(System.currentTimeMillis())))).toString();
	}

	private static String getNextId(String str) {
		List<String> list = new ArrayList();
		for (Character s : str.toCharArray()) {
			list.add(s.toString());
		}
		//随机打乱
		Collections.shuffle(list);
		return String.join("", list);
	}

	public static String nextStringId(String prefix) {
		return new StringBuffer().append(prefix).append(nextId(System.currentTimeMillis())).toString();
	}

	private static synchronized long nextId(long epochSecond) {
		if (epochSecond < lastEpoch) {
			// warning: clock is turn back:
//			log.warn("clock is back: " + epochSecond + " from previous:" + lastEpoch);
			epochSecond = lastEpoch;
		}
		if (lastEpoch != epochSecond) {
			lastEpoch = epochSecond;
			reset();
		}
		offset++;
		long next = offset & MAX_NEXT;
		if (next == 0) {
			log.warn("maximum id reached in 1 second in epoch: " + epochSecond);
			return nextId(epochSecond + 1);
		}
		return generateId(epochSecond, next, SHARD_ID);
	}

	private static void reset() {
		offset = 0;
	}

	private static long generateId(long epochSecond, long next, long shardId) {
		return ((epochSecond - OFFSET) << 21) | (next << 5) | shardId;
	}

	private static long getServerIdAsLong() {
		try {
			String hostname = InetAddress.getLocalHost().getHostName();
			Matcher matcher = PATTERN_HOSTNAME.matcher(hostname);
			if (matcher.matches()) {
				long n = Long.parseLong(matcher.group(1));
				if (n >= 0 && n < 8) {
					log.info("detect server id from host name {}: {}.", hostname, n);
					return n;
				}
			} else if (hostname != null && !hostname.isEmpty()) {
				//相当于随便转了 如果当前没有hostname
				char c = hostname.charAt(hostname.length() - 1);//取最后一位的 随便取模就行了
				int lastIndex = c;
				return lastIndex % 8;
			}
		} catch (UnknownHostException e) {
			log.warn("unable to get host name. set server id = 0.");
		}
		return 0;
	}


	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 200; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 100; i++) {
						System.out.println(IdUtil.nextStringId());
					}
				}
			}).start();
		}
	}
}
