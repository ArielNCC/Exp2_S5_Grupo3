package com.duoc.LearningPlatform.service;

import java.util.Arrays;
import com.duoc.LearningPlatform.model.Curso;
import com.duoc.LearningPlatform.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private final CursoRepository repository;
    private final List<Boolean> prioridadActivos;

    public CursoService(CursoRepository repository, @Value("${app.cursos.prioridad}") String prioridadActivos) {
        this.repository = repository;

        if (prioridadActivos == null || prioridadActivos.isBlank()) {
            this.prioridadActivos = List.of();
        } else {
            this.prioridadActivos = Arrays.stream(prioridadActivos.split(","))
                    .map(String::trim)
                    .map(Boolean::parseBoolean)
                    .toList();
        }
    }

    public List<Curso> listarTodos() {
        return ordenar(repository.findAll());
    }

    public Optional<Curso> obtenerPorIndice(String indice) {
        return repository.findByIndiceIgnoreCase(indice);
    }

    public List<Curso> listarPorDisponibilidad(boolean activo) {
        return ordenar(repository.findByActivo(activo));
    }

    public List<Curso> listarPorCategoria(String categoria) {
        return ordenar(repository.findByCategoriaIgnoreCase(normalizarTextoPath(categoria)));
    }

    public List<Curso> listarPorProfesor(String profesor) {
        return ordenar(repository.findByProfesorIgnoreCase(normalizarTextoPath(profesor)));
    }

    private List<Curso> ordenar(List<Curso> base) {

        Comparator<Curso> porActivoPreferente = Comparator.comparingInt(c -> indexOrMax(c.isActivo(), prioridadActivos));
        Comparator<Curso> porNombre = Comparator.comparing(Curso::getNombre);
        Comparator<Curso> comp = porActivoPreferente.thenComparing(porNombre).thenComparing(Curso::getIndice);

        return base.stream().sorted(comp).collect(Collectors.toList());
    }

    private int indexOrMax(boolean valor, List<Boolean> orden) {
        int idx = orden.indexOf(valor);
        return idx < 0 ? Integer.MAX_VALUE : idx;
    }

    private String normalizarTextoPath(String valor) {
        if (valor == null) {
            return "";
        }

        return valor.trim().replace('-', ' ');
    }
}