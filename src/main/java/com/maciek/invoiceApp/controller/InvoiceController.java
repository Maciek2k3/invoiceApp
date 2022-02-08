package com.maciek.invoiceApp.controller;

import com.maciek.invoiceApp.domian.Client;
import com.maciek.invoiceApp.domian.Invoice;
import com.maciek.invoiceApp.domian.dto.InvoiceDto;
import com.maciek.invoiceApp.mapper.InvoiceMapper;
import com.maciek.invoiceApp.service.InvoiceService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("invoice/v1")
@CrossOrigin(origins = "*")
public class InvoiceController {
    private InvoiceMapper invoiceMapper;
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceMapper invoiceMapper, InvoiceService invoiceService) {
        this.invoiceMapper = invoiceMapper;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/getInvoices")
    public List<InvoiceDto> getAllInvoices() {
        List<Invoice> invoicesList = invoiceService.findAllInvoices();
        return invoiceMapper.mapToInvoiceDtoList(invoicesList);
    }

    @GetMapping("/getInvoice")
    @ResponseBody
    public InvoiceDto getInvoice(@RequestParam long id) throws NotFoundExeption {
        return invoiceMapper.mapToInvoiceDto(invoiceService
                .findInvoiceById(id)
                .orElseThrow(NotFoundExeption::new));

    }

    @DeleteMapping("/deleteInvoice")
    public void deleteByIdInvoice(@RequestParam long id) {
        invoiceService.deleteInvoice(id);
    }

    @PutMapping("/updateInvoice")
    public InvoiceDto udppateInvoice(@RequestBody InvoiceDto invoiceDto) {
        Invoice invoice = invoiceMapper.mapToInvoice(invoiceDto);
        Invoice saveInvoice = invoiceService.saveInvoice(invoice);
        return invoiceMapper.mapToInvoiceDto(saveInvoice);
    }

    @PostMapping("/addInvoice")
    public void addInvoice(@RequestBody InvoiceDto invoiceDto) {
        Invoice invoice = invoiceMapper.mapToInvoice(invoiceDto);
        invoiceService.saveInvoice(invoice);
    }



}
