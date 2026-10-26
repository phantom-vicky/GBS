angular.module('gbmUltraApp').service('cbsService', function ($http) {
	
	var service = {};
    
    service.postMethod = function(url, postData, callback){
    	var data = JSON.stringify(postData);
    	$http({
            method: "post",
            url: url,
            data: data
        })
		.then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			if(typeof(response.data.message) != 'undefined' && response.data.message != ''){
				response.message = response.data.message;
			} else {
				switch(response.status){
					case 500:
						response.message = "Server error. Unable to process request.";
						break;
					case 422:
						response.message = "Server side validation failed";
						break;
					case 400:
						response.message = response.data.error[0];
						break;
					case 405:
						response.message = "Method not allowed";
						break;
					default:
						response.message = response.data.message;
						break;
				};
			}
			callback(response);
		});
    };
    
    service.getMethod = function(url, parameter, callback){
   	 	$http({
            method: "get",
            url: url,
            param: parameter
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			if(typeof(response.data.message) != 'undefined' && response.data.message != ''){
				response.message = response.data.message;
			} else {
				switch(response.status){
					case 500:
						response.message = "Server error. Unable to process request.";
						break;
					case 422:
						response.message = "Server side validation failed";
						break;
					case 400:
						response.message = response.data.error[0];
						break;
					case 405:
						response.message = "Method not allowed";
						break;
					default:
						response.message = response.data.message;
						break;
				};
			}
			callback(response);
		});
    };

    service.deleteMethod = function(url, postData, callback) {
    	var data = JSON.stringify(postData);
        $http({
            method: "delete",
            url: url,
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
			if(typeof(response.data.message) != 'undefined' && response.data.message != ''){
				response.message = response.data.message;
			} else {
				switch(response.status){
					case 500:
						response.message = "Server error. Unable to process request.";
						break;
					case 422:
						response.message = "Server side validation failed";
						break;
					case 400:
						response.message = response.data.error[0];
						break;
					case 405:
						response.message = "Method not allowed";
						break;
					default:
						response.message = response.data.message;
						break;
				};
			}
			callback(response);
		});
    };
    
    service.putMethod = function(url, postData, callback) {
        var data = JSON.stringify(postData);
        $http({
            method: "put",
            url: url,
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
			if(typeof(response.data.message) != 'undefined' && response.data.message != ''){
				response.message = response.data.message;
			} else {
				switch(response.status){
					case 500:
						response.message = "Server error. Unable to process request.";
						break;
					case 422:
						response.message = "Server side validation failed";
						break;
					case 400:
						response.message = response.data.error[0];
						break;
					case 405:
						response.message = "Method not allowed";
						break;
					default:
						response.message = response.data.message;
						break;
				};
			}
			callback(response);
		});;
    };

    return service;
    
});