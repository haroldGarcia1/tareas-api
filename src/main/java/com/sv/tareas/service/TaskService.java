package com.sv.tareas.service;

import com.sv.tareas.dto.TaskRequest;
import com.sv.tareas.dto.TaskResponse;
import com.sv.tareas.entity.Task;
import com.sv.tareas.exception.ResourceNotFoundException;
import com.sv.tareas.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService { //Servicio de la entidad Task
    private final TaskRepository taskRepository; //Variable JPA(Inyeccion)

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Obtener todos los registros de Tareas
    public List<TaskResponse> findAll(){
        return taskRepository.findAll().stream().map(this::toResponse).toList();
    }

    //Busqueda por id de la tarea
    public TaskResponse findById(Long id){
        Task tarea = findTaskById(id);
        return toResponse(tarea);
    }

    // <editor-fold defaultstate="collapsed" desc="CRUD">
    //Creacion de registro
    public TaskResponse create(TaskRequest request){
        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                request.isCompleted()
        );
        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    //Actualizacion de registro
    public TaskResponse update(Long id, TaskRequest request){
        Task task = findTaskById(id); //busqueda de registro

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());

        Task updatedTask = taskRepository.save(task);

        return toResponse(updatedTask);
    }

    //Eliminacion de registro
    public void delete(Long id){
        Task task = findTaskById(id);
        taskRepository.delete(task);
    }
    // </editor-fold>

    //Metodo de busqueda de id de la tarea, sino lanzara la excepcion personalizada
    private Task findTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro la tarea con id: " + id));
    }

    //Metodo de respuesta luego del evento ejecutado
    private TaskResponse toResponse(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }
}
