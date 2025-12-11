package com.quoteExpress.quoteExpress.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class Clients {

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "utilisateurid")
    private User users;

    @Column(name = "nomclient")
    private String nomclient;

    @Column(name = "addreseclient")
    private String addreseclient;

    @Column(name = "codepostalclient")
    private Integer codepostalclient;

    @Column(name = "villeclient")
    private String villeclient;

    @Column(name = "Siretclient")
    private String Siretclient;

    @Column(name = "telephoneclient")
    private Integer telephoneclient;

    public Clients() {}

    public Clients(User users, String nomclient, String addreseclient, Integer codepostalclient, String villeclient, String siretclient, Integer telephoneclient) {
        this.users = users;
        this.nomclient = nomclient;
        this.addreseclient = addreseclient;
        this.codepostalclient = codepostalclient;
        this.villeclient = villeclient;
        Siretclient = siretclient;
        this.telephoneclient = telephoneclient;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getAddreseclient() {
        return addreseclient;
    }

    public void setAddreseclient(String addreseclient) {
        this.addreseclient = addreseclient;
    }

    public Integer codepostalclient() {
        return codepostalclient;
    }

    public void setCodepostalclient(Integer codepostalclient) {
        this.codepostalclient = codepostalclient;
    }

    public String getVilleclient() {
        return villeclient;
    }

    public void setVilleclient(String villeclient) {
        this.villeclient = villeclient;
    }

    public String getSiretclient() {
        return Siretclient;
    }

    public void setSiretclient(String siretclient) {
        Siretclient = siretclient;
    }

    public Integer getTelephoneclient() {
        return telephoneclient;
    }

    public void setTelephoneclient(Integer telephoneclient) {
        this.telephoneclient = telephoneclient;
    }
}

