/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.beans.company;

import co.com.primo.controllers.domain.DomainController;
import co.com.primo.model.Empresa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import co.com.primo.controllers.security.UserSessionManager;
import co.com.primo.ui.utils.UIMessageManagement;
import co.com.primo.containers.PRCompany;
import co.com.primo.model.Contacto;
import co.com.primo.model.Dominio;
import co.com.primo.ws.company.CompanyWSClient;
import co.com.primo.ws.contacto.ContactoWSClient;
import java.math.BigInteger;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;

/**
 *
 * @author OvalleGA
 */
@SessionScoped
@ManagedBean(name="companyBean")
public class CompanyBean {

    private PRCompany prcompany;
    private String status="close";
    private String branch="REGISTRE UNA SUCURSAL";
    private boolean register=true;
    private List<SelectItem> idTypeCompany=DomainController.obtenerListadoDominioTipo("2");
    
    
    public CompanyBean(){
        this.prcompany=UserSessionManager.getInstance().getCompanyContainer((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true));
        if(prcompany.getCompany()==null){
            prcompany.setCompany(getDefaultCompany());
            prcompany.setContact(getDefaultContact());
            this.register = false;
            branch="REGISTRA UNA SUCURSAL";
        }else{
            branch=prcompany.getBranchName();
        }
    }
    
    public PRCompany getPrcompany() {
        return prcompany;
    }

    public void setPrcompany(PRCompany prcompany) {
        this.prcompany = prcompany;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String company_branch) {
        this.branch = company_branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String company_status) {
        this.status = company_status;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean company_is_register) {
        this.register = company_is_register;
    }

    public List<SelectItem> getIdTypeCompany() {
        return idTypeCompany;
    }

    public void setIdTypeCompany(List<SelectItem> idTypeCompany) {
        this.idTypeCompany = idTypeCompany;
    }
    
    public void changeCompanyStatus(){
        if(isRegister()){
            if(status.equals("close")){
                setStatus("open");
            }else{
                setStatus("close");
            }
        }else{
            UIMessageManagement.putCustomMessage(FacesMessage.SEVERITY_ERROR, "Compañia no registrada", "Por favor finalice su registro para abrir su sucursal");
        }
    }
    
    public void saveCompany(){
        try {
            if(register){
                CompanyWSClient.updateCompany(prcompany.getCompany());
                ContactoWSClient.updateContact(prcompany.getContact());
            }else{
                CompanyWSClient.registerCompany(prcompany.getCompany());
                UserSessionManager.getInstance().reloadCompany((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true));
                prcompany.getContact().setMyEmpresa(prcompany.getCompany());
                ContactoWSClient.guardarContacto(prcompany.getContact());
            }
        } catch (Exception ex) {
            UIMessageManagement.putException(ex);
        }
    }
    
    private Empresa getDefaultCompany() {
        Empresa def_comp=new Empresa();
        def_comp.setMyDominio(new Dominio(BigInteger.ZERO));
        def_comp.setMyUsuario(prcompany.getUser());
        def_comp.setStrRazonSocial(prcompany.getUser().getStrUsuario());
        return def_comp;
    }

    private Contacto getDefaultContact() {
        Contacto def_contact = new Contacto();
        def_contact.setStrEmail(prcompany.getUser().getStrUsuario());
        def_contact.setMyDominio(new Dominio(BigInteger.ZERO));
        return def_contact;
    }
}