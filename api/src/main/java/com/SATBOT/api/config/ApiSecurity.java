package com.SATBOT.api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ApiSecurity extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager;

    ApiSecurity(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username , password);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC512( "Mavlonbek".getBytes());

        List<String> list = new ArrayList<>();

        if( userDetails.getAuthorities().stream().anyMatch( a -> a.getAuthority().equals("ROLE_USER") )){
            list.add("ROLE_USER");
        }

        if( userDetails.getAuthorities().stream().anyMatch( a -> a.getAuthority().equals("ROLE_ADMIN") )){
            list.add("ROLE_ADMIN");
        }

        String access_token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer( request.getRequestURL().toString() )
                .withClaim("roles" , list)
                .withExpiresAt( new Date( System.currentTimeMillis() + 10*60*60*1000))
                .sign(algorithm);


//        response.setHeader("access_token" , access_token);
//        response.setHeader("refresh_token" , refresh_token);

        Map<String, String> map = new HashMap<>();
        map.put( "access_token" , access_token);

        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), map);
    }
}
