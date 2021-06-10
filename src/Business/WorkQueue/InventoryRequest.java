/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.HashMap;

/**
 *
 * @author psrib
 */
public class InventoryRequest extends WorkRequest{
    
    HashMap<String, Integer> vaccineName;
    
    public InventoryRequest(){
        vaccineName = new HashMap<>();
    }

    public HashMap<String, Integer> getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(HashMap<String, Integer> vaccineName) {
        this.vaccineName = vaccineName;
    }
    
    public HashMap<String, Integer> reduceStock(String name, int count){
        
        vaccineName.put(name, vaccineName.get(name)-count);
        return vaccineName;
    }
    
    public HashMap<String, Integer> reStock(String name, int count){
        
        vaccineName.put(name, vaccineName.get(name)+count);
        return vaccineName;
    }
    
}
