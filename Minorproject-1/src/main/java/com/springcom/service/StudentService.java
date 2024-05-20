package com.springcom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcom.model.OperationType;
import com.springcom.model.Student;
import com.springcom.model.StudentFilterType;
import com.springcom.repositry.StudentRepositry;
import com.springcom.request.CreateStudentRequest;

@Service
public class StudentService {
	@Autowired
	private StudentRepositry studentRepositry;
	
	public List<Student> findStudent(StudentFilterType studentFilterType, String value, OperationType operationType) {
		switch (operationType) {
		case Equals:
			switch (studentFilterType) {
			case EMAIL:
				return studentRepositry.findByEmail(value);
			case CONTACT:
				return studentRepositry.findByContact(value);
			}
		default:
			return new ArrayList<>();
		}

	}

	public void create(CreateStudentRequest createStudentRequest) {
		List<Student> oldStudents = findStudent(StudentFilterType.CONTACT, createStudentRequest.getContact(),
				OperationType.Equals);
		if (oldStudents == null || oldStudents.isEmpty()) {
			Student student = createStudentRequest.to();
			studentRepositry.save(student);
		} else {
			oldStudents.get(0);
		}

	}

	

}

