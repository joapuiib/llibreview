package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreGenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.database.DatabaseConnection;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper.LlibreRowMapper;

import java.util.List;

public class LlibreGenereDaoJdbc implements LlibreGenereDao {

    private final DatabaseConnection databaseConnection;
    private final LlibreRowMapper llibreRowMapper;
    public LlibreGenereDaoJdbc() {
        this.databaseConnection = DatabaseConnection.getInstance();
        this.llibreRowMapper = new LlibreRowMapper();
    }

    @Override
    public List<Llibre> findLlibreByGenereId(int id) {
        return List.of();
    }

    @Override
    public List<Genere> findGeneresByLlibreIsbn(String isbn) {
        return List.of();
    }
}
