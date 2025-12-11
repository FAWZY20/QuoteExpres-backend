package com.quoteExpress.quoteExpress.sevice;

import com.quoteExpress.quoteExpress.controler.StatistiqueControler;
import com.quoteExpress.quoteExpress.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;
import java.util.UUID;

@Service
public class StatistiqueService implements StatistiqueControler {

    private final DevisRepository devisRepository;

    @Autowired
    public StatistiqueService(DevisRepository devisRepository) {
        this.devisRepository = devisRepository;
    }
    
    @Override
    public ResponseEntity totalDevisDay(UUID userId) throws Exception {
        try {
            LocalDate date = LocalDate.now();
            return ResponseEntity.ok(devisRepository.countDevisToday(userId, date));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity totalDevisMonth(UUID userId, Integer month) throws Exception {
        try {
            if (month == null || month == 0) {
                return ResponseEntity.badRequest().body("Le mois est obligatoire");
            }

            int year = LocalDate.now().getYear();

            YearMonth yearMonth = YearMonth.of(year, month);
            LocalDate monthStart = yearMonth.atDay(1);
            LocalDate monthEnd = yearMonth.atEndOfMonth();

            return ResponseEntity.ok(devisRepository.countDevisMonth(userId, monthStart, monthEnd));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity totalDevisYear(UUID userId, Integer year) throws Exception {
       try {
           if (year == null) {
               return ResponseEntity.badRequest().body("L'annee est obligatoire");
           }
           LocalDate yearStart = LocalDate.of(year, 1, 1);
           LocalDate yearEnd = LocalDate.of(year, 12, 31);

           return ResponseEntity.ok(devisRepository.countDevisMonth(userId, yearStart, yearEnd));

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public ResponseEntity totalFacturee(UUID userId) throws Exception {
        try {
            return ResponseEntity.ok(devisRepository.sumFacture(userId));
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public ResponseEntity totalPrixDevisDay(UUID userId) throws Exception {
        try {
            LocalDate date = LocalDate.now();
            System.out.println(date);
            return ResponseEntity.ok(devisRepository.sumTotalDevisToday(userId, date));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity totalPrixDevisMonth(UUID userId, Integer month) throws Exception {
        try {
            if (month == null || month == 0) {
                return ResponseEntity.badRequest().body("Le mois est obligatoire");
            }

            int year = LocalDate.now().getYear();

            YearMonth yearMonth = YearMonth.of(year, month);
            LocalDate monthStart = yearMonth.atDay(1);
            LocalDate monthEnd = yearMonth.atEndOfMonth();

            return ResponseEntity.ok(devisRepository.sumDevisMonth(userId, monthStart, monthEnd));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity totalPrixDevisYear(UUID userId, Integer year) throws Exception {
        try {
            if (year == null) {
                return ResponseEntity.badRequest().body("L'annee est obligatoire");
            }
            LocalDate yearStart = LocalDate.of(year, 1, 1);
            LocalDate yearEnd = LocalDate.of(year, 12, 31);

            return ResponseEntity.ok(devisRepository.sumDevisMonth(userId, yearStart, yearEnd));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
