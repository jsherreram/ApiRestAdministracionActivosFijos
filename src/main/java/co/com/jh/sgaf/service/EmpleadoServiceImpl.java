/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.service;

import co.com.jh.sgaf.dao.IAreaDao;
import co.com.jh.sgaf.dao.IEmpleadoDao;
import co.com.jh.sgaf.entity.Area;
import co.com.jh.sgaf.entity.Empleado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Esta es la clase de implementacion de la capa de servicio de empleado es la que va a implementar los metodos
 * acciones o comportamientos definidos en la interfaz de servicio de empleado, en esta clase se implementa la 
 * logica de negocio de la API.
 * 
 * @author jsherreram
 * @version 1.0
 */
@Service
public class EmpleadoServiceImpl implements IEmpleadoService {
    
    @Autowired
    private IEmpleadoDao empleadoDao;

    @Autowired
    private IAreaDao areaDao;

    /**
     * Implementacion de un metodo que encuentra todos los empleados por un parametro de entrada que recibe de tipo
     * Sort ordenamiento. Es transaccional de solo lectura.
     * 
     * @param sort Criterio de ordenamiento que recibe para clasificar.
     * @see        Empleado
     * @return     Una lista de tipo Empleado ordenados por un criterio de clasificacion.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll(Sort sort) {
        return empleadoDao.findAll(sort);
    }

    /**
     * Implementacion de un metodo que encuentra todos los empleados por un parametro de entrada que recibe de
     * tipo Pageable paginacion. Es transaccional de solo lectura.
     * 
     * @param pageable Criterio de paginacion por pagina y tamaño de registros, ya contiene el Sort embebido.
     * @see            Empleado
     * @return         Una pagina de tipo Empleado ordenados por un criterio de clasificacion.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Empleado> findAll(Pageable pageable) {
        return empleadoDao.findAll(pageable);
    }

    /**
     * Implementacion de un metodo que encuentra un empleado por un parametro de entrada que recibe de tipo long
     * id. Es transaccional de solo lectura.
     * 
     * @param id Identificador unico del empleado.
     * @see      Empleado
     * @return   Un objeto de tipo Empleado encontrado por un parametro de entrada id empleado.
     */
    @Override
    @Transactional(readOnly = true)
    public Empleado findById(long id) {
        return empleadoDao.findById(id);
    }

    /**
     * Implementacion de un metodo que guarda un empleado por un parametro de entrada que recibe de tipo Empleado.
     * Es transaccional permite realizar commit o rollback en la capa de datos.
     * 
     * @param empleado Objeto de tipo Empleado con todos sus atributos.
     * @see            Empleado
     * @return         Un objeto de tipo Empleado guardado.
     */
    @Override
    @Transactional
    public Empleado save(Empleado empleado) {

        Area area = areaDao.findById(empleado.getArea().getIdArea()).orElse(new Area());
        empleado.setArea(area);

        return empleadoDao.save(empleado);
    }

    /**
     * Implementacion de un metodo que elimina un empleado por un parametro de entrada que recibe de tipo long id.
     * Es transaccional permite realizar commit o rollback en la capa de datos.
     * 
     * @param id Identificador unico de cada empleado.
     */
    @Override
    @Transactional
    public void deleteById(long id) {
        empleadoDao.deleteById(id);
    }

}
