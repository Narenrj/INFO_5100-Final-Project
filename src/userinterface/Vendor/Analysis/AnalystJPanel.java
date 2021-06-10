/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.Vendor.Analysis;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.ClinicRegistrationRequest;
import Business.WorkQueue.PatientRegisterationRequest;
import Business.WorkQueue.WorkQueue;
import Business.WorkQueue.WorkRequest;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
/**
 *
 * @author psrib
 */
public class AnalystJPanel extends javax.swing.JPanel {

    /**
     * Creates new form AnalystJPanel
     */
    
    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    DefaultPieDataset result = new DefaultPieDataset();

    public AnalystJPanel(JPanel userProcessContainer, UserAccount userAccount, EcoSystem system) {
          initComponents();

          this.userProcessContainer = userProcessContainer;
          this.userAccount = userAccount;
          this.system = system;

          jPanel1.removeAll();
          jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
          jPanel1.add(createPieChart("SS"));
          jPanel2.removeAll();
          jPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
          jPanel2.add(createPieChart1("SS"));
          getData();
      }

    private ChartPanel createPieChart(String chartTitle) {
          System.out.println("PieChart");
          PieDataset dataset = createDataset();
          JFreeChart chart = createChart(dataset, chartTitle);
          ChartPanel chartPanel = new ChartPanel(chart);
          return chartPanel;
      }
    
        private ChartPanel createPieChart1(String chartTitle) {
          System.out.println("PieChart");
          PieDataset dataset = createDataset1();
          JFreeChart chart = createChart1(dataset, chartTitle);
          ChartPanel chartPanel = new ChartPanel(chart);
          return chartPanel;
      }
        
    private PieDataset createDataset() {
        System.out.println("PieDataset");
        int count =0;
        int registered=0;
        int firstDosage=0;
        int secondDosage =0;
        
        for(Network n : system.getNetworkList()){
            System.out.println("NN "+ n.getNetworkName());
            for(Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
                
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.PublicHealthDepartment){
                   for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                        if(workRequest instanceof PatientRegisterationRequest){
                          if(((PatientRegisterationRequest) workRequest).getStatus().equals("Registered")){
                              registered++;
                          }else if (((PatientRegisterationRequest) workRequest).getStatus().equals("Vaccinated Dose 1"))
                            firstDosage++;
                          else if(((PatientRegisterationRequest) workRequest).getStatus().equals("Vaccinated Dose 2"))
                              secondDosage++;
                        }
                    }   
                }
            }
        }
        result = new DefaultPieDataset();
        
        result.setValue("Registered", registered);
        result.setValue("First dose", firstDosage);
        result.setValue("Second Dose", secondDosage);
        createChart(result, "Covid");
        
        System.out.println("COunt "+ registered);
           return result;

       }
    
    private PieDataset createDataset1() {
        System.out.println("PieDataset");
        int count =0;
        int pfizer=0;
        int moderna=0;
        int jh =0;
        
        for(Network n : system.getNetworkList()){
            System.out.println("NN "+ n.getNetworkName());
            for(Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
               
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.PublicHealthDepartment){
                    for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                        if(workRequest instanceof PatientRegisterationRequest){
                            System.out.println("VCCC "+ ((PatientRegisterationRequest) workRequest).getVaccineType());
                            if(!((PatientRegisterationRequest) workRequest).getStatus().equals("Registered") &&
                                    ((PatientRegisterationRequest) workRequest).getVaccineType().equalsIgnoreCase("pfizer")){
                                pfizer++;
                                
                            }else if(!((PatientRegisterationRequest) workRequest).getStatus().equals("Registered") &&
                                    ((PatientRegisterationRequest) workRequest).getVaccineType().equalsIgnoreCase("moderna")){
                                moderna++;
                            }else if(!((PatientRegisterationRequest) workRequest).getStatus().equals("Registered") &&
                                    ((PatientRegisterationRequest) workRequest).getVaccineType().equalsIgnoreCase("J&J/Janssen")){
                                jh++;
                            
                        }   
                    }
                }
            }
        }
   
    
    }
        result = new DefaultPieDataset();
        
        result.setValue("Pfizer", pfizer);
        result.setValue("Moderna", moderna);
        result.setValue("Johnson & Johnson", jh);
        createChart(result, "Covid");
        
        //System.out.println("COunt "+ registered);
           return result;

       }

    private JFreeChart createChart(PieDataset dataset, String title) {
           System.out.println("Create Chart");
           JFreeChart chart = ChartFactory.createPieChart3D(
               title, dataset, true, true, false);
           PiePlot3D plot = (PiePlot3D) chart.getPlot();
           plot.setStartAngle(290);
           plot.setDirection(Rotation.CLOCKWISE);
           plot.setForegroundAlpha(0.5f);
           plot.setCircular(true);
           return chart;

       }
    
       private JFreeChart createChart1(PieDataset dataset, String title) {
           System.out.println("Create Chart");
           JFreeChart chart = ChartFactory.createPieChart3D(
               title, dataset, true, true, false);
           PiePlot3D plot = (PiePlot3D) chart.getPlot();
           plot.setStartAngle(400);
           plot.setDirection(Rotation.CLOCKWISE);
           plot.setForegroundAlpha(0.5f);
           plot.setCircular(true);
           return chart;

       }

    public void getData(){
        int count =0;
        int pfizer=0;
        int moderna=0;
        int jh =0;
        
        for(Network n : system.getNetworkList()){
            System.out.println("NN "+ n.getNetworkName());
            for(Enterprise e : n.getEnterpriseDirectory().getEnterpriseDirectoryList()){
               
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.PublicHealthDepartment){
                    for( WorkRequest workRequest : e.getWorkQueue().getWorkRequestList() ){
                        if(workRequest instanceof PatientRegisterationRequest){
                            System.out.println("VCCC "+ ((PatientRegisterationRequest) workRequest).getVaccineType());
                            if(!((PatientRegisterationRequest) workRequest).getStatus().equals("Registered") &&
                                    ((PatientRegisterationRequest) workRequest).getVaccineType().equalsIgnoreCase("pfizer")){
                                pfizer++;
                                
                            }else if(!((PatientRegisterationRequest) workRequest).getStatus().equals("Registered") &&
                                    ((PatientRegisterationRequest) workRequest).getVaccineType().equalsIgnoreCase("moderna")){
                                moderna++;
                            }else if(!((PatientRegisterationRequest) workRequest).getStatus().equals("Registered") &&
                                    ((PatientRegisterationRequest) workRequest).getVaccineType().equalsIgnoreCase("J&J/Janssen")){
                                jh++;
                            
                        }   
                    }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
