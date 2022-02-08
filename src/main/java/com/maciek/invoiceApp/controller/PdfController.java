package com.maciek.invoiceApp.controller;

import com.lowagie.text.DocumentException;
import com.maciek.invoiceApp.domian.Invoice;
import com.maciek.invoiceApp.mapper.InvoiceMapper;
import com.maciek.invoiceApp.pdfExport.UserPDFExporter;
import com.maciek.invoiceApp.pdfExport.UserPDFExporterOne;
import com.maciek.invoiceApp.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PdfController {

    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceService service;

    @GetMapping("/export")
    @ResponseBody
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException, NotFoundExeption {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Invoice> invoicesList = service.findAllInvoices();

        UserPDFExporter exporter = new UserPDFExporter(invoicesList);
        exporter.export(response);

    }
    @GetMapping("/exportOne")
    @ResponseBody
    public void exportToPDFOne(HttpServletResponse response, @RequestParam long id) throws DocumentException, IOException, NotFoundExeption {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Invoice> invoicesList = service.findAllInvoices();

        UserPDFExporterOne exporter = new UserPDFExporterOne(invoicesList);
        exporter.export(response,id);

    }
}

