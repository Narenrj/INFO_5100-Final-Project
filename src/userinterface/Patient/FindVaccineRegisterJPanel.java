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
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.PatientRegisterationRequest;
import Business.WorkQueue.VaccinationVendor;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.CardLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author naren
 */
public class FindVaccineRegisterJPanel extends javax.swing.JPanel {
    
    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    private VaccinationVendor vaccinationVendor;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
//    private PatientRegisterationRequest patientRegisterationRequest;
    String vendorName;
    String vaccineName;
    ButtonGroup G1;
    ButtonGroup G2;
    
    /**
     * Creates new form VendorDashboardJPanel
     */
    public FindVaccineRegisterJPanel(JPanel userProcessContainer, UserAccount account, EcoSystem business,
            VaccinationVendor vaccinationVendor, String vendorName, String vaccineName) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.system = business;
        this.vaccinationVendor = vaccinationVendor;
        this.vendorName = vendorName;
        this.vaccineName = vaccineName; 
        ScheduleAptJPanel.setVisible(false);
        
        Nametxt.setText(userAccount.getEmployee().getName());
        System.out.println("Name Value: "+userAccount.getEmployee().getName());
        
        G1 = new ButtonGroup();
        G1.add(medicalconditionrbtn);
        G1.add(medicalconditionrbtn1);
        G1.add(medicalconditionrbtn2);
        
        G2 = new ButtonGroup();
        G2.add(jRadioButton3);
        G2.add(jRadioButton4);
    }
    
    
    
    
    public void populateCheckBox(){
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
        AvailableDates.setModel( model1 );
        
        System.out.println("Dates: "+vaccinationVendor.getDates().keySet());
        for (String date1 : vaccinationVendor.getDates().keySet()){
            AvailableDates.addItem(date1);
        }
    }
    
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Nametxt = new java.awt.TextField();
        jLabel6 = new javax.swing.JLabel();
        DOBtxt = new java.awt.TextField();
        jLabel7 = new javax.swing.JLabel();
        Ethinicity = new java.awt.TextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ContactNotxt = new java.awt.TextField();
        Addresstxt = new java.awt.TextField();
        medicalconditionrbtn = new javax.swing.JRadioButton();
        medicalconditionrbtn1 = new javax.swing.JRadioButton();
        ScheduleAptJPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        AvailableDates = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        AvailableSlots = new javax.swing.JComboBox<>();
        bookSlotbtn = new javax.swing.JButton();
        CheckEligibilitybtn = new javax.swing.JButton();
        medicalconditionrbtn2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        emailtxt = new java.awt.TextField();
        jLabel14 = new javax.swing.JLabel();
        Citytxt = new java.awt.TextField();
        Statetxt = new java.awt.TextField();
        profession3 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Please be informed that people with certain conditions or profession are prioritized currently");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 74, -1, -1));

        jLabel5.setText("Name");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 115, -1, -1));

        Nametxt.setEditable(false);
        add(Nametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 115, 60, -1));

        jLabel6.setText("DOB:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 159, -1, -1));

        DOBtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOBtxtActionPerformed(evt);
            }
        });
        add(DOBtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 153, 60, -1));

        jLabel7.setText("Ethinicity:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 189, -1, -1));
        add(Ethinicity, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 189, 60, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Medical Conditons : ");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 291, -1, 36));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Profession: ");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(411, 302, -1, -1));

        jLabel10.setText("Phone No:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, -1, -1));

        jLabel11.setText("Address:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 228, -1, -1));
        add(ContactNotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 50, -1));
        add(Addresstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 233, 60, -1));

        medicalconditionrbtn.setText("One");
        add(medicalconditionrbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 298, -1, -1));

        medicalconditionrbtn1.setText("Two or More");
        add(medicalconditionrbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 298, -1, -1));

        jLabel13.setText("Dates:");

        AvailableDates.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AvailableDatesMouseClicked(evt);
            }
        });
        AvailableDates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvailableDatesActionPerformed(evt);
            }
        });

        jLabel12.setText("Slots:");

        AvailableSlots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvailableSlotsActionPerformed(evt);
            }
        });

        bookSlotbtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bookSlotbtn.setText("Schedule Appointment");
        bookSlotbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookSlotbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ScheduleAptJPanelLayout = new javax.swing.GroupLayout(ScheduleAptJPanel);
        ScheduleAptJPanel.setLayout(ScheduleAptJPanelLayout);
        ScheduleAptJPanelLayout.setHorizontalGroup(
            ScheduleAptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScheduleAptJPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel13)
                .addGap(39, 39, 39)
                .addGroup(ScheduleAptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ScheduleAptJPanelLayout.createSequentialGroup()
                        .addComponent(bookSlotbtn)
                        .addContainerGap(98, Short.MAX_VALUE))
                    .addGroup(ScheduleAptJPanelLayout.createSequentialGroup()
                        .addComponent(AvailableDates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(30, 30, 30)
                        .addComponent(AvailableSlots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );
        ScheduleAptJPanelLayout.setVerticalGroup(
            ScheduleAptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScheduleAptJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ScheduleAptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ScheduleAptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(AvailableDates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ScheduleAptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(AvailableSlots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(bookSlotbtn)
                .addGap(26, 26, 26))
        );

        add(ScheduleAptJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 470, -1, -1));

        CheckEligibilitybtn.setText("Check Eligibility");
        CheckEligibilitybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckEligibilitybtnActionPerformed(evt);
            }
        });
        add(CheckEligibilitybtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 413, 125, -1));

        medicalconditionrbtn2.setText("None");
        add(medicalconditionrbtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 298, -1, -1));

        jRadioButton3.setText("Frontline Worker");
        add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 298, -1, -1));

        jRadioButton4.setText("Volunteer");
        add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(601, 298, -1, -1));
        add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 50, -1));

        jLabel14.setText("Email ID:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, -1, -1));
        add(Citytxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 50, -1));
        add(Statetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 50, -1));

        profession3.setText("None");
        add(profession3, new org.netbeans.lib.awtextra.AbsoluteConstraints(672, 298, -1, -1));

        jLabel15.setText("Blood Pressure");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 333, -1, -1));

        jLabel16.setText("Heart Disease");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 353, -1, -1));

        jLabel17.setText("Cancer");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 373, -1, -1));

        jLabel18.setText("Diabetes");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 393, -1, -1));

        jLabel19.setText("Dementia");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 417, -1, -1));

        jLabel20.setText("City:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, -1, -1));

        jLabel21.setText("State:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VACCINE REGISTRATION");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 25, 244, 31));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vaccine_PNG13_501.png"))); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void bookSlotbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookSlotbtnActionPerformed
        // TODO add your handling code here:

        
        System.out.println("User "+ userAccount);
        String state = vaccinationVendor.getState();
        vaccinationVendor.setName(vendorName);
        Network network = system.searchNetworkName(state);
        Organization.Type type = Organization.Type.Patient;
        PatientRegisterationRequest patientRegisterationRequest = new PatientRegisterationRequest();

        patientRegisterationRequest.setAddress(Nametxt.getText());
        patientRegisterationRequest.setDob(DOBtxt.getText());
        patientRegisterationRequest.setNetwork(network);
        patientRegisterationRequest.setOrgType(type);
        patientRegisterationRequest.setPhone(ContactNotxt.getText());
        patientRegisterationRequest.setUsername(userAccount.getUsername());
        patientRegisterationRequest.setEmail(emailtxt.getText());
        patientRegisterationRequest.setName(userAccount.getEmployee().getName());
        patientRegisterationRequest.setCity(Citytxt.getText());
        patientRegisterationRequest.setState(Statetxt.getText());
        System.out.println("Vaccine Name Set as: "+vaccineName);
        patientRegisterationRequest.setVaccineType(vaccineName);
        patientRegisterationRequest.setUniqueID();
        
        patientRegisterationRequest.setVaccinationVendor(vaccinationVendor);
        patientRegisterationRequest.setStatus("Registered");
        patientRegisterationRequest.setRegistrationType("Find Vaccine");
        patientRegisterationRequest.setFirstDoseDate(String.valueOf(AvailableDates.getSelectedItem()));
        patientRegisterationRequest.setFirstDoseTime(String.valueOf(AvailableSlots.getSelectedItem()));
        vaccinationVendor.deleteSlot(String.valueOf(AvailableSlots.getSelectedItem()),String.valueOf(AvailableDates.getSelectedItem()) );
        System.out.println("Done");
        JOptionPane.showMessageDialog(null, "You have Successfully Scheduled an appointment");
        
        
        for(Network n : system.getNetworkList()){
            System.out.println("NN "+ n.getNetworkName());
            for(Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.VaccinationSites){
                    if(e.getWorkQueue()==null){
                        e.setWorkQueue(new WorkQueue());
                    }
                    e.getWorkQueue().getWorkRequestList().add(patientRegisterationRequest);
                    dB4OUtil.storeSystem(system);
                    return;
                }
            }
        }
        
    }//GEN-LAST:event_bookSlotbtnActionPerformed

    private void AvailableDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvailableDatesActionPerformed
        // TODO add your handling code here:
        System.out.println("GET slots **");
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
        AvailableSlots.setModel( model1 );

        String key = String.valueOf(AvailableDates.getSelectedItem());
        for(String s : vaccinationVendor.getSlotforDate(key)){
            AvailableSlots.addItem(s);
        }
    }//GEN-LAST:event_AvailableDatesActionPerformed

    private void AvailableSlotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvailableSlotsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AvailableSlotsActionPerformed

    private void AvailableDatesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AvailableDatesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AvailableDatesMouseClicked

    private void CheckEligibilitybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckEligibilitybtnActionPerformed
        // TODO add your handling code here:

        if(Nametxt.getText().isEmpty() || DOBtxt.getText().isEmpty() || Ethinicity.getText().isEmpty() || Addresstxt.getText().isEmpty() || Citytxt.getText().isEmpty() || Statetxt.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill all the fields at this pages.", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
        }
        
        if(!isValidEmailAddress(emailtxt.getText())){
            JOptionPane.showMessageDialog(null, "Please fill the email address in the correct format", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
        }
        
        String medicalConditions = " ";
  
                // If condition to check if jRadioButton2 is selected.
                if (medicalconditionrbtn.isSelected()) {
  
                    medicalConditions = "One Medical Condition";
                }
                else if(medicalconditionrbtn1.isSelected()){
                    medicalConditions = "Two or More Medical Condition";
                }
  
                else if (medicalconditionrbtn2.isSelected()) {
  
                    medicalConditions = "None";
                }
                else {
                    JOptionPane.showMessageDialog(null, "No value selected for Medical condition field.", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
                }
                
                String profession = " ";
                if (jRadioButton3.isSelected()) {
  
                    profession = "FrontLine Worker";
                }
                else if(jRadioButton4.isSelected()){
                    profession = "Volunteer";
                }
                else {
                    //JOptionPane.showMessageDialog(null, "No value selected for Profession field.");
                    JOptionPane.showMessageDialog(null, "No value selected for Profession field.", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
                }
                
             System.out.println("Selected Radio Buttons: "+medicalConditions+ " "+profession);
             if(medicalconditionrbtn.isSelected() || medicalconditionrbtn1.isSelected() && jRadioButton3.isSelected() || jRadioButton4.isSelected()){
             ScheduleAptJPanel.setVisible(true);   
             populateCheckBox();
             } else {
                 JOptionPane.showMessageDialog(null, "Fill the necessary radio buttons", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
                 //JOptionPane.showMessageDialog(null, "Fill the necessary radio buttons");
             }
    }//GEN-LAST:event_CheckEligibilitybtnActionPerformed

    private void DOBtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOBtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOBtxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextField Addresstxt;
    private javax.swing.JComboBox<String> AvailableDates;
    private javax.swing.JComboBox<String> AvailableSlots;
    private javax.swing.JButton CheckEligibilitybtn;
    private java.awt.TextField Citytxt;
    private java.awt.TextField ContactNotxt;
    private java.awt.TextField DOBtxt;
    private java.awt.TextField Ethinicity;
    private java.awt.TextField Nametxt;
    private javax.swing.JPanel ScheduleAptJPanel;
    private java.awt.TextField Statetxt;
    private javax.swing.JButton bookSlotbtn;
    private java.awt.TextField emailtxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton medicalconditionrbtn;
    private javax.swing.JRadioButton medicalconditionrbtn1;
    private javax.swing.JRadioButton medicalconditionrbtn2;
    private javax.swing.JRadioButton profession3;
    // End of variables declaration//GEN-END:variables
}