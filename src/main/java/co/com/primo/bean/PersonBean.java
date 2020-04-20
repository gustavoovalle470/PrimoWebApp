/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.bean;

import co.com.primo.client.persona.PersonaWSClient;
import co.com.primo.model.Persona;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String person_name;
    private String person_surname;
    private String person_document;
    
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

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_surname() {
        return person_surname;
    }

    public void setPerson_surname(String person_surname) {
        this.person_surname = person_surname;
    }

    public String getPerson_document() {
        return person_document;
    }

    public void setPerson_document(String person_document) {
        this.person_document = person_document;
    }
    
    public void cleanField(){
        setPerson_name(null);
        setPerson_surname(null);
        setPerson_document(null);
    }
    
    public void createPerson(){
        System.out.println("HOLA");
        System.out.println("NOMBRE: "+person_name);
        System.out.println("APELLIDO: "+person_surname);
        System.out.println("DOCUMENTO: "+person_document);
        if(person_name != null && !person_name.equals("") &&
           person_surname != null && !person_surname.equals("") &&
           person_document != null && !person_document.equals("")){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                                            Locale.ENGLISH);
                SimpleDateFormat d = new SimpleDateFormat("yyyy-mm-dd");
                Persona p = new Persona();
                p.setBitActivo(true);
                p.setDtmFechaCreacion(d.parse("2019-03-12"));
                p.setStrNombre(person_name);
                p.setStrApellido(person_surname);
                p.setStrNumeroDocumento(person_document);
                PersonaWSClient.getInstance().register(p);
                cleanField();
                persona = PersonaWSClient.getInstance().getAllPersons();
            } catch (IOException | ParseException ex) {
                Logger.getLogger(PersonBean.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                cleanField();
            }
        }
    }
}
