package com.fpmislata.daw1.projectedaw1.domain.entity;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.common.container.UserIoc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Rating {
    private String isbn;
    private String userame;
    private LocalDate ratingDate;
    private int rating;

    private Llibre llibre;
    private User user;

    public Rating(String isbn, String userame, LocalDate ratingDate, int rating) {
        this.isbn = isbn;
        this.userame = userame;
        this.ratingDate = ratingDate;
        this.rating = rating;
    }

    public Rating() {
    }

    public Llibre getLlibre(){
        if (llibre == null)
            llibre =  LlibreIoc.createService().findByIsbn(isbn);
        return llibre;
    }

    public User getUser(){
        if (user == null)
            user =  UserIoc.getUserService().findByUsername(userame);
        return user;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "grade=" + rating +
                ", ratingDate=" + ratingDate +
                ", userame='" + userame + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
