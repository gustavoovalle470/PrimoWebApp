/*
 * PACKAGE
 */
package co.com.primo.model;

/*
 * IMPORTS
 */
import java.io.Serializable;
import java.math.BigInteger;

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
    private String latitud;
    private String longitud;
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
     * @param latitud
     * @param longitud
     * @param myEmpresa 
     */
    public Sucursal(String strNombre, boolean bitPrincipal, String latitud, 
                    String longitud, Empresa myEmpresa){
        this.strNombre = strNombre;
        this.bitPrincipal = bitPrincipal;
        this.myEmpresa = myEmpresa;
        this.latitud = latitud;
        this.longitud = latitud;
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