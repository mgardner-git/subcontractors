<jsp:include page="header.jsp"/>
<script type="text/javascript">
	
	app.controller('projectsController', function($scope, $http,$timeout) {
	
		$scope.getAssignments=function(){		
			$http.get('rest/assignment').
			success(function(data, status, headers, config) {		
				$scope.assignments = data;
			});			
		}
		$scope.getProjects=function(){		
			$http.get('rest/project').
			success(function(data, status, headers, config) {		
				$scope.projects=data;
			});			
		}
		$scope.getSubcontractors=function(){		
			$http.get('rest/assignment').
			success(function(data, status, headers, config) {		
				$scope.subcontractors = data;
			});			
		}

		
		$scope.getProjects();
	});
</script>
<style>
canvas
{
}
#container
{
	width: 100%;
	position: relative;
}

ul{
	list-style-type: none;
}

#projects li, #subcontractors li
{
	position: absolute;
	width: 150px;
	hieght: 50px;
	border: 1px solid black;
}
#footer
{
	clear: both;
}
<style>
</style>
<div ng-controller="projectsController" id="wrapper">
	<h1>My Projects</h1>
	<table border="1">
	</table>
</div>
