/*
 * PACKAGE
 */
package com.primo.model;

/*
 * IMPORTS
 */
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa el Objeto Sucursal Telefono
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 15/08/2019
 */

public class SucursalTelefono implements Serializable {

    /** Atributos de Clase **/
    private BigInteger idSucursalTelefono;
    
    private Sucursal mySucursal;
    
    private Telefono myTelefono;

    /**
     * @return the idSucursalTelefono
     */
    public BigInteger getIdSucursalTelefono() {
        return idSucursalTelefono;
    }

    /**
     * @return the mySucursal
     */
    public Sucursal getMySucursal() {
        return mySucursal;
    }

    /**
     * @return the myTelefono
     */
    public Telefono getMyTelefono() {
        return myTelefono;
    }

    /**
     * @param idSucursalTelefono the idSucursalTelefono to set
     */
    public void setIdSucursalTelefono(BigInteger idSucursalTelefono) {
        this.idSucursalTelefono = idSucursalTelefono;
    }

    /**
     * @param mySucursal the mySucursal to set
     */
    public void setMySucursal(Sucursal mySucursal) {
        this.mySucursal = mySucursal;
    }

    /**
     * @param myTelefono the myTelefono to set
     */
    public void setMyTelefono(Telefono myTelefono) {
        this.myTelefono = myTelefono;
    }
}