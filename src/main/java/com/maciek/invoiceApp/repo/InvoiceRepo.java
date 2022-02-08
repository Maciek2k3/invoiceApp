package com.maciek.invoiceApp.repo;

import com.maciek.invoiceApp.domian.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepo extends CrudRepository<Invoice,Long> {

    @Override
    <S extends Invoice> S save(S entity);

    @Override
    Optional<Invoice> findById(Long aLong);

    @Override
    List<Invoice> findAll();

    @Override
    void delete(Invoice invoice);
}
