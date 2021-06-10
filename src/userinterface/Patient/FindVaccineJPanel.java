/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.Patient;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.VaccinationVendor;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author naren
 */
public class FindVaccineJPanel extends javax.swing.JPanel {
    
    
    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    private VaccinationVendor vaccinationVendor;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
    String cname;
    
    /**
     * Creates new form VendorDashboardJPanel
     */
    public FindVaccineJPanel(JPanel userProcessContainer, UserAccount account, EcoSystem business, String cityName) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.system = business;
        this.cname = cityName;
       // ListOfVaccinationSitesJPanel.setVisible(false);
        VaccineInformationJPanel.setVisible(false);
        populateSearchResults();
        
    }
    
    
    
    public void populateSearchResults(){
        
        String cityName = cname;
        DefaultTableModel dtm = (DefaultTableModel) ListOfVS.getModel();
        dtm.setRowCount(0);
        for(Network n : system.getNetworkList()){
              
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof VaccinationVendor){
                        System.out.println("w "+ workRequest);
                        if(((VaccinationVendor) workRequest).getCity().equals(cityName) && ((VaccinationVendor) workRequest).getStatus().equals("Approved") ){
                            Object row[] = new Object[3];
                        row[0] = ((VaccinationVendor) workRequest);
                        row[1] = ((VaccinationVendor) workRequest).getAddress();
                        row[2] = ((VaccinationVendor) workRequest).getCity();
                        dtm.addRow(row);
                        }
                    }
                }
            }
               
        }
    }
    
    
    public void populateDose1Details(){

        int selectedRow = ListOfVS.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        vaccinationVendor = (VaccinationVendor)ListOfVS.getValueAt(selectedRow,0);
        String name  =  vaccinationVendor.getName();
        
        
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        System.out.println(system.getNetworkList().size());
        
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        
        
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof VaccinationVendor){
                        //System.out.println("w "+ workRequest);
                        hm = ((VaccinationVendor) workRequest).getStockOne();
                        Iterator hmIterator = hm.entrySet().iterator();
                        //System.out.println("HashMap Assigned Values: "+hm);
                        Object row[] = new Object[2];
                        while (hmIterator.hasNext() && ((VaccinationVendor) workRequest).getName().equals(name)) {
                            Map.Entry mapElement = (Map.Entry)hmIterator.next();
                            row[0] = mapElement.getKey();
                            if(((int)mapElement.getValue()) == 0){
                                row[1] = "Out Of Stock";
                            } else {
                              row[1] = ((int)mapElement.getValue());  
                            }
                            
                            dtm.addRow(row);
                        }
                    }
                }
            }
               
        }
    }
    
    
    public void scheduleFirstVaccineShot(){

        String name  =  vaccinationVendor.getName();
        
        //Vaccine Provider Name
        int selectedRow1 = jTable1.getSelectedRow();
        String vName = (String) jTable1.getValueAt(selectedRow1, 0);
        System.out.println("Row Number: "+selectedRow1+" Vaccine Name: "+vName);
        
        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
        userProcessContainer.add(new FindVaccineRegisterJPanel(userProcessContainer, userAccount, system, vaccinationVendor,name, vName));
        layout.next(userProcessContainer);
    }
    
    
    
    /**
     * Creates new form FindVaccineJPanel
     */
//    public FindVaccineJPanel() {
//        initComponents();
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Registerbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        VaccineInformationJPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BookSlotbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListOfVS = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Registerbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Registerbtn.setForeground(new java.awt.Color(0, 51, 153));
        Registerbtn.setText("Request Info");
        Registerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterbtnActionPerformed(evt);
            }
        });
        add(Registerbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("FIND VACCINES");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 160, 37));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/FIND VACCINE.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 520, 210));

        VaccineInformationJPanel.setBackground(new java.awt.Color(255, 255, 255));
        VaccineInformationJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Vaccine Name", "Availabillity Status"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        VaccineInformationJPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 120));

        BookSlotbtn.setText("Book Slot");
        BookSlotbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookSlotbtnActionPerformed(evt);
            }
        });
        VaccineInformationJPanel.add(BookSlotbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

        add(VaccineInformationJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 560, 330));

        ListOfVS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Vendor Name", "Address", "City"
            }
        ));
        jScrollPane1.setViewportView(ListOfVS);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 346, 103));
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterbtnActionPerformed
        // TODO add your handling code here:
        VaccineInformationJPanel.setVisible(true);
        populateDose1Details();
        
    }//GEN-LAST:event_RegisterbtnActionPerformed

    private void BookSlotbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookSlotbtnActionPerformed
        // TODO add your handling code here:
        //VaccineInformationJPanel.setVisible(true);
       if(jTable1.getSelectedRow() >= 0){
            System.out.println("######Table#########1");
            scheduleFirstVaccineShot();
            
        } else {
            JOptionPane.showMessageDialog(null,"Please Select a Record", "Warning", JOptionPane.WARNING_MESSAGE);
            System.out.println("XXXXX");
        }      
    }//GEN-LAST:event_BookSlotbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BookSlotbtn;
    private javax.swing.JTable ListOfVS;
    private javax.swing.JButton Registerbtn;
    private javax.swing.JPanel VaccineInformationJPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

