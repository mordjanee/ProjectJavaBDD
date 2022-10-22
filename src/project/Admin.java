/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author mordj
 */
public class Admin extends JFrame{
    
    public static void main (String[] args){
        
        JFrame frame = new JFrame();
        frame.pack();
        frame.setVisible(true);
    }
    
    private Statement stmt;
    JLabel lblMail, lblPassword;
    JTextField txtMail, txtPassword;
    JButton btnConnection, btnExit;
    List<String> mail = new ArrayList<>();
    List<String> password = new ArrayList<>();
    
    
    public Admin(){
        
        JPanel panel = new JPanel(new GridLayout(1, 1));
        
        panel.add(lblMail = new JLabel("Mail"));
        panel.add(txtMail = new JTextField());
        
        panel.add(lblPassword = new JLabel("Password"));
        panel.add(txtPassword = new JTextField());
        
        panel.add(btnConnection = new JButton("Connection"));
        panel.add(btnExit = new JButton("Exit"));
        
        add(panel);
        
        btnConnection.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                
                try{
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    System.out.println("Driver Loaded");
                    
                    String url = "jdbc:ucanaccess://D:\\Semestre_5\\Java\\Project\\DatabaseProject.accdb";
                    
                    Connection connection = DriverManager.getConnection(url);
                    
                    stmt = connection.createStatement();
                    System.out.println("Database Loaded");
                    
                    String QueryString = "Select * from Admin";
                    ResultSet rset = stmt.executeQuery(QueryString);
                    
                    
                    while(rset.next()){
                        mail.add(rset.getString("Mail"));
                        password.add(rset.getString("Password"));
                    }

                }catch(Exception ex){
                    System.out.println("Error " + ex);
                }
                
                int i = 0;
                boolean reg = false;
                for(i=0; i<mail.size(); i++){
                    if(mail.get(i).equals(txtMail.getText()) && password.get(i).equals(txtPassword.getText())){
                        reg = true;
                    }
                }
                
                if(reg==true){
                    JFrame frame = new AdminAction();
                    frame.pack();
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Mail or Password incorrect");
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
