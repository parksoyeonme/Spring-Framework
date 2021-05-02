package com.kh.spring.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.menu.model.service.MenuService;
import com.kh.spring.menu.model.vo.Menu;

import lombok.extern.slf4j.Slf4j;

/**
 * @RestController 모든 메소드가 @ResponseBody 어노테이션 처리
 */
@RestController
@Slf4j
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menus")
	public List<Menu> menus(){
		log.debug("/menus 요청!");
		List<Menu> list = menuService.selectMenuList();
		log.debug("list = {}", list);
		return list;
	}
	
	@GetMapping("/menuType/{type}")
	public List<Menu> menuType(@PathVariable("type") String type){
		log.debug("type = {}", type);
		List<Menu> list = menuService.selectMenuTypeList(type);
		return list;
	}
}



