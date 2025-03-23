package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuari {
    private String username;
    private String email;
    private LocalDate dataRegistre;
    private String passwordHash;

    private List<Rol> roles;

    public Usuari(String username, String email, LocalDate dataRegistre, String passwordHash) {
        this.username = username;
        this.email = email;
        this.dataRegistre = dataRegistre;
        this.passwordHash = passwordHash;
    }

    public Usuari(String username, String email, LocalDate dataRegistre) {
        this(username, email, dataRegistre, null);
    }

    public Usuari(String username, String email) {
        this(username, email, null, null);
    }

    public Usuari() {
        this(null, null, null, null);
    }

    private Usuari(Usuari other) {
        this.username = other.username;
        this.email = other.email;
        this.dataRegistre = other.dataRegistre;
        this.passwordHash = other.passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuari usuari = (Usuari) o;
        return Objects.equals(username, usuari.username)
                && Objects.equals(email, usuari.email)
                && Objects.equals(dataRegistre, usuari.dataRegistre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, dataRegistre);
    }

    public Usuari clone() {
        return new Usuari(this);
    }
}
