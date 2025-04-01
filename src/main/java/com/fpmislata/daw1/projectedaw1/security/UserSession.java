package com.fpmislata.daw1.projectedaw1.security;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

public class UserSession {
    @Getter
    @Setter
    private static HttpSession session;
    private final static String USER_KEY = "user";

    public static void setAttribute(String key, Object value) {
        session.setAttribute(key, value);
    }
    public static Object getAttribute(String key) {
        return session.getAttribute(key);
    }
    public static void removeAttribute(String key) {
        session.removeAttribute(key);
    }

    public static void setUser(Usuari usuari) {
        setAttribute(USER_KEY, usuari);
    }
    public static Usuari getUser() {
        try {
            return (Usuari) getAttribute(USER_KEY);
        } catch (RuntimeException e) {
            session.invalidate();
        }
        return null;
    }

    public static boolean isUserLoggedIn() {
        return getUser() != null;
    }

    public static void clear() {
        session.invalidate();
    }
}
