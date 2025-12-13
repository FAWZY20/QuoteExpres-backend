package com.quoteExpress.quoteExpress.controler;

import com.quoteExpress.quoteExpress.model.Clients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/devis/clients")
public interface ClientControler {

    @PutMapping
    ResponseEntity addClient(@RequestBody Clients clients) throws Exception;

    @GetMapping
    ResponseEntity getListClient(@RequestParam("idUser")UUID idUser) throws Exception;

    @GetMapping("/{clientId}")
    ResponseEntity getClient(@PathVariable("clientId") UUID idClient) throws Exception;

    @DeleteMapping("/{clientId}")
    ResponseEntity deleteClient(@PathVariable("clientId")UUID idClient ) throws Exception;

}
