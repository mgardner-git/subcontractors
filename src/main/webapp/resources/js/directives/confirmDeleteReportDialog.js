app.directive("confirmDeleteReport",function(){
	return{ 
		restrict: "E",
		templateUrl: "resources/dialogs/confirmDeleteReport.html",
		scope:{
			report: "=",
			updateFunc: "&"
		},
		controller: function($scope,$http){			

		},
		link: function($scope, element, $watch, $http){
			$scope.$watch("report", function(){
				jQuery("#confirmDeleteReportDialog").dialog({
					autoOpen: false,
					title: "Delete Report?",
					modal: true,
					buttons:[
						{
							text: ("Delete Report #" + $scope.report.id),
							icons: {primary: "ui-icon-closethick"},
							click: function() {					
																
								var callback = function(){
									jQuery("#confirmDeleteReportDialog").dialog("close");									
									$scope.updateFunc();
								}
								var url = "rest/report";								
								jQuery.ajax ({
									    url: url + "/" + $scope.report.id,
									    type: "DELETE",
									    data: submitText,
									    dataType: "json",
									    contentType: "application/json; charset=utf-8",
									    success: callback
									    }
								);
							}
						},
						{
							text: ("Don't Delete the Report"),
							click: function(){
								jQuery("#confirmDeleteReportDialog").dialog("close");
							}
						}
					]
				}); //end dialog
			}); //end watch	
		}//end link
	};
});