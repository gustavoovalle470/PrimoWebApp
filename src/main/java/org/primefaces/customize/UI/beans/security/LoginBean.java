/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.customize.UI.beans.security;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.customize.UI.utils.UIMessageManagement;
import org.primefaces.customize.controllers.security.LoginUsers;


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
    
    public String login(){
        username = username.toUpperCase();
        try {
            if(LoginUsers.getInstance().ValidateCredentials(username, password, (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true))){
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
           && password.equals(confim_pasword)){
            System.out.println("REGISTRAR AQUI!");
            return login();
        }
        return "denied";
    }
}
