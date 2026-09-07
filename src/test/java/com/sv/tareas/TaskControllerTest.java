package com.sv.tareas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createTask() throws Exception { //Metodo de prueba de creacion
        String requestBody = """
                {
                    "title": "Preparar examen",
                    "description": "Completar la API REST",
                    "isCompleted": false
                }
                """;
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Preparar examen"))
                .andExpect(jsonPath("$.isCompleted").value(false));
    }

    @Test
    void rejectTaskWithoutTitle() throws Exception { //Metodo de prueba de creacion fallida
        String requestBody = """
                {
                    "title": "   ",
                    "description": "Tarea inválida",
                    "isCompleted": false
                }
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors.title")
                        .value("El titulo es obligatorio"));
    }

    @Test
    void taskNotFound() throws Exception { //Metodo de prueba, busqueda por ID
        mockMvc.perform(get("/api/tasks/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message")
                        .value("No se encontro la tarea con id: 999"));
    }
}
