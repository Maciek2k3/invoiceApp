package com.maciek.invoiceApp.service;

import com.maciek.invoiceApp.domian.Client;
import com.maciek.invoiceApp.domian.Invoice;
import com.maciek.invoiceApp.repo.ClientRepo;
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
public class ClientService {
    @Autowired
    ClientRepo clientRepo;

    public Client saveClient(final Client client){
        return clientRepo.save(client);
    }

    public List<Client> findAllClients() {
        return clientRepo.findAll();

    }

    public void deleteClient (Long id) {
        clientRepo.deleteById(id);
    }

    public Optional<Client> findClientById(Long id) {
        return clientRepo.findById(id);
    }

}

