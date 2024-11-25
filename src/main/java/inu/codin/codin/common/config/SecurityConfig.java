package inu.codin.codin.common.config;

import inu.codin.codin.common.security.filter.ExceptionHandlerFilter;
import inu.codin.codin.common.security.filter.JwtAuthenticationFilter;
import inu.codin.codin.common.security.jwt.JwtTokenProvider;
import inu.codin.codin.common.security.jwt.JwtUtils;
import inu.codin.codin.common.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(CsrfConfigurer::disable) // csrf 비활성화
                .cors(AbstractHttpConfigurer::disable) // cors 비활성화
                .formLogin(FormLoginConfigurer::disable) // form login 비활성화
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용하지 않음
                )
                .httpBasic(Customizer.withDefaults()) // httpBasic 활성화
                // authorizeHttpRequests 메서드를 통해 요청에 대한 권한 설정
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(PERMIT_ALL).permitAll()
                                .requestMatchers(SWAGGER_AUTH_PATHS).hasRole("ADMIN")
                                .requestMatchers(ADMIN_AUTH_PATHS).hasRole("ADMIN")
                                .requestMatchers(MANAGER_AUTH_PATHS).hasRole("MANAGER")
                                .requestMatchers(USER_AUTH_PATHS).hasRole("USER")
                                .anyRequest().hasRole("USER")
                )
                // JwtAuthenticationFilter 추가
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService, jwtService, jwtUtils),
                        UsernamePasswordAuthenticationFilter.class
                )
                // 예외 처리 필터 추가
                .addFilterBefore(new ExceptionHandlerFilter(), LogoutFilter.class);

        http.setSharedObject(AuthenticationManager.class, authenticationManager(http));
        http.setSharedObject(RoleHierarchy.class, roleHierarchy());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > ROLE_MANGER > ROLE_USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] PERMIT_ALL = {
            "/auth/login",
            "/auth/reissue",
            "/auth/logout",
            "/users/sign-up",
            "/email/auth/check",
            "/email/auth/send",
            "/v3/api/test1",
    };

    private static final String[] SWAGGER_AUTH_PATHS = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/swagger-resources/**",
    };

    private static final String[] USER_AUTH_PATHS = {
            "/v3/api/test2",
            "/v3/api/test3",
    };

    private static final String[] ADMIN_AUTH_PATHS = {
            "/v3/api/test4",
    };

    private static final String[] MANAGER_AUTH_PATHS = {
            "/v3/api/test5",
    };

}
