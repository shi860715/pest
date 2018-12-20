package com.isoftstone.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MainDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mainSqlSessionFactory")
public class MainDataSourceConfig {

    // 精确到 main 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.isoftstone.mapper.main";
    static final String MAPPER_LOCATION = "classpath:mapper/main/*.xml";

    @Value("${main.datasource.url}")
    private String url;

    @Value("${main.datasource.username}")
    private String user;

    @Value("${main.datasource.password}")
    private String password;

    @Value("${main.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "mainDataSource")
    @Primary
    public DataSource mainDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "mainTransactionManager")
    @Primary
    public DataSourceTransactionManager mainTransactionManager() {
        return new DataSourceTransactionManager(mainDataSource());
    }

    @Bean(name = "mainSqlSessionFactory")
    @Primary
    public SqlSessionFactory mainSqlSessionFactory(@Qualifier("mainDataSource") DataSource mainDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mainDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MainDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}