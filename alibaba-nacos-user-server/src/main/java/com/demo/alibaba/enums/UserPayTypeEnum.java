package com.demo.alibaba.enums;

import java.util.Arrays;
import java.util.List;

public enum UserPayTypeEnum {
    USER_ONLINE_PAY(1, "user_online_pay"),
    USER_OFFLINE_PAY(2, "user_offline_pay");
    private int userType;
    private String userPayTypeame;

    UserPayTypeEnum(int userType, String userPayTypeame) {
        this.userType = userType;
        this.userPayTypeame = userPayTypeame;
    }

    public static UserPayTypeEnum getUserPayType(int type) {
        for (UserPayTypeEnum value : UserPayTypeEnum.values()) {
            if (value.getUserType() == type) {
                return value;
            }
        }
        return null;
    }

    public static List<UserPayTypeEnum> getAllUserPayType() {
        return Arrays.asList(UserPayTypeEnum.values());
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserPayTypeame() {
        return userPayTypeame;
    }

    public void setUserPayTypeame(String userPayTypeame) {
        this.userPayTypeame = userPayTypeame;
    }
}
