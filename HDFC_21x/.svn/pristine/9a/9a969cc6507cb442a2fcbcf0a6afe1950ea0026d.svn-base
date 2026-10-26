(function() {

	'use strict';

	angular.module('gbmUltraApp').service('cbsUserWorkClassInformationService',
			cbsUserWorkClassInformationService);

	cbsUserWorkClassInformationService.$inject = [ '$http' ];

	function cbsUserWorkClassInformationService($http) {

		var cbsUserWorkClassInformationService = this;

		cbsUserWorkClassInformationService.getlist = getlist;
		cbsUserWorkClassInformationService.add = add;
		cbsUserWorkClassInformationService.modify = modify;
		cbsUserWorkClassInformationService.verify = verify;
		cbsUserWorkClassInformationService.getrecord = getrecord;
		cbsUserWorkClassInformationService.deleteUser = deleteUser;
		cbsUserWorkClassInformationService.getverifiedrecords = getverifiedrecords;
		cbsUserWorkClassInformationService.getunverifiedrecords = getunverifiedrecords;
		cbsUserWorkClassInformationService.inquiryTorch = inquiryTorch;
		cbsUserWorkClassInformationService.verifyDeleteTorch = verifyDeleteTorch;
		cbsUserWorkClassInformationService.modifyTorch = modifyTorch;
		cbsUserWorkClassInformationService.verifySubmit = verifySubmit;
		cbsUserWorkClassInformationService.deleteSubmit = deleteSubmit;
		cbsUserWorkClassInformationService.modifySubmit = modifySubmit;
		function add(formData) {
			var parameter = JSON.stringify(formData);

			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/userdetails/add',
				params : {},
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}


        function inquiryTorch(requestData)
           {
              return	$http({
                  method : "POST",
                  url : 'gbmultra/cbs/integration/master/userdetails/inquiry',
                  data :requestData
              });
           }

        function verifyDeleteTorch(requestData)
          {
             return	$http({
                 method : "POST",
                 url : 'gbmultra/cbs/integration/master/userdetails/verifyDeleteSelect',
                 data :requestData
             });
          }
        function modifyTorch(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/userdetails/modifySelect',
               data :requestData
           });
        }

        function verifySubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/userdetails/verifySubmit',
               data :requestData
           });
        }
        function deleteSubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/userdetails/deleteSubmit',
               data :requestData
           });
        }
        function modifySubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/userdetails/modifySubmit',
               data :requestData
           });
        }
		function modify(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/userdetailsupdate',
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
				method : "post",
				url : 'gbmultra/cbs/integration/master/userdetails/verify',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function deleteUser(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/userdetails/delete',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function getrecord(gbmUserDetailsId) {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/userdetails',
				data : {id:gbmUserDetailsId}
			});
		}

		function getverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/userdetails/getVerfiedRecords',
				data : {}
			});
		}

		function getunverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/userdetails/unverifiedrecords',
				data : {}
			});
		}

		function getlist() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/userdetails/list',
				data : {}
			});
		}

	}

})();