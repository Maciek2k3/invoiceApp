package com.maciek.invoiceApp.pdfExport;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.maciek.invoiceApp.domian.Invoice;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class UserPDFExporter {
    private List<Invoice> invoiceList;

    public UserPDFExporter(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Invoice ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Invoice Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Invoice Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Client ID", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Invoice invoice : invoiceList) {
            table.addCell(String.valueOf(invoice.getId()));
            table.addCell(String.valueOf(invoice.getInvoiceNumber()));
            table.addCell(String.valueOf(invoice.getInvoiceDate()));
            table.addCell(String.valueOf(invoice.getClientID()));
        }
    }


    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Invoices", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
