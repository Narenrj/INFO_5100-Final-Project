/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.Vendor;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Email.JavaMailService;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.PatientRegisterationRequest;
import Business.WorkQueue.VaccinationVendor;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import static com.db4o.qlin.QLinSupport.p;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author naren
 */
public class VendorDashboardJPanel extends javax.swing.JPanel {

    /**
     * Creates new form VendorDashboardJPanel
     */

    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    PatientRegisterationRequest p;
    private VaccinationVendor vaccinationVendor;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();

    
    /**
     * Creates new form LabAssistantWorkAreaJPanel
     */
    public VendorDashboardJPanel(JPanel userProcessContainer, UserAccount account, EcoSystem business) {
        initComponents();
        
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.system = business;
        
        RegisterJPanel.setVisible(true);
        VendorDashboardJPanel.setVisible(false);
        ApplicationPendingJPanel.setVisible(false);
        CheckInJPanel.setVisible(false);
        
        String uname = userAccount.getUsername();
        System.out.println(system.getUserAccountDirectory().getUserAccountList());
        System.out.println("Uname value: "+uname);
        
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                System.out.println("Check 1");
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    System.out.println("Check 2");
                if(workRequest instanceof VaccinationVendor){
                    System.out.println("Check 3");
                    
                    if(((VaccinationVendor) workRequest).getUsername().equals(uname) && ((VaccinationVendor) workRequest).getStatus().equals("Pending Approval") ){
                        System.out.println("HVJ condition");
                        RegisterJPanel.setVisible(false);
                        VendorDashboardJPanel.setVisible(false);
                        ApplicationPendingJPanel.setVisible(true);
                        return;
                    }
                    else if (((VaccinationVendor) workRequest).getUsername().equals(uname) && ((VaccinationVendor) workRequest).getStatus().equals("Approved")){
                        RegisterJPanel.setVisible(false);
                        ApplicationPendingJPanel.setVisible(false);
                        VendorDashboardJPanel.setVisible(true);
                        //populateInventoryTable();
                        populatePatientsTable();
                        return;
                    } 
                    else {
                        System.out.println("No conditions satisfied");
                        
                        VendorDashboardJPanel.setVisible(false);
                        ApplicationPendingJPanel.setVisible(false);
                        RegisterJPanel.setVisible(true);
                        
                    }
                    
                  }
                }
            }
        }
    }
    
    public void populatePatientsTable()
    {
        String uname = userAccount.getUsername();
        System.out.println("Mouse Clicked!.."+uname);
        
        DefaultTableModel dtm = (DefaultTableModel) VendorPatiientstbl.getModel();
        dtm.setRowCount(0);
        
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof PatientRegisterationRequest ){
                        System.out.println("Inside final loop");

                        //if(workRequest.getStatus().equals("Registered") && ((PatientRegisterationRequest) workRequest).getVaccinationVendor().getUsername().equals(uname))
                        System.out.println("Debug: "+workRequest.getStatus()+"  "+((PatientRegisterationRequest) workRequest).getUsername());
                        if(((PatientRegisterationRequest) workRequest).getVaccinationVendor()!=null && workRequest.getStatus().equals("Registered") && ((PatientRegisterationRequest) workRequest).getVaccinationVendor().getUsername().equals(uname))
                        {
                        System.out.println("w "+ workRequest);
                        Object row[] = new Object[9];
                        row[0] = ((PatientRegisterationRequest) workRequest);
                        row[1] = ((PatientRegisterationRequest) workRequest).getDob();
                        row[2] = ((PatientRegisterationRequest) workRequest).getAddress();
                        row[3] = ((PatientRegisterationRequest) workRequest).getCity();
                        row[4] = ((PatientRegisterationRequest) workRequest).getState();
                        row[5] = ((PatientRegisterationRequest) workRequest).getEmail();
                        row[6] = ((PatientRegisterationRequest) workRequest).getPhone();
                        row[7] = ((PatientRegisterationRequest) workRequest).getVaccineType();
                        row[8] = ((PatientRegisterationRequest) workRequest);
                        dtm.addRow(row);
                        }
                    }
                }
            }
               
        }
    }    
//    public void populateInventoryTable(){
//        
//        
//        String name  =  userAccount.getUsername();
//        
//        DefaultTableModel dtm = (DefaultTableModel) VendorDashboardInventorytbl1.getModel();
//        dtm.setRowCount(0);
//        System.out.println(system.getNetworkList().size());
//        
//        HashMap<String, Integer> hm = new HashMap<String, Integer>();
//        
//        
//        for(Network n : system.getNetworkList()){
//               
//            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
//                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
//                    if(workRequest instanceof VaccinationVendor){
//                        System.out.println("w "+ workRequest);
//                        hm = ((VaccinationVendor) workRequest).getStockOne();
//                        Iterator hmIterator = hm.entrySet().iterator();
//                        System.out.println("HashMap Assigned Values: "+hm);
//                        Object row[] = new Object[2];
//                        while (hmIterator.hasNext() && ((VaccinationVendor) workRequest).getUsername().equals(name)) {
//                            Map.Entry mapElement = (Map.Entry)hmIterator.next();
//                            row[0] = mapElement.getKey();
//                            row[1] = ((int)mapElement.getValue());
//                            dtm.addRow(row);
//                        }
//                    }
//                }
//            }
//               
//        }
//    }
    
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

        RegisterJPanel = new javax.swing.JPanel();
        state = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Registerbtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Address = new java.awt.TextField();
        City = new java.awt.TextField();
        jLabel9 = new javax.swing.JLabel();
        VendorName = new java.awt.TextField();
        jLabel1 = new javax.swing.JLabel();
        ApplicationPendingJPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        VendorDashboardJPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        VendorPatiientstbl = new javax.swing.JTable();
        PatientCheckInbtn = new javax.swing.JButton();
        CheckInJPanel = new javax.swing.JPanel();
        doseTypebox = new javax.swing.JComboBox<>();
        DoseTypelbl = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        commentstxt = new java.awt.TextField();
        Confirmbtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        RegisterJPanel.setBackground(new java.awt.Color(255, 255, 255));
        RegisterJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        state.setForeground(new java.awt.Color(0, 51, 153));
        state.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "California", "Massachusetts" }));
        state.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateActionPerformed(evt);
            }
        });
        RegisterJPanel.add(state, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 164, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Email");
        RegisterJPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, -1, -1));
        RegisterJPanel.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 160, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("State");
        RegisterJPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 40, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("VENDOR REGISTER");
        RegisterJPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 290, 30));

        Registerbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Registerbtn.setForeground(new java.awt.Color(0, 51, 153));
        Registerbtn.setText("Register");
        Registerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterbtnActionPerformed(evt);
            }
        });
        RegisterJPanel.add(Registerbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("Address");
        RegisterJPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("City");
        RegisterJPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 40, 30));

        Address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddressActionPerformed(evt);
            }
        });
        RegisterJPanel.add(Address, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 163, -1));
        RegisterJPanel.add(City, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 163, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setText("Name");
        jLabel9.setToolTipText("");
        RegisterJPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));
        RegisterJPanel.add(VendorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 162, -1));

        jLabel1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/istockphoto-1286323980-170667a (1).png"))); // NOI18N
        RegisterJPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 620, 390));

        ApplicationPendingJPanel.setBackground(new java.awt.Color(255, 255, 255));
        ApplicationPendingJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Your application is with Centre for Disease Control. You will be notifed when Approved !");
        ApplicationPendingJPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 38, 554, 54));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/images.jpg"))); // NOI18N
        ApplicationPendingJPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 110, 280, -1));

        VendorDashboardJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        VendorPatiientstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "DOB", "Address", "City", "State", "Email ID", "Contact No.", "Vaccine Status"
            }
        ));
        jScrollPane1.setViewportView(VendorPatiientstbl);

        VendorDashboardJPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 74, 470, 110));

        PatientCheckInbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PatientCheckInbtn.setForeground(new java.awt.Color(0, 51, 153));
        PatientCheckInbtn.setText("Check In");
        PatientCheckInbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientCheckInbtnActionPerformed(evt);
            }
        });
        VendorDashboardJPanel.add(PatientCheckInbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 202, -1, -1));

        doseTypebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dose 1", "Dose 2" }));

        DoseTypelbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DoseTypelbl.setForeground(new java.awt.Color(0, 51, 153));
        DoseTypelbl.setText("Dose Type:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setText("Comments: ");

        commentstxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentstxtActionPerformed(evt);
            }
        });

        Confirmbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Confirmbtn.setForeground(new java.awt.Color(0, 51, 153));
        Confirmbtn.setText("Confirm");
        Confirmbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CheckInJPanelLayout = new javax.swing.GroupLayout(CheckInJPanel);
        CheckInJPanel.setLayout(CheckInJPanelLayout);
        CheckInJPanelLayout.setHorizontalGroup(
            CheckInJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckInJPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(DoseTypelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(doseTypebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(commentstxt, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(CheckInJPanelLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(Confirmbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CheckInJPanelLayout.setVerticalGroup(
            CheckInJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckInJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CheckInJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CheckInJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(doseTypebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DoseTypelbl)
                        .addComponent(jLabel11))
                    .addComponent(commentstxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Confirmbtn)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        VendorDashboardJPanel.add(CheckInJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 252, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Vendor Dashboard");
        VendorDashboardJPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 29, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RegisterJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(242, 242, 242)
                    .addComponent(ApplicationPendingJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(242, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(164, 164, 164)
                    .addComponent(VendorDashboardJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(165, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RegisterJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(227, 227, 227)
                    .addComponent(ApplicationPendingJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(227, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(209, 209, 209)
                    .addComponent(VendorDashboardJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(210, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterbtnActionPerformed
        // TODO add your handling code here:
        
        
        
        if(VendorName.getText().isEmpty() || City.getText().isEmpty() || Address.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill all the necessary fields", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
        }
        
        if(!isValidEmailAddress(email.getText())){
            JOptionPane.showMessageDialog(null, "Email address field in wrong input", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
        }
        Network network;
        String vendorName = VendorName.getText();
        String userName = userAccount.getUsername();
        System.out.println("username: "+userName);
        String emailId = email.getText();
        String city = City.getText();
        String address = Address.getText();
        String stateName = String.valueOf(state.getSelectedItem());
        Employee employee = system.getEmployeeDirectory().createEmployee(userName);

        network = system.searchNetworkName(stateName);
        if(network == null){
            network = new Network();
            network.setNetworkName(stateName);
            system.setNetworkList(network);
        }
        if(network.getEnterpriseDirectory() == null){
            EnterpriseDirectory ed = new EnterpriseDirectory();
            ed.createAndAddEnterprise("VaccinationSites",
                Enterprise.EnterpriseType.VaccinationSites);
            network.setEnterpriseDirectory(ed);

        }else{
            EnterpriseDirectory ed = network.getEnterpriseDirectory();
            ed.createAndAddEnterprise("VaccinationSites", Enterprise.EnterpriseType.VaccinationSites);
            network.setEnterpriseDirectory(ed);
        }

        Organization.Type type = Organization.Type.Vendor;

        VaccinationVendor vaccinationVendor = new VaccinationVendor();
        vaccinationVendor.setName(vendorName);
        vaccinationVendor.setUsername(userName);
        vaccinationVendor.setAddress(address);
        vaccinationVendor.setCity(city);
        vaccinationVendor.setEmail(emailId);
        vaccinationVendor.setState(stateName);
        //vaccinationVendor.setUsername();
        vaccinationVendor.setStatus("Pending Approval");

        for(Network n : system.getNetworkList()){
            System.out.println("NN "+ n.getNetworkName());
            for(Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){

                if(e.getEnterpriseType() == Enterprise.EnterpriseType.VaccinationSites){
                    if(e.getWorkQueue()==null){
                        e.setWorkQueue(new WorkQueue());
                    }
                    e.getWorkQueue().getWorkRequestList().add(vaccinationVendor);
                    dB4OUtil.storeSystem(system);
                    JOptionPane.showMessageDialog(null, "Successfully Registered", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

                    return;
                }
            }
        }

    }//GEN-LAST:event_RegisterbtnActionPerformed

    private void AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddressActionPerformed

    private void stateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stateActionPerformed

    private void PatientCheckInbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientCheckInbtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = VendorPatiientstbl.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        CheckInJPanel.setVisible(true);
    }//GEN-LAST:event_PatientCheckInbtnActionPerformed

    private void ConfirmbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmbtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = VendorPatiientstbl.getSelectedRow();
        p = (PatientRegisterationRequest)VendorPatiientstbl.getValueAt(selectedRow,0);
        p.setVaccineType(String.valueOf(doseTypebox.getSelectedItem()));
       
        if(String.valueOf(doseTypebox.getSelectedItem()).equals("Dose 1")){
            p.setStatus("Vaccinated Dose 1");

        }else{
            p.setStatus("Vaccinated Dose 2");
        }
        
        sendEmail("narenrj95@gmail.com");
        
        String vendorName = userAccount.getEmployee().getName();
        
        //System.out.println("Hash Map Data: "+vaccinationVendor.getStockOne());
        
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        
        p.setUniqueID();
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof VaccinationVendor){
                        System.out.println("w "+ workRequest);
                        hm = ((VaccinationVendor) workRequest).getStockOne();
                        Iterator hmIterator = hm.entrySet().iterator();
                        System.out.println("HashMap Assigned Values: "+hm);
                        while (hmIterator.hasNext() && ((VaccinationVendor) workRequest).getName().equals(vendorName)) {
                            Map.Entry mapElement = (Map.Entry)hmIterator.next();
                            String vaccineName = p.getVaccineType();
                            System.out.println("Vaccine Name inside schedule : "+vaccineName);
                            String vName = (String) mapElement.getKey();
                            int stock = (int) mapElement.getValue();
                            if(vName.equals(vaccineName)){
                                System.out.println("Vaccine Name inside loop: "+vaccineName+"Stock is: "+stock);
                                stock--;
                                System.out.println("Vaccine Name after decrement: "+vaccineName+"Stock is: "+stock);
                                hm.put(vaccineName, stock);
                                System.out.println("HashMap Values are :"+hm);
                            }   
                        }
                    }
                }
            }
               
        }
        JOptionPane.showMessageDialog(null, "Successfully Registered", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_ConfirmbtnActionPerformed

    private void commentstxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentstxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_commentstxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextField Address;
    private javax.swing.JPanel ApplicationPendingJPanel;
    private javax.swing.JPanel CheckInJPanel;
    private java.awt.TextField City;
    private javax.swing.JButton Confirmbtn;
    private javax.swing.JLabel DoseTypelbl;
    private javax.swing.JButton PatientCheckInbtn;
    private javax.swing.JPanel RegisterJPanel;
    private javax.swing.JButton Registerbtn;
    private javax.swing.JPanel VendorDashboardJPanel;
    private java.awt.TextField VendorName;
    private javax.swing.JTable VendorPatiientstbl;
    private java.awt.TextField commentstxt;
    private javax.swing.JComboBox<String> doseTypebox;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> state;
    // End of variables declaration//GEN-END:variables
}
