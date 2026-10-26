angular
.module('gbmUltraApp').controller('sackUserController', function ($scope, $location, sackUserService) {
	
	$scope.getSolID = function(){
		//$scope.loader = true;
		sackUserService.getSolID(function(response){
			if(response.success) {
				$scope.solId = response.data.solId;
				$scope.solDescription = response.data.solDescription;
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
			}
			$scope.loader = false;
        });
	}
	
	$scope.getSackUsers = function(){
		$scope.loader = true;
		sackUserService.getSackUsers(function(response){
			if(response.success) {
				$scope.sackUsers = response.data;
				angular.forEach($scope.sackUsers, function (item) {
		            item.Selected = true;
				});
				$scope.getSolID();
				//$scope.IsAllChecked = true;
				$scope.CheckUncheckHeader();
				$scope.showForm = true;
			} else {
				$scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
				$scope.loader = false;
			}
			
        });
	}

	$scope.initialize = function(){
		
		$scope.sackUsers = "";
		$scope.solId = "";
		$scope.solDescription = "";
		$scope.showForm = true;
		$scope.selectedUsers = [];
		$scope.isAllSelected = false;

		$scope.leftMenu = {};
		$scope.leftMenu.add = {display: false, href: '#!/'};
		$scope.leftMenu.modify = {display: false, href: '#!/'};
		$scope.leftMenu.delete = {display: false, href: '#!/'};
		$scope.leftMenu.inquiry = {display: false, href: '#!/'};
		$scope.leftMenu.verify = {display: false, href: '#!/'};
		
		// to initialize loader
		$scope.loader = false;
		
		$scope.getSackUsers();
	}
	$scope.initialize();
	
	$scope.CheckUncheckHeader = function ($event) {
		
    	$scope.IsAllChecked = true;
        angular.forEach($scope.sackUsers, function (item) {
        	if(!item.Selected){
        		item.Selected = false;
        		$scope.IsAllChecked = false;
        	}
        });
    };

    $scope.CheckUncheckAll = function ($event) {
    	if($scope.IsAllChecked == true){
    		$scope.IsAllChecked = false;
    	} else {
    		$scope.IsAllChecked = true;
    	}
        angular.forEach($scope.sackUsers, function (item) {
        	
        	item.Selected = $scope.IsAllChecked;
        	//console.log(item.Selected);
        })
    };
	
    /*
	$scope.toggleCheckbox = function(user_id){
		$scope.isAllSelected = false;
		var index =  $scope.selectedUsers.indexOf(user_id);
		if(index == -1){
			$scope.selectedUsers.push(user_id);
		} else {
			$scope.selectedUsers.splice(index, 1);
		}
	}
	
	$scope.toggleAllCheckbox = function(){
	    
        angular.forEach($scope.sackUsers, function (item) {
            item.selected =  event.target.checked;
            if($scope.isAllSelected == true){
            	$scope.selectedUsers.push(item.id);
            } else {
            	var index =  $scope.selectedUsers.indexOf(item.id);
            	$scope.selectedUsers.splice(index, 1);
            }
        });
	}
	*/
    
	$scope.submitForm = function(){
		$scope.loader = true;
		
		var data = [];
		angular.forEach($scope.sackUsers, function (item) {
			if(item.Selected == true){
				var tempData = {
						"id": item.id,
				    	"solId": item.solId,
				        "userId": item.userId,
				        "inTime": item.inTime	
				};
				data.push(tempData);
			}
		});
		
		sackUserService.updateSackUser(data,
            function (response) {
            	if(response.status==200){
            		$scope.sackUser = {};
            		$scope.$emit('showAlert', { 'type': 'true', 'msg': response.data.message });
            		$scope.initialize();
            	} else {
                	$scope.$emit('showAlert', { 'type': 'false', 'msg': response.data.message });
                }         
            	$scope.loader = false;
			}
        );
	}
	
	$scope.cancelOperation = function(action){
		location.href = "#!/admin_functionality";
	}
});