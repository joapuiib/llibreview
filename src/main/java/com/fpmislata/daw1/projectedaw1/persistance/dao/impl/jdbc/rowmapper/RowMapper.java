package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class RowMapper<E> {
    public abstract E mapItem(ResultSet rs) throws SQLException;

    public List<E> map(ResultSet rs) throws SQLException {
        if(rs ==  null) {
            return null;
        }
        List<E> userList = new ArrayList<>();
        while (rs.next()) {
            userList.add(mapItem(rs));
        }
        return userList;
    }
}
