package com.dev.invoice.rest.service;

import java.util.List;

import com.dev.invoice.rest.entity.Invoice;

public interface InvoiceService {

	/**
	 * Takes Invoice Object as input and returns PK generated
	 */
	public Long saveInvoice(Invoice invoice);

	/**
	 * select all rows and provides result as a List<Invoice>
	 */
	public List<Invoice> getAllInvoice();

	/**
	 * Takes id as input and returns one row as one object
	 */
	public Invoice getOneInvoice(Long id);

	/**
	 * Takes existing Invoice data as input and updates values
	 */
	public void updateInvoice(Invoice invoice);

	/**
	 * Takes PK(ID) as input and deletes Invoice Object data
	 */
	public void deleteInvoice(Long id);

	/**
	 * Takes Id as input,checks if record exists returns true, else false
	 * 
	 */
	public boolean isInvoiceExist(Long id);

	/**
	 * Takes 2 fields as input, updates Invoice data as provided where clause like
	 * 'UPDATE Invoice SET number=:number WHERE id=:id'
	 */
	public Integer updateInvoiceNumberById(String number, Long id);
}
