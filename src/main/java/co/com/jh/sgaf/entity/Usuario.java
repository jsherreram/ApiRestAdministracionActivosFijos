/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Esta clase actua como entidad de la tabla usuario y por medio ORM (Object Relational Mapping) se puede mapear a
 * tablas de la base de datos, con cada uno de los atributos declarados en esta clase. Por medio de la anotacion 
 * Data de la libreria de Lombok no hay necesidad de declarar metodos constructores, ni tampoco es necesario 
 * declarar lo metodos accesores como son getters y setters de cada uno de los atributos con modificador de acceso
 * private, Lombok lo hace por nosotros. Esta entidad representa a todos los usuarios que van a interactuar con la 
 * API.
 * 
 * @author jsherreram
 * @version 1.0
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotEmpty(message = "El campo username no puede estar vacío")
    @Size(min = 4, max = 45, message = "El campo username debe tener entre 4 y 45 caracteres")
    private String username;

    @NotEmpty(message = "El campo password no puede estar vacío")
    @Size(min = 3, max = 128, message = "El campo password debe tener entre 3 y 128 caracteres")
    private String password;

    /**
     * El atributo roles es una lista de tipo Rol que hace referencia a la relacion de uno a muchos con la entidad 
     * de usuario, es decir un usuario puede tener uno o muchos roles asociadas a el.
     */
    @OneToMany
    @JoinColumn(name = "id_usuario")
    private List<Rol> roles;
    
}
