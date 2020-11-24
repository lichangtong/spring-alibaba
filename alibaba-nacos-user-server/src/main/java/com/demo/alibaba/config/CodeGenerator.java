package com.demo.alibaba.config;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: IntelliJ IDEA
 * @ClassName: CodeGenerator
 * @Description: CodeGenerator
 * @Author: lichangtong
 * @Date: 2020-11-24 16:24
 */

public class CodeGenerator {
	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOpen(false);
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("lichangtong");
		gc.setFileOverride(true); //是否覆盖已有文件
		gc.setBaseResultMap(true); //XML是否需要BaseResultMap
		gc.setBaseColumnList(true); //XML是否显示字段
		gc.setControllerName("%sController");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setSwagger2(true);
		gc.setIdType(IdType.AUTO);
		gc.setBaseResultMap(true);
		gc.setSwagger2(true); // 实体属性 Swagger2 注解
		gc.setFileOverride(false);

		InjectionConfig injectionConfig = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>();
				// 用来作为类注释的时间，模板中通过${cfg.datetime}获取
				map.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				this.setMap(map);
			}
		};
		mpg.setGlobalConfig(gc);
		mpg.setCfg(injectionConfig);
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/nacos?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
		// dsc.setSchemaName("public");
//		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("1234567");
		mpg.setDataSource(dsc);

		//策略配置
		StrategyConfig sc = new StrategyConfig();

//        sc.setExclude();//不需要生成的表  可以用正则表达式排除掉
	/*	String[] includeTables = new String[]{"mumway_crm_bafe_data_collection_master",
				"mumway_crm_allot_data_collection_master", "mumway_master", "mumway_report_work_sell", "mumway_store", "mumway_city", "mumway_masterfrom", "mumway_masterneed"
		};
		sc.setInclude(includeTables);*/
//		sc.setTablePrefix("tc_"); //表名前缀
		sc.setSkipView(true);
		sc.setNaming(NamingStrategy.underline_to_camel); //表名生成策略
		sc.setEntityBuilderModel(true);
		sc.setEntityLombokModel(true);
		sc.setEntitySerialVersionUID(true);
		sc.setEntityTableFieldAnnotationEnable(true);
		mpg.setStrategy(sc);

		//包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.demo.alibaba");
		pc.setEntity("entity");
		pc.setController("controller");
//		pc.setService("manager");
		pc.setService("service");
//		pc.setServiceImpl("manager.Impl");
		pc.setServiceImpl("service.Impl");
		pc.setMapper("mapper");
		pc.setXml("mapper");
		mpg.setPackageInfo(pc);

		mpg.execute();
	}
}
