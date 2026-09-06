package com.sv.tareas.controller;

import com.sv.tareas.dto.TaskRequest;
import com.sv.tareas.dto.TaskResponse;
import com.sv.tareas.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Endpoints REST
@RequestMapping("/api/tasks") //Ruta
public class TaskController {
    private final TaskService taskService;

    public TaskController (TaskService taskService){
        this.taskService = taskService;
    }

    //Lista todas las tareas
    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll(){
        return ResponseEntity.ok(taskService.findAll());
    }

    //va obtener la tarea especifica con el id
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.findById(id));
    }

    //Registra una tarea
    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request){
        TaskResponse response = taskService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Actualiza una tarea
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @Valid @RequestBody TaskRequest request){
        return ResponseEntity.ok(taskService.update(id,request));
    }

    //Elimina una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
