package Business;

import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.PublicHealthDepartment.PHD;
import Business.PublicHealthDepartment.Patient;
import Business.PublicHealthDepartment.PatientDirectory;
import Business.Role.Analyst;
import Business.Role.CDCAdminRole;
import Business.Role.ClinicRole;
import Business.Role.ExternalDepartmentRole;
import Business.Role.SystemAdminRole;
import Business.Role.VaccinationSitesAdminRole;
import Business.Role.VaccineManuFacturerRole;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.InventoryRequest;
import Business.WorkQueue.PHDAdminRequest;
import Business.WorkQueue.PatientRegisterationRequest;
import Business.WorkQueue.VaccineManufacturerRequest;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author rrheg
 */
public class ConfigureASystem {
    
    private static final String n1 = "California";
    private static final String n2 = "Mass";
    
    public static EcoSystem configure(){
        
        EcoSystem system = EcoSystem.getInstance();
        
        //Create a network
        //create an enterprise
        //initialize some organizations
        //have some employees 
        //create user account
        Network network = system.searchNetworkName(n1);
        if(network ==null){
            network = new Network();
            network.setNetworkName(n1);
            system.setNetworkList(network);
        }
        if(network.getEnterpriseDirectory() == null){
            EnterpriseDirectory ed = new EnterpriseDirectory();
            ed.createAndAddEnterprise("PublicHealthDepartment", 
                    Enterprise.EnterpriseType.PublicHealthDepartment);
            network.setEnterpriseDirectory(ed);
            
        }
                
        Employee employee = system.getEmployeeDirectory().createEmployee("sysadmin");
        UserAccount ua = system.getUserAccountDirectory().createUserAccount("sysadmin", "sysadmin", employee, new SystemAdminRole());
        
        PHDAdminRequest pHDAdminRequest = new PHDAdminRequest();
        pHDAdminRequest.setUserAccount(ua);
        HashMap<String, Integer> map = new HashMap();
        map.put("pfizer", 5);
        map.put("moderna", 5);
        map.put("J&J/Janssen", 5);
        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setVaccineName(map);
        pHDAdminRequest.setInventoryRequest(inventoryRequest);
        
//        PHDAdminRequest pHDAdminRequest1 = new PHDAdminRequest();
//        pHDAdminRequest1.setUserAccount(ua);
//        HashMap<String, Integer> map1 = new HashMap();
//        map1.put("moderna", 5);
//        InventoryRequest inventoryRequest1 = new InventoryRequest();
//        inventoryRequest1.setVaccineName(map1);
//        pHDAdminRequest1.setInventoryRequest(inventoryRequest1);
//        
//        PHDAdminRequest pHDAdminRequest2 = new PHDAdminRequest();
//        pHDAdminRequest2.setUserAccount(ua);
//        HashMap<String, Integer> map2 = new HashMap();
//        map2.put("J&J/Janssen", 5);
//        InventoryRequest inventoryRequest2 = new InventoryRequest();
//        inventoryRequest2.setVaccineName(map2);
//        pHDAdminRequest2.setInventoryRequest(inventoryRequest2);
        

//        UserAccount ua1 = system.getUserAccountDirectory().createUserAccount("p", "p",  employee,new CustomerRole());
        UserAccount ua2 = system.getUserAccountDirectory().createUserAccount("clinic", "clinic", employee , new ClinicRole());
        //VaccinationSitesAdminRole
        UserAccount ua3 = system.getUserAccountDirectory().createUserAccount("vsites", "vsites", employee, new VaccinationSitesAdminRole());
        UserAccount ua4 = system.getUserAccountDirectory().createUserAccount("cdc", "cdc", employee, new CDCAdminRole());

        Employee employee1 = system.getEmployeeDirectory().createEmployee("pfizer");
        Employee employee2 = system.getEmployeeDirectory().createEmployee("moderna");

        UserAccount ua5 = system.getUserAccountDirectory().createUserAccount("pfizer", "pfizer", employee1, new VaccineManuFacturerRole());
        UserAccount ua6 = system.getUserAccountDirectory().createUserAccount("moderna", "moderna", employee2, new VaccineManuFacturerRole());

        
       UserAccount ua7 = system.getUserAccountDirectory().createUserAccount("ext", "ext", employee2, new ExternalDepartmentRole());

//        VaccineManufacturerRequest vaccineManufacturerRequest = new VaccineManufacturerRequest("p1", 90, "pfi", 2, "BatchNO", new Date());
        
        
       
        System.out.println("NET "+ network);
        
        Organization.Type type = Organization.Type.Inventory;
        
        VaccineManufacturerRequest vaccineManufacturerRequest = new VaccineManufacturerRequest("Pfizer-BioNTech", 95, "Pfizer", 2, "JHG656Sz", new Date());
        vaccineManufacturerRequest.setStatus("Approved");
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("Pfizer", 20);
//        vaccineManufacturerRequest.getInventoryRequest().setVaccineName(map);
//        vaccineManufacturerRequest.
       
        VaccineManufacturerRequest vaccineManufacturerRequest1 = new VaccineManufacturerRequest("ModernaTX, Inc.", 90, "Moderna", 2, "KSLSZ99", new Date());
        vaccineManufacturerRequest1.setStatus("Approved");
//        HashMap<String, Integer> map1 = new HashMap<>();
//        map1.put("Moderna", 20);
//        vaccineManufacturerRequest.getInventoryRequest().setVaccineName(map1);
                
        VaccineManufacturerRequest vaccineManufacturerRequest2 = new VaccineManufacturerRequest("Janssen Pharmaceutical", 63, "J&J/Janssen", 1, "JKSOSZ", new Date());
        vaccineManufacturerRequest2.setStatus("Approved");
//        HashMap<String, Integer> map2 = new HashMap<>();
//        map2.put("J&J/Janssen", 10);
//        vaccineManufacturerRequest.getInventoryRequest().setVaccineName(map2);
                
        
        //for(Network n : system.getNetworkList()){
          //  System.out.println("NN "+ n.getNetworkName());
            for(Enterprise e : network.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.PublicHealthDepartment){
                    if(e.getWorkQueue()==null){
                        e.setWorkQueue(new WorkQueue());
                    }
                    e.getWorkQueue().getWorkRequestList().add(vaccineManufacturerRequest);
                    e.getWorkQueue().getWorkRequestList().add(vaccineManufacturerRequest1);
                    e.getWorkQueue().getWorkRequestList().add(vaccineManufacturerRequest2);
                    e.getWorkQueue().getWorkRequestList().add(pHDAdminRequest);
//                    e.getWorkQueue().getWorkRequestList().add(pHDAdminRequest1);
//                    e.getWorkQueue().getWorkRequestList().add(pHDAdminRequest2);

                    

                }
            }
        //}
        
        UserAccount userAccount = system.getUserAccountDirectory().createUserAccount("analyst", "analyst", employee, new Analyst());
        
        
        
        return system;
    }
    
}
