/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.Clinic;

import Business.EcoSystem;
import Business.Email.JavaMailService;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.ClinicRegistrationRequest;
import Business.WorkQueue.PatientRegisterationRequest;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Notification;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author psrib
 */
public class ClinicDashboardJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ClinicDashboardJPanel
     */
    JPanel userProcessContainer;
    EcoSystem system;
    private UserAccount userAccount;
    PatientRegisterationRequest p;
    ClinicRegistrationRequest c;

    
    public ClinicDashboardJPanel(JPanel userProcessContainer, UserAccount userAccount, EcoSystem ecosystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system=ecosystem;
        this.userAccount = userAccount;
        
        //jLabel5.setText(getInventoryCount().get("Pfizer").toString());
        populateTable();
        populateStockCountTable();
    }

    public static String getNextDate(String  date) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, 1);
        System.out.println("Date111 "+ sdf.format(c.getTime()));
        return sdf.format(c.getTime());
    }
    
    public HashMap<String, Integer> getInventoryCount(){
        
        for(Network n : system.getNetworkList()){
              
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof ClinicRegistrationRequest){
                        if( ((ClinicRegistrationRequest) workRequest).getClinicName().
                                equals(userAccount.getEmployee().getName())){
                            return ((ClinicRegistrationRequest) workRequest).getInventoryRequest().getVaccineName();
                        }
                    }
                }
            }
               
        }
        return new HashMap();
    }
    
    public void populateStockCountTable(){
        DefaultTableModel dtm = (DefaultTableModel) stock.getModel();
        dtm.setRowCount(0);
         for(Network n : system.getNetworkList()){
              
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof ClinicRegistrationRequest){
                        if( ((ClinicRegistrationRequest) workRequest).getClinicName().
                                equals(userAccount.getEmployee().getName())){
                            HashMap<String, Integer> map = ((ClinicRegistrationRequest) workRequest).getInventoryRequest()
                                    .getVaccineName();
                              for (Map.Entry<String,Integer> entry : map.entrySet()){
                                    Object row[] = new Object[2];
                                    row[0] = entry.getKey();
                                    row[1] = entry.getValue();
                                    dtm.addRow(row);
                              }
         
                        }
                    }
                }
            }
               
        }
    }
    
    public void populateTable(){
         DefaultTableModel dtm = (DefaultTableModel) patientTable.getModel();
        dtm.setRowCount(0);
        
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof PatientRegisterationRequest &&
                            ((PatientRegisterationRequest) workRequest).getClinicRegistrationRequest()!=null){
                        if(((PatientRegisterationRequest) workRequest).getClinicRegistrationRequest().getClinicName().
                                equals(userAccount.getEmployee().getName())){
                            System.out.println("w "+ workRequest);
                            Object row[] = new Object[7];
                            row[0] = ((PatientRegisterationRequest) workRequest);
                            row[1] = ((PatientRegisterationRequest) workRequest).getDob();
                            row[2] = ((PatientRegisterationRequest) workRequest).getAddress();
                            row[3] = ((PatientRegisterationRequest) workRequest).getPhone();
                            row[4] = ((PatientRegisterationRequest) workRequest).getEmail();
                            row[5] = ((PatientRegisterationRequest) workRequest).getCity();
                            row[6] = ((PatientRegisterationRequest) workRequest).getState();

                            //row[4] = ((ClinicRegistrationRequest) workRequest).getPractionerName();
                                              
                        dtm.addRow(row);
                        }
                    }
                }
            }
               
        }
    }
    
    private void sendEmail(String to) {
        try {
            // all values as variables to clarify its usage
            
            String from = "sender@test.com";
            String subject = "Your PDF";
            String text = "Here there is your <b>PDF</b> file!";
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
        checkIN = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        vaccineName = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        stock = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(0, 51, 153));
        setToolTipText("");
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("CLINIC DASHBOARD");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 260, 31));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 490, 130));

        checkIN.setBackground(new java.awt.Color(255, 255, 255));
        checkIN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkIN.setForeground(new java.awt.Color(0, 51, 153));
        checkIN.setText("Check In");
        checkIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkINActionPerformed(evt);
            }
        });
        add(checkIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 669));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-hospital-96.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 110, -1));

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("Clinic");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 80, 40));

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 153));
        jButton2.setText("Patient");
        jButton2.setBorder(null);
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 80, 40));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 760));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/unnamed.png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, -30, 460, 450));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Name");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 700, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 170, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Comments");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 153));
        jButton3.setText("Confirm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("Dose no");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 70, -1));

        jComboBox1.setForeground(new java.awt.Color(0, 51, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dose 1", "Dose 2" }));
        jComboBox1.setToolTipText("");
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 80, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("Vaccine Name");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        vaccineName.setForeground(new java.awt.Color(0, 51, 153));
        vaccineName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pfizer", "Moderna", "Johnson and Johnson", " " }));
        vaccineName.setToolTipText("");
        jPanel3.add(vaccineName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 170, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 570, 600, 190));

        stock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Vaccine Name", "Stock Count"
            }
        ));
        jScrollPane3.setViewportView(stock);
        if (stock.getColumnModel().getColumnCount() > 0) {
            stock.getColumnModel().getColumn(1).setResizable(false);
        }

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, 280, 110));
    }// </editor-fold>//GEN-END:initComponents

    private void checkINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkINActionPerformed

        
        
        

    }//GEN-LAST:event_checkINActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        for(Network n : system.getNetworkList()){
              
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof ClinicRegistrationRequest){
                        if( ((ClinicRegistrationRequest) workRequest).getClinicName().
                                equals(userAccount.getEmployee().getName())){
                            c = (ClinicRegistrationRequest) workRequest;
                        }
                    }
                }
            }
               
        }
        int selectedRow = patientTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        p = (PatientRegisterationRequest)patientTable.getValueAt(selectedRow,0);
        p.setVaccineType(String.valueOf(vaccineName.getSelectedItem()));
        p.setUniqueID();
       
        if(String.valueOf(vaccineName.getSelectedItem()).equals("Dose 1")){
            //p.setFirstDoseDate();
            p.setStatus("Vaccinated Dose 1");
            
            
        }else{
            p.setStatus("Vaccinated Dose 2");
        }
        
        c.getInventoryRequest().reduceStock(String.valueOf(vaccineName.getSelectedItem()), 1);
        populateStockCountTable();
        JOptionPane.showMessageDialog(null, "Appointment Confirmed.", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

        sendEmail("narenrj95@gmail.com");
        
        
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkIN;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable patientTable;
    private javax.swing.JTable stock;
    private javax.swing.JComboBox<String> vaccineName;
    // End of variables declaration//GEN-END:variables
}