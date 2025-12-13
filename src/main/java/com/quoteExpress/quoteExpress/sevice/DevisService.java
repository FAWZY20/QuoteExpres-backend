package com.quoteExpress.quoteExpress.sevice;


import com.quoteExpress.quoteExpress.DTO.Product;
import com.quoteExpress.quoteExpress.controler.DevisControler;
import com.quoteExpress.quoteExpress.model.Devis;
import com.quoteExpress.quoteExpress.model.Status;
import com.quoteExpress.quoteExpress.model.User;
import com.quoteExpress.quoteExpress.repository.ClientRepository;
import com.quoteExpress.quoteExpress.repository.DevisRepository;
import com.quoteExpress.quoteExpress.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DevisService implements DevisControler {

    private final DevisRepository devisRepository;
    private final UserRepository userRepository;
    private final ClientService clientService;

    @Autowired
    public DevisService(DevisRepository devisRepository, UserRepository userRepository, ClientRepository clientRepository, ClientService clientService) {
        this.devisRepository = devisRepository;
        this.userRepository = userRepository;
        this.clientService = clientService;
    }

    @Override
    @Transactional
    public ResponseEntity addDevis(UUID userId, Devis devis) throws Exception {
        try {
            User user = userRepository.findUserById(userId);
            devis.setNumeroFacture(generateNumeroFacture());
            devis.setUsers(user);
            clientService.addClient(devis.getClient());
            devisRepository.save(devis);
            return ResponseEntity.ok(devis);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public ResponseEntity getDevis(UUID devisId) throws Exception {
        try {
            return ResponseEntity.ok(devisRepository.findDevisById(devisId));
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public ResponseEntity getListDevisByUserId(UUID userId) throws Exception {
        try {
            List<Devis> devis = devisRepository.findDevisByUserId(userId);
            return ResponseEntity.ok(devis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity getListDevisByUserIdAndYears(UUID userId, int year) throws Exception {
        try {
            LocalDate yearStart = LocalDate.of(year, 1, 1);
            LocalDate yearEnd = LocalDate.of(year, 12, 31);
            List<Devis> devis = devisRepository.findDevisByUserIdAndDate(userId, yearStart, yearEnd);
            return ResponseEntity.ok(devis);
        }catch (Exception e){
            throw new Exception();
        }
    }

    @Override
    public ResponseEntity getListDevisByUserIdAndStatus(UUID userId, Status status) throws Exception {
        try {
            List<Devis> devis = devisRepository.findByUsers_IdAndStatus(userId, status);
            return ResponseEntity.ok(devis);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public ResponseEntity deleteDevis(UUID devisId) throws Exception {
        try {
            devisRepository.deleteDevisById(devisId);
            return ResponseEntity.ok("le devis a bien etait supprimer");
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public ResponseEntity updateDevis(UUID userId, Devis devis) throws Exception {
        try {
            Devis lastdevis = devisRepository.findDevisById(userId);

            lastdevis.setTitreDevis(devis.getTitreDevis());
            lastdevis.setClient(devis.getClient());
            lastdevis.setInfo(devis.getInfo());   
            devisRepository.save(lastdevis);
            return ResponseEntity.ok(lastdevis);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity updateDate(UUID userId, Map<String, String> date) throws Exception {
        try {
            Devis lastdevis = devisRepository.findDevisById(userId);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateDevis = LocalDate.from(LocalDate.parse(date.get("dateDevis"), formatter).atStartOfDay());

            lastdevis.setDateDevis(dateDevis);
            devisRepository.save(lastdevis);

            return ResponseEntity.ok(lastdevis);

        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public ResponseEntity addProduct(UUID devisId, Product product) throws Exception {
        try {
            Devis devis = devisRepository.findDevisById(devisId);

            List<Product> products = devis.getListProduct();

            if (products == null) {
                products = new ArrayList<>();
            }

            products.add(product);
            devis.setListProduct(products);
            devisRepository.save(devis);

            return ResponseEntity.ok(devis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity deleteProduct(UUID devisId, int indexProduct) throws Exception {
        try {
            Devis devis = devisRepository.findDevisById(devisId);

            List<Product> products = devis.getListProduct();

            if (products == null) {
                products = new ArrayList<>();
            }

            products.remove(indexProduct);
            devis.setListProduct(products);
            devisRepository.save(devis);

            return ResponseEntity.ok(devis);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Long generateNumeroFacture() {
        Long min = 100000L;
        Long max = 999999L;
        Long numero;

        do {
            numero = min + (long) (Math.random() * ((max - min) + 1));
        } while (devisRepository.existsByNumeroFacture(numero));

        return numero;
    }

}
