package com.sv.tareas.exception;

/*Clase Excepcion, si el cliente un Create, Update o Delete de
un registro que no existe */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
