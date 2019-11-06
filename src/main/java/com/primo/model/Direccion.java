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
 * Clase que representa el Objeto Direccion
 * @author Mauricio Alejandro Rocuts
 * @version 1.1
 * @date 15/08/2019
 * 
 * Adicción de las columnas de Latitud y Longitud - Mauricio Rocuts - 05/11/2019
 */
public class Direccion implements Serializable {
   
    /** Atributos de Clase **/
    private BigInteger idDireccion;
    private String strDireccion;
    private String strDescripcion;
    private boolean bitPrincipal;
    private Dominio myDominio;
    private String latitud;
    private String longitud;
    
    /**
     * Constructor Vacio de la Clase Dirección
     */
    public Direccion(){
        super();
    }

    /**
     * Constructor de la Clase Dirección
     * @param strDireccion
     * @param strDescripcion
     * @param bitPrincipal
     * @param myDominio 
     * @param latitud
     * @param longitud
     */
    public Direccion(String strDireccion,String strDescripcion,boolean bitPrincipal,Dominio myDominio,
                     String latitud, String longitud){
        this.strDireccion = strDireccion;
        this.strDescripcion = strDescripcion;
        this.bitPrincipal = bitPrincipal;
        this.myDominio = myDominio;
        this.latitud = latitud;
        this.longitud = latitud;
    }

    /**
     * @return the idDireccion
     */
    public BigInteger getIdDireccion() {
        return idDireccion;
    }

    /**
     * @return the strDireccion
     */
    public String getStrDireccion() {
        return strDireccion;
    }

    /**
     * @return the strDescripcion
     */
    public String getStrDescripcion() {
        return strDescripcion;
    }

    /**
     * @return the bitPrincipal
     */
    public boolean isBitPrincipal() {
        return bitPrincipal;
    }

    /**
     * @return the myDominio
     */
    public Dominio getMyDominio() {
        return myDominio;
    }

    /**
     * @return the latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * @param idDireccion the idDireccion to set
     */
    public void setIdDireccion(BigInteger idDireccion) {
        this.idDireccion = idDireccion;
    }

    /**
     * @param strDireccion the strDireccion to set
     */
    public void setStrDireccion(String strDireccion) {
        this.strDireccion = strDireccion;
    }

    /**
     * @param strDescripcion the strDescripcion to set
     */
    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    /**
     * @param bitPrincipal the bitPrincipal to set
     */
    public void setBitPrincipal(boolean bitPrincipal) {
        this.bitPrincipal = bitPrincipal;
    }

    /**
     * @param myDominio the myDominio to set
     */
    public void setMyDominio(Dominio myDominio) {
        this.myDominio = myDominio;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
