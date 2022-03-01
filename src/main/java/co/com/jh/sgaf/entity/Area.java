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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Esta clase actua como entidad de la tabla area y por medio ORM (Object Relational Mapping) se puede mapear a
 * tablas de la base de datos, con cada uno de los atributos declarados en esta clase. Por medio de la anotacion 
 * Data de la libreria de Lombok no hay necesidad de declarar metodos constructores, ni tampoco es necesario 
 * declarar lo metodos accesores como son getters y setters de cada uno de los atributos con modificador de acceso
 * private, Lombok lo hace por nosotros.
 * 
 * @author jsherreram
 * @version 1.0
 */
@Data
@Entity
@Table(name = "area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Long idArea;

    @Column(name = "nombre_area")
    @NotEmpty(message = "El campo nombre área no puede estar vacío")
    @Size(min = 4, max = 45, message = "El campo nombre área debe tener entre 4 y 45 caracteres")
    private String nombreArea;

    /**
     * El atributo activosFijos es una lista de tipo ActivoFijo que hace referencia a la relacion de uno a muchos
     * con la entidad de area, es decir un area puede tener uno o muchos activos fijos asociadas a esta. El tipo de
     * carga es LAZY (perezosa) lo que quiere decir que al consultar un area no va a a traer los activos fijos 
     * asociados al area, esto la hace mas eficiente al momento de consultar muchos registros en varias conexiones 
     * simultaneas.
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "area")
    private List<ActivoFijo> activosFijos;

    /**
     * El atributo empleados es una lista de tipo Empleado que hace referencia a la relacion de uno a muchos con la
     * entidad de area, es decir un area puede tener uno o muchos empleados asociados a esta. El tipo de carga es 
     * LAZY (perezosa) lo que quiere decir que al consultar un area no va a a traer los empleados asociados al area,
     * esto la hace mas eficiente al momento de consultar muchos registros en varias conexiones simultaneas.
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "area")
    private List<Empleado> empleados;

    /**
     * El atributo asignaciones es una lista de tipo Asignacion que hace referencia a la relacion de uno a muchos 
     * con la entidad de area, es decir un area puede tener una o muchas asignaciones asociadas a esta. El tipo de
     * carga es LAZY (perezosa) lo que quiere decir que al consultar un area no va a a traer las asignaciones 
     * asociadas al area, esto la hace mas eficiente al momento de consultar muchos registros en varias conexiones 
     * simultaneas.
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "area")
    private List<Asignacion> asignaciones;

}
