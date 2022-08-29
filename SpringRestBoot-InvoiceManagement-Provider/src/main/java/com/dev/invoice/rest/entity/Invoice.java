package com.dev.invoice.rest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Double amount;
	private Double finalAmount;
	private String number;
	private String receivedDate;
	private String type;
	private String vendor;
	private String comments;
}
