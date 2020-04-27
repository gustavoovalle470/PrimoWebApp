/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author OvalleGA
 */
public class Servicio implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private BigInteger idservicio;
    private String strnombre;
    private Dominio iddominio;
    private Empresa idempresa;
    private boolean bitActivo;
    
    public Servicio() {
    }

    public Servicio(BigInteger idservicio) {
        this.idservicio = idservicio;
    }

    public Servicio(BigInteger idservicio, String strnombre) {
        this.idservicio = idservicio;
        this.strnombre = strnombre;
    }

    public BigInteger getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(BigInteger idservicio) {
        this.idservicio = idservicio;
    }

    public String getStrnombre() {
        return strnombre;
    }

    public void setStrnombre(String strnombre) {
        this.strnombre = strnombre;
    }

    public Dominio getIddominio() {
        return iddominio;
    }

    public void setIddominio(Dominio iddominio) {
        this.iddominio = iddominio;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }    

    public boolean isBitActivo() {
        return bitActivo;
    }

    public void setBitActivo(boolean bitActivo) {
        this.bitActivo = bitActivo;
    }
}
