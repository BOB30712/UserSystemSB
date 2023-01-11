package com.example.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.model.User;
import com.example.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@CrossOrigin
@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
    private JavaMailSender mailSender;
	
	//帳號登入
	//第一步，取得輸入的帳號、密碼
	//第二步，與資料庫比對是否相同
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody Map<String,String> Data) {
		System.out.println(Data);
        //md5加密密碼
        String md5password=DigestUtils.md5DigestAsHex(Data.get("password").getBytes());
		User user=userService.checkUser(Data.get("accountnumber"), md5password);
		if(user==null) {
			System.out.println("使用者不存在");
			return "資料有誤";
		}else {
			System.out.println("使用者"+user.toString());
			//token期限為1分鐘
			Date expireDate = new Date(System.currentTimeMillis()+ 1 * 60 * 1000);
			String jwtToken = Jwts.builder()
					.setSubject(Data.get("accountnumber"))
					.setExpiration(expireDate)
					.signWith(SignatureAlgorithm.HS512,"MySecret")
					.compact();
			return jwtToken;
		}
	}
	
	//使用者資料輸出
	@PostMapping("/getResult")
	@ResponseBody
	public User getResult(@RequestHeader HttpHeaders headers,HttpServletRequest request){
		//System.out.println("Cookie CORS設置");--->失敗
		String authorHeader =  request.getHeader(AUTHORIZATION);
		//System.out.println(headers);
		//System.out.println(authorHeader);
		try{
	        String token = authorHeader;
	        Claims claims = Jwts.parser().setSigningKey("MySecret")
	        .parseClaimsJws(token).getBody();
	
	        //System.out.println("JWT payload:"+claims.toString());
	        //System.out.println("JWT sub:"+(String)(claims.get("sub")));
	        User user=userService.searchUser((String)(claims.get("sub")));
        
	        return user;
        
		}catch (Exception e) {
			System.err.println("Error : "+e);
			return null;
		}
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
        //md5加密密碼
        String md5password=DigestUtils.md5DigestAsHex(Data.get("password").getBytes());
        user.setPassword(md5password);
        user.setUseremail(Data.get("useremail"));
        user.setMemberlevel("GOLD");
        user.setCreateddate(ts);
        user.setUpdatedate(ts);

        if(userService.addUser(user)) {
			//token期限為1分鐘
			Date expireDate = new Date(System.currentTimeMillis()+ 1 * 60 * 1000);
			String jwtToken = Jwts.builder()
					.setSubject(user.getAccountnumber())
					.setExpiration(expireDate)
					.signWith(SignatureAlgorithm.HS512,"MySecret")
					.compact();
			return jwtToken;
        }
		
		return "資料有誤";
	}
	
	//確認是否有重複帳號
	@PostMapping("/checkAccount")
	@ResponseBody
	public String checkAccount(@RequestBody Map<String,String> Data) {
		User user=userService.searchUser(Data.get("accountnumber"));
		System.out.println("user:"+user);
		System.out.println("Data:"+Data.get("accountnumber"));
		if(user==null) {
			System.out.println("帳號驗證成功");
			return "帳號驗證成功";
		}else {
			System.out.println("帳號驗證失敗");
			return "帳號驗證失敗";
		}
	}
	
	//向信箱寄送驗證碼(4碼)
	//回傳驗證碼給前端，同時寄信給目標的信箱
	@PostMapping("/checkEmail")
	@ResponseBody
	public String checkEmail(@RequestBody Map<String,String> Data) {
		//產生隨機驗證碼
		String checkNumber="";
		while(checkNumber.length()<4){//隨機4位數密碼
			checkNumber+=(int)(Math.random()*10);//隨機0~9數字
		}
		//呼叫JavaMailSender
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("a0930023855@Outlook.com");
		message.setTo(Data.get("useremail"));
		message.setSubject("主旨：使用者系統信箱驗證");
		message.setText("內容：這是信箱驗證碼 "+checkNumber);
		mailSender.send(message);
		
		System.out.println("已傳送信件到"+Data.get("useremail"));
		return checkNumber;
	}
	
	//向信箱寄送新密碼(6碼)
	@PostMapping("/forgotPassword")
	@ResponseBody
	public String forgotPassword(@RequestBody Map<String,String> Data) {
		String newPassword="";
		while(newPassword.length()<6){//隨機6位數密碼
			newPassword+=(int)(Math.random()*10);//隨機0~9數字
		}
		
		User user=userService.searchUser(Data.get("accountnumber"));
		System.out.println("user:"+user);
		System.out.println("Data:"+Data.get("accountnumber"));
		if(user!=null) {
			System.out.println("帳號驗證成功");
			user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
			userService.updateUser(user);
			
			//呼叫JavaMailSender
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("a0930023855@Outlook.com");
			message.setTo(user.getUseremail());
			message.setSubject("主旨：使用者系統密碼更換");
			message.setText("內容：新密碼 "+newPassword);
			mailSender.send(message);
			
			return "已重新設定密碼";
		}else {
			System.out.println("帳號不存在");
			return "帳號不存在";
		}
	}
	
}
