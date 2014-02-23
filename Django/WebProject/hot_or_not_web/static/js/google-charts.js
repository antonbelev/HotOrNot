		// Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      chartEnum = {
		LINE : 0,
		COLUMN : 1,
		SCATTER : 2,
		GEO : 3
	  };
	  
	  statisticsType = {
		TOTAL_HITS : 0,
		TIME_FRAME : 1,
	  };
	  var daysOfTheWeek = ["mon", "tue", "wed", "thu", "fri", "sat", "sun"];
	  var chartChoice;	 
	  var statisticsTypeChoice = statisticsType.TOTAL_HITS;
	  google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);      
	  
	  function drawChart() {		
		// Create the data table.
		var data = new google.visualization.DataTable();
				
		switch(statisticsTypeChoice) {
			case(statisticsType.TIME_FRAME): 
				data.addColumn('string', 'Day of the week');		
				for (var venue_type in jsonData){
					data.addColumn('number', venue_type.split(',')[0]); // add venue names as columns
				}				
				for (var day in daysOfTheWeek){		
					var content = [];
					content.push(daysOfTheWeek[day]);
					
					for (var venue_type in jsonData) { 
						content.push(jsonData[venue_type][daysOfTheWeek[day]]);						
					}
					data.addRow(content);				
				}
				chartChoice = chartEnum.LINE;	 
				break;
				
			case(statisticsType.TOTAL_HITS):
				data.addColumn('string', 'Venue');
				data.addColumn('number', 'Total hits');
				data.addColumn('number', 'Celebrity hits');
				
				for (var venue_type in jsonData) {
					var content = [];
					content.push(venue_type.split(',')[0]);
					content.push(jsonData[venue_type]['total_hits']);
					content.push(jsonData[venue_type]['celebrity_hits']);			
					data.addRow(content);			
				}
				
				chartChoice = chartEnum.COLUMN;	 
				break;
		}
		
		
				

		var options = {
			'title:' : 'Venue ranking over time',
            'height': 500,//$( document ).innerHeight() - 50,
            'width': '80%',
            animation: {duration: 1000, easing: 'inAndOut'},
        };

        // Instantiate and draw our chart, passing in some options.
        var chart;
		switch(chartChoice) {
			case(chartEnum.LINE): 
				chart = new google.visualization.LineChart(document.getElementById('chart_div'));
				break;
			case(chartEnum.COLUMN): 
				chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
				break;
			case(chartEnum.GEO):
				chart = new google.visualization.GeoMap(document.getElementById('chart_div'));
				break;
		}
		chart.draw(data, options);
      }
      
      