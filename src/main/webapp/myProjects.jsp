<jsp:include page="header.jsp"/>
<script type="text/javascript">
	
	app.controller('myProjectsController', function($scope, $http,$timeout) {
	
		$scope.getProjects=function(){		
			$http.get('rest/project/myProjects').
			success(function(data, status, headers, config) {		
				$scope.myProjects = data;
			});
		}
		$scope.getProjects();
	});
</script>
<style>
</style>
	<div ng-controller="myProjectsController" id="wrapper">
		<table border="1">
			<thead><tr><td>Name</td><td>Description</td></tr></thead>
			<tbody>
				<tr ng-repeat="project in myProjects">
					<td>{{project.name}}</td>
					<td>{{project.description}}</td>
					<td class="icons">
						<a ng-href="addShift.jsp?projectId={{project.id}}" tooltip = "Log a shift on this project" class="ui-icon-large ui-icon-shift"></a>
						
					</td>
				</tr>
			</tbody>
		</table>
	</div>	
<jsp:include page="footer.jsp"/>

