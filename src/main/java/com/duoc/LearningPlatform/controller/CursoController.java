package com.duoc.LearningPlatform.controller;

import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Map<String, Object>> inicio() {
        return ResponseEntity.ok(Map.of(
                "status", HttpStatus.OK.value(),
                "message", "LearningPlatform API version 1.0",
                "basePath", "/api/v1"
        ));
    }

    @GetMapping("/cursos")
    public ResponseEntity<ArrayList<Curso>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/cursos/indice/{indice}")
    public ResponseEntity<Curso> listarPorIndice(@PathVariable String indice) {
        if (indice == null || indice.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

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
        if (cursoInvalido(curso)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Curso nuevoCurso = service.crearCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable String id, @RequestBody Curso curso) {
        if (id == null || id.isBlank() || cursoInvalido(curso)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return service.actualizarCurso(id, curso)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable String id) {
        if (id == null || id.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (service.eliminarCurso(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping("/**")
    public ResponseEntity<Map<String, Object>> endpointNoImplementado(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(Map.of(
                "status", HttpStatus.NOT_IMPLEMENTED.value(),
                "error", HttpStatus.NOT_IMPLEMENTED.getReasonPhrase(),
                "message", "Endpoint no implementado para /api/v1",
                "path", request.getRequestURI()
        ));
    }

    private boolean cursoInvalido(Curso curso) {
        return curso == null
                || esVacio(curso.getIndice())
                || esVacio(curso.getNombre())
                || esVacio(curso.getCategoria())
                || esVacio(curso.getProfesor());
    }

    private boolean esVacio(String valor) {
        return valor == null || valor.isBlank();
    }
}