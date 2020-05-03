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
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author OvalleGA
 */
@ViewScoped
@ManagedBean(name="ServiceByBranchBean")
public class ServiceByBranchBean {
    
    private final HttpSession session;
    private PRCompany prcompany;
    private DualListModel<String> branches;
    private Sucursal branchSelected;
    
    
    public ServiceByBranchBean(){
        this.session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.prcompany=UserSessionManager.getInstance().getCompanyContainer(session);
        branches = new DualListModel<>(getListBranches(), 
                                       getListBranchesAsgined());
        cleanBranchesAssigned();
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
    
    public void onTransfer(TransferEvent event){
        for(Object s: event.getItems()){
            branchSelected = getBranchSelect((String)s);
            if(event.isRemove()){
                removeService();
            }
            if(event.isAdd()){
                assignService();
            }
        }
    }
    
    private List<String> getListBranches(){
        List<String>listBranches=new ArrayList<>();
        for(Sucursal s:UserSessionManager.getInstance().getCompanyContainer(session).getBranchOffices()){
            listBranches.add(s.getStrNombre());
        }
        return listBranches;
    }

    private List<String> getListBranchesAsgined(){
        List<String>listBranches=new ArrayList<>();
        try {
            for(Sucursal s:UserSessionManager.getInstance().getBranchesByService(session)){
                listBranches.add(s.getStrNombre());
            }
        } catch (NoSuchAlgorithmException ex) {
            UIMessageManagement.putException(ex);
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
    
    private void assignService(){
        try {
            SucursalServicio sServicio=new SucursalServicio();
            sServicio.setMyServicio(prcompany.getServiceSelected());
            sServicio.setMySucursal(branchSelected);
            sServicio.setDblValor(0);
            SucursalWSClient.assignService(sServicio);
            UIMessageManagement.putInfoMessage("Se asigno el servicio "+prcompany.getServiceSelected().getStrnombre()+" a la sucrusal "+branchSelected.getStrNombre());
        } catch (Exception ex) {
            UIMessageManagement.putException(ex);
        }
    }
    
    private void removeService() {
        try {
            SucursalServicio sServicio=new SucursalServicio();
            sServicio.setMyServicio(prcompany.getServiceSelected());
            sServicio.setMySucursal(branchSelected);
            sServicio.setDblValor(0);
            SucursalWSClient.removeService(sServicio);
            UIMessageManagement.putInfoMessage("Se elimino el servicio "+prcompany.getServiceSelected().getStrnombre()+" a la sucrusal "+branchSelected.getStrNombre());
        } catch (Exception ex) {
            UIMessageManagement.putException(ex);
        }
    }
    private void cleanBranchesAssigned() {
        for(String s: branches.getTarget()){
            branches.getSource().remove(s);
        }        
    }
}
