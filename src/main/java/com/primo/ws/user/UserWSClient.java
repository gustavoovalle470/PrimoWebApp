/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primo.ws.user;

import com.google.gson.Gson;
import com.primo.constants.ws.PrimoURI;
import com.primo.model.Usuario;
import java.io.IOException;
import java.math.BigInteger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author OvalleGA
 */
public class UserWSClient {
    public static void registerUser(Usuario user) throws IOException, Exception{
        Gson gson = new Gson();
        StringEntity entity = new StringEntity(gson.toJson(user), ContentType.APPLICATION_JSON);
        System.out.println(entity);
        HttpPost request = new HttpPost(PrimoURI.REG_USER_WS);
        request.setEntity(entity);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
        String json = EntityUtils.toString(response.getEntity());
        BigInteger usuarioId = gson.fromJson(json, BigInteger.class);
        System.out.println("Usuario id= "+usuarioId);
        if(usuarioId==null){
            throw new Exception("Usuario ya registrado previamente.");
        }
    }
    
    public static Usuario login(Usuario myUusario) throws IOException{
        Client myClient = ClientBuilder.newClient();
        String myURL = PrimoURI.LOG_USER_WS + myUusario.getStrUsuario() + "/" + myUusario.getStrPassword();
        Usuario myUsuarioTemp = myClient.target(myURL).request(MediaType.APPLICATION_JSON_TYPE).get(Usuario.class);
        myClient.close();
        return myUsuarioTemp;
    }
}
