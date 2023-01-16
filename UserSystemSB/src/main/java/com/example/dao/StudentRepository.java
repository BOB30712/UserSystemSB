package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Student;

public interface StudentRepository extends JpaRepository<Student, String>{

}
