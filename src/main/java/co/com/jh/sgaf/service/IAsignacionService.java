/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.service;

import co.com.jh.sgaf.entity.Asignacion;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Esta es la interfaz de la capa de servicio de asignacion es la que va a interactuar con la capa de datos los 
 * metodos que se definen en esta interfaz son acciones o comportamientos que se requieren hacer en la capa de datos.
 * 
 * @author jsherreram
 * @version 1.0
 */
public interface IAsignacionService {
    
    /**
     * Definicion de un metodo que encuentra todos las asignaciones por un parametro de entrada que recibe de tipo
     * Sort ordenamiento.
     * 
     * @param sort Criterio de ordenamiento que recibe para clasificar.
     * @see        Asignacion
     * @return     Una lista de tipo Asignacion ordenados por un criterio de clasificacion.
     */
    public List<Asignacion> findAll(Sort sort);

    /**
     * Definicion de un metodo que encuentra todos las asignaciones por un parametro de entrada que recibe de tipo
     * Pageable paginacion.
     * 
     * @param pageable Criterio de paginacion por pagina y tamaño de registros, ya contiene el Sort embebido.
     * @see            Asignacion
     * @return         Una pagina de tipo Asignacion ordenados por un criterio de clasificacion.
     */
    public Page<Asignacion> findAll(Pageable pageable);

    /**
     * Definicion de un metodo que encuentra una asignacion por un parametro de entrada que recibe de tipo long id.
     * 
     * @param id Identificador unico de asignacion.
     * @see      Asignacion
     * @return   Un objeto de tipo Asignacion encontrado por un parametro de entrada id asignacion.
     */
    public Asignacion findById(long id);

    /**
     * Definicion de un metodo que guarda una asignacion por un parametro de entrada que recibe de tipo Asignacion.
     * 
     * @param asignacion Objeto de tipo Asignacion con todos sus atributos.
     * @see              Asignacion
     * @return           Un objeto de tipo Asignacion guardado.
     */
    public Asignacion save(Asignacion asignacion);

    /**
     * Definicion de un metodo que elimina una asignacion por un parametro de entrada que recibe de tipo long id.
     * 
     * @param id Identificador unico de cada asignacion.
     */
    public void deleteById(long id);
    
}
