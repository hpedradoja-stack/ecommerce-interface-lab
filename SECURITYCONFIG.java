import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

@PostMapping
@PreAuthorize("isAuthenticated()")
public Order createOrder(@RequestBody Order order) {
    return orderService.save(order);
}

@PreAuthorize("hasAnyRole('ADMIN','USER')")
   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .authorizeHttpRequests(auth -> auth

            .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**")
                .hasRole("ADMIN")

            .requestMatchers(HttpMethod.POST, "/api/v1/orders/**")
                .authenticated()

            .anyRequest().permitAll()
        )
        .formLogin(form -> form.permitAll());

    return http.build();
}'
  hasRole("ADMIN")   // NO "ROLE_" prefix
