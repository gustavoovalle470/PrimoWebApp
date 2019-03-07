/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primo.bean;

import com.primo.client.persona.PersonaWSClient;
import com.primo.model.Persona;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author OvalleGA
 */
@Named(value = "PersonBean")
@SessionScoped
public class PersonBean implements Serializable{
    private Persona[] persona;
    
    public PersonBean(){
        System.out.println("Ingrese...");
        persona = PersonaWSClient.getInstance().getAllPersons();
        System.out.println("Econtre "+persona.length+" personas.");
    }

    public Persona[] getPersona() {
        return persona;
    }

    public void setPersona(Persona[] persona) {
        this.persona = persona;
    }
}
