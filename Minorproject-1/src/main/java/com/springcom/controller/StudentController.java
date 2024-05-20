package com.springcom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springcom.model.OperationType;
import com.springcom.model.Student;
import com.springcom.model.StudentFilterType;
import com.springcom.request.CreateStudentRequest;
import com.springcom.service.StudentService;

public class StudentController {
	@Autowired
	private StudentService studentService;
	
	 @PostMapping("/create")
	    public void create(@RequestBody CreateStudentRequest createStudentRequest) {
		 studentService.create(createStudentRequest);
	    }
	 @GetMapping("/find")
	  public List<Student> findStudent(@RequestParam("filter") StudentFilterType studentFilterType,
              @RequestParam("value") String value,
              @RequestParam("operation") OperationType operationType){
return studentService.findStudent(studentFilterType.CONTACT, value, operationType.Equals);

}
}
