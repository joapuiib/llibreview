package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.domain.service.RatingService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.RatingServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RatingDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.RatingDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.RatingDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.RatingTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RatingRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.RatingRepositoryImpl;

public class RatingIoc {
    private static RatingService ratingService;
    private static RatingRepository ratingRepository;
    private static RatingDao ratingDao;
    private static RatingTableMemory ratingTableMemory;

    public static RatingService createService() {
        if (ratingService == null) {
            RatingRepository ratingRepository = createRepository();
            ratingService = new RatingServiceImpl(ratingRepository);
        }
        return ratingService;
    }

    private static RatingRepository createRepository() {
        if (ratingRepository == null) {
            RatingDao ratingDao = createDao();
            ratingRepository = new RatingRepositoryImpl(ratingDao);
        }
        return ratingRepository;
    }

    public static RatingDao createDao() {
        if (ratingDao == null) {
            if (AppPropertiesReader.getProperty("dao").equals("jdbc"))
                ratingDao = new RatingDaoJdbc();
            else {
                RatingTableMemory ratingTableMemory = createTableMemory();
                ratingDao = new RatingDaoMemory(ratingTableMemory);
            }
        }
        return ratingDao;
    }

    public static RatingTableMemory createTableMemory() {
        if (ratingTableMemory == null)
            ratingTableMemory = new RatingTableMemory();
        return ratingTableMemory;
    }
}
