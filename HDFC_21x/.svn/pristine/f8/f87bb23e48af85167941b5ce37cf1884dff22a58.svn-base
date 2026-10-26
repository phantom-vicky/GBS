(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('sackUserService', sackUserService);

    sackUserService.$inject = ['$http'];

    function sackUserService($http) {
    	
    	var sackUserService = this;
    	sackUserService.getSolID = getSolID;
    	sackUserService.getSackUsers = getSackUsers;
    	sackUserService.updateSackUser  =  updateSackUser;
    	
    	
    	
    	
    	 function getSolID(callback){
    	   	$http({
    	            method: "POST",
    	            url: "gbmultra/common/loginsol", /*/MANOJ6"*/ // MANOJ6 - is hard coded value provided by Developer. It will be replaced by current logged in user ID 
    	            headers: {
    	                'Content-type': 'application/json;charset=utf-8'
    	            }
    	        }).then(function(response){
    				if(response.status==200){
    					response.success = true;
    					callback(response);
    				}
    			},
    			function(response){
    				callback(response);
    			});
    	    };
    	    
    	    function getSackUsers(postData, callback){
    	     var data = JSON.stringify(postData);
    	   	 	$http({
    	            method: "post",
    	            url: "gbmultra/admin/activeusersforsack",
    	            data: data,
    	            headers: {
    	                'Content-type': 'application/json;charset=utf-8'
    	            }
    	        }).then(function(response){
    				if(response.status==200){
    					response.success = true;
    					callback(response);
    				}
    			},
    			function(response){
    				callback(response);
    			});
    	    };
    	    
    	    function updateSackUser(postData, callback) {
    	        var data = JSON.stringify(postData);
    	        $http({
    	            method: "post",
    	            url: "gbmultra/admin/sackusers",
    	            data: data,
    	            headers: {
    	                'Content-type': 'application/json;charset=utf-8'
    	            }
    	        }).then(function(response){
    				if(response.status==200){
    					response.success = true;
    					callback(response);
    				}
    			},
    			function(response){
    				callback(response);
    			});
    	    };
    	    
        
    }
    
})();