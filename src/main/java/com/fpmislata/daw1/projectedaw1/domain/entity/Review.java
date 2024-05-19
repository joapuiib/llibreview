package com.fpmislata.daw1.projectedaw1.domain.entity;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.common.container.UserIoc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Review {
    private String isbn;
    private String userame;
    private LocalDate reviewDate;
    private int grade;

    private Llibre llibre;
    private User user;

    public Review(String isbn, String userame, LocalDate reviewDate, int grade) {
        this.isbn = isbn;
        this.userame = userame;
        this.reviewDate = reviewDate;
        this.grade = grade;
    }

    public Review() {
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
        return "Review{" +
                "grade=" + grade +
                ", reviewDate=" + reviewDate +
                ", userame='" + userame + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
