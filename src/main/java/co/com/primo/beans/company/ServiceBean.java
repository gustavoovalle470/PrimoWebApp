/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.beans.company;

import co.com.primo.containers.PRCompany;
import co.com.primo.controllers.domain.DomainController;
import co.com.primo.controllers.security.UserSessionManager;
import co.com.primo.model.Servicio;
import co.com.primo.ui.utils.UIMessageManagement;
import co.com.primo.ws.sucursal.SucursalWSClient;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author OvalleGA
 */
@ViewScoped
@ManagedBean(name="ServiceBean")
public class ServiceBean{
    private final HttpSession session;
    private PRCompany prcompany;
    private final String dominio="8";
    private BigInteger vehicleCategoryId=BigInteger.ONE;
    private BigInteger serviceCategoryId;
    private String serviceName;
    private List<SelectItem> allVehicleCategory=DomainController.obtenerListadoDominioTipo(dominio);
    private List<SelectItem> allServiceCategory=new ArrayList<>();
    
    public ServiceBean(){
        this.session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.prcompany=UserSessionManager.getInstance().getCompanyContainer(session);
    }

    public PRCompany getPrcompany() {
        return prcompany;
    }

    public void setPrcompany(PRCompany prcompany) {
        this.prcompany = prcompany;
    }

    public BigInteger getVehicleCategoryId() {
        return vehicleCategoryId;
    }

    public void setVehicleCategoryId(BigInteger vehicleCategoryId) {
        this.vehicleCategoryId = vehicleCategoryId;
    }

    public List<SelectItem> getAllVehicleCategory() {
        return allVehicleCategory;
    }

    public void setAllVehicleCategory(List<SelectItem> allVehicleCategory) {
        this.allVehicleCategory = allVehicleCategory;
    }

    public List<SelectItem> getAllServiceCategory() {
        return allServiceCategory;
    }

    public void setAllServiceCategory(List<SelectItem> allServiceCategory) {
        this.allServiceCategory = allServiceCategory;
    }

    public BigInteger getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(BigInteger serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public void updateServicesTypes(){
        allServiceCategory=DomainController.obtenerListadoDominioPadre(vehicleCategoryId.toString());
    }
    
    public int getBranchesCount(Servicio s){
        try {
            return UserSessionManager.getInstance().getBranchesByService(prcompany.getCompany(), s).size();
        } catch (NoSuchAlgorithmException ex) {
            UIMessageManagement.putException(ex);
        }
        return 0;
    }
    
    public void saveService(){
        try {
            Servicio s = new Servicio();
            s.setIdservicio(BigInteger.ONE);
            s.setIddominio(DomainController.getDomainByParent(vehicleCategoryId, serviceCategoryId));
            s.setIdempresa(prcompany.getCompany());
            s.setStrnombre(serviceName);
            UserSessionManager.getInstance().addService(session, s);
            clean();
            UIMessageManagement.putInfoMessage("Se ha adicionado el servicio.");
        } catch (Exception ex) {
            UIMessageManagement.putException(ex);
        }
    }
    
    public boolean isHaveServices(){
        return !prcompany.getServices().isEmpty();
    }
    
    public void clean(){
        vehicleCategoryId=BigInteger.ZERO;
        serviceCategoryId=BigInteger.ZERO;
        serviceName="";
    }
    
    public void updateService(Servicio s){
        try{
            UserSessionManager.getInstance().updateService(s);
            UIMessageManagement.putInfoMessage("El servicio se ha actualizado.");
        }catch(Exception ex){
            UIMessageManagement.putException(ex);
        }
    }
    
    public String goToAsignBranches(Servicio s){
        UserSessionManager.getInstance().putServiceSelected(session, s);
        return "/system/services/branch_services.xhtml?faces-redirect=true";
    }
}
