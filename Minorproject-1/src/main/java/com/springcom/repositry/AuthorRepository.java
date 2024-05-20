package com.springcom.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springcom.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	// this is native query 
	   @Query(value = "select * from author where email =:email", nativeQuery = true)
	    Author getAuthorWithMailAddress(String email);
	   
	   
		/*
		 * @Query("select a from Author a where a.email = ?1") Author
		 * getAuthorWithMailAddressWithoutNative(String email);
		 */

	    //Author findByEmail(String email);

}
//not writing any query

//use some standards given by jpa( name the method in a way that jpa can understand and your query will be
//done by JPA

//find some author where name like a
