		// Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      chartEnum = {
		LINE : 0,
		BAR : 1,
		SCATTER : 2,
		GEO : 3
	  };
	  var chartChoice = chartEnum.BAR;
	  var invertedAttributes = [];
	  var isNormalized = false;		
	  var selectedVenues = ["o2 Academy", "University of Glasgow", "Firhill Stadium"];
	  var countVenues = 0;
	  var years = ["2009", "2010", "2011"];
	  var venues = ["o2 Academy", "University of Glasgow", "Firhill Stadium"];
	  
	  google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      
	  
	  function drawChart() {
        /*var data = google.visualization.arrayToDataTable([
          ['Year', 'o2 Academy', 'University of Glasgow', 'Firhill Stadium'],
          ['2004',  125, 234, 400],
          ['2005',  1170, 4554, 460],
          ['2006',  4545, 3458, 1120],
          ['2007',  2343, 1345, 540]
        ]);*/
		
		// Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Year');

        for (var i = 0; i < venues.length; i++) {
			if (selectedVenues.indexOf(venues[i]) > -1)
				data.addColumn('number', venues[i]);
		}
		
		for (var i = 0; i < years.length; i++) {
                var content = [];
				content.push(years[i]);
				var year = years[i];
				
				for (var j = 0; j < venues.length; j++) {
					if (selectedVenues.indexOf(venues[j]) > -1)
						content.push(json[year][venues[j]]);
				}
				
				data.addRow(content);			
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
			case(chartEnum.BAR): 
				chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
				break;
			case(chartEnum.GEO):
				chart = new google.visualization.GeoMap(document.getElementById('chart_div'));
				break;
		}
		chart.draw(data, options);
      }
      
      