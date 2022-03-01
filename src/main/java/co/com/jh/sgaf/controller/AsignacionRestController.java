/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.controller;

import co.com.jh.sgaf.entity.Asignacion;
import co.com.jh.sgaf.service.IAsignacionService;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jsherreram
 */
@Api(tags = "API Administracion Asignaciones")
@RestController
@RequestMapping("/asignaciones")
public class AsignacionRestController {

    @Autowired
    private IAsignacionService asignacionService;

    @GetMapping
    public ResponseEntity<List<Asignacion>> findAllAsignaciones(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {

        Sort sortByArea = Sort.by("area");
        ResponseEntity<List<Asignacion>> responseEntity;
        List<Asignacion> asignaciones;

        if (page != null && size != null) {
            // Con paginación
            Pageable pageable = PageRequest.of(page, size, sortByArea);
            asignaciones = asignacionService.findAll(pageable).getContent();
        } else {
            // Sin paginación
            asignaciones = asignacionService.findAll(sortByArea);
        }
        if (asignaciones.size() > 0) {
            responseEntity = new ResponseEntity<List<Asignacion>>(asignaciones, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Asignacion>>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Asignacion> findAsignacionById(@PathVariable long id) {

        ResponseEntity<Asignacion> responseEntity;
        Asignacion asignacion = asignacionService.findById(id);

        if (asignacion != null) {
            responseEntity = new ResponseEntity<Asignacion>(asignacion, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Asignacion>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> insertAsignacion(@Valid @RequestBody Asignacion asignacion, BindingResult result) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity;
        List<String> errores;

        if (result.hasErrors()) {
            errores = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errors", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        try {
            Asignacion asignacionFromDB = asignacionService.save(asignacion);
            if (asignacionFromDB != null) {
                responseAsMap.put("asignacion", asignacion);
                responseAsMap.put("mensaje", "La asignación con id: " + asignacion.getIdAsignacion() + ", se ha creado exitosamente!");
//                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
            } else {
                responseAsMap.put("mensaje", "La asignación no se ha creado exitosamente!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            responseAsMap.put("mensaje", "La asignación no se ha creado exitosamente: " + e.getMostSpecificCause().toString());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> updateAsignacion(@PathVariable long id, @Valid @RequestBody Asignacion asignacion, BindingResult result) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity;
        List<String> errores;

        if (result.hasErrors()) {
            errores = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errors", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        try {
            asignacion.setIdAsignacion(id);
            Asignacion asignacionFromDB = asignacionService.save(asignacion);
            if (asignacionFromDB != null) {
                responseAsMap.put("asignacion", asignacion);
                responseAsMap.put("mensaje", "La asignación con id: " + asignacion.getIdAsignacion() + ", se ha actualizado exitosamente!");
//                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.ACCEPTED);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
            } else {
                responseAsMap.put("mensaje", "La asignación no se ha actualizado exitosamente!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            responseAsMap.put("mensaje", "La asignación no se ha actualizado exitosamente: " + e.getMostSpecificCause().toString());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAsignacionById(@PathVariable long id) {

        ResponseEntity<Asignacion> responseEntity;
        Asignacion asignacion = asignacionService.findById(id);

        if (asignacion != null) {
            asignacionService.deleteById(id);
            responseEntity = new ResponseEntity("La asignación con id: " + asignacion.getIdAsignacion() + ", se eliminó exitosamente!", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
