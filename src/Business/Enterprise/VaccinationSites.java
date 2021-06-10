/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Enterprise.Enterprise.EnterpriseType;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author naren
 */
public class VaccinationSites extends Enterprise {
   
    public VaccinationSites(String name){
        super(name, EnterpriseType.VaccinationSites);
    }

    public ArrayList<Role> getSupportedRole() {
     throw new UnsupportedOperationException("Not supported yet.");   
    }
    
}
