/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class ScheduleGraph extends JPanel
{
    int xa;
    int ya;
    
    private static final int BORDER_GAP = 30;
    private static final int PREF_W = 820;
    private static final int PREF_H = 650;
    
    ArrayList list1=new ArrayList();
    //Color clr[]={Color.red,Color.BLUE,Color.CYAN,Color.orange,Color.WHITE,Color.GREEN,Color.BLACK,Color.PINK,Color.YELLOW,Color.magenta,Color.DARK_GRAY};
    
    
    ScheduleGraph(int x,int y,ArrayList at1)            
    {
        xa=x;
        ya=y;
        list1=at1;
    }
    
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    
        // create x and y axes 
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
	
        g2.drawString("t",getWidth()-20,getHeight()-20);
        g2.drawString("R",30,30);
	  
	List<Point> xPoints = new ArrayList<Point>();
	List<Point> yPoints = new ArrayList<Point>();
	  
	  
        // create hatch marks for y axis. 
        for (int i = 0; i < ya; i++) 
        {
             int x0 = BORDER_GAP;
             int x1 = 12 + BORDER_GAP;
             int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / ya + BORDER_GAP);
             int y1 = y0;
             g2.drawLine(x0-10, y0, x1-12, y1);
             g2.drawString(String.valueOf(i+1), x1-30, y1+3);
		 
             yPoints.add(new Point(x1-12, y1));
		 
		
        }
        
      // and for x axis
      
        for (int i = 0; i < xa; i++) 
	{
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (xa + 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - 12;
            g2.drawLine(x0, y0+10, x1, y1+12);
            g2.drawString(String.valueOf(i+1), x1-2, y1+40);
		 
            xPoints.add(new Point(x1, y1+12));
		 
        }
        
        
         int xx1 = yPoints.get(2).x;
         int yy1 = yPoints.get(2).y;
		 
	 int xx2 = yPoints.get(1).x;
         int yy2 = yPoints.get(1).y;
        
         
         int k1=(xx2-xx1)*(xx2-xx1);
         int k2=(yy2-yy1)*(yy2-yy1);
         
         int ygap=(int)Math.sqrt((k1+k2));
         
         
         
         
         int xx3 = xPoints.get(2).x;
         int yy3 = xPoints.get(2).y;
		 
	 int xx4 = xPoints.get(1).x;
         int yy4 = xPoints.get(1).y;
        
         
         int k3=(xx4-xx3)*(xx4-xx3);
         int k4=(yy4-yy3)*(yy4-yy3);
         
         int xgap=(int)Math.sqrt((k3+k4));
         
                 System.out.println("gap "+ygap+" : "+xgap);
         
         
        // g2.drawString("j",xx1,yy1);
         //g2.drawString("o",xx2,yy2);
        //System.out.println("gap "+ygap1 +" : "+ygap2);
        String f1[]=list1.get(0).toString().split("#");
        
        //int pre=Integer.parseInt(f1[0]);
        int pre=0;
        //at.add("3#6#1#1@2#2#1#2");
        for(int i=0;i<list1.size();i++)
       // for(int i=0;i<3;i++)
        {
            String g1=list1.get(i).toString();
            if(g1.contains("@"))
            {
                String sg1[]=g1.split("@");
                String sg2[]=sg1[0].split("#");
                String sg3[]=sg1[1].split("#");
                
                int xxa1=Integer.parseInt(sg2[0]);
                int yya1=Integer.parseInt(sg2[1]);
                
                
                int act1=Integer.parseInt(sg2[3]);
                
                int x2 = yPoints.get(yya1-1).x;
                int y2 = yPoints.get(yya1-1).y;
                
                //g2.setColor(clr[i]);
                g2.setColor(Color.BLACK);
                
                
                
                int xxa2=Integer.parseInt(sg3[0]);
                int yya2=Integer.parseInt(sg3[1]);
                
                int x3 = yPoints.get(yya2-1).x;
                int y3 = yPoints.get(yya2-1).y;
                
                int act2=Integer.parseInt(sg3[3]);
                
               // g2.setColor(Color.GREEN);
                
                
                
                
                if(pre==0)
                {
                    g2.drawRect(x2,y2,(xgap*xxa1),(ygap*(yya1)));
                    g2.drawRect(x3,y2-(ygap*yya2),(xgap*xxa2),(ygap*(yya2)));
                    
                    //pre=xgap*xxa2;
                    pre=xgap*xxa1;
                    
                    //g2.drawString(String.valueOf(act1),x2+15,y2);
                    //g2.drawString(String.valueOf(act2),x3+15,y2);
                    
                    g2.drawString(String.valueOf(act1),x2+10,y2+ygap);
                    g2.drawString(String.valueOf(act2),x3+10,y2-(ygap*yya2)+ygap);
                }
                else
                {
                  // pre=(pre+(xgap*xxa2))-xgap;
                   
                   g2.drawRect(x2+pre,y2,(xgap*xxa1),(ygap*(yya1)));
                    g2.drawRect(x3+pre,y2-(ygap*yya2),(xgap*xxa2),(ygap*(yya2)));
                    
                    
                    g2.drawString(String.valueOf(act1),x2+pre+10,y2+ygap);
                    g2.drawString(String.valueOf(act2),x3+pre+10,(y2-(ygap*yya2))+ygap);
                    
                    pre=pre+(xgap*xxa1);
                    
                   // g2.drawString(String.valueOf(act1),x2+pre+15,y2);
                    // g2.drawString(String.valueOf(act2),x3+pre+15,y2);
                    
                    
                }
                
                //g2.drawString("h",x2,y2);
                
              //  break;
                
                
                
            }
            else
            {
                //at.add("3#6#1#1");
                String sg1[]=g1.split("#");
                int xx=Integer.parseInt(sg1[0]);
                int yy=Integer.parseInt(sg1[1]);
                
                int act1=Integer.parseInt(sg1[3]);
                
                int x2 = yPoints.get(yy-1).x;
                int y2 = yPoints.get(yy-1).y;
                
                System.out.println("xx "+xx+ " : "+yy);
                System.out.println("x2 "+x2+ " : "+y2);
                
                
                // g2.setColor(clr[i]);
                g2.setColor(Color.BLACK);
                
                 System.out.println("pre "+pre+" : "+xx);
                 
                 if(pre==0)
                 {
                    g2.drawRect(x2,y2,(xgap*xx),(ygap*(yy)));
                    g2.drawString(String.valueOf(act1),x2+10,y2+ygap);
                    
                    pre=xgap*xx;
                    
                    //g2.drawString(String.valueOf(act1),x2+15,y2);
                    
                    
                 }
                 else
                 {
                   //  pre=(pre+(xgap*xx))-xgap;  // now1
                     
                     g2.drawRect(x2+pre,y2,(xgap*xx),(ygap*(yy)));
                    // pre=(pre+(xgap*xx));
                    //g2.drawRect(x3+pre,y2-(ygap*yya2),(xgap*xxa2),(ygap*(yya2)));
                    //g2.drawString(String.valueOf(act1),x2+pre+15,y2);
                    g2.drawString(String.valueOf(act1),(x2+pre)+10,y2+ygap);
                    
                    pre=pre+(xgap*xx);
                    
                   //  g2.drawRect(x2+(xgap*pre),y2,(xgap*xx),(ygap*(yy)));
                    // pre=xx;
                 }
                 
                // g2.drawString("k",x2,y2);
                // break;
            
            }
        }
        
          /*  int x1 = xPoints.get(1).x;
            int y1 = xPoints.get(1).y;
		 
            int x2 = yPoints.get(1).x;
            int  y2 = yPoints.get(1).y;
		 
		 
		 
				
		g2.setColor(Color.red);
		 //g2.drawRect(x3,y3,100,58);
		// g2.drawLine(x1,y1,x1,y2);
		//g2.setColor(Color.blue);
		//	 g2.drawLine(x2,y2,x1,y2);
			
		g2.setColor(Color.blue);
		 g2.drawRect(x2,y2,120,40);
				*/
	
                 
		
   }

   
   public Dimension getPreferredSize() 
   {
      return new Dimension(PREF_W, PREF_H);
   }
    
}
