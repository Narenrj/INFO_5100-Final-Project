/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.VaccineManufacturer;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.PHDAdminRequest;
import Business.WorkQueue.VaccineManufacturerRequest;
import Business.WorkQueue.VaccineProductionRequest;
import Business.WorkQueue.WorkRequest;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author psrib
 */
public class VaccineManufacturerJPanel extends javax.swing.JPanel {

    /**
     * Creates new form VaccineManufacturerJPanel
     */
    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    PHDAdminRequest phdar;
    
    

    public VaccineManufacturerJPanel(JPanel userProcessContainer, EcoSystem system , UserAccount userAccount) {
        initComponents();
        
        this.userProcessContainer = userProcessContainer;
       // this.userAccount = account;
        this.system = system;
        this.userAccount = userAccount;
       populateTable();
       
    }
    public void populateTable()
    {
      DefaultTableModel dtm = (DefaultTableModel) patientTable1.getModel();
        dtm.setRowCount(0);
        
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof VaccineManufacturerRequest && 
           ((VaccineManufacturerRequest) workRequest).getManufacturername().toString().equalsIgnoreCase(userAccount.getUsername())
                    &&   ((VaccineManufacturerRequest) workRequest).getStatus().equals("Ordered")){
                        System.out.println("w "+ workRequest);
                        Object row[] = new Object[4];
                        row[0] = ((VaccineManufacturerRequest) workRequest).getSender().getUsername();
                        row[1] = ((VaccineManufacturerRequest) workRequest).getMap().get(((VaccineManufacturerRequest) workRequest)
                                .getName().toLowerCase());
                                
                        row[2] = ((VaccineManufacturerRequest) workRequest).getRequestDate();
                        row[3] = ((VaccineManufacturerRequest) workRequest).getStatus();
                                             
                       // row[4] = ((ClinicRegistrationRequest) workRequest).getPractionerName();

                        
                       
                        dtm.addRow(row);
                        
                    }
                }
            }
               
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        patientTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("Accept");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 630, -1, -1));

        patientTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Vendor Name", "No of Units", "Order Placed Date", "Status"
            }
        ));
        jScrollPane2.setViewportView(patientTable1);
        if (patientTable1.getColumnModel().getColumnCount() > 0) {
            patientTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 980, 140));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 153));
        jButton2.setText("Reject");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 630, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Hnet (2).png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 1020, 360));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("VACCINE MANUFACTURER");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 350, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
           
       int selectedRow = patientTable1.getSelectedRow();
       if (selectedRow < 0){
            return;
        }
        String s = (String) patientTable1.getValueAt(selectedRow, 0);
        for(Network n : system.getNetworkList()){
          
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof PHDAdminRequest && 
           ((PHDAdminRequest) workRequest).toString().equalsIgnoreCase(s)){
                        System.out.println("w "+ workRequest);
                        phdar =(PHDAdminRequest) workRequest;
                       
                    }
                }
            }
               
        }
        System.out.println("SS "+ s);
        Integer units = (int) patientTable1.getValueAt(selectedRow, 1);
        Integer count = phdar.getInventoryRequest().getVaccineName().get(userAccount.getUsername());
        HashMap<String, Integer> map = phdar.getInventoryRequest().getVaccineName();
        
        if(!map.containsKey(userAccount.getUsername())){
            map.put(userAccount.getUsername(), units);
        }else        
            map.put(userAccount.getUsername(), phdar.getInventoryRequest().getVaccineName().get(userAccount.getUsername()) + units);
//        

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selectedRow = patientTable1.getSelectedRow();
        
        if (selectedRow < 0){
            return;
        }
        VaccineProductionRequest request = (VaccineProductionRequest)patientTable1.getValueAt(selectedRow, 0);
        
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable patientTable1;
    // End of variables declaration//GEN-END:variables
}
