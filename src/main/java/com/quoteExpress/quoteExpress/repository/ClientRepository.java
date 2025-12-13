package com.quoteExpress.quoteExpress.repository;

import com.quoteExpress.quoteExpress.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Clients,Long> {

    void  deleteById(UUID idClient);

    Clients findById(UUID idClient);

    boolean existsByTelephoneclient(Integer telephone);

    List<Clients> findClientsByUsers_Id(UUID idUser);
}
