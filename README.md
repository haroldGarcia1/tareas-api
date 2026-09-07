# API REST de Gestión de Tareas

Aplicativo REST para administrar tareas mediante operaciones CRUD. El proyecto fue desarrollado con Java y Spring Boot, priorizando una estructura por capas, validaciones, manejo de errores y pruebas automatizadas.

El aplicativo permite crear, consultar, actualizar y eliminar tareas. La persistencia utiliza H2 en memoria, por lo que no requiere instalar un servidor de base de datos para ejecutar el proyecto localmente.

## Tecnologías

- Java 21
- Spring Boot 4.1.1
- Maven
- Spring Web MVC
- Spring Data JPA
- H2 Database
- Bean Validation
- JUnit y MockMvc
- Docker

Se eligió Spring Boot porque permite construir la API rápidamente y separar el código en controladores, servicios, repositorios, entidades y DTOs. Maven administra las dependencias y los comandos de compilación y pruebas.

## Requisitos

- JDK 21
- Git
- Docker Desktop, para ejecutar la aplicación en un contenedor

El proyecto incluye Maven Wrapper, por lo que no es necesario instalar Maven globalmente.

## Ejecutar localmente

Desde la carpeta raíz del proyecto, ejecutar en Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

La API queda disponible en:

```text
http://localhost:8080
```

H2 se utiliza en memoria. Los datos permanecen mientras la aplicación está ejecutándose y se eliminan al reiniciarla.

## Ejecutar las pruebas

```powershell
.\mvnw.cmd clean test
```

Las pruebas automatizadas cubren:

- Creación exitosa de una tarea y respuesta `201 Created`.
- Rechazo de un título vacío y respuesta `400 Bad Request`.
- Consulta de un ID inexistente y respuesta `404 Not Found`.

## Estructura

```text
src/main/java/com/sv/tareas
├── controller
├── dto
├── entity
├── exception
├── repository
└── service
```

- `controller`: recibe las solicitudes HTTP y define las respuestas.
- `service`: contiene la lógica de negocio.
- `repository`: accede a H2 mediante Spring Data JPA.
- `entity`: representa la tarea persistida.
- `dto`: define los datos recibidos y devueltos por la API.
- `exception`: centraliza las respuestas de error.

## Modelo de tarea

```json
{
  "id": 1,
  "title": "Preparar examen",
  "description": "Completar la API REST",
  "isCompleted": false
}
```

El `id` es generado automáticamente. `title` es obligatorio y no puede estar vacío. `description` es opcional e `isCompleted` indica si la tarea está completada.

## Endpoints

| Método | Ruta | Descripción | Respuestas principales |
|---|---|---|---|
| `GET` | `/api/tasks` | Lista todas las tareas | `200 OK` |
| `GET` | `/api/tasks/{id}` | Obtiene una tarea por ID | `200 OK`, `404 Not Found` |
| `POST` | `/api/tasks` | Crea una tarea | `201 Created`, `400 Bad Request` |
| `PUT` | `/api/tasks/{id}` | Actualiza una tarea existente | `200 OK`, `400 Bad Request`, `404 Not Found` |
| `DELETE` | `/api/tasks/{id}` | Elimina una tarea | `204 No Content`, `404 Not Found` |

### Crear una tarea

```http
POST http://localhost:8080/api/tasks
Content-Type: application/json
```

```json
{
  "title": "Preparar examen",
  "description": "Completar la API REST",
  "isCompleted": false
}
```

Respuesta `201 Created`:

```json
{
  "id": 1,
  "title": "Preparar examen",
  "description": "Completar la API REST",
  "isCompleted": false
}
```

### Listar tareas

```http
GET http://localhost:8080/api/tasks
```

Respuesta `200 OK`:

```json
[
  {
    "id": 1,
    "title": "Preparar examen",
    "description": "Completar la API REST",
    "isCompleted": false
  }
]
```

### Obtener una tarea

```http
GET http://localhost:8080/api/tasks/1
```

Respuesta `200 OK`:

```json
{
  "id": 1,
  "title": "Preparar examen",
  "description": "Completar la API REST",
  "isCompleted": false
}
```

### Actualizar una tarea

```http
PUT http://localhost:8080/api/tasks/1
Content-Type: application/json
```

```json
{
  "title": "Preparar examen actualizado",
  "description": "Repasar los endpoints",
  "isCompleted": true
}
```

Respuesta `200 OK` con la tarea actualizada.

### Eliminar una tarea

```http
DELETE http://localhost:8080/api/tasks/1
```

Respuesta `204 No Content`. La respuesta no contiene cuerpo cuando la eliminación es exitosa.

## Manejo de errores

Si el título está vacío o contiene solamente espacios, se devuelve `400 Bad Request`:

```json
{
  "title": "   ",
  "description": "Tarea inválida",
  "isCompleted": false
}
```

```json
{
  "status": 400,
  "message": "La solicitud contiene datos inválidos",
  "errors": {
    "title": "El titulo es obligatorio"
  },
  "timestamp": "2026-09-06T20:00:00"
}
```

Si el ID no existe, se devuelve `404 Not Found`:

```http
GET http://localhost:8080/api/tasks/999
```

```json
{
  "status": 404,
  "message": "No se encontro la tarea con id: 999",
  "errors": {},
  "timestamp": "2026-09-06T20:00:00"
}
```

## Ejecutar con Docker

Con Docker Desktop iniciado, construir la imagen desde la carpeta raíz:

```powershell
docker build -t tareas-api:1.0.0 .
```

Ejecutar el contenedor:

```powershell
docker run --name tareas-api-container -p 8080:8080 tareas-api:1.0.0
```

Con el contenedor activo, la API queda disponible en `http://localhost:8080`. Para detenerlo:

```powershell
docker stop tareas-api-container
```

Para eliminar el contenedor detenido:

```powershell
docker rm tareas-api-container
```

La imagen puede conservarse para crear otro contenedor posteriormente.
