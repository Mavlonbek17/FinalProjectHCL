package com.example.chatbot.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyDateSource {

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