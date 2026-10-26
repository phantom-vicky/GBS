angular.module('gbmUltraApp').controller('cbsGeneralSetupController', function($scope, $stateParams, cbsService){
	
	$scope.initialize = function(){
		
		$scope.baseurl = "gbmultra/cbs/integration/generalsetup";
		
		$scope.generalSetup = {};
		$scope.generalSetupScope = {};
		$scope.leftMenu = {};
		$scope.leftMenu.add = {display: false, href: '#/'};
		$scope.leftMenu.modify = {display: true, href: '#!/cbs_general_setup/modify'};
		$scope.leftMenu.delete = {display: false, href: '#/'};
		$scope.leftMenu.inquiry = {display: true, href: '#!/cbs_general_setup/inquiry'};
		$scope.leftMenu.verify = {display: true, href: '#!/cbs_general_setup/verify'};
		
	};
	$scope.initialize();
	
	$scope.action = $stateParams.operation;
	
	if($scope.action == '' || typeof($scope.action) == 'undefined'){
		$scope.action = 'modify';
	}
	
	$scope.submitForm = function(data){
		if($scope.action == 'verify'){
			$scope.verify();
		} else if($scope.action == 'modify'){
			$scope.modify();
		}
	};
	
	$scope.generateData = function(){
		var data = [];
		angular.forEach($scope.generalSetup, function(value, item){
			if(typeof($scope.generalSetupScope[value.parameterName]) != 'undefined' && $scope.generalSetupScope[value.parameterName] != '' ){
				if(value.parameterValue != $scope.generalSetupScope[value.parameterName]){
					value.isVerified = false;
				}
				value.parameterValue = $scope.generalSetupScope[value.parameterName];
			}
			data.push(value);
        });
		console.log(data);
		return data;
	};

	$scope.getGeneralSetupParameters = function(){
		
		var url = $scope.baseurl;
		var parameter= '';
		
		cbsService.getMethod(url, parameter, function(response){
			if(response.success) {
				$scope.initialize();
				$scope.generalSetup = response.data;
				$scope.enableVerifyButton = true;
				angular.forEach($scope.generalSetup, function(value){
					
					if($scope.action == 'verify'){
						if(value.isVerified == false){
							$scope.enableVerifyButton = false;
						}
						$scope.generalSetupScope[value.parameterName] = value.parameterValue;
					} else {
						$scope.generalSetupScope[value.parameterName] = value.parameterValue;
					}
					
				});
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
		
	}
	
	$scope.modify = function(){
		var url = $scope.baseurl;
		var data = $scope.generateData();
		
		cbsService.postMethod(url, data, function(response){
			if(response.success) {
				$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
        		$scope.initialize();
        		$scope.getGeneralSetupParameters();
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
		});
	};
	
	$scope.verify = function(){
		var url = $scope.baseurl+'/verify';
		var data = $scope.generateData();
		var result = confirm("Want to verify?");
		if (result) {
			cbsService.postMethod(url, data, function(response){
				if(response.success) {
					$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
	        		$scope.initialize();
	        		$scope.getGeneralSetupParameters();
				} else {
					$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
				}
			});
		}
	};
	
	$scope.getPage = function(){
		switch($scope.action){
			case 'modify':
			case 'verify':
			case 'inquiry':
				$scope.initialize();
				$scope.getGeneralSetupParameters();
				break;
			/*case 'verify':
				$scope.initialize();
				$scope.getGeneralSetupParameters();
				break;
			case 'inquiry':
				$scope.initialize();
				$scope.getGeneralSetupParameters();
				break;*/
		}
	};
	$scope.getPage();
	
	$scope.moveTo = function (){
		$location.path('/');
	}
	
	
});