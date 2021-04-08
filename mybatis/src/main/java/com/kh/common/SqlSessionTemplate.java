package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {

	public static SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml"; 
		
		try {
			InputStream is = Resources.getResourceAsStream(resource); //ibatis로 import
			SqlSessionFactoryBuilder builder = 
					new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			session = factory.openSession(false);
			//auto commit여부
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}

	
}