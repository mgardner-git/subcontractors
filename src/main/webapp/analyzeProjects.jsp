<jsp:include page="header.jsp"/>
<script type="text/javascript">
	
	app.controller('analysisController', function($scope, $http,$timeout) {
		$scope.analysis={};
		$scope.getAnalysis=function(){		
			$http.get('rest/project').
			success(function(data, status, headers, config) {		
				$scope.analysis=data;
				$timeout(function(){
					if (!$scope.canvas){
						$scope.initializeCanvas();
					}
					$scope.formatColumns();
					$scope.drawLines();

				});
			});			
		}
		$scope.initializeCanvas = function(){
			$scope.canvas = document.createElement("canvas");
			$scope.context = $scope.canvas.getContext("2d");		
			var container = jQuery("#container");
			container.append($scope.canvas);
			var canvasWrapper=jQuery($scope.canvas);
			canvasWrapper.css("position","absolute").css("top","0px").css("left","0px");
			container.css("position","relative");
			container.css("z-index",1);
			canvasWrapper.css("z-index",0);
		}
		
		$scope.drawLines = function(){
			var container = jQuery("#container");
			$scope.canvas.width = container.width();
			$scope.canvas.height = container.height();			
			$scope.context.clearRect(0, 0, $scope.canvas.width, $scope.canvas.height);			
			var ctx = $scope.context;
			
		    ctx.beginPath();	
		    var subcontractorContainer = jQuery("#subcontractors");
			jQuery("#projects li").each(function(index){
		    	var projectNode = jQuery(this); //the HTML node
		    	var projectEntity = $scope.analysis.projects[index]; //the data entity from the database
				var projectPosition = projectNode.offset();
				var canvasPosition = jQuery($scope.canvas).offset();
				
				var beginX = projectPosition.left - canvasPosition.left + projectNode.width();
				var beginY = projectPosition.top - canvasPosition.top + projectNode.height()/2;		
				
				for (var index=0; index < $scope.analysis.assignments.length; index++){
					var assignment = $scope.analysis.assignments[index];
					if (assignment.projectFk == projectEntity.id){
						var subcontractorNode = subcontractorContainer.find("#subcontractor-" + assignment.subcontractorFk);
						var subcontractorPosition = subcontractorNode.offset();
						
						var endX = subcontractorPosition.left - canvasPosition.left;
						var endY  = subcontractorPosition.top - canvasPosition.top + subcontractorNode.height()/2;
						
						ctx.moveTo(beginX,beginY);
						ctx.lineTo(endX,endY);
					}
				}		    	  
			});
		    ctx.stroke();  
			
		}
		
		$scope.formatColumns = function(){
			var container = jQuery("#container");
			var width = container.width();
			var maxQuantity = Math.max($scope.analysis.projects.length, $scope.analysis.subcontractors.length);
			var max = maxQuantity * 60; //total pixel height space alloted
			
			
			var projects = jQuery("#projects li");
			projects.each(function(index){
				var slice = max / projects.length;				
				jQuery(this).css("top", (slice*index) + "px");				
			});
			
			var subcontractors = jQuery("#subcontractors li");
			console.log(subcontractors.length);
			subcontractors.each(function(index){				
				var slice = max / subcontractors.length;
				jQuery(this).css("top", (slice*index) + "px");
				jQuery(this).css("left","600px");
			});			
			container.css("height",(max+60) + "px");
		}
				
		
		
		$scope.getAnalysis();
		
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
<div ng-controller="analysisController" id="wrapper">

	<h1>My Projects</h1>
	<div id="container">
		<ul id="projects">
			<li ng-repeat="project in analysis.projects">{{project.name}}</li>
		</ul>
		<ul id="subcontractors">
			<li ng-repeat="subcontractor in analysis.subcontractors" ng-attr-id="{{ 'subcontractor-' + subcontractor.id }}">{{subcontractor.name}}</li>
		</ul>
	</div>
</div>
