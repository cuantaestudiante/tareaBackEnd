package com.Practica1.Estudiante_api.services;

//Importando las rutas  de las packeg
import com.Practica1.Estudiante_api.domain.Student;
import com.Practica1.Estudiante_api.dto.StudentDTO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class StudentService {

    // Lista en memoria que simula una base de datos
    private List<Student> students = new ArrayList<>();

    // AtomicLong para generar un ID único en cada creación de estudiante
    private AtomicLong idCounter = new AtomicLong(1);

    public StudentService() {
        // Creamos tres estudiantes por defecto
        students.add(new Student(idCounter.getAndIncrement(), "Juan Pérez", "juanp@example.com"));
        students.add(new Student(idCounter.getAndIncrement(), "María García", "mariag@example.com"));
        students.add(new Student(idCounter.getAndIncrement(), "Luis Rodríguez", "luisr@example.com"));
    }

    // Método para buscar un estudiante por su ID
    public Optional<Student> getStudentById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    // Método para actualizar un estudiante existente
    public Optional<Student> updateStudent(Long id, StudentDTO studentDTO) {
        // Buscar el estudiante
        Optional<Student> optionalStudent = getStudentById(id);
        if (optionalStudent.isPresent()) {
            // Si se encuentra, se actualizan los datos
            Student student = optionalStudent.get();
            student.setName(studentDTO.getName());
            student.setEmail(studentDTO.getEmail());
            // Se retorna el estudiante actualizado
            return Optional.of(student);
        }
        // Si no se encuentra, se retorna vacío
        return Optional.empty();
    }

    // Método para crear un nuevo estudiante
    public Student createStudent(StudentDTO studentDTO) {
        // Se genera un nuevo ID automáticamente
        Long newId = idCounter.getAndIncrement();
        // Se crea el estudiante a partir de los datos recibidos
        Student student = new Student(newId, studentDTO.getName(), studentDTO.getEmail());
        // Se añade a la lista
        students.add(student);
        return student;
    }

    // Método para eliminar un estudiante por su ID
    public boolean deleteStudent(Long id) {
        Optional<Student> optionalStudent = getStudentById(id);
        if (optionalStudent.isPresent()) {
            students.remove(optionalStudent.get());
            return true;
        }
        return false;
    }
}
