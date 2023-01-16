package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.CourseRepository;
import com.example.dao.ScRepository;
import com.example.dao.StudentRepository;
import com.example.dao.TeacherRepository;
import com.example.model.Teacher;

@Controller
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ScRepository scRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/getT001")
	@ResponseBody
	public ResponseEntity getTeacher() {
		System.out.println("老師資料:");
		
		return ResponseEntity.status(HttpStatus.OK).body(teacherRepository.findById("T003"));
	}
	
	@GetMapping("/getSID1")
	@ResponseBody
	public ResponseEntity getSc() {
		System.out.println("分數資料:");
		
		return ResponseEntity.status(HttpStatus.OK).body(scRepository.findById(1));
	}
	
	@GetMapping("/getA01")
	@ResponseBody
	public ResponseEntity getStudent() {
		System.out.println("學生資料:");
		
		return ResponseEntity.status(HttpStatus.OK).body(studentRepository.findById("A01"));
	}
	
	
	
	
	
}
