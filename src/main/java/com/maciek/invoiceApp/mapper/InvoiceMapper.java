package com.maciek.invoiceApp.mapper;

import com.maciek.invoiceApp.domian.Invoice;
import com.maciek.invoiceApp.domian.dto.InvoiceDto;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceMapper {

    public Invoice mapToInvoice(final InvoiceDto invoiceDto) {
        return new Invoice(invoiceDto.getId(), invoiceDto.getInvoiceNumber(), invoiceDto.getInvoiceDate(), invoiceDto.getClientID());
    }

    public InvoiceDto mapToInvoiceDto(final Invoice invoice) {
        return new InvoiceDto(invoice.getId(), invoice.getInvoiceNumber(), invoice.getInvoiceDate(), invoice.getClientID());

    }

    public List<Invoice> mapToInvoiceList(final List<InvoiceDto> invoiceDtoList) {
        return invoiceDtoList.stream().map(this::mapToInvoice).collect(Collectors.toList());
    }

    public List<InvoiceDto> mapToInvoiceDtoList(final List<Invoice> invoiceList) {
        return invoiceList.stream().map(this::mapToInvoiceDto).collect(Collectors.toList());
    }
}
