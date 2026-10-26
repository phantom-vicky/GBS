angular.module('gbmUltraApp').controller('cbsBulkUploadVerifyController', function ($scope, $location, cbsBulkUploadService){
	$scope.leftMenu = {};
	$scope.leftMenu.upload = {display: true, href: '#!/cbs_bulk_upload'};
	$scope.leftMenu.add = {display: false, href: '#/'};
	$scope.leftMenu.modify = {display: false, href: '#/'};
	$scope.leftMenu.delete = {display: false, href: '#/'};
	$scope.leftMenu.inquiry = {display: false, href: '#/'};
	$scope.leftMenu.verify = {display: true, href: '#!/cbs_bulk_upload_verify'};
	
	$scope.initiateFunction = function(){
		$scope.functionalityDataLoading = false;
		$scope.fileDataLoading = false;
		$scope.commonDataLoading = false;
		$scope.cbsVerify = {};
		$scope.cbsVerify.functionality = "";
		$scope.cbsVerify.unverifiedFile = "";
		$scope.unverifiedFileList = "";
		$scope.showContent = false;
		
		$scope.itemsPerPage = '10';
		$scope.currentPage = 0;
		$scope.items = [];
	}
	$scope.initiateFunction();
	
	$scope.getUnverifiedFiles = function(){
		$scope.functionalityDataLoading = true;
		cbsBulkUploadService.getCBSExcelFileNames($scope.cbsVerify.functionality)
			.then(
					function(data){
						if(data.status == 200){
							$scope.functionalityDataLoading = false;
							$scope.unverifiedFileList = data.data;
							$scope.cbsVerify.unverifiedFile = $scope.unverifiedFileList[0];
							$scope.showContent= false;
						}
					}
			);
	}
	
	$scope.submitForm = function(){
		$scope.fileDataLoading = true;
		cbsBulkUploadService.getCBSDataByFileName($scope.cbsVerify.functionality, $scope.cbsVerify.unverifiedFile)
			.then(
					function(data){
						if(data.status == 200){
							$scope.fileDataLoading = false;
							$scope.items = data.data;
							$scope.currentPage = 0;
							$scope.config = {
							    itemsPerPage: '10',
							    fillLastPage: true
							}
						} else {
							$scope.$emit('showAlert', { 'type': 'false', 'msg': data.data.error });
						}
					}
			);
	}
	
	$scope.cbsVerify = function(){
		if($scope.currentPage == $scope.pageCount()){
			$scope.commonDataLoading = true;
			var result = confirm("Want to verify?");
			if (result) {
				cbsBulkUploadService.verifyCBSDataByFileName($scope.cbsVerify.functionality, $scope.cbsVerify.unverifiedFile)
					.then(
							function(data){
								if(data.status == 200){
									$scope.commonDataLoading = false;
									$scope.showContent = false;
				                	$scope.$emit('showAlert', { 'type': 'true', 'msg': data.data.message });
				                	$scope.cbsVerify.functionality = "";
				                	$scope.cbsVerify.unverifiedFile = "";
				                	$scope.unverifiedFileList = "";
				                	$scope.items = '';
								}
							}
					);
			} else {
				$scope.commonDataLoading = false;
			}
		}
	}

	$scope.cbsDelete = function(){
		$scope.commonDataLoading = true;
		var result = confirm("Want to delete?");
		if (result) {
			cbsBulkUploadService.deleteCBSDataByFileName($scope.cbsVerify.functionality, $scope.cbsVerify.unverifiedFile)
				.then(
						function(data){
							if(data.status == 200){
								$scope.commonDataLoading = false;
								$scope.showContent = false;
			                	$scope.$emit('showAlert', { 'type': 'true', 'msg': data.data.message });
			                	$scope.cbsVerify.functionality = "";
			                	$scope.cbsVerify.unverifiedFile = "";
			                	$scope.unverifiedFileList = "";
			                	$scope.items = '';
							}
						}
				);
		} else {
			$scope.commonDataLoading = false;
		}
	}
	
	$scope.moveTo = function (){
		$location.path('/');
	}
	
	  $scope.itemsPerPage = '10';
	  $scope.currentPage = 0;
	
	  $scope.prevPage = function() {
	    if ($scope.currentPage > 0) {
	      $scope.currentPage--;
	    }
	  };
	
	  $scope.prevPageDisabled = function() {
	    return $scope.currentPage === 0 ? "page-disabled" : "";
	  };
	
	  $scope.pageCount = function() {
	    return Math.ceil($scope.items.length/$scope.itemsPerPage)-1;
	  };
	
	  $scope.nextPage = function() {
	    if ($scope.currentPage < $scope.pageCount()) {
	      $scope.currentPage++;
	    }
	  };
	
	  $scope.nextPageDisabled = function() {
		  return $scope.currentPage === $scope.pageCount() ? "page-disabled" : "";
	  };
	  
	  $scope.buttonsDisabled = function(){
		  return $scope.currentPage === $scope.pageCount() ? "" : "disabled";
	  };
	  
	  
});