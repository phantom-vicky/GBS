angular.module('gbmUltraApp').controller('cbsBulkUploadController', function ($scope, $location, cbsBulkUploadService){
	

	$scope.leftMenu = {};
	$scope.leftMenu.upload = {display: true, href: '#!/cbs_bulk_upload'};
	$scope.leftMenu.add = {display: false, href: '#/'};
	$scope.leftMenu.modify = {display: false, href: '#/'};
	$scope.leftMenu.delete = {display: false, href: '#/'};
	$scope.leftMenu.inquiry = {display: false, href: '#/'};
	$scope.leftMenu.verify = {display: true, href: '#!/cbs_bulk_upload_verify'};
	
	$scope.cbsBulkUpload = {};
	$scope.cbsBulkUpload.functionality = "";
	$scope.file = "";
	$scope.fileUploadDataLoading = false;
	
	$scope.submitForm = function(){
		var $el = $('#file');
		$scope.fileUploadDataLoading = true;
		cbsBulkUploadService.addcbsBulkUpload($scope.file, $scope.cbsBulkUpload.functionality)
        .then(
                function (data) {
                	if(data.status==200){
                		var json = JSON.parse(data.data);
            		    $scope.$emit('showAlert', { 'type': 'true', 'msg': $scope.file.name + " " + json.message });
            		    $scope.file = "";
                		$scope.cbsBulkUpload.functionality = "";
                		
                		clearFileInput($el);
                		
                		if (typeof(param) != "undefined"){
                    		$location.path('/');
                    	}
                	}
                	$scope.fileUploadDataLoading = false;
                }, function (data) {
                	
                	if(data.status==500){
                		$scope.$emit('showAlert', { 'type': 'false', 'msg': 'Something went Wrong!' });
                	}

                	$scope.fileUploadDataLoading = false;
                	clearFileInput($el);
                	
                	var json = JSON.parse(data.data);
                	if(data.status==400){
                		$scope.$emit('showAlert', { 'type': 'false', 'msg': json.error });
                	} else if(typeof(json.result.File_Exists) != "undefined"){
                		$scope.$emit('showAlert', { 'type': 'false', 'msg': json.result.File_Exists });
                	} else if(typeof(json.result.Duplicate_Data) != "undefined"){
                		$scope.$emit('showAlert', { 'type': 'false', 'msg': json.result.Duplicate_Data });
                	} else if(typeof(json.result.Invalid_File) != "undefined"){
                		$scope.$emit('showAlert', { 'type': 'false', 'msg': json.result.Invalid_File });
                	} else if(typeof(json.message) != "undefined"){
                		$scope.$emit('showAlert', { 'type': 'false', 'msg': json.message });
                	} else {
                		$scope.$emit('showAlert', { 'type': 'false', 'msg': 'Something went Wrong!' });
                	}
                }
        );
	}
	
	$scope.moveTo = function (){
		$location.path('/');
	}
	
	
	});