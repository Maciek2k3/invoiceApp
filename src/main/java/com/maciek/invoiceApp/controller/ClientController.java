package com.maciek.invoiceApp.controller;

import com.maciek.invoiceApp.domian.Client;
import com.maciek.invoiceApp.domian.Invoice;
import com.maciek.invoiceApp.domian.dto.ClientDto;
import com.maciek.invoiceApp.mapper.ClientMapper;
import com.maciek.invoiceApp.repo.ClientRepo;
import com.maciek.invoiceApp.repo.InvoiceRepo;
import com.maciek.invoiceApp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("invoice/v1")
@CrossOrigin(origins = "*")
public class ClientController {

    private ClientMapper clientMapper;
    private ClientService clientService;

    @Autowired
    private InvoiceRepo invoiceRepo;
    @Autowired
    private ClientRepo clientRepo;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @GetMapping("/getClients")
    public List<ClientDto> getAllClients() {
        List<Client> clientsList = clientService.findAllClients();
        return clientMapper.mapToClientDtoList(clientsList);
    }

    @GetMapping("/getClient")
    @ResponseBody
    public ClientDto getClient(@RequestParam long id) throws NotFoundExeption {
        return clientMapper.mapToClientDto(clientService
                .findClientById(id)
                .orElseThrow(NotFoundExeption::new));

    }

    @DeleteMapping("/deleteClient")
    public void deleteByIdClient(@RequestParam long id) {
        clientService.deleteClient(id);
    }

    @PutMapping("/updateClient")
    public ClientDto updateClient(@RequestBody ClientDto clientDto) {
        Client client = clientMapper.mapToClient(clientDto);
        Client saveClient = clientService.saveClient(client);
        return clientMapper.mapToClientDto(saveClient);
    }

    @PostMapping("/addClient")
    public void addClient(@RequestBody ClientDto clientDto) {
        Client client = clientMapper.mapToClient(clientDto);
        clientService.saveClient(client);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void saveItems() {
        Client client = new Client(1L, "Mac");
        Invoice invoice = new Invoice(1L, 01, LocalDate.now(), client);
        clientRepo.save(client);
        invoiceRepo.save(invoice);
    }
}

