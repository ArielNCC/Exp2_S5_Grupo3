package com.duoc.LearningPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.LearningPlatform.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, String> {

	List<Curso> findByActivo(boolean activo);

	List<Curso> findByCategoria(String categoria);

	List<Curso> findByCategoriaAndActivo(String categoria, boolean activo);
}