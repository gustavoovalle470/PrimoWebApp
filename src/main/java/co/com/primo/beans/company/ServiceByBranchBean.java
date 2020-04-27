/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.beans.company;

import co.com.primo.containers.PRCompany;
import co.com.primo.controllers.security.UserSessionManager;
import co.com.primo.model.Sucursal;
import co.com.primo.model.SucursalServicio;
import co.com.primo.ui.utils.UIMessageManagement;
import co.com.primo.ws.sucursal.SucursalWSClient;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author OvalleGA
 */
@SessionScoped
@ManagedBean(name="ServiceByBranchBean")
public class ServiceByBranchBean {
    
    private final HttpSession session;
    private PRCompany prcompany;
    private DualListModel<String> branches;
    private Sucursal branchSelected;
    private Double priceService=(double)1;
    
    public ServiceByBranchBean(){
        this.session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.prcompany=UserSessionManager.getInstance().getCompanyContainer(session);
        branches = new DualListModel<>(getListBranches(), 
                                            new ArrayList<>());
    }

    public PRCompany getPrcompany() {
        return prcompany;
    }

    public void setPrcompany(PRCompany prcompany) {
        this.prcompany = prcompany;
    }

    public DualListModel<String> getBranches() {
        return branches;
    }

    public void setBranches(DualListModel<String> branches) {
        this.branches = branches;
    }

    public Sucursal getBranchSelected() {
        return branchSelected;
    }

    public void setBranchSelected(Sucursal branchSelected) {
        this.branchSelected = branchSelected;
    }

    public Double getPriceService() {
        return priceService;
    }

    public void setPriceService(Double priceService) {
        this.priceService = priceService;
    }
    
    public void onTransfer(TransferEvent event){
        System.out.println("BRANCH: "+(String) event.getItems().get(0)+" SIZE: "+event.getItems().size());
        branchSelected = getBranchSelect((String) event.getItems().get(0));
    }
    
    private List<String> getListBranches(){
        List<String>listBranches=new ArrayList<>();
        for(Sucursal s:UserSessionManager.getInstance().getCompanyContainer(session).getBranchOffices()){
            listBranches.add(s.getStrNombre());
        }
        return listBranches;
    }

    private Sucursal getBranchSelect(String nameSelected) {
        for(Sucursal s:UserSessionManager.getInstance().getCompanyContainer(session).getBranchOffices()){
            if(s.getStrNombre().equals(nameSelected)){
                System.out.println("LA ENCONTRE!!!!!");
                return s;
            }
        }
        return null;
    }
    
    public void assignService(){
        try {
            SucursalServicio sServicio=new SucursalServicio();
            sServicio.setMyServicio(prcompany.getServiceSelected());
            sServicio.setMySucursal(branchSelected);
            sServicio.setDblValor(priceService);
            SucursalWSClient.assignService(sServicio);
            UIMessageManagement.putInfoMessage("Se asigno el servicio "+prcompany.getServiceSelected().getStrnombre()+" a la sucrusal "+branchSelected.getStrNombre());
        } catch (Exception ex) {
            UIMessageManagement.putException(ex);
        }
    }
}
