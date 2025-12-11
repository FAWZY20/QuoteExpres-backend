package com.quoteExpress.quoteExpress.repository;


import com.quoteExpress.quoteExpress.model.Devis;
import com.quoteExpress.quoteExpress.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DevisRepository extends JpaRepository<Devis,Long> {

    void deleteDevisById(UUID devisId);

    Devis findDevisById(UUID devisId);


    //Find

    @Query("SELECT d FROM Devis d WHERE d.users.id = :utilisateurId ")
    List<Devis> findDevisByUserId(@Param("utilisateurId") UUID utilisateurId);

    @Query("SELECT d FROM Devis d WHERE d.users.id = :utilisateurId AND d.dateDevis BETWEEN :monthStart AND :monthEnd")
    List<Devis> findDevisByUserIdAndDate(@Param("utilisateurId") UUID utilisateurId, @Param("monthStart") LocalDate monthStart, @Param("monthEnd") LocalDate monthEnd);

    List<Devis> findByUsers_IdAndStatus(UUID userId, Status status);

    boolean existsByNumeroFacture(Long numero);

    //Count

    @Query("SELECT COUNT(d) FROM Devis d WHERE d.users.id = :utilisateurId AND d.dateDevis = :date")
    long countDevisToday(@Param("utilisateurId") UUID utilisateurId, @Param("date") LocalDate date);

    @Query("SELECT COUNT(d) FROM Devis d WHERE d.users.id = :utilisateurId AND d.dateDevis BETWEEN :monthStart AND :monthEnd")
    long countDevisMonth(@Param("utilisateurId")UUID utilisateurId, @Param("monthStart") LocalDate monthStart, @Param("monthEnd") LocalDate monthEnd);


    //Sum

    @Query("SELECT COALESCE(SUM(d.totalTtc), 0) FROM Devis d WHERE d.users.id = :utilisateurId AND d.dateDevis BETWEEN :monthStart AND :monthEnd")
    long sumDevisMonth(@Param("utilisateurId")UUID utilisateurId, @Param("monthStart") LocalDate monthStart, @Param("monthEnd") LocalDate monthEnd);

    @Query("SELECT COALESCE(SUM(d.totalTtc), 0) FROM Devis d WHERE d.users.id = :utilisateurId AND d.dateDevis = :date")
    Long sumTotalDevisToday(@Param("utilisateurId") UUID utilisateurId, @Param("date") LocalDate date);

    @Query("SELECT COALESCE(SUM(d.totalTtc), 0) FROM Devis d WHERE d.users.id = :utilisateurId")
    long sumFacture(@Param("utilisateurId") UUID utilisateurId);

    @Query("SELECT COALESCE(SUM(d.totalTtc), 0) FROM Devis d WHERE d.users.id = :utilisateurId AND status = :status")
    long sumFactureByStatus(@Param("utilisateurId") UUID utilisateurId, @Param("status") String status);

}
