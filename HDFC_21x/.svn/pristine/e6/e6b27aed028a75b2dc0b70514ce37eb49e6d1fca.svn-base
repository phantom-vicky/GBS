angular.module('gbmUltraApp').controller('populationOfSolDateController', function ($scope, $stateParams,$location,$filter, cbsService) {
	
	$scope.recordsList = '';
	$scope.displayForm = true;

	// Initialize the page
	$scope.initialize = function(){
		$scope.cbsPopulationOfSolDate = {};
		$scope.cbsPopulationOfSolDate.solDate = "";
		$scope.cbsPopulationOfSolDate.additionalText1 = "";
		$scope.cbsPopulationOfSolDate.additionalText2 = "";
		$scope.cbsPopulationOfSolDate.additionalText3 = "";
		$scope.displayFormTable = false;
		$scope.baseurl = "gbmultra/cbs/integration/master/soldate";
		
		// to initialize left menu
		$scope.leftMenu = {};
		$scope.leftMenu.add = {display: false, href: '#/'};
		$scope.leftMenu.modify = {display: true, href: '#!/population_of_sol_date/modify'};
		$scope.leftMenu.delete = {display: false, href: '#/'};
		$scope.leftMenu.inquiry = {display: true, href: '#!/population_of_sol_date/inquiry'};
		$scope.leftMenu.verify = {display: false, href: '#/'};
		
		$("#cbsDate").on("dp.change", function() {
	        $scope.cbsPopulationOfSolDate.solDate = $("#cbsDate").val();
	    });
		
		$('#cbsDate').datetimepicker({
	    	format: 'DD/MM/YYYY',
	    	sideBySide:true,
	    	showClose: true,
	        showClear: true,
	       // minDate:new Date()
	    });
		
	};
	$scope.initialize();
	
	
    $scope.action = $stateParams.operation;
    
    if($scope.action == '' || typeof($scope.action) == 'undefined'){
		$scope.action = 'modify';
	}
	
    
    
    
    $scope.generateData = function(){
		var data = {};
		angular.forEach($scope.cbsPopulationOfSolDate, function(value, item){
			if(item == 'solDate'){
			    var dateFormat = value.split("/");
			    var getsolDate = dateFormat[2] + '-' + dateFormat[1] + '-' +  dateFormat[0];
			    data[item] = getsolDate;
			   } else{
			    data[item] = value;
			    
			   }
        });
		return data;
	};
	
	// modify data to database
	$scope.modify = function(){
		var url = $scope.baseurl;
		var data = $scope.generateData();
		
		cbsService.postMethod(url, data, function(response){
			if(response.success) {
        		$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
        		$scope.initialize();
        		$scope.getRecord();
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	/*$scope.getRecord = function(){
		commonService.getSolDate()
		.then(
			function(response){
				if(response.status == 200){
					
					$scope.cbsPopulationOfSolDate = {};
					angular.forEach(response.data, function(value, item){
						if(item == 'solDate'){
	                   $scope.cbsPopulationOfSolDate[item] = $filter('date')(response.data.solDate, 'dd/MM/yyyy');
	                   
						}
						else
							{
							$scope.cbsPopulationOfSolDate[item] = value;
							
							}
	                   
	                });
				}
			}, function (response){
				console.log (response.data.message);
			}
		);
	}*/
	
	$scope.getRecord = function(){
		var url = $scope.baseurl;
		var parameter= '';
		
		cbsService.getMethod(url, parameter, function(response){
			
			if(response.success) {
				$scope.cbsPopulationOfSolDate = {};
				angular.forEach(response.data, function(value, item){
					if(item == 'solDate'){
                   $scope.cbsPopulationOfSolDate[item] = $filter('date')(value, 'dd/MM/yyyy');
                   
					}
					else
						{
						$scope.cbsPopulationOfSolDate[item] = value;
						
						}
                   
                });
				$scope.recordsList = '';
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	$scope.inquiryForm = function(){
		$scope.initialize();
		$scope.displayForm = true;
		$scope.getRecord();
	};
	
	$scope.inquiry = function(){
		$scope.initialize();
		$scope.displayForm = false;
		$scope.getRecord();
	};
	
	$scope.getPage = function(){
		switch($scope.action){
			case 'modify':
				$scope.initialize();
				$scope.recordsList = '';
				$scope.requiredClass = 'required';
				$scope.getRecord();
				$scope.displayForm = true;
				break;
			
			case 'inquiry':
				$scope.initialize();
				$scope.recordsList = '';
				$scope.requiredClass = '';
				$scope.displayForm = true;
				$scope.getRecord();
				break;
			
		}
	};
	$scope.getPage();
 
	$scope.moveTo = function (){
		$location.path('/populationOfSolDate/modify');
	}
});