package com.example.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.UserRepository;
import com.example.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
public class TestController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private JavaMailSender mailSender;

	@GetMapping("/name")
	@ResponseBody
	public String name() {
		for(User u:userRepository.findAll()) {
			System.out.println(u);
		}
		return "hello bobb";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	//根據userno查詢
	@GetMapping("/nameno")
	@ResponseBody
	public String getuserbyno() {
		System.out.println("byNo:"+userRepository.getById(1));
		return "nameno";
	}
	
	//根據account查詢
	@GetMapping("/account")
	@ResponseBody
	public String getuserbyaccount(){
		System.out.println("byAccount:"+userRepository.findbyaccount("admin"));
		return "account";
	}
	
	//更新password
	@GetMapping("/updateuser")
	@ResponseBody
	public String updateuser(){
		System.out.println("after:"+userRepository.updateuser("王曉明","user1226","sliver","admin"));
		//System.out.println("after:"+userRepository.findbyaccount("admin"));
		return "updateuser";
	}
	
	//新增user
	@GetMapping("/adduser")
	@ResponseBody
	public String adduser(){
		 Date date = new Date();  
         Timestamp ts=new Timestamp(date.getTime());  
         User user=new User();
         user.setAccountnumber("admin4");
         user.setCreateddate(ts);
         user.setMemberlevel("admin4");
         user.setPassword("admin4");
         user.setUpdatedate(ts);
         user.setUseremail("admin4");
         user.setUsername("admin4");
         user.setUserno(4);
		//userRepository.adduser(3,"admin3","admin3","adminpassword","@gmail.com","GOLD",ts,ts);
        //userRepository.adduser(user);
        User user2=userRepository.save(user);
        System.out.println("UPDATE_TEST:"+user2.toString());
		return "adduser";
	}
	
	
	//刪除user
	@GetMapping("/deleteuser")
	@ResponseBody
	public String deleteuser(){
		userRepository.deleteuserbyaccount("admin4");
		return "deleteuser";
	}
	
	//傳送email
	@GetMapping("/sendemail")
	@ResponseBody
	public String sendemail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("a0930023855@Outlook.com");
		message.setTo("a0930023855@Outlook.com");
		message.setSubject("主旨：Hello 你好嗎!");
		message.setText("內容：這是一封測試信件，恭喜您成功發送了唷，恭喜你啊!!!");
		
		mailSender.send(message);
		return "sendemail";
	}
	
	
	//JWT測試
	@GetMapping("/getJWT")
	@ResponseBody
	public String getJWT() {
		Date expireDate = new Date(System.currentTimeMillis()+ 1 * 60 * 1000);
		String jwtToken = Jwts.builder()
				.setSubject("test")
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512,"MySecret")
				.compact();
		return "取得JWT成功，恭喜你啊:"+"\n"+jwtToken;
	}
	
	@PostMapping("/testlogin")
	@ResponseBody
	public String testlogin(@RequestHeader HttpHeaders headers,HttpServletRequest request) {
		System.out.println(headers);
		String authorHeader =  request.getHeader(AUTHORIZATION);
		System.out.println(authorHeader);
		try{
        String token = authorHeader;
        Claims claims = Jwts.parser().setSigningKey("MySecret")
        .parseClaimsJws(token).getBody();

        System.out.println("JWT payload:"+claims.toString());
		}catch (Exception e) {
			System.err.println("Error : "+e);
			return "testlogin失敗，怎麼會這樣";
		}
		return "testlogin成功，恭喜你啊:";
	}
	
}
