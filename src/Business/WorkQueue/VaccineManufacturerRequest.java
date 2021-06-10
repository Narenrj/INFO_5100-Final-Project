/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author psrib
 */
public class VaccineManufacturerRequest extends WorkRequest{
    String name;
    
    HashMap<String, Integer> map;
    InventoryRequest inventoryRequest;

    public InventoryRequest getInventoryRequest() {
        return inventoryRequest;
    }

    public void setInventoryRequest(InventoryRequest inventoryRequest) {
        this.inventoryRequest = inventoryRequest;
    }
    
    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }
    
    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public String getManufacturername() {
        return manufacturername;
    }

    public void setManufacturername(String manufacturername) {
        this.manufacturername = manufacturername;
    }

    public int getNumberofdoses() {
        return numberofdoses;
    }

    public void setNumberofdoses(int numberofdoses) {
        this.numberofdoses = numberofdoses;
    }
   
    
    int efficiency;
    String manufacturername;
    int numberofdoses;
    String batchNumber;
    Date expirydate;
    
    public VaccineManufacturerRequest( String name , int efficiency , String manufacturername , int numberofdoses , String batchnumber , Date date )
    {
        this.name= name;
        this.efficiency =efficiency;
        this.manufacturername = manufacturername;
        this.numberofdoses = numberofdoses;
        this.batchNumber = batchnumber;
        this.expirydate = new Date();
        
    }
    
    @Override
    public String toString(){
        return name;
    }
    
//    @Override
//    public String toString(){
//        
//    }
    
    
    
}
