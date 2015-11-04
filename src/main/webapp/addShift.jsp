<jsp:include page="header.jsp"/>

<script type="text/javascript">
	
	app.controller('addShiftController', function($scope, $http,$timeout) {
	

	});
</script>
<style>
</style>
	<div ng-controller="addShiftController" id="wrapper">
	  <label>Start</label><date-time model="start"></date-time>
	  <br/>
	  <label>End</label><date-time model="end"></date-time>
	</div>	
<jsp:include page="footer.jsp"/>

