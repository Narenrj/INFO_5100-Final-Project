/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author psrib
 */
public class PatientRegisterationRequest extends WorkRequest{
    
    private Network network;
    private Enterprise  enterprise;
    private Organization.Type orgType;
    private String name;
    private String dob;
    private String address;
    private String phone;
    private String username;
    private String email;
    private String city;
    private String state;
    private HashMap<String, String> slotDate;
    private String vaccineType;
    String uniqueID ;
    private String RegistrationType;
    private int medicalConditons;
    private boolean occupation;

    public int getMedicalConditons() {
        return medicalConditons;
    }

    public void setMedicalConditons(int medicalConditons) {
        this.medicalConditons = medicalConditons;
    }

    public boolean isOccupation() {
        return occupation;
    }

    public void setOccupation(boolean occupation) {
        this.occupation = occupation;
    }

    public String getRegistrationType() {
        return RegistrationType;
    }

    public void setRegistrationType(String RegistrationType) {
        this.RegistrationType = RegistrationType;
    }
    
    public void setUniqueID(){
        uniqueID = UUID.randomUUID().toString();
    }
    
    public String getUniqueID(){
        return uniqueID;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public HashMap<String, String> getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(HashMap<String, String> slotDate) {
        this.slotDate = slotDate;
    }
    ClinicRegistrationRequest clinicRegistrationRequest;
    
    
    public ClinicRegistrationRequest getClinicRegistrationRequest() {
        return clinicRegistrationRequest;
    }

    public void setClinicRegistrationRequest(ClinicRegistrationRequest clinicRegistrationRequest) {
        this.clinicRegistrationRequest = clinicRegistrationRequest;
    }
    VaccinationVendor vaccinationVendor;

    public VaccinationVendor getVaccinationVendor() {
        return vaccinationVendor;
    }

    public void setVaccinationVendor(VaccinationVendor vaccinationVendor) {
        this.vaccinationVendor = vaccinationVendor;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    
}
