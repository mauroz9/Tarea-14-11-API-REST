package com.salesianostriana.dam.tarea14.controller;

import com.salesianostriana.dam.tarea14.model.Monument;
import com.salesianostriana.dam.tarea14.repository.MonumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monument")
@RequiredArgsConstructor
public class MonumentController {

    private final MonumentRepository monumentRepository;

    @GetMapping
    public ResponseEntity<List<Monument>> getAllMonuments(){
        List<Monument> monuments = monumentRepository.findAll();

        if(monuments.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monuments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monument> getMonumentById(@PathVariable Long id){
        Optional<Monument> monumentOpt =  monumentRepository.findById(id);

        if(monumentOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(monumentOpt.get());

    }

    @PostMapping
    public ResponseEntity<String> createMonument(Monument monument) {
        try {
            Monument savedMonument = monumentRepository.save(crearMonumento);
            return ResponseEntity.status(HttpStatus.CREATED).body("Monumento creado: " + savedMonument);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el monumento");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMonument(
            @PathVariable Long id, Monument monument) {

        if (!monumentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el monumento");
        }

        try {
            monument.setId(id);
            Monument updatedMonument = monumentRepository.save(monument);
            return ResponseEntity.status(HttpStatus.CREATED).body("Monumento actualizado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el monumento");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMonument(@PathVariable Long id) {
        if (!monumentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Monumento no encontrado");
        }
        monumentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Monumento eliminado");
    }








}
