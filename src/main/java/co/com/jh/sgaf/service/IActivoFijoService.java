/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.service;

import co.com.jh.sgaf.entity.ActivoFijo;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Esta es la interfaz de la capa de servicio de activo fijo es la que va a interactuar con la capa de datos los 
 * metodos que se definen en esta interfaz son acciones o comportamientos que se requieren hacer en la capa de datos.
 * 
 * @author jsherreram
 * @version 1.0
 */
public interface IActivoFijoService {

    /**
     * Definicion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo
     * Sort ordenamiento.
     * 
     * @param sort Criterio de ordenamiento que recibe para clasificar.
     * @see        ActivoFijo
     * @return     Una lista de tipo ActivoFijo ordenados por un criterio de clasificacion.
     */
    public List<ActivoFijo> findAll(Sort sort);

    /**
     * Definicion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo
     * Pageable paginacion.
     * 
     * @param pageable Criterio de paginacion por pagina y tamaño de registros, ya contiene el Sort embebido.
     * @see            ActivoFijo
     * @return         Una pagina de tipo ActivoFijo ordenados por un criterio de clasificacion.
     */
    public Page<ActivoFijo> findAll(Pageable pageable);

    /**
     * Definicion de un metodo que encuentra un activo fijo por un parametro de entrada que recibe de tipo long id.
     * 
     * @param id Identificador unico del activo fijo.
     * @see      ActivoFijo
     * @return   Un objeto de tipo ActivoFijo encontrado por un parametro de entrada id activo fijo.
     */
    public ActivoFijo findById(long id);

    /**
     * Definicion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo
     * String tipo.
     * 
     * @param tipo Grupo dentro del cual se categoriza el activo fijo.
     * @see        ActivoFijo
     * @return     Una lista de tipo ActivoFijo encontrado por un parametro de entrada tipo activo fijo.
     */
    public List<ActivoFijo> findAllByTipo(String tipo);

    /**
     * Definicion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo
     * Date fechaCompra.
     * 
     * @param fechaCompra Fecha en la cual se realizo la compra del activo fijo.
     * @see               ActivoFijo
     * @return            Una lista de tipo ActivoFijo encontrado por un parametro de entrada fecha compra activo fijo.       
     */
    public List<ActivoFijo> findAllByFechaCompra(Date fechaCompra);

    /**
     * Definicion de un metodo que encuentra un activo fijo por un parametro de entrada que recibe de tipo String
     * serial.
     * 
     * @param serial Referencia unica de cada activo fijo.
     * @see          ActivoFijo
     * @return       Un objeto de tipo ActivoFijo encontrado por un paramero de entrada serial activo fijo.
     */
    public ActivoFijo findBySerial(String serial);

    /**
     * Definicion de un metodo que guarda un activo fijo por un parametro de entrada que recibe de tipo ActivoFijo.
     * 
     * @param activoFijo Objeto de tipo ActivoFijo con todos sus atributos.
     * @see              ActivoFijo
     * @return           Un objeto de tipo ActivoFijo guardado.
     */
    public ActivoFijo save(ActivoFijo activoFijo);

    /**
     * Definicion de un metodo que elimina un activo fijo por un parametro de entrada que recibe de tipo long id.
     * 
     * @param id Identificador unico de cada activo fijo.
     */
    public void deleteById(long id);

}
