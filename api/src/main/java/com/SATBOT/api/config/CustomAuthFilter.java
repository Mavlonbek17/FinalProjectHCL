package com.SATBOT.api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class CustomAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if( !request.getRequestURL().isEmpty() && (request.getServletPath().equals("/login")) ){
            filterChain.doFilter(request , response);
        } else {
            String authHeader = request.getHeader(AUTHORIZATION);

            if( authHeader == null ){
                response.setHeader("error" , "Not Token");
                response.sendError( 403 );
            } else {
                if( !authHeader.substring(0 ,7 ).equals("Bearer ") ){
                    response.setHeader("Error", "Invalid Token Type");
                    response.sendError( 403 );
                } else {
                    try {
                        String token = authHeader.substring( "Bearer ".length() );
                        Algorithm algorithm = Algorithm.HMAC512( "Mavlonbek".getBytes());

                        JWTVerifier verifier = JWT.require(algorithm).build();

                        DecodedJWT decodedJWT = verifier.verify(token);

                        String username = decodedJWT.getSubject();



                        List<GrantedAuthority> list = new ArrayList<>();

                        String[] myroles =   decodedJWT.getClaim("roles").asArray(String.class);

                        for (String role : myroles) {
                            GrantedAuthority grantedAuthority = new GrantedAuthority() {
                                @Override
                                public String getAuthority() {
                                    return role;
                                }
                            };

                            list.add(grantedAuthority);
                        }

                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(username , null  , list  );

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                        filterChain.doFilter(request , response);

                    } catch (Exception e){
                        response.setHeader("error", "Token Invalid");
                        response.setHeader("cachedError", e.getMessage());
                        response.sendError( 403 );
                    }
                }
            }


        }
    }


}
