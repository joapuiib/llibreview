package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.RatingRecord;

import java.time.LocalDate;
import java.util.List;

public class RatingData {
    public static final List<Rating> REVIEW_LIST = List.of(
            new Rating("isbn1", "user1", 7, LocalDate.parse("2024-05-01")),
            new Rating("isbn2", "user1", 7, LocalDate.parse("2024-05-01")),
            new Rating("isbn2", "user2", 8, LocalDate.parse("2024-05-01"))
    );

    public static final List<RatingRecord> REVIEW_RECORD_LIST = List.of(
            new RatingRecord("isbn1", "user1", 7, LocalDate.parse("2024-05-01")),
            new RatingRecord("isbn2", "user1", 7, LocalDate.parse("2024-05-01")),
            new RatingRecord("isbn2", "user2", 8, LocalDate.parse("2024-05-01"))
    );
}
