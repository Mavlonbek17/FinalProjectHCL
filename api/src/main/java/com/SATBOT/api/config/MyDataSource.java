package com.SATBOT.api.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyDataSource {

    @Bean
    public DataSource dataSource(){

        DataSourceBuilder db = DataSourceBuilder.create();
        db.driverClassName("com.mysql.cj.jdbc.Driver");
        db.url("jdbc:mysql://localhost:3306/mbot");
        db.username("root");
        db.password("MySql1705");
        return db.build();
    }
}