$(document).on("pageinit", function (event) {
	
	selectedVenues = []
	chartChoice = chartEnum.LINE;
	statisticsTypeChoice = statisticsType.TOTAL_HITS;	
	
	 $("input[type='checkbox']").on("change", function (event, ui) {
        if ($(this).hasClass("chckVenue")) {
            if ($(this).prop('checked')) {
                selectedVenues.push($(this).prop("name").substring(2)); //name is vnvenuename venuetype excape vn
            } else {
                //$(".selectAllColumns").attr('checked', false).checkboxradio().checkboxradio('refresh');
                var newlist = [];
                for (var i = 0; i < selectedVenues.length; i++) {
                    if (selectedVenues[i].toString() != $(this).prop("name").toString().substring(2)) {
                        newlist.push(selectedVenues[i]);
                    }
                }
                selectedVenues = newlist;
            }
            drawChart();
        }
		else if ($(this).hasClass("chckCat")) {
            if ($(this).prop('checked')) {
				var cat = $(this).prop("name").substring(3) //name is catvenuetype excape cat				
				$("input.chckVenue[type='checkbox']").each(function () {
					if (cat == $(this).prop("name").toString().split(',')[1]) {
						//console.log("checkbox - " + $(this).prop("name").toString().split(',')[1])
                        $(this).prop('checked', true).checkboxradio().checkboxradio('refresh');
						if (selectedVenues.indexOf($(this).prop("name").substring(2)) == -1)
							selectedVenues.push($(this).prop("name").substring(2));
                    }                    
                });		
            } else {
				var cat = $(this).prop("name").substring(3) //name is catvenuetype excape cat	
				//console.log("category - " + cat);
				var newlist = [];
				$("input.chckVenue[type='checkbox']").each(function () {
					if (cat == $(this).prop("name").toString().split(',')[1]) {
                        $(this).prop('checked', false).checkboxradio().checkboxradio('refresh');
						if (selectedVenues.indexOf($(this).prop("name").substring(2)) != -1) {
							selectedVenues.splice(selectedVenues.indexOf($(this).prop("name").substring(2)), 1);
						}
					}
                });
            }
            drawChart();
        }
		else if ($(this).hasClass("selectAllVenues")) {
            if ($(this).prop('checked')) {
                $("input.chckVenue[type='checkbox']").each(function () {
                    $(this).prop('checked', true).checkboxradio().checkboxradio('refresh');
					selectedVenues.push($(this).prop("name").substring(2));
                });
                drawChart();
            } else {
                var newlist = [];
                $("input.chckVenue[type='checkbox']").each(function () {
                    $(this).prop('checked', false).checkboxradio().checkboxradio('refresh');
                });
                selectedVenues = newlist;
                drawChart();
            }
        }
        else if ($(this).hasClass("selectAllCategories")) {
            if ($(this).prop('checked')) {
                $("input.chckCat[type='checkbox']").each(function () {
                    $(this).prop('checked', true).checkboxradio().checkboxradio('refresh');
                    var cat = $(this).prop("name").substring(3) //name is catvenuetype excape cat				
					$("input.chckVenue[type='checkbox']").each(function () {
						if (cat == $(this).prop("name").toString().split(',')[1]) {
							//console.log("checkbox - " + $(this).prop("name").toString().split(',')[1])
							$(this).prop('checked', true).checkboxradio().checkboxradio('refresh');
							if (selectedVenues.indexOf($(this).prop("name").substring(2)) == -1)
								selectedVenues.push($(this).prop("name").substring(2));
						}                    
					});
                });
                drawChart();
            } else {
                var newlist = [];
                $("input.chckCat[type='checkbox']").each(function () {
                    $(this).prop('checked', false).checkboxradio().checkboxradio('refresh');
                });
                selectedVenues = newlist;
                drawChart();
            }
        }
    });
	
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