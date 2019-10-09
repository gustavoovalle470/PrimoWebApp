/*
 * PACKAGE
 */
package com.primo.ws.contacto;

/*
 * IMPORTS
 */
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Contacto;
import com.primo.ws.PrimoMsg;
import java.io.IOException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

/**
 * Clase que implementa el cliente de los Servicios Web para valores del Dominio
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 09/10/2019
 */
public class ContactoWSClient {
        
    /**
     * Método que guarda la información del Contacto
     * @param myContacto
     * @throws IOException
     * @throws Exception 
     */
    public static void guardarContacto(Contacto myContacto) throws IOException, Exception{

        //Crear Contexto
        SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);

        //Crear el cliente
        Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	WebTarget resourceTarget= myClient.target(PrimoURI.REG_CONT_WS);

        //Invocar el servicio 
        Invocation.Builder invocationBuilder = resourceTarget.request();
        Response response= invocationBuilder.post(Entity.json(myContacto));

        //Traer la respuesta del servicio Web
        PrimoMsg respuesta=response.readEntity(PrimoMsg.class);

        //Verificar la respuesta del Web Services
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }
}
