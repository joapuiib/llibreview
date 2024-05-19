package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Review;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.ReviewRecord;

import java.time.LocalDate;
import java.util.List;

public class ReviewData {
    public static final List<Review> REVIEW_LIST = List.of(
            new Review("isbn1", "user1", LocalDate.parse("2024-05-01"), 7),
            new Review("isbn2", "user1", LocalDate.parse("2024-05-01"), 7),
            new Review("isbn2", "user2", LocalDate.parse("2024-05-01"), 8)
    );

    public static final List<ReviewRecord> REVIEW_RECORD_LIST = List.of(
            new ReviewRecord("isbn1", "user1", LocalDate.parse("2024-05-01"), 7),
            new ReviewRecord("isbn2", "user1", LocalDate.parse("2024-05-01"), 7),
            new ReviewRecord("isbn2", "user2", LocalDate.parse("2024-05-01"), 8)
    );
}
