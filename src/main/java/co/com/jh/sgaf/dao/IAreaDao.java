/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.dao;

import co.com.jh.sgaf.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Esta es la interfaz de la capa de datos de area extiende de la interfaz JpaRepository esta a su vez recibe un 
 * generico con dos parametros el primero el nombre de la entidad y el segundo el tipo de dato Object de la llave 
 * primaria de la entidad. Esta interfaz JpaRepository ya contiene definidos todos los metodos para realizar 
 * operaciones en la base de datos.
 * 
 * @author jsherreram
 * @version 1.0
 */
public interface IAreaDao extends JpaRepository<Area, Long> {
    
}
