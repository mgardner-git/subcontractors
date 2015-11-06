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
		//on change calculate the number of hours being entered if possible
		$scope.$watch("[shift.begin,shift.end]",function(){
			console.log("A");
			if ($scope.shift.begin && $scope.shift.end){
				console.log("B");
				$scope.hours = ($scope.shift.end-$scope.shift.begin)/1000/60/60;
				console.log($scope.hours);
			}
		});

	});
</script>
<style>
</style>
	<div ng-controller="addShiftController" id="wrapper">
	  <div>
	  	<label>Start:</label><date-time date-val="shift.begin"></date-time> {{shift.begin}}
	  	<br/>
	  	<label>End:</label><date-time date-val="shift.end"></date-time>{{shift.end}}
	  	<br/>
	  	<label>Hours:</label>{{hours}}
	  </div>	  
	  <label>Description:</label>
	  <input ng-model="shift.description"/>
	  <button ng-click="addShift()">Add Shift</button>	  
	</div>
	
		
<jsp:include page="footer.jsp"/>

