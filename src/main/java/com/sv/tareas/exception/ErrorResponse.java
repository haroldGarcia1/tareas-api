package com.sv.tareas.exception;

import java.time.LocalDateTime;
import java.util.Map;

//Formato estandar de errores API
public class ErrorResponse {
    // <editor-fold defaultstate="collapsed" desc="Variables">
    private int status;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Getter-Setter">

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    // </editor-fold>
}
