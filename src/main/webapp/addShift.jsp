<jsp:include page="header.jsp"/>

<script type="text/javascript">
	
	app.controller('addShiftController', function($scope, $http,$timeout) {
		var params=parseLocation();
		$scope.shift={
				begin: new Date().getTime(),
				end: new Date().getTime(),
				description: "",
				projectId: params.projectId
		}
		
		$scope.addShift = function(){
			var submitText = angular.toJson($scope.shift);	
			$http({
			    url: "rest/shift",
			        method: "POST",		        
			        data: submitText,
			    }).success(function(data, status, headers, config) {
			        alert("Saved");
			        window.location.href="myProjects.jsp";
			    });				
		}

	});
</script>
<style>
</style>
	<div ng-controller="addShiftController" id="wrapper">
	  <div>
	  	<label>Start:</label><date-time date-val="shift.begin"></date-time>
	  	<br/>
	  	<label>End:</label><date-time date-val="shift.end"></date-time>
	  </div>
	  <label>Description:</label>
	  <input ng-model="shift.description"/>
	  <button ng-click="addShift()">Add Shift</button>	  
	</div>
	
		
<jsp:include page="footer.jsp"/>

