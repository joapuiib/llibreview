package com.fpmislata.daw1.projectedaw1.controller.components;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alert {
    private String message;
    private String type;

    public Alert() {
    }

    public Alert(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
