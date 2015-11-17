app.directive("reportDialog",function(){
	return{ 
		restrict: "E",
		templateUrl: "resources/dialogs/report.html",
		scope:{
			report: "=",
		},
		controller: function($scope,$http){			

		},
		link: function($scope, element, $watch, $http){
			$scope.$watch("report", function(){
				jQuery("#reportDialog").dialog({
					autoOpen: false,
					title: "Ingredient",
					modal: true,
					buttons:[
						{
							text: ($scope.report && $scope.report.id) ? "Edit " + $scope.report.id : "Create Report",
							icons: {primary: "ui-icon-plusthick"},
							click: function() {
								var submitText = angular.toJson($scope.report);					
								jQuery.post("",submitText,function(data, status, headers, config) {
									
									jQuery("#reportDialog").dialog("close");
								});
							}
						}
					]
				}); //end dialog
			}); //end watch	
		}//end link
	};
});