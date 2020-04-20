/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.beans.security;

import co.com.primo.model.Usuario;
import co.com.primo.ws.user.UserWSClient;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import co.com.primo.ui.utils.UIMessageManagement;
import co.com.primo.controllers.security.UserSessionManager;


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
    private String contrasenaAnt;

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
    
    /**
     * @return the contrasenaAnt
     */
    public String getContrasenaAnt() {
        return contrasenaAnt;
    }

    /**
     * @param contrasenaAnt the contrasenaAnt to set
     */
    public void setContrasenaAnt(String contrasenaAnt) {
        this.contrasenaAnt = contrasenaAnt;
    }

    public String login(){
        username = username.toUpperCase();
        try {
            Usuario myUsuario = new Usuario();
            myUsuario.setStrUsuario(username);
            myUsuario.setStrPassword(password);
            myUsuario.setIntTipoUsuario(new BigInteger("1"));
            Usuario myUsuarioTemp = UserWSClient.login(myUsuario);
            
            if(myUsuarioTemp != null){
                UserSessionManager.getInstance().connectUser(myUsuarioTemp, (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true));
                UIMessageManagement.putInfoMessage("Bienvenido "+username);
                return "/dashboard.xhtml?faces-redirect=true";
            }else{
                UIMessageManagement.putErrorMessage("Usuario o Contraseña incorrecta");
                return "/login.xhtml?faces-redirect=true";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            UIMessageManagement.putErrorMessage(ex.getMessage());
            return "/login.xhtml?faces-redirect=true";
        }
    }
    
    public String register(){
        if(password != null && confim_pasword != null && username != null
           && password.equals(confim_pasword) && acept_terms){
            Usuario user = new Usuario();
            user.setStrUsuario(username);
            user.setStrPassword(password);
            user.setIntNumIntentos(0);
            user.setBitActivo(true);
            user.setIntTipoUsuario(new BigInteger("1"));

            try {
                UserWSClient.registerUser(user);
                return login();
            } catch (Exception ex) {
                UIMessageManagement.putException(ex);
                return "/login.xhtml?faces-redirect=true";
            }
        }
        if(!acept_terms){
            UIMessageManagement.putWarnMessage("Le recomendamos leer y si esta de acuerdo aceptar nuestros terminos y condiciones de uso de esta aplicacion.");
        }
        return "/login.xhtml?faces-redirect=true";
    }

    public String recuperarContrasena(){
        //Atributos de Metodo
        String myResultado = "";
        
        if(username != null){
            Usuario myUsuario = new Usuario();
            myUsuario.setStrUsuario(username);

            try {
                myResultado = UserWSClient.recuperarContrasena(myUsuario);
                
                //Verificar el resultado
                if(myResultado.equalsIgnoreCase("OK")){
                    UIMessageManagement.putInfoMessage("Se envío la contraseña por Correo");
                }
                else{
                    UIMessageManagement.putInfoMessage(myResultado);
                }
                
                return "/dashboard.xhtml?faces-redirect=true";
            } catch (Exception ex) {
                UIMessageManagement.putException(ex);
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                return "/login.xhtml?faces-redirect=true";
            }
        }
        return "/login.xhtml?faces-redirect=true";
    }
    
    /**
     * 
     * @return 
     */
    public String cambiarContrasena(){
         try {

           if(password != null && confim_pasword != null && password.equals(confim_pasword)){
                //Traer la información del Usuario
                String myUser = UserSessionManager.getInstance().getCompanyContainer((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getUser().getStrUsuario();

                //Verificar que sea un Usuario Válido
                Usuario myUsuario = new Usuario();
                myUsuario.setStrUsuario(myUser);
                myUsuario.setStrPassword(contrasenaAnt);
                Usuario myUsuarioTemp = UserWSClient.login(myUsuario);

                if(myUsuarioTemp != null){
                    String myResultado = UserWSClient.cambiarContrasena(myUsuario);
                    
                    //Verificar la respuesta del servicio
                    if(myResultado.equalsIgnoreCase("OK")){
                        UIMessageManagement.putInfoMessage("Se realizó el cambio de Contraseña");
                    }
                    else{
                        UIMessageManagement.putInfoMessage(myResultado);
                    }

                    return "autowired";
                }
                else{
                    UIMessageManagement.putErrorMessage("La contraseña anterior no coincide");
                    return "denied";
                }
           }
           else{
                UIMessageManagement.putErrorMessage("Las contraseñas no coinciden");
                return "denied";
           }
        } 
         catch (Exception ex) {
            UIMessageManagement.putErrorMessage(ex.getMessage());
            return "denied";
        }
    }

    /**
     * 
     * @return 
     */
    public String cambiarContrasenaEmail(){
         try {
           if(username != null && password != null && confim_pasword != null && password.equals(confim_pasword)){

                //Verificar que sea un Usuario Válido
                Usuario myUsuario = new Usuario();
                myUsuario.setStrUsuario(username);
                myUsuario.setStrPassword(contrasenaAnt);
                Usuario myUsuarioTemp = UserWSClient.login(myUsuario);

                if(myUsuarioTemp != null){
                    String myResultado = UserWSClient.cambiarContrasena(myUsuario);
                    
                    //Verificar la respuesta del servicio
                    if(myResultado.equalsIgnoreCase("OK")){
                        UIMessageManagement.putInfoMessage("Se realizó el cambio de Contraseña");
                    }
                    else{
                        UIMessageManagement.putInfoMessage(myResultado);
                    }

                    return "autowired";
                }
                else{
                    UIMessageManagement.putErrorMessage("La contraseña anterior no coincide");
                    return "denied";
                }
           }
           else{
                UIMessageManagement.putErrorMessage("Las contraseñas no coinciden");
                return "denied";
           }
        } 
         catch (Exception ex) {
            UIMessageManagement.putErrorMessage(ex.getMessage());
            return "denied";
        }
    }
}
