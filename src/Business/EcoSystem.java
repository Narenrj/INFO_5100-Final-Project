/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;


import Business.Network.Network;
import Business.Organization.Organization;

import Business.Role.Role;
import Business.Role.SystemAdminRole;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class EcoSystem extends Organization{
    
    private static EcoSystem business;

    private ArrayList<Network> networkList;

 
    
    public static EcoSystem getInstance(){
        if(business==null){
            business=new EcoSystem();
        }
        return business;
    }

    private EcoSystem(){
        super(null);
        networkList = new ArrayList<Network>();
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }
    
    public void setNetworkList(Network network){
        System.out.println("NET "+ network);
        networkList.add(network);
    }

   
    
    public boolean checkIfUserIsUnique(String userName){
       //
       return false;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
         ArrayList<Role> roleList=new ArrayList<Role>();
        roleList.add(new SystemAdminRole());
        return roleList;
    }
    
    
    public Network searchNetworkName(String n){
        
        for(Network net: networkList){
            if(net.getNetworkName().equals(n)){
                return net;
            }
        }
        return null;
            
    }
    
}
