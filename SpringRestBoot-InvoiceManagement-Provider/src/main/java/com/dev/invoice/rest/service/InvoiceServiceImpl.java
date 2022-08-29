package com.dev.invoice.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.invoice.rest.entity.Invoice;
import com.dev.invoice.rest.exception.InvoiceNotFoundException;
import com.dev.invoice.rest.repository.InvoiceRepository;
import com.dev.invoice.rest.util.InvoiceUtil;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository repo;

	@Autowired
	private InvoiceUtil util;

	@Override
	public Long saveInvoice(Invoice invoice) {
		util.CalculateFinalAmountIncludingGST(invoice);
		Long id = repo.save(invoice).getId();
		return id;
	}

	@Override
	public List<Invoice> getAllInvoice() {
		List<Invoice> list = repo.findAll();
		// JDK 1.8 List Sort (using Comparator)
		list.sort((ob1, ob2) -> ob1.getId().intValue() - ob2.getId().intValue());
		return list;
	}

	@Override
	public Invoice getOneInvoice(Long id) {
		Invoice invoice = repo.findById(id)
				.orElseThrow(() -> new InvoiceNotFoundException("Product '" + id + "' not existed"));
		return invoice;
	}

	@Override
	public void updateInvoice(Invoice invoice) {
		util.CalculateFinalAmountIncludingGST(invoice);
		repo.save(invoice);
	}

	@Override
	public void deleteInvoice(Long id) {
		Invoice invoice = repo.getById(id);
		repo.delete(invoice);
	}

	@Override
	public boolean isInvoiceExist(Long id) {
		return repo.existsById(id);
	}

	@Override
	@Transactional()
	public Integer updateInvoiceNumberById(String number, Long id) {
		if (!repo.existsById(id)) {
			throw new InvoiceNotFoundException(
					new StringBuffer().append("Invoice '").append(id).append(" ' not exist").toString());
		}
		return repo.updateInvoiceNumberById(number, id);
	}
}
