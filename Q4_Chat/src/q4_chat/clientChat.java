/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package q4_chat;

import com.sun.security.ntlm.Client;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author User
 */

public class clientChat extends JFrame{
    
    //Attributes.  
    private final JPanel cpane1,cpane2,cpane3;
    private final JLabel cservLbl;
    private final JButton csendBtn,cexitBtn;
    private final JTextField ctxtf;
    private static JTextArea ctxta;
    private final Box chba,chb1,chb2,cvb1,cvb2;
    
    public static DataInputStream in;
    public static DataOutputStream out;
    
    
    
    //Constructor.  
    public clientChat() {
        //THIS.  
        this.setTitle("Client Chat");
        this.setSize(600,380);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //JPaneles 
        cpane1 = new JPanel();
        cpane2 = new JPanel();
        cpane3 = new JPanel(new GridLayout(2,2,5,5));
        
        //JLabel
        cservLbl = new JLabel("Client");
        cservLbl.setHorizontalAlignment(JLabel.LEFT);
        
        //JTextfields and JTextArea
        ctxtf = new JTextField(20);
        ctxta = new JTextArea(12,20);
        
        //JButtons
        csendBtn = new JButton("Send");
        csendBtn.addActionListener(new clSend());
        
        cexitBtn = new JButton("Exit");
        cexitBtn.addActionListener(new clExit()); 
        
        //Box Layout.  
        //H-boxes.  
        chb1 = Box.createHorizontalBox();
        chb1.add(Box.createHorizontalStrut(5));
        chb1.add(ctxtf);
        chb1.add(Box.createHorizontalStrut(5));
        chb1.add(csendBtn);
        
        chb2 = Box.createHorizontalBox();
        chb2.add(Box.createHorizontalStrut(230));
        chb2.add(cexitBtn);
      
        chba = Box.createHorizontalBox();
        chba.add(Box.createHorizontalStrut(-5));
        chba.add(ctxta);
        chba.add(Box.createHorizontalStrut(10));
        
        //vV-boxes.  
        cvb1 = Box.createVerticalBox();
        cvb1.add(cservLbl);
        cvb1.add(Box.createVerticalStrut(40));
        cvb1.add(chb1);
        cvb1.add(Box.createVerticalStrut(120));
        cvb1.add(chb2);
        
        cvb2 = Box.createVerticalBox();
        cvb2.add(Box.createVerticalStrut(20));
        cvb2.add(chba);
        cvb2.add(Box.createVerticalStrut(20));
        
        //Adding to panels.  
        cpane1.add(cvb1);
        cpane2.add(cvb2);
        
        //THIS.  
        this.add(cpane3, BorderLayout.NORTH);
        this.add(cpane1, BorderLayout.WEST);
        this.add(cpane2, BorderLayout.EAST);
        this.setVisible(true); 
    }
    
    //Client send class.  
    class clSend implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            String sendTxt = ctxtf.getText();
            String clientTime = new SimpleDateFormat("HH.mm.ss").format(new Date());
            String sendFor = clientTime+" Client "+sendTxt+"\n";
            
            try { 
                out.writeUTF(sendFor);
                ctxta.append(sendFor); 
                ctxtf.setText(null);
            } 
            catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            ctxtf.setText(null);         
        }
    }
    
    //Client Exit class.  
    class clExit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
    
    //Main Method for Client side. 
    public static void main (String[] args){
        clientChat cli = new clientChat();
        
        try {
             Socket cli1 = new Socket("localhost",9000);
             in = new DataInputStream(cli1.getInputStream());
             out = new DataOutputStream(cli1.getOutputStream()); 
             String ins = (String)in.readUTF();
             ctxta.append(ins);
             
            } catch (IOException ex) {
             Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
