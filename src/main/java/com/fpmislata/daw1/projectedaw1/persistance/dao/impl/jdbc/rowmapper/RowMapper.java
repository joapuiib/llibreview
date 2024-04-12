package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper<E> {
    public E mapItem(ResultSet rs) throws SQLException;
    public List<E> map(ResultSet rs) throws SQLException;
}
