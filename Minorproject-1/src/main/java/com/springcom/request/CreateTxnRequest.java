package com.springcom.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTxnRequest {
	  @NotBlank(message = "Student Contact must not be blank")
	    private String studentContact;
	    @NotBlank(message = "BookNo must not be blank")
	    private String bookNo;
	    @NotNull(message = "Paid Amount must not be null")
	    @Positive(message = "Amount should be positive")
	    private Integer paidAmount;

}
