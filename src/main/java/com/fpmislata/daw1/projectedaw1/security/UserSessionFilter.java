package com.fpmislata.daw1.projectedaw1.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserSessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //System.out.println("Se activa el filtro");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        UserSession.setSession(httpServletRequest.getSession());

        chain.doFilter(request, response);
    }
}
