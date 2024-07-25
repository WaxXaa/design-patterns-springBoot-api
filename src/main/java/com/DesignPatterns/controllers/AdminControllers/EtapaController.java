package com.DesignPatterns.controllers.AdminControllers;

import com.DesignPatterns.models.Ayuda;
import com.DesignPatterns.services.admin.AyudaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.DesignPatterns.exceptions.DataBaseException;
import com.DesignPatterns.models.Etapas;
import com.DesignPatterns.services.admin.EtapasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EtapaController {


    @PostMapping("admin/etapas/registrar")
    public ResponseEntity<String> registrarEtapasController(@RequestBody String nombre, String descripcion) {
        try {
            int resultado = new EtapasService().createEtapa(nombre, descripcion);
            if(resultado == 0) {throw new Exception("No se pudo crear la etapa");}
            return new ResponseEntity<String>("Etapa registrada exitosamente", HttpStatus.CREATED);// http status 201
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage()+ "No se pudo registró la Etapa",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("admin/etapas/leer/{idEtapa}")
    public ResponseEntity<Etapas> obtenerEtapasController(@PathVariable("idUEtapa") int idEtapa) {
        try {
            Etapas etapa = new EtapasService().readEtapa(idEtapa);
            if (etapa == null) {
                throw new Exception("No se pudo obtener la información del usuario");
            }
            return new ResponseEntity<>(etapa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((Etapas) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("admin/etapas/actualizar/}")
    public ResponseEntity<String> actualizarEtapasController(@RequestBody Etapas etapa) {
        try {
            int resultado = new EtapasService().updateEtapa(etapa.getId(), etapa.getNombre(),etapa.getDescripcion(), etapa.getImage_url());
            if(resultado == 0) {throw new Exception("No se pudo actualizar la Etapa");}
            return new ResponseEntity<String>("Etapa actualizada exitosamente", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("admin/etapas/eliminar/{idEtapa}")
    public ResponseEntity<String> eliminarEtapasController(@PathVariable("idEtapa") int idEtapa) {
        try {
            int resultado = new EtapasService().deleteEtapa(idEtapa);
            if(resultado == 0) {throw new Exception("No se pudo eliminar la etapa");}
            return new ResponseEntity<String>("Etapa eliminada exitosamente", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
