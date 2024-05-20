package com.springcom.request;

import com.springcom.model.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateStudentRequest {
	private String name;
	private String address;
	private String email;
	private String contact;
	public Student to() {
		return Student.builder().name(this.name).address(this.address).email(this.email).contact(this.contact)
				.build();
	}
}
