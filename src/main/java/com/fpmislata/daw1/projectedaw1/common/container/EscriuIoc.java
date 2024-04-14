package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.domain.service.AutorService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.AutorServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.AutorDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.EscriuDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.AutorRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.AutorRepositoryImpl;

public class EscriuIoc {
    private static EscriuDao escriuDao;

    public static EscriuDao createDao() {
        if (escriuDao == null) {
            escriuDao = new EscriuDaoMemory();
        }
        return escriuDao;
    }
}
