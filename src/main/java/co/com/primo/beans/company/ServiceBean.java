/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.beans.company;

import co.com.primo.model.Dominio;
import co.com.primo.model.Empresa;
import co.com.primo.model.Servicio;
import co.com.primo.model.Sucursal;
import co.com.primo.model.SucursalServicio;
import co.com.primo.ws.company.CompanyWSClient;
import co.com.primo.ws.dominio.DominioWSClient;
import co.com.primo.ws.sucursal.ServicioWSClient;
import co.com.primo.ws.sucursal.SucursalWSClient;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import co.com.primo.controllers.security.UserSessionManager;

/**
 *
 * @author OvalleGA
 */
@SessionScoped
@Named("ServiceBean")
public class ServiceBean implements Serializable{
    private Empresa company;
    private Sucursal sucursal;
    private Servicio service;
    private String service_category;
    private String service_price="0";
    private Dominio myDominio;
    private List<SucursalServicio>serviceBySucursal=new ArrayList();
    
    
    public ServiceBean(){
        System.out.println("Creacion...");
        //List<Empresa> myListEmpresa = CompanyWSClient.getCompany(UserSessionManager.getInstance().getUser((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getIdUsuario());
        //company=myListEmpresa.get(0);
        List<Sucursal> mySucursals = SucursalWSClient.getSucursal(company.getIdEmpresa());
        sucursal=mySucursals.get(0);
        this.myDominio = new Dominio();
        this.service=new Servicio();
        this.service.setIdServicio(BigInteger.ONE);
        this.service.setStrNombre("MONTAJE DE LLANTAS");
        SucursalServicio ss = new SucursalServicio();
        ss.setIdSucursalServicio(sucursal.getIdSucursal());
        ss.setMyServicio(service);
        ss.setMySucursal(sucursal);
        serviceBySucursal.add(ss);
    }

    public Servicio getService() {
        return service;
    }

    public void setService(Servicio service) {
        this.service = service;
    }
    
    public Empresa getCompany() {
        return company;
    }

    public void setCompany(Empresa company) {
        this.company = company;
    }

    public String getService_category() {
        return service_category;
    }

    public void setService_category(String service_category) {
        this.service_category = service_category;
    }

    public String getService_price() {
        return service_price;
    }

    public void setService_price(String service_price) {
        this.service_price = service_price;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Dominio getMyDominio() {
        return myDominio;
    }

    public void setMyDominio(Dominio myDominio) {
        this.myDominio = myDominio;
    }

    public List<SucursalServicio> getServiceBySucursal() {
        return serviceBySucursal;
    }

    public void setServiceBySucursal(List<SucursalServicio> serviceBySucursal) {
        this.serviceBySucursal = serviceBySucursal;
    }
    
    public void saveService(){
        try {
            System.out.println("A crear sercicio para la sucursal: "+sucursal.getIdSucursal()+" Servicio: "+service.getIdServicio()+" Precio: "+service_price);
           SucursalWSClient.addService(sucursal.getIdSucursal(), service.getIdServicio(), service_price);
        } catch (Exception ex) {
            System.err.println("Error: "+ex.getCause());
        }
    }
    
    public List<SelectItem> obtenerListadoDominioTipo(String myIdTipoDominio) {
        //Atributos de Método
        List<SelectItem> myListSelectItems= new ArrayList<>();
                    
        try {
            //Traer la Lista de Dominios
            List<Dominio> myListDominio = DominioWSClient.traerDominiosPorTipo(new BigInteger(myIdTipoDominio));
            
            //Recorrer la lista de los dominios
            myListDominio.stream().map((myDominioTemp) -> {
                //Crear el Item
                SelectItem item = new SelectItem();
                item.setLabel(myDominioTemp.getStrDescripcion());
                item.setDescription(myDominioTemp.getStrDescripcion());
                item.setValue(myDominioTemp.getIdDominio());
                return item;                
            }).forEachOrdered((item) -> {
                //Adicionarlo en la lista
                myListSelectItems.add(item);
            });
        } catch (IOException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Retornar la lista de los tipos de Identificación
        return myListSelectItems;
    }
    
    public List<SelectItem> obtenerListadoServicios() {
        //Atributos de Método
        List<SelectItem> myListSelectItems= new ArrayList<>();
                    
        List<Servicio> myListService = ServicioWSClient.traerServicios();
        myListService.stream().map((myDominioTemp) -> {
            //Crear el Item
            SelectItem item = new SelectItem();
            item.setLabel(myDominioTemp.getStrNombre());
            item.setDescription(myDominioTemp.getStrNombre());
            item.setValue(myDominioTemp.getIdServicio());
            return item;
        }).forEachOrdered((item) -> {
            //Adicionarlo en la lista
            myListSelectItems.add(item);
        });

        //Retornar la lista de los tipos de Identificación
        return myListSelectItems;
    }
}
