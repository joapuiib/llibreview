package com.fpmislata.daw1.projectedaw1.security;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

public class UserSession {
    @Getter
    @Setter
    private static HttpSession session;
    private final static String USER_KEY = "username";

    public static void setAttribute(String key, Object value) {
        session.setAttribute(key, value);
    }
    public static Object getAttribute(String key) {
        return session.getAttribute(key);
    }
    public static void removeAttribute(String key) {
        session.removeAttribute(key);
    }

    public static void setUser(User user) {
        session.setAttribute(USER_KEY, user);
    }
    public static User getUser() {
        try {
            return (User) session.getAttribute(USER_KEY);
        } catch(RuntimeException e) {
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
