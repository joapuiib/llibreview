package com.fpmislata.daw1.projectedaw1.common.container;

import com.fpmislata.daw1.projectedaw1.common.AppPropertiesReader;
import com.fpmislata.daw1.projectedaw1.domain.service.ReviewService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.ReviewServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ReviewDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.ReviewDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.ReviewTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ReviewRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.ReviewRepositoryImpl;

public class ReviewIoc {
    private static ReviewService reviewService;
    private static ReviewRepository reviewRepository;
    private static ReviewDao reviewDao;
    private static ReviewTableMemory reviewTableMemory;

    public static ReviewService createService() {
        if (reviewService == null) {
            ReviewRepository reviewRepository = createRepository();
            reviewService = new ReviewServiceImpl(reviewRepository);
        }
        return reviewService;
    }

    private static ReviewRepository createRepository() {
        if (reviewRepository == null) {
            ReviewDao reviewDao = createDao();
            reviewRepository = new ReviewRepositoryImpl(reviewDao);
        }
        return reviewRepository;
    }

    public static ReviewDao createDao() {
        if (reviewDao == null) {
            if (AppPropertiesReader.getProperty("dao").equals("jdbc"))
                reviewDao = null;
            else {
                ReviewTableMemory reviewTableMemory = createTableMemory();
                reviewDao = new ReviewDaoMemory(reviewTableMemory);
            }
        }
        return reviewDao;
    }

    public static ReviewTableMemory createTableMemory() {
        if (reviewTableMemory == null)
            reviewTableMemory = new ReviewTableMemory();
        return reviewTableMemory;
    }
}
