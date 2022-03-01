/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.controller;

import co.com.jh.sgaf.entity.ActivoFijo;
import co.com.jh.sgaf.exception.BusinessRuleException;
import co.com.jh.sgaf.service.IActivoFijoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
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
 * Esta es la clase controladora de la capa de presentacion de activo fijo es la que recibe las peticiones del 
 * cliente y envia las repuestas obtenidas en la capa de servicio.
 * 
 * @author jsherreram
 * @version 1.0
 */
@Api(tags = "API Administracion Activos Fijos")
@RestController
@RequestMapping("/activosFijos")
public class ActivoFijoRestController {

    @Autowired
    private IActivoFijoService activoFijoService;
    
    /**
     * Metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo Page paginacion
     * y otro de tipo Sort ordenamiento no son obligatorios, en caso de no ingresarlos el retorna el listado de
     * activos fijos ordenados por un criterio de clasificacion definido en este metodo.
     * 
     * @param page Criterio de paginacion por pagina, ya contiene el Sort embebido.
     * @param size Criterio de tamaño de registros para ver por pagina.
     * @see        ActivoFijo
     * @return     Una lista de tipo ActivoFijo paginado y ordenado por un criterio de clasificacion.
     */
    @ApiOperation(value = "Encontrar todos los activos fijos incluidos en el Response ordenados por el atributo de nombreActivoFijo y paginados de 5 en 5", notes = "Devuelve 204 si no se encuentran registros")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La solicitud tuvo éxito. El recurso se ha obtenido y transmitido en el cuerpo del mensaje."),
        @ApiResponse(code = 400, message = "Solicitud incorrecta. El servidor no puede o no procesará la solicitud debido a algo que se percibe como un error del cliente (ej: sintaxis de solicitud mal formada, marco de mensaje de solicitud no válido o enrutamiento de solicitud engañoso"),
        @ApiResponse(code = 404, message = "No encontrada. El servidor no puede encontrar el recurso solicitado. también puede significar que el endpoint es válido pero el recurso en sí no existe."),
        @ApiResponse(code = 500, message = "Error Interno del Servidor. Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.")})
    @GetMapping
    public ResponseEntity<List<ActivoFijo>> findAllActivosFijos(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {

        Sort sortByNombreActivoFijo = Sort.by("nombreActivoFijo");
        ResponseEntity<List<ActivoFijo>> responseEntity;
        List<ActivoFijo> activosFijos;

        if (page != null && size != null) {
            // Con paginación
            Pageable pageable = PageRequest.of(page, size, sortByNombreActivoFijo);
            activosFijos = activoFijoService.findAll(pageable).getContent();
        } else {
            // Sin paginación
            activosFijos = activoFijoService.findAll(sortByNombreActivoFijo);
        }
        if (activosFijos.size() > 0) {
            responseEntity = new ResponseEntity<List<ActivoFijo>>(activosFijos, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<ActivoFijo>>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    /**
     * Metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo String tipo de
     * activo fijo.
     * 
     * @param tipo Grupo dentro del cual se categoriza el activo fijo.
     * @see        ActivoFijo
     * @return     Una lista de tipo ActivoFijo encontrado por un parametro de entrada tipo activo fijo.
     */
    @ApiOperation(value = "Encontrar todos los activos fijos recibiendo como parámetro el tipo incluido en el Request", notes = "Devuelve 204 si no se encuentran registros")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La solicitud tuvo éxito. El recurso se ha obtenido y transmitido en el cuerpo del mensaje."),
        @ApiResponse(code = 400, message = "Solicitud incorrecta. El servidor no puede o no procesará la solicitud debido a algo que se percibe como un error del cliente (ej: sintaxis de solicitud mal formada, marco de mensaje de solicitud no válido o enrutamiento de solicitud engañoso"),
        @ApiResponse(code = 404, message = "No encontrada. El servidor no puede encontrar el recurso solicitado. también puede significar que el endpoint es válido pero el recurso en sí no existe."),
        @ApiResponse(code = 500, message = "Error Interno del Servidor. Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.")})
    @GetMapping(value = "/tipo")
    public ResponseEntity<List<ActivoFijo>> findAllActivosFijosByTipo(@RequestParam String tipo) throws BusinessRuleException {

        ResponseEntity<List<ActivoFijo>> responseEntity;
        
        if (tipo == null || tipo.isEmpty()) {
            BusinessRuleException businessRuleException = new BusinessRuleException("Error-1025", "Error de validación, atributo tipo activo fijo no existe", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        } else {
            List<ActivoFijo> activosFijos = activoFijoService.findAllByTipo(tipo);

            if (activosFijos.size() > 0) {
                responseEntity = new ResponseEntity<List<ActivoFijo>>(activosFijos, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<List<ActivoFijo>>(HttpStatus.NO_CONTENT);
            }
        }
        return responseEntity;
    }

    /**
     * Metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo Date fechaCompra
     * de activo fijo.
     * 
     * @param fechaCompra Fecha en la cual se realizo la compra del activo fijo.
     * @see               ActivoFijo
     * @return            Una lista de tipo ActivoFijo encontrado por un parametro de entrada fecha compra activo fijo.
     */
    @ApiOperation(value = "Encontrar todos los activos fijos recibiendo como parámetro la fechaCompra incluido en el Request", notes = "Devuelve 204 si no se encuentran registros")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La solicitud tuvo éxito. El recurso se ha obtenido y transmitido en el cuerpo del mensaje."),
        @ApiResponse(code = 400, message = "Solicitud incorrecta. El servidor no puede o no procesará la solicitud debido a algo que se percibe como un error del cliente (ej: sintaxis de solicitud mal formada, marco de mensaje de solicitud no válido o enrutamiento de solicitud engañoso"),
        @ApiResponse(code = 404, message = "No encontrada. El servidor no puede encontrar el recurso solicitado. también puede significar que el endpoint es válido pero el recurso en sí no existe."),
        @ApiResponse(code = 500, message = "Error Interno del Servidor. Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.")})
    @GetMapping(value = "/fechaCompra")
    public ResponseEntity<List<ActivoFijo>> findAllActivosFijosByFechaCompra(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaCompra) throws BusinessRuleException {

        ResponseEntity<List<ActivoFijo>> responseEntity;
        
        if (fechaCompra == null) {
            BusinessRuleException businessRuleException = new BusinessRuleException("Error-1025", "Error de validación, atributo fecha compra activo fijo no existe", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        } else {
            List<ActivoFijo> activosFijos = activoFijoService.findAllByFechaCompra(fechaCompra);

            if (activosFijos.size() > 0) {
                responseEntity = new ResponseEntity<List<ActivoFijo>>(activosFijos, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<List<ActivoFijo>>(HttpStatus.NO_CONTENT);
            }
        }
        return responseEntity;
    }

    /**
     * Metodo que encuentra un activo fijo por un parametro de entrada que recibe de tipo String serial de activo
     * fijo.
     * 
     * @param serial Referencia unica de cada activo fijo.
     * @see          ActivoFijo
     * @return       Un objeto de tipo ActivoFijo encontrado por un paramero de entrada serial activo fijo.
     */
    @ApiOperation(value = "Encontrar un activo fijo recibiendo como parámetro el serial incluido en el Request", notes = "Devuelve 204 si no se encuentran registros")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La solicitud tuvo éxito. El recurso se ha obtenido y transmitido en el cuerpo del mensaje."),
        @ApiResponse(code = 400, message = "Solicitud incorrecta. El servidor no puede o no procesará la solicitud debido a algo que se percibe como un error del cliente (ej: sintaxis de solicitud mal formada, marco de mensaje de solicitud no válido o enrutamiento de solicitud engañoso"),
        @ApiResponse(code = 404, message = "No encontrada. El servidor no puede encontrar el recurso solicitado. también puede significar que el endpoint es válido pero el recurso en sí no existe."),
        @ApiResponse(code = 500, message = "Error Interno del Servidor. Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.")})
    @GetMapping(value = "/serial")
    public ResponseEntity<ActivoFijo> findActivoFijoBySerial(@RequestParam String serial) throws BusinessRuleException {

        ResponseEntity<ActivoFijo> responseEntity;
        
        if (serial == null || serial.isEmpty()) {
            BusinessRuleException businessRuleException = new BusinessRuleException("Error-1025", "Error de validación, atributo serial activo fijo no existe", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        } else {
            ActivoFijo activoFijo = activoFijoService.findBySerial(serial);

            if (activoFijo != null) {
                responseEntity = new ResponseEntity<ActivoFijo>(activoFijo, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<ActivoFijo>(HttpStatus.NOT_FOUND);
            }
        }
        return responseEntity;
    }

    /**
     * Metodo que encuentra un activo fijo por un parametro de entrada que recibe de tipo long id de activo fijo.
     * 
     * @param id Identificador unico del activo fijo.
     * @see      ActivoFijo
     * @return   Un objeto de tipo ActivoFijo encontrado por un parametro de entrada id activo fijo.
     */
    @ApiOperation(value = "Encontrar un activo fijo recibiendo como parámetro el idActivoFijo incluido en el Request", notes = "Devuelve 204 si no se encuentran registros")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La solicitud tuvo éxito. El recurso se ha obtenido y transmitido en el cuerpo del mensaje."),
        @ApiResponse(code = 400, message = "Solicitud incorrecta. El servidor no puede o no procesará la solicitud debido a algo que se percibe como un error del cliente (ej: sintaxis de solicitud mal formada, marco de mensaje de solicitud no válido o enrutamiento de solicitud engañoso"),
        @ApiResponse(code = 404, message = "No encontrada. El servidor no puede encontrar el recurso solicitado. también puede significar que el endpoint es válido pero el recurso en sí no existe."),
        @ApiResponse(code = 500, message = "Error Interno del Servidor. Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<ActivoFijo> findActivoFijoById(@PathVariable long id) {

        ResponseEntity<ActivoFijo> responseEntity;
        ActivoFijo activoFijo = activoFijoService.findById(id);

        if (activoFijo != null) {
            responseEntity = new ResponseEntity<ActivoFijo>(activoFijo, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<ActivoFijo>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * Metodo que inserta un activo fijo por un parametro de entrada que recibe de tipo ActivoFijo, valida todos los
     * atributos ingresados en el cuerpo del objeto, en caso de presentarse una inconsistencia devuelve un arreglo
     * JSON con todos los mensajes de error indicados en cada uno. Si el identificador no se encuentra en la base de
     * datos hara un insert a traves de un objeto inyectado de la capa de servicio y este se comunicara con la capa
     * de datos para persistir en la base de datos.
     * 
     * @param activoFijo Objeto de tipo ActivoFijo con todos sus atributos.
     * @param result     Objeto de tipo BindingResult para obtener todos los errores validados en el cuerpo del objeto ActivoFijo.
     * @see              Map
     * @return           Un objeto de tipo Map ActivoFijo insertado con una llave generada autoincrementable.
     */
    @ApiOperation(value = "Insertar un activo fijo recibiendo como parámetro el body incluido en el Request")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La solicitud tuvo éxito. El recurso que describe el resultado de la acción se transmite en el cuerpo del mensaje."),
        @ApiResponse(code = 400, message = "Solicitud incorrecta. El servidor no puede o no procesará la solicitud debido a algo que se percibe como un error del cliente (ej: sintaxis de solicitud mal formada, marco de mensaje de solicitud no válido o enrutamiento de solicitud engañoso"),
        @ApiResponse(code = 404, message = "No encontrada. El servidor no puede encontrar el recurso solicitado. también puede significar que el endpoint es válido pero el recurso en sí no existe."),
        @ApiResponse(code = 500, message = "Error Interno del Servidor. Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.")})
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertActivoFijo(@Valid @RequestBody ActivoFijo activoFijo, BindingResult result) {

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
            ActivoFijo activoFijoFromDB = activoFijoService.save(activoFijo);
            if (activoFijoFromDB != null) {
                responseAsMap.put("activoFijo", activoFijo);
                responseAsMap.put("mensaje", "El activo fijo con id: " + activoFijo.getIdActivoFijo() + ", se ha creado exitosamente!");
//                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
            } else {
                responseAsMap.put("mensaje", "El activo fijo no se ha creado exitosamente!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            responseAsMap.put("mensaje", "El activo fijo no se ha creado exitosamente: " + e.getMostSpecificCause().toString());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * Metodo que actualiza un activo fijo por un parametro de entrada que recibe de tipo long id, valida todos los
     * atributos ingresados en el cuerpo del objeto, en caso de presentarse una inconsistencia devuelve un arreglo
     * JSON con todos los mensajes de error indicados en cada uno. Si el identificador se encuentra en la base de
     * datos hara un update a traves de un objeto inyectado de la capa de servicio y este se comunicara con la capa
     * de datos para hacer un merge en la base de datos.
     * 
     * @param id         Identificador unico del activo fijo.
     * @param activoFijo Objeto de tipo ActivoFijo con todos sus atributos.
     * @param result     Objeto de tipo BindingResult para obtener todos los errores validados en el cuerpo del objeto ActivoFijo.
     * @see              Map
     * @return           Un objeto de tipo Map ActivoFijo actualizado.
     */
    @ApiOperation(value = "Actualizar un activo fijo recibiendo como parámetro el idActivoFijo incluido en el Request ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La solicitud tuvo éxito. El recurso que describe el resultado de la acción se transmite en el cuerpo del mensaje."),
        @ApiResponse(code = 400, message = "Solicitud incorrecta. El servidor no puede o no procesará la solicitud debido a algo que se percibe como un error del cliente (ej: sintaxis de solicitud mal formada, marco de mensaje de solicitud no válido o enrutamiento de solicitud engañoso"),
        @ApiResponse(code = 404, message = "No encontrada. El servidor no puede encontrar el recurso solicitado. también puede significar que el endpoint es válido pero el recurso en sí no existe."),
        @ApiResponse(code = 500, message = "Error Interno del Servidor. Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.")})
    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> updateActivoFijo(@PathVariable long id, @Valid @RequestBody ActivoFijo activoFijo, BindingResult result) {

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
            activoFijo.setIdActivoFijo(id);
            ActivoFijo activoFijoFromDB = activoFijoService.save(activoFijo);
            if (activoFijoFromDB != null) {
                responseAsMap.put("activoFijo", activoFijo);
                responseAsMap.put("mensaje", "El activo fijo con id: " + activoFijo.getIdActivoFijo() + ", se ha actualizado exitosamente!");
//                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.ACCEPTED);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
            } else {
                responseAsMap.put("mensaje", "El activo fijo no se ha actualizado exitosamente!");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            responseAsMap.put("mensaje", "El activo fijo no se ha actualizado exitosamente: " + e.getMostSpecificCause().toString());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * Metodo que elimina un activo fijo por un parametro de entrada que recibe de tipo long id de activo fijo.
     * 
     * @param id Identificador unico de cada activo fijo.
     * @return   Devuelve un ResponseEntity en caso de ser exitoso genera un mensaje de que fué elimnado exitosamente.
     */
    @ApiOperation(value = "Eliminar un activo fijo recibiendo como parámetro el idActivoFijo incluido en el Request", notes = "Devuelve 204 si no se encuentran registros")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La solicitud tuvo éxito. El recurso se ha obtenido y transmitido en el cuerpo del mensaje."),
        @ApiResponse(code = 400, message = "Solicitud incorrecta. El servidor no puede o no procesará la solicitud debido a algo que se percibe como un error del cliente (ej: sintaxis de solicitud mal formada, marco de mensaje de solicitud no válido o enrutamiento de solicitud engañoso"),
        @ApiResponse(code = 404, message = "No encontrada. El servidor no puede encontrar el recurso solicitado. también puede significar que el endpoint es válido pero el recurso en sí no existe."),
        @ApiResponse(code = 500, message = "Error Interno del Servidor. Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteActivoFijoById(@PathVariable long id) {

        ResponseEntity<ActivoFijo> responseEntity;
        ActivoFijo activoFijo = activoFijoService.findById(id);

        if (activoFijo != null) {
            activoFijoService.deleteById(id);
            responseEntity = new ResponseEntity("El activo fijo con id: " + activoFijo.getIdActivoFijo() + ", se eliminó exitosamente!", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
