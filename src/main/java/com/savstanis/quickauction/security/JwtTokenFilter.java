package com.savstanis.quickauction.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savstanis.quickauction.controller.response.ResponseEntityFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try {
            String token = jwtTokenProvider.resolveToken(httpServletRequest);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);

                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            chain.doFilter(httpServletRequest, httpServletResponse);
        } catch (AuthenticationException e) {
            ResponseEntity responseEntity = ResponseEntityFactory.getErrorResponse(e.getMessage());
            String responseEntityJson = new ObjectMapper().writeValueAsString(responseEntity.getBody());

            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(responseEntityJson);
            httpServletResponse.flushBuffer();
        }
    }
}
