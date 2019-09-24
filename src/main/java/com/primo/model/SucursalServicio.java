/*
 * PACKAGE
 */
package com.primo.model;

/*
 * IMPORTS
 */
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Clase que representa el Objeto Sucursal Servicio
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 15/08/2019
 */

public class SucursalServicio implements Serializable {

    /** Atributos de Clase **/
    private BigInteger idSucursalServicio;
    

    private Sucursal mySucursal;

    private Servicio myServicio;

    /**
     * @return the idSucursalServicio
     */
    public BigInteger getIdSucursalServicio() {
        return idSucursalServicio;
    }

    /**
     * @return the mySucursal
     */
    public Sucursal getMySucursal() {
        return mySucursal;
    }

    /**
     * @return the myServicio
     */
    public Servicio getMyServicio() {
        return myServicio;
    }

    /**
     * @param idSucursalServicio the idSucursalServicio to set
     */
    public void setIdSucursalServicio(BigInteger idSucursalServicio) {
        this.idSucursalServicio = idSucursalServicio;
    }

    /**
     * @param mySucursal the mySucursal to set
     */
    public void setMySucursal(Sucursal mySucursal) {
        this.mySucursal = mySucursal;
    }

    /**
     * @param myServicio the myServicio to set
     */
    public void setMyServicio(Servicio myServicio) {
        this.myServicio = myServicio;
    }
}