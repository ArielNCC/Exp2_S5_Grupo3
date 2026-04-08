package com.duoc.LearningPlatform.service;

import com.duoc.LearningPlatform.model.Curso;
import com.duoc.LearningPlatform.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    // 🔥 Método solicitado: filtrar + ordenar + presentar
    public List<Curso> obtenerCursosActivosOrdenados() {
        return repository.findAll().stream()
                .filter(Curso::isActivo)
                .sorted(Comparator.comparing(Curso::getNombre))
                .toList();
    }
}