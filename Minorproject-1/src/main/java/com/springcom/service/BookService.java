package com.springcom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springcom.model.Author;
import com.springcom.model.Book;
import com.springcom.model.BookFilterType;
import com.springcom.model.BookType;
import com.springcom.model.OperationType;
import com.springcom.repositry.AuthorRepository;
import com.springcom.repositry.BookRepository;
import com.springcom.request.CreateBookRequest;

@Service
public class BookService {
	@Autowired
	public BookRepository bookRepository;
	@Autowired
	public AuthorRepository authorRepository;
	   @Autowired
	    private RedisTemplate redisTemplate;

	    private static final String BOOK_PREFIX_KEY ="book:";
	
	 public void create(CreateBookRequest createBookRequest) {
	        Book book = createBookRequest.to();
	     // condition that we need to check whether we need to save it in our db or not
	        // here u need to have same method present in author repo which will tell
	        
	        Author authorFromDB = authorRepository.getAuthorWithMailAddress(book.getAuthor().getEmail());
	        if(authorFromDB == null){
	            authorFromDB = authorRepository.save(book.getAuthor());
	        }
	        book.setAuthor(authorFromDB);
	        bookRepository.save(book);
	        // i will save the same data to redis
	        pushDataToRedisByBookNo(book);
	 }
	  private void pushDataToRedisByBookNo(Book book){
	        if(book != null){
	            redisTemplate.opsForValue().set(BOOK_PREFIX_KEY+book.getBookNo(), book,10,TimeUnit.MINUTES);
	        }
	    }

		@SuppressWarnings("incomplete-switch")
		public List<Book> findAllBooks(BookFilterType bookFilterType, String value, OperationType operationType) {
			switch (operationType) {
			case Equals:
				switch (bookFilterType) {
				case book_no:
					// before hitting directly to database
                    // u need to check first at redis
                    Book bookWithPrefix = (Book)redisTemplate.opsForValue().get(BOOK_PREFIX_KEY+value);
                    if(bookWithPrefix != null){
                    	System.out.println("cache data---->>>>>>>>>>>");
                        List<Book> list = new ArrayList<>();
                        list.add(bookWithPrefix);
                        return list;
                    }
                    // book is not present in cache 
                    List<Book> bookList = bookRepository.findByBookNo(value);
                    //keep it in the cache
                    pushDataToRedisByBookNo(bookList != null ? bookList.get(0) : null );
                    return bookList;
					//return bookRepository.findByBookNo(value);
				case author_name:
					return bookRepository.findByAuthor_Name(value);
				case cost:
					return bookRepository.findByCost(Integer.valueOf(value));
				case Book_type:
					return  bookRepository.findByType(BookType.valueOf(value));
				}
			case Less_Than:
				switch (bookFilterType) {
				case cost:
					return bookRepository.findByCostLessThan(Integer.valueOf(value));
				}

			case Greater_Than:
				switch (bookFilterType) {
				case cost:
					return bookRepository.findByCostGreaterThan(Integer.valueOf(value));
				}

			default:
				return new ArrayList<>();
			}
		}
		 public void createUpdate(Book book) {
		        bookRepository.save(book);
		 }
}
