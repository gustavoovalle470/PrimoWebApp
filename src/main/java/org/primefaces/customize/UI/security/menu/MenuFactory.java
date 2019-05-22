/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.customize.UI.security.menu;

import java.util.ArrayList;
import java.util.HashMap;
import org.primefaces.customize.controllers.security.SecurityMenuManager;

/**
 *
 * @author OvalleGA
 */
public class MenuFactory {
    private static MenuFactory instance;
    private HashMap<String, MenuDescriptor> allMenuSystem;
    
    public MenuFactory(){
        allMenuSystem = SecurityMenuManager.getInstance().getAllMenuSystem();
    }
    
    public static MenuFactory getInstance(){
        if(instance == null){
            instance = new MenuFactory();
        }
        return instance;
    }

    private void putAllMenuSystem() {
        
    }

    private HashMap<String, MenuDescriptor> organizeMenus(HashMap<String, MenuDescriptor> to_organize) {
        HashMap<String, MenuDescriptor> organizeMenus = new  HashMap<>();
        to_organize.keySet().forEach((menuId) -> {
            MenuDescriptor descriptor=to_organize.get(menuId);
            if (descriptor.isPrincipalNode()) {
                descriptor.setSubMenus(getSubMenus(menuId, to_organize));
                organizeMenus.put(menuId, descriptor);
            }
        });
        return organizeMenus;
    }

    private ArrayList<MenuDescriptor> getSubMenus(String menuId, HashMap<String, MenuDescriptor> to_organize) {
        ArrayList<MenuDescriptor> subMenus = new ArrayList<>();
        for(MenuDescriptor descriptor: to_organize.values()){
            if(descriptor.getParentId() != null && descriptor.getParentId().equals(menuId)){
                if(descriptor.isNode()){
                    descriptor.setSubMenus(getSubMenus(descriptor.getId(), to_organize));
                }
                subMenus.add(descriptor);
            }
        }
        return subMenus;
    }
    
    public HashMap<String, MenuDescriptor> getSecMenus(String usernamne){
        HashMap<String, MenuDescriptor> user_menus = new HashMap<>();
        for(String id : SecurityMenuManager.getInstance().getMenuSec(usernamne)){
            user_menus.put(id, allMenuSystem.get(id));
            String parentId = allMenuSystem.get(id).getParentId();
            while(parentId != null){
                user_menus.put(parentId, allMenuSystem.get(parentId));
                parentId = allMenuSystem.get(parentId).getParentId();
            }
        }
        return organizeMenus(user_menus);
    }
}
