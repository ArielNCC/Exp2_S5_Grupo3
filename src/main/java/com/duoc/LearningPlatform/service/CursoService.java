package com.duoc.LearningPlatform.service;

import com.duoc.LearningPlatform.model.Curso;
import com.duoc.LearningPlatform.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> listarCursos(Optional<String> categoria, Optional<Boolean> activo) {
        List<Curso> cursos = categoria
                .map(cat -> activo
                        .map(act -> repository.findByCategoriaAndActivo(cat, act))
                        .orElseGet(() -> repository.findByCategoria(cat)))
                .orElseGet(() -> activo
                        .map(repository::findByActivo)
                        .orElseGet(repository::findAll));

        return cursos.stream()
                .sorted(Comparator.comparing(Curso::getNombre))
                .toList();
    }
}