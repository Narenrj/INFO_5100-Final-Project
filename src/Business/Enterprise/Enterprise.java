/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.Inventory;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import Business.PublicHealthDepartment.Patient;
import Business.PublicHealthDepartment.PatientDirectory;

/**
 *
 * @author psrib
 */
public abstract class Enterprise extends Organization {

    private EnterpriseType enterpriseType;
    private PatientDirectory patientDirectory;
    private OrganizationDirectory organizationDirectory;
    public Inventory inventory;

    //private Patient patient;

   public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public void setOrganizationDirectory(OrganizationDirectory organizationDirectory) {
        this.organizationDirectory = organizationDirectory;
    }

    
    public void addStock(String vaccine , int count)
    {
        inventory.addToInventory(vaccine, count);
    }
    
    public int takeStock(String vaccine , int count)
    {
         return inventory.takeStock(vaccine, count);
    }
  

    public PatientDirectory getPatientDirectory() {
        return patientDirectory;
    }

    public void setPatientDirectory(PatientDirectory patientDirectory) {
        this.patientDirectory = patientDirectory;
    }
  
  
    public enum EnterpriseType {
        
        PublicHealthDepartment("PublicHealthDepartment"),VaccinationSites("VaccinationSites"),
        CDC("CDC"),VaccineProvider("VaccineProvider");
        
        private String value;

        private EnterpriseType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public Enterprise(String name, EnterpriseType type) {
        super(name);
        this.enterpriseType = type;

        if (enterpriseType == EnterpriseType.PublicHealthDepartment) {
            patientDirectory = new PatientDirectory();
            
        } else if (enterpriseType == EnterpriseType.VaccinationSites) {
            
        }
        
        organizationDirectory = new OrganizationDirectory();
       inventory = new Inventory();
    }
   @Override
    public String toString(){
        return this.getName();
    }

}
