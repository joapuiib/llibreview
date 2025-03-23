package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;

public interface UsuariDao {
    Usuari findByUsername(String username);
    Usuari findByEmail(String email);
    int insert(Usuari usuari, String passwordHash);

    /**
     * Comrpova si les credencials de l'usuari són correctes.
     *
     * @param username Nom d'usuari
     * @param passwordHash Hash de la contrasenya
     * @return true si les credencials són correctes, false si no coincideixen o l'usuari no existeix.
     */
    boolean login(String username, String passwordHash);
}
