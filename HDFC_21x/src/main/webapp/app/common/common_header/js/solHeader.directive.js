gbmUltraApp.directive('solHeader', function () {
	return {
	    restrict: 'E', //E = element, A = attribute, C = class, M = comment
	    scope: {
	        //@ reads the attribute value, = provides two-way binding, & works with functions
	        title: '@'
	    },
	    controller : function($scope,$rootScope) {
	       $scope.globals =$rootScope.globals
	    },
	    templateUrl: 'app/common/common_header/views/solHeader.html'
	}
});