<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- Formatting should be redone in css -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hot or Not</title>
<meta name="viewport" content="initial-scale = 1, user-scalable = no">
	<style>
			canvas{
			}
		</style>
		
	<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
    
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <!-- 1.4.4js -->
  <script type="text/javascript" src="/HotOrNotUI/resources/js/jquery/jquery-1.4.4.min.js"></script>
  <script type="text/javascript">
     var jq = jQuery.noConflict();
 </script>
  <script>
  $(function() {
    $( "input[type=submit], a, button" )
      .button()
      .click(function( event ) {
        event.preventDefault();
      });
  });
  </script>
  <script>
  $(function() {
    $( "#radioAttribute" ).buttonset();
  });
  </script>
  <script>
  $(function() {
    $( "#radioChartType" ).buttonset();
  });
  </script>
		
</head>

<body>

<h1>
	<font size="6"><b>Hot or Not:</b></font>
	<font size="5"><i><br>Identifying Emerging Venues on Twitter</br></i></font>
		
</h1>

<p><font size = "3">"@Arches was great last night. #yolo"</font></p>

<!-- chart image, should probably hold somewhere instead -->
<img src="${url}"  alt="Chart" />



<!-- to hold attribute selection radios -->
<form>
  <div id="radioAttribute">
    <input type="radio" id="radioTweetsTotal" name="radio"><label for="radioTweetsTotal">Tweets</label>
    <input type="radio" id="radioTweetsGrowth" name="radio" checked="checked"><label for="radioTweetsGrowth">Growth</label>
    <input type="radio" id="radioFollowerAverage" name="radio"><label for="radioFollowerAverage">Followers</label>
  </div>
</form>

<!-- to hold chart-type selection radios -->
<form>
  <div id="radioChartType">
    <input type="radio" id="radioBar" name="radio"><label for="radioBar">Bar</label>
    <input type="radio" id="radioColumn" name="radio" checked="checked"><label for="radioColumn">Column</label>
    <input type="radio" id="radioPie" name="radio"><label for="radioPie">Pie</label>
    <input type="radio" id="radioDonut" name="radio"><label for="radioDonut">Line</label>
  </div>
</form>


<!-- jsfunc for cupdate -->
<script type="text/javascript"> 

function updateChart() {
	jq(function() {
		jq.post("/HotOrNotUI/test",
					{ 	
						radioAttribute: jq("#radioAttribute").val(),
						radioChartType: jq("#radioChartType").val() },
						function(url){
							<!-- fix -->
							jq("#Chart").replaceWith('<img src="'+url +'" alt=Chart');
					});
	});
}

</script>

</body>
</html>