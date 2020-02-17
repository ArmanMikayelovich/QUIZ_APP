package com.workfront.quiz.security.jwt;

import com.workfront.quiz.api.impl.ExceptionHandlerController;
import com.workfront.quiz.config.ExceptionHandlerControllerConfig.ExceptionHandlerControllerProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtTokenFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;


    private ExceptionHandlerController exceptionHandlerController = new ExceptionHandlerControllerProvider().provide();

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {

            Optional<String> optionalJwtToken = jwtTokenProvider
                    .resolveToken((HttpServletRequest) servletRequest);
            optionalJwtToken.map(jwtTokenProvider::getAuthentication)
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (JwtAuthenticationException jwtException) {
            exceptionHandlerController.handleFilterExceptions(jwtException, (HttpServletResponse) servletResponse);
        }
    }
}
