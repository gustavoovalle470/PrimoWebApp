/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primo.ws.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Dominio;
import com.primo.model.Empresa;
import com.primo.ws.PrimoMsg;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

/**
 *
 * @author OvalleGA
 */
public class CompanyWSClient {
    public static void registerCompany(Empresa empresa) throws NoSuchAlgorithmException, Exception {
        System.out.println("Iniciando");
        SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);
        System.out.println("Contexto");
	Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	WebTarget resourceTarget= myClient.target(PrimoURI.REG_COMP_WS);
	Builder invocationBuilder = resourceTarget.request();
	System.out.println("request");
        Response response= invocationBuilder.post(Entity.json(empresa));
        System.out.println("obteniendo respuesta");
        PrimoMsg respuesta=response.readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }
    
    /**
     * Método que se encarga de traer la información de las empresas por usuario
     * @param idUser
     * @return List<Empresa>
     */
    public static List<Empresa> getCompany(BigInteger idUser){
        //Atributos de Metodo
        Gson myGson = new Gson();
        Client myClient = ClientBuilder.newClient();
        
        //Traer la información de la empresa
        String myURL = PrimoURI.REG_COMP_WS +"/"+ idUser;
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        List<Empresa> myListDominio = myGson.fromJson(myStringList, new TypeToken<List<Empresa>>(){}.getType());
        myClient.close();
        
        //Retornar la información de las empresas
        return myListDominio;
    }
}
