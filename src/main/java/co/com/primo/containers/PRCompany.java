/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.containers;

import co.com.primo.model.Contacto;
import co.com.primo.model.Empresa;
import co.com.primo.model.Servicio;
import co.com.primo.model.Sucursal;
import co.com.primo.model.Usuario;
import java.util.List;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author OvalleGA
 */
public class PRCompany {
    private Usuario user;
    private Empresa company;
    private Contacto contact;
    private Sucursal branchConnected;
    private Servicio serviceSelected;
    private List<Sucursal> branchOffices;
    private List<Servicio> services;

    public Sucursal getBranchConnected() {
        return branchConnected;
    }

    public void setBranchConnected(Sucursal branchConnected) {
        this.branchConnected = branchConnected;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Empresa getCompany() {
        return company;
    }

    public void setCompany(Empresa company) {
        this.company = company;
    }

    public List<Servicio> getServices() {
        return services;
    }

    public void setServices(List<Servicio> services) {
        this.services = services;
    }

    public Contacto getContact() {
        return contact;
    }

    public void setContact(Contacto contact) {
        this.contact = contact;
    }

    public List<Sucursal> getBranchOffices() {
        return branchOffices;
    }

    public void setBranchOffices(List<Sucursal> branchOffices) {
        this.branchOffices = branchOffices;
    }

    public Servicio getServiceSelected() {
        return serviceSelected;
    }

    public void setServiceSelected(Servicio serviceSelected) {
        this.serviceSelected = serviceSelected;
    }

    public String getBranchName(){
        if(branchOffices != null && !branchOffices.isEmpty()){
            return branchOffices.get(0).getStrNombre();
        }else{
            return "REGISTRE UNA SUCURSAL";
        }
    }

    public void addSucursal(Sucursal s) {
        branchOffices.add(s);
    }

    public Sucursal getBranch(LatLng latlng) {
        for(Sucursal s :branchOffices){
            if(s.getLatitud().equals(""+latlng.getLat()) && s.getLongitud().equals(""+latlng.getLng())){
                return s;
            }
        }
        return null;
    }
    
    public void connectBranch(Sucursal conectBranch){
        this.branchConnected=conectBranch;
    }

    public boolean isRegisterService(Servicio serviceToRegister) {
        for(Servicio s: services){
            System.out.println("COMPARANDO: "+s.getStrnombre().toUpperCase()+" VS "+serviceToRegister.getStrnombre().toUpperCase());
            if(s.getStrnombre().toUpperCase().equals(serviceToRegister.getStrnombre().toUpperCase())) 
                return true;
        }
        return false;
    }
}
