/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
/**
 *
 * @author seabirds
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try
        {
           
           
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel"); 
  	}
  	catch (Exception ex)
  	{
   		System.out.println("Failed loading L&F: ");
   		System.out.println(ex);
  	}
        int id=Integer.parseInt(JOptionPane.showInputDialog(new JFrame(),"Enter User Id"));
        String ip=JOptionPane.showInputDialog(new JFrame(),"Enter Cloud Server IP","127.0.0.1");
        UserFrame uf=new UserFrame(id,ip);
        uf.setVisible(true);
        uf.setResizable(false);
        uf.setTitle("User - "+id);
        
        UserReceiver ur=new UserReceiver(uf,id,ip);
        ur.start();
    }
}
