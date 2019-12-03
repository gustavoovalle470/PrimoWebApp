/*
 * PACKAGE
 */
package com.primo.ws.sucursal;

/*
 * IMPORTS
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Servicio;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

/**
 * Clase que implementa el cliente de los Servicios Web del objeto Servicio
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 09/10/2019
 */
public class ServicioWSClient {
    
    public static List<Servicio> traerServicios() {
        //Atributos de Metodo
        Gson myGson = new Gson();
        Client myClient = ClientBuilder.newClient();
        
        //Traer la información de la empresa
        String myURL = PrimoURI.GET_SERV_WS+"/all";
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        
        List<Servicio> myListDominio = myGson.fromJson(myStringList, new TypeToken<List<Servicio>>(){}.getType());
        myClient.close();
        
        //Retornar la información de las empresas
        return myListDominio;
    }
}
