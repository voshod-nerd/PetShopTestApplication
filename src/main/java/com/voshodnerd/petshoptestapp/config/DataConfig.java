/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.config;

import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 *
 * @author Талалаев
 */
@Configuration
@MapperScan("com.voshodnerd.petshoptestapp.mapper")
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUsername("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/test");
        dataSource.setPassword("19800123");

        // create a table and populate some data
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("Creating tables");
        jdbcTemplate.execute("drop table IF EXISTS city ");
        jdbcTemplate.execute("drop table IF EXISTS country ");
        jdbcTemplate.execute("CREATE TABLE country (id serial Primary key, country_name varchar) ");
        jdbcTemplate.execute("CREATE TABLE city  (id SERIAL Primary key,city_name varchar,idcountry integer REFERENCES country (id))");
        jdbcTemplate.execute("insert into country (country_name) values ('Norway')");
        jdbcTemplate.execute("insert into country (country_name) values ('USA')");
        jdbcTemplate.execute("insert into country (country_name) values ('UK')");
        jdbcTemplate.execute("insert into city (city_name,idcountry) values ('New York',2)");
        jdbcTemplate.execute("insert into city (city_name,idcountry) values ('Washington',2)");
        jdbcTemplate.execute("insert into city (city_name,idcountry) values ('San Francisco',2)");
        jdbcTemplate.execute("insert into city (city_name,idcountry) values ('Oslo',1)");
        jdbcTemplate.execute("insert into city (city_name,idcountry) values ('London',3)");
        jdbcTemplate.execute("insert into city (city_name,idcountry) values ('Liverpool',3)");

        System.out.println("All done");

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.voshodnerd.petshoptestapp.model");

        return sessionFactory;
    }

    /*  @Bean
    public CountryMapper userMapper() throws Exception {
      SqlSessionTemplate sessionTemplate = new SqlSessionTemplate((SqlSessionFactory) sqlSessionFactory());
      return sessionTemplate.getMapper(CountryMapper.class);
    }
     */
}
