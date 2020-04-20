/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.primo.controllers.geolocalization;

import co.com.primo.ui.constants.GlobalConstants;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import java.io.IOException;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author OvalleGA
 */
public class GeolocaliaztionController {
     
    /**
     * Regresa la geolocalizacion dada una direccion.
     * @param address la direccion a convertir
     * @return LatLng el objeto con la localizacion para colocar en un mapa JSF.
     * @throws com.google.maps.errors.ApiException si:
     * <ol><li>Se preseanta un error al momento de invocar el API de google</li></ol>
     * @throws java.lang.InterruptedException si:
     * <ol><li>Se preseanta un error de conexion al momento de invocar el API de google</li></ol>
     * @throws java.io.IOException si:
     * <ol><li>El API no retorna informacion</li></ol>
     */
    public static LatLng getGeolocalization(String address) throws ApiException, InterruptedException, IOException{
        GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(GlobalConstants.GEO_API_KEY)
                    .build();
            GeocodingResult[] results =  GeocodingApi.geocode(context,
                    address).await();
        String[] coords = ((results[0].geometry.location).toString()).split(",");
        return getGeolocalization(coords[0], coords[1]);
    }
    
    /**
     * Regresa la geolocalizacion dada una localizacion con latitud y longitud.
     * @param latitud la latiud de la localizacion
     * @param longitud la longitud de la localizacion
     * @return LatLng el objeto con la localizacion para colocar en un mapa JSF.
     */
    public static LatLng getGeolocalization(String latitud, String longitud){
        return new LatLng(Double.parseDouble(latitud), 
                          Double.parseDouble(longitud));
    }
    
    /**
     * Regregsa la direccion de una localizacion definida.
     * @param latitud la latiud de la localizacion
     * @param longitud la longitud de la localizacion
     * @return String la direccion de una localizacion.
     */
    public static String getAddress(String latitud, String longitud){
        com.google.maps.model.LatLng coords = 
                new com.google.maps.model.LatLng(Double.parseDouble(latitud), 
                                                 Double.parseDouble(longitud));
        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(GlobalConstants.GEO_API_KEY)
                    .build();
            GeocodingResult[] results= GeocodingApi.reverseGeocode(context, coords).await();
            return results[0].formattedAddress;
        } catch (ApiException | InterruptedException | IOException ex) {
            System.out.println("Excepcion");
        }
        return "";
    }
}
