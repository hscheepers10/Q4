/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package q4_chat;

import com.sun.security.ntlm.Client;
import com.sun.security.ntlm.Server;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * @author User
 */

public class serverChat extends JFrame{
    
    //Attributes.  
    private final JPanel cpane1,cpane2,cpane3;
    private final JLabel cservLbl;
    private final JButton csendBtn,cexitBtn;
    private final JTextField ctxtf;
    private static JTextArea ctxta;
    private final Box chba,chb1,chb2,cvb1,cvb2;

    private final boolean click = false;
    public static DataInputStream in;
    public static DataOutputStream out;
    
    
    
    //Constructor.  
    public serverChat() {
        //Textserver.  

        
        //THIS.  
        this.setTitle("Server Chat");
        this.setSize(600,380);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //JPaneles 
        cpane1 = new JPanel();
        cpane2 = new JPanel();
        cpane3 = new JPanel(new GridLayout(2,2,5,5));
        
        //JLabel
        cservLbl = new JLabel("Server");
        cservLbl.setHorizontalAlignment(JLabel.LEFT);
        
        //JTextfields and JTextArea
        ctxtf = new JTextField(20);
        ctxta = new JTextArea(12,20);
        
        //JButtons
        csendBtn = new JButton("Send");
        csendBtn.addActionListener(new serverChat.sSend());
        
        cexitBtn = new JButton("Exit");
        cexitBtn.addActionListener(new serverChat.sExit()); 
        
        //Box Layouts
        //H-boxes
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
        
        //V-boxes
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
    
    //Server send class.
    class sSend implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            String sendTxt = ctxtf.getText();
            String servTime = new SimpleDateFormat("HH.mm.ss").format(new Date());
            String sendFor = servTime+" Server: "+sendTxt+"\n";
            
            try { 
                out.writeUTF(sendFor);
                ctxta.append(sendFor); 
                ctxtf.setText(null);
            } 
            catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
//            ctxtf.setText(null);         
        }
    }
    
    //Server Exit class.  
    class sExit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
    
    //Main Method for Client side. 
    public static void main (String[] args){
        serverChat serv = new serverChat();
        System.out.println("Server is starting...");
        
        try {
             ServerSocket serv1 = new ServerSocket(9000);
             Socket serv2 = serv1.accept();
             in = new DataInputStream(serv2.getInputStream());
             out = new DataOutputStream(serv2.getOutputStream()); 
             String ins = in.readUTF();
             ctxta.append(ins);
             
            } catch (IOException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
      
}
