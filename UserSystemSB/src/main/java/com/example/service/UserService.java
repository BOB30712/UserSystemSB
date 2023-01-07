package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserService {
	
	public User searchUser(String account);
	
	public List<User> getAllUser();
	
	public boolean addUser(User u);
	
	public boolean updateUser(User u);
	
	public User checkUser(String account,String password);
}
