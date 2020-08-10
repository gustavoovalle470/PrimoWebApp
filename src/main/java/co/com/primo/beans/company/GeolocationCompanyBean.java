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
import co.com.primo.model.SucursalServicio;
import co.com.primo.ui.constants.GlobalConstants;
import co.com.primo.ui.utils.UIMessageManagement;
import co.com.primo.ws.sucursal.SucursalWSClient;
import com.google.maps.errors.ApiException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
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
    private String newBranchName;
    private boolean connected=false;
    private List<SucursalServicio> branchService= new ArrayList<>();
            
    public GeolocationCompanyBean(){
        this.prcompany=UserSessionManager.getInstance().getCompanyContainer(session);
        branchMap = new DefaultMapModel();
        setConnectBranch();
        putBranches();
        putServices();
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getNewBranchName() {
        return newBranchName;
    }

    public void setNewBranchName(String newBranchName) {
        this.newBranchName = newBranchName;
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

    public List<SucursalServicio> getBranchService() {
        return branchService;
    }

    public void setBranchService(List<SucursalServicio> branchService) {
        this.branchService = branchService;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event){
        Marker markerSelect = (Marker) event.getOverlay();
        branchSelected=prcompany.getBranch(markerSelect.getLatlng());
        connected=prcompany.getBranchConnected().equals(branchSelected);
        branchAddress=GeolocaliaztionController.getAddress(branchSelected.getLatitud(), branchSelected.getLongitud());        
        putServices();
    }
    
    public void updateBranch(){
        try {
            saveBranch(branchSelected, new LatLng(Double.parseDouble(branchSelected.getLatitud()),
                                                  Double.parseDouble(branchSelected.getLongitud())));
        } catch (Exception ex) {
            UIMessageManagement.putException(ex);
        }
    }
    
    public String saveNewBranch(){
        try {
            LatLng location = GeolocaliaztionController.getGeolocalization(branchAddress);
            System.out.println(""+location.getLat()+", "+location.getLng());
            branchSelected=new Sucursal(newBranchName, false, ""+location.getLat(), ""+location.getLng(), prcompany.getCompany());
            saveBranch(branchSelected, location);
        } catch (Exception ex) {
            UIMessageManagement.putException(ex);
        }
        return GlobalConstants.BRANCH_URL;
    }
    
    public void changeBranch(ValueChangeEvent event){
        boolean connect= (boolean)event.getNewValue();
        if(connect){
            UserSessionManager.getInstance().changeBranchConnected(session, branchSelected);
            UIMessageManagement.putInfoMessage("Se conecto a la sucursal"+branchSelected.getStrNombre() );
        }
    }
    
    private void saveBranch(Sucursal sucursal, LatLng coords) throws ApiException, InterruptedException, IOException, Exception{
        Marker markerToAdd = new Marker(coords, sucursal.getStrNombre());
        markerToAdd.setDraggable(true);
        UserSessionManager.getInstance().putSucursal(session, sucursal);
        branchMap.addOverlay(markerToAdd);
        UIMessageManagement.putInfoMessage("Se actualizo la sucursal "+branchSelected.getStrNombre());
    }

    private void putBranches() {
        if(prcompany.getBranchOffices()!=null && !prcompany.getBranchOffices().isEmpty()){
            for(Sucursal s : prcompany.getBranchOffices()){
                branchMap.addOverlay(new Marker(new LatLng(Double.parseDouble(s.getLatitud()), 
                                                 Double.parseDouble(s.getLongitud())), 
                                                 s.getStrNombre()));
            }
        }
    }

    private void setConnectBranch() {
        if(prcompany.getBranchConnected()!=null && prcompany.getBranchConnected().getLatitud()!=null){
            connected=true; 
            branchSelected=prcompany.getBranchConnected();
            branchAddress=GeolocaliaztionController.getAddress(branchSelected.getLatitud(), branchSelected.getLongitud());
        }
    }

    private void putServices() {
        System.out.println("branchSelected: "+branchSelected);
        if(branchSelected != null){
            try {
                branchService=SucursalWSClient.getServicesForBranch(branchSelected);
                } catch (NoSuchAlgorithmException ex) {
                    UIMessageManagement.putException(ex);
                }
        }
    }
    
    public void onRowEdit(RowEditEvent event) {
        SucursalServicio ss=(SucursalServicio) event.getObject();
        System.out.println(ss);
        try {
            SucursalWSClient.updateServiceForSucursal(ss);
            UIMessageManagement.putInfoMessage("Se ha asignado un valor de "+ss.getDblValor()+" al servicio "+ss.getMyServicio().getStrnombre()+" en la sucursal "+ss.getMySucursal().getStrNombre());
        } catch (Exception ex) {
            UIMessageManagement.putException(ex);
        }
        UserSessionManager.getInstance().reloadCompany(session);
    }
}
