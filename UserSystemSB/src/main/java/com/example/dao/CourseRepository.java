package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Course;
import com.example.model.Teacher;

public interface CourseRepository extends JpaRepository<Course, String>{

}
