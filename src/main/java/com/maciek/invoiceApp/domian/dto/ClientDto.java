package com.maciek.invoiceApp.domian.dto;

import com.maciek.invoiceApp.domian.Client;
import com.maciek.invoiceApp.domian.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long Id;
    private String name;


}
