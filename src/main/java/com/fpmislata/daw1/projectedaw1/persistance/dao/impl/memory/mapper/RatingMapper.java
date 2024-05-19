package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.RatingRecord;

import java.util.ArrayList;
import java.util.List;

public class RatingMapper implements Mapper<RatingRecord, Rating> {
    public Rating map(RatingRecord ratingRecord) {
        if (ratingRecord == null) {
            return null;
        }

        Rating rating = new Rating();
        rating.setIsbn(ratingRecord.getIsbn());
        rating.setUsername(ratingRecord.getUsername());
        rating.setRatingDate(ratingRecord.getRatingDate());
        rating.setRating(ratingRecord.getRating());

        return rating;
    }

    public List<Rating> map(List<RatingRecord> ratingRecordList){
        if (ratingRecordList == null) {
            return null;
        }

        return ratingRecordList.stream().collect(
                ArrayList::new,
                (list, llibreRecord) -> list.add(map(llibreRecord)),
                ArrayList::addAll
        );
    }
}
