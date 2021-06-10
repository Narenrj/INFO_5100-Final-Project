/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.Patient;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Email.JavaMailService;
import Business.Enterprise.Enterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.ClinicRegistrationRequest;
import Business.WorkQueue.PatientRegisterationRequest;
import Business.WorkQueue.VaccinationVendor;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

//import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import jdk.nashorn.internal.parser.JSONParser;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import sun.rmi.runtime.Log;

/**
 *
 * @author psrib
 */
public class PatientDashboardJPanel extends javax.swing.JPanel {

    /**
     * Creates new form PatientDashboardJPanel
     */
    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
    private static final int MIN_ZOOM = 0;
    private static final int MAX_ZOOM = 21;
    ClinicRegistrationRequest c;
    PatientRegisterationRequest pr;
    VaccinationVendor vaccinationVendor;
    Boolean isNormal = false;
    ButtonGroup G1;
    
        private static final String setMarkerScript =
            "var myLatlng = new google.maps.LatLng(48.4431727,23.0488126);\n" +
                    "var marker = new google.maps.Marker({\n" +
                    "    position: myLatlng,\n" +
                    "    map: map,\n" +
                    "    title: 'Hello World!'\n" +
                    "}); var myLatlng1 = new google.maps.LatLng(48.4431727,23.0488126);\n" +
                    "var marker = new google.maps.Marker({\n" +
                    "    position: myLatlng1,\n" +
                    "    map: map,\n" +
                    "    title: 'Hello World!'\n" +
                    "}); ";

    /**
     * In map.html file default zoom value is set to 4.
     */
    private static int zoomValue = 4;
    
    /**
     * Creates new form LabAssistantWorkAreaJPanel
     */
    public PatientDashboardJPanel(JPanel userProcessContainer, UserAccount account, EcoSystem business) {
        initComponents();
        
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.system = business;
        dashboardPanel.setVisible(false);
        reschedulePanel.setVisible(false);
        
        populateFields();
        
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    System.out.println("Check 2");
                if(workRequest instanceof PatientRegisterationRequest){
                    System.out.println("Check 3");
                    
                    if(((PatientRegisterationRequest) workRequest).getUsername().
                            equals(userAccount.getUsername()) && 
                            (((PatientRegisterationRequest) workRequest).getStatus().equals("Registered")|| 
                            ((PatientRegisterationRequest) workRequest).getStatus().equals("Vaccinated Dose 1") ||
                            ((PatientRegisterationRequest) workRequest).getStatus().equals("Vaccinated Dose 2")))
                            {
                        System.out.println("Registered");
                        dashboardPanel.setVisible(true);
                        jPanel1.setVisible(false);
                        reschedulePanel.setVisible(false);
                        if(((PatientRegisterationRequest) workRequest).getRegistrationType().equals("Normal Queue")){
                        populateRegisterationTable();
                        isNormal = true;
                        //return;
                        }
                        
                        else if(((PatientRegisterationRequest) workRequest).getRegistrationType().equals("Find Vaccine")){
                            populateRegistrationFindVaccineTable();
                                //return;
                        }
                        //return;
                    } else {
                        dashboardPanel.setVisible(false);
                        jPanel1.setVisible(true);
                        reschedulePanel.setVisible(false);
                        //populateRegisterationTable();
                        //return;
                    }
                  }
                }
            }
        }
    }
    
    public void populateFields(){
        String uvalue = userAccount.getUsername();
        //String emailvalue = 
        String nvalue = userAccount.getEmployee().getName();
        System.out.println("Name and Username Values are: "+uvalue+"  "+nvalue);
        name.setText(nvalue);
        
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
    
    public void populateRegisterationTable(){
        DefaultTableModel dtm = (DefaultTableModel) schedule.getModel();
        dtm.setRowCount(0);
        for(Network n : system.getNetworkList()){
              
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof PatientRegisterationRequest){
                        if(((PatientRegisterationRequest) workRequest).getName().
                                equals(userAccount.getEmployee().getName())){
                            System.out.println("w "+ workRequest);
                            Object row[] = new Object[9];
                            row[0] = ((PatientRegisterationRequest) workRequest).getClinicRegistrationRequest();
                            row[1] = ((PatientRegisterationRequest) workRequest).getClinicRegistrationRequest().getAddress();
                            row[2] = ((PatientRegisterationRequest) workRequest).getStatus();
                            row[3] = ((PatientRegisterationRequest) workRequest).getCity();
                            row[4] = ((PatientRegisterationRequest) workRequest);
                            row[5] = ((PatientRegisterationRequest) workRequest).getVaccineType();
                            row[6] = ((PatientRegisterationRequest) workRequest).getFirstDoseDate();
                            row[7] = ((PatientRegisterationRequest) workRequest).getFirstDoseTime();
                            row[8] = ((PatientRegisterationRequest) workRequest).getUniqueID();
                                           
                            dtm.addRow(row);
                        }
                    }
                }
            }
               
        }
        
        
    }
    
    public void populateRegistrationFindVaccineTable(){
        DefaultTableModel dtm = (DefaultTableModel) schedule.getModel();
        dtm.setRowCount(0);
        for(Network n : system.getNetworkList()){
              
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof PatientRegisterationRequest){
                        if(((PatientRegisterationRequest) workRequest).getName().
                                equals(userAccount.getEmployee().getName())){
                            System.out.println("w "+ workRequest);
                            System.out.println("Vaccine Type; "+((PatientRegisterationRequest) workRequest).getVaccineType());
                            Object row[] = new Object[9];
                            row[0] = ((PatientRegisterationRequest) workRequest).getVaccinationVendor();
                            row[1] = ((PatientRegisterationRequest) workRequest).getVaccinationVendor().getAddress();
                            row[2] = ((PatientRegisterationRequest) workRequest).getStatus();
                            row[3] = ((PatientRegisterationRequest) workRequest).getCity();
                            row[4] = ((PatientRegisterationRequest) workRequest);
                            row[5] = ((PatientRegisterationRequest) workRequest).getVaccineType();
                            row[6] = ((PatientRegisterationRequest) workRequest).getFirstDoseDate();
                            row[7] = ((PatientRegisterationRequest) workRequest).getFirstDoseTime();
                            row[8] = ((PatientRegisterationRequest) workRequest).getUniqueID();
                            dtm.addRow(row);
                        }
                    }
                }
            }
        }
    }
//    private boolean strongPassword() {
//        Pattern pat1;
//        pat1 = Pattern.compile("^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$%^&*-]).{6,}$");
//        Matcher m1 = pat1.matcher(pass.getText());
//        boolean bat1 = m1.matches();
//        return bat1;
//    }
    
    public void getRequest(String add) throws MalformedURLException, IOException{
        // Create a neat value object to hold the URL
        HttpURLConnection connection = (HttpURLConnection) new URL("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyDOlTzWL8Eo2ijHa--q9edXVPIwU19GQys").openConnection();
        InputStream inputStream = connection.getInputStream();

        System.out.println("SS "+inputStream.toString());

    }
        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dob = new javax.swing.JTextField();
        phoneNum = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        register = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        city = new javax.swing.JTextField();
        state = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textField1 = new java.awt.TextField();
        Searchbtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dashboardPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        reschedule = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        schedule = new javax.swing.JTable();
        cancel = new javax.swing.JButton();
        reschedulePanel = new javax.swing.JPanel();
        date = new javax.swing.JComboBox<>();
        time = new javax.swing.JComboBox<>();
        confirm = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("PATIENT DASHBOARD");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 352, 69));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("Pre Register");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, 30));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 153));
        jButton2.setText("Find Vaccination Sites");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, -1, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Date of Birth");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Address");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, -1, -1));
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 180, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Phone number");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, -1, -1));

        dob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobActionPerformed(evt);
            }
        });
        jPanel1.add(dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 183, -1));

        phoneNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumActionPerformed(evt);
            }
        });
        jPanel1.add(phoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, 190, -1));

        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });
        jPanel1.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 185, -1));

        register.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        register.setForeground(new java.awt.Color(0, 51, 153));
        register.setText("Register");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        jPanel1.add(register, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 660, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("City");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, -1, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("State");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, -1, -1));
        jPanel1.add(city, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 185, -1));

        state.setBackground(new java.awt.Color(0, 51, 153));
        state.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        state.setForeground(new java.awt.Color(0, 51, 153));
        state.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "California", "Massachusetts" }));
        state.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateActionPerformed(evt);
            }
        });
        jPanel1.add(state, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 185, -1));

        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-registration-30.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 35, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-location-30.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, -1, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Hnet.com-image (1).png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 880, 560));
        jPanel1.add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, 130, -1));

        Searchbtn.setText("Search");
        Searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchbtnActionPerformed(evt);
            }
        });
        jPanel1.add(Searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 197, 70, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 153));
        jLabel14.setText("Email");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, -1, -1));
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 190, -1));

        jLabel8.setText("MM/DD/YYYY");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, -1));

        dashboardPanel.setBackground(new java.awt.Color(255, 255, 255));
        dashboardPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 153));
        jLabel12.setText("REGISTRATION DETAILS");
        dashboardPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 290, 25));

        reschedule.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        reschedule.setForeground(new java.awt.Color(0, 51, 153));
        reschedule.setText("Reschedule Appointment");
        reschedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rescheduleActionPerformed(evt);
            }
        });
        dashboardPanel.add(reschedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, -1, -1));

        schedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Clinic Name", "Clinic Address", "Status", "City", "Name", "Dose Type", "Appointment Date", "Appointment  Time", "Vaccination ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(schedule);

        dashboardPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 800, 151));

        cancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancel.setForeground(new java.awt.Color(0, 51, 153));
        cancel.setText("Cancel Appointment");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        dashboardPanel.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 440, -1, -1));

        reschedulePanel.setBackground(new java.awt.Color(255, 255, 255));
        reschedulePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });
        reschedulePanel.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 64, 150, -1));

        reschedulePanel.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 64, 160, -1));

        confirm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirm.setForeground(new java.awt.Color(0, 51, 153));
        confirm.setText("Confirm");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });
        reschedulePanel.add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        dashboardPanel.add(reschedulePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, 500, 240));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/download.png"))); // NOI18N
        dashboardPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 260, 200));

        add(dashboardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 990, 800));
    }// </editor-fold>//GEN-END:initComponents

    private void dobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dobActionPerformed

    private void phoneNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    public static boolean isValidEmailAddress(String email) {
   boolean result = true;
   try {
      InternetAddress emailAddr = new InternetAddress(email);
      emailAddr.validate();
   } catch (AddressException ex) {
      result = false;
   }
   return result;
}
  
    
    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        // TODO add your handling code here:
        
        if(name.getText().isEmpty() || dob.getText().isEmpty() || address.getText().isEmpty() || city.getText().isEmpty()||
                String.valueOf(state.getSelectedItem()).isEmpty() || phoneNum.getText().isEmpty() || email.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "All the fields are required", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
            return;
        }
         if (!phoneNum.getText().matches("\\d{10}")){
            JOptionPane.showMessageDialog(null, "Enter the valid phone number", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
  
             return ;
         }
         if(!isValidEmailAddress(email.getText())){
            JOptionPane.showMessageDialog(null, "Enter the valid email ID", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
            return ; 
         }
         
         
        System.out.println("User "+ userAccount);
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
        
//        Enterprise enterprise =  network.getEnterpriseDirectory().searchEnterprise("PublicHealthDepartment");
//        
//        if(enterprise ==null){
//            enterprise  = network.getEnterpriseDirectory().createAndAddEnterprise("PublicHealthDepartment",
//            Enterprise.EnterpriseType.PublicHealthDepartment);
//            
//        }else{
//            EnterpriseDirectory ad = network.getEnterpriseDirectory();
//            network.setEnterpriseDirectory(ad);
//        }
        
        
        System.out.println("NET "+ network);
        
        Organization.Type type = Organization.Type.Patient;
        
        
        
        PatientRegisterationRequest patientRegisterationRequest = new PatientRegisterationRequest();
        patientRegisterationRequest.setAddress(address.getText());
        patientRegisterationRequest.setDob(dob.getText());
        patientRegisterationRequest.setNetwork(network);
        patientRegisterationRequest.setOrgType(type);
        patientRegisterationRequest.setPhone(phoneNum.getText());
        patientRegisterationRequest.setUsername(userAccount.getUsername());
        patientRegisterationRequest.setEmail(email.getText());
        patientRegisterationRequest.setName(userAccount.getEmployee().getName());
//        patientRegisterationRequest.setMedicalConditons(Integer.parseInt(mctxt.getText()));
//        patientRegisterationRequest.setOccupation(value);
        patientRegisterationRequest.setCity(city.getText());
        patientRegisterationRequest.setState(String.valueOf(state.getSelectedItem()));
        patientRegisterationRequest.setStatus("Registered");
        patientRegisterationRequest.setRegistrationType("Normal Queue");
        
                
       
        
        for(Network n : system.getNetworkList()){
            System.out.println("NN "+ n.getNetworkName());
            for(Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.PublicHealthDepartment){
                    if(e.getWorkQueue()==null){
                        e.setWorkQueue(new WorkQueue());
                    }
                    e.getWorkQueue().getWorkRequestList().add(patientRegisterationRequest);
                    //e.getWorkQueue().getWorkRequestList().add(patientRegisterationRequest1);
                    dB4OUtil.storeSystem(system);
                    return;
                }
            }
        }
        
        System.out.println("Success");
        JOptionPane.showMessageDialog(null, "Successfully Registered", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

        
        
        
    }//GEN-LAST:event_registerActionPerformed

    private void stateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_stateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

//        System.setProperty("jxbrowser.license.key", "1BNDJDKIKHWX3LLVFZ3E2JZZP6XET8GTGM1QREBZ6RZU6K5ANENMB9DV75BCQHHZZXW9KS");
//
//       EngineOptions options =
//                EngineOptions.newBuilder(HARDWARE_ACCELERATED).licenseKey("1BNDJDKIKHWX3LLVFZ3E2JZZP6XET8GTGM1QREBZ6RZU6K5ANENMB9DV75BCQHHZZXW9KS").build();
//        Engine engine = Engine.newInstance(options);
//        Browser browser = (Browser) engine.newBrowser();
//
//        SwingUtilities.invokeLater(() -> {
//            BrowserView view = BrowserView.newInstance(browser);
//
//          JButton zoomInButton = new JButton("Zoom In");
//            zoomInButton.addActionListener(e -> {
//               if (zoomValue < MAX_ZOOM) {
//                    browser.mainFrame().ifPresent(frame ->
//                            frame.executeJavaScript("map.setZoom(" +
//                                    ++zoomValue + ")"));
//                }
//            });
//
//            JButton zoomOutButton = new JButton("Zoom Out");
//            zoomOutButton.addActionListener(e -> {
//                if (zoomValue > MIN_ZOOM) {
//                    browser.mainFrame().ifPresent(frame ->
//                            frame.executeJavaScript("map.setZoom(" +
//                                    --zoomValue + ")"));
//                }
//            });
//
//            JButton setMarkerButton = new JButton("Set Marker");
//            setMarkerButton.addActionListener(e ->
//                    browser.mainFrame().ifPresent(frame ->
//                            frame.executeJavaScript(setMarkerScript)));
//
//            JPanel toolBar = new JPanel();
//            toolBar.add(zoomInButton);
//            toolBar.add(zoomOutButton);
//          toolBar.add(setMarkerButton);
//
//            JFrame frame = new JFrame("Google Maps");
//            frame.add(toolBar, BorderLayout.SOUTH);
//           frame.add(view, BorderLayout.CENTER);
//            frame.setSize(800, 500);
//           frame.setVisible(true);
//
//            browser.navigation().loadUrl("D:\\Aed Project\\db4odemo\\src\\userinterface\\map.html");
//        });  

       try {

        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyDOlTzWL8Eo2ijHa--q9edXVPIwU19GQys");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
            (conn.getInputStream())));

        String output;
        StringBuilder sb = new StringBuilder();
        
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            sb.append(output);
            
        }
        JSONObject json = new JSONObject(sb.toString());
        JSONArray result = json.getJSONArray("results");
        JSONObject result1 = result.getJSONObject(0);
        JSONObject geometry = result1.getJSONObject("geometry");
        JSONObject locat = geometry.getJSONObject("location");

        //"iterate onto level of location";

        double lat = locat.getDouble("lat");
        double lng = locat.getDouble("lng");
        System.out.println("Lat "+ lat);
        System.out.println("Lon "+ lng);
           //char c [] = sb.toString().split("lat")

//        JSONObject json = new JSONObject(sb.toString());
//        JSONArray result = json.getJSONArray("results");
//        JSONObject result1 = result.getJSONObject(0);
//        JSONObject geometry = result1.getJSONObject("geometry");
//        JSONObject locat = geometry.getJSONObject("location");
//
//        //"iterate onto level of location";
//
//        double lat = locat.getDouble("lat");
//        double lng = locat.getDouble("lng");
//           System.out.println("Lat "+ lat);
//           System.out.println("Lon "+ lng);
//           //char c [] = sb.toString().split("lat")

        
//        String pageName = obj.getJSONObject("results").getString("address_components");
//
//        System.out.println(pageName);

//        JSONArray arr = obj.getJSONArray("results");
//        for (int i = 0; i < arr.length(); i++) {
//            Object post_id = arr.getJSONObject(i).get("geometry");
//            System.out.println("PID "+post_id);
//        }
//
//        conn.disconnect();
//
//      } catch (MalformedURLException e) {
//
//        e.printStackTrace();
//
//      } catch (IOException e) {
//
//        e.printStackTrace();
//
//      }

       } catch (MalformedURLException ex) {
            Logger.getLogger(PatientDashboardJPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PatientDashboardJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.setProperty("jxbrowser.license.key", "1BNDJDKIKHWX3LLVFZ3E2JZZP6XET8GTGM1QREBZ6RZU6K5ANENMB9DV75BCQHHZZXW9KS");
//
//       EngineOptions options =
//                EngineOptions.newBuilder(HARDWARE_ACCELERATED).licenseKey("1BNDJDKIKHWX3LLVFZ3E2JZZP6XET8GTGM1QREBZ6RZU6K5ANENMB9DV75BCQHHZZXW9KS").build();
//        Engine engine = Engine.newInstance(options);
//        Browser browser = (Browser) engine.newBrowser();
//
//        SwingUtilities.invokeLater(() -> {
//            BrowserView view = BrowserView.newInstance(browser);
//
//          JButton zoomInButton = new JButton("Zoom In");
//            zoomInButton.addActionListener(e -> {
//               if (zoomValue < MAX_ZOOM) {
//                    browser.mainFrame().ifPresent(frame ->
//                            frame.executeJavaScript("map.setZoom(" +
//                                    ++zoomValue + ")"));
//                }
//            });
//
//            JButton zoomOutButton = new JButton("Zoom Out");
//            zoomOutButton.addActionListener(e -> {
//                if (zoomValue > MIN_ZOOM) {
//                    browser.mainFrame().ifPresent(frame ->
//                            frame.executeJavaScript("map.setZoom(" +
//                                    --zoomValue + ")"));
//                }
//            });
//
//            JButton setMarkerButton = new JButton("Set Marker");
//            setMarkerButton.addActionListener(e ->
//                    browser.mainFrame().ifPresent(frame ->
//                            frame.executeJavaScript(setMarkerScript)));
//
//            JPanel toolBar = new JPanel();
//            toolBar.add(zoomInButton);
//            toolBar.add(zoomOutButton);
//          toolBar.add(setMarkerButton);
//
//            JFrame frame = new JFrame("Google Maps");
//            frame.add(toolBar, BorderLayout.SOUTH);
//           frame.add(view, BorderLayout.CENTER);
//            frame.setSize(800, 500);
//           frame.setVisible(true);
//
//            //browser.navigation().loadUrl("D:\\Aed Project\\db4odemo\\src\\userinterface\\map.html");
//        });  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof PatientRegisterationRequest){
                        if(((PatientRegisterationRequest) workRequest).getName().equals(userAccount.getEmployee().getName())){
                            pr = (PatientRegisterationRequest) workRequest;
                           
                        }
                    }
                }
            }
               
        }
        pr.setStatus("Cancelled");
        if(isNormal){
            populateRegisterationTable();
        } else {
            populateRegistrationFindVaccineTable();
        }
        
    }//GEN-LAST:event_cancelActionPerformed

    private void rescheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rescheduleActionPerformed
        // TODO add your handling code here:
        
        reschedulePanel.setVisible(true);
        int selectedRow = schedule.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(isNormal){
         c = (ClinicRegistrationRequest)schedule.getValueAt(selectedRow,0);
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
        date.setModel( model1 );
        System.out.println(c.getDates());
        for (String date1 : c.getDates().keySet()){
            date.addItem(date1);
            }   
        } else {
         vaccinationVendor = (VaccinationVendor)schedule.getValueAt(selectedRow,0);
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
        date.setModel( model1 );
        System.out.println(c.getDates());
        for (String date1 : c.getDates().keySet()){
            date.addItem(date1);
          }
        }
        
        
    }//GEN-LAST:event_rescheduleActionPerformed

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
        
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
        time.setModel( model1 );

        //System.out.println(c.getDates());
        String key = String.valueOf(date.getSelectedItem());
        if(isNormal){
          for(String s : c.getSlotforDate(key)){
            time.addItem(s);
        }  
        } else {
            for(String s : vaccinationVendor.getSlotforDate(key)){
            time.addItem(s);
        }
        }
        
    }//GEN-LAST:event_dateActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        // TODO add your handling code here
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof PatientRegisterationRequest){
                        if(((PatientRegisterationRequest) workRequest).getName().equals(userAccount.getEmployee().getName())){
                            pr = (PatientRegisterationRequest) workRequest;
                           
                        }
                    }
                }
            }
               
        }
     
        pr.setFirstDoseDate(String.valueOf(date.getSelectedItem()));
        pr.setFirstDoseTime(String.valueOf(time.getSelectedItem()));
        if(isNormal){
            populateRegisterationTable();
        } else {
            populateRegistrationFindVaccineTable();
        }
       
    }//GEN-LAST:event_confirmActionPerformed

    private void SearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchbtnActionPerformed
        // TODO add your handling code here:
        
        if(textField1.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "City Name field cannot be left blank");
        }
        String cityName = textField1.getText();
//        System.out.println("City Name: "+cityName);

        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
        userProcessContainer.add(new FindVaccineJPanel(userProcessContainer, userAccount, system, cityName));
        layout.next(userProcessContainer);
    }//GEN-LAST:event_SearchbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Searchbtn;
    private javax.swing.JTextField address;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField city;
    private javax.swing.JButton confirm;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JComboBox<String> date;
    private javax.swing.JTextField dob;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField phoneNum;
    private javax.swing.JButton register;
    private javax.swing.JButton reschedule;
    private javax.swing.JPanel reschedulePanel;
    private javax.swing.JTable schedule;
    private javax.swing.JComboBox<String> state;
    private java.awt.TextField textField1;
    private javax.swing.JComboBox<String> time;
    // End of variables declaration//GEN-END:variables
}
