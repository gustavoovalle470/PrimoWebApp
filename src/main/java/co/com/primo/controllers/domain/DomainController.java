/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.controllers.domain;

import co.com.primo.beans.company.CompanyBean;
import co.com.primo.model.Dominio;
import co.com.primo.ws.dominio.DominioWSClient;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;

/**
 *
 * @author OvalleGA
 */
public class DomainController {
    /**
     * Metodo que obtiene la informacion de la lista de dominios de acuerdo al tipo
     * @param myIdTipoDominio
     * @return myListSelectItems
     */

    public static List<SelectItem> obtenerListadoDominioTipo(String myIdTipoDominio) {
        List<SelectItem> myListSelectItems= new ArrayList<>();
        try {
            List<Dominio> myListDominio = DominioWSClient.traerDominiosPorTipo(new BigInteger(myIdTipoDominio));
            myListDominio.stream().map((myDominioTemp) -> {
                SelectItem item = new SelectItem();
                item.setLabel(myDominioTemp.getStrDescripcion());
                item.setDescription(myDominioTemp.getStrDescripcion());
                item.setValue(myDominioTemp.getIdDominio());
                return item;                
            }).forEachOrdered((item) -> {
                myListSelectItems.add(item);
            });
        } catch (IOException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myListSelectItems;
    }
    
    public static List<SelectItem> obtenerListadoDominioPadre(String myIdTipoDominio) {
        List<SelectItem> myListSelectItems= new ArrayList<>();
        try {
            List<Dominio> myListDominio = DominioWSClient.traerDominiosPorPadre(new BigInteger(myIdTipoDominio));
            myListDominio.stream().map((myDominioTemp) -> {
                SelectItem item = new SelectItem();
                item.setLabel(myDominioTemp.getStrDescripcion());
                item.setDescription(myDominioTemp.getStrDescripcion());
                item.setValue(myDominioTemp.getIdDominio());
                return item;                
            }).forEachOrdered((item) -> {
                myListSelectItems.add(item);
            });
        } catch (IOException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myListSelectItems;
    }
    
    public static Dominio getDomainByType(BigInteger domainId, BigInteger myIdTipoDominio) throws IOException{
        List<Dominio> myListDominio = DominioWSClient.traerDominiosPorTipo(myIdTipoDominio);
        for(Dominio d: myListDominio){
            if(d.getIdDominio().equals(domainId)){
                return d;
            }
        }
        return null;
    }
    
    public static Dominio getDomainByParent(BigInteger domainId, BigInteger myIdTipoDominio) throws IOException{
        List<Dominio> myListDominio = DominioWSClient.traerDominiosPorPadre(new BigInteger(""+domainId));
        for(Dominio d: myListDominio){
            if(d.getIdDominio().equals(myIdTipoDominio)){
                return d;
            }
        }
        return null;
    }
}
