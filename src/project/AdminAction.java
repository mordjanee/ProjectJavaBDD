/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author mordj
 */

public class AdminAction extends JFrame{
    
    public static void main (String[] args){
        
        JFrame frame = new JFrame();
        frame.pack();
        frame.setTitle("MANAGE DATABASE");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private Statement stmt;
    
    private JLabel lblCountry, lblCity, lblAveragePrice, lblClimate, 
            lblPool, lblBreakfast, lblPark, lblWifi, lblStars;  
    private JTextField txtCountry, txtCity, txtAveragePrice, txtClimate, 
            txtPool, txtBreakfast, txtPark, txtWifi, txtStars; 
    private JButton btnConnect, btnCheck, btnInsert, btnDelete, btnUpdate, btnExit;
    
    public AdminAction(){
        
        JPanel panel = new JPanel(new GridLayout(5, 1));
        
        panel.add(lblCity = new JLabel("City"));
        panel.add(txtCity = new JTextField(20));
        
        panel.add(lblAveragePrice = new JLabel("Average price"));
        panel.add(txtAveragePrice = new JTextField(20));
        
        panel.add(lblClimate = new JLabel("Climate"));
        panel.add(txtClimate = new JTextField(20));
        
        panel.add(lblPool = new JLabel("Pool"));
        panel.add(txtPool = new JTextField(20));
        
        panel.add(lblBreakfast = new JLabel("Breakfast"));
        panel.add(txtBreakfast = new JTextField(20));
        
        panel.add(lblPark = new JLabel("Parking"));
        panel.add(txtPark = new JTextField(20));
        
        panel.add(lblWifi = new JLabel("Wi-Fi"));
        panel.add(txtWifi = new JTextField(20));
        
        panel.add(lblStars = new JLabel("Stars"));
        panel.add(txtStars = new JTextField(20));
        
        panel.add(btnConnect = new JButton("Connect"));
        panel.add(btnCheck = new JButton("Check"));
        panel.add(btnInsert = new JButton("Insert"));
        panel.add(btnDelete = new JButton("Delete"));
        panel.add(btnUpdate = new JButton("Update"));
        panel.add(btnExit = new JButton("Exit"));
        
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

                }catch(Exception ex){
                    System.out.println("Error " + ex);
                }
            }

        });
        
        btnCheck.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    String QueryString = "Select * from Destination";
                    ResultSet rset = stmt.executeQuery(QueryString);
                    
                    System.out.println("City : AveragePrice : Climate : Pool : Breakfast : Parking : Wifi : Stars");
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
        
        btnInsert.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                String City = txtCity.getText();
                String AveragePrice = txtAveragePrice.getText();
                String Climate = txtClimate.getText();
                String Pool = txtPool.getText();
                String Breakfast = txtBreakfast.getText();
                String Parking = txtPark.getText();
                String Wifi = txtWifi.getText();
                String Stars = txtStars.getText();
                
                
                try{
                    String queryString = "Insert into Destination " + "(City, AveragePrice, Availablity, Climate, Pool, Breakfast, Parking, Wifi, Stars)" + "values " +
                            "('"+City+"','"+AveragePrice+"','"+Climate+"','"+Pool+"','"+Breakfast+"','"+Parking+"','"+Wifi+"','"+Stars+"')";
                    stmt.execute(queryString);
                    System.out.println("Destination Added");
                    
                }catch(Exception ex){
                    System.out.println("Error " + ex);
                }
            }
        });
        
        btnUpdate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                String City = txtCity.getText();
                String AveragePrice = txtAveragePrice.getText();
                String Climate = txtClimate.getText();
                String Pool = txtPool.getText();
                String Breakfast = txtBreakfast.getText();
                String Parking = txtPark.getText();
                String Wifi = txtWifi.getText();
                String Stars = txtStars.getText();
                
                System.out.println("Update a destination from the city");
                
                
                try{
                    String queryString = "UPDATE Customers set AveragePrice = '"+AveragePrice+"', Climate = '"+Climate+"', Pool = '"+Pool+"', Breakfast = '"+Breakfast+"', Parking = '"+Parking+"', Wifi = '"+Wifi+"', Stars = '"+Stars+"' where City = '" + City + "'";
                    
                    stmt.execute(queryString);
                    System.out.println("Destination Updated");
                    
                }catch(Exception ex){
                    System.out.println("Error " + ex);
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                String City = txtCity.getText();
                
                System.out.println("Delete a destination from the city");
                
                try{
                    String queryString = "DELETE FROM Destination WHERE City = '"+City+"'";
                    
                    stmt.execute(queryString);
                    System.out.println("Destination Deleted");
                    
                }catch(Exception ex){
                    System.out.println("Error " + ex);
                }
            }
        });
        
        btnExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                
            }
        });
    }
    
}

