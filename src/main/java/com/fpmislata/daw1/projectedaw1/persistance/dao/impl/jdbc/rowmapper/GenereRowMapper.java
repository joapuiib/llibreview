package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import com.fpmislata.daw1.projectedaw1.common.i18n.Language;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenereRowMapper extends RowMapper<Genere> {
    @Override
    public Genere mapItem(ResultSet rs) throws SQLException {
        String lang = LocaleContextHolder.getLocale().getLanguage();

        Genere genere = new Genere();
        genere.setId(rs.getInt("id_genere"));
        genere.setNom(rs.getString("nom_" + lang));
        return genere;
    }
}