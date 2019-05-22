/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.customize.UI.beans.security;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.customize.UI.utils.UIMessageManagement;
import org.primefaces.customize.controllers.security.UserSessionManager;

/**
 *
 * @author OvalleGA
 */
@SessionScoped
@ManagedBean(name = "UserSessionBean")
public class UserSessionBean implements Serializable{
    private String username;
    private final HttpSession session;

    public UserSessionBean(){
        session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        username = UserSessionManager.getInstance().getUser(session);
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean isValidHttpSession(){
        return session.equals((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true));
    }
    
    public String logout(){
        System.out.println("Cerrar la sesion para el usuario: "+username);
        if (UserSessionManager.getInstance().disconectUser(session)) {
            UIMessageManagement.putInfoMessage("La sesion del usuario "+username+" finalizó correctamente.");
            return "logout";
        } else {
            return "failed";
        }
    }
}
