package com.cdemo.dal.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @description:
 * @author: star
 * @time: 2021/8/6 10:56
 */
@Configuration
@MapperScan(basePackages = "com.cdemo.dal.daoslave", sqlSessionTemplateRef = "sqlSessionTemplateSlave")
public class DataSourceLdspSlave {

    @Autowired
    @Qualifier("dataSourceSlave")
    private DataSource dataSource;

/*    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }*/

    /**
     * @Qualifier("dataSourceSlave") DataSource dataSource
     * @author: col_star
     * @time: 2021/8/6 22:19
     */    
    @Bean(name = "sqlSessionFactorySlave")
    public SqlSessionFactory testSqlSessionFactory()
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapperslave/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "transactionManagerSlave")
    public DataSourceTransactionManager testTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplateSlave")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("sqlSessionFactorySlave") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
