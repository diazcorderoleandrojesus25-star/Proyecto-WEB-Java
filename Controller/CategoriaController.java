package com.Jobxpress.Jobxpress.Controller;

import com.Jobxpress.Jobxpress.Entity.Categoria;
import com.Jobxpress.Jobxpress.Repository.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // LISTAR
    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCategoria(@PathVariable Integer id) {

        return categoriaRepository.findById(id)
                .<ResponseEntity<?>>map(cat -> ResponseEntity.ok(cat))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Categoría no encontrada"));
    }

    // CREAR
    @PostMapping
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nueva = categoriaRepository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(
            @PathVariable Integer id,
            @RequestBody Categoria datosCategoria) {

        return categoriaRepository.findById(id)
                .<ResponseEntity<?>>map(cat -> {
                    cat.setNombre(datosCategoria.getNombre());
                    return ResponseEntity.ok(categoriaRepository.save(cat));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Categoría no encontrada"));
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Integer id) {

        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoría no encontrada");
        }

        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

