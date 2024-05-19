package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.ReviewData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Review;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.ReviewServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ReviewRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private final List<Review> reviewList = ReviewData.REVIEW_LIST;
    private final List<Llibre> llibreList = LlibreData.LLIBRE_LIST;

    @Nested
    class FindByLlibre {
        @Test
        void givenBookWithNoReviews_thenReturnEmptyList() {
            Llibre llibre = llibreList.get(2);
            List<Review> expected = List.of();
            when(reviewRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Review> result = reviewService.findByLlibre(llibre);

            assertEquals(expected, result);
        }

        @Test
        void givenBookWithSingleReviews_thenReturnReviewList() {
            Llibre llibre = llibreList.get(0);
            List<Review> expected = List.of(reviewList.get(0));
            when(reviewRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Review> result = reviewService.findByLlibre(llibre);

            assertEquals(expected, result);
        }

        @Test
        void givenBookWithMultipleReviews_thenReturnReviewList() {
            Llibre llibre = llibreList.get(1);
            List<Review> expected = List.of(reviewList.get(1), reviewList.get(2));
            when(reviewRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Review> result = reviewService.findByLlibre(llibre);

            assertEquals(expected, result);
        }
    }
}