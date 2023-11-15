package com.alibou.security;

import com.alibou.security.auth.AuthenticationService;
import com.alibou.security.auth.RegisterRequest;
import com.alibou.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.alibou.security.user.Role.admin;
import static com.alibou.security.user.Role.client;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	//@Profile("dev")
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.nom("Admin")
					.prenom("Admin")
					.contact("778793245")
					.email("thian@mail.com")
					.password("password")
					.role(Role.admin)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var client = RegisterRequest.builder()
					.nom("Admin")
					.prenom("Admin")
					.contact("773214565")
					.email("client@mail.com")
					.password("password")
					.role(Role.client)
					.build();
			System.out.println("Client token: " + service.register(client).getAccessToken());


			var paysan = RegisterRequest.builder()
					.nom("paysan")
					.prenom("paysan")
					.contact("773214565")
					.email("paysan@mail.com")
					.password("password")
					.role(Role.paysan)
					.build();
			System.out.println("Paysan token: " + service.register(paysan).getAccessToken());

			var employe = RegisterRequest.builder()
					.nom("Niass")
					.prenom("Astou")
					.contact("773214565")
					.email("employe@mail.com")
					.password("password")
					.role(Role.employe)
					.build();
			System.out.println("Employe token: " + service.register(employe).getAccessToken());
		};
	}
}
