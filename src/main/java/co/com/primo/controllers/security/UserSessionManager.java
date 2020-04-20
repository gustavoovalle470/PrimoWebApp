/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.controllers.security;

import co.com.primo.containers.PRCompany;
import co.com.primo.model.Contacto;
import co.com.primo.model.Dominio;
import co.com.primo.model.Empresa;
import co.com.primo.model.Sucursal;
import co.com.primo.model.SucursalServicio;
import co.com.primo.model.Usuario;
import co.com.primo.ws.company.CompanyWSClient;
import co.com.primo.ws.contacto.ContactoWSClient;
import co.com.primo.ws.sucursal.SucursalWSClient;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author OvalleGA
 */
public class UserSessionManager {
    private static UserSessionManager instance;
    private final HashMap<HttpSession, PRCompany> companies_online;
    
    public UserSessionManager(){
        companies_online = new HashMap<>();
    }
    
    public static UserSessionManager getInstance(){
        if(instance == null){
            instance = new UserSessionManager();
        }
        return instance;
    }
    
    public boolean isUserConnected(HttpSession session){
        return companies_online.containsKey(session);
    }
    
    public void connectUser(Usuario user, HttpSession session){
        PRCompany company = new PRCompany();
        company.setUser(user);
        company.setCompany(getCompany(user));
        if(company.getCompany()!= null){
            company.setContact(getContact(company.getCompany()));
            company.setBranchOffices(getBranchOffices(company.getCompany()));
            company.setServices(getServices());
            company.connectBranch(getDefaultBranchConect(user, company.getBranchOffices()));
        }
        companies_online.put(session, company);
    }
    
    public boolean disconectUser(HttpSession session){        
        companies_online.remove(session);
        session.invalidate();
        return true;
    }
      
    public PRCompany getCompanyContainer(HttpSession session){
        return companies_online.get(session);
    }

    public void reloadCompany(HttpSession session){
        PRCompany company= getCompanyContainer(session);
        company.setCompany(getCompany(company.getUser()));
        company.setBranchOffices(getBranchOffices(company.getCompany()));
    }
    
    private Empresa getCompany(Usuario user) {
        if(CompanyWSClient.getCompany(user.getIdUsuario()) != null && 
           !CompanyWSClient.getCompany(user.getIdUsuario()).isEmpty())
        {
            return CompanyWSClient.getCompany(user.getIdUsuario()).get(0);
        }
        return null;
    }

    private List<Sucursal> getBranchOffices(Empresa company) {
        return (List<Sucursal>) SucursalWSClient.getSucursal(company.getIdEmpresa());
    }

    private List<SucursalServicio> getServices() {
        return null;
    }

    private Contacto getContact(Empresa company) {
        Contacto contacto =new Contacto();
        contacto.setMyDominio(new Dominio(BigInteger.ZERO));
        if(ContactoWSClient.getContact(company.getIdEmpresa())!=null && !ContactoWSClient.getContact(company.getIdEmpresa()).isEmpty()){
            contacto =ContactoWSClient.getContact(company.getIdEmpresa()).get(0);
        }
        return contacto;
    }

    public void putSucursal(HttpSession session, Sucursal s) throws Exception {
        System.out.println("Sucursal: "+s.getStrNombre());
        System.out.println("Principal?: "+s.isBitPrincipal());
        if(s.isBitPrincipal()){
            takeOffAllBranches(session);
            s.setBitPrincipal(true);
        }
        if(getCompanyContainer(session).getBranch(new LatLng(Double.parseDouble(s.getLatitud()), Double.parseDouble(s.getLongitud())))!= null){
            System.out.println("Actualizando sigue siendo principal?: "+s.isBitPrincipal());
            SucursalWSClient.updateSucursal(s);
        }else{
            if(getCompanyContainer(session).getBranchOffices().isEmpty()){
                s.setBitPrincipal(true);
                changeBranchConnected(session, s);
            }
            SucursalWSClient.guardarSucursal(s);
        }
        reloadCompany(session);
    }

    private void takeOffAllBranches(HttpSession session) throws Exception {
        for(Sucursal s : getCompanyContainer(session).getBranchOffices()){
            s.setBitPrincipal(false);
            SucursalWSClient.updateSucursal(s);
        }
    }

    private Sucursal getDefaultBranchConect(Usuario user, List<Sucursal> branchOffices) {
        // if(USER.GETBRANCHDEFAULT)ESTO NO EXISTE
        for(Sucursal s: branchOffices){
            if(s.isBitPrincipal()){
                return s;
            }
        }
        return new Sucursal("Registre una sucursal", false, null, null,null);
    }

    public void changeBranchConnected(HttpSession session, Sucursal branchSelected) {
        getCompanyContainer(session).setBranchConnected(branchSelected);
    }
}
