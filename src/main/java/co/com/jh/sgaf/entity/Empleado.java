/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Esta clase actua como entidad de la tabla empleado y por medio ORM (Object Relational Mapping) se puede mapear a
 * tablas de la base de datos, con cada uno de los atributos declarados en esta clase. Por medio de la anotacion 
 * Data de la libreria de Lombok no hay necesidad de declarar métodos constructores, ni tampoco es necesario 
 * declarar lo metodos accesores como son getters y setters de cada uno de los atributos con modificador de acceso
 * private, Lombok lo hace por nosotros.
 * 
 * @author jsherreram
 * @version 1.0
 */
@Data
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @NotNull(message = "El campo cédula empleado no puede estar nulo")
    private int cedula;

    @Column(name = "nombre_empleado")
    @NotEmpty(message = "El campo nombre empleado no puede estar vacío")
    @Size(min = 4, max = 45, message = "El campo nombre empleado debe tener entre 4 y 45 caracteres")
    private String nombreEmpleado;

    @Column(name = "apellido_empleado")
    @NotEmpty(message = "El campo apellido empleado no puede estar vacío")
    @Size(min = 4, max = 45, message = "El campo apellido empleado debe tener entre 4 y 45 caracteres")
    private String apellidoEmpleado;

    @NotEmpty(message = "El campo email empleado no puede estar vacío")
    @Size(min = 10, max = 45, message = "El campo email empleado debe tener entre 10 y 45 caracteres")
    @Email(message = "El campo email empleado no tiene un formato de una dirección de correo electrónico válida")
    private String email;

    /**
     * El atributo area de tipo Area hace referencia a la llave foranea en la tabla empleado, es decir que 
     * existe una relacion de muchos a uno. Un area puede tener uno o muchos empleados asociados a esta. El tipo de
     * carga es LAZY (perezosa) lo que quiere decir que al consultar un empleado no va a a traer las areas 
     * asociadas al empleado, esto la hace mas eficiente al momento de consultar muchos registros en varias 
     * conexiones simultaneas y el tipo de cascada indica el tipo de accion en el objeto area al momento de hacer 
     * una operacion transaccional con empleado.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
//    @NotNull(message = "El campo área empleado no puede estar nulo")
    private Area area;

     /**
     * El atributo activosFijos es una lista de tipo ActivoFijo que hace referencia a la relacion de uno a muchos
     * con la entidad de empleado, es decir un empleado puede tener uno o muchos activos fijos asociados a el. El 
     * tipo de carga es LAZY (perezosa) lo que quiere decir que al consultar un empleado no va a a traer los activos
     * fijos asociados al empleado, esto la hace mas eficiente al momento de consultar muchos registros en varias 
     * conexiones simultaneas.
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "empleado")
    private List<ActivoFijo> activosFijos;

}
