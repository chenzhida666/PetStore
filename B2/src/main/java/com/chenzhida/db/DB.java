package com.chenzhida.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DB {
	
	static InputStream is=null;
	static DruidPooledConnection conn;
	static Properties pro=null;
	static{
		
		is=DB.class.getClassLoader().getResourceAsStream("druid.ini");
		pro=new Properties();
		
		try {
			pro.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static DruidPooledConnection getconnection(){
		
		
		try {
			DruidDataSource ds=(DruidDataSource)DruidDataSourceFactory.createDataSource(pro);
			conn=ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return conn;
	}
	
	
	
	
	
	public static void main(String[] args) {
		new DB();
	}
	
	
	
}
