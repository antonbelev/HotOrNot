$(document).on("pageinit", function (event) {
	
	chartChoice = chartEnum.LINE;
	statisticsTypeChoice = statisticsType.TOTAL_HITS;
		
	$("input[type='radio']").bind("change", function (event, ui) {
		console.log("value - " + $(this).prop('value'));
        switch ($(this).prop('value')) {
            case ('chart-choice-Line'):
                chartChoice = chartEnum.LINE;
                break;
            case ('chart-choice-Column'):
                chartChoice = chartEnum.COLUMN;
                break;
			case ('statistics-choice-Total-hits'):
                statisticsTypeChoice = statisticsType.TOTAL_HITS;
                break;
            case ('statistics-choice-Time-frame'):
                statisticsTypeChoice = statisticsType.TIME_FRAME;
                break;
        }
        drawChart();
    });
});