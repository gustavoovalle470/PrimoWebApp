/*
 * PACKAGE
 */
package com.primo.model;

/*
 * IMPORTS
 */
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;

/**
 * Clase que representa el Objeto Empresa
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 12/08/2019
 */

public class Empresa implements Serializable {
    
    /** Atributos de Clase **/
    private BigInteger idEmpresa;
    
    
    private String strIdentificacion;
    
    
    
    private String strRazonSocial;
    
    
    private String dtmFechaFundacion;
    
    
    private Blob imgLogo;
    
    private Usuario myUsuario;
    
    private Dominio myDominio;

    /**
     * @return the idEmpresa
     */
    public BigInteger getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @return the strIdentificacion
     */
    public String getStrIdentificacion() {
        return strIdentificacion;
    }

    /**
     * @return the strRazonSocial
     */
    public String getStrRazonSocial() {
        return strRazonSocial;
    }

    /**
     * @return the dtmFechaFundacion
     */
    public String getDtmFechaFundacion() {
        return dtmFechaFundacion;
    }

    /**
     * @return the imgLogo
     */
    public Blob getImgLogo() {
        return imgLogo;
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
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(BigInteger idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @param strIdentificacion the strIdentificacion to set
     */
    public void setStrIdentificacion(String strIdentificacion) {
        this.strIdentificacion = strIdentificacion;
    }

    /**
     * @param strRazonSocial the strRazonSocial to set
     */
    public void setStrRazonSocial(String strRazonSocial) {
        this.strRazonSocial = strRazonSocial;
    }

    /**
     * @param dtmFechaFundacion the dtmFechaFundacion to set
     */
    public void setDtmFechaFundacion(String dtmFechaFundacion) {
        this.dtmFechaFundacion = dtmFechaFundacion;
    }

    /**
     * @param imgLogo the imgLogo to set
     */
    public void setImgLogo(Blob imgLogo) {
        this.imgLogo = imgLogo;
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