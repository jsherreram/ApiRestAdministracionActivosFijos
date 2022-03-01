/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.dao;

import co.com.jh.sgaf.entity.ActivoFijo;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Esta es la interfaz de la capa de datos de activo fijo extiende de la interfaz JpaRepository esta a su vez 
 * recibe un generico con dos parametros el primero el nombre de la entidad y el segundo el tipo de dato Object de 
 * la llave primaria de la entidad. Esta interfaz JpaRepository ya contiene definidos todos los metodos para 
 * realizar operaciones en la base de datos, los metodos que se definen en esta interfaz son personalizados con 
 * consultas a la base de datos que no estan contempladas en ninguna interfaz de Java.
 * 
 * @author jsherreram
 * @version 1.0
 */
public interface IActivoFijoDao extends JpaRepository<ActivoFijo, Long> {
    
    /**
     * Definicion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo
     * Sort ordenamiento, la anotacion @Query genera una consulta a la base de datos donde obtiene todos los activos
     * fijos haciendo un join a la tabla empleado y a la tabla area con el fin de mostrar cada activo fijo con su
     * empleado y area asociada.
     * 
     * @param sort Criterio de ordenamiento que recibe para clasificar.
     * @see        ActivoFijo
     * @return     Una lista de tipo ActivoFijo ordenados por un criterio de clasificacion.
     */
    @Query(value = "SELECT af FROM ActivoFijo af LEFT JOIN FETCH af.empleado LEFT JOIN FETCH af.area")
    public List<ActivoFijo> findAll(Sort sort);

    /**
     * Definicion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo
     * Pageable paginacion, la anotacion @Query genera una consulta a la base de datos donde obtiene todos los activos
     * fijos haciendo un join a la tabla empleado y a la tabla area con el fin de mostrar cada activo fijo con su
     * empleado y area asociada y con la propiedad countQuery obtenemos el conteo de registros.
     * 
     * @param pageable Criterio de paginacion por pagina y tamaño de registros, ya contiene el Sort embebido.
     * @see            ActivoFijo
     * @return         Una pagina de tipo ActivoFijo ordenados por un criterio de clasificacion.
     */
    @Query(value = "SELECT af FROM ActivoFijo af LEFT JOIN FETCH af.empleado LEFT JOIN FETCH af.area", countQuery = "SELECT COUNT(af) FROM ActivoFijo af LEFT JOIN af.empleado LEFT JOIN af.area")
    public Page<ActivoFijo> findAll(Pageable pageable);

    /**
     * Definicion de un metodo que encuentra un activo fijo por un parametro de entrada que recibe de tipo long id,
     * la anotacion @Query genera una consulta a la base de datos donde obtiene un activo fijo haciendo un join a 
     * la tabla empleado y a la tabla area con la codicion de que el idActivoFijo sea igual a la del parametro con
     * el fin de mostrar un activo fijo con su empleado y area asociada por id.
     * 
     * @param id Identificador unico del activo fijo.
     * @see      ActivoFijo
     * @return   Un objeto de tipo ActivoFijo encontrado por un parametro de entrada id activo fijo.
     */
    @Query(value = "SELECT af FROM ActivoFijo af LEFT JOIN FETCH af.empleado LEFT JOIN FETCH af.area WHERE af.idActivoFijo = :id")
    public ActivoFijo findById(long id);
    
    /**
     * Definicion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo
     * String tipo, la anotacion @Query genera una consulta a la base de datos donde obtiene todos los activos 
     * fijos haciendo un join a la tabla empleado y a la tabla area con el fin de mostrar cada activo fijo con su
     * empleado y area asociada por tipo.
     * 
     * @param tipo Grupo dentro del cual se categoriza el activo fijo.
     * @see        ActivoFijo
     * @return     Una lista de tipo ActivoFijo encontrado por un parametro de entrada tipo activo fijo.
     */
    @Query(value = "SELECT af FROM ActivoFijo af LEFT JOIN FETCH af.empleado LEFT JOIN FETCH af.area WHERE af.tipo LIKE %?1%")
    public List<ActivoFijo> findAllByTipo(String tipo);

    /**
     * Definicion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de tipo
     * Date fechaCompra, la anotacion @Query genera una consulta a la base de datos donde obtiene todos los activos
     * fijos haciendo un join a la tabla empleado y a la tabla area con el fin de mostrar cada activo fijo con su
     * empleado y area asociada por fechaCompra.
     * 
     * @param fechaCompra Fecha en la cual se realizo la compra del activo fijo.
     * @see               ActivoFijo
     * @return            Una lista de tipo ActivoFijo encontrado por un parametro de entrada fecha compra activo fijo.       
     */
    @Query(value = "SELECT af FROM ActivoFijo af LEFT JOIN FETCH af.empleado LEFT JOIN FETCH af.area WHERE af.fechaCompra = ?1")
    public List<ActivoFijo> findAllByFechaCompra(Date fechaCompra);
    
    /**
     * Definicion de un metodo que encuentra un activo fijo por un parametro de entrada que recibe de tipo String
     * serial, la anotacion @Query genera una consulta a la base de datos donde obtiene un activo fijo haciendo un
     * join a la tabla empleado y a la tabla area con el fin de mostrar el activo fijo con su empleado y area 
     * asociada por serial.
     * 
     * @param serial Referencia unica de cada activo fijo.
     * @see          ActivoFijo
     * @return       Un objeto de tipo ActivoFijo encontrado por un paramero de entrada serial activo fijo.
     */
    @Query(value = "SELECT af FROM ActivoFijo af LEFT JOIN FETCH af.empleado LEFT JOIN FETCH af.area WHERE af.serial = :serial")
    public ActivoFijo findBySerial(String serial);
    
}
