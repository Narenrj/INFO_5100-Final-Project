/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.UserAccount.UserAccount;

/**
 *
 * @author psrib
 */
public class PHDAdminRequest extends WorkRequest{
    
    
    UserAccount userAccount;
    InventoryRequest inventoryRequest;
    VaccineManufacturerRequest vaccineManufacturerRequest;
   
    
    
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public InventoryRequest getInventoryRequest() {
        return inventoryRequest;
    }

    public void setInventoryRequest(InventoryRequest inventoryRequest) {
        this.inventoryRequest = inventoryRequest;
    }

    public VaccineManufacturerRequest getVaccineManufacturerRequest() {
        return vaccineManufacturerRequest;
    }

    public void setVaccineManufacturerRequest(VaccineManufacturerRequest vaccineManufacturerRequest) {
        this.vaccineManufacturerRequest = vaccineManufacturerRequest;
    }
    
    @Override
    public String toString(){
        return userAccount.getUsername();
    }
    
}
