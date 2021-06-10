/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.SystemAdminWorkArea;

import Business.EcoSystem;
import Business.Email.JavaMailService;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.ClinicRegistrationRequest;
import Business.WorkQueue.PatientRegisterationRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.util.ArrayList;

import javafx.util.converter.DateStringConverter;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author psrib
 */
public class ManagePatientsJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManagePatientsJPanel
     */
    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    
    private PatientRegisterationRequest patientRegisterationRequest;

    private ClinicRegistrationRequest c;



    public ManagePatientsJPanel(JPanel userProcessContainer, EcoSystem system) {
        initComponents();
        
        this.userProcessContainer = userProcessContainer;
       // this.userAccount = account;
        this.system = system;
       
       populatePatientTable();
    }
    
    
    public void populatePatientTable(){
        
        
        DefaultTableModel dtm = (DefaultTableModel) patientTable.getModel();
        dtm.setRowCount(0);
        
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof PatientRegisterationRequest){
                        System.out.println("w "+ workRequest);
                        Object row[] = new Object[7];
                        row[0] = ((PatientRegisterationRequest) workRequest);
                        row[1] = ((PatientRegisterationRequest) workRequest).getDob();
                        row[2] = ((PatientRegisterationRequest) workRequest).getAddress();
                        row[3] = ((PatientRegisterationRequest) workRequest).getPhone();
                        row[4] = ((PatientRegisterationRequest) workRequest).getEmail();
                        row[5] = ((PatientRegisterationRequest) workRequest).getCity();
                        row[6] = ((PatientRegisterationRequest) workRequest).getState();

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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        clinicTable = new javax.swing.JTable();
        checkSlots = new javax.swing.JButton();
        assignToClinic = new javax.swing.JButton();
        date = new javax.swing.JComboBox<>();
        slots = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("MANAGE PATIENTS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 240, 40));

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Date of Birth", "Address", "Phone Number", "Email", "City", "State"
            }
        ));
        jScrollPane1.setViewportView(patientTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 790, 141));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("Find nearest Clinic");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, 188, -1));

        clinicTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Clinic Name", "Practitioner Name", "Address", "City"
            }
        ));
        jScrollPane3.setViewportView(clinicTable);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 610, 820, 170));

        checkSlots.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkSlots.setForeground(new java.awt.Color(0, 51, 153));
        checkSlots.setText("Check Slots");
        checkSlots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSlotsActionPerformed(evt);
            }
        });
        add(checkSlots, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 820, -1, -1));

        assignToClinic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        assignToClinic.setForeground(new java.awt.Color(0, 51, 153));
        assignToClinic.setText("Assign to Clinic");
        assignToClinic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignToClinicActionPerformed(evt);
            }
        });
        add(assignToClinic, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 820, -1, -1));

        date.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });
        add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 820, 130, -1));

        slots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slotsActionPerformed(evt);
            }
        });
        add(slots, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 820, 90, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Hnet (1).png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, 310));

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void checkSlotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSlotsActionPerformed
        // TODO add your handling code here:
        int selectedRow = clinicTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        c = (ClinicRegistrationRequest)clinicTable.getValueAt(selectedRow,0);


     
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
        date.setModel( model1 );
        System.out.println(c.getDates());
        for (String date1 : c.getDates().keySet()){
            date.addItem(date1);
        }


    }//GEN-LAST:event_checkSlotsActionPerformed

     public void getClinicDetails(PatientRegisterationRequest p){
        DefaultTableModel dtm = (DefaultTableModel) clinicTable.getModel();
        dtm.setRowCount(0);
        
        System.out.println("Name "+ p.getName());
         for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof ClinicRegistrationRequest){
                        if(((ClinicRegistrationRequest) workRequest).getCity().equalsIgnoreCase(p.getCity())){
                            System.out.println("w "+ workRequest);
                            Object row[] = new Object[4];
                            row[0] = ((ClinicRegistrationRequest) workRequest);
                            row[1] = ((ClinicRegistrationRequest) workRequest).getPractionerName();
                            row[2] = ((ClinicRegistrationRequest) workRequest).getAddress();
                            row[3] = ((ClinicRegistrationRequest) workRequest).getCity();
                           // row[4] = ((ClinicRegistrationRequest) workRequest).getPractionerName();

                            dtm.addRow(row);
                        }
                    }
                }
            }
               
        }
        
    }
     private void sendEmail(String to, String text) {
        try {
            // all values as variables to clarify its usage
            
            String from = "sender@test.com";
            String subject = "Your upcoming COVID-19 vaccination appointment.";
           // String text = "Here there is your <b>PDF</b> file!";
            String fileName = "file.pdf";
            String mimeType = "application/pdf";
            // call the mail service to send the message
            JavaMailService.send(from, to, subject, text,  fileName, mimeType);
            System.out.println("Email sent");
          
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email "+ e);
        }
    } 
     
    private void assignToClinicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignToClinicActionPerformed
        // TODO add your handling code here:
        int selectedRow = clinicTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        c = (ClinicRegistrationRequest)clinicTable.getValueAt(selectedRow,0);
        patientRegisterationRequest.setClinicRegistrationRequest(c);
        patientRegisterationRequest.setStatus("Registered");
        patientRegisterationRequest.setFirstDoseDate(String.valueOf(date.getSelectedItem()));
        patientRegisterationRequest.setFirstDoseTime(String.valueOf(slots.getSelectedItem()));
        c.deleteSlot(String.valueOf(slots.getSelectedItem()),String.valueOf(date.getSelectedItem()) );
        System.out.println("Done");
        
        String text = "Hello "+patientRegisterationRequest.getName()+",\n" +
"\n" +
"Thank you for scheduling a COVID-19 vaccination appointment. The appointment details are "
                +patientRegisterationRequest.getClinicRegistrationRequest().getClinicName()+" address "
                +patientRegisterationRequest.getClinicRegistrationRequest().getAddress() + ""
                +patientRegisterationRequest.getFirstDoseDate() +" time :"
                +patientRegisterationRequest.getFirstDoseTime();
        
        sendEmail(patientRegisterationRequest.getEmail(), text);

    }//GEN-LAST:event_assignToClinicActionPerformed

    private void slotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slotsActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_slotsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = patientTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        patientRegisterationRequest = (PatientRegisterationRequest)patientTable.getValueAt(selectedRow,0);
        getClinicDetails(patientRegisterationRequest);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
        System.out.println("GET slots ******");
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
        slots.setModel( model1 );

        //System.out.println(c.getDates());
        String key = String.valueOf(date.getSelectedItem());
        for(String s : c.getSlotforDate(key)){
            slots.addItem(s);
        }
        
    }//GEN-LAST:event_dateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignToClinic;
    private javax.swing.JButton checkSlots;
    private javax.swing.JTable clinicTable;
    private javax.swing.JComboBox<String> date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable patientTable;
    private javax.swing.JComboBox<String> slots;
    // End of variables declaration//GEN-END:variables
}
