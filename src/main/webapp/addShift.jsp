<jsp:include page="header.jsp"/>

<script type="text/javascript">
	
	app.controller('addShiftController', function($scope, $http,$timeout) {
		$scope.shift={
				
		};
		
		$scope.addShift = function(){
			var submitText = angular.toJson($scope.shift);
			$http({
			    url: "rest/shift",
			        method: "POST",		        
			        data: submitText,
			    }).success(function(data, status, headers, config) {							        
			        alert("Saved");
			        
			    });
		}
	});
</script>
<style>
</style>
	<div ng-controller="addShiftController" id="wrapper">
	  <label>Start</label><date-time model="start"></date-time>
	  <br/>
	  <label>End</label><date-time model="end"></date-time>
	  <button value="Add" ng-click="addShift()"/>
	</div>	
<jsp:include page="footer.jsp"/>

