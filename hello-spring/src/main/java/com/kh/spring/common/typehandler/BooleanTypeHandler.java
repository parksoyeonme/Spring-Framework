package com.kh.spring.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MappedTypes(boolean.class)
@MappedJdbcTypes(JdbcType.CHAR)
public class BooleanTypeHandler implements TypeHandler<Boolean> {
	
    public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
         String s = rs.getString(columnName);

         return parseBoolean(s);
     }

     public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
         String s = rs.getString(columnIndex);

         return parseBoolean(s);
     }

     public Boolean getResult(CallableStatement cs, int columnIndex)
         throws SQLException {
         String s = cs.getString(columnIndex);

         return parseBoolean(s);
     }

     public void setParameter(PreparedStatement ps, int i, Boolean bool,
         JdbcType jdbcType) throws SQLException {

         ps.setString(i, parseString(bool));
     }

     private boolean parseBoolean(String s) {
         if (s == null) {
             return false;
         }

         s = s.trim().toUpperCase();

         if (s.length() == 0) {
             return false;
         }

         return "Y".equals(s);
     }

     private String parseString(Boolean bool) {
         return (bool != null && bool == true) ? "Y" : "N";
     }

 }