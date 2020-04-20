/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.ws.user;

import co.com.primo.ws.constants.PrimoURI;
import co.com.primo.model.Usuario;
import co.com.primo.ws.PrimoMsg;
import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

/**
 *
 * @author OvalleGA
 */
public class UserWSClient {
    public static void registerUser(Usuario user) throws IOException, Exception{
        System.out.println("Iniciando");
	SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);
        System.out.println("Contexto");
	Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	WebTarget resourceTarget= myClient.target(PrimoURI.REG_USER_WS);
	Builder invocationBuilder = resourceTarget.request();
	System.out.println("request");
        Response response= invocationBuilder.post(Entity.json(user));
        System.out.println("obteniendo respuesta");
        PrimoMsg respuesta=response.readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }
    
    /**
     * 
     * @param myUusario
     * @return
     * @throws IOException 
     */
    public static Usuario login(Usuario myUusario) throws IOException{
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.LOG_USER_WS + myUusario.getStrUsuario() + "/" + myUusario.getStrPassword() + "/" + myUusario.getIntTipoUsuario();
        Usuario myUsuarioTemp = myClient.target(myURL).request(MediaType.APPLICATION_JSON_TYPE).get(Usuario.class);
        myClient.close();
        return myUsuarioTemp;
    }

    /**
     * 
     * @param myUsuario
     * @return
     * @throws IOException 
     */
    public static String recuperarContrasena(Usuario myUsuario) throws IOException{
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.REC_USER_WS + myUsuario.getStrUsuario() + "/";
        String myresultado = myClient.target(myURL).request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        myClient.close();
        return myresultado;
    }

    /**
     * 
     * @param myUsuario
     * @return
     * @throws IOException 
     */
    public static String cambiarContrasena(Usuario myUsuario) throws IOException, Exception{
        
	SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);
	Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	WebTarget resourceTarget= myClient.target(PrimoURI.CHG_PASS_WS);
	Builder invocationBuilder = resourceTarget.request();
        Response response= invocationBuilder.put(Entity.json(myUsuario));
        String myresultado = response.readEntity(String.class);
        return myresultado;
    }
}