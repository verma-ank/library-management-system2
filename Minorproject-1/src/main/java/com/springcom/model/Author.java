package com.springcom.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Author implements Serializable {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(length = 30)
	    private String name;

	    @Column(length = 30,unique = true , nullable = false)
	    private String email;
	    @OneToMany(mappedBy = "author")
	    @JsonIgnoreProperties("author")
	    private List<Book> bookList;

	    @CreationTimestamp
	    private Date createdOn;
	    @UpdateTimestamp
	    private Date updatedOn;

}