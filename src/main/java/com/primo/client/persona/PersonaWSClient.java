/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primo.client.persona;

import com.primo.constants.ws.PrimoURI;
import com.primo.model.Persona;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Esta clase realiza el consumo del servicio web, expuesto que realiza las 
 * consultas a la base de datos de primos.
 * @author OvalleGA
 * @version 1.0
 * @since 07/03/2019
 * *****************************************************************************
 * *****************************************************************************
 * ************************  CONTROL DE CAMBIOS    *****************************
 * *****************************************************************************
 * *****************************************************************************
 * |        AUTOR       |   FECHA   |   VERSION    |        COMENTARIO         |
 * *****************************************************************************
 * |                    |           |              |Se realiza la creacion de  |
 * |       OVALLEGA     |07/03/2019 |   1.0        |esta clase que consume el  |
 * |                    |           |              |WS                         |
 * *****************************************************************************
 */
public class PersonaWSClient {
    private static PersonaWSClient instance; //Instancia actual de este controlador.
    private final Client client; //Cliente de consumo RESTFull.
    
    /**
     * Crea una nueva instancia de este controlador.
     */
    public PersonaWSClient(){
        client = ClientBuilder.newClient();
    }
    
    /**
     * Devuelve una instancia de este controlador.
     * @return PersonaWSClient la instancia actual de este controlador.
     */
    public static PersonaWSClient getInstance(){
        if(instance == null){
            instance = new PersonaWSClient();
        }
        return instance;
    }
    
    /**
     * Solicita via servicio web todas las personas guardadas en la base de datos.
     * @return Persona[] todas las personas en la base de datos.
     */
    public Persona[] getAllPersons(){
        return client.target(PrimoURI.ALL_PERSON_WS).request(MediaType.APPLICATION_JSON).get(Persona[].class);
    }
    
    /**
     * Retorna via servicio web la persona identificada con un ID entregado.
     * @param personId int el identificador de la persona a buscar.
     * @return Persona la persona identificada.
     */
    public Persona getPerson(int personId){
        return client.target(PrimoURI.GET_PERSON_WS.replace(PrimoURI.URL_CHAR_CHANGE, ""+personId)).request(MediaType.APPLICATION_JSON).get(Persona.class);
    }
}