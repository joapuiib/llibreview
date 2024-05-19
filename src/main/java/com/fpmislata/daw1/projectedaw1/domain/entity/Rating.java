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
    private String username;
    private LocalDate date;
    private int rating;

    private Llibre llibre;
    private User user;

    public Rating(String isbn, String username, int rating, LocalDate date) {
        this.isbn = isbn;
        this.username = username;
        this.rating = rating;
        this.date = date;
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
            user =  UserIoc.getUserService().findByUsername(username);
        return user;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "grade=" + rating +
                ", ratingDate=" + date +
                ", userame='" + username + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
