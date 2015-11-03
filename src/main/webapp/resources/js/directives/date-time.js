app.directive("dateTime",function(){
	return{ 
		restrict: "E",
		template:'<input type="text" ng-model="model" class="{{cssClass}}" tabIndex="{{tabIndex}}" placeholder="{{placeholder}}" ng-blur="blur()"></input>',
		scope:{
			model: "=",
			cssClass: "@",
			tabIndex: "@",
			blur: "&",
			fieldName: "@",
			placeholder: "@"
		},
		link: function($scope, element, attrs){
			var inputNode = jQuery(element).find("input");
			inputNode.datetimepicker({
				timeFormat: "hh:mm tt",
				showSecond: false,
				showMicrosecond: false,
				showTimezone: false
			});
		} ,
		controller: function($scope){

		}
	};
});