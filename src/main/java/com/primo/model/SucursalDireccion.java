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
 * Clase que representa el Objeto Sucursal Direccion
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 15/08/2019
 */

public class SucursalDireccion implements Serializable {

    /** Atributos de Clase **/
    private BigInteger idSucursalDireccion;

    private Sucursal mySucursal;
   
    private Direccion myDireccion;

    /**
     * @return the idSucursalDireccion
     */
    public BigInteger getIdSucursalDireccion() {
        return idSucursalDireccion;
    }

    /**
     * @return the mySucursal
     */
    public Sucursal getMySucursal() {
        return mySucursal;
    }

    /**
     * @return the myDireccion
     */
    public Direccion getMyDireccion() {
        return myDireccion;
    }

    /**
     * @param idSucursalDireccion the idSucursalDireccion to set
     */
    public void setIdSucursalDireccion(BigInteger idSucursalDireccion) {
        this.idSucursalDireccion = idSucursalDireccion;
    }

    /**
     * @param mySucursal the mySucursal to set
     */
    public void setMySucursal(Sucursal mySucursal) {
        this.mySucursal = mySucursal;
    }

    /**
     * @param myDireccion the myDireccion to set
     */
    public void setMyDireccion(Direccion myDireccion) {
        this.myDireccion = myDireccion;
    }
}