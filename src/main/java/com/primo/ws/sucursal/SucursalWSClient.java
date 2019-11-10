/*
 * PACKAGE
 */
package com.primo.ws.sucursal;

/*
 * IMPORTS
 */
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Sucursal;
import java.security.NoSuchAlgorithmException;
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
 * Clase que implementa el cliente de los Servicios Web del objeto Sucursal
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 09/10/2019
 */
public class SucursalWSClient {

    /**
     * Método que se encarga de guardar la información de la Sucursal
     * @param mySucursal
     * @return Sucursal
     * @throws NoSuchAlgorithmException
     * @throws Exception 
     */    
    public static Sucursal guardarSucursal(Sucursal mySucursal) throws NoSuchAlgorithmException, Exception {
        
        //Crear Contexto
        SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);

        //Crear el cliente
        Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	WebTarget resourceTarget= myClient.target(PrimoURI.REG_SUC_WS);
	
        //Invocar el servicio 
        Invocation.Builder invocationBuilder = resourceTarget.request();
        Response response= invocationBuilder.post(Entity.json(mySucursal));

        //Traer la respuesta del servicio Web
        Sucursal mySucursalTemp = response.readEntity(Sucursal.class);
        
        //Retornar el elemento
        return mySucursalTemp;
    }
}
