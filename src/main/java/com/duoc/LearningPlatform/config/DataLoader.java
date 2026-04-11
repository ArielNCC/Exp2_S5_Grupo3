package com.duoc.LearningPlatform.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.duoc.LearningPlatform.model.Curso;
import com.duoc.LearningPlatform.repository.CursoRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seed(CursoRepository cursos) {
        return args -> {
            if (cursos.count() == 0) {
                cursos.saveAll(List.of(
                        new Curso("Java Básico", "Programación", true),
                        new Curso("Spring Boot", "Backend", true),
                        new Curso("Angular", "Frontend", false),
                        new Curso("Base de Datos", "SQL", true),
                        new Curso("Docker", "DevOps", true)
                ));
            }
        };
    }
}