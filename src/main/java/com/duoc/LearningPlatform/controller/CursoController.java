package com.duoc.LearningPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.LearningPlatform.model.Curso;
import com.duoc.LearningPlatform.service.CursoService;

import jakarta.validation.constraints.Pattern;

@Validated
@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) @Pattern(regexp = "true|false") String activo) {

        Optional<Boolean> activoBool = Optional.ofNullable(activo).map(Boolean::parseBoolean);
        List<Curso> cursos = service.listarCursos(Optional.ofNullable(categoria), activoBool);

        return ResponseEntity.ok(cursos);
    }
}