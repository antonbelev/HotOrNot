$(document).on("pageinit", function (event) {
	
	/*for (var i = 0; i < venues.lenght; i++) {
		selectedVenues.push(venues[i]);
	}
	drawChart();*/
	
	selectedVenues = []
	
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
    });
	
	$("input[type='checkbox']").on("change", function (event, ui) {
        if ($(this).hasClass("chckCat")) {
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
    });
	
});