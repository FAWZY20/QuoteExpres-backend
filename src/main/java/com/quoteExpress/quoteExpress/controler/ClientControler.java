package com.quoteExpress.quoteExpress.controler;

import com.quoteExpress.quoteExpress.model.Clients;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/devis/")
public interface ClientControler {

    @GetMapping("{idUser}/clients")
    public Clients getListClient(@PathVariable("idUser")UUID idUser);

    @GetMapping("{idUser}/client/{clientId}")
    public void deleteClient(@PathVariable("idUser")UUID idUser, @PathVariable("clientId")UUID idClient );

}
