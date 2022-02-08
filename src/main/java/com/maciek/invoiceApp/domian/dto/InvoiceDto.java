package com.maciek.invoiceApp.domian.dto;

import com.maciek.invoiceApp.domian.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private Long Id;
    private int invoiceNumber;
    private LocalDate invoiceDate;
    private Client clientID;
}
