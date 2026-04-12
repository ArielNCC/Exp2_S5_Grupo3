package com.duoc.LearningPlatform.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.duoc.LearningPlatform.model.Curso;
import com.duoc.LearningPlatform.service.CursoService;

@RestController
@RequestMapping("/api/v1")
public class CursoController {

    @Autowired
    private CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping("/cursos")
    public ResponseEntity<ArrayList<Curso>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/cursos/indice/{indice}")
    public ResponseEntity<Curso> listarPorIndice(@PathVariable String indice) {
        return service.obtenerPorIndice(indice)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cursos/disponibilidad/{activo}")
    public ResponseEntity<ArrayList<Curso>> listarPorDisponibilidad(@PathVariable boolean activo) {
        return ResponseEntity.ok(service.listarPorDisponibilidad(activo));
    }

    @GetMapping("/cursos/categoria/{categoria}")
    public ResponseEntity<ArrayList<Curso>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.listarPorCategoria(categoria));
    }

    @GetMapping("/cursos/profesor/{profesor}")
    public ResponseEntity<ArrayList<Curso>> listarPorProfesor(@PathVariable String profesor) {
        return ResponseEntity.ok(service.listarPorProfesor(profesor));
    }

    @PostMapping("/cursos")
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = service.crearCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso); // Retorna 201 Created
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable String id, @RequestBody Curso curso) {
        return service.actualizarCurso(id, curso)
                .map(ResponseEntity::ok) // Retorna 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna 404 si no existe
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable String id) {
        if (service.eliminarCurso(id)) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no existe
    }
}