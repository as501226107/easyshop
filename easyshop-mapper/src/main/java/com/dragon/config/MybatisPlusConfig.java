package com.dragon.config;

import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootConfiguration
public class MybatisPlusConfig {

    @Bean
    @ConditionalOnMissingBean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        MybatisSqlSessionFactoryBean factoryBean=new MybatisSqlSessionFactoryBean();
        //设置连接池
        factoryBean.setDataSource(dataSource);
       //配置mybatis配置文件
         ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource mybatisConfigXml = resolver.getResource("classpath:mybatis-config.xml");
//        //设置mapper扫描位置
//        Resource mybatisMapperXml = resolver.getResource("classpath*:com/dragon/mapper/*.xml");
//        factoryBean.setMapperLocations(new Resource[]{mybatisMapperXml});
//        factoryBean.setConfigLocation(mybatisConfigXml);
        //设置别名扫描包
        factoryBean.setTypeAliasesPackage("com.dragon.bean");
        return factoryBean;
    }
}
