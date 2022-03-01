/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Esta clase actua como entidad de la tabla rol y por medio ORM (Object Relational Mapping) se puede mapear a
 * tablas de la base de datos, con cada uno de los atributos declarados en esta clase. Por medio de la anotacion 
 * Data de la libreria de Lombok no hay necesidad de declarar metodos constructores, ni tampoco es necesario 
 * declarar lo metodos accesores como son getters y setters de cada uno de los atributos con modificador de acceso
 * private, Lombok lo hace por nosotros. Esta entidad representa a todos los roles que van a interactuar con los 
 * usuarios en la API.
 * 
 * @author jsherreram
 * @version 1.0
 */
@Data
@Entity
@Table(name = "rol")
public class Rol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre_rol")
    @NotEmpty(message = "El campo nombre rol no puede estar vacío")
    @Size(min = 6, max = 45, message = "El campo nombre rol debe tener entre 6 y 45 caracteres")
    private String nombreRol;
    
}
