/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.controller;

import co.com.jh.sgaf.entity.Empleado;
import co.com.jh.sgaf.service.IEmpleadoService;
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
@Api(tags = "API Administracion Empleados")
@RestController
@RequestMapping("/empleados")
public class EmpleadoRestController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> findAllEmpleadoes(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {

        Sort sortByApellidoEmpleado = Sort.by("apellidoEmpleado");
        ResponseEntity<List<Empleado>> responseEntity;
        List<Empleado> empleadoes;

        if (page != null && size != null) {
            // Con paginación
            Pageable pageable = PageRequest.of(page, size, sortByApellidoEmpleado);
            empleadoes = empleadoService.findAll(pageable).getContent();
        } else {
            // Sin paginación
            empleadoes = empleadoService.findAll(sortByApellidoEmpleado);
        }
        if (empleadoes.size() > 0) {
            responseEntity = new ResponseEntity<List<Empleado>>(empleadoes, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Empleado>>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Empleado> findEmpleadoById(@PathVariable long id) {

        ResponseEntity<Empleado> responseEntity;
        Empleado empleado = empleadoService.findById(id);

        if (empleado != null) {
            responseEntity = new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Empleado>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> insertEmpleado(@Valid @RequestBody Empleado empleado, BindingResult result) {

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
            Empleado empleadoFromDB = empleadoService.save(empleado);
            if (empleadoFromDB != null) {
                responseAsMap.put("empleado", empleado);
                responseAsMap.put("mensaje", "El empleado con id: " + empleado.getIdEmpleado() + ", se ha creado exitosamente!");
//                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
            } else {
                responseAsMap.put("mensaje", "El empleado no se ha creado exitosamente!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            responseAsMap.put("mensaje", "El empleado no se ha creado exitosamente: " + e.getMostSpecificCause().toString());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> updateEmpleado(@PathVariable long id, @Valid @RequestBody Empleado empleado, BindingResult result) {

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
            empleado.setIdEmpleado(id);
            Empleado empleadoFromDB = empleadoService.save(empleado);
            if (empleadoFromDB != null) {
                responseAsMap.put("empleado", empleado);
                responseAsMap.put("mensaje", "El empleado con id: " + empleado.getIdEmpleado() + ", se ha actualizado exitosamente!");
//                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.ACCEPTED);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
            } else {
                responseAsMap.put("mensaje", "El empleado no se ha actualizado exitosamente!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            responseAsMap.put("mensaje", "El empleado no se ha actualizado exitosamente: " + e.getMostSpecificCause().toString());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmpleadoById(@PathVariable long id) {

        ResponseEntity<Empleado> responseEntity;
        Empleado empleado = empleadoService.findById(id);

        if (empleado != null) {
            empleadoService.deleteById(id);
            responseEntity = new ResponseEntity("El empleado con id: " + empleado.getIdEmpleado() + ", se eliminó exitosamente!", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
