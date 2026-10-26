angular
.module('gbmUltraApp').service('sackUserService', function ($http) {
	
	var baseurl = "admin/sackuser/";
	var service = {};
    
    service.getSolID = function(callback){
   	 	$http({
            method: "get",
            url: baseurl+"solid", /*/MANOJ6"*/ // MANOJ6 - is hard coded value provided by Developer. It will be replaced by current logged in user ID 
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
    
    service.getSackUsers = function(callback){
   	 	$http({
            method: "get",
            url: baseurl+"getSackUserList",  
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
    
    service.updateSackUser = function(postData, callback) {
        var data = JSON.stringify(postData);
        $http({
            method: "post",
            url: baseurl+"update",
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
    
    return service;
});