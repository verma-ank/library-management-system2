package com.springcom.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.junit.Test;

import com.springcom.exception.TxnServiceException;
import com.springcom.repositry.TxnRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestTxnService {
@Mock
private StudentService studentService;

@Mock
private BookService bookService;

@Mock
private TxnRepository txnRepository;
@InjectMocks
private TxnService txnService;
@SuppressWarnings("deprecation")
@Before
public void setUp() {
	MockitoAnnotations.initMocks(new TestTxnService());
	 ReflectionTestUtils.setField(txnService, "validDays", "14");
     ReflectionTestUtils.setField(txnService, "finePerDay", 2);
}
@Test(expected =TxnServiceException.class)
public void testFindStudentWithNullCheck() throws TxnServiceException {
    Mockito.when(studentService.findStudent(any(),eq("8168139116"), any())).thenReturn(null);
    Mockito.when(studentService.findStudent(any(),eq("8168139119"), any())).thenReturn(new ArrayList<>());
    System.out.println(txnService.findStudent("8168139119"));
}
}
