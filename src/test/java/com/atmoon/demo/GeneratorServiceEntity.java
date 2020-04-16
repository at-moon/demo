package com.atmoon.demo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeneratorServiceEntity {

    @Autowired
    private Environment environment;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Test
    public void generateCode() {
        String packageName = "com.atmoon.demo";
        String[] s = new String[]{"sys_menu"};
        generateByTables(packageName, s);
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(environment.getProperty("spring.datasource.url"))
                .setUsername(environment.getProperty("spring.datasource.username"))
                .setPassword(environment.getProperty("spring.datasource.password"))
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                // 生成实体类使用lombok模式
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                // 表名下划线转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                // 需要生成全部表时去掉这一行
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("zy")
                // 直接设置你代码保存的位置就不用复制了
                .setOutputDir("D:\\IdeaProjects\\demo\\src\\main\\java")
                .setFileOverride(true)
                // 设置主键类型
                .setIdType(IdType.AUTO)
                // 是否开启二级缓存
                .setEnableCache(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}
