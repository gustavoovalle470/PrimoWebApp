/*
 * PACKAGE
 */
package co.com.primo.model;

/*
 * IMPORTS
 */
import java.math.BigInteger;

/**
 * Clase que representa el Objeto Dominio
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 07/08/2019
 */
public class Dominio {
    
    /** Atributos de Clase **/
    private BigInteger idDominio;

    
    private String strDescripcion;
    

    private TipoDominio myTipoDominio;
    
    private Dominio myDominioPadre;

    
    /**
     * Constructor vacio de la Clase
     */
    public Dominio(){
        super();
    }

    /**
     * Constructor
     */
    public Dominio(BigInteger idDominio){
        this.idDominio = idDominio;
    }
    
    /**
     * @return the idDominio
     */
    public BigInteger getIdDominio() {
        return idDominio;
    }

    /**
     * @return the strDescripcion
     */
    public String getStrDescripcion() {
        return strDescripcion;
    }

    /**
     * @return the myTipoDominio
     */
    public TipoDominio getMyTipoDominio() {
        return myTipoDominio;
    }

    /**
     * @return the myDominioPadre
     */
    public Dominio getMyDominioPadre() {
        return myDominioPadre;
    }

    /**
     * @param idDominio the idDominio to set
     */
    public void setIdDominio(BigInteger idDominio) {
        this.idDominio = idDominio;
    }

    /**
     * @param strDescripcion the strDescripcion to set
     */
    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    /**
     * @param myTipoDominio the myTipoDominio to set
     */
    public void setMyTipoDominio(TipoDominio myTipoDominio) {
        this.myTipoDominio = myTipoDominio;
    }

    /**
     * @param myDominioPadre the myDominioPadre to set
     */
    public void setMyDominioPadre(Dominio myDominioPadre) {
        this.myDominioPadre = myDominioPadre;
    }

}
