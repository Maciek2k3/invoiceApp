package com.maciek.invoiceApp.repo;

import com.maciek.invoiceApp.domian.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends CrudRepository<Client,Long> {
    @Override
    <S extends Client> S save(S entity);

    @Override
    Optional<Client> findById(Long aLong);

    @Override
    List<Client> findAll();

    @Override
    void delete(Client client);
}
