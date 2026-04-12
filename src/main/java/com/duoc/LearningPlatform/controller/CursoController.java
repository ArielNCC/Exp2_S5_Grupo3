package com.duoc.LearningPlatform.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.duoc.LearningPlatform.model.Curso;
import com.duoc.LearningPlatform.service.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/indice/{indice}")
    public ResponseEntity<Curso> listarPorIndice(@PathVariable String indice) {
        return service.obtenerPorIndice(indice)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibilidad/{activo}")
    public ResponseEntity<List<Curso>> listarPorDisponibilidad(@PathVariable boolean activo) {
        return ResponseEntity.ok(service.listarPorDisponibilidad(activo));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Curso>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.listarPorCategoria(categoria));
    }

    @GetMapping("/profesor/{profesor}")
    public ResponseEntity<List<Curso>> listarPorProfesor(@PathVariable String profesor) {
        return ResponseEntity.ok(service.listarPorProfesor(profesor));
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = service.crearCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso); // Retorna 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable String id, @RequestBody Curso curso) {
        return service.actualizarCurso(id, curso)
                .map(ResponseEntity::ok) // Retorna 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna 404 si no existe
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable String id) {
        if (service.eliminarCurso(id)) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no existe
    }
}