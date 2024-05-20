package com.springcom.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springcom.exception.TxnServiceException;
import com.springcom.model.Book;
import com.springcom.model.BookFilterType;
import com.springcom.model.OperationType;
import com.springcom.model.Student;
import com.springcom.model.StudentFilterType;
import com.springcom.model.Txn;
import com.springcom.model.TxnStatus;
import com.springcom.repositry.TxnRepository;
import com.springcom.request.CreateReturnTxnRequest;
import com.springcom.request.CreateTxnRequest;
import com.springcom.response.TxnSettlementResponse;

@Service
public class TxnService {
@Autowired
private StudentService stuedentService;
@Autowired
private BookService bookService;
@Autowired
private TxnRepository txnRepository;
@Value("${student.valid.days}")
private String validDays;
@Value("${student.perday.fine}")
private Integer finePerDay;
	public String create(CreateTxnRequest createTxnRequest) throws TxnServiceException {
		Student student=findStudent(createTxnRequest.getStudentContact());
		   Book book = findBook(createTxnRequest.getBookNo());
		    Txn txn = Txn.builder().
	                student(student).
	                book(book).
	                txnId(UUID.randomUUID().toString()).
	                paidCost(createTxnRequest.getPaidAmount()).
	                txnStatus(TxnStatus.ISSUED).
	                build();
		    book.setStudent(student);
		    //saving the data of book to book table 1) query
		    bookService.createUpdate(book);
		  return  txnRepository.save(txn).getTxnId();
		
	}
  public Student findStudent(String studentContact) throws TxnServiceException {
	 List <Student> studentList=stuedentService.findStudent(StudentFilterType.CONTACT,studentContact , OperationType.Equals);
	 if (studentList == null || studentList.isEmpty()) {
         throw new TxnServiceException("Student does not exist in the library");
     }
     return studentList.get(0);
  }
  public Book findBook(String bookNo) throws TxnServiceException {
      List<Book> listBook = bookService.findAllBooks(BookFilterType.book_no,
              bookNo,
              OperationType.Equals);

      if(listBook == null || listBook.isEmpty() || listBook.get(0).getStudent() != null){
          throw new TxnServiceException("Book does not exist in the library");
      }
      return listBook.get(0);
  }
  public Book findBookForTxn(String bookNo) throws TxnServiceException {
      List<Book> listBook = bookService.findAllBooks(BookFilterType.book_no,
              bookNo,
              OperationType.Equals);

      if(listBook == null || listBook.isEmpty()){
          throw new TxnServiceException("Book does not exist in the library");
      }
      return listBook.get(0);
  }

	public TxnSettlementResponse returnBook(CreateReturnTxnRequest createReturnTxnRequest) throws TxnServiceException {
		Student student = findStudent(createReturnTxnRequest.getStudentContact());
		Book book = findBookForTxn(createReturnTxnRequest.getBookNo());
		Txn txn = txnRepository.findByStudent_ContactAndBook_BookNoAndTxnStatusOrderByCreatedOnDesc(
				student.getContact(), book.getBookNo(), TxnStatus.ISSUED);
		 int settlementAmount = calculateSettlementAmount(txn);
	        txn.setTxnStatus(settlementAmount == txn.getPaidCost() ? TxnStatus.RETURNED : TxnStatus.FINED);
	        txn.setPaidCost(settlementAmount);
	        txnRepository.save(txn);

	        // book available
	        book.setStudent(null);
	        bookService.createUpdate(book);
	        
	        return TxnSettlementResponse.builder().
	                txnId(txn.getTxnId()).
	                settlementAmount(settlementAmount).
	                build();
	}
	 public int calculateSettlementAmount(Txn txn){
	        // i can convert both dates into milliseconds
	        // check the diff
	        // convert it back to days
	        long issueTime = txn.getCreatedOn().getTime();
	        long returnTime = System.currentTimeMillis();

	        long diff = returnTime-issueTime;
	        int daysPassed = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	        if(daysPassed > Integer.valueOf(validDays)){
	            int amount = ((daysPassed-Integer.valueOf(validDays)) * finePerDay );
	            return txn.getPaidCost()-amount;

	        }
	        return txn.getPaidCost();

}
}