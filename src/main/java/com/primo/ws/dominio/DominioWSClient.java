/*
 * PACKAGE
 */
package com.primo.ws.dominio;

/*
 * IMPORTS
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Dominio;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el cliente de los Servicios Web para valores del Dominio
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 26/09/2019
 */
public class DominioWSClient {
    
    /**
     * Método que encarga de traer la información de los dominios de acuerdo al tipo
     * @param myIdTipoDominio
     * @return myListDominio
     * @throws IOException 
     */
    public static List<Dominio> traerDominiosPorTipo(BigInteger myIdTipoDominio) throws IOException{
        
        //Atributos de Metodo
        Gson myGson = new Gson();
        
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.GET_DOM_WS + myIdTipoDominio;
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        List<Dominio> myListDominio = myGson.fromJson(myStringList, new TypeToken<List<Dominio>>(){}.getType());
        myClient.close();
        return myListDominio;
    }

    /**
     * Método que encarga de traer la información de los dominios de acuerdo al Padre
     * @param myIdDominioPadre
     * @return myListDominio
     * @throws IOException 
     */
    public static List<Dominio> traerDominiosPorPadre(BigInteger myIdDominioPadre) throws IOException{
        
        //Atributos de Metodo
        Gson myGson = new Gson();
        
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.GET_DOM_WS + "/padre/" + myIdDominioPadre;
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        List<Dominio> myListDominio = myGson.fromJson(myStringList, new TypeToken<List<Dominio>>(){}.getType());
        myClient.close();
        return myListDominio;
    }
}