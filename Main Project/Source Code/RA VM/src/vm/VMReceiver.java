/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vm;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;
/**
 *
 * @author seabirds
 */
public class VMReceiver extends Thread
{
    VMFrame nf;
    int nid;
    int npt;
    String sip;
    DatagramSocket ds;
    VMReceiver(VMFrame ne,int id,String ip1)
    {
        nf=ne;
        nid=id;
        npt=nid+8000;
        sip=ip1;
    }
    
    public void run()
    {
        try
        {
            try
            {
                ds=new DatagramSocket(npt);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(nf,"ID already exist");
                System.exit(0);
                //e.printStackTrace();
            }
            String myip=InetAddress.getLocalHost().getHostAddress();
            String m1="NodeDt#"+nid+"#"+myip+"#"+npt;
            byte bt[]=m1.getBytes();
            DatagramPacket dp1=new DatagramPacket(bt,0,bt.length,InetAddress.getByName(sip),9000);
            ds.send(dp1);
            
            while(true)
            {
                byte data[]=new byte[60000];
                DatagramPacket dp=new DatagramPacket(data,0,data.length);
                ds.receive(dp);
                
                String str=new String(dp.getData()).trim();
                String req[]=str.split("#");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
