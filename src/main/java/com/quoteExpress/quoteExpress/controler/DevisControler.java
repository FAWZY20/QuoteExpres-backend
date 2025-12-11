package com.quoteExpress.quoteExpress.controler;


import com.quoteExpress.quoteExpress.DTO.Product;
import com.quoteExpress.quoteExpress.model.Devis;
import com.quoteExpress.quoteExpress.model.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/devis")
public interface DevisControler {

    @PostMapping("/user/{userId}")
    ResponseEntity addDevis(@PathVariable("userId") UUID userId,@RequestBody Devis devis) throws Exception;

    @GetMapping("/{devisId}")
    ResponseEntity getDevis(@PathVariable("devisId") UUID devisId) throws Exception;

    @GetMapping("/list/{userId}")
    ResponseEntity getListDevisByUserId(@PathVariable("userId") UUID userId) throws Exception;

    @GetMapping("/list/year/{userId}")
    ResponseEntity getListDevisByUserIdAndYears(@PathVariable("userId") UUID userId,@RequestParam("year") int year) throws Exception;

    @GetMapping("/list/statut/{userId}")
    ResponseEntity getListDevisByUserIdAndStatus(@PathVariable("userId") UUID userId,@RequestParam("status") Status status) throws Exception;


    @DeleteMapping("/{devisId}")
    ResponseEntity deleteDevis(@PathVariable UUID devisId) throws Exception;

    @PatchMapping("/{devisId}/client")
    ResponseEntity updateDevis(@PathVariable("devisId") UUID devisId,@RequestBody Devis devis) throws Exception;

    @PatchMapping("/{devisId}/date")
    ResponseEntity updateDate(@PathVariable("devisId") UUID devisId,@RequestBody Map<String, String> date) throws Exception;

    @PatchMapping("/{devisId}/products")
    ResponseEntity addProduct(@PathVariable("devisId") UUID devisId, @RequestBody Product product) throws Exception;

    @DeleteMapping("/{devisId}/products/{index}")
    ResponseEntity deleteProduct(@PathVariable("devisId") UUID devisId, @PathVariable("index") int  indexProduct) throws Exception;

}
