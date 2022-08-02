/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class NodeNetwork 
{
    ArrayList node1=new ArrayList();
    ArrayList node2=new ArrayList();
    int fnn;
    
    NodeNetwork(ArrayList at1,ArrayList at2,int t)
    {
        node1=at1;
        node2=at2;
        fnn=t;
    }
    
    public void createNetwork()
    {
        String gr="";
        String conn="";
        try
        {
            gr="digraph g {\n"+"  graph [fontsize=30 labelloc=\"t\" label=\"\" splines=true overlap=false rankdir = \"LR\"];";
            gr=gr+"\nratio = auto\n";
            
            ArrayList lt1=new ArrayList();
            ArrayList lt2=new ArrayList();
            
            for(int i=0;i<node1.size();i++)
            {
                String s1[]=node1.get(i).toString().split("#");
                lt1.add(s1[0]);
                String s2[]=s1[2].split("@");
                int cc=Integer.parseInt(s2[0]);
                
                for(int j=1;j<=cc;j++)
                {
                    conn=conn+"\n"+s1[0]+" -> "+s2[j]+" [ penwidth = 1 fontsize = 12 fontcolor = \"grey28\" ];";
                }
                
                
            }
            System.out.println("lt "+lt1);
            System.out.println("come "+conn);
            
            for(int i=0;i<lt1.size();i++)
            {
                String s1=lt1.get(i).toString();
                String sg1="";
                for(int j=0;j<node2.size();j++)
                {
                    String s2=node2.get(j).toString();
                    if(s2.startsWith(s1+"#"))
                    {
                        sg1=sg1+s2+"@";
                    }
                }
              //  System.out.println("sg1 "+sg1);
                String sg2[]=sg1.split("@");
                if(sg2.length>1)
                    lt2.add(sg1);
                else
                    lt2.add(sg2[0]);
            }
            
            for(int i=0;i<lt2.size();i++)
            {    
             //   System.out.println(lt2.get(i));
                String sf1=lt2.get(i).toString();
                if(sf1.contains("@"))
                {
                    String sf2[]=lt2.get(i).toString().split("@");
                    String st[]=sf2[0].split("#");
                    
                    gr=gr+"\""+st[0]+"\" [ style = \"filled\" penwidth = 1 fillcolor = \"white\" fontname = \"Courier New\"";
                    gr=gr+" shape = \"Mrecord\" label =<<table border=\"0\" cellborder=\"0\"";
                    gr=gr+" cellpadding=\"3\" bgcolor=\"white\"><tr><td bgcolor=\"black\"";
                    gr=gr+" align=\"center\" colspan=\"2\"><font color=\"white\">";
                    gr=gr+" Job"+st[0]+"</font></td></tr>";
                    
                    for(int j=0;j<sf2.length;j++)
                    {
                        String t1[]=sf2[j].split("#");
                        gr=gr+"<tr><td align=\"left\"> "+t1[2]+"("+t1[3]+","+t1[4]+","+t1[5]+","+t1[6]+")";
                        gr=gr+"</td> </tr> ";
                    }
                    gr=gr+"</table>> ];\n";
                    
                }
                else
                {
                    String t1[]=sf1.split("#");
                    gr=gr+"\""+t1[0]+"\" [ style = \"filled\" penwidth = 1 fillcolor = \"white\" fontname = \"Courier New\"";
                    gr=gr+" shape = \"Mrecord\" label =<<table border=\"0\" cellborder=\"0\"";
                    gr=gr+" cellpadding=\"3\" bgcolor=\"white\"><tr><td bgcolor=\"black\"";
                    gr=gr+" align=\"center\" colspan=\"2\"><font color=\"white\" size=\"20\">";
                    gr=gr+" Job"+t1[0]+"</font></td></tr>";
                    
                    gr=gr+"<tr><td align=\"left\"> "+t1[2]+"("+t1[3]+","+t1[4]+","+t1[5]+","+t1[6]+")";
                    gr=gr+"</td> </tr> </table>> ];\n";
                }
                
                
            }
            
            gr=gr+conn+"\n}";
            
            File fe=new File(fnn+".dot");
            FileOutputStream fos=new FileOutputStream(fe);
            fos.write(gr.getBytes());
            fos.close();
            
            GraphViz gv = new GraphViz();
            gv.readSource(fnn+".dot");
            File out = new File(fnn+".gif");
            System.out.println("create");
            gv.writeGraphToFile(gv.getGraph(gv.getDotSource()), out);
            System.out.println("write");
            
            NetworkDiagram nd=new NetworkDiagram();
            nd.setTitle("Job-VM Diagram");
            nd.setVisible(true);
            
            nd.jLabel2.setIcon(new ImageIcon(fnn+".gif"));
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
