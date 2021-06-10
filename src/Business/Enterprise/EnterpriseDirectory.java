/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import java.util.ArrayList;

/**
 *
 * @author psrib
 */
public class EnterpriseDirectory {
    
    ArrayList<Enterprise> enterpriseDirectoryList;

    public ArrayList<Enterprise> getEnterpriseDirectoryList() {
        return enterpriseDirectoryList;
    }

    public void setEnterpriseDirectoryList(ArrayList<Enterprise> enterpriseDirectoryList) {
        this.enterpriseDirectoryList = enterpriseDirectoryList;
    }
      public EnterpriseDirectory(){
        enterpriseDirectoryList=new ArrayList<Enterprise>();
       
    }
    
    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType type){
        Enterprise enterprise=null;
        if (type == Enterprise.EnterpriseType.PublicHealthDepartment) {
            enterprise = new PublicHealthDepartment(name);
            enterpriseDirectoryList.add(enterprise);
        }else if (type == Enterprise.EnterpriseType.VaccinationSites) {
            enterprise = new VaccinationSites(name);
            enterpriseDirectoryList.add(enterprise);
        }else if (type == Enterprise.EnterpriseType.VaccineProvider) {
            enterprise = new VaccineProviderEnterprise(name , Enterprise.EnterpriseType.VaccineProvider);
            enterpriseDirectoryList.add(enterprise);
        }

        return enterprise;
    }
    
    
    public Enterprise searchEnterprise(String name){
        
        for(Enterprise enterprise : enterpriseDirectoryList){
            if(enterprise.getName().equals(name))
                return enterprise;
        }
        return  null;
    }
    
}
