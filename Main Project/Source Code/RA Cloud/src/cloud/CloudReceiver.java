/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author seabirds
 */
public class CloudReceiver extends Thread
{
    CloudFrame cf;
    Details dt=new Details();
    CloudReceiver(CloudFrame ce)
    {
        cf=ce;
    }
    
    public void run()
    {
        try
        {
            DatagramSocket ds=new DatagramSocket(9000);
            while(true)
            {
                byte data[]=new byte[60000];
                DatagramPacket dp=new DatagramPacket(data,0,data.length);
                ds.receive(dp);
                
                String str=new String(dp.getData()).trim();
                String req[]=str.split("#");
                if(req[0].equals("NodeDt"))
                {
                    DefaultTableModel dm=(DefaultTableModel)cf.jTable1.getModel();
                    Vector v=new Vector();
                    v.add(req[1]);
                    dm.addRow(v);
                    
                    dt.vm[dt.vind][0]=req[1]; // id
                    dt.vm[dt.vind][1]=req[2]; //ip
                    dt.vm[dt.vind][2]=req[3]; //pt
                    dt.vind++;
                    
                } // NodeDt
                
                if(req[0].equals("UserDt"))
                {
                    DefaultTableModel dm=(DefaultTableModel)cf.jTable3.getModel();
                    Vector v=new Vector();
                    v.add(req[1]);
                    dm.addRow(v);
                    
                    dt.user[dt.uind][0]=req[1]; // id
                    dt.user[dt.uind][1]=req[2]; //ip
                    dt.user[dt.uind][2]=req[3]; //pt
                    dt.uind++;
                    
                } //UserDt
                
                if(req[0].equals("AddResource"))
                {
                                        
                    DefaultTableModel dm=(DefaultTableModel)cf.jTable2.getModel();
                    Vector v=new Vector();
                    v.add(req[1]);
                    v.add(req[2]);
                    v.add(req[3]);
                    v.add(req[4]);
                    v.add(req[5]);
                    dm.addRow(v);
                    
                } //AddResource
                
                if(req[0].equals("UpdateResource"))
                {
                    
                    DefaultTableModel dm=(DefaultTableModel)cf.jTable2.getModel();
                    int row=dm.getRowCount();
                    for(int i=0;i<row;i++)
                    {
                        String id=dm.getValueAt(i, 0).toString();
                        String re=dm.getValueAt(i, 1).toString();
                        
                        if(id.equals(req[1]) && re.equals(req[2]))
                        {
                            dm.setValueAt(req[3], i, 2);
                            dm.setValueAt(req[4], i, 3);
                            dm.setValueAt(req[5], i, 4);
                        }
                    }
                    
                } //UpdateResource
                if(req[0].equals("GetResource"))
                {
                    DefaultTableModel dm=(DefaultTableModel)cf.jTable2.getModel();
                    int row=dm.getRowCount();
                    ArrayList at=new ArrayList();
                    for(int i=0;i<row;i++)
                    {
                        String r1=dm.getValueAt(i, 1).toString();
                        if(!(at.contains(r1)))
                            at.add(r1);                        
                    }
                    String s1="SetResource";
                    for(int i=0;i<at.size();i++)
                    {
                        s1=s1+"#"+at.get(i).toString();
                    }
                    System.out.println("s1 "+s1);
                    byte bt[]=s1.getBytes();
                    int pt=Integer.parseInt(req[1])+7000;
                    String uip=req[2];
                    DatagramPacket dp1=new DatagramPacket(bt,0,bt.length,InetAddress.getByName(uip),pt);
                    ds.send(dp1);
                    
                    
                } // getresource
                
                if(req[0].equals("Task"))
                {
                    String uid=req[1];
                    String rc=req[2];
                    String uip=req[3];
                    
                    String res="";
                    ArrayList at1=new ArrayList();
                    ArrayList at2=new ArrayList();
                    for(int i=4;i<req.length;i++)
                    {
                        res=res+req[i]+"-";
                        String g1[]=req[i].split("@");
                        at1.add(g1[0]);
                        at2.add(g1[1]);
                    }
                    res=res.substring(0, res.lastIndexOf("-"));
                    
                    DefaultTableModel dm1=(DefaultTableModel)cf.jTable4.getModel();
                    Vector v=new Vector();
                    v.add(uid);
                    v.add(rc);
                    v.add(res);
                    dm1.addRow(v);
                    
                    DefaultTableModel dm2=(DefaultTableModel)cf.jTable2.getModel();
                    int row=dm2.getRowCount();
                    ArrayList at3=new ArrayList();
                    for(int i=0;i<row;i++)
                    {
                        String id=dm2.getValueAt(i, 0).toString();
                        String re1=dm2.getValueAt(i, 1).toString();
                        if(at1.contains(re1))
                        {
                            if(!(at3.contains(id)))
                                at3.add(id);
                        }
                    }
                    
                    
                    String s1="SetTaskNode";
                    for(int i=0;i<at3.size();i++)
                    {
                        s1=s1+"#"+at3.get(i).toString();
                    }
                    if(s1.contains("#"))
                    {
                        
                    }
                    else
                        s1=s1+"#"+"No";
                    System.out.println("s1 "+s1);
                    byte bt[]=s1.getBytes();
                    int pt=Integer.parseInt(uid)+7000;
                    DatagramPacket dp1=new DatagramPacket(bt,0,bt.length,InetAddress.getByName(uip),pt);
                    ds.send(dp1);
                    
                } //Task
                
                if(req[0].equals("Query"))
                {
                    String rs=req[1];
                    String cond=req[2];
                    String val=req[3];
                    String type=req[4];
                    int pt=Integer.parseInt(req[5])+7000;
                    String  uip=req[6];
                              
                    DefaultTableModel dm2=(DefaultTableModel)cf.jTable2.getModel();
                    int row=dm2.getRowCount();
                    ArrayList at1=new ArrayList();
                    for(int i=0;i<row;i++)
                    {
                        String id=dm2.getValueAt(i, 0).toString();
                        String re1=dm2.getValueAt(i, 1).toString();
                        String mi1=dm2.getValueAt(i, 2).toString();
                        String ma1=dm2.getValueAt(i, 3).toString();
                        
                        if(re1.equals(rs))
                        {
                            if(cond.equals("Min"))
                            {
                                if(Double.parseDouble(val)<=Double.parseDouble(mi1))
                                {
                                    at1.add(id);
                                }
                            }
                            if(cond.equals("Max"))
                            {
                                 if(Double.parseDouble(val)<=Double.parseDouble(ma1))
                                {
                                    at1.add(id);
                                }
                            }
                        }
                    }
                    String ms1="Result1#"+rs+"#"+cond+"#"+val;
                    System.out.println("at1 size "+at1.size());
                    if(at1.isEmpty())
                    {
                        ms1=ms1+"#No";
                    }
                    else if(at1.size()==1)
                    {
                        ms1=ms1+"#"+at1.get(0);
                    }
                    else
                    {
                        Random rn=new Random();
                        int n1=rn.nextInt(at1.size());
                        ms1=ms1+"#"+at1.get(n1);
                    }
                    
                    byte bt[]=ms1.getBytes();
                    DatagramPacket dp1=new DatagramPacket(bt,0,bt.length,InetAddress.getByName(uip),pt);
                    ds.send(dp1);
                    
                } // Query
                
                if(req[0].equals("RangeQuery"))
                {
                    //String ms="RangeQuery#"+rs1+"#"+cond1+"#"+value1+"#"+comp+"#"+rs2+"#"+cond2+"#"+value2+"#"+uid;
                    String rs1=req[1];
                    String cond1=req[2];
                    String val1=req[3];
                    
                    String comp=req[4];
                    String rs2=req[5];
                    String cond2=req[6];
                    String val2=req[7];
                    String uid=req[8];
                    int pt=Integer.parseInt(uid)+7000;
                    String uip=req[9];
                    
                    DefaultTableModel dm2=(DefaultTableModel)cf.jTable2.getModel();
                    int row=dm2.getRowCount();
                    ArrayList at1=new ArrayList();
                    ArrayList at2=new ArrayList();
                    for(int i=0;i<row;i++)
                    {
                        String id=dm2.getValueAt(i, 0).toString();
                        String re1=dm2.getValueAt(i, 1).toString();
                        String mi1=dm2.getValueAt(i, 2).toString();
                        String ma1=dm2.getValueAt(i, 3).toString();
                        
                        if(re1.equals(rs1))
                        {
                            if(cond1.equals("Min"))
                            {
                                if(Double.parseDouble(val1)<=Double.parseDouble(mi1))
                                {
                                    at1.add(id);
                                }
                            }
                            if(cond1.equals("Max"))
                            {
                                 if(Double.parseDouble(val1)<=Double.parseDouble(ma1))
                                {
                                    at1.add(id);
                                }
                            }
                        }
                        
                        
                        if(re1.equals(rs2))
                        {
                            if(cond2.equals("Min"))
                            {
                                if(Double.parseDouble(val2)<=Double.parseDouble(mi1))
                                {
                                    at2.add(id);
                                }
                            }
                            if(cond2.equals("Max"))
                            {
                                 if(Double.parseDouble(val2)<=Double.parseDouble(ma1))
                                {
                                    at2.add(id);
                                }
                            }
                        }
                        
                    }
                    String ms1="Result2#"+rs1+"#"+cond1+"#"+val1;
                    ms1=ms1+"#"+comp+"#"+rs2+"#"+cond2+"#"+val2;
                    System.out.println("at1 size "+at1.size());
                    System.out.println("at2 size "+at2.size());
                    if(at1.isEmpty()||at2.isEmpty())
                    {
                        ms1=ms1+"#No";
                    }
                    else if(at2.size()==1 || at1.size()==1)
                    {
                        //if(at1.get(0))
                        ms1=ms1+"#"+at1.get(0)+","+at2.get(0);
                    }
                    else
                    {
                        Random rn=new Random();
                        int n1=rn.nextInt(at1.size());
                        int n2=rn.nextInt(at2.size());
                        ms1=ms1+"#"+at1.get(n1)+" , "+at2.get(n2);
                    }
                    
                    
                    byte bt[]=ms1.getBytes();
                    DatagramPacket dp1=new DatagramPacket(bt,0,bt.length,InetAddress.getByName(uip),pt);
                    ds.send(dp1);
                    
                    
                } // RangeQuery
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
            
}
