/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.dao;

import co.com.jh.sgaf.entity.Empleado;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Esta es la interfaz de la capa de datos de empleado extiende de la interfaz JpaRepository esta a su vez recibe un
 * generico con dos parametros el primero el nombre de la entidad y el segundo el tipo de dato Object de la llave 
 * primaria de la entidad. Esta interfaz JpaRepository ya contiene definidos todos los metodos para realizar 
 * operaciones en la base de datos, los metodos que se definen en esta interfaz son personalizados con consultas a 
 * la base de datos que no estan contempladas en ninguna interfaz de Java.
 * 
 * @author jsherreram
 * @version 1.0
 */
public interface IEmpleadoDao extends JpaRepository<Empleado, Long> {

    /**
     * Definicion de un metodo que encuentra todos los empleados por un parametro de entrada que recibe de tipo Sort
     * ordenamiento, la anotacion @Query genera una consulta a la base de datos donde obtiene todos los empleados
     * haciendo un join a la tabla area con el fin de mostrar cada empleado con su area asociada.
     * 
     * @param sort Criterio de ordenamiento que recibe para clasificar.
     * @see        Empleado
     * @return     Una lista de tipo Empleado ordenados por un criterio de clasificacion.
     */
    @Query(value = "SELECT e FROM Empleado e LEFT JOIN FETCH e.area")
    public List<Empleado> findAll(Sort sort);

    /**
     * Definicion de un metodo que encuentra todos los empleados por un parametro de entrada que recibe de tipo
     * Pageable paginacion, la anotacion @Query genera una consulta a la base de datos donde obtiene todos los 
     * empleados haciendo un join a la tabla area con el fin de mostrar cada empleado con su area asociada y con la
     * propiedad countQuery obtenemos el conteo de registros.
     * 
     * @param pageable Criterio de paginacion por pagina y tamaño de registros, ya contiene el Sort embebido.
     * @see            Empleado
     * @return         Una pagina de tipo Empleado ordenados por un criterio de clasificacion.
     */
    @Query(value = "SELECT e FROM Empleado e LEFT JOIN FETCH e.area", countQuery = "SELECT COUNT(e) FROM Empleado e LEFT JOIN e.area")
    public Page<Empleado> findAll(Pageable pageable);

    /**
     * Definicion de un metodo que encuentra un empleado por un parametro de entrada que recibe de tipo long id,
     * la anotacion @Query genera una consulta a la base de datos donde obtiene un empleado haciendo un join a 
     * la tabla area con la codicion de que el idEmpleado sea igual a la del parametro con el fin de mostrar un 
     * empleado con su area asociada por id.
     * 
     * @param id Identificador unico del empleado.
     * @see      Empleado
     * @return   Un objeto de tipo Empleado encontrado por un parametro de entrada id empleado.
     */
    @Query(value = "SELECT e FROM Empleado e LEFT JOIN FETCH e.area WHERE e.idEmpleado = :id")
    public Empleado findById(long id);

}
