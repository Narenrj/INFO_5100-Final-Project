/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.SystemAdminWorkArea;

import Business.EcoSystem;

import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author MyPC1
 */
public class SystemAdminWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SystemAdminWorkAreaJPanel
     */
    JPanel userProcessContainer;
    EcoSystem ecosystem;
    UserAccount userAccount;
    public SystemAdminWorkAreaJPanel(JPanel userProcessContainer,EcoSystem ecosystem, UserAccount userAccount) {
        initComponents();
        this.userProcessContainer=userProcessContainer;
        this.ecosystem=ecosystem;
        this.userAccount = userAccount;
       
    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        managePatient = new javax.swing.JButton();
        addClinic = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        manageInventory = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(280, 148));
        jPanel1.setPreferredSize(new java.awt.Dimension(280, 148));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        managePatient.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        managePatient.setForeground(new java.awt.Color(0, 51, 153));
        managePatient.setText("Manage Patients");
        managePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managePatientActionPerformed(evt);
            }
        });
        jPanel1.add(managePatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 270, 150, 30));

        addClinic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addClinic.setForeground(new java.awt.Color(0, 51, 153));
        addClinic.setText("Manage Clinic");
        addClinic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClinicActionPerformed(evt);
            }
        });
        jPanel1.add(addClinic, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 342, 130, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Hnet.com-image (3).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 27, 207, -1));

        manageInventory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        manageInventory.setForeground(new java.awt.Color(0, 51, 153));
        manageInventory.setText("Manage Inventory");
        manageInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageInventoryActionPerformed(evt);
            }
        });
        jPanel1.add(manageInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 170, -1));

        jSplitPane.setLeftComponent(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/PHD1.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 595, 392));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("PUBLIC HEALTH DEAPARTMENT");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 380, 42));

        jSplitPane.setRightComponent(jPanel2);

        add(jSplitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addClinicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClinicActionPerformed
        ManageClinicJPanel manageClinicJPanel = new ManageClinicJPanel(userProcessContainer, ecosystem);
        userProcessContainer.add("Manage Clinic", manageClinicJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
        
    }//GEN-LAST:event_addClinicActionPerformed

    private void managePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managePatientActionPerformed
        // TODO add your handling code here:
        ManagePatientsJPanel managePatientsJPanel = new ManagePatientsJPanel(userProcessContainer, ecosystem);
        userProcessContainer.add("Manage Patient", managePatientsJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_managePatientActionPerformed

    private void manageInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageInventoryActionPerformed
        // TODO add your handling code here:
        ManageInventory manageInventory = new ManageInventory(userProcessContainer, ecosystem, userAccount);
        userProcessContainer.add("Manage Inventory", manageInventory);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_manageInventoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClinic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JButton manageInventory;
    private javax.swing.JButton managePatient;
    // End of variables declaration//GEN-END:variables
}