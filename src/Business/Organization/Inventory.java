/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author psrib
 */
public class Inventory {
    Inventory inventory = null;
     Map <String , Integer> map = new HashMap();
    public  Inventory getInstance()
    {
        if(inventory == null)
        {
            inventory =  new Inventory();
            
        }
        
        return inventory;
    }
    
    public void addToInventory(String vacccine , int count)
    {
        map.put(vacccine, count);
    }
    
    public int takeStock(String vaccine , int count)
    {
        if(hasStockVaccine(vaccine, count))
        {
            int result = map.get(vaccine);
            map.put(vaccine, result - count);
            return result;
        }
        return -1;
    }
    
    private boolean hasStockVaccine(String vaccine , int count)
    {
        if(map.get(vaccine) != null && map.get(vaccine) > count)
        {
            return true;
        }
        
        return false;
        
    }
}
