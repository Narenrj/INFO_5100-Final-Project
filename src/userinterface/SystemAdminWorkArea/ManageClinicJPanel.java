/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.SystemAdminWorkArea;

import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Network.Network;
import Business.Organization.ClinicOrganization;
import Business.Organization.Organization;
import Business.Role.ClinicRole;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.ClinicRegistrationRequest;
import Business.WorkQueue.InventoryRequest;
import Business.WorkQueue.PHDAdminRequest;
import Business.WorkQueue.PatientRegisterationRequest;
import Business.WorkQueue.VaccineManufacturerRequest;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import static userinterface.Patient.PatientDashboardJPanel.isValidEmailAddress;

/**
 *
 * @author psrib
 */
public class ManageClinicJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    
    PHDAdminRequest pHDAdminRequest;

    public ManageClinicJPanel(JPanel userProcessContainer, EcoSystem system) {
        initComponents();
        
        this.userProcessContainer = userProcessContainer;
       // this.userAccount = account;
        this.system = system;
        jPanel1.setVisible(false);
        populateTable();
       getVaccineStockTable();
       
    }
    
    public void populateTable(){
        DefaultTableModel dtm = (DefaultTableModel) clinicTable.getModel();
        dtm.setRowCount(0);
        
        for(Network n : system.getNetworkList()){
               
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof ClinicRegistrationRequest){
                        System.out.println("w "+ workRequest);
                        Object row[] = new Object[4];
                        row[0] = ((ClinicRegistrationRequest) workRequest);
                        row[1] = ((ClinicRegistrationRequest) workRequest).getPractionerName();
                        row[2] = ((ClinicRegistrationRequest) workRequest).getAddress();
                        row[3] = ((ClinicRegistrationRequest) workRequest).getCity();
                        //row[4] = ((ClinicRegistrationRequest) workRequest).getCity();
                       // row[4] = ((ClinicRegistrationRequest) workRequest).getPractionerName();

                        
                       
                        dtm.addRow(row);
                    }
                }
            }
               
        }
           
    }
    
    public void getVaccineStockTable(){
        DefaultTableModel dtm = (DefaultTableModel) stock1.getModel();
        dtm.setRowCount(0);
         for(Network n : system.getNetworkList()){
              
            for( Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof PHDAdminRequest){
                        //if( ((PHDAdminRequest) workRequest).getUserAccount().getUsername().
                          //      equals(userAccount.getEmployee().getName())){
                            HashMap<String, Integer> map = ((PHDAdminRequest) workRequest).getInventoryRequest()
                                    .getVaccineName();
                              for (Map.Entry<String,Integer> entry : map.entrySet()){
                                    Object row[] = new Object[2];
                                    row[0] = entry.getKey();
                                    row[1] = entry.getValue();
                                    dtm.addRow(row);
                           //   }
         
                        }
                    }
                }
            }
               
        }
  
    }
    public static boolean isAlpha(String s) {
        return s != null && s.chars().allMatch(Character::isLetter);
    }
    private boolean strongPassword() {
        Pattern pat1;
        pat1 = Pattern.compile("^(?=.?[A-Z])(?=.?[a-z])(?=.?[0-9])(?=.?[#?!@$%^&*-]).{6,}$");
        Matcher m1 = pat1.matcher(pass.getText());
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
        cname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pname = new javax.swing.JTextField();
        add = new javax.swing.JTextField();
        city = new javax.swing.JTextField();
        addClinic = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        clinicTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        uname = new javax.swing.JTextField();
        pass = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        state = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stock = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        stock1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        vname = new javax.swing.JComboBox<>();
        units = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("ADD CLINIC");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 174, 28));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Clinic Name");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 93, -1));
        add(cname, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 142, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Practitioner Name");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Address");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("City");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));
        add(pname, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 142, -1));
        add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 142, -1));
        add(city, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 142, -1));

        addClinic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addClinic.setForeground(new java.awt.Color(0, 51, 153));
        addClinic.setText("Add Clinic");
        addClinic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClinicActionPerformed(evt);
            }
        });
        add(addClinic, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 540, -1, -1));

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
        jScrollPane1.setViewportView(clinicTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 512, 140));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Username");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("Password");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, -1));
        add(uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 142, -1));
        add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 141, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("State");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, -1, -1));

        state.setForeground(new java.awt.Color(0, 51, 153));
        state.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "California", "Massachusetts" }));
        add(state, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 141, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("Get Stock Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 540, 150, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setText("VIEW CLINIC");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 187, 28));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/istockphoto-688337988-612x612.png"))); // NOI18N
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 770, 440));

        stock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Vaccine Name", "Stock"
            }
        ));
        jScrollPane2.setViewportView(stock);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 340, 310, 130));

        stock1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Vaccine Name", "Count"
            }
        ));
        jScrollPane3.setViewportView(stock1);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 660, 340, 130));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 153));
        jLabel10.setText("Public Health Department Stock");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 620, -1, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 153));
        jButton2.setText("Add Stock");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 540, 130, -1));

        vname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        vname.setForeground(new java.awt.Color(0, 51, 153));

        units.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitsActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 153));
        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 153));
        jLabel12.setText("No of Units");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 153));
        jLabel13.setText("Vaccine Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(vname, 0, 115, Short.MAX_VALUE)
                            .addComponent(units)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jButton3)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(units, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 630, -1, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void addClinicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClinicActionPerformed
        // TODO add your handling code here:
        
         if(cname.getText().isEmpty() || pname.getText().isEmpty() || add.getText().isEmpty() || city.getText().isEmpty()||
                String.valueOf(state.getSelectedItem()).isEmpty() || uname.getText().isEmpty() || pass.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "All the fields are required", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(!isAlpha(cname.getText()) || !isAlpha(pname.getText())){
            JOptionPane.showMessageDialog(null, "Should be Aphabets only no numbers allowed", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
 
        }if(!strongPassword()){
            JOptionPane.showMessageDialog(null, "Strong password required", "InfoBox: ", JOptionPane.WARNING_MESSAGE);

        }
         
         
//         if (!phoneNum.getText().matches("\\d{10}")){
//            JOptionPane.showMessageDialog(null, "Enter the valid phone number", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
//  
//             return ;
//         }
//         if(!isValidEmailAddress(email.getText())){
//            JOptionPane.showMessageDialog(null, "Enter the valid email ID", "InfoBox: ", JOptionPane.WARNING_MESSAGE);
//            return ; 
//         }
        Network network = system.searchNetworkName(String.valueOf(state.getSelectedItem()));
        if(network ==null){
            System.out.println("Network not found");
            return;
        }
        System.out.println("Ne "+ network);
          
        
        Enterprise enterprise = network.getEnterpriseDirectory().searchEnterprise("PublicHealthDepartment");
        if(enterprise ==null){
            System.out.println("Enterprise is null");
            //return;
        }
        enterprise = network.getEnterpriseDirectory().createAndAddEnterprise("PublicHealthDepartment", 
                Enterprise.EnterpriseType.PublicHealthDepartment);
        
       
       // network.setEnterpriseDirectory(enterpriseDirectory);
        System.out.println("NET "+ network);
        Organization.Type type = Organization.Type.Clinic;
//        
        ClinicRegistrationRequest clinicRegistrationRequest = new ClinicRegistrationRequest();
        clinicRegistrationRequest.setClinicName(cname.getText());
        clinicRegistrationRequest.setAddress(add.getText());
        clinicRegistrationRequest.setPractionerName(pname.getText());
        clinicRegistrationRequest.setCity(city.getText());
        clinicRegistrationRequest.setOrgType(type);
        clinicRegistrationRequest.setEnterprise(enterprise);
        
        InventoryRequest inventoryRequest = new InventoryRequest();
        HashMap<String, Integer> map = new HashMap();
        map.put("pfizer", 10);
        map.put("moderna", 10);
        map.put("j&j", 5);
        inventoryRequest.setVaccineName(map);
        clinicRegistrationRequest.setInventoryRequest(inventoryRequest);
        Employee emp = new Employee();
        emp.setName(cname.getText());
        system.getUserAccountDirectory().createUserAccount(uname.getText(), pass.getText(), emp, new ClinicRole());
        
        for(Network n : system.getNetworkList()){
            System.out.println("NN "+ n.getNetworkName());
            for(Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.PublicHealthDepartment){
                    if(e.getWorkQueue()==null){
                        e.setWorkQueue(new WorkQueue());
                    }
                    e.getWorkQueue().getWorkRequestList().add(clinicRegistrationRequest);
                    populateTable();
                    JOptionPane.showMessageDialog(null, "Successfully Added the clinic", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

                    return;
                }
            }
        }
        
        
    }//GEN-LAST:event_addClinicActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = clinicTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ClinicRegistrationRequest c = (ClinicRegistrationRequest)clinicTable.getValueAt(selectedRow,0);
        DefaultTableModel dtm = (DefaultTableModel) stock.getModel();
        dtm.setRowCount(0);
        
      
        HashMap<String, Integer> map = c.getInventoryRequest().getVaccineName();
          for (Map.Entry<String,Integer> entry : map.entrySet()){
                Object row[] = new Object[2];
                row[0] = entry.getKey();
                row[1] = entry.getValue();
                dtm.addRow(row);
          }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jPanel1.setVisible(true);
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
        vname.setModel( model1 );
        Set<String> name = new HashSet();
        for(Network n : system.getNetworkList()){
            System.out.println("NN "+ n.getNetworkName());
            for(Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                
                for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                    if(workRequest instanceof VaccineManufacturerRequest){
                        name.add(((VaccineManufacturerRequest) workRequest).getManufacturername());
                    }
                }
            }
        }
        
        for(String s: name)
            vname.addItem(s);
            
       
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void unitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitsActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       int selectedRow = clinicTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ClinicRegistrationRequest c = (ClinicRegistrationRequest)clinicTable.getValueAt(selectedRow,0);
        for(Network n : system.getNetworkList()){
                   System.out.println("NN "+ n.getNetworkName());
                   for(Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){

                       for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                           if(workRequest instanceof PHDAdminRequest){
                               pHDAdminRequest = (PHDAdminRequest)workRequest;
                               break;
                           }
                       }
                   }
        }
        System.out.println("MAAPP "+c.getInventoryRequest().getVaccineName());
        if(c.getInventoryRequest().getVaccineName().get(String.valueOf(vname.getSelectedItem()).toLowerCase())==null){
            HashMap<String, Integer> map = c.getInventoryRequest().getVaccineName();
            map.put(String.valueOf(vname.getSelectedItem()).toLowerCase(), Integer.parseInt(units.getText()));
        }else{
        int count = c.getInventoryRequest().getVaccineName().get(String.valueOf(vname.getSelectedItem()).toLowerCase());
        c.getInventoryRequest().getVaccineName().put(String.valueOf(vname.getSelectedItem()).toLowerCase(), 
                count+ Integer.parseInt(units.getText()));
        }
       
        System.out.println("MAPSS "+pHDAdminRequest.getInventoryRequest().getVaccineName());
        int count1 = pHDAdminRequest.getInventoryRequest().getVaccineName().get(String.valueOf(vname.getSelectedItem()).toLowerCase());
        pHDAdminRequest.getInventoryRequest().getVaccineName().put(String.valueOf(vname.getSelectedItem()).toLowerCase(), 
                count1 - Integer.parseInt(units.getText()));
        
        populateTable();
       getVaccineStockTable();
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
            
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField add;
    private javax.swing.JButton addClinic;
    private javax.swing.JTextField city;
    private javax.swing.JTable clinicTable;
    private javax.swing.JTextField cname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField pass;
    private javax.swing.JTextField pname;
    private javax.swing.JComboBox<String> state;
    private javax.swing.JTable stock;
    private javax.swing.JTable stock1;
    private javax.swing.JTextField uname;
    private javax.swing.JTextField units;
    private javax.swing.JComboBox<String> vname;
    // End of variables declaration//GEN-END:variables
}
