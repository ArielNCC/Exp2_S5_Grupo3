package com.duoc.LearningPlatform.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.LearningPlatform.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, String> {

	ArrayList<Curso> findByActivo(boolean activo);

	ArrayList<Curso> findByCategoriaIgnoreCase(String categoria);

	ArrayList<Curso> findByProfesorIgnoreCase(String profesor);

	java.util.Optional<Curso> findByIndiceIgnoreCase(String indice);

	ArrayList<Curso> findByCategoriaAndActivo(String categoria, boolean activo);
}