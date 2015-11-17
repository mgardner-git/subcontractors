<jsp:include page="header.jsp"/>
<script type="text/javascript" src="/subcontractors/resources/js/directives/reportDialog.js"></script>
<script type="text/javascript">
	console.log("A");
	app.controller('manageReportsController', function($scope, $http,$timeout) {
		var projectId = parseLocation().projectId;
		$scope.getProjects=function(){
			$http.get("rest/report/myReports/" + projectId).
			success(function(data, status, headers, config) {
				$scope.myReports = data;
			});
		}
		$scope.openEditReportDialog = function(report){
			$scope.selectedReport = report;
			jQuery("#reportDialog").dialog("open");
		}
		$scope.getProjects();
	});
</script>
<style>
</style>
	<div ng-controller="manageReportsController" id="wrapper">
		<h1>Reports</h1>
		<table border="1">
			<thead><tr><td>ID</td><td>Description</td><td>Actions</td></tr></thead>
			<tbody>
				<tr ng-repeat="report in myReports">
					<td>{{report.id}}</td>
					<td>{{report.description}}</td>
					<td class="icons">
						<a ng-click="confirmDeleteReport(report)" class="ui-icon-large ui-icon-delete"/>
						<a ng-click="openEditReportDialog(report)" class="ui-icon-large ui-icon-edit"/>
					</td>
				</tr>
			</tbody>
		</table>
		<report-dialog report = "selectedReport"></ingredient-dialog>		
	</div>	
<jsp:include page="footer.jsp"/>

