/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author admin
 */
public class SchedulingTime 
{
    ArrayList list1=new ArrayList();
    ArrayList resource=new ArrayList();
    ArrayList list2=new ArrayList();
    ArrayList lt1=new ArrayList();
    ArrayList lt2=new ArrayList();
    
    int sc=0;
    
    SchedulingTime(ArrayList ar1,ArrayList ar2)
    {
        list1=ar1;
        resource=ar2;
    }
    
    public void getTime1()
    {
        try
        {
            int rs=Integer.parseInt(resource.get(0).toString());
            int ac=0;
            
            for(int i=1;i<list1.size()-1;i+=3)
            {
                ac++;
                String g1[]=list1.get(i).toString().split("#");
                String g2[]=list1.get(i+1).toString().split("#");
                String g3[]=list1.get(i+2).toString().split("#");
                
                
                
                
                
                String md[]=new String[3];
                
                md[0]=g1[1];
                md[1]=g2[1];
                md[2]=g3[1];
                
                
                String sg1[]=new String[3];
                
                sg1[0]=g1[2];
                sg1[1]=g2[2];
                sg1[2]=g3[2];
                
              String sg2[]=new String[3];
              
                sg2[0]=g1[3];
                sg2[1]=g2[3];
                sg2[2]=g3[3];
                
                for(int f1=0;f1<sg1.length;f1++)
                {
                    for(int f2=f1+1;f2<sg1.length;f2++)
                    {
                        if(Integer.parseInt(sg1[f1])>Integer.parseInt(sg1[f2]))
                        {
                            String t1=sg1[f1];
                            sg1[f1]=sg1[f2];
                            sg1[f2]=t1;
                            
                            String t2=sg2[f1];
                            sg2[f1]=sg2[f2];
                            sg2[f2]=t2;
                            
                            String t3=md[f1];
                            md[f1]=md[f2];
                            md[f2]=t3;
                        }
                    }
                }
                 //System.out.println("Resource  "+i)   ;
                String dur="";
                String rse="";
                String mode="";
                
                for(int f1=0;f1<sg1.length;f1++)
                {
                   // System.out.println(sg1[f1]+" : "+sg2[f1]);
                    if(sg2[f1].equals("0") || Integer.parseInt(sg2[f1])>rs)
                    {
                        continue;
                    }
                    else
                    {
                        dur=sg1[f1];
                        rse=sg2[f1];
                        mode=md[f1];
                        break;
                    }
                
                }  
                 
                
                if(!(dur.equals("")||rse.equals("")||mode.equals("")))
                {
                    System.out.println("dur "+dur+" :  "+rse+" : "+mode);
                      list2.add(dur+"#"+rse+"#"+mode+"#"+ac);
                }
            }
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
    }
    
    public void getTime2()
    {
        try
        {
            int rs=Integer.parseInt(resource.get(0).toString());
            
            
            
            System.out.println("list2  -- > "+list2);
            
            for(int i=0;i<list2.size();i++)
            {
                String g1[]=list2.get(i).toString().split("#");
                
                int r1=Integer.parseInt(g1[1]);
                String d1=g1[0];
                String m1=g1[2];
                String ac1=g1[3];
                
                
                //for(int j=1;j<list2.size()-1;j++)
                int flag=0;
                for(int j=i+1;j<list2.size();j++)
                {
                    String g2[]=list2.get(j).toString().split("#");
                
                    int r2=Integer.parseInt(g2[1]);
                    String d2=g2[0];
                    String m2=g2[2];
                    String ac2=g2[3];
                    
                    System.out.println(list2.get(i)+" : "+list2.get(j));
                    
                    if(rs>=(r1+r2) && Integer.parseInt(d1)>Integer.parseInt(d2))
                    {
                        flag=1;
                        
                       // lt1.add(d1+"#"+r1+"#"+m1);
                        //lt1.add(d2+"#"+r2+"#"+m2);
                        
                        lt1.add(d1+"#"+r1+"#"+m1+"#"+ac1+"@"+d2+"#"+r2+"#"+m2+"#"+ac2);
                        
                        //list2.remove(d1+"#"+r1+"#"+m1);
                        //list2.remove(d2+"#"+r2+"#"+m2);
                        
                        list2.remove(i);
                        list2.remove(j-1);
                        i=i-1;
                        System.out.println("current lt-----> "+lt1);
                        System.out.println("current list-----> "+list2);
                        
                        break;
                    }
                
                }
                
                System.out.println("flag "+flag);
                System.out.println("size "+list2.size());
                if(flag==0)
                {
                    lt1.add(d1+"#"+r1+"#"+m1+"#"+ac1); 
                    list2.remove(i);
                    i=i-1;
                }
                System.out.println("----->  "+i+" : "+(list2.size()));
            }
            
            
            sc=lt1.size();
            
            if(!(list2.size()==0))
            {
                            
                for(int i=0;i<list2.size();i++)
                  lt1.add(list2.get(i));
                               
                
            }
            
            for(int i=0;i<lt1.size();i++)
                   System.out.println("time "+lt1.get(i));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void getTime3()
    {
        try
        {
            int dur1=0;
            for(int i=0;i<lt1.size();i++)
            {
                String g1[]=lt1.get(i).toString().split("#");
                dur1=dur1+Integer.parseInt(g1[0]);
            }
            System.out.println(dur1);
            
            int rs=Integer.parseInt(resource.get(0).toString());
            
           ScheduleGraph mainPanel = new ScheduleGraph(dur1,rs,lt1);

            JFrame frame = new JFrame("ScheduleGraph");
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            frame.setResizable(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
