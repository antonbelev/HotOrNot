package test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.charts4j.*;

/*
 * static chart generation for experimentation
 */
@Controller
public class ChartController {
    @RequestMapping("/index.html")
    public ModelAndView indexHandler() {
        //create chart
    	int a = 42, b = 24, c = 72, d = 51, e = 98;
    	Plot plot = Plots.newPlot(Data.newData(a, b, c, d, e));
        LineChart chart = GCharts.newLineChart(plot);
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("Jan", "Feb", "Mar", "Apr,", "May"));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(0, 50, 100));
        return new ModelAndView().addObject("url", chart.toURLString());      
    }
}

/*
 * dygen
 */
@Controller
public class ChartController {
	@RequestMapping("/interface")
	@Resource(name="springService")
	private Calculator springService;
	
    @RequestMapping(value = "/calc", method = RequestMethod.GET)
    public String getInterface() {    	
    	// resolves to /WEB-INF/jsp/index.jsp
    	return "index";
	}
 
    @RequestMapping(value = "/calc", method = RequestMethod.POST)
    public @ResponseBody Integer add(@RequestParam(value="radioAttribute", required=true) String radioAttribute,
    							@RequestParam(value="radioChartType", required=true) String radioChartType,
    							Model model) {		
		String Chart = springService.resolve(radioAttribute, radioChartType);		
		return Chart;
	}
}
