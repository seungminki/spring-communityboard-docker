package dev.community.config;

import dev.community.filter.JwtAuthenticationFilter;
import dev.community.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtUtil jwtUtil;

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//			.csrf(AbstractHttpConfigurer::disable)
//			.authorizeHttpRequests(authz -> authz
//				.requestMatchers("/members/new", "/members/login", "/boards/all", "/boards/all/*", "/comments/all", "/comments/all/*", "/swagger-ui.html", "/api-docs", "/swagger-ui", "/h2-console").permitAll()
//				.anyRequest().authenticated()
//			)
//			.formLogin(AbstractHttpConfigurer::disable)
//			.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.headers(headers -> headers
				.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable) // 이게 뭔데
			)
				.authorizeHttpRequests(authz -> authz
				.anyRequest().permitAll()  // 모든 요청 허용
			)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
