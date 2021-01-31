/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmi.calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 *
 * @author Reshma
 */
public class BMICalculator implements ActionListener{
    JTextField tfHeight,tfWeight;
    static JLabel labelHeight,labelWeight,status,result,result2;
    JButton btn;
    JFrame frame;
    /**
     * @param args the command line arguments
     */
    BMICalculator(){
        frame = new JFrame();
        Color c = new Color(240,128,128);
        frame.getContentPane().setBackground(c);
        Image icon = new ImageIcon(this.getClass().getResource("/icons/logo.png")).getImage(); 
        labelWeight = new JLabel("Weight in kg");
        labelWeight.setFont(new Font("Serif",Font.PLAIN,16));
        labelWeight.setForeground(Color.BLACK);
        labelWeight.setBounds(100,70,100,20);
        tfWeight = new JTextField();
        tfWeight.setToolTipText("Enter weight");
        tfWeight.setBounds(90,95,100,30);
        labelHeight = new JLabel("Height in cm");
        labelHeight.setFont(new Font("Serif",Font.PLAIN,16));
        labelHeight.setForeground(Color.BLACK);
        labelHeight.setBounds(100,130,100,20);
        tfHeight = new JTextField();
        tfHeight.setToolTipText("Enter height");
        tfHeight.setBounds(90,152,100,30);
        status = new JLabel();
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setBounds(60,185,190,40);
        btn=new JButton("Calculate");
        btn.setBounds(95,230,90,30);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        result = new JLabel();
        result.setBounds(95,270,100,30);
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result2 = new JLabel();
        result2.setBounds(50,300,190,60);
        result2.setHorizontalAlignment(SwingConstants.CENTER);
        result2.setOpaque(false);
        btn.addActionListener(this);
        frame.setIconImage(icon);
        frame.add(labelHeight);
        frame.add(labelWeight);
        frame.add(tfHeight);
        frame.add(tfWeight);
        frame.add(btn);
        frame.add(status);
        frame.add(result);
        frame.add(result2);
        frame.setSize(300,450);
        frame.setLayout(null);
        frame.setTitle("Height Calculator");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    // Convert height in centimeter to meter
    public static double centimeterToMeter(double cm){
        double metre = cm/100;
        return metre;
    }
    // Calculate BMI
    public static double findBMI(double height,double weight){
        double h = centimeterToMeter(height);
        double bmi = weight/h/h;
        DecimalFormat df = new DecimalFormat("##.##");
        String trimBmi=df.format(bmi);
        result.setFont(new Font("Serif",Font.PLAIN,16));
        result.setForeground(Color.black);
        result.setText("BMI is "+trimBmi);
        status.setOpaque(false);
        return bmi;
    }
    // Display bmi by JLabel
    public static void setResult(double bmi){
        result2.setFont(new Font("Serif",Font.PLAIN,20));
        result2.setOpaque(true);
        if(bmi<15){
            String s = "<html>Very severely<br> underweight</html>";
            result2.setBackground(new Color(0,0,255));
            result2.setForeground(Color.WHITE);
            result2.setText(s);
        }
        else if(bmi>=15 && bmi<16){
            result2.setBackground(new Color(102,153,255));
            result2.setForeground(Color.WHITE);
            result2.setText("Severely Underweight");
        }
        else if(bmi>=16 && bmi<18.5){
            result2.setBackground(new Color(102,255,255));
            result2.setForeground(Color.black);
            result2.setText("Underweight");
        }
        else if(bmi>=18.5 && bmi<=24.9){
            result2.setBackground(Color.GREEN);
            result2.setForeground(Color.black);
            result2.setText("Healthy");
        }
        else if(bmi>=25 && bmi<=29.9){
            result2.setBackground(Color.yellow);
            result2.setForeground(Color.black);
            result2.setText("Overweight");
        }
        else if(bmi>=30 && bmi<40){
            result2.setBackground(Color.orange);
            result2.setForeground(Color.black);
            result2.setText("Obese");
        }
        else if(bmi>=40){
            result2.setBackground(Color.red);
            result2.setForeground(Color.white);
            result2.setText("Extremely Obese");
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        BMICalculator bmicalculator = new BMICalculator();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(tfWeight.getText().trim().isEmpty()){
            status.setOpaque(true);
            status.setBackground(new Color(204,153,255));
            status.setFont(new Font("Serif",Font.PLAIN,18));
            status.setText("Weight cannot be empty");
        }
        else if(tfHeight.getText().trim().isEmpty()){
            status.setOpaque(true);
            status.setBackground(new Color(204,153,255));
            status.setFont(new Font("Serif",Font.PLAIN,18));
            status.setText("Height cannot be empty");
        }
        else{
            try{
                double wtf = Double.parseDouble(tfWeight.getText());
                double htf = Double.parseDouble(tfHeight.getText());
                if(wtf==0 || htf==0){
                    status.setText("");
                    status.setOpaque(false);
                    result.setText("");
                    result2.setText("");
                    result2.setOpaque(false);
                    JOptionPane.showMessageDialog(frame,"Height and weight cannot be 0");   
                }
                else{
                    double userbmi=findBMI(htf,wtf);
                    setResult(userbmi);
                    status.setText("");
                }
            } catch(NumberFormatException nfe){
                status.setOpaque(true);
                status.setText("Enter only numbers");
                status.setForeground(Color.DARK_GRAY);
                status.setBackground(new Color(204,153,255));
                status.setFont(new Font("Serif",Font.PLAIN,18));
            }
        }
    }
    
}
