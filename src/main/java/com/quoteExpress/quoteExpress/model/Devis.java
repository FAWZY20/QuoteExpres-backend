package com.quoteExpress.quoteExpress.model;


import com.quoteExpress.quoteExpress.DTO.Product;
import com.quoteExpress.quoteExpress.sevice.ProductListConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devis")
public class Devis {

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "utilisateurid")
    private User users;

    @Column(name = "titredevis")
    private String titreDevis;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status =  Status.EN_ATTENTE;

    @Column(name = "numerofacture", unique = true)
    private Long numeroFacture;

    @ManyToOne
    @JoinColumn(name = "clientid")
    private Clients client;

    @Column(name = "datedevis")
    private LocalDate dateDevis;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "listproduct", columnDefinition = "jsonb")
    private List<Product> listProduct;

    @Column(name = "info")
    private String info;

    @Column(name = "totalht")
    private BigDecimal totalHt;

    @Column(name = "totaltva")
    private BigDecimal  totalTva;

    @Column(name = "totalttc")
    private BigDecimal  totalTtc;

    public Devis(UUID id, User user,String titreDevis, Status status, Long numeroFacture, LocalDate dateDevis, List<Product> listProduct,
                 String info, BigDecimal  totalHt, BigDecimal  totalTva, BigDecimal  totalTtc) {
        this.id = id;
        this.users = users;
        this.titreDevis = titreDevis;
        this.numeroFacture = numeroFacture;
        this.status = status;
        this.dateDevis = dateDevis;
        this.listProduct = listProduct;
        this.info = info;
        this.totalHt = totalHt;
        this.totalTva = totalTva;
        this.totalTtc = totalTtc;
    }

    public Devis(){}

    public UUID getId() { return id; }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Long getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(Long numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public String getTitreDevis() { return titreDevis; }

    public void setTitreDevis(String titreDevis) { this.titreDevis = titreDevis; }

    public LocalDate getDateDevis() {
        return dateDevis;
    }

    public void setDateDevis(LocalDate dateDevis) {
        this.dateDevis = dateDevis;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal  getTotalHt() {
        return totalHt;
    }

    public void setTotalHt(BigDecimal  totalHt) {
        this.totalHt = totalHt;
    }

    public BigDecimal  getTotalTva() {
        return totalTva;
    }

    public void setTotalTva(BigDecimal  totalTva) {
        this.totalTva = totalTva;
    }

    public BigDecimal  getTotalTtc() {
        return totalTtc;
    }

    public void setTotalTtc(BigDecimal  totalTtc) {
        this.totalTtc = totalTtc;
    }
}
