package com.sv.tareas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

//Clase DTO Respuesta
public class TaskResponse {
    // <editor-fold defaultstate="collapsed" desc="Variables">
    private Long id;

    private String title;

    private String description;

    @JsonProperty("isCompleted")
    private boolean isCompleted;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public TaskResponse() {
    }

    public TaskResponse(Long id, String title, String description, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getter-Setter">

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("isCompleted")
    public boolean isCompleted() {
        return isCompleted;
    }

    // </editor-fold>
}
