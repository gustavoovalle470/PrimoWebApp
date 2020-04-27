/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.ws;

import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

/**
 *
 * @author OvalleGA
 */
public class PrimoWSBuilder {
    public static WebTarget getContext(String url) throws NoSuchAlgorithmException{
        SSLContext ctx = SSLContext.getDefault();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        ClientBuilder builder = ClientBuilder.newBuilder().sslContext(ctx);
        Client myClient = builder.hostnameVerifier(hostnameVerifier).build();
	return myClient.target(url);
    }
}
