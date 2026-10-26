gbmUltraApp.controller('cbsCommonController', function ($scope, $routeParams, cbsService){
	$scope.viewby = '10';
	$scope.totalItems = 0;
	$scope.currentPage = 1;
	$scope.itemsPerPage = $scope.viewby;
	$scope.maxSize = 5; //Number of pager buttons to show

	$scope.setPage = function(pageNo) {
		$scope.currentPage = parseInt(pageNo);
		$scope.displayDots();
	};
	
	$scope.displayDots = function(){
		var pageNo = $scope.currentPage;
		//var calulatePage = Math.ceil($scope.totalItems/$scope.itemsPerPage);
		$scope.currentPageClass = [];
		$scope.currentDisplay = false;
		$scope.currentPageClass[pageNo] = 'active-page';
		if(pageNo == 1){
			for(var i=1; i<=5; i++){
				$scope.currentPageClass[pageNo+i] = 'active-page';
			}
		}
		
	}
	$scope.displayDots();

	$scope.pageChanged = function() {
		console.log('Page changed to: ' + $scope.currentPage);
	};

	$scope.setItemsPerPage = function(num) {
		$scope.itemsPerPage = num;
		$scope.currentPage = 1; //reset to first page
		$scope.displayDots();
	}

	$scope.firstPageDisabled = function() {
		return $scope.currentPage === 1 ? "disabled" : "";
	};
	
	$scope.lastPageDisabled = function() {
		return $scope.currentPage === Math.ceil($scope.totalItems/$scope.itemsPerPage) ? "disabled" : "";
	};

	$scope.firstPage = function() {
		if ($scope.currentPage > 1) {
			$scope.currentPage = 1;
			$scope.displayDots();
		}
	};
	
	$scope.lastPage = function() {
		//$scope.itemsPerPage = parseInt($scope.itemsPerPage);
		var calulatePage = Math.ceil($scope.totalItems/$scope.itemsPerPage);
	    if ($scope.currentPage < calulatePage) {
			$scope.currentPage = calulatePage;
			$scope.displayDots();
		}
	};

	$scope.searchInquiryFn = function(){
		if($scope.recordsList > 0){
			var tempRecordsList = [];
			angular.forEach($scope.recordsList, function(value, item){
				
				console.log(value);
				angular.forEach(value, function(tempValue, tempItem){
					console.log(tempValue);
					
				});
				tempRecordsList[item] = value;
            });
		}
	}
});