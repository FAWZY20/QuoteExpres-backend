package com.quoteExpress.quoteExpress.controler;

import com.quoteExpress.quoteExpress.DTO.LoginRequest;
import com.quoteExpress.quoteExpress.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/devis/utilisateur")
public interface UserControler {

    @PostMapping("/inscription")
    ResponseEntity addUser(@RequestBody User user) throws Exception;

    @GetMapping("/{userId}")
    ResponseEntity getUser(@PathVariable("userId") UUID userID) throws Exception;

    @GetMapping("/email/{email}")
    ResponseEntity getUserByEmail(@PathVariable("email") String email) throws Exception;

    @DeleteMapping("/{userId}")
    ResponseEntity deletUser(@PathVariable("userId") UUID userID) throws Exception;

    @PostMapping("/connexion")
    ResponseEntity connexionUser(@RequestBody LoginRequest loginRequest);

    @PatchMapping("/pdw/{userId}")
    ResponseEntity updatePassword(@PathVariable("userId") UUID userid, @RequestBody Map<String, String> newPwd) throws Exception;

    @PatchMapping("/{userId}")
    ResponseEntity updateUser(@PathVariable("userId") UUID userid, @RequestBody User user) throws RuntimeException;

}
