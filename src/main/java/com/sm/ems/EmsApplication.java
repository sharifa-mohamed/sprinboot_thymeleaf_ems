package com.sm.ems;

import com.sm.ems.security.service.AuditAwareImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sm.ems"})
@EnableJpaAuditing(auditorAwareRef = "auditAware")
@OpenAPIDefinition
//@EnableJpaRepositories("com.sm.ems.department.repository")
public class EmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsApplication.class, args);
    }


    @Bean
    public AuditorAware<String> auditAware() {
        return new AuditAwareImpl();
    }
}
