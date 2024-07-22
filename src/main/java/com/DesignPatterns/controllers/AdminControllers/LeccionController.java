package com.DesignPatterns.controllers.AdminControllers;

import com.DesignPatterns.models.Lecciones;
import com.DesignPatterns.services.admin.LeccionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeccionController {

    @PostMapping("admin/lecciones/registrar")
    public ResponseEntity<String> registrarLeccionController(@RequestBody Lecciones leccion) {
        try {
            int resultado = new LeccionesService().createLeccion(leccion.getNombre(), leccion.getDescripcion(), leccion.getVideo(), leccion.getNivel());
            if(resultado == 0) {throw new Exception("No se pudo crear la lección");}
            return new ResponseEntity<>("Lección registrada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() + " No se pudo registrar la lección", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("admin/lecciones/leer/{idLeccion}")
    public ResponseEntity<Lecciones> obtenerLeccionController(@PathVariable("idLeccion") int idLeccion) {
        try {
            Lecciones leccion = new LeccionesService().readLeccion(idLeccion);
            if (leccion == null) {
                throw new Exception("No se pudo obtener la información de la lección");
            }
            return new ResponseEntity<>(leccion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((Lecciones) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("admin/lecciones/actualizar")
    public ResponseEntity<String> actualizarLeccionController(@RequestBody Lecciones leccion) {
        try {
            int resultado = new LeccionesService().updateLeccion(leccion.getId(), leccion.getNombre(), leccion.getDescripcion(), leccion.getVideo(), leccion.getNivel());
            if(resultado == 0) {throw new Exception("No se pudo actualizar la lección");}
            return new ResponseEntity<>("Lección actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("admin/lecciones/eliminar/{idLeccion}")
    public ResponseEntity<String> eliminarLeccionController(@PathVariable("idLeccion") int idLeccion) {
        try {
            int resultado = new LeccionesService().deleteLeccion(idLeccion);
            if(resultado == 0) {throw new Exception("No se pudo eliminar la lección");}
            return new ResponseEntity<>("Lección eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
