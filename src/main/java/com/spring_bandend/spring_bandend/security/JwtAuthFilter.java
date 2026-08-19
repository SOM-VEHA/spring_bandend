//package com.spring_bandend.spring_bandend.security;
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.spring_bandend.spring_bandend.entity.User;
//import com.spring_bandend.spring_bandend.feature.core.role.dto.filter.RoleFilter;
//import com.spring_bandend.spring_bandend.feature.core.role.dto.response.RoleResponse;
//import com.spring_bandend.spring_bandend.feature.core.role.service.RoleService;
//import com.spring_bandend.spring_bandend.feature.core.user.repository.UserRepository;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthFilter extends OncePerRequestFilter{
//
//    private static final String BEARER_PREFIX = "Bearer ";
//
//    private final JwtService jwtService;
//
//    private  final RoleService roleService;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        // 1) Pull token from Authorization header (may be null on public routes)
//        String token = resolveToken(request);
//        System.out.println("Request");
//        System.out.println(request);
//        System.out.println("token");
//        System.out.println(token);
//
//
//
//        // 2) No token or invalid signature/expiry — pass through without authentication
//        //    (permitAll routes still work; protected routes fail later with 401)
//        if (token == null || token.isBlank() || !jwtService.validateToken(token)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // 3) Valid token — read the subject (username) from claims
//        String username = jwtService.getUsernameFromToken(token);
//
//        // 4) Only set authentication if the context is still empty (avoid overwriting)
//        if (SecurityContextHolder.getContext().getAuthentication() == null) {
//            final RoleFilter roleFilter = new RoleFilter();
//            Page<RoleResponse> page = roleService.getAllPaginationFilter(roleFilter);
//            List<RoleResponse> roleResponses = page.getContent();
//            List<GrantedAuthority> authorities = roleResponses.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());
//            System.out.println(authorities);
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        // 5) Continue down the filter chain — controller can now read the authenticated user
//        chain.doFilter(request, response);
//    }
//    // Stateless
//    private String resolveToken(HttpServletRequest request) {
//        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        System.out.println(authHeader);
//        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
//            return authHeader.substring(BEARER_PREFIX.length());
//        }
//        return null;
//    }
//
//}

package com.spring_bandend.spring_bandend.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.spring_bandend.spring_bandend.feature.auth.service.impl.TokenServiceImpl;
import com.spring_bandend.spring_bandend.feature.core.role.dto.filter.RoleFilter;
import com.spring_bandend.spring_bandend.feature.core.role.dto.response.RoleResponse;
import com.spring_bandend.spring_bandend.feature.core.role.service.RoleService;
import com.spring_bandend.spring_bandend.feature.intergtation.redis.RedisService;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";
    private final JwtService jwtService;
    private final RoleService roleService;
    private final RedisService redisService; // 1) Inject RedisService
    private final RestAuthenticationEntryPoint authenticationEntryPoint;
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {

        // 1) ទាញយក token ចេញពី Authorization header
        String token = resolveToken(request);
        // 2) បើគ្មាន token ឬ invalid
        if (token == null || token.isBlank() || !jwtService.validateToken(token)) {
            chain.doFilter(request, response);
            return;
        }
        // 3) ទាញយក username ចេញពី token
        String username = jwtService.getUsernameFromToken(token);
        // 4) ទាញយក active token ចេញពី Redis (ប្រើ Optional)
        Optional<String> activeTokenOptional = redisService.get(TokenServiceImpl.TOKEN_KEY_PREFIX + username);

        // 5) ឆែកមើល៖ បើគ្មានក្នុង Redis ឬ Token មិនដូចគ្នា មានន័យថាជា Token ចាស់
        if (activeTokenOptional.isEmpty() || !activeTokenOptional.get().equals(token)) {
            chain.doFilter(request, response);
            return; // បញ្ឈប់ filter chain
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            final RoleFilter roleFilter = new RoleFilter();
            Page<RoleResponse> page = roleService.getAllPaginationFilter(roleFilter);
            List<RoleResponse> roleResponses = page.getContent();
            List<GrantedAuthority> authorities = roleResponses.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(BEARER_PREFIX.length());
        }
        return null;
    }
    
}