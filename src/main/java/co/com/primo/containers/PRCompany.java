/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.containers;

import co.com.primo.model.Contacto;
import co.com.primo.model.Empresa;
import co.com.primo.model.Sucursal;
import co.com.primo.model.SucursalServicio;
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
    private List<Sucursal> branchOffices;
    private List<SucursalServicio> services;

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

    public List<SucursalServicio> getServices() {
        return services;
    }

    public void setServices(List<SucursalServicio> services) {
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
}
