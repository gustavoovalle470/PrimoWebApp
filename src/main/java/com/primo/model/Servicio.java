/*
 * PACKAGE
 */
package com.primo.model;

/*
 * IMPORTS
 */
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa el Objeto Servicio
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 15/08/2019
 */
public class Servicio implements Serializable {

    /** Atributos de Clase **/
    private BigInteger idServicio;

    
    private String strNombre;
    
    private Dominio myDominio;

    /**
     * @return the idServicio
     */
    public BigInteger getIdServicio() {
        return idServicio;
    }

    /**
     * @return the strNombre
     */
    public String getStrNombre() {
        return strNombre;
    }
    
    /**
     * @return the myDominio
     */
    public Dominio getMyDominio() {
        return myDominio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(BigInteger idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * @param strNombre the strNombre to set
     */
    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    /**
     * @param myDominio the myDominio to set
     */
    public void setMyDominio(Dominio myDominio) {
        this.myDominio = myDominio;
    }
}