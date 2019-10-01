/*
 * PACKAGE
 */
package co.com.primo.ws.dominio;

import co.com.primo.util.DominioUtilList;
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Dominio;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.GET_DOM_WS + myIdTipoDominio;
        DominioUtilList myDominioUtilList = myClient.target(myURL).request(MediaType.APPLICATION_JSON_TYPE).get(DominioUtilList.class);
        List<Dominio> myListDominio = myDominioUtilList.getMyListDominio();
        System.out.println("Tamaño Lista : " + myListDominio.size());
        myClient.close();
        return myListDominio;
    }
}