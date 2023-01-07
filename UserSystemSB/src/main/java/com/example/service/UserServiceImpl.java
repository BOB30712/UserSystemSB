package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserRepository;
import com.example.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User searchUser(String account) {
		return userRepository.findbyaccount(account);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public boolean addUser(User u) {
		if(searchUser(u.getAccountnumber())==null) {
			userRepository.save(u);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(User u) {
		if(userRepository.updateuser(u.getUsername(),u.getPassword(), 
				u.getMemberlevel(), u.getAccountnumber())==1) {
			return true;
		}
		return false;
	}

	@Override
	public User checkUser(String account, String password) {
		// TODO Auto-generated method stub
		User user=searchUser(account);
		if(user==null) {
			return null;
		}
		if(user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
