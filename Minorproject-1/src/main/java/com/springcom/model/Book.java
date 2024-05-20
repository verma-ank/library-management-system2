package com.springcom.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Book implements Serializable{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    @Column(length = 30)
	    private String name;
	    private int cost;
	    @ManyToOne
	    @JoinColumn
	  //  @JsonIgnoreProperties("bookList")
	  //  @JsonIgnore
	    private Author author;
	 // just to keep a track which student took which book
	    private String bookNo; 
	    @Enumerated(value = EnumType.STRING)
	    private BookType type; // 1000
	    @JoinColumn
	    @ManyToOne
	    @JsonIgnoreProperties("booksList")
	    private Student student;
	    
	  
}