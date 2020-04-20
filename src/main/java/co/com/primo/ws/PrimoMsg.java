/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.ws;

import java.io.Serializable;

/**
 *
 * @author OvalleGA
 */
public class PrimoMsg implements Serializable{
    private boolean succes;
    private String response;
    
    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
