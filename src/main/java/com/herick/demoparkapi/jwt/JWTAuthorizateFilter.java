package com.herick.demoparkapi.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JWTAuthorizateFilter extends OncePerRequestFilter {

    @Autowired
    private final JWTUserDetailsService detailsService;

    public JWTAuthorizateFilter() {
        detailsService = null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(JWTUtils.JWT_AUTHORIZATION);
        if (token == null || token.startsWith(JWTUtils.JWT_BEARER)) {
            log.info(" 'JWT Token está nulo, vazio ou não iniciado com Bearer. '");
            filterChain.doFilter(request, response);
            return;
        }

        if (!JWTUtils.isTokenValid(token)) {
            log.warn("'JWT Token está Inváçido ou expirado.'");
            filterChain.doFilter(request, response);
            return;
        }

        String username = JWTUtils.getUsernameFromToken((token));

        toAuthentication(request,username);
        filterChain.doFilter(request,response);
    }

    private void toAuthentication(HttpServletRequest request, String username) {
        UserDetails userDetails = detailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken
                .authenticated(userDetails,null,userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}