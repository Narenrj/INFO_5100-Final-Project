/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.SystemAdminWorkArea;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Network.Network;
import Business.Role.SystemAdminRole;
import Business.Role.VaccineManuFacturerRole;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.ClinicRegistrationRequest;
import Business.WorkQueue.PatientRegisterationRequest;
import Business.WorkQueue.VaccineProductionRequest;
import Business.WorkQueue.WorkQueue;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author psrib
 */
public class NewManufacturerJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewManufacturer
     */
    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
        private DB4OUtil dB4OUtil = DB4OUtil.getInstance();

    private PatientRegisterationRequest patientRegisterationRequest;

    private ClinicRegistrationRequest c;



    public NewManufacturerJPanel(JPanel userProcessContainer, EcoSystem system, UserAccount userAccount) {
        initComponents();
        
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.system = system;
       
    }
    private boolean strongPassword() {
        Pattern pat1;
        pat1 = Pattern.compile("^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$%^&*-]).{6,}$");
        Matcher m1 = pat1.matcher(password.getText());
        boolean bat1 = m1.matches();
        return bat1;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        efficacy = new javax.swing.JTextField();
        doses = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        password = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        state = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("REGISTER");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 141, 44));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Name");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Vaccine Efficacy");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("No of Doses");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Username");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, -1, -1));

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 149, -1));
        add(efficacy, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 150, -1));
        add(doses, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, 149, -1));

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 400, 149, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Password");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, -1, -1));
        add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 457, 149, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("Register");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 592, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/istockphoto-1289767581-612x612.png"))); // NOI18N
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 610, 500));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("State");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 520, 70, -1));

        state.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "California", "Massachusetts", " " }));
        add(state, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 520, 160, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        if(name.getText().isEmpty() || efficacy.getText().isEmpty() || doses.getText().isEmpty() ||
                username.getText().isEmpty() || password.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "All the fields are required", "InfoBox: ", JOptionPane.ERROR_MESSAGE);
           return;

        }
        if(!strongPassword()){
             JOptionPane.showMessageDialog(null, "Strong password required", "InfoBox: ", JOptionPane.ERROR_MESSAGE);
           return;
        }
        VaccineProductionRequest vaccineProductionRequest = new VaccineProductionRequest(name.getText(), Integer.parseInt(efficacy.getText()), name.getText(), Integer.parseInt(doses.getText()), "", new Date(), 0, String.valueOf(state.getSelectedItem()));
        vaccineProductionRequest.setStatus("Waiting Aproval");
        
        UserAccount ua = new UserAccount();
        ua.setUsername(username.getText());
        ua.setPassword(password.getText());
        
        Employee employee = new Employee();
        employee.setName(name.getText());
        ua.setEmployee(employee);
        system.getUserAccountDirectory().createUserAccount(username.getText(), password.getText(), employee, new VaccineManuFacturerRole());

        
        Network network = system.searchNetworkName(String.valueOf(state.getSelectedItem()));
        if(network ==null){
            network = new Network();
            network.setNetworkName(String.valueOf(state.getSelectedItem()));
            system.setNetworkList(network);
        }
        if(network.getEnterpriseDirectory() == null){
            EnterpriseDirectory ed = new EnterpriseDirectory();
            ed.createAndAddEnterprise("PublicHealthDepartment", 
                    Enterprise.EnterpriseType.PublicHealthDepartment);
            network.setEnterpriseDirectory(ed);
            
        }
        
        for(Enterprise e : network.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.PublicHealthDepartment){
                    if(e.getWorkQueue()==null){
                        e.setWorkQueue(new WorkQueue());
                    }
                    e.getWorkQueue().getWorkRequestList().add(vaccineProductionRequest);
              

                }
            }
        dB4OUtil.storeSystem(system);
        JOptionPane.showMessageDialog(null, "Successfully Registered", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField doses;
    private javax.swing.JTextField efficacy;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField name;
    private javax.swing.JTextField password;
    private javax.swing.JComboBox<String> state;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
