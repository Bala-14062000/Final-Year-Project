/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import java.awt.Color;

/**
 *
 * @author admin
 */
public class Graph1
{
    Graph1()
    {
        try
        {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(5, "Existing", "Small");
            dataset.setValue(3, "Proposed", "Small");
            
            dataset.setValue(12, "Existing", "Medium");
            dataset.setValue(9, "Proposed", "Medium");
            
            dataset.setValue(17, "Existing", "Large");
            dataset.setValue(11, "Proposed", "Large");
            
            JFreeChart chart = ChartFactory.createBarChart
            ("Performance","Job Type", "Cost", dataset, 
  
            PlotOrientation.VERTICAL, true,true, false);  
            chart.getTitle().setPaint(Color.blue); 
  
            CategoryPlot p = chart.getCategoryPlot(); 
  
            p.setRangeGridlinePaint(Color.red); 
            System.out.println("Range : "+p.getRangeAxisCount() );
  
  
            CategoryItemRenderer renderer = p.getRenderer();

            renderer.setSeriesPaint(0, Color.red);
            renderer.setSeriesPaint(1, Color.blue);
	  
          //  renderer.setSeriesPaint(1, Color.green);
           // renderer.setSeriesPaint(2, Color.blue);
  
  
            ChartFrame frame1=new ChartFrame("Cost",chart);
  
            frame1.setSize(500,300);
            
            frame1.setVisible(true);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
