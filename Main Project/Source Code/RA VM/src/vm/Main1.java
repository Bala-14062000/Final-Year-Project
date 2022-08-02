/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vm;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.UIManager;
/**
 *
 * @author seabirds
 */
public class Main1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         JFrame.setDefaultLookAndFeelDecorated(true);
         JDialog.setDefaultLookAndFeelDecorated(true);
         try
  	 {
           
            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel"); 
         }
  	 catch (Exception ex)
  	 {
   		System.out.println("Failed loading L&F: ");
   		System.out.println(ex);
  	 }
        int id=Integer.parseInt(JOptionPane.showInputDialog(new JFrame(),"Enter VM Id"));
        String sip=JOptionPane.showInputDialog(new JFrame(), "Enter Cloud Server IP","127.0.0.1");
        VMFrame nf=new VMFrame(id,sip);
        nf.setVisible(true);
        nf.setResizable(false);
        nf.setTitle("VM - "+id);
        
        VMReceiver nr=new VMReceiver(nf,id,sip);
        nr.start();
        
    }
}
