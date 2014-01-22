package services;
import org.springframework.stereotype.Service;

import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;

@Service("springService")
public class Calculator {
	
	public String calc(String radioAttribute, String radioChartType) {
		
		String url = "";
		
		/*
		 * Insert code determining attribute and making relevant request to database
		 */
		
		
		/*
		 * Static chart from controller until linked to DB
		 */
		if (radioChartType == "radioBar")
		{int a = 42, b = 24, c = 72, d = 51, e = 98;
    	Plot plot = Plots.newPlot(Data.newData(a, b, c, d, e));
        BarChart chart = GCharts.newBarChart(plot);
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("Jan", "Feb", "Mar", "Apr,", "May"));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(0, 50, 100));
        url = chart.toURLString(); }
		else if (radioChartType == "radioColumn")
		{int a = 42, b = 24, c = 72, d = 51, e = 98;
    	Plot plot = Plots.newPlot(Data.newData(a, b, c, d, e));
        BarChart chart = GCharts.newBarChart(plot);
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("Jan", "Feb", "Mar", "Apr,", "May"));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(0, 50, 100));
        url = chart.toURLString(); }
		else if (radioChartType == "radioPie")
		{int a = 42, b = 24, c = 72, d = 51, e = 98;
    	Plot plot = Plots.newPlot(Data.newData(a, b, c, d, e));
        BarChart chart = GCharts.newBarChart(plot);
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("Jan", "Feb", "Mar", "Apr,", "May"));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(0, 50, 100));
        url = chart.toURLString(); }
		else
		{int a = 42, b = 24, c = 72, d = 51, e = 98;
    	Plot plot = Plots.newPlot(Data.newData(a, b, c, d, e));
        BarChart chart = GCharts.newBarChart(plot);
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("Jan", "Feb", "Mar", "Apr,", "May"));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(0, 50, 100));
        url = chart.toURLString(); }
		
		
		return url;
	}
	
}