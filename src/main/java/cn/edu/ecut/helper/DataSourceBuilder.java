package cn.edu.ecut.helper;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.beanutils.BeanUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DataSourceBuilder {
	
	public static void main(String[] args) throws Exception {
		DataSource ds = getDataSource() ;
		System.out.println( ds.getConnection()  );
	}
	
	private static DataSource dataSource ;
	
	private static void load( String config ) {
		InputStream in = DataSourceBuilder.class.getResourceAsStream( config );
		Properties props = new Properties();
		try {
			props.load( in );
			dataSource = new DruidDataSource() ;
			BeanUtils.copyProperties( dataSource , props );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource() {
		load( "/config.properties" );
		return dataSource ;
	}
	
	public static DataSource getDataSource( String path ) {
		load( "/" + path );
		return dataSource ;
	}

}
