$(document).on("pageinit", function (event) {
	
	 $("input[type='checkbox']").on("change", function (event, ui) {
        if ($(this).hasClass("chckVenue")) {
            if ($(this).attr('checked')) {
                selectedVenues.push($(this).attr("name"));
            } else {
                //$(".selectAllColumns").attr('checked', false).checkboxradio().checkboxradio('refresh');
                var newlist = [];
                for (var i = 0; i < selectedVenues.length; i++) {
                    if ($.trim(selectedVenues[i].toString()) != $.trim($(this).attr("name").toString())) {
                        newlist.push(selectedVenues[i]);
                    }
                }
                selectedVenues = newlist;
            }
            drawChart();
        }
		
		
    });
	

});