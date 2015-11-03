<jsp:include page="header.jsp"/>
<script type="text/javascript">
	
	app.controller('addShiftController', function($scope, $http,$timeout) {
	
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
	<div ng-controller="addShiftController" id="wrapper">
	
	</div>	
<jsp:include page="footer.jsp"/>

