/*
 * PACKAGE
 */
package com.primo.model;

/*
 * IMPORTS
 */
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
 * Clase que representa el Objeto Log
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 07/08/2019
 */

public class Log {
    
    /** Atributos de Clase **/

    private BigInteger idLog;
    
    
    private String dtmFecha;
    
    
    private String strAccion;
    

    private Usuario myUsuario;
  
    private Dominio myDominio;

    /**
     * @return the idLog
     */
    public BigInteger getIdLog() {
        return idLog;
    }

    /**
     * @return the dtmFecha
     */
    public String getDtmFecha() {
        return dtmFecha;
    }

    /**
     * @return the strAccion
     */
    public String getStrAccion() {
        return strAccion;
    }

    /**
     * @return the myUsuario
     */
    public Usuario getMyUsuario() {
        return myUsuario;
    }

    /**
     * @return the myDominio
     */
    public Dominio getMyDominio() {
        return myDominio;
    }

    /**
     * @param idLog the idLog to set
     */
    public void setIdLog(BigInteger idLog) {
        this.idLog = idLog;
    }

    /**
     * @param dtmFecha the dtmFecha to set
     */
    public void setDtmFecha(String dtmFecha) {
        this.dtmFecha = dtmFecha;
    }

    /**
     * @param strAccion the strAccion to set
     */
    public void setStrAccion(String strAccion) {
        this.strAccion = strAccion;
    }

    /**
     * @param myUsuario the myUsuario to set
     */
    public void setMyUsuario(Usuario myUsuario) {
        this.myUsuario = myUsuario;
    }

    /**
     * @param myDominio the myDominio to set
     */
    public void setMyDominio(Dominio myDominio) {
        this.myDominio = myDominio;
    }

}
