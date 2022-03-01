/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.service;

import co.com.jh.sgaf.entity.Empleado;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Esta es la interfaz de la capa de servicio de empleado es la que va a interactuar con la capa de datos los 
 * metodos que se definen en esta interfaz son acciones o comportamientos que se requieren hacer en la capa de datos.
 * 
 * @author jsherreram
 * @version 1.0
 */
public interface IEmpleadoService {
    
    /**
     * Definicion de un metodo que encuentra todos los empleados por un parametro de entrada que recibe de tipo
     * Sort ordenamiento.
     * 
     * @param sort Criterio de ordenamiento que recibe para clasificar.
     * @see        Empleado
     * @return     Una lista de tipo Empleado ordenados por un criterio de clasificacion.
     */
    public List<Empleado> findAll(Sort sort);

    /**
     * Definicion de un metodo que encuentra todos los empleados por un parametro de entrada que recibe de tipo
     * Pageable paginacion.
     * 
     * @param pageable Criterio de paginacion por pagina y tamaño de registros, ya contiene el Sort embebido.
     * @see            Empleado
     * @return         Una pagina de tipo Empleado ordenados por un criterio de clasificacion.
     */
    public Page<Empleado> findAll(Pageable pageable);

    /**
     * Definicion de un metodo que encuentra un empleado por un parametro de entrada que recibe de tipo long id.
     * 
     * @param id Identificador unico del empleado.
     * @see      Empleado
     * @return   Un objeto de tipo Empleado encontrado por un parametro de entrada id empleado.
     */
    public Empleado findById(long id);

    /**
     * Definicion de un metodo que guarda un empleado por un parametro de entrada que recibe de tipo Empleado.
     * 
     * @param empleado Objeto de tipo Empleado con todos sus atributos.
     * @see            Empleado
     * @return         Un objeto de tipo Empleado guardado.
     */
    public Empleado save(Empleado empleado);

    /**
     * Definicion de un metodo que elimina un empleado por un parametro de entrada que recibe de tipo long id.
     * 
     * @param id Identificador unico de cada empleado.
     */
    public void deleteById(long id);    

}
