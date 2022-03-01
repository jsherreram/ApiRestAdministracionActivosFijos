/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.dao;

import co.com.jh.sgaf.entity.Asignacion;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Esta es la interfaz de la capa de datos de asignacion extiende de la interfaz JpaRepository esta a su vez recibe 
 * un generico con dos parametros el primero el nombre de la entidad y el segundo el tipo de dato Object de la llave
 * primaria de la entidad. Esta interfaz JpaRepository ya contiene definidos todos los metodos para realizar 
 * operaciones en la base de datos, los metodos que se definen en esta interfaz son personalizados con consultas a 
 * la base de datos que no estan contempladas en ninguna interfaz de Java.
 * 
 * @author jsherreram
 * @version 1.0
 */
public interface IAsignacionDao extends JpaRepository<Asignacion, Long> {
    
    /**
     * Definicion de un metodo que encuentra todos las asignaciones por un parametro de entrada que recibe de tipo
     * Sort ordenamiento, la anotacion @Query genera una consulta a la base de datos donde obtiene todos las 
     * asignaciones haciendo un join a la tabla area y a la tabla ciudad con el fin de mostrar cada asignacion con
     * su area y ciudad asociada.
     * 
     * @param sort Criterio de ordenamiento que recibe para clasificar.
     * @see        Asignacion
     * @return     Una lista de tipo Asignacion ordenadas por un criterio de clasificacion.
     */
    @Query(value = "SELECT a FROM Asignacion a LEFT JOIN FETCH a.area LEFT JOIN FETCH a.ciudad")
    public List<Asignacion> findAll(Sort sort);

    /**
     * Definicion de un metodo que encuentra todos las asignaciones por un parametro de entrada que recibe de tipo
     * Pageable paginacion, la anotacion @Query genera una consulta a la base de datos donde obtiene todos las 
     * asignaciones haciendo un join a la tabla area y a la tabla ciudad con el fin de mostrar cada asignacion con 
     * su area y ciudad asociada y con la propiedad countQuery obtenemos el conteo de registros.
     * 
     * @param pageable Criterio de paginacion por pagina y tamaño de registros, ya contiene el Sort embebido.
     * @see            Asignacion
     * @return         Una pagina de tipo Asignacion ordenados por un criterio de clasificacion.
     */
    @Query(value = "SELECT a FROM Asignacion a LEFT JOIN FETCH a.area LEFT JOIN FETCH a.ciudad", countQuery = "SELECT COUNT(a) FROM Asignacion a LEFT JOIN a.area LEFT JOIN a.ciudad")
    public Page<Asignacion> findAll(Pageable pageable);

    /**
     * Definicion de un metodo que encuentra una asignacion por un parametro de entrada que recibe de tipo long id,
     * la anotacion @Query genera una consulta a la base de datos donde obtiene una asignacion haciendo un join a 
     * la tabla area y a la tabla ciudad con la codicion de que el idAsignacion sea igual a la del parametro con
     * el fin de mostrar una asignacion con su area y ciudad asociada por id.
     * 
     * @param id Identificador unico de asignacion.
     * @see      Asignacion
     * @return   Un objeto de tipo Asignacion encontrado por un parametro de entrada id asignacion.
     */
    @Query(value = "SELECT a FROM Asignacion a LEFT JOIN FETCH a.area LEFT JOIN FETCH a.ciudad WHERE a.idAsignacion = :id")
    public Asignacion findById(long id);

}
