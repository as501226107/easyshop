package com.dragon.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootConfiguration
@PropertySource(value = {"jdbc.properties"})
public class DatasourceConfig {
    @Value("${user}")
    private String username;
    @Value("${driver}")
    private String driverClass;
    @Value("${url}")
    private String url;
    @Value("${password}")
    private String pass;
    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource ds=new DruidDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(pass);
        return ds;
    }
}
