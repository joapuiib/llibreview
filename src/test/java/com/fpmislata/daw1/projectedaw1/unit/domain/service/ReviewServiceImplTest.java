package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.RatingData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.RatingServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RatingRepository;
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
class RatingServiceImplTest {
    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    private final List<Rating> ratingList = RatingData.REVIEW_LIST;
    private final List<Llibre> llibreList = LlibreData.LLIBRE_LIST;

    @Nested
    class FindByLlibre {
        @Test
        void givenBookWithNoRatings_thenReturnEmptyList() {
            Llibre llibre = llibreList.get(2);
            List<Rating> expected = List.of();
            when(ratingRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Rating> result = ratingService.findByLlibre(llibre);

            assertEquals(expected, result);
        }

        @Test
        void givenBookWithSingleRatings_thenReturnRatingList() {
            Llibre llibre = llibreList.get(0);
            List<Rating> expected = List.of(ratingList.get(0));
            when(ratingRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Rating> result = ratingService.findByLlibre(llibre);

            assertEquals(expected, result);
        }

        @Test
        void givenBookWithMultipleRatings_thenReturnRatingList() {
            Llibre llibre = llibreList.get(1);
            List<Rating> expected = List.of(ratingList.get(1), ratingList.get(2));
            when(ratingRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Rating> result = ratingService.findByLlibre(llibre);

            assertEquals(expected, result);
        }
    }
}