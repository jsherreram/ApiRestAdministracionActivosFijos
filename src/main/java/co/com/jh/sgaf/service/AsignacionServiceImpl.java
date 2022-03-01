/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.service;

import co.com.jh.sgaf.dao.IAreaDao;
import co.com.jh.sgaf.dao.IAsignacionDao;
import co.com.jh.sgaf.dao.ICiudadDao;
import co.com.jh.sgaf.entity.Area;
import co.com.jh.sgaf.entity.Asignacion;
import co.com.jh.sgaf.entity.Ciudad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Esta es la clase de implementacion de la capa de servicio de asignacion es la que va a implementar los metodos
 * acciones o comportamientos definidos en la interfaz de servicio de asignacion, en esta clase se implementa la 
 * logica de negocio de la API.
 * 
 * @author jsherreram
 * @version 1.0
 */
@Service
public class AsignacionServiceImpl implements IAsignacionService {
    
    @Autowired
    private IAsignacionDao asignacionDao;

    @Autowired
    private IAreaDao areaDao;

    @Autowired
    private ICiudadDao ciudadDao;

    /**
     * Implementacion de un metodo que encuentra todos las asignaciones por un parametro de entrada que recibe de 
     * tipo Sort ordenamiento. Es transaccional de solo lectura.
     * 
     * @param sort Criterio de ordenamiento que recibe para clasificar.
     * @see        Asignacion
     * @return     Una lista de tipo Asignacion ordenados por un criterio de clasificacion.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Asignacion> findAll(Sort sort) {
        return asignacionDao.findAll(sort);
    }

    /**
     * Implementacion de un metodo que encuentra todos las asignaciones por un parametro de entrada que recibe de
     * tipo Pageable paginacion. Es transaccional de solo lectura.
     * 
     * @param pageable Criterio de paginacion por pagina y tamaño de registros, ya contiene el Sort embebido.
     * @see            Asignacion
     * @return         Una pagina de tipo Asignacion ordenados por un criterio de clasificacion.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Asignacion> findAll(Pageable pageable) {
        return asignacionDao.findAll(pageable);
    }

    /**
     * Implementacion de un metodo que encuentra una asignacion por un parametro de entrada que recibe de tipo long
     * id. Es transaccional de solo lectura.
     * 
     * @param id Identificador unico del activo fijo.
     * @see      Asignacion
     * @return   Un objeto de tipo Asignacion encontrado por un parametro de entrada id asignacion.
     */
    @Override
    @Transactional(readOnly = true)
    public Asignacion findById(long id) {
        return asignacionDao.findById(id);
    }

    /**
     * Implementacion de un metodo que guarda una asignacion por un parametro de entrada que recibe de tipo 
     * Asignacion. Es transaccional permite realizar commit o rollback en la capa de datos.
     * 
     * @param asignacion Objeto de tipo Asignacion con todos sus atributos.
     * @see              Asignacion
     * @return           Un objeto de tipo Asignacion guardado.
     */
    @Override
    @Transactional
    public Asignacion save(Asignacion asignacion) {

        Area area = areaDao.findById(asignacion.getArea().getIdArea()).orElse(new Area());
        asignacion.setArea(area);

        Ciudad ciudad = ciudadDao.findById(asignacion.getCiudad().getIdCiudad()).orElse(new Ciudad());
        asignacion.setCiudad(ciudad);

        return asignacionDao.save(asignacion);

    }

    /**
     * Implementacion de un metodo que elimina una asignacion por un parametro de entrada que recibe de tipo long 
     * id. Es transaccional permite realizar commit o rollback en la capa de datos.
     * 
     * @param id Identificador unico de cada asignacion.
     */
    @Override
    @Transactional
    public void deleteById(long id) {
        asignacionDao.deleteById(id);
    }

}
