/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.PublicHealthDepartment;

import java.util.ArrayList;

/**
 *
 * @author psrib
 */
public class PatientDirectory {
    
    ArrayList<Patient> ap;

    public PatientDirectory() {
        ap = new ArrayList<Patient>();
    }

    
    public ArrayList<Patient> getAp() {
        return ap;
    }

    public void setAp(ArrayList<Patient> ap) {
        this.ap = ap;
    }
    
    public Patient addPatient(String name, String add, String pid){
        Patient p = new Patient();
        p.name = name;
        p.add = add;
        p.pid = pid;
        ap.add(p);
        return p;
    }
    
    
}
