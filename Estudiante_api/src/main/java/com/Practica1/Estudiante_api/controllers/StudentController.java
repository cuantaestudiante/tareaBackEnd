package com.Practica1.Estudiante_api.controllers;

import com.Practica1.Estudiante_api.domain.Student;
import com.Practica1.Estudiante_api.dto.StudentDTO;
import com.Practica1.Estudiante_api.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students") // Ruta base para todos los endpoints de estudiantes
public class StudentController {

    // Inyectamos el servicio para usar su lógica de negocio
    @Autowired
    private StudentService studentService;

    // Endpoint para obtener un estudiante por su ID (usamos GET)
    @GetMapping("/hola")
    public String helloWorld(){
        System.out.println("Solicitud Ejecutada!");
        return "Hello, World!";
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // a) Endpoint para actualizar un estudiante existente (usamos PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO)
                .map(updatedStudent -> new ResponseEntity<>(updatedStudent, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // b) Endpoint para crear un nuevo estudiante (usamos POST)
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // c) Endpoint para eliminar un estudiante por su ID (usamos DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
