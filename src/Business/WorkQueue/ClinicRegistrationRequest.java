/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import userinterface.Clinic.ClinicDashboardJPanel;
import javax.swing.JPanel;

/**
 *
 * @author psrib
 */
public class ClinicRegistrationRequest extends WorkRequest{
    
    
    private Network network;
    private Enterprise  enterprise;
    private Organization.Type orgType;
    
        
    String clinicName;
    String practionerName;
    String Address;
    String city;
    String state;
    private Set<String> slots;
    InventoryRequest inventoryRequest;

    public InventoryRequest getInventoryRequest() {
        return inventoryRequest;
    }

    public void setInventoryRequest(InventoryRequest inventoryRequest) {
        this.inventoryRequest = inventoryRequest;
    }

    private  HashMap<String, Set<String>> dates;

    public  HashMap<String, Set<String>> getDates() {
        return dates;
    }

    public void setDates(HashMap<String, Set<String>> dates) {
        this.dates = dates;
    }


       
    public ClinicRegistrationRequest(){
        
        SimpleDateFormat sdFormat = new SimpleDateFormat("dd");
        Calendar calendar = Calendar.getInstance();
        String day = sdFormat.format( calendar.getTime() );

        dates = new HashMap();
        
        addDates();
        
    }
    public String getNextDate1(String  date) throws ParseException {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, 1);
        System.out.println("Date111 "+ sdf.format(c.getTime()));
        dates.put(sdf.format(c.getTime()), getSlots());
      
        return sdf.format(c.getTime());

    }

    
    public void addDates(){
        
        String date = java.time.LocalDate.now().toString();
        
        try {
            // TODO add your handling code here:
            for(int i =0 ;i <10 ; i++)
               date = getNextDate1(date);
              
        } catch (ParseException ex) {
            Logger.getLogger(ClinicDashboardJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Maps "+ dates);

    }
    
    
    public Set<String> getSlots() {
       
        slots = new HashSet<>();
        slots.add("10 AM");
        slots.add("10:30 AM");
        slots.add("11 AM");
        slots.add("11:30 PM");
        slots.add("12:00 PM");
        slots.add("12:30 PM");
        slots.add("1:00 PM");
        slots.add("1:30 PM");
        slots.add("2:00 PM");
        slots.add("2:30 PM");
        slots.add("3:00 PM");

        return slots;
    }

    public void setSlots(Set<String> slots) {
        this.slots = slots;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Organization.Type getOrgType() {
        return orgType;
    }

    public void setOrgType(Organization.Type orgType) {
        this.orgType = orgType;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getPractionerName() {
        return practionerName;
    }

    public void setPractionerName(String practionerName) {
        this.practionerName = practionerName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    

    @Override
    public String toString(){
        return clinicName;
    }

      
    public Set<String> deleteSlot(String time, String date){
        //System.out.println("Arrays "+ Arrays.toString(slots));
     
        dates.get(date).remove(time);
                
        
        return slots;
    }
    
    public HashMap<String, Set<String>> deleteDate(String d){
        dates.remove(d);
        return dates;
    }
    
    public Set<String> getSlotforDate(String date){
        
        return dates.get(date);
    }
    
    
}
