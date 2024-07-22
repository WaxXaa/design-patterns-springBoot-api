package com.DesignPatterns.controllers.AdminControllers;

import com.DesignPatterns.models.Respuestas;
import com.DesignPatterns.services.admin.RespuestasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RespuestaController {

    @PostMapping("admin/respuestas/registrar")
    public ResponseEntity<String> registrarRespuestaController(@RequestBody Respuestas respuesta) {
        try {
            int resultado = new RespuestasService().createRespuesta(respuesta.getRespuesta(), respuesta.getCorrecta(), respuesta.getPregunta());
            if(resultado == 0) {throw new Exception("No se pudo crear la respuesta");}
            return new ResponseEntity<>("Respuesta registrada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() + " No se pudo registrar la respuesta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("admin/respuestas/leer/{idRespuesta}")
    public ResponseEntity<Respuestas> obtenerRespuestaController(@PathVariable("idRespuesta") int idRespuesta) {
        try {
            Respuestas respuesta = new RespuestasService().readRespuesta(idRespuesta);
            if (respuesta == null) {
                throw new Exception("No se pudo obtener la información de la respuesta");
            }
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((Respuestas) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("admin/respuestas/actualizar")
    public ResponseEntity<String> actualizarRespuestaController(@RequestBody Respuestas respuesta) {
        try {
            int resultado = new RespuestasService().updateRespuesta(respuesta.getId(), respuesta.getRespuesta(), respuesta.getCorrecta(), respuesta.getPregunta());
            if(resultado == 0) {throw new Exception("No se pudo actualizar la respuesta");}
            return new ResponseEntity<>("Respuesta actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("admin/respuestas/eliminar/{idRespuesta}")
    public ResponseEntity<String> eliminarRespuestaController(@PathVariable("idRespuesta") int idRespuesta) {
        try {
            int resultado = new RespuestasService().deleteRespuesta(idRespuesta);
            if(resultado == 0) {throw new Exception("No se pudo eliminar la respuesta");}
            return new ResponseEntity<>("Respuesta eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

