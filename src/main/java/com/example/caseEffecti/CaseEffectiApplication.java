package com.example.caseEffecti;

import com.example.caseEffecti.service.LicitacoesService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CaseEffectiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseEffectiApplication.class, args);

	}

	@Bean
    public CommandLineRunner run(LicitacoesService licitacoesService) {
        return args -> {
            licitacoesService.createObj();
        };
    }

}
