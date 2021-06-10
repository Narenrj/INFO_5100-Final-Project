/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import userinterface.Patient.FindVaccineRegisterJPanel;

/**
 *
 * @author naren
 */
public class VaccinationVendor extends WorkRequest {
    private Network network;
    private Enterprise  enterprise;
    private Organization.Type orgType;
    
    String name;
    String username;
    String address;
    String city;
    String state;
    String email;
    String status;
    
    HashMap<String, Integer> stockOne = new HashMap<>();
    
    private Set<String> slots;

    private  HashMap<String, Set<String>> dates;

    public  HashMap<String, Set<String>> getDates() {
        return dates;
    }
    
    public VaccinationVendor(){
        SimpleDateFormat sdFormat = new SimpleDateFormat("dd");
        Calendar calendar = Calendar.getInstance();
        String day = sdFormat.format( calendar.getTime() );

        dates = new HashMap();
        
        addDates();
    }

    public void setDates(HashMap<String, Set<String>> dates) {
        this.dates = dates;
    }

    
    public HashMap<String, Integer> getStockOne() {
        return stockOne;
    }

    public void setStockOne(HashMap<String, Integer> stockOne) {
        this.stockOne = stockOne;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    public void addDates(){
        
        String date = java.time.LocalDate.now().toString();
        
        try {
            // TODO add your handling code here:
            for(int i =0 ;i <10 ; i++){
                date = getNextDate1(date);
            }
              
        } catch (ParseException ex) {
            Logger.getLogger(FindVaccineRegisterJPanel.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public String getNextDate1(String  date) throws ParseException {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, 1);
        System.out.println("Date: "+ sdf.format(c.getTime()));
        dates.put(sdf.format(c.getTime()), getSlots());
      
        return sdf.format(c.getTime());

    }
}
