package com.DesignPatterns.controllers.AdminControllers;

import com.DesignPatterns.models.Niveles;
import com.DesignPatterns.services.admin.NivelesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NivelController {

    @PostMapping("admin/niveles/registrar")
    public ResponseEntity<String> registrarNivelController(@RequestBody Niveles nivel) {
        try {
            int resultado = new NivelesService().createNivel(nivel.getNombre(), nivel.getDescripcion());
            if(resultado == 0) {throw new Exception("No se pudo crear el nivel");}
            return new ResponseEntity<>("Nivel registrado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() + " No se pudo registrar el nivel", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("admin/niveles/leer/{idNivel}")
    public ResponseEntity<Niveles> obtenerNivelController(@PathVariable("idNivel") int idNivel) {
        try {
            Niveles nivel = new NivelesService().readNivel(idNivel);
            if (nivel == null) {
                throw new Exception("No se pudo obtener la información del nivel");
            }
            return new ResponseEntity<>(nivel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((Niveles) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("admin/niveles/actualizar")
    public ResponseEntity<String> actualizarNivelController(@RequestBody Niveles nivel) {
        try {
            int resultado = new NivelesService().updateNivel(nivel.getId(), nivel.getNombre(), nivel.getDescripcion());
            if(resultado == 0) {throw new Exception("No se pudo actualizar el nivel");}
            return new ResponseEntity<>("Nivel actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("admin/niveles/eliminar/{idNivel}")
    public ResponseEntity<String> eliminarNivelController(@PathVariable("idNivel") int idNivel) {
        try {
            int resultado = new NivelesService().deleteNivel(idNivel);
            if(resultado == 0) {throw new Exception("No se pudo eliminar el nivel");}
            return new ResponseEntity<>("Nivel eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
