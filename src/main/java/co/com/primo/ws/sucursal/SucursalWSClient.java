/*
 * PACKAGE
 */
package co.com.primo.ws.sucursal;

/*
 * IMPORTS
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import co.com.primo.ws.constants.PrimoURI;
import co.com.primo.model.Sucursal;
import co.com.primo.ws.PrimoMsg;
import co.com.primo.ws.PrimoWSBuilder;
import com.google.gson.GsonBuilder;
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
        WebTarget resourceTarget= PrimoWSBuilder.getContext(PrimoURI.REG_SUC_WS);
	Invocation.Builder invocationBuilder = resourceTarget.request();
        PrimoMsg respuesta=invocationBuilder.post(Entity.json(mySucursal)).readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            System.out.println("PRUEBA");
            throw new Exception(respuesta.getResponse());
        }
        return mySucursal;
    }
    
    public static List<Sucursal> getSucursal(BigInteger id_company){
        //Atributos de Metodo
        Gson myGson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        Client myClient = ClientBuilder.newClient();
        
        //Traer la información de la empresa
        String myURL = PrimoURI.REG_SUC_WS +"/"+ id_company;
        String myStringList = myClient.target(myURL).request(MediaType.APPLICATION_JSON).get(String.class);
        
        List<Sucursal> myListDominio = myGson.fromJson(myStringList, new TypeToken<List<Sucursal>>(){}.getType());
        myClient.close();
        
        //Retornar la información de las empresas
        return myListDominio;
    }

    public static void addService(BigInteger idSucursal, BigInteger idService, String service_price) throws NoSuchAlgorithmException {
        //Crear Contexto
        SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);

        //Crear el cliente
        Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	WebTarget resourceTarget= myClient.target(PrimoURI.REG_SERV_WS+"/"+idSucursal+"/"+idService+"/"+service_price);
	
        //Invocar el servicio 
        Invocation.Builder invocationBuilder = resourceTarget.request();
        invocationBuilder.get();
    }

    public static Sucursal updateSucursal(Sucursal mySucursal) throws NoSuchAlgorithmException, Exception {
        WebTarget resourceTarget= PrimoWSBuilder.getContext(PrimoURI.UPD_SUC_WS);
	Invocation.Builder invocationBuilder = resourceTarget.request();
        PrimoMsg respuesta=invocationBuilder.post(Entity.json(mySucursal)).readEntity(PrimoMsg.class);
        if(!respuesta.isSucces()){
            throw new Exception(respuesta.getResponse());
        }
        return mySucursal;
    }
}
