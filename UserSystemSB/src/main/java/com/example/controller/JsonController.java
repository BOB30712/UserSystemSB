package com.example.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.User;

@CrossOrigin
@Controller
public class JsonController {
	
	@GetMapping("/json")
	public String json() {
		return "Json";
	}
	//前端發送GET請求--->回傳字串
	//@CrossOrigin(origins = "http://localhost:8080")
	//@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getString")
	@ResponseBody
	public String retrunString() {
		return "回傳字串...成功";
	}
	//前端發送GET請求--->回傳map
	@GetMapping("/getMap")
	@ResponseBody
	public Map retrunMap() {
		Map map=new HashMap<String, String>();
		map.put("姓名","王曉明");
		map.put("生日","2023/01/03");
		map.put("專長","打籃球");
		return map;
	}
	//前端發送GET請求--->回傳物件
	@GetMapping("/getUser")
	@ResponseBody
	public User returnUser() {
		Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime());
		User user=new User();
		user.setAccountnumber("USER202301");
		user.setPassword("20230105");
		user.setMemberlevel("GOLD");
		user.setCreateddate(ts);
		user.setUpdatedate(ts);
		System.out.println(user.toString());
		return user;
	}
	
	//前端發送POST請求--->取得並回傳字串
	@PostMapping("/postString")
	@ResponseBody
	public String postString(@RequestParam(name = "name", 
	defaultValue = "xxx") String name) {
		System.out.println("你好!我是 "+name);
		return "postString...請求成功";
	}
	
	//前端發送POST請求--->取得Map並回傳字串
	@PostMapping("/postMap")
	@ResponseBody
	public String postMap(@RequestParam Map<String,Object> params) {
		System.out.println("姓名: "+params);
		System.out.println("密碼: "+params.get("password"));
		return "postMap...請求成功";
	}
	
	//前端發送POST請求--->取得User並回傳字串
	@PostMapping("/postUser")
	@ResponseBody
	public String postUser(@RequestBody Map params) {
		System.out.println("使用者資料: "+params);
		return "postUser--->請求成功"; 
	}
	
	//前端發送POST請求--->取得User並回傳字串
	@PostMapping("/postUser2")
	@ResponseBody
	//public String postUser2(@RequestParam(name = "Name") String Name,
			//@RequestParam(name = "Date") @DateTimeFormat(pattern="MM/dd/yyyy") Date Date) {
	public String postUser2(@RequestBody Map params) {
		//System.out.println("Name: "+Name);
		//System.out.println("Date: "+Date);
		System.out.println("資料: "+params);
		return "postUser2--->請求成功"; 
	}
	
}
