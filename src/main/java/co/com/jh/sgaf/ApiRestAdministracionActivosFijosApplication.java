package co.com.jh.sgaf;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase para iniciar la API con Spring donde se establece la configuracion de Swagger2 y el método de 
 * autenticacion por medio de Swagger2 en la interfaz grafica.
 * 
 * @author jsherreram
 * @version 1.0
 */
//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableSwagger2
public class ApiRestAdministracionActivosFijosApplication {

    /**
     * Metodo principal para iniciar la aplicacion con Spring Boot.
     * 
     * @param args Es un areglo de tipo String de la linea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiRestAdministracionActivosFijosApplication.class, args);
    }

    /**
     * Metodo que define todos los detalle informativos de la API.
     * 
     * @see    ApiInfo
     * @return Un objeto de tipo ApiInfo con todas las propiedades de información del API.
     */
    // Define all details for app info
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API Rest para la Administración de Activos Fijos")
                .description("Referencia de API de John Steak Herrera Moreno para prueba técnica de desarrollo, permite administrar los activos fijos de una compañía, esta API debería permitir:\n"
                        + "• Buscar los activos fijos por: tipo, fecha de compra, serial y como plus lista por ordenamiento y paginación.\n"
                        + "• Crea nuevos activos fijos.\n"
                        + "• Actualiza activos fijos.\n"
                        + "El controlador de errores se implementa mediante el IETF desarrollado RFC 7807 esfuerzo, que crea un esquema de manejo de errores generalizado.\n"
                        + "https://tools.ietf.org/html/rfc7807")
                .termsOfServiceUrl("http://www.linkedin.com/in/jsherreram")
                .contact(new Contact("John Steak Herrera Moreno", "https://facebook.com/jsherreram1", "john.steak05b@gmail.com;john.steak05b@hotmail.com"))
                .license("jsherreram Licencia")
                .licenseUrl("https://about.udemy.com/es/company/#offices")
                .version("1.0")
                .build();
    }

    /**
     * Metodo principal de definicion y configuracion de Swagger2.
     * 
     * @see    Docket
     * @return Un objeto de tipo Docket con todas las propiedades de definicion y configuracion del API.
     */
    // Main Swagger config definition    
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ApiRestAdministracionActivosFijos-sgaf")
                .apiInfo(apiInfo())
                // set up JWT input
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("co.com.jh"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("API Administracion Activos Fijos", "Todas las apis relacionadas con el servicio de administración de activos fijos") {
                },
                        new Tag("API Administracion Empleados", "Todas las apis relacionadas con el servicio de administración de empleados"),
                        new Tag("API Administracion Asignaciones", "Todas las apis relacionadas con el servicio de administración de asignaciones"));
    }

    /**
     * Metodo que define la clave para el API e incluirla en el header.
     * 
     * @see    ApiKey
     * @return Devuelve un objeto de tipo ApiKey con los atributos JWT, Authorization y header.
     */
    // Define API key to include the header   
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    /**
     * Metodo que configura la seguridad de JWT con un alcance de autorización global.
     * 
     * @see    SecurityContext
     * @return Devuelve un objeto de tipo SecurityContext.
     */
    // Condigure JWT security with global Autorization Scope
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    /**
     * Metodo que obtiene la autenticacion por defecto.
     * 
     * @see    SecurityReference
     * @return Un listado de tipo SecurityReference.
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}
