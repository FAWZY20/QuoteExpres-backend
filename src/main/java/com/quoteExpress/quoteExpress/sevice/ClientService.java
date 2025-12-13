package com.quoteExpress.quoteExpress.sevice;

import ch.qos.logback.core.net.server.Client;
import com.quoteExpress.quoteExpress.controler.ClientControler;
import com.quoteExpress.quoteExpress.model.Clients;
import com.quoteExpress.quoteExpress.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService implements ClientControler {

    final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity addClient(Clients clients) throws Exception {
       try {
           if (!clientRepository.existsByTelephoneclient(clients.getTelephoneclient())){
               clientRepository.save(clients);
               return ResponseEntity.ok("le client a bien etait ajouter");
           }else {
               return ResponseEntity.ok("le client existe deja");
           }
       }catch (Exception e){
           throw new Exception("une erreur et survenue durant l'ajout du client", e);
       }
    }

    @Override
    public ResponseEntity getListClient(UUID idUser) throws Exception{
        try {
            List<Clients> listClient = clientRepository.findClientsByUsers_Id(idUser);
            if (listClient != null){
                return ResponseEntity.ok(listClient);
            }else{
                return ResponseEntity.ofNullable("aucun client existant");
            }
        }catch (Exception e){
            throw new Exception("une erreur et survenue lors de la recherche des clients", e);
        }
    }

    @Override
    public ResponseEntity getClient(UUID idClient) throws Exception {
        try{
            Clients client = clientRepository.findById(idClient);
            if (client != null) return ResponseEntity.ok(client);
            else return ResponseEntity.ofNullable("le client n'existe pas");
        } catch (Exception e) {
            throw new Exception("erreur lors de la recherche du client ",e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity deleteClient(UUID idClient) throws Exception {
        try{
            clientRepository.deleteById(idClient);
            return ResponseEntity.ok("le client a bien etait supprimer");
        } catch (Exception e) {
            throw new Exception("erreur lors de la suppresion du client ",e);
        }
    }
}
