/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author seabirds
 */
public class UserReceiver extends Thread
{
    UserFrame uf;
    int uid;
    int upt;
    String sip;
    DatagramSocket ds;
    UserReceiver(UserFrame ue,int id,String ip1)
    {
        uf=ue;
        uid=id;
        upt=uid+7000;
        sip=ip1;
    }
    public void run()
    {
        try
        {
            try
            {
                ds=new DatagramSocket(upt);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(uf,"ID already exist");
                System.exit(0);
                //e.printStackTrace();
            }
            String myip=InetAddress.getLocalHost().getHostAddress();
            String m1="UserDt#"+uid+"#"+myip+"#"+upt;
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
                
                if(req[0].equals("SetResource"))
                {
                    uf.jComboBox1.removeAllItems();
                    uf.jComboBox2.removeAllItems();
                    
                    ArrayList at1=new ArrayList();
                    for(int i=1;i<req.length;i++)
                    {
                        at1.add(req[i]);
                    }
                    DefaultTableModel dm=(DefaultTableModel)uf.jTable1.getModel();
                    int row=dm.getRowCount();                    
                    for(int i=0;i<row;i++)
                    {
                        String g1=dm.getValueAt(i, 0).toString();
                        if(at1.contains(g1))
                        {
                            dm.setValueAt("Yes",i, 3);
                            uf.jComboBox1.addItem(g1);
                            uf.jComboBox2.addItem(g1);
                        }
                    }
                    
                } //SetResource
                
                if(req[0].equals("SetTaskNode"))
                {
                    if(req[1].equals("No"))
                    {
                        uf.jLabel4.setText("Resource Not Allocated");
                    }
                    else
                    {
                        String g1="The following VM contains resources ";
                        for(int i=1;i<req.length;i++)
                        {
                               g1=g1+req[i]+" , ";
                        }
                        g1=g1.substring(0, g1.lastIndexOf(","));
                        uf.jLabel4.setText(g1);
                    }
                } //SetTaskNode
                
                if(req[0].equals("Result1"))
                {
                     DefaultTableModel dm=(DefaultTableModel)uf.jTable3.getModel();
                     Vector v=new Vector();
                     v.add(req[1]+" "+req[2]+" "+req[3]);
                     v.add(req[4]);
                     dm.addRow(v);
                     
                } //Result
                
                if(req[0].equals("Result2"))
                {
                     DefaultTableModel dm=(DefaultTableModel)uf.jTable3.getModel();
                     Vector v=new Vector();
                     v.add(req[1]+" "+req[2]+" "+req[3]+" "+req[4]+" "+req[5]+" "+req[6]+" "+req[7]);
                     v.add(req[8]);
                     dm.addRow(v);
                     
                } //Result
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
