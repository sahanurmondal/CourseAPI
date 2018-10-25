package com.springbootstarter.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class VerifyAccessInterceptor implements HandlerInterceptor {

    // ...

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /* Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (auth.isAuthenticated()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        User userFromDatabase = getUserFromDatabase(auth.getName());
        if (userFromDatabase != null) {
            // add whatever authorities you want here
            authorities.add(new SimpleGrantedAuthority("..."));
        }

        Authentication newAuth = null;

        if (auth.getClass() == OAuth2AuthenticationToken.class) {
            OAuth2User principal = ((OAuth2AuthenticationToken)auth).getPrincipal();
            if (principal != null) {
                newAuth = new OAuth2AuthenticationToken(principal, authorities,(((OAuth2AuthenticationToken)auth).getAuthorizedClientRegistrationId()));
            }
        }

        SecurityContextHolder.getContext().setAuthentication(newAuth);*/

        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        System.out.println("current authority : "+updatedAuthorities.get(0));
        updatedAuthorities.add(new SimpleGrantedAuthority("USER")); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        System.out.println("no of auth : "+updatedAuthorities.size()+" new added role "+updatedAuthorities.get(1));
        SecurityContextHolder.getContext().setAuthentication(newAuth);*/

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Where User is an my own defined entity which implements UserDetails interface
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.add(new SimpleGrantedAuthority("USER"));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user,
                "password",
                updatedAuthorities); //Where authorities is a List<GrantedAuthority> = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));

       /// Authentication authenticatedUser = AuthenticationManager.authenticate(token);//--------A
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        return true;
    }

}