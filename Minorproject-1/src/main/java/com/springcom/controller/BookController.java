package com.springcom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcom.model.Book;
import com.springcom.model.BookFilterType;
import com.springcom.model.OperationType;
import com.springcom.request.CreateBookRequest;
import com.springcom.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	  @Autowired
	    private BookService bookService;

	    @PostMapping("/create")
	    public void create(@RequestBody CreateBookRequest createBookRequest) {
	        bookService.create(createBookRequest);
	    }
	    @GetMapping("/find")
	    public List<Book> findBooks(@RequestParam("filter")BookFilterType bookFilterType,@RequestParam("value")String value,@RequestParam("operation")OperationType operationType) {
	    	return  bookService.findAllBooks(bookFilterType,value,operationType);
	    }

}
