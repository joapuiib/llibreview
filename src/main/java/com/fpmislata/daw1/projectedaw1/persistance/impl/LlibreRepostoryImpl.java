package com.fpmislata.daw1.projectedaw1.persistance.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.LlibreRepository;
import com.fpmislata.daw1.projectedaw1.persistance.impl.rowmapper.LlibreRowMapper;

import java.util.List;

public class LlibreRepostoryImpl extends Repository implements LlibreRepository {
    private final String tableName = "llibre";

    @Override
    public List<Llibre> findAll() {
        String sql = "SELECT * FROM " + tableName;
        return jdbcTemplate.query(sql, new LlibreRowMapper());
    }

    @Override
    public List<Llibre> findLatest() {
        return null;
    }

    @Override
    public List<Llibre> findMostRead() {
        return null;
    }

    @Override
    public List<Llibre> findBestReview() {
        return null;
    }
}