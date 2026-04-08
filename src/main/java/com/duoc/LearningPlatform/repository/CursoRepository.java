package com.duoc.LearningPlatform.repository;

import com.duoc.LearningPlatform.model.Curso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository {

    private List<Curso> cursos = new ArrayList<>();

    public CursoRepository() {
        cursos.add(new Curso(1L, "Java Básico", "Programación", true));
        cursos.add(new Curso(2L, "Spring Boot", "Backend", true));
        cursos.add(new Curso(3L, "Angular", "Frontend", false));
        cursos.add(new Curso(4L, "Base de Datos", "SQL", true));
        cursos.add(new Curso(5L, "Docker", "DevOps", true));
    }

    public List<Curso> findAll() {
        return cursos;
    }
}