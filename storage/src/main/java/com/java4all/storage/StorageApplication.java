package com.java4all.storage;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author IT云清
 */
@SpringBootApplication
@MapperScan("com.java4all.storage.dao")
public class StorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		return dataSource;
	}



	@Primary
	@Bean("dataSourceProxy")
	public DataSourceProxy dataSourceProxy(DataSource dataSource){
		return new DataSourceProxy(dataSource);
	}


	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy)throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSourceProxy);
		return sqlSessionFactoryBean.getObject();
	}

}
