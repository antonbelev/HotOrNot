function initialize() {
	var myLatlng = new google.maps.LatLng(venueLatitude, venueLongitude);
	var mapOptions = {
	  zoom: 12,
	  center: myLatlng,
	}
	var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

	// To add the marker to the map, use the 'map' property
	var map = new google.maps.Map(document.getElementById("map-canvas"),
		mapOptions);
	var marker = new google.maps.Marker({
		position: myLatlng,
		map: map,
		title:"Hello World!"
	});        
	marker.setMap(map);		
}
google.maps.event.addDomListener(window, 'load', initialize);