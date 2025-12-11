package com.quoteExpress.quoteExpress.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/devis/statistique")
public interface StatistiqueControler {

    @GetMapping("/day/{userId}")
    ResponseEntity totalDevisDay(@PathVariable("userId") UUID userId) throws Exception;

    @GetMapping("/month/{userId}")
    ResponseEntity totalDevisMonth(@PathVariable("userId") UUID userId,@RequestParam("month") Integer month) throws Exception;

    @GetMapping("/year/{userId}")
    ResponseEntity totalDevisYear(@PathVariable("userId") UUID userId,@RequestParam("year") Integer year) throws Exception;

    @GetMapping("/totalFacturee/{userId}")
    ResponseEntity totalFacturee(@PathVariable("userId") UUID userId) throws Exception;

    @GetMapping("/day/price/{userId}")
    ResponseEntity totalPrixDevisDay(@PathVariable("userId") UUID userId) throws Exception;

    @GetMapping("/month/price/{userId}")
    ResponseEntity totalPrixDevisMonth(@PathVariable("userId") UUID userId,@RequestParam("month") Integer month) throws Exception;

    @GetMapping("/year/price/{userId}")
    ResponseEntity totalPrixDevisYear(@PathVariable("userId") UUID userId,@RequestParam("year") Integer year) throws Exception;

}
