/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.customize.UI.company;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author OvalleGA
 */
@ManagedBean(name="CompanyBean")
@SessionScoped
public class CompanyBean {
    private String company_name;
    private String company_address;
    private int company_rate_primos;
    private int company_ranking_zone;
    private int company_ranking_service;
    private String company_status;

    public CompanyBean(){
        // Recuperar informacion con WS.
        company_name = "Sin asignar";
        company_address = "Sin asignar";
        company_rate_primos = 0;
        company_ranking_zone = 0;
        company_ranking_service = 0;
        company_status="close";
    }
    
    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public int getCompany_rate_primos() {
        return company_rate_primos;
    }

    public void setCompany_rate_primos(int company_rate_primos) {
        this.company_rate_primos = company_rate_primos;
    }

    public int getCompany_ranking_zone() {
        return company_ranking_zone;
    }

    public void setCompany_ranking_zone(int company_ranking_zone) {
        this.company_ranking_zone = company_ranking_zone;
    }

    public int getCompany_ranking_service() {
        return company_ranking_service;
    }

    public void setCompany_ranking_service(int company_ranking_service) {
        this.company_ranking_service = company_ranking_service;
    }

    public String getCompany_status() {
        return company_status;
    }

    public void setCompany_status(String company_status) {
        this.company_status = company_status;
    }
    
    public void change_status_company(){
        System.out.println("Click actual: "+company_status);
        if(company_status.equals("open")){
            company_status = "close";
        }else{
            company_status = "open";
        }
    }
    
    public String save_company_info(){
        // SALVAR INFORMACION CON EL WS.
        return "";
    }
}
