package com.duoc.LearningPlatform.config;

import java.util.ArrayList;
import java.util.Arrays;

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
            cursos.deleteAll();
            ArrayList<Curso> cursosSemilla = new ArrayList<>();
                cursosSemilla.addAll(Arrays.asList(
                    new Curso("c001", "Fundamentos de Programacion", "Desarrollo de Software", "Ana Rojas", true),
                    new Curso("c002", "Antropología", "Humanidades", "Paula Fuentes", true),
                    new Curso("c003", "Desarrollo Backend I", "Desarrollo de Software", "Diego Morales", false),
                    new Curso("c004", "Gestion de Requisitos", "Desarrollo de Software", "Camila Soto", true),
                    new Curso("c005", "Seguridad y Calidad en el Desarrollo", "Desarrollo de Software", "Javier Paredes", true),
                    new Curso("c006", "Herramientas Devops", "Infraestructura y Cloud", "Ricardo Mena", true),
                    new Curso("c007", "Programacion de bases de datos", "Bases de Datos", "Diego Morales", false),
                    new Curso("c008", "Consultas de bases de datos", "Bases de Datos", "Camila Soto", true),
                    new Curso("c009", "Etica para el trabajo", "Humanidades", "Paula Fuentes", true),
                    new Curso("c010", "Introduccion al cloud computing", "Infraestructura y Cloud", "Ricardo Mena", true),
                    new Curso("c011", "Desarrollo orientado a objetos II", "Desarrollo de Software", "Ana Rojas", true),
                    new Curso("c012", "Modelamiento de bases de datos", "Bases de Datos", "Javier Paredes", false),
                    new Curso("c013", "Desarrollo orientado a objetos I", "Desarrollo de Software", "Ana Rojas", true),
                    new Curso("c014", "Fundamentos de antropología", "Humanidades", "Paula Fuentes", true),
                    new Curso("c015", "Fundamentos de programación", "Desarrollo de Software", "Diego Morales", true)
            ));
            cursos.saveAll(cursosSemilla);
        };
    }
}