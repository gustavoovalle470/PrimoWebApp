/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.beans.company;

import co.com.primo.containers.PRCompany;
import co.com.primo.controllers.geolocalization.GeolocaliaztionController;
import co.com.primo.controllers.security.UserSessionManager;
import co.com.primo.model.Sucursal;
import co.com.primo.ui.constants.GlobalConstants;
import co.com.primo.ui.utils.UIMessageManagement;
import co.com.primo.ws.sucursal.SucursalWSClient;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author OvalleGA
 */
@ViewScoped
@ManagedBean(name="GeolocationCompanyBean")
public class GeolocationCompanyBean {
    private String centerCoord=GlobalConstants.GEO_CENTER_CORD;
    private String zoom=GlobalConstants.GEO_ZOOM;
    private MapModel branchMap;
    private PRCompany prcompany;
    private String branchName;
    private String branchAddress;
    private final HttpSession session= (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private Sucursal branchSelected=null;
    
    public GeolocationCompanyBean(){
        this.prcompany=UserSessionManager.getInstance().getCompanyContainer(session);
        branchMap = new DefaultMapModel();
        if(prcompany.getBranchOffices()!=null && !prcompany.getBranchOffices().isEmpty()){
            for(Sucursal s : prcompany.getBranchOffices()){
                if(s.isBitPrincipal()){
                    branchSelected=s;
                    branchAddress=GeolocaliaztionController.getAddress(branchSelected.getLatitud(), branchSelected.getLongitud());
                }
                branchMap.addOverlay(new Marker(new LatLng(Double.parseDouble(s.getLatitud()), 
                                                 Double.parseDouble(s.getLongitud())), 
                                                 s.getStrNombre()));
            }
        }else{
            branchAddress=prcompany.getContact().getStrDireccion();
        }
    }
    
    public Sucursal getBranchSelected() {
        return branchSelected;
    }

    public void setBranchSelected(Sucursal branchSelected) {
        this.branchSelected = branchSelected;
    }
    
    public MapModel getBranchMap() {
        return branchMap;
    }

    public void setBranchMap(MapModel branchMap) {
        this.branchMap = branchMap;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public PRCompany getPrcompany() {
        return prcompany;
    }

    public void setPrcompany(PRCompany prcompany) {
        this.prcompany = prcompany;
    }

    public String getCenterCoord() {
        return centerCoord;
    }

    public void setCenterCoord(String centerCoord) {
        this.centerCoord = centerCoord;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public void onMarkerSelect(OverlaySelectEvent event){
        Marker markerSelect = (Marker) event.getOverlay();
        branchSelected=prcompany.getBranch(markerSelect.getLatlng());
        branchAddress=GeolocaliaztionController.getAddress(branchSelected.getLatitud(), branchSelected.getLongitud());        
    }
    
    public void saveBranch(){
        try {
            Marker markerToAdd = new Marker(GeolocaliaztionController.getGeolocalization(branchAddress), branchName);
            markerToAdd.setDraggable(true);
            branchSelected.setLatitud(""+markerToAdd.getLatlng().getLat());
            branchSelected.setLongitud(""+markerToAdd.getLatlng().getLng());
            boolean isUpdate=UserSessionManager.getInstance().putSucursal(session, branchSelected);
            branchMap.addOverlay(markerToAdd);
            UIMessageManagement.putInfoMessage((isUpdate?"Se actualizo la sucursal ":"Se agrego la sucursal ")+branchSelected.getStrNombre());
        } catch (Exception ex) {
            UIMessageManagement.putException(ex);
        }
    }
    
    public void newBranch(){
        System.out.println("CLick nueva branch");
        branchAddress=new String();
        branchSelected=new Sucursal("", false, null, null, prcompany.getCompany());
    }
}
