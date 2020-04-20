/*
 * PACKAGE
 */
package co.com.primo.model;

/*
 * IMPORTS
 */
import java.math.BigInteger;

/**
 * Clase que representa el Objeto Usuario
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 17/07/2019
 */

public class Usuario {

    /** Atributos de Clase **/
    private BigInteger idUsuario;
    private BigInteger intTipoUsuario;
    private String strUsuario;
    private String strPassword;
    private int intNumIntentos;
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

    /**
     * @return the intTipoUsuario
     */
    public BigInteger getIntTipoUsuario() {
        return intTipoUsuario;
    }

    /**
     * @param intTipoUsuario the intTipoUsuario to set
     */
    public void setIntTipoUsuario(BigInteger intTipoUsuario) {
        this.intTipoUsuario = intTipoUsuario;
    }
}