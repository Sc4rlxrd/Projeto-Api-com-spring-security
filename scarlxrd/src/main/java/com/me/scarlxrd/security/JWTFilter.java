package com.me.scarlxrd.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class JWTFilter extends OncePerRequestFilter {
    private TexturePaintContext SecurityContextHolder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //obtem o token da request com AUTHORIZATION
        String token =  request.getHeader(JWTCreator.HEADER_AUTHORIZATION);
        //esta implementação só esta validando a integridade do token
        try {
            if(token!=null && !token.isEmpty()) {
                String tokenObject = JWTCreator.create(token,SecurityConfig.PREFIX, SecurityConfig.KEY);

                List<SimpleGrantedAuthority> authorities;
                authorities = authorities(tokenObject.getRoles());

                UsernamePasswordAuthenticationToken userToken;
                userToken = new UsernamePasswordAuthenticationToken(
                        tokenObject.getSubject(),
                        null,
                        authorities);

                SecurityContextHolder.getContext().setAuthentication(userToken);

            }else {
                getObject().clearContext();
            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }
    }

    private static Object getObject() {
        return SecurityContextHolder;
    }

    private List<SimpleGrantedAuthority> authorities(List<String> roles){
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
