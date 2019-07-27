/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.customize.UI.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author OvalleGA
 * Fecha de Modificación: 04/06/2019
 */
@SessionScoped
@ManagedBean(name = "SystemInfoBean")
public class SystemInfoBean implements Serializable{
    /** Atributos de Clase **/
    private InputStream myInputStream;
    private Properties myProperties = new Properties();
    private String app_name;
    private String header_view;

    {
        try {
            myInputStream = new FileInputStream("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\appPrimo\\src\\main\\webapp\\resources\\application.properties");
            myProperties.load(myInputStream);
            this.app_name = myProperties.getProperty("appName");
            this.header_view = myProperties.getProperty("appName");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SystemInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SystemInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getHeader_view() {
        return header_view;
    }

    public void setHeader_view(String header_view) {
        this.header_view = header_view;
    }
    
    public void init(){
        
    }
}
