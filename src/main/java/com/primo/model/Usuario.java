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
import javax.persistence.Table;

/**
 * Clase que representa el Objeto Usuario
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 17/07/2019
 */

@Entity
@Table(name="usuario")
public class Usuario {

    /** Atributos de Clase **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger idUsuario;
    
    @Column
    private String strUsuario;
    
    @Column
    private String strPassword;
    
    @Column
    private int intNumIntentos;
    
    @Column
    private boolean bitActivo;

    /**
     * @return the idUsuario
     */
    public BigInteger getIdUsuario() {
        return idUsuario;
    }

    /**
     * @return the strUsuario
     */
    public String getStrUsuario() {
        return strUsuario;
    }

    /**
     * @return the strPassword
     */
    public String getStrPassword() {
        return strPassword;
    }

    /**
     * @return the intNumIntentos
     */
    public int getIntNumIntentos() {
        return intNumIntentos;
    }

    /**
     * @return the bitActivo
     */
    public boolean isBitActivo() {
        return bitActivo;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(BigInteger idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @param strUsuario the strUsuario to set
     */
    public void setStrUsuario(String strUsuario) {
        this.strUsuario = strUsuario;
    }

    /**
     * @param strPassword the strPassword to set
     */
    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    /**
     * @param intNumIntentos the intNumIntentos to set
     */
    public void setIntNumIntentos(int intNumIntentos) {
        this.intNumIntentos = intNumIntentos;
    }

    /**
     * @param bitActivo the bitActivo to set
     */
    public void setBitActivo(boolean bitActivo) {
        this.bitActivo = bitActivo;
    }
}