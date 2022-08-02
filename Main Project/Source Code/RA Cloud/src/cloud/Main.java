/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud;
import javax.swing.JFrame;
import javax.swing.JDialog;
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
           
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
  	 }
  	 catch (Exception ex)
  	 {
   		System.out.println("Failed loading L&F: ");
    		System.out.println(ex);
    	 }
        CloudFrame cf=new CloudFrame();
        cf.setVisible(true);
        cf.setTitle("Cloud");
        cf.setResizable(false);
        
        CloudReceiver cr=new CloudReceiver(cf);
        cr.start();
    }
}
