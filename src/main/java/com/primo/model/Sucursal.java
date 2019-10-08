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
 * Clase que representa el Objeto Sucursal
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 15/08/2019
 */
public class Sucursal implements Serializable {

    /** Atributos de Clase **/
    private BigInteger idSucursal;
    private String strNombre;
    private boolean bitPrincipal;
    private Empresa myEmpresa;

    /**
     * Constructor vacio de la Clase Sucursal
     */
    public Sucursal(){
        super();
    }
    
    /**
     * Constructor de la Clase Sucursal
     * @param strNombre
     * @param bitPrincipal
     * @param myEmpresa 
     */
    public Sucursal(String strNombre, boolean bitPrincipal, Empresa myEmpresa){
        this.strNombre = strNombre;
        this.bitPrincipal = bitPrincipal;
        this.myEmpresa = myEmpresa;
    }

    /**
     * @return the idSucursal
     */
    public BigInteger getIdSucursal() {
        return idSucursal;
    }

    /**
     * @return the strNombre
     */
    public String getStrNombre() {
        return strNombre;
    }

    /**
     * @return the bitPrincipal
     */
    public boolean isBitPrincipal() {
        return bitPrincipal;
    }

    /**
     * @return the myEmpresa
     */
    public Empresa getMyEmpresa() {
        return myEmpresa;
    }

    /**
     * @param idSucursal the idSucursal to set
     */
    public void setIdSucursal(BigInteger idSucursal) {
        this.idSucursal = idSucursal;
    }

    /**
     * @param strNombre the strNombre to set
     */
    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    /**
     * @param bitPrincipal the bitPrincipal to set
     */
    public void setBitPrincipal(boolean bitPrincipal) {
        this.bitPrincipal = bitPrincipal;
    }

    /**
     * @param myEmpresa the myEmpresa to set
     */
    public void setMyEmpresa(Empresa myEmpresa) {
        this.myEmpresa = myEmpresa;
    }
}