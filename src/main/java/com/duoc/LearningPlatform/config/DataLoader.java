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
                    crearCurso("c001", "Fundamentos de Programacion", "Desarrollo de Software", "Ana Rojas", true),
                    crearCurso("c002", "Antropología", "Humanidades", "Paula Fuentes", true),
                    crearCurso("c003", "Desarrollo Backend I", "Desarrollo de Software", "Diego Morales", false),
                    crearCurso("c004", "Gestion de Requisitos", "Desarrollo de Software", "Camila Soto", true),
                    crearCurso("c005", "Seguridad y Calidad en el Desarrollo", "Desarrollo de Software", "Javier Paredes", true),
                    crearCurso("c006", "Herramientas Devops", "Infraestructura y Cloud", "Ricardo Mena", true),
                    crearCurso("c007", "Programacion de bases de datos", "Bases de Datos", "Diego Morales", false),
                    crearCurso("c008", "Consultas de bases de datos", "Bases de Datos", "Camila Soto", true),
                    crearCurso("c009", "Etica para el trabajo", "Humanidades", "Paula Fuentes", true),
                    crearCurso("c010", "Introduccion al cloud computing", "Infraestructura y Cloud", "Ricardo Mena", true),
                    crearCurso("c011", "Desarrollo orientado a objetos II", "Desarrollo de Software", "Ana Rojas", true),
                    crearCurso("c012", "Modelamiento de bases de datos", "Bases de Datos", "Javier Paredes", false),
                    crearCurso("c013", "Desarrollo orientado a objetos I", "Desarrollo de Software", "Ana Rojas", true),
                    crearCurso("c014", "Fundamentos de antropología", "Humanidades", "Paula Fuentes", true),
                    crearCurso("c015", "Fundamentos de programación", "Desarrollo de Software", "Diego Morales", true)
            ));
            cursos.saveAll(cursosSemilla);
        };
    }

    private Curso crearCurso(String indice, String nombre, String categoria, String profesor, boolean activo) {
        Curso curso = new Curso();
        curso.setIndice(indice);
        curso.setNombre(nombre);
        curso.setCategoria(categoria);
        curso.setProfesor(profesor);
        curso.setActivo(activo);
        return curso;
    }
}