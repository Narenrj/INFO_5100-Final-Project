/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author psrib
 */
public class PublicHealthDepartment extends Enterprise{

    public PublicHealthDepartment(String name){
        super(name,EnterpriseType.PublicHealthDepartment);
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      //roles = new ArrayList<Role>();
      
      
    }
    
    
}
