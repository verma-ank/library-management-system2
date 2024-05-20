package com.springcom.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcom.model.Student;


public interface StudentRepositry extends JpaRepository<Student, Integer> {
	List<Student> findByEmail(String email);
	List<Student> findByContact(String contact);

}
