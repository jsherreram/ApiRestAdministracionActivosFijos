/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Esta clase actua como entidad de la tabla activo_fijo y por medio ORM (Object Relational Mapping) se puede 
 * mapear a tablas de la base de datos, con cada uno de los atributos declarados en esta clase. Por medio de la
 * anotacion Data de la libreria de Lombok no hay necesidad de declarar metodos constructores, ni tampoco es
 * necesario declarar lo metodos accesores como son getters y setters de cada uno de los atributos con modificador
 * de acceso private, Lombok lo hace por nosotros.
 *
 * @author jsherreram
 * @version 1.0
 */
@Data
@Entity
@Table(name = "activo_fijo")
public class ActivoFijo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activo_fijo")
    private Long idActivoFijo;

    @Column(name = "nombre_activo_fijo")
    @NotEmpty(message = "El campo nombre activo fijo no puede estar vacío")
    @Size(min = 4, max = 45, message = "El campo nombre activo fijo debe tener entre 4 y 45 caracteres")
    private String nombreActivoFijo;

    private String descripcion;

    @NotEmpty(message = "El campo tipo activo fijo no puede estar vacío")
    @Size(min = 4, max = 45, message = "El campo tipo activo fijo debe tener entre 4 y 45 caracteres")
    private String tipo;

    @NotEmpty(message = "El campo serial activo fijo no puede estar vacío")
    @Size(min = 4, max = 9, message = "El campo serial activo fijo debe tener entre 4 y 9 caracteres")
    private String serial;

    @Column(name = "numero_interno_inventario")
    @NotNull(message = "El campo número interno inventario activo fijo no puede estar nulo")
    private int numeroInternoInventario;

    @Min(value = 0, message = "El campo peso activo fijo no puede tener valor negativo")
    private Float peso;

    @Min(value = 0, message = "El campo alto activo fijo no puede tener valor negativo")
    private Float alto;

    @Min(value = 0, message = "El campo ancho activo fijo no puede tener valor negativo")
    private Float ancho;

    @Min(value = 0, message = "El campo largo activo fijo no puede tener valor negativo")
    private Float largo;

    @Column(name = "valor_compra")
    @NotNull(message = "El campo valor compra activo fijo no puede estar nulo")
    @Min(value = 0, message = "El campo valor compra activo fijo no puede tener valor negativo")
    private double valorCompra;

    @Column(name = "fecha_compra")
    @NotNull(message = "El campo fecha compra activo fijo no puede estar nulo")
    @Past(message = "El campo fecha compra activo fijo debe ser una fecha en el pasado")
    private Date fechaCompra;

    /**
     * El atributo empleado de tipo Empleado hace referencia a la llave foranea en la tabla activo_fijo, es decir 
     * que existe una relacion de muchos a uno. Un empleado puede tener uno o muchos activos fijos asociados a el.
     * El tipo de carga es LAZY (perezosa) lo que quiere decir que al consultar un activo fijo no va a a traer los
     * empleados asociados al activo fijo, esto la hace mas eficiente al momento de consultar muchos registros en 
     * varias conexiones simultaneas y el tipo de cascada indica el tipo de acción en el objeto empleado al momento
     * de hacer una operacion transaccional con activo fijo.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    private Empleado empleado;

    /**
     * El atributo area de tipo Area hace referencia a la llave foranea en la tabla activo_fijo, es decir que 
     * existe una relacion de muchos a uno. Un area puede tener uno o muchos activos fijos asociados a esta.
     * El tipo de carga es LAZY (perezosa) lo que quiere decir que al consultar un activo fijo no va a a traer las
     * areas asociadas al activo fijo, esto la hace mas eficiente al momento de consultar muchos registros en
     * varias conexiones simultaneas y el tipo de cascada indica el tipo de accion en el objeto area al momento de
     * hacer una operacion transaccional con activo fijo.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    private Area area;

}
