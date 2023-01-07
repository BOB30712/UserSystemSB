package com.example.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.model.User;
import com.example.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@CrossOrigin
@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	//帳號登入
	//第一步，取得輸入的帳號、密碼
	//第二步，與資料庫比對是否相同
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody Map<String,String> Data) {
		System.out.println(Data);
		User user=userService.checkUser(Data.get("accountnumber"), Data.get("password"));
		if(user==null) {
			System.out.println("使用者不存在");
			return "資料有誤";
		}else {
			System.out.println("使用者"+user.toString());
		}
		return Data.get("accountnumber");
	}
	
	//使用者資料輸出
	@PostMapping("/getResult")
	@ResponseBody
	public User getResult(@RequestBody Map<String,String> Data) {
		//System.out.println("Cookie CORS設置");--->失敗
		System.out.println("登入結果:"+Data.get("result"));
		User user=userService.searchUser(Data.get("result"));
		return user;
	}
	
	//註冊帳號
	@PostMapping("/Register")
	@ResponseBody
	public String Register(@RequestBody Map<String,String> Data) {
		System.out.println("Register資料:"+Data);
		//設定資料建立時間
		Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime()); 
		//建立新的使用者
        User user=new User();
        
        user.setUserno(userService.getAllUser().size());
        user.setUsername(Data.get("username"));
        user.setAccountnumber(Data.get("accountnumber"));
        user.setPassword(Data.get("password"));
        user.setUseremail(Data.get("useremail"));
        user.setMemberlevel("GOLD");
        user.setCreateddate(ts);
        user.setUpdatedate(ts);

        if(userService.addUser(user)) {
        	return user.getAccountnumber();
        }
		
		return "資料有誤";
	}
	
	//確認是否有重複帳號
	@PostMapping("/testaccount")
	@ResponseBody
	public String testAccount(@RequestBody Map<String,String> Data) {
		User user=userService.searchUser(Data.get("accountnumber"));
		if(user==null) {
			System.out.println("帳號驗證成功");
			return "帳號驗證成功";
		}else {
			System.out.println("帳號驗證失敗");
			return "帳號驗證失敗";
		}
	}
	
	
	
}
