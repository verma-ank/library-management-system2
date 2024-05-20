package com.springcom.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Entity
public class Student implements Serializable {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(length = 30)
	    private String name;
	    @Column(length = 30)
	    private String address;
	    @Column(nullable = false, unique = true)
	    private String contact;
	    @Column(length = 30,unique = true)
	    private String email;
	    @CreationTimestamp
	    private Date createdOn;
	    @UpdateTimestamp
	    private Date updatedOn;
	    @OneToMany(mappedBy = "student")
	    private List<Book> booksList;
	    @Enumerated
	    private StudentType studentType;
}
