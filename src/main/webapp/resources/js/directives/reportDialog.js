app.directive("reportDialog",function(){
	return{ 
		restrict: "E",
		templateUrl: "resources/dialogs/report.html",
		scope:{
			report: "=",
			project: "=",
			updateFunc: "&"
		},
		controller: function($scope,$http){			

		},
		link: function($scope, element, $watch, $http){
			$scope.$watch("report", function(){
				jQuery("#reportDialog").dialog({
					autoOpen: false,
					title: "Report",
					modal: true,
					buttons:[
						{
							text: ($scope.report && $scope.report.id) ? "Edit " + $scope.report.id : "Create Report",
							icons: {primary: "ui-icon-plusthick"},
							click: function() {
								$scope.report.project_fk = $scope.project.id;
								
								var submitText = angular.toJson($scope.report);
								var callback = function(){
									jQuery("#reportDialog").dialog("close");									
									$scope.updateFunc();
								}
								var url = "rest/report";
								
								if ($scope.report.id){
									jQuery.ajax ({
									    url: url + "/" + $scope.report.id,
									    type: "PUT",
									    data: submitText,
									    dataType: "json",
									    contentType: "application/json; charset=utf-8",
									    success: callback
									    }
									);
								}else{
									jQuery.ajax ({
									    url: url,
									    type: "POST",
									    data: submitText,
									    dataType: "json",
									    contentType: "application/json; charset=utf-8",
									    success: callback
									    }
									);
								}
							}
						}
					]
				}); //end dialog
			}); //end watch	
		}//end link
	};
});