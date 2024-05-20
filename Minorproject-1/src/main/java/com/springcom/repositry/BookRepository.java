package com.springcom.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcom.model.Book;
import com.springcom.model.BookType;

public interface BookRepository extends JpaRepository<Book, Integer>{
	List<Book>findByBookNo(String bookNo);
	List<Book>findByAuthor_Name(String authorName);
	List<Book>findByCost(int cost);
	
	List<Book>findByType(BookType type);
	List<Book>findByCostLessThan(Integer cost);
	List<Book>findByCostGreaterThan(Integer cost);

}
