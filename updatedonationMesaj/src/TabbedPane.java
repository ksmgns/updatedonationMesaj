

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Component;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.*;
import java.text.*;
import javax.swing.DefaultListCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JComboBox;

public class TabbedPane extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
    
    public TabbedPane() {
    	
    	
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(500, 400);
        setTitle("Donation Update");
        JTabbedPane jtp = new JTabbedPane();
        getContentPane().add(jtp);
        jtp.setBounds(100, 100, 450, 300);
        
        JPanel jp1 = new JPanel();
        JLabel label1 = new JLabel();
        label1.setBounds(239, 5, 0, 0);
        jp1.setLayout(null);
        jp1.add(label1);
        jtp.addTab("Update Message", jp1);
        
        textField = new JTextField();
        textField.setBounds(192, 28, 86, 20);
        jp1.add(textField);
        textField.setColumns(10);
        final JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JButton btnNewButton = new JButton("Mesaj Getir");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		DBAutomationConnection msj = new DBAutomationConnection();
				
					String k = null;
					try {
						k = msj.main(textField.getText());
					} catch (ClassNotFoundException | IOException
							| SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					textArea.setText(k);
				
				
        	}
        });
        btnNewButton.setBounds(299, 27, 122, 23);
        jp1.add(btnNewButton);
        
        JLabel lblKsaNo = new JLabel("K\u0131sa No:");
        lblKsaNo.setBounds(101, 31, 46, 14);
        jp1.add(lblKsaNo);
        
        
        scrollPane.setBounds(99, 82, 322, 118);
        jp1.add(scrollPane);
        
        
        scrollPane.setViewportView(textArea);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		 int n = JOptionPane.showConfirmDialog(
				            null,
				            "Deðiþiklik Yapmak Ýstediðinizden Emin misiniz?",
				            "Update yapýlsýn mý?",
				            JOptionPane.YES_NO_OPTION);

				        if(n==0){
				        	if (textArea.getText().length()>160){
				        		
				        		JOptionPane.showMessageDialog(null, "Mesaj 160 karakterden fazla olamaz.");
				        	}
				        	else{
				        	DBAutomationConnection k = new DBAutomationConnection();
							try {
								k.update(textArea.getText(), textField.getText());
								JOptionPane.showMessageDialog(null, "Mesaj Baþarý ile Update Edildi.");
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				         
				        	}
				        }
				        else {
				            JOptionPane.showMessageDialog(null, "Update iþleminden vazgeçildi.");
				        }	
        	}
        });
        btnUpdate.setBounds(299, 240, 122, 23);
        jp1.add(btnUpdate);
        
        JLabel lblMesaj = new JLabel("Mesaj:");
        lblMesaj.setBounds(101, 67, 46, 14);
        jp1.add(lblMesaj);
        JPanel jp2 = new JPanel();
        jp2.setLayout(null);
       
        JLabel label2 = new JLabel();
        label2.setBounds(239, 5, 0, 0);
       
        jp2.add(label2);
        jtp.addTab("Update StopeDate", jp2);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 13); // 1pm

        float minutes = 100.5f;

     
        final JFormattedTextField formattedTextField_1 = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        formattedTextField_1.setValue(new java.util.Date());
        
        textField_2 = new JTextField();
        textField_3 = new JTextField();
        textField_1 = new JTextField();
        textField_1.setBounds(131, 29, 152, 20);
        jp2.add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnNewButton_1 = new JButton("Tarih Getir");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		DBAutomationConnection tarih=new DBAutomationConnection();
        	
        		try {
        			Vector k=tarih.TarihGetir(textField_1.getText());
        			
					textField_2.setText((String) k.get(0));
					textField_3.setText((String) k.get(1));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnNewButton_1.setBounds(315, 28, 105, 23);
        jp2.add(btnNewButton_1);
        
        JLabel lblKsaNo_1 = new JLabel("K\u0131sa No:");
        lblKsaNo_1.setBounds(64, 32, 46, 14);
        jp2.add(lblKsaNo_1);
        
       
        textField_2.setBounds(131, 119, 152, 20);
        jp2.add(textField_2);
        textField_2.setColumns(10);
        
        JLabel lblStartdate = new JLabel("StartDate:");
        lblStartdate.setBounds(64, 122, 57, 14);
        jp2.add(lblStartdate);
        
       
        textField_3.setBounds(131, 150, 152, 20);
        jp2.add(textField_3);
        textField_3.setColumns(10);
        
     
        JLabel lblStopdate = new JLabel("StopDate:");
        lblStopdate.setBounds(64, 153, 57, 14);
        jp2.add(lblStopdate);
        
       
        
        
        JButton btnUpdate_1 = new JButton("Update");
        btnUpdate_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		 int n = JOptionPane.showConfirmDialog(
				            null,
				            "Deðiþiklik Yapmak Ýstediðinizden Emin misiniz?",
				            "Update yapýlsýn mý?",
				            JOptionPane.YES_NO_OPTION);

				        if(n==0){
				        	if (textField_2.getText().equals("") || textField_3.getText().equals("")){
				        		
				        		JOptionPane.showMessageDialog(null, "Baþlangýç yada Bitiþ tarihi boþ býrakýlamaz.");
				        	}
				        	else{
				        		
				        	
				        		DBAutomationConnection k = new DBAutomationConnection();
								try {
									
									k.updateTarih(formattedTextField_1.getText(), textField_1.getText());
									
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									JOptionPane.showMessageDialog(null, " Update Baþarýsýz oldu.");
								}
					           
				        	
				        	}
				        }
				        else {
				            JOptionPane.showMessageDialog(null, "Update iþleminden vazgeçildi.");
				        }		
				}
        		
        	
        });
        btnUpdate_1.setBounds(194, 219, 89, 23);
        jp2.add(btnUpdate_1);
        
       
        
        
        formattedTextField_1.setBounds(131, 181, 152, 20);
        jp2.add(formattedTextField_1);
        
        JLabel lblNewStopdate = new JLabel(" New StopDate:");
        lblNewStopdate.setBounds(32, 184, 89, 14);
        jp2.add(lblNewStopdate);
        
        
        
        
        
        
    }
    public static void main(String[] args) {
        
        TabbedPane tp = new TabbedPane();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
        
    }
}
