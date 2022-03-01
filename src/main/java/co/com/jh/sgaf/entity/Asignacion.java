/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Esta clase actua como entidad de la tabla asignacion y por medio ORM (Object Relational Mapping) se puede mapear 
 * a tablas de la base de datos, con cada uno de los atributos declarados en esta clase. Por medio de la anotación 
 * Data de la libreria de Lombok no hay necesidad de declarar métodos constructores, ni tampoco es necesario 
 * declarar lo métodos accesores como son getters y setters de cada uno de los atributos con modificador de acceso
 * private, Lombok lo hace por nosotros. Esta entidad se creo para normalizar la relación bidireccional de muchos a 
 * muchos entre las entidades de area y ciudad porque un area puede estar en una o muchas ciudades y en una ciudad 
 * pueden existir una o muchas areas.
 * 
 * @author jsherreram
 * @version 1.0
 */
@Data
@Entity
@Table(name = "asignacion")
public class Asignacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long idAsignacion;

    /**
     * El atributo area de tipo Area hace referencia a la llave foranea en la tabla asignacion, es decir que existe
     * una relacion de muchos a uno. Un area puede tener una o muchas asignaciones asociadas a esta. El tipo de 
     * carga es LAZY (perezosa) lo que quiere decir que al consultar una asignacion no va a a traer las areas 
     * asociadas a la asignacion, esto la hace mas eficiente al momento de consultar muchos registros en varias 
     * conexiones simultaneas y el tipo de cascada indica el tipo de acción en el objeto area al momento de hacer 
     * una operacion transaccional con asignacion.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @NotNull(message = "El campo área no puede ser nulo")
    private Area area;

    /**
     * El atributo ciudad de tipo Ciudad hace referencia a la llave foranea en la tabla asignacion, es decir que 
     * existe una relacion de muchos a uno. Una ciudad puede tener una o muchas asignaciones asociadas a esta. El 
     * tipo de carga es LAZY (perezosa) lo que quiere decir que al consultar una asignacion no va a a traer las 
     * ciudades asociadas a la asignacion, esto la hace mas eficiente al momento de consultar muchos registros en 
     * varias conexiones simultaneas y el tipo de cascada indica el tipo de accion en el objeto ciudad al momento de
     * hacer una operacion transaccional con asignacion.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    @NotNull(message = "El campo ciudad no puede ser nulo")
    private Ciudad ciudad;

}
