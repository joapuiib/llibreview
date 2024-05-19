package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Review;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.ReviewRecord;

import java.util.ArrayList;
import java.util.List;

public class ReviewMapper implements Mapper<ReviewRecord, Review> {
    public Review map(ReviewRecord reviewRecord) {
        if (reviewRecord == null) {
            return null;
        }

        Review review = new Review();
        review.setIsbn(reviewRecord.getIsbn());
        review.setUserame(reviewRecord.getUsername());
        review.setReviewDate(reviewRecord.getReviewDate());
        review.setGrade(reviewRecord.getGrade());

        return review;
    }

    public List<Review> map(List<ReviewRecord> reviewRecordList){
        if (reviewRecordList == null) {
            return null;
        }

        return reviewRecordList.stream().collect(
                ArrayList::new,
                (list, llibreRecord) -> list.add(map(llibreRecord)),
                ArrayList::addAll
        );
    }
}
