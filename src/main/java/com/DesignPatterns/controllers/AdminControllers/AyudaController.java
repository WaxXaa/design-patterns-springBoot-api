package com.DesignPatterns.controllers.AdminControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.DesignPatterns.models.Ayuda;
import com.DesignPatterns.services.admin.AyudaService;

@RestController
public class AyudaController {

    @PostMapping("admin/ayuda/registrar")
    public ResponseEntity<String> registrarAyuda(@RequestBody Ayuda ayuda) {
        try {
            int resultado = new AyudaService().createAyuda(ayuda.getPregunta(), ayuda.getRespuesta());
            if(resultado == 0) { throw new Exception("No se pudo crear la ayuda"); }
            return new ResponseEntity<>("Ayuda registrada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() + " No se pudo registrar la ayuda", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("admin/ayuda/obtener/{id}")
    public ResponseEntity<Ayuda> obtenerAyuda(@PathVariable("id") int id) {
        try {
            Ayuda ayuda = new AyudaService().readAyuda(id);
            if (ayuda == null) {
                throw new Exception("No se pudo obtener la información de la ayuda");
            }
            return new ResponseEntity<>(ayuda, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("admin/ayuda/actualizar")
    public ResponseEntity<String> actualizarAyuda(@RequestBody Ayuda ayuda) {
        try {
            int resultado = new AyudaService().updateAyuda(ayuda.getId(), ayuda.getPregunta(), ayuda.getRespuesta());
            if(resultado == 0) { throw new Exception("No se pudo actualizar la ayuda"); }
            return new ResponseEntity<>("Ayuda actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("admin/ayuda/eliminar/{id}")
    public ResponseEntity<String> eliminarAyuda(@PathVariable("id") int id) {
        try {
            int resultado = new AyudaService().deleteAyuda(id);
            if(resultado == 0) { throw new Exception("No se pudo eliminar la ayuda"); }
            return new ResponseEntity<>("Ayuda eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

