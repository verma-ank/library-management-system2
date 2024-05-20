package com.springcom.request;

import com.springcom.model.Author;
import com.springcom.model.Book;
import com.springcom.model.BookType;

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
public class CreateBookRequest {
	   private String bookName;
	    private int cost;
	    private BookType type;

	    private String bookNo;
	    private String authorName;

	    private String authorEmail;
	   

		public Book to() {
			Author authorData = Author.builder().name(this.authorName).email(this.authorEmail).build();
			return Book.builder().bookNo(this.bookNo).name(this.bookName).type(this.type).cost(this.cost)
					.author(authorData).build();
		}

}
