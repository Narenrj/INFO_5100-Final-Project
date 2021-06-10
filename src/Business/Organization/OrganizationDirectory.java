/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import java.util.ArrayList;

/**
 *
 * @author psrib
 */
public class OrganizationDirectory {
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }

    public Organization createOrganization(Organization.Type type) {
        Organization organization = null;

        if (type.getValue().equals(Organization.Type.Patient.getValue())) {
            organization = new PatientOrganization();
        } else if (type.getValue().equals(Organization.Type.Clinic.getValue())) {
            organization = new ClinicOrganization();
        } 
        organizationList.add(organization);
        return organization;
    }
    public void deleteOrganization(Organization organization)
    {
      organizationList.remove(organization);
    }
}
