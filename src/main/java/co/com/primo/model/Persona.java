/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Usuario
 */

public class Persona implements Serializable{
    
    /** Atributos de Clase **/
    private BigInteger idPersona;
    
    
    private String strNumeroDocumento;
    
    
    private String strNombre;
    
    
    private String strApellido;
    
    
    private boolean bitActivo;
    
    
    private Date dtmFechaCreacion;

    /**
     * @return the idPersona
     */
    public BigInteger getIdPersona() {
        return idPersona;
    }

    /**
     * @return the strNumeroDocumento
     */
    public String getStrNumeroDocumento() {
        return strNumeroDocumento;
    }

    /**
     * @return the strNombre
     */
    public String getStrNombre() {
        return strNombre;
    }

    /**
     * @return the strApellido
     */
    public String getStrApellido() {
        return strApellido;
    }

    /**
     * @return the bitActivo
     */
    public boolean isBitActivo() {
        return bitActivo;
    }

    /**
     * @return the dtmFechaCreacion
     */
    public Date getDtmFechaCreacion() {
        return dtmFechaCreacion;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(BigInteger idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @param strNumeroDocumento the strNumeroDocumento to set
     */
    public void setStrNumeroDocumento(String strNumeroDocumento) {
        this.strNumeroDocumento = strNumeroDocumento;
    }

    /**
     * @param strNombre the strNombre to set
     */
    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    /**
     * @param strApellido the strApellido to set
     */
    public void setStrApellido(String strApellido) {
        this.strApellido = strApellido;
    }

    /**
     * @param bitActivo the bitActivo to set
     */
    public void setBitActivo(boolean bitActivo) {
        this.bitActivo = bitActivo;
    }

    /**
     * @param dtmFechaCreacion the dtmFechaCreacion to set
     */
    public void setDtmFechaCreacion(Date dtmFechaCreacion) {
        this.dtmFechaCreacion = dtmFechaCreacion;
    }
}
