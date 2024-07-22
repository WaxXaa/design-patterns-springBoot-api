package com.DesignPatterns.controllers.AdminControllers;

import com.DesignPatterns.models.PreguntasDTO;
import com.DesignPatterns.services.admin.PreguntasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PreguntaController {

    @PostMapping("admin/preguntas/registrar")
    public ResponseEntity<String> registrarPreguntaController(@RequestBody PreguntasDTO pregunta) {
        try {
            int resultado = new PreguntasService().createPregunta(pregunta.getPregunta(), pregunta.getTipo(), pregunta.getFoto(), pregunta.getNivel());
            if(resultado == 0) {throw new Exception("No se pudo crear la pregunta");}
            return new ResponseEntity<>("Pregunta registrada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() + " No se pudo registrar la pregunta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("admin/preguntas/leer/{idPregunta}")
    public ResponseEntity<PreguntasDTO> obtenerPreguntaController(@PathVariable("idPregunta") int idPregunta) {
        try {
            PreguntasDTO pregunta = new PreguntasService().readPregunta(idPregunta);
            if (pregunta == null) {
                throw new Exception("No se pudo obtener la información de la pregunta");
            }
            return new ResponseEntity<>(pregunta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((PreguntasDTO) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("admin/preguntas/actualizar")
    public ResponseEntity<String> actualizarPreguntaController(@RequestBody PreguntasDTO pregunta) {
        try {
            int resultado = new PreguntasService().updatePregunta(pregunta.getId(), pregunta.getPregunta(), pregunta.getTipo(), pregunta.getFoto(), pregunta.getNivel());
            if(resultado == 0) {throw new Exception("No se pudo actualizar la pregunta");}
            return new ResponseEntity<>("Pregunta actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("admin/preguntas/eliminar/{idPregunta}")
    public ResponseEntity<String> eliminarPreguntaController(@PathVariable("idPregunta") int idPregunta) {
        try {
            int resultado = new PreguntasService().deletePregunta(idPregunta);
            if(resultado == 0) {throw new Exception("No se pudo eliminar la pregunta");}
            return new ResponseEntity<>("Pregunta eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
