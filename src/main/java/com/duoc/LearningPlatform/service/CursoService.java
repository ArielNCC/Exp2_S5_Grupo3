package com.duoc.LearningPlatform.service;

import java.util.ArrayList;
import com.duoc.LearningPlatform.model.Curso;
import com.duoc.LearningPlatform.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Curso> listarTodos() {
        return ordenar(new ArrayList<>(repository.findAll()));
    }

    public Optional<Curso> obtenerPorIndice(String indice) {
        return repository.findByIndiceIgnoreCase(indice);
    }

    public ArrayList<Curso> listarPorDisponibilidad(boolean activo) {
        return ordenar(new ArrayList<>(repository.findByActivo(activo)));
    }

    public ArrayList<Curso> listarPorCategoria(String categoria) {
        return ordenar(new ArrayList<>(repository.findByCategoriaIgnoreCase(normalizarTextoPath(categoria))));
    }

    public ArrayList<Curso> listarPorProfesor(String profesor) {
        return ordenar(new ArrayList<>(repository.findByProfesorIgnoreCase(normalizarTextoPath(profesor))));
    }


    // Crear nuevo curso (POST)
    public Curso crearCurso(Curso curso) {
        return repository.save(curso);
    }

    // Actualizar curso existente (PUT)
    public Optional<Curso> actualizarCurso(String id, Curso cursoActualizado) {
        return repository.findById(id).map(cursoExistente -> {
            // Actualizamos los campos permitidos
            cursoExistente.setIndice(cursoActualizado.getIndice());
            cursoExistente.setNombre(cursoActualizado.getNombre());
            cursoExistente.setCategoria(cursoActualizado.getCategoria());
            cursoExistente.setProfesor(cursoActualizado.getProfesor());
            cursoExistente.setActivo(cursoActualizado.isActivo());
            
            // Guardamos el curso modificado en la bd
            return repository.save(cursoExistente);
        });
    }

    // Eliminar curso (DELETE)
    public boolean eliminarCurso(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private ArrayList<Curso> ordenar(ArrayList<Curso> base) {

        Comparator<Curso> porDisponible = Comparator.comparing(Curso::isActivo).reversed();
        Comparator<Curso> porIndiceAsc = Comparator.comparing(Curso::getIndice, String.CASE_INSENSITIVE_ORDER);
        Comparator<Curso> comp = porDisponible.thenComparing(porIndiceAsc);

        return base.stream().sorted(comp).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private String normalizarTextoPath(String valor) {
        if (valor == null) {
            return "";
        }

        return valor.trim().replace('-', ' ');
    }
}