/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.Date;

/**
 *
 * @author psrib
 */
public class VaccineProductionRequest extends WorkRequest {
     
    String name;

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
    int quantity;

    public int getQuantity() {
        return quantity;
    }
        String state;

    public String getState() {
        return state;
    }
    public VaccineProductionRequest( String name , int efficiency , String manufacturername , int numberofdoses , String batchnumber , Date date ,int quantity , String state )
    {
        this.name= name;
        this.efficiency =efficiency;
        this.manufacturername = manufacturername;
        this.numberofdoses = numberofdoses;
        this.quantity = quantity;
        this.state=state;
        
    } 
    @Override
    public String toString(){
        return name;
    }
}