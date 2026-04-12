package com.duoc.LearningPlatform.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.LearningPlatform.model.Curso;
import com.duoc.LearningPlatform.service.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Curso>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/indice/{indice}")
    public ResponseEntity<Curso> listarPorIndice(@PathVariable String indice) {
        return service.obtenerPorIndice(indice)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibilidad/{activo}")
    public ResponseEntity<ArrayList<Curso>> listarPorDisponibilidad(@PathVariable boolean activo) {
        return ResponseEntity.ok(service.listarPorDisponibilidad(activo));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<ArrayList<Curso>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.listarPorCategoria(categoria));
    }

    @GetMapping("/profesor/{profesor}")
    public ResponseEntity<ArrayList<Curso>> listarPorProfesor(@PathVariable String profesor) {
        return ResponseEntity.ok(service.listarPorProfesor(profesor));
    }
}