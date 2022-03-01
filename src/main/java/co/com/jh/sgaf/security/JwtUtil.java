/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import static java.util.Collections.emptyList;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author jsherreram
 * @version 1.0
 */
@Slf4j
public class JwtUtil {

    /**
     * Metodo para crear el JWT y enviarlo al cliente en el header de la respuesta.
     * 
     * @param res      Respuesta http.
     * @param username Nombre de usuario.
     */
    static void addAuthentication(HttpServletResponse res, String username) {

        String token = Jwts.builder()
                .setSubject(username)
                // Vamos a asignar un tiempo de expiracion de 1 minuto solo con fines demostrativos.
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                // Hash con el que firmaremos la clave.
                .signWith(SignatureAlgorithm.HS512, "P@tit0")
                .compact();
        // Agregamos al encabezado el token.
        res.addHeader("Authorization", "Bearer " + token);
        log.info("\n\n"
                + "Hola desde co.com.jh.sgaf.security.JwtUtil\n"
                + "Validando método addAuthentication para crear el JWT y enviarlo al cliente en el header de la respuesta.\n"
                + "HttpServletResponse: " + res + "\n"
                + "Username: " + username + "\n"
                + "Bearer: " + token + "\n");
    }

    /**
     * Metodo para validar el token enviado por el cliente.
     * 
     * @param request Peticion http.
     * @see           Authentication
     * @return        Un objeto de tipo UsernamePasswordAuthenticationToken sin password.
     */
    static Authentication getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion.
        String token = request.getHeader("Authorization");

        // Si hay un token presente, entonces lo validamos.
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("P@tit0")
                    .parseClaimsJws(token.replace("Bearer", "")) // Este método es el que valida.
                    .getBody()
                    .getSubject();
            // Recordamos que para las demás peticiones que no sean /login no requerimos una autenticacion por 
            // username/password por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin 
            // password.
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
        }
        return null;
    }

}
