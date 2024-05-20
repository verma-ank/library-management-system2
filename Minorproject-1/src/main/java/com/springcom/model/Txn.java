package com.springcom.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Txn {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	 private String txnId;
	    @ManyToOne
	    @JoinColumn
	    private Student student;
	    @ManyToOne
	    @JoinColumn
	    private Book book;
	    private int paidCost;
	    @Enumerated(value = EnumType.STRING)
	    private TxnStatus txnStatus;

	    @CreationTimestamp
	    private Date createdOn;
	    @UpdateTimestamp
	    private Date updatedOn;

}
