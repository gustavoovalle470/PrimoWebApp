/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primo.bean;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class AddMarkersView implements Serializable {
     
    private MapModel emptyModel;
    private String title;
    private String adrress="";
    private String coordToCenter="4.71098860,-74.07209200";
    private String zoom="12";
    private Marker markerSelect;
      
    private double lat;
      
    private double lng;
  
    @PostConstruct
    public void init() {
        System.out.println("Creacion");
        emptyModel = new DefaultMapModel();
        for(Marker m: MarkersController.getInstance().getMarkers()){
            emptyModel.addOverlay(m);
        }
    }

    public String getAdrress() {
        return adrress;
    }

    public void setAdrress(String adrress) {
        this.adrress = adrress;
    }
    
    
    public MapModel getEmptyModel() {
        return emptyModel;
    }
      
    public String getTitle() {
        return title;
    }
  
    public void setTitle(String title) {
        this.title = title;
    }
  
    public double getLat() {
        return lat;
    }
  
    public void setLat(double lat) {
        this.lat = lat;
    }
  
    public double getLng() {
        return lng;
    }
  
    public void setLng(double lng) {
        this.lng = lng;
    }
    
    public String getCoordToCenter() {
        return coordToCenter;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }
    
    public void setCoordToCenter(String coordToCenter) {
        this.coordToCenter = coordToCenter;
    }

    public Marker getMarkerSelect() {
        return markerSelect;
    }

    public void setMarkerSelect(Marker markerSelect) {
        this.markerSelect = markerSelect;
    }
      
    public void addMarker() {
        Marker markerToAdd = new Marker(new org.primefaces.model.map.LatLng(lat, lng), title);
        markerToAdd.setId("1");
        markerToAdd.setDraggable(true);
        emptyModel.addOverlay(markerToAdd);
        MarkersController.getInstance().putMarker(markerToAdd);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }
    
    public void onMarkerSelect(OverlaySelectEvent event){
        System.out.println("Evento: "+event);
        markerSelect = (Marker) event.getOverlay();
        System.out.println("Marcador: "+markerSelect);
    }
    
    public void onMarkerDrag(MarkerDragEvent event) {
        markerSelect = event.getMarker();     
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerSelect.getLatlng().getLat() + ", Lng:" + markerSelect.getLatlng().getLng()));
    }
    public void putAdress(){
        System.out.println("Click");
        try {
            GeoApiContext context = new GeoApiContext.Builder()
		    .apiKey("")
		    .build();
            GeocodingResult[] results =  GeocodingApi.geocode(context,
                    getAdrress()).await();
            zoom="16";
            coordToCenter = (results[0].geometry.location).toString();
            System.out.println("Coordenadas: "+coordToCenter);
            String[] data=coordToCenter.split(",");
            Marker markerToAdd = new Marker(new org.primefaces.model.map.LatLng(Double.parseDouble(data[0]), Double.parseDouble(data[1])), getAdrress());
            markerToAdd.setDraggable(true);
            emptyModel.addOverlay(markerToAdd);
            MarkersController.getInstance().putMarker(markerToAdd);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + markerToAdd.getLatlng().getLat() + ", Lng:" + markerToAdd.getLatlng().getLat()));
        } catch (ApiException ex) {
            Logger.getLogger(AddMarkersView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AddMarkersView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddMarkersView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}