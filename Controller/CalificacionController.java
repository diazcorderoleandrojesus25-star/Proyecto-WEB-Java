package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Calificacion;
import com.Jobxpress.Jobxpress.Repository.CalificacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
@CrossOrigin("*")
public class CalificacionController {

    private final CalificacionRepository calificacionRepository;

    public CalificacionController(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    // Crear
    @PostMapping
    public ResponseEntity<Calificacion> crear(@RequestBody Calificacion calificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                calificacionRepository.save(calificacion)
        );
    }

    // Listar todos
    @GetMapping
    public List<Calificacion> listar() {
        return calificacionRepository.findAll();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {

        return calificacionRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Calificación no encontrada"));
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                        @RequestBody Calificacion datos) {

        return calificacionRepository.findById(id)
                .<ResponseEntity<?>>map(existing -> {
                    existing.setPuntuacion(datos.getPuntuacion());
                    existing.setComentario(datos.getComentario());
                    existing.setFechaCalificacion(datos.getFechaCalificacion());
                    existing.setCliente(datos.getCliente());
                    existing.setPrestador(datos.getPrestador());
                    existing.setContratacion(datos.getContratacion());
                    return ResponseEntity.ok(calificacionRepository.save(existing));
                })
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Calificación no encontrada"));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {

        return calificacionRepository.findById(id)
                .<ResponseEntity<?>>map(c -> {
                    calificacionRepository.delete(c);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Calificación no encontrada"));
    }
}
