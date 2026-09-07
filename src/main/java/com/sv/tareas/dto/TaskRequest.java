package com.sv.tareas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

//CLase DTO de Peticion
public class TaskRequest {
    // <editor-fold defaultstate="collapsed" desc="Variables">
    @NotBlank(message = "El titulo es obligatorio")
    private String title;

    private String description;

    @JsonProperty("isCompleted")
    private boolean isCompleted;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public TaskRequest() {
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getter-Setter">
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("isCompleted")
    public boolean isCompleted() {
        return isCompleted;
    }

    @JsonProperty("isCompleted")
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    // </editor-fold>
}
