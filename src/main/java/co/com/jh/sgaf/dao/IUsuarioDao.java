/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.dao;

import co.com.jh.sgaf.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Esta es la interfaz de la capa de datos de usuario extiende de la interfaz JpaRepository esta a su vez recibe un 
 * generico con dos parametros el primero el nombre de la entidad y el segundo el tipo de dato Object de la llave 
 * primaria de la entidad. Esta interfaz JpaRepository ya contiene definidos todos los metodos para realizar 
 * operaciones en la base de datos, los metodos que se definen en esta interfaz son personalizados.
 * 
 * @author jsherreram
 * @version 1.0
 */
public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

    /**
     * Definicion de un metodo que encuentra un usuario por un parametro de entrada que recibe de tipo String
     * username.
     * 
     * @param username Nombre de usuario que identifica la autenticación en el API, debe ser unico.
     * @see            Usuario
     * @return         Un objeto de tipo Usuario encontrado por un paramero de entrada username usuario.
     */
    public Usuario findByUsername(String username);

}
