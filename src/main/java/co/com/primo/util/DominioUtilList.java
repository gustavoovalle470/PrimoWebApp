/*
 * PACKAGE
 */
package co.com.primo.util;

/*
 * IMPORTS
 */
import com.primo.model.Dominio;
import java.util.List;

/**
 * Clase que implementa la utilidad de lista para Dominios
 * @author Mauricio Alejandro Rocuts
 * @version 1.0
 * @date 29/09/2019
 */
public class DominioUtilList {
   
    /** Atributos de Clase **/
    private List<Dominio> myListDominio;
    
    /**
     * @return the myListDominio
     */
    public List<Dominio> getMyListDominio() {
        return myListDominio;
    }

    /**
     * @param myListDominio the myListDominio to set
     */
    public void setMyListDominio(List<Dominio> myListDominio) {
        this.myListDominio = myListDominio;
    }
}