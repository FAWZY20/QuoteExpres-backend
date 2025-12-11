package com.quoteExpress.quoteExpress.sevice;

import com.quoteExpress.quoteExpress.DTO.LoginRequest;
import com.quoteExpress.quoteExpress.cofiguration.JwtProvider;
import com.quoteExpress.quoteExpress.controler.UserControler;
import com.quoteExpress.quoteExpress.model.User;
import com.quoteExpress.quoteExpress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
   public class UserService implements UserControler {

    private final UserRepository userRepository;
    private final EncodePassword encodePassword;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserService(UserRepository userRepository, EncodePassword encodePassword, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.encodePassword = encodePassword;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public ResponseEntity addUser(User user) throws Exception {
        try {
            if (userRepository.findUserByEmail(user.getEmail()) == null){
                user.setPassword(encodePassword.enccodePwd(user.getPassword()));
                userRepository.save(user);
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        }catch (Exception e){
            throw new Exception("l'utilisateur n'a pas pu etre ajouter", e);
        }
    }

    @Override
    public ResponseEntity getUser(UUID userID) throws Exception {
        try {
            User user = userRepository.findUserById(userID);
            if (user != null){
                return ResponseEntity.ok(user);
            }else {
                return ResponseEntity.ofNullable("aucun utilisteur trouver");
            }
        }catch (Exception e){
            throw new Exception("une erreur ces produit lors de la recherche de l'tuilisateur", e);
        }
    }

    @Override
    public ResponseEntity getUserByEmail(String email) throws Exception {
        try {
            User user = userRepository.findUserByEmail(email);
            if (user != null){
                return ResponseEntity.ok(user);
            }else {
                return ResponseEntity.ofNullable("aucun utilisteur trouver");
            }
        }catch (Exception e){
            throw new Exception("une erreur ces produit lors de la recherche de l'tuilisateur", e);
        }
    }

    @Override
    public ResponseEntity deletUser(UUID userID) throws Exception {
        try {
            userRepository.deleteById(userID);
            return ResponseEntity.ok("l'utilisateur a etait supprimer");
        }catch (Exception e){
            throw new Exception("l'utilisateur n'a pas pu etre supprimer");
        }
    }

    @Override
    public ResponseEntity connexionUser(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        if (BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())){
            Map<String, String> token = new HashMap<>();
            token.put("token", jwtProvider.generateToken(loginRequest.getEmail()));
            return  ResponseEntity.ok(token);
        }else {
            return ResponseEntity.badRequest().body("Votre mot de pase ou email et incorect");
        }
    }

    @Override
    public ResponseEntity updatePassword(UUID userid, Map<String, String> newPwd) throws Exception {
        try {
            String newPassword = newPwd.get("newPassword");
            User user = userRepository.findUserById(userid);
            user.setPassword(encodePassword.enccodePwd(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok("le mdp a bien etait modifier");
        }catch (Exception e){
            throw new Exception("un probleme a etait rencontrer lors de la modicfication du mdp", e);
        }
    }

    @Override
    public ResponseEntity updateUser(UUID userid, User user) throws RuntimeException {
        try {
            User existingUser = userRepository.findUserById(userid);

            if (existingUser == null) {
                return ResponseEntity.notFound().build();
            }

            if (user.getName() != null) existingUser.setName(user.getName());
            if (user.getLastName() != null) existingUser.setLastName(user.getLastName());
            if (user.getCompanyName() != null) existingUser.setCompanyName(user.getCompanyName());
            if (user.getAddress() != null) existingUser.setAddress(user.getAddress());
            if (user.getZipCode() != null) existingUser.setZipCode(user.getZipCode());
            if (user.getCity() != null) existingUser.setCity(user.getCity());
            if (user.getCountry() != null) existingUser.setCountry(user.getCountry());
            if (user.getPhoneNumber() != null) existingUser.setPhoneNumber(user.getPhoneNumber());
            if (user.getSiretNumber() != null) existingUser.setSiretNumber(user.getSiretNumber());
            if (user.getVatNumber() != null) existingUser.setVatNumber(user.getVatNumber());
            if (user.getLogoUrl() != null) existingUser.setLogoUrl(user.getLogoUrl());
            if (user.getEmail() != null) existingUser.setEmail(user.getEmail());

            User savedUser = userRepository.save(existingUser);

            return ResponseEntity.ok(savedUser);

        } catch (Exception e) {
            throw new RuntimeException("une erreur et survenu l'utilisateur n'a pas pu etre modifier", e);
        }
    }

}
