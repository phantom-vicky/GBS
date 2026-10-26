//angular.module('gbmUltraApp').controller('commonCtrl', function($scope,$rootScope,$timeout,$location,$cookies,loginService) {
gbmUltraApp.controller('commonCtrl', function ($scope, $rootScope,$timeout,$location) {

	$scope.showAlert = false;	
	$scope.$on('showAlert', pushAlert);
	function pushAlert(event, data) {
		if(data.type=='true')
			$scope.alertClass = 'alert-success';				
		else
			$scope.alertClass = 'alert-danger';
		
        $scope.alert = data.msg;
        $scope.showAlert = true;
        $timeout(hideAlerts, 30000);
    }
	function hideAlerts(){
		$scope.showAlert = false;
		$scope.alert = undefined;
	}
	
	$scope.$on('$locationChangeStart', function( event ) {
		$timeout(hideAlerts, 10000);
	});
	
	/*function setCookie(){		
		if($cookies.get("access_token")!=undefined)
			$rootScope.showMenu = true;			
		else
			$rootScope.showMenu = false;
		
	}
	$scope.logout = function(){
		loginService.logout()
        .then(
                function (data) {
                	if(data.status==200){    
                		
                		$cookies.put("access_token", undefined);
                		$cookies.put("refresh_token", undefined);
                		$rootScope.showMenu = false;
                		$scope.$emit('showAlert', { 'type': 'true', 'msg': data.data });
                		$location.path('login');
                	}           
                }, function (data) {
                	$scope.$emit('showAlert', { 'type': 'false', 'msg': data.data.error_description });
                }
        );
		
    }*/
	
	
});

function cancelFn() {
    location.href = "#!/dc_nps_menu";
}

function clearFileInput($el){
	$el.wrap('<form>').closest('form').get(0).reset();
    $el.unwrap();
}

// DD-MM-YYYY to YYYY-MM-DD
function formateDate(date){
	date = date.split("-");
	return date[2] + '-' + date[1] + '-' + date[0];
}