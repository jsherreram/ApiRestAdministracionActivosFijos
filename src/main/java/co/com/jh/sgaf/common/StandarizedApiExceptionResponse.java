/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * Esta Clase maneja la respuesta estandarizada de Excepciones del Api bajo el estandar RFC 7807.
 * El esfuerzo por estandarizar los informes de errores de la API de descanso cuenta con el apoyo de ITEF 
 * (Grupo de Trabajo de Ingeniería de Internet, abierto organizacion estandar que desarrolla y promueve estandares 
 * voluntarios de Internet) en RFC 7807 que creo un esquema generalizado de manejo de errores compuesto por cinco 
 * partes.
 * 1-type: Un identificador de URI que categoriza el error
 * 2-title: Un mensaje breve y legible por humanos sobre el error
 * 3-code: El codigo de error unico
 * 4-detail: Una explicacion legible por humanos del error
 * 5-instance: Un URI que identifica la ocurrencia especifica del error Estandarizado es opcional pero tiene
 * ventaja, se usa para facebook y twitter, es decir:
 * https://graph.facebook.com/oauth/access_token?
 * https://api.twitter.com/1.1/statuses/update.json?include_entities=true
 *
 * @author jsherreram
 * @version 1.0
 */
public class StandarizedApiExceptionResponse {

    @ApiModelProperty(notes = "El identificador de uri único que categoriza el error", name = "type", required = true, example = "/errors/authentication/not-authorized")
    private String type = "/errors/business";

    @ApiModelProperty(notes = "Un mensaje breve y legible por humanos sobre el error", name = "title", required = true, example = "El usuario no tiene autorización")
    private String title;

    @ApiModelProperty(notes = "El código de error único", name = "code", required = false, example = "192")
    private String code;

    @ApiModelProperty(notes = "Una explicación legible por humanos del error", name = "detail", required = true, example = "El usuario no tiene los permisos de propiedad para acceder al recurso, por favor contacte con a: https://facebook.com/jsherreram1")
    private String detail;

    @ApiModelProperty(notes = "Un URI que identifica la ocurrencia específica del error", name = "instance", required = true, example = "/errors/authentication/not-authorized/01")
    private String instance = "/errors/business/data-validation";

    public StandarizedApiExceptionResponse(String title, String code, String detail) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getStatus() {
        return code;
    }

    public void setStatus(String status) {
        this.code = status;
    }

}
