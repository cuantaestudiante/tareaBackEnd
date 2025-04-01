package com.Practica1.Estudiante_api.dto;

public class StudentDTO {

    // Estos son los datos que vamos a recibir desde el cliente
    private String name;
    private String email;

    // Constructor vacío: importante para la deserialización del JSON
    public StudentDTO() {
    }

    // Constructor con parámetros
    public StudentDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters y setters para acceder a los atributos
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
