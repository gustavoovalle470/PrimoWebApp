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
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author OvalleGA
 */
public class UserWSClient {
    public static void registerUser(Usuario user) throws IOException{
        Gson gson = new Gson();
        StringEntity entity = new StringEntity(gson.toJson(user), ContentType.APPLICATION_JSON);
        System.out.println(entity);
        HttpPost request = new HttpPost(PrimoURI.REG_USER_WS);
        request.setEntity(entity);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
    }
}
