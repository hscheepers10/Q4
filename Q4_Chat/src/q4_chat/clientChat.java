/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package q4_chat;

import java.awt.GridLayout;
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

public class clientChat extends JFrame{
    
    //Attributes.  
    JPanel pane1,pane2,pane3;
    JLabel servLbl;
    JButton sSendBtn,sExitBtn;
    JTextField sTxtf;
    JTextArea sTxta;
    Box hba,hb1,hb2,vb1,vb2;
    
    //Constructor.  
    public clientChat(){
        //Code.  
        super("Server Chat");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,380);
        this.setResizable(false);
        
        //Pane1.  
        pane1 = new JPanel();
        
        servLbl = new JLabel("Client: ");
        servLbl.setHorizontalAlignment(JLabel.LEFT);
        
        //Vertical Box.  
        vb1 = Box.createVerticalBox();
        vb1.add(servLbl);
        vb1.add(Box.createVerticalStrut(40));
        vb1.add(hb1);
        vb1.add(Box.createVerticalStrut(120));
        vb1.add(hb2);
        
        vb2 = Box.createVerticalBox();
        vb2.add(Box.createVerticalStrut(20));
        vb2.add(hba);
        vb2.add(Box.createVerticalStrut(20));
        
        pane1.add(vb1);
        
        //Pane2.  
        pane2 = new JPanel();
        
        sTxtf = new JTextField(20);
        sSendBtn = new JButton("Send");
        sExitBtn = new JButton("Exit");
        //Exit Onclicklistener.  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        sTxta = new JTextArea(12,20);
        
        //Horizontal Box.  
        hb1 = Box.createHorizontalBox();
        hb1.add(Box.createHorizontalStrut(5));
        hb1.add(sTxtf);
        hb1.add(Box.createHorizontalStrut(5));
        hb1.add(sSendBtn);
        
        hb2 = Box.createHorizontalBox();
        hb2.add(Box.createHorizontalStrut(230));
        hb2.add(sExitBtn);
        
        hba = Box.createHorizontalBox();
        hba.add(Box.createHorizontalStrut(-5));
        hba.add(sTxta);
        hba.add(Box.createHorizontalStrut(10));
        
        pane2.add(vb2);
        
        //Pane3.  
        pane3 = new JPanel(new GridLayout(2,2));
        
        
        
        
    }
    
}
