# LearningPlatform API

Backend Spring Boot para gestionar cursos en memoria con H2.

## Requisitos

- Java 21
- Maven Wrapper (incluido en el proyecto)

## Ejecucion

```bash
./mvnw spring-boot:run
```

Base URL local:

- `http://localhost:8080`

## Modelo de respuesta: Curso

```json
{
  "id": "b9f5bb81-1d17-4f8a-8eb4-2a2bba65fdcb",
  "indice": "c001",
  "nombre": "Fundamentos de Programacion",
  "categoria": "Desarrollo de Software",
  "profesor": "Ana Rojas",
  "activo": true
}
```

## Endpoints

Nota para endpoints con texto en la ruta (categoria/profesor):

- Usa `-` en lugar de espacios. Ejemplo: `Ana-Rojas`, `Desarrollo-de-Software`.
- El backend convierte automaticamente `-` a espacio antes de consultar.

### 0) Saludo de inicio

- Metodo: `GET`
- Ruta: `/`
- Respuesta: `200 OK` con texto plano

Ejemplo:

```bash
curl -X GET "http://localhost:8080/"
```

Respuesta esperada:

```text
LearningPlatform API version 1.0
```

---

### 1) Listado de todos los cursos

- Metodo: `GET`
- Ruta: `/api/v1/cursos`
- Respuesta: `200 OK` con arreglo de cursos

Ejemplo:

```bash
curl -X GET "http://localhost:8080/api/v1/cursos"
```

---

### 2) Listado por indice

- Metodo: `GET`
- Ruta: `/api/v1/cursos/indice/{indice}`
- Path variable:
  - `indice` (ejemplo: `c001`)
- Respuestas:
  - `200 OK` si existe
  - `404 Not Found` si no existe

Ejemplo:

```bash
curl -X GET "http://localhost:8080/api/v1/cursos/indice/c001"
```

---

### 3) Listado por disponible o no disponible

- Metodo: `GET`
- Ruta: `/api/v1/cursos/disponibilidad/{activo}`
- Path variable:
  - `activo`: `true` o `false`
- Respuesta: `200 OK` con arreglo de cursos

Ejemplos:

```bash
curl -X GET "http://localhost:8080/api/v1/cursos/disponibilidad/true"
curl -X GET "http://localhost:8080/api/v1/cursos/disponibilidad/false"
```

---

### 4) Listado por categoria

- Metodo: `GET`
- Ruta: `/api/v1/cursos/categoria/{categoria}`
- Path variable:
  - `categoria` (no distingue mayusculas/minusculas)
- Respuesta: `200 OK` con arreglo de cursos

Ejemplo:

```bash
curl -X GET "http://localhost:8080/api/v1/cursos/categoria/Desarrollo-de-Software"
```

---

### 5) Listado por profesor que imparte

- Metodo: `GET`
- Ruta: `/api/v1/cursos/profesor/{profesor}`
- Path variable:
  - `profesor` (no distingue mayusculas/minusculas)
- Respuesta: `200 OK` con arreglo de cursos

Ejemplo:

```bash
curl -X GET "http://localhost:8080/api/v1/cursos/profesor/Ana-Rojas"
```

## Utilidades del proyecto

### Consola H2

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:deliverybd`
- User: `sa`
- Password: *(vacio)*

### Actuator

- Health: `http://localhost:8080/actuator/health`
- Info: `http://localhost:8080/actuator/info`

## Nota de datos semilla

Al iniciar la aplicacion, `DataLoader` elimina y vuelve a poblar los cursos para asegurar datos consistentes en cada arranque.
