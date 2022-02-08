package com.maciek.invoiceApp.mapper;

import com.maciek.invoiceApp.domian.Client;
import com.maciek.invoiceApp.domian.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ClientMapper {
    public Client mapToClient(final ClientDto clientDto){
        return new Client(clientDto.getId(),clientDto.getName());
    }
    public ClientDto mapToClientDto(final Client client){
        return new ClientDto(client.getId(),client.getName());
    }

    public List<Client> mapToClientList(final List<ClientDto> clientDtoList){
        return clientDtoList.stream().map(this::mapToClient).collect(Collectors.toList());
    }


    public List<ClientDto> mapToClientDtoList(final List<Client> clientList){
        return clientList.stream().map(this::mapToClientDto).collect(Collectors.toList());
    }
}
