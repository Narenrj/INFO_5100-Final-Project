/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.Vendor;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Role.PatientRole;
import Business.Role.VendorRole;
import Business.WorkQueue.VaccinationVendor;
import Business.WorkQueue.WorkRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author naren
 */
public class VendorSignUpJPanel extends javax.swing.JPanel {

    /**
     * Creates new form VendorSignUpJPanel
     */
    EcoSystem system;
    private JPanel userProcessContainer;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();

    public VendorSignUpJPanel(JPanel userProcessContainer, EcoSystem ecoSystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system=ecoSystem;
        //this.organization=organization;
        
//        RegisterJPanel.setVisible(false);
//        VendorDashboardJPanel.setVisible(false);
//        ApplicationPendingJPanel.setVisible(false);
//        
//        String uname = userAccount.getUsername();
//        System.out.println(system.getUserAccountDirectory().getUserAccountList());
//        
//        for(Network n : system.getNetworkList()){
//               
//            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
//                System.out.println("Check 1");
//                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
//                    System.out.println("Check 2");
//                if(workRequest instanceof VaccinationVendor){
//                    System.out.println("Check 3");
//                    
//                    if(((VaccinationVendor) workRequest).getName().equals(uname) && ((VaccinationVendor) workRequest).getStatus().equals("Pending Approval") ){
//                        System.out.println("HVJ condition");
//                        
////                        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
////                        System.out.println("Pending Panel");
////                        userProcessContainer.add(new VendorApplicationPendingJPanel(userProcessContainer, account, business));
////                        layout.next(userProcessContainer);
//                        RegisterJPanel.setVisible(false);
//                        VendorDashboardJPanel.setVisible(false);
//                        ApplicationPendingJPanel.setVisible(true);
//                        break;
//                        
//                        //new VendorApplicationPendingJPanel(userProcessContainer,account,business);
//                    }
//                    else if (((VaccinationVendor) workRequest).getName().equals(uname) && ((VaccinationVendor) workRequest).getStatus().equals("Approved")){
//                            
////                        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
////                        userProcessContainer.add(new VendorDashboardJPanel(userProcessContainer, account, business));
////                        layout.next(userProcessContainer);
////                        break;
//                        RegisterJPanel.setVisible(false);
//                        ApplicationPendingJPanel.setVisible(false);
//                        VendorDashboardJPanel.setVisible(true);
//                        break;
//                        //new VendorDashboardJPanel(userProcessContainer,account,business);
//                    }
////                    else if (((VaccinationVendor) workRequest).getName().equals(uname) && ((VaccinationVendor) workRequest).getStatus().isEmpty()){
////                        initComponents();
////                       System.out.println("Empty Status");
////                    } 
//                    else {
//                        System.out.println("No conditions satisfied");
//                        
//                        VendorDashboardJPanel.setVisible(false);
//                        ApplicationPendingJPanel.setVisible(false);
//                        RegisterJPanel.setVisible(true);
//                        
//                    }
//                    
//                  }
//                }
//            }
//           
//                       
//                    
//        }
    }
    
    private boolean strongUsername() {
        Pattern pat = Pattern.compile("^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$%^&*-]).{6,}$");
        Matcher m = pat.matcher(userName.getText());
        boolean boo = m.matches();
        return boo;
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
        pass = new java.awt.TextField();
        userName = new java.awt.TextField();
        Createbtn = new java.awt.Button();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("VENDOR SIGN UP");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 307, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("User Name:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Password:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, -1, -1));
        add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 110, -1));

        userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameActionPerformed(evt);
            }
        });
        add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 110, -1));

        Createbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Createbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Createbtn.setForeground(new java.awt.Color(0, 51, 153));
        Createbtn.setLabel("Sign Up");
        Createbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreatebtnActionPerformed(evt);
            }
        });
        add(Createbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 430, -1, 30));
        Createbtn.getAccessibleContext().setAccessibleName("");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vaccinesignup.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 340, 390));
    }// </editor-fold>//GEN-END:initComponents

    private void userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameActionPerformed

    private void CreatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreatebtnActionPerformed
        // TODO add your handling code here:
        boolean val = strongUsername();
        //boolean val1 = strongPassword();
        if(userName.getText().isEmpty() || val || pass.getText().isEmpty() || pass.getText().isEmpty()){
            //JOptionPane.showMessageDialog(null, "Name field cannot be left blank or in wrong format");
            JOptionPane.showMessageDialog(null, "User Name or Password field cannot be left blank or Entered in wrong format", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
        }
       Employee emp = new Employee();
        emp.setName(userName.getText());
        system.getUserAccountDirectory().createUserAccount(userName.getText(), pass.getText(), emp, new VendorRole());
        dB4OUtil.storeSystem(system);  
        JOptionPane.showMessageDialog(null, "Success", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

        System.out.println("Sign Up");
    }//GEN-LAST:event_CreatebtnActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button Createbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private java.awt.TextField pass;
    private java.awt.TextField userName;
    // End of variables declaration//GEN-END:variables
}
