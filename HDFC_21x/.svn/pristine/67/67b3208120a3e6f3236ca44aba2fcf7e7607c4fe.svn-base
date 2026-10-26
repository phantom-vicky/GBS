(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('branchIfscCodeService', branchIfscCodeService);

    branchIfscCodeService.$inject = ['$http'];

    function branchIfscCodeService($http) {
    	
    	var branchIfscCodeService = this;
    	
    	
    	branchIfscCodeService.add = add;
    	branchIfscCodeService.modify = modify;
    	branchIfscCodeService.verify = verify;
    	branchIfscCodeService.deleteBranchIfscCodes = deleteBranchIfscCodes;
    	branchIfscCodeService.getrecord = getrecord;
    	branchIfscCodeService.getverifiedrecords = getverifiedrecords;
    	branchIfscCodeService.getunverifiedrecords = getunverifiedrecords;
    	branchIfscCodeService.getlist = getlist;
    	branchIfscCodeService.modifyTorch=modifyTorch;
    	branchIfscCodeService.inquiryTorch=inquiryTorch;
    	branchIfscCodeService.verifyDeleteTorch=verifyDeleteTorch;
    	branchIfscCodeService.verifySubmit=verifySubmit;
    	branchIfscCodeService.deleteSubmit=deleteSubmit;

    	 function add(data) {
    	        var parameter = data;

    	        return $http({
    	            method: "post",
    	            url: 'gbmultra/cbs/integration/master/branchifscdetails/save',
    	            params: {},
    	            data: parameter,
    	            headers : {
    					'Content-type' : 'application/json;charset=utf-8'
    				}
    	        });
    	   }

    	   function modifyTorch(requestData)
           {
              return	$http({
                  method : "POST",
                  url : 'gbmultra/cbs/integration/master/branchifscdetails/modifySelect',
                  data :requestData
              });
           }
           function inquiryTorch(requestData)
           {
              return	$http({
                  method : "POST",
                  url : 'gbmultra/cbs/integration/master/branchifscdetails/inquiry',
                  data :requestData
              });
           }

            function verifyDeleteTorch(requestData)
           {
              return	$http({
                  method : "POST",
                  url : 'gbmultra/cbs/integration/master/branchifscdetails/verifyDeleteSelect',
                  data :requestData
              });
           }
     	function modify(data) {
     		var parameter = data;
 			return $http({
 				method : "put",
 				url : 'gbmultra/cbs/integration/master/branchifscdetails',
 				params : {},
 				data : parameter,
 				headers : {
 					'Content-type' : 'application/json;charset=utf-8'
 				}
 			});
 		}
       function verifySubmit(data) {
            var parameter = data;
            return $http({
                method : "post",
                url : 'gbmultra/cbs/integration/master/branchifscdetails/verifySubmit',
                params : {},
                data : parameter,
                headers : {
                    'Content-type' : 'application/json;charset=utf-8'
                }
            });
        }
        function deleteSubmit(data) {
            var parameter = data;
            return $http({
                method : "post",
                url : 'gbmultra/cbs/integration/master/branchifscdetails/deleteSubmit',
                params : {},
                data : parameter,
                headers : {
                    'Content-type' : 'application/json;charset=utf-8'
                }
            });
        }


        
       function verify(data) {
            var parameter = JSON.stringify(data);
            return $http({
                method: "post",
                url:'gbmultra/cbs/integration/master/branchifscdetails/verify',
                data: parameter,
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
        }
        
        function deleteBranchIfscCodes(data) {
            var parameter = JSON.stringify(data);
            return $http({
                method: "post",
                url:'gbmultra/cbs/integration/master/branchifscdetails/delete',
                data: parameter,
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
        }
        
    	function getrecord(id) {
			return $http({
				method : "put",
				url : 'gbmultra/cbs/integration/master/branchifscdetails',
				data : {id:id}
			});
		}
    	
    	function getverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/branchifscdetails/getVerifiedRecords',
				data : {}
			});
		}
    	
    	function getunverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/branchifscdetails/unverifiedrecords',
				data : {}
			});
		}
    	function getlist() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/branchifscdetails/list',
				data : {}
			});
		}
    	
    	
    	
    }
    
})();