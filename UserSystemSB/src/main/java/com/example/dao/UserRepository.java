package com.example.dao;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("from User where accountnumber=:account")
	User findbyaccount(@Param("account") String account);
	
	@Transactional
	@Modifying
	@Query("update User set username=:name, password=:password, memberlevel=:level where accountnumber=:account")
	int updateuser(@Param("name") String username,
					@Param("password") String password,
					@Param("level") String memberlevel,
					@Param("account") String account);
	@Transactional
	@Modifying
	@Query("delete User where accountnumber=:account")
	void deleteuserbyaccount(@Param("account") String account);
	/*
	//使用原生的sql语句
	//userRepository.save(user);
	@Transactional
	@Modifying
	@Query(value = "insert into User_jpa"
			+ " values (:userno, :username, :accountnumber, :password, :useremail, :level, :create, :update)",nativeQuery = true)
	void adduser(@Param("userno") int userno,
				@Param("username") String username,
				@Param("accountnumber") String accountnumber,
				@Param("password") String password,
				@Param("useremail") String useremail,
				@Param("level") String memberlevel,
				@Param("create") Timestamp create,
				@Param("update") Timestamp update);
				*/
}
