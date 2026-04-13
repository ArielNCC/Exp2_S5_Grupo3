# Endpoints LearningPlatform

Base URL:

`http://localhost:8080/api/v1`

## GET

- `GET /cursos`
- `GET /cursos/indice/{indice}`
- `GET /cursos/disponibilidad/{activo}`
- `GET /cursos/categoria/{categoria}`
- `GET /cursos/profesor/{profesor}`

## POST

- `POST /cursos`

## PUT

- `PUT /cursos/{id}`

## DELETE

- `DELETE /cursos/{id}`

## Mini Tabla De Codigos HTTP

| Codigo | Nombre | Cuando aplica en esta API |
|---|---|---|
| 200 | OK | GET exitoso, PUT exitoso |
| 201 | Creado | POST exitoso |
| 204 | Sin contenido | DELETE exitoso |
| 400 | Solicitud incorrecta | Body o parametros invalidos |
| 403 | Prohibido | Intento de cambiar el indice en PUT |
| 404 | No encontrado | Recurso no existe |
| 405 | Metodo no permitido | Verbo HTTP incorrecto para endpoint existente |
| 501 | No implementado | Ruta bajo `/api/v1` no implementada |

## Ejemplos JSON Por Codigo

### 200 OK

```json
{
	"id": "a21f3aa4-3bb5-4757-ac5a-4f83d7960f58",
	"indice": "c001",
	"nombre": "Fundamentos de Programacion",
	"categoria": "Desarrollo de Software",
	"profesor": "Ana Rojas",
	"activo": true
}
```

### 201 Creado

```json
{
	"id": "d9df8f1e-50cc-4e2f-a94e-4f7bceef5a7a",
	"indice": "c099",
	"nombre": "Prueba API",
	"categoria": "Desarrollo de Software",
	"profesor": "Tester QA",
	"activo": true
}
```

### 204 Sin Contenido

```json
{}
```

### 400 Solicitud Incorrecta

```json
{
	"status": 400,
	"error": "Bad Request",
	"message": "Solicitud incorrecta",
	"path": "/api/v1/cursos"
}
```

### 403 Prohibido

```json
{
	"status": 403,
	"error": "Forbidden",
	"message": "No se permite cambiar el indice del curso",
	"path": "/api/v1/cursos/{id}"
}
```

### 404 No Encontrado

```json
{
	"status": 404,
	"error": "Not Found",
	"message": "Recurso no encontrado",
	"path": "/api/v1/cursos/indice/c999"
}
```

### 405 Metodo No Permitido

```json
{
	"status": 405,
	"error": "Method Not Allowed",
	"message": "Metodo no permitido para este endpoint",
	"path": "/api/v1/cursos"
}
```

### 501 No Implementado

```json
{
	"status": 501,
	"error": "Not Implemented",
	"message": "Endpoint no implementado para /api/v1",
	"path": "/api/v1/cualquiercosaquenoexiste"
}
```