/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.customize.UI.beans.security;

import com.primo.model.Usuario;
import com.primo.ws.user.UserWSClient;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.customize.UI.utils.UIMessageManagement;
import org.primefaces.customize.controllers.security.UserSessionManager;


/**
 *
 * @author OvalleGA
 */
@RequestScoped
@ManagedBean(name = "LoginBean")
public class LoginBean {
    private String username;
    private String password;
    private String confim_pasword;
    private boolean acept_terms;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfim_pasword() {
        return confim_pasword;
    }

    public void setConfim_pasword(String confim_pasword) {
        this.confim_pasword = confim_pasword;
    }

    public boolean isAcept_terms() {
        return acept_terms;
    }

    public void setAcept_terms(boolean acept_terms) {
        this.acept_terms = acept_terms;
    }
    
    public String login(){
        username = username.toUpperCase();
        try {
            Usuario myUsuario = new Usuario();
            myUsuario.setStrUsuario(username);
            myUsuario.setStrPassword(password);
            Usuario myUsuarioTemp = UserWSClient.login(myUsuario);
            
            if(myUsuarioTemp != null){
                UserSessionManager.getInstance().connectUser(username, (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true));
                UIMessageManagement.putInfoMessage("Bienvenido "+username);
                return "autowired";
            }else{
                return "denied";
            }
        } catch (Exception ex) {
            UIMessageManagement.putErrorMessage(ex.getMessage());
            return "denied";
        }
    }
    
    public String register(){
        if(password != null && confim_pasword != null && username != null
           && password.equals(confim_pasword) && acept_terms){
            Usuario user = new Usuario();
            user.setStrUsuario(username);
            user.setStrPassword(password);
            user.setIntNumIntentos(3);
            user.setBitActivo(true);
            try {
                UserWSClient.registerUser(user);
                return login();
            } catch (Exception ex) {
                UIMessageManagement.putException(ex);
                return "denied";
            }
        }
        if(!acept_terms){
            UIMessageManagement.putWarnMessage("Le recomendamos leer y si esta de acuerdo aceptar nuestros terminos y condiciones de uso de esta aplicacion.");
        }
        return "denied";
    }
}
