/*
 * PACKAGE
 */
package co.com.primo.ws.sucursal;

/*
 * IMPORTS
 */
import co.com.primo.model.Empresa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import co.com.primo.ws.constants.PrimoURI;
import co.com.primo.model.Servicio;
import co.com.primo.ws.PrimoMsg;
import co.com.primo.ws.PrimoWSBuilder;
import com.google.gson.GsonBuilder;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el cliente de los Servicios Web del objeto Servicio
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 09/10/2019
 */
public class ServicioWSClient {
    
    public static void saveService(Servicio service) throws NoSuchAlgorithmException, Exception{
        WebTarget resourceTarget= PrimoWSBuilder.getContext(PrimoURI.REG_SERV_WS);
	Invocation.Builder invocationBuilder = resourceTarget.request();
        PrimoMsg respuesta=invocationBuilder.post(Entity.json(service)).readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }
    
    public static List<Servicio> traerServicios(Empresa empresa) {
        Gson myGson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();;
        Client myClient = ClientBuilder.newClient();
        System.out.println("URL: "+PrimoURI.GET_SERV_COMP_WS.replace(PrimoURI.URL_CHAR_CHANGE, ""+empresa.getIdEmpresa()));
        String myStringList = myClient.target(PrimoURI.GET_SERV_COMP_WS.replace(PrimoURI.URL_CHAR_CHANGE, ""+empresa.getIdEmpresa())).request(MediaType.APPLICATION_JSON).get(String.class);
        List<Servicio> myListDominio = myGson.fromJson(myStringList, new TypeToken<List<Servicio>>(){}.getType());
        myClient.close();
        return myListDominio;
    }

    public static void updateSercice(Servicio service) throws Exception {
        WebTarget resourceTarget= PrimoWSBuilder.getContext(PrimoURI.UPD_SER_WS);
	Invocation.Builder invocationBuilder = resourceTarget.request();
        PrimoMsg respuesta=invocationBuilder.post(Entity.json(service)).readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }
}
