package com.DesignPatterns.controllers.AdminControllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.DesignPatterns.models.Ayuda;
import com.DesignPatterns.services.admin.AyudaService;

@RestController
public class AyudaController {


    @GetMapping("admin/ayuda")
    public ResponseEntity<List<Ayuda>> obtenerAyuda() {
        try {
            List<Ayuda> ayuda = new AyudaService().readAyuda();
            if (ayuda == null) {
                throw new Exception("No se pudo obtener la información de la ayuda");
            }
            return new ResponseEntity<List<Ayuda>>(ayuda, HttpStatus.OK);
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

