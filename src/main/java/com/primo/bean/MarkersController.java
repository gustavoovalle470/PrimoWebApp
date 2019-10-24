/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primo.bean;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.map.Marker;

/**
 *
 * @author OvalleGA
 */
public class MarkersController {
    private static MarkersController instance;
    private List<Marker> markers;
    
    public MarkersController(){
        markers=new ArrayList<>();
    }
    
    public static MarkersController getInstance(){
        if(instance==null){
            instance=new MarkersController();
        }
        return instance;
    }
    
    public void putMarker(Marker marker){
        markers.add(marker);
    }
    
    public List<Marker>getMarkers(){
        return markers;
    }
}
