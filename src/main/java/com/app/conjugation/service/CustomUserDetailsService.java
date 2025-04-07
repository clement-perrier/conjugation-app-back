package com.app.conjugation.service;

import com.app.conjugation.model.CustomUserDetails;
import com.app.conjugation.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {
    User loadUserById(String userId) throws UsernameNotFoundException;
}



//@Configuration
//public class ApplicationConfiguration {
//
//    private final CustomUserDetailsService customUserDetailsService;
//
//    public ApplicationConfiguration(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        // Use the custom UserDetailsService for authentication
//        authProvider.setUserDetailsService(customUserDetailsService);
//        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}


//@Override
//protected void doFilterInternal(
//        @NonNull HttpServletRequest request,
//        @NonNull HttpServletResponse response,
//        @NonNull FilterChain filterChain
//) throws ServletException, IOException {
//    final String authHeader = request.getHeader("Authorization");
//
//    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//        filterChain.doFilter(request, response);
//        return;
//    }
//
//    try {
//        final String jwt = authHeader.substring(7);
//        final Integer userId = jwtService.extractUserId(jwt);  // Extract userId from the JWT
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (userId != null && authentication == null) {
//            // Use the custom method to load the user by ID
//            UserDetails userDetails = this.customUserDetailsService.loadUserById(userId);
//
//            if (jwtService.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    } catch (Exception exception) {
//        handlerExceptionResolver.resolveException(request, response, null, exception);
//    }
//}

