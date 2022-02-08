package com.maciek.invoiceApp.service;

import com.maciek.invoiceApp.domian.Invoice;
import com.maciek.invoiceApp.repo.InvoiceRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceService {
    @Autowired
    InvoiceRepo invoiceRepo;

    public Invoice saveInvoice(final Invoice invoice){
        return invoiceRepo.save(invoice);
    }

    public List<Invoice> findAllInvoices() {
        return invoiceRepo.findAll();

    }

    public void deleteInvoice(Long id) {
        invoiceRepo.deleteById(id);
    }

    public Optional<Invoice> findInvoiceById(Long id) {
        return invoiceRepo.findById(id);
    }

}
