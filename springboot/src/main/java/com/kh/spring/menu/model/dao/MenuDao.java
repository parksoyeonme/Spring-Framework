package com.kh.spring.menu.model.dao;

import java.util.List;

import com.kh.spring.menu.model.vo.Menu;

public interface MenuDao {

	List<Menu> selectMenuList();


	int insertMenu(Menu menu);

	Menu selectOneMenu(int id);

	int updateMenu(Menu menu);

	int deleteMenu(int id);

	List<Menu> selectMenuListByType(String type);

	

	

}
