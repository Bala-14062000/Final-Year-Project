/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Preprocess 
{
    
    String fname;
    
    Preprocess(String s1)
    {
    
        fname=s1;
    }
    
    public ArrayList getPrecedenceRelation()
    {
        ArrayList pre=new ArrayList();
        try
        {
            File fe=new File(fname);
            FileInputStream fis=new FileInputStream(fe);
            byte data[]=new byte[fis.available()];
            fis.read(data);
            fis.close();
            
            String str=new String(data);
            
            String temp=str;
            
            temp=temp.substring(temp.indexOf("PRECEDENCE RELATIONS:"),temp.length());
            temp=temp.substring(0, temp.indexOf("*"));
            //System.out.println("temp "+temp);
            
            String sg1[]=temp.split("\n");
            
            for(int i=2;i<sg1.length;i++)
            {
                
                String sf="";
                     //   System.out.println(sg1[i]);
                String sg2[]=sg1[i].trim().split("        ");
                //System.out.println(sg2[0]+" : "+sg2[1]);
                int suc=Integer.parseInt(sg2[2].trim());
               // System.out.println(sg2[0].trim()+"#"+sg2[1].trim()+"#"+sg2[2].trim());
                
                sf=sg2[0].trim()+"#"+sg2[1].trim()+"#"+sg2[2].trim();
                
                if(suc==0)
                    sf=sf+"@"+0;
                else if(suc==1)
                {
                    sf=sf+"@"+sg2[3].trim();
                }
                else
                {
                    String sg3[]=sg2[3].trim().split("  ");
                    for(int j=0;j<sg3.length;j++)
                    {
                        
                        sf=sf+"@"+sg3[j].trim();
                    }
                }
                System.out.println(sf);
                
                pre.add(sf);
                
            }
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(pre);
        return pre;
    }
    
    public ArrayList getRequest()
    {
        ArrayList dur=new ArrayList();
        try
        {
            File fe=new File(fname);
            FileInputStream fis=new FileInputStream(fe);
            byte data[]=new byte[fis.available()];
            fis.read(data);
            fis.close();
            
            String str=new String(data);
            String temp=str;
            
            temp=temp.substring(temp.indexOf("REQUESTS/DURATIONS:"),temp.length());
            temp=temp.substring(0, temp.indexOf("*"));
            
            String sg1[]=temp.split("\n");
            int k=0;
            int h=7;
            System.out.println("Duration ");
            ArrayList at=new ArrayList();
            for(int i=3;i<sg1.length;i++)
            {
                String st1=sg1[i].trim().replaceAll(" ","#");
              //  System.out.println(st1);
              /*  char chr[]=st1.toCharArray();
                String st2=String.valueOf(chr[0]);
                for(int j=1;j<chr.length-1;j++)
                {
                    if(chr[j]=='#')
                    {
                        continue;
                    }
                    else
                    {
                        st2=st2+"#"+chr[j];
                    }
                }*/
                String st2=st1.replace("#######", "#");
                       st2=st2.replace("######", "#");
                       st2=st2.replace("#####", "#");
                       st2=st2.replace("####", "#");
                       st2=st2.replace("###", "#");
                 System.out.println("-----------> "+st2);
                at.add(st2);
            }
            
            
            
            for(int i=0;i<at.size();i++)
            {
                //String sg2[]=sg1[i].trim().split("    ");
                String sg2[]=at.get(i).toString().trim().split("#");
                String sf="";
                if(sg2.length==h)
                {
                    k++;
                    sf=String.valueOf(k);
                    for(int j=1;j<sg2.length;j++)
                    {
                        sf=sf+"#"+sg2[j].trim();
                    }
                    dur.add(sf);
                }
                else
                {
                    sf=String.valueOf(k);
                    for(int j=0;j<sg2.length;j++)
                    {
                        sf=sf+"#"+sg2[j].trim();
                    }
                    dur.add(sf);
                }
                System.out.println("sf "+sf);
            }
            
            
            for(int i=0;i<dur.size();i++)
                System.out.println("dur "+dur.get(i));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return dur;
    }
    
    public ArrayList getResourceAvail()
    {
        ArrayList resrc=new ArrayList();
        try
        {
            File fe=new File(fname);
            FileInputStream fis=new FileInputStream(fe);
            byte data[]=new byte[fis.available()];
            fis.read(data);
            fis.close();
            
            String str=new String(data);
            String temp=str;
            
            temp=temp.substring(temp.indexOf("RESOURCEAVAILABILITIES:"),temp.length());
            temp=temp.substring(0, temp.indexOf("*"));
            
            String sg1[]=temp.split("\n");
            String sg2[]=sg1[2].trim().split("   ");
            System.out.println("available resource ");
            for(int i=0;i<sg2.length;i++)
            {
                System.out.println(sg2[i].trim());
                resrc.add(sg2[i].trim());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return resrc;
    }
    
    public ArrayList removeMode1(ArrayList at)
    {
        ArrayList res=new ArrayList();
        try
        {
           for(int i=0;i<at.size()-1;i++) 
           {
              String s1[]=at.get(i).toString().split("#");
              String s2[]=at.get(i+1).toString().split("#");
            //  System.out.println(s1[0]+" : "+s2[0]);
           //   System.out.println("Mode   "+s1[1]+" : "+s2[1]);
              
              if(s1[0].equals(s2[0]))
              {
                  int f1=0;
                  int f2=0;
                  int f3=0;
                  int f4=0;
                  int f5=0;
                  
                  if(Integer.parseInt(s1[2])<=Integer.parseInt(s2[2]))
                  {
                      f1=1;
                  }
                  
                  if(Integer.parseInt(s1[3])>=Integer.parseInt(s2[3]))
                  {
                      f2=1;
                  }
                  
                  if(Integer.parseInt(s1[4])>=Integer.parseInt(s2[4]))
                  {
                      f3=1;
                  }
                  
                  if(Integer.parseInt(s1[5])>=Integer.parseInt(s2[5]))
                  {
                      f4=1;
                  }
                  
                  if(Integer.parseInt(s1[6])>=Integer.parseInt(s2[6]))
                  {
                      f5=1;
                  }
                  
                //  System.out.println(f1+ " : "+f2+" : "+f3+" : "+f4+" : "+f5);
                  if(f1==1 && f2==1 && f3==1 && f4==1 && f5==1)
                  {
                      //System.out.println("Remove "+(at.get(i+1)));
                      //at.remove(at.get(i+1));
                      
                      if(!(res.contains(at.get(i))))
                        res.add(at.get(i));
                      if(!(res.contains(at.get(i+1))))
                         res.add(at.get(i+1));
                      
                  }
                  else
                  {
                     /* if(!(res.contains(at.get(i))))
                        res.add(at.get(i));
                      if(!(res.contains(at.get(i+1))))
                         res.add(at.get(i+1));*/
                      if(!(res.contains(at.get(i))))
                        res.add(at.get(i));
                     // System.out.println("Remove "+(at.get(i+1)));
                      at.remove(at.get(i+1));
                  }
              }
              else
              {
                  if(!(res.contains(at.get(i))))
                         res.add(at.get(i));
                  if(!(res.contains(at.get(i+1))))
                         res.add(at.get(i+1));
              }
           }
           
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }
    
    public ArrayList removeMode2(ArrayList at)
    {
        ArrayList res=new ArrayList();
        try
        {
           for(int i=0;i<at.size()-1;i++) 
           {
              String s1[]=at.get(i).toString().split("#");
              String s2[]=at.get(i+1).toString().split("#");
              System.out.println(s1[0]+" : "+s2[0]);
              System.out.println("Mode   "+s1[1]+" : "+s2[1]);
              
              if(s1[0].equals(s2[0]))
              {
                  int f1=0;
                  int f2=0;
                  int f3=0;
                  int f4=0;
                  int f5=0;
                  
                  if(Integer.parseInt(s1[2])<=Integer.parseInt(s2[2]))
                  {
                      f1=1;
                  }
                  
                  if(Integer.parseInt(s1[3])>Integer.parseInt(s2[3]))
                  {
                      f2=1;
                  }
                  
                  if(Integer.parseInt(s1[4])>Integer.parseInt(s2[4]))
                  {
                      f3=1;
                  }
                  
                  if(Integer.parseInt(s1[5])>Integer.parseInt(s2[5]))
                  {
                      f4=1;
                  }
                  
                  if(Integer.parseInt(s1[6])>Integer.parseInt(s2[6]))
                  {
                      f5=1;
                  }
                  
                  System.out.println(f1+ " : "+f2+" : "+f3+" : "+f4+" : "+f5);
                  if(f1==1 && f2==1 && f3==1 && f4==1 && f5==1)
                  {
                      //System.out.println("Remove "+(at.get(i+1)));
                      //at.remove(at.get(i+1));
                      
                      if(!(res.contains(at.get(i))))
                        res.add(at.get(i));
                      if(!(res.contains(at.get(i+1))))
                         res.add(at.get(i+1));
                      
                  }
                  else
                  {
                     /* if(!(res.contains(at.get(i))))
                        res.add(at.get(i));
                      if(!(res.contains(at.get(i+1))))
                         res.add(at.get(i+1));*/
                      if(!(res.contains(at.get(i))))
                        res.add(at.get(i));
                      System.out.println("Remove "+(at.get(i+1)));
                      at.remove(at.get(i+1));
                  }
              }
              else
              {
                  if(!(res.contains(at.get(i))))
                         res.add(at.get(i));
                  
                  if(!(res.contains(at.get(i+1))))
                         res.add(at.get(i+1));
              }
           }
           
           if(!(res.contains(at.get(at.size()-1))))
               res.add(at.get(at.size()-1));
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }
    
}
