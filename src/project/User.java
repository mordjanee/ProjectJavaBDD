/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;

/**
 *
 * @author mordj
 */
public class User extends JFrame{
    
    public static void main (String[] args){
        
        JFrame frame = new JFrame();
        frame.pack();
        frame.setTitle("DESTINATION FILTER");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private Statement stmt;
    
    
    JLabel lblAveragePrice, img, img1, img2;
    JTextField txtAveragePrice;
    JComboBox chkCity, chkClimate, chkStars;
    JCheckBox chkPool, chkBreakfast, chkPark, chkWifi;
    JButton btnConnect, btnAll, btnFilter, btnExit;
    
    
    public User(){
        
        JPanel panel = new JPanel(new GridLayout(3, 1));
        
        panel.add(lblAveragePrice = new JLabel("Max Price"));
        panel.add(txtAveragePrice = new JTextField());
        panel.add(chkCity = new JComboBox());
        chkCity.setRenderer(new MyComboBoxRenderer("CITY"));
        chkCity.setSelectedIndex(-1);
        
        panel.add(chkClimate = new JComboBox());
        chkClimate.addItem("Tropical");
        chkClimate.addItem("Temperate");
        chkClimate.addItem("Polar");
        chkClimate.setRenderer(new MyComboBoxRenderer("CLIMATE"));
        chkClimate.setSelectedIndex(-1);
        
        panel.add(chkPool = new JCheckBox("Pool"));
        panel.add(chkBreakfast = new JCheckBox("Breakfast"));
        panel.add(chkPark = new JCheckBox("Parking"));
        panel.add(chkWifi = new JCheckBox("Wifi"));
        
        
        panel.add(chkStars = new JComboBox());
        chkStars.addItem("1");
        chkStars.addItem("2");
        chkStars.addItem("3");
        chkStars.addItem("4");
        chkStars.addItem("5");
        chkStars.setRenderer(new MyComboBoxRenderer("STARS"));
        chkStars.setSelectedIndex(-1);
        
        
        panel.add(btnConnect = new JButton("Connect"));
        panel.add(btnAll = new JButton("All destinations"));
        panel.add(btnFilter = new JButton("Filter"));
        
        panel.add(img = new JLabel(new ImageIcon("D:\\Semestre_5\\Java\\Project\\hotel.jpg")));
        panel.add(img = new JLabel(new ImageIcon("D:\\Semestre_5\\Java\\Project\\hotel1.jpg")));
        panel.add(img = new JLabel(new ImageIcon("D:\\Semestre_5\\Java\\Project\\hotel2.jpg")));
        panel.add(img = new JLabel(new ImageIcon("D:\\Semestre_5\\Java\\Project\\hotel3.jpg")));
        panel.add(img = new JLabel(new ImageIcon("D:\\Semestre_5\\Java\\Project\\hotel4.jpg")));
        
        btnExit = new JButton("Exit");
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        panel.add(btnExit);
                
               
        
        
        add(panel);
        
        
        btnConnect.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    System.out.println("Driver Loaded");

                    String url = "jdbc:ucanaccess://D:\\Semestre_5\\Java\\Project\\DatabaseProject.accdb";

                    Connection connection = DriverManager.getConnection(url);
                    stmt = connection.createStatement();

                    System.out.println("Database Loaded");

                    String QueryString = "Select * from Destination";
                    ResultSet rset = stmt.executeQuery(QueryString);

                    while(rset.next()){
                        chkCity.addItem(rset.getString("City")); 
                    }

                }catch(Exception ex){
                    System.out.println("Error " + ex);
                }
            }
        });
        
        
        
        btnAll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    String QueryString = "Select * from Destination";
                    ResultSet rset = stmt.executeQuery(QueryString);
                    
                    System.out.println("City : Max Price : Climate : Pool : Breakfast : Parking : Wifi : Stars");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    
                    while(rset.next()){
                        System.out.println(rset.getString("City") +
                                ": " + rset.getString("AveragePrice")+
                                ": " + rset.getString("Climate") +
                                ": " + rset.getString("Pool") +
                                ": " + rset.getString("Breakfast")+
                                ": " + rset.getString("Parking")+
                                ": " + rset.getString("Wifi")+
                                ": " + rset.getString("Stars"));
                    }
                    
                }catch(Exception ex){
                    System.out.println("Error " + ex);
                }
            }
        });
        
        btnFilter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try {
                    String QueryString = "Select * from Destination where";
                    
                    if(chkPool.isSelected()){
                        QueryString += " Pool = '1' ";
                    }
                    if(chkBreakfast.isSelected()){
                        QueryString += " Breakfast = '1' ";
                    }
                    if(chkPark.isSelected()){
                        QueryString += " Parking = '1' ";
                    }
                    if(chkWifi.isSelected()){
                        QueryString += " Wifi = '1' ";
                    }
                    
                    QueryString += " City = '" + chkCity.getSelectedItem().toString() + "' "; 
                    QueryString += " Climate = '" + chkClimate.getSelectedItem().toString() + "' "; 
                    QueryString += " Stars = '" + chkStars.getSelectedItem().toString() + "' "; 
                    
                    QueryString += " AveragePrice <= " + Integer.parseInt(txtAveragePrice.getText());
                    
                    QueryString = QueryString.replaceAll("  ", " and ");
                    System.out.println(QueryString);
                    
                           
                    ResultSet rset = stmt.executeQuery(QueryString);
                    
                    System.out.println("City : Max Price : Availablity : Climate : Pool : Breakfast : Parking : Wifi : Stars");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    
                    while(rset.next()){
                        System.out.println(rset.getString("City") +
                                ": " + rset.getString("AveragePrice")+
                                ": " + rset.getString("Climate") +
                                ": " + rset.getString("Pool") +
                                ": " + rset.getString("Breakfast")+
                                ": " + rset.getString("Parking")+
                                ": " + rset.getString("Wifi")+
                                ": " + rset.getString("Stars"));
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        
        
        btnExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                
            }
        });
    }

    class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
        private String _title;

        public MyComboBoxRenderer(String title) {
          _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean hasFocus) {
        if (index == -1 && value == null)
            setText(_title);
        else
            setText(value.toString());
            return this;
       }
    }
    
    
}
