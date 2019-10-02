/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.customize.UI.company;

import co.com.primo.ws.dominio.DominioWSClient;
import com.primo.model.Dominio;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.customize.UI.utils.UIMessageManagement;

/**
 *
 * @author OvalleGA
 */
@ManagedBean(name="CompanyBean")
@SessionScoped
public class CompanyBean {

    /** Atributos de Clase **/
    private String company_name;
    private String company_address;
    private int company_rate_primos;
    private int company_ranking_zone;
    private int company_ranking_service;
    private String company_status;
    private int company_client_attend;
    private int company_new_client;
    private int company_recurrent_client;
    private boolean company_is_register;
    private Dominio myDominio;

    public CompanyBean(){
        //Inicializar los Valores
        setDefaultValues();
    }
        
    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public int getCompany_rate_primos() {
        return company_rate_primos;
    }

    public void setCompany_rate_primos(int company_rate_primos) {
        this.company_rate_primos = company_rate_primos;
    }

    public int getCompany_ranking_zone() {
        return company_ranking_zone;
    }

    public void setCompany_ranking_zone(int company_ranking_zone) {
        this.company_ranking_zone = company_ranking_zone;
    }

    public int getCompany_ranking_service() {
        return company_ranking_service;
    }

    public void setCompany_ranking_service(int company_ranking_service) {
        this.company_ranking_service = company_ranking_service;
    }

    public String getCompany_status() {
        return company_status;
    }

    public void setCompany_status(String company_status) {
        this.company_status = company_status;
    }

    public int getCompany_client_attend() {
        return company_client_attend;
    }

    public void setCompany_client_attend(int company_client_attend) {
        this.company_client_attend = company_client_attend;
    }

    public int getCompany_new_client() {
        return company_new_client;
    }

    public void setCompany_new_client(int company_new_client) {
        this.company_new_client = company_new_client;
    }

    public int getCompany_recurrent_client() {
        return company_recurrent_client;
    }

    public void setCompany_recurrent_client(int company_recurrent_client) {
        this.company_recurrent_client = company_recurrent_client;
    }

    public boolean isCompany_is_register() {
        return company_is_register;
    }

    public void setCompany_is_register(boolean company_is_register) {
        this.company_is_register = company_is_register;
    }
    
    /**
     * @return the myDominio
     */
    public Dominio getMyDominio() {
        return myDominio;
    }

    /**
     * @param myDominio the myDominio to set
     */
    public void setMyDominio(Dominio myDominio) {
        this.myDominio = myDominio;
    }

    private void setDefaultValues(){
        company_name = "NOMBRE DE TU EMPRESA";
        company_address = "DIRECCION DE TU EMRPESA";
        company_rate_primos = 0;
        company_ranking_zone = 0;
        company_ranking_service = 0;
        company_status="close";
        company_client_attend=0;
        company_new_client=0;
        company_recurrent_client=0;
        company_is_register=false;
        this.myDominio = new Dominio();
    }
    
    public void change_status_company(){
        if(company_is_register){
            if(company_status.equals("open")){
                company_status = "close";
            }else{
                company_status = "open";
            }
        }else{
            UIMessageManagement.putCustomMessage(FacesMessage.SEVERITY_WARN, "Registro incompleto", "Debe completar el registro de su compañia para poder abrir.");
        }
    }
    
    public String save_company_info(){
        // SALVAR INFORMACION CON EL WS.
        return "";
    }
    
    /**
     * Metodo que obtiene la informacion de la lista de los tipos de Identificacion
     * @return myListSelectItems
     */
    public List<SelectItem> obtenerListadoTipoIdentificacion() {
        //Atributos de Método
        List<SelectItem> myListSelectItems= new ArrayList<SelectItem>();
                    
        try {
            //Traer la Lista de los Tipos de Identificación
            List<Dominio> myListDominio = DominioWSClient.traerDominiosPorTipo(new BigInteger("2"));
            System.out.println(myListDominio.size());
            //Recorrer la lista de los dominios
            for (Dominio myDominioTemp : myListDominio)
            {
                //Crear el Item
                SelectItem item = new SelectItem();
                item.setLabel(myDominioTemp.getStrDescripcion());
                item.setDescription(myDominioTemp.getStrDescripcion());
                item.setValue(myDominioTemp.getIdDominio());
                
                //Adicionarlo en la lista
                myListSelectItems.add(item);
            }
        } catch (IOException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Retornar la lista de los tipos de Identificación
        return myListSelectItems;
    }
}
