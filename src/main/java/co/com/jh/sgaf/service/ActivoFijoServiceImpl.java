/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.service;

import co.com.jh.sgaf.dao.IActivoFijoDao;
import co.com.jh.sgaf.dao.IAreaDao;
import co.com.jh.sgaf.dao.IEmpleadoDao;
import co.com.jh.sgaf.entity.ActivoFijo;
import co.com.jh.sgaf.entity.Area;
import co.com.jh.sgaf.entity.Empleado;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Esta es la clase de implementacion de la capa de servicio de activo fijo es la que va a implementar los metodos
 * acciones o comportamientos definidos en la interfaz de servicio de activo fijo, en esta clase se implementa la 
 * logica de negocio de la API.
 * 
 * @author jsherreram
 * @version 1.0
 */
@Service
public class ActivoFijoServiceImpl implements IActivoFijoService {

    @Autowired
    private IActivoFijoDao activoFijoDao;

    @Autowired
    private IEmpleadoDao empleadoDao;

    @Autowired
    private IAreaDao areaDao;

    /**
     * Implementacion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de 
     * tipo Sort ordenamiento. Es transaccional de solo lectura.
     * 
     * @param sort Criterio de ordenamiento que recibe para clasificar.
     * @see        ActivoFijo
     * @return     Una lista de tipo ActivoFijo ordenados por un criterio de clasificacion.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ActivoFijo> findAll(Sort sort) {
        return activoFijoDao.findAll(sort);
    }

    /**
     * Implementacion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de
     * tipo Pageable paginacion. Es transaccional de solo lectura.
     * 
     * @param pageable Criterio de paginacion por pagina y tamaño de registros, ya contiene el Sort embebido.
     * @see            ActivoFijo
     * @return         Una pagina de tipo ActivoFijo ordenados por un criterio de clasificacion.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActivoFijo> findAll(Pageable pageable) {
        return activoFijoDao.findAll(pageable);
    }

    /**
     * Implementacion de un metodo que encuentra un activo fijo por un parametro de entrada que recibe de tipo long
     * id. Es transaccional de solo lectura.
     * 
     * @param id Identificador unico del activo fijo.
     * @see      ActivoFijo
     * @return   Un objeto de tipo ActivoFijo encontrado por un parametro de entrada id activo fijo.
     */
    @Override
    @Transactional(readOnly = true)
    public ActivoFijo findById(long id) {
        return activoFijoDao.findById(id);
    }

    /**
     * Implementacion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de 
     * tipo String tipo. Es transaccional de solo lectura.
     * 
     * @param tipo Grupo dentro del cual se categoriza el activo fijo.
     * @see        ActivoFijo
     * @return     Una lista de tipo ActivoFijo encontrado por un parametro de entrada tipo activo fijo.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ActivoFijo> findAllByTipo(String tipo) {
        return activoFijoDao.findAllByTipo(tipo);
    }

    /**
     * Implementacion de un metodo que encuentra todos los activos fijos por un parametro de entrada que recibe de
     * tipo Date fechaCompra. Es transaccional de solo lectura.
     * 
     * @param fechaCompra Fecha en la cual se realizo la compra del activo fijo.
     * @see               ActivoFijo
     * @return            Una lista de tipo ActivoFijo encontrado por un parametro de entrada fecha compra activo fijo.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ActivoFijo> findAllByFechaCompra(Date fechaCompra) {
        return activoFijoDao.findAllByFechaCompra(fechaCompra);
    }

    /**
     * Implementacion de un metodo que encuentra un activo fijo por un parametro de entrada que recibe de tipo 
     * String serial. Es transaccional de solo lectura.
     * 
     * @param serial Referencia unica de cada activo fijo.
     * @see          ActivoFijo
     * @return       Un objeto de tipo ActivoFijo encontrado por un paramero de entrada serial activo fijo.
     */
    @Override
    @Transactional(readOnly = true)
    public ActivoFijo findBySerial(String serial) {
        return activoFijoDao.findBySerial(serial);
    }

    /**
     * Implementacion de un metodo que guarda un activo fijo por un parametro de entrada que recibe de tipo 
     * ActivoFijo. Es transaccional permite realizar commit o rollback en la capa de datos.
     * 
     * @param activoFijo Objeto de tipo ActivoFijo con todos sus atributos.
     * @see              ActivoFijo
     * @return           Un objeto de tipo ActivoFijo guardado.
     */
    @Override
    @Transactional
    public ActivoFijo save(ActivoFijo activoFijo) {

//        Empleado empleado = empleadoDao.findById(activoFijo.getEmpleado().getIdEmpleado()).orElse(null);
//        activoFijo.setEmpleado(empleado);
//
//        Area area = areaDao.findById(activoFijo.getArea().getIdArea()).orElse(null);
//        activoFijo.setArea(area);

        return activoFijoDao.save(activoFijo);

    }

    /**
     * Implementacion de un metodo que elimina un activo fijo por un parametro de entrada que recibe de tipo long 
     * id. Es transaccional permite realizar commit o rollback en la capa de datos.
     * 
     * @param id Identificador unico de cada activo fijo.
     */
    @Override
    @Transactional
    public void deleteById(long id) {
        activoFijoDao.deleteById(id);
    }

}
