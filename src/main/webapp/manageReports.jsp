<jsp:include page="header.jsp"/>
<script type="text/javascript" src="/subcontractors/resources/js/directives/reportDialog.js"></script>
<script type="text/javascript" src="/subcontractors/resources/js/directives/confirmDeleteReportDialog.js"></script>
<script type="text/javascript">
	app.controller('manageReportsController', function($scope, $http,$timeout) {
		var projectId = parseLocation().projectId;
		$scope.getReports=function(){
			$http.get("rest/report/myReports/" + projectId).
			success(function(data, status, headers, config) {
				$scope.myReports = data;
			});
		}
		$scope.getProject = function(){
			$http.get("rest/project/" + projectId).
			success(function(data,status,headers,config){
				$scope.project = data;
			});
		}
		$scope.openEditReportDialog = function(report){
			$scope.selectedReport = report;
			jQuery("#reportDialog").dialog("open");
		}
		$scope.openCreateReportDialog = function(){
			$scope.selectedReport = {};
			jQuery("#reportDialog").dialog("open");
		}
		$scope.confirmDeleteReport =function(report){
			$scope.selectedReport = report;
			$timeout(function(){
				jQuery("#confirmDeleteReportDialog").dialog("open");
			});
		}
		$scope.getReports();
		$scope.getProject();
	});
</script>
<style>
</style>
	<div ng-controller="manageReportsController" id="wrapper">
		<h1>Reports</h1>
		<h2>Project: {{project.name}}</h2>
		<table border="1">
			<thead><tr>
				<td>ID</td><td>Description</td><td>Actions</td>
				<td><span class="ui-icon ui-icon-create" title="Create Report" ng-click="openCreateReportDialog()"></span></td>
			</tr></thead>
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
		<report-dialog project="project" report = "selectedReport" update-func="getReports()"></report-dialog>
		<confirm-delete-report report="selectedReport" update-func="getReports()"></confirm-delete-report>		
	</div>	
<jsp:include page="footer.jsp"/>

