/*
 * PACKAGE
 */
package co.com.primo.ws.contacto;

/*
 * IMPORTS
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import co.com.primo.ws.constants.PrimoURI;
import co.com.primo.model.Contacto;
import co.com.primo.model.Empresa;
import co.com.primo.ws.PrimoMsg;
import co.com.primo.ws.PrimoWSBuilder;
import com.google.gson.GsonBuilder;
import java.io.IOException;
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
import org.apache.http.HttpStatus;
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
	WebTarget resourceTarget= PrimoWSBuilder.getContext(PrimoURI.REG_CONT_WS);
        Invocation.Builder invocationBuilder = resourceTarget.request();
        PrimoMsg respuesta=invocationBuilder.post(Entity.json(myContacto)).readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }
    
    public static List<Contacto>getContact(BigInteger companyId){
        Gson myGson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.GET_CONT_WS.replace(PrimoURI.URL_CHAR_CHANGE, companyId.toString());
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        List<Contacto> result = myGson.fromJson(myStringList, new TypeToken<List<Contacto>>(){}.getType());
        myClient.close();
        return result;
    }
    
    public static void updateContact(Contacto contact) throws NoSuchAlgorithmException, Exception {
        WebTarget resourceTarget= PrimoWSBuilder.getContext(PrimoURI.UPD_CONT_WS);
        Invocation.Builder invocationBuilder = resourceTarget.request();
        PrimoMsg respuesta=invocationBuilder.post(Entity.json(contact)).readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
    }
}
