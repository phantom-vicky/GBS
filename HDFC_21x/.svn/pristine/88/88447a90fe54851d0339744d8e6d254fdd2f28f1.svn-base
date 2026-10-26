(function() {

	'use strict';

	angular.module('gbmUltraApp').service('cbsListOfTransactionCodesService',
			cbsListOfTransactionCodesService);

	cbsListOfTransactionCodesService.$inject = [ '$http' ];

	function cbsListOfTransactionCodesService($http) {

		var cbsListOfTransactionCodesService = this;

		cbsListOfTransactionCodesService.getrecord = getrecord;
		cbsListOfTransactionCodesService.getverifiedrecords = getverifiedrecords;
		cbsListOfTransactionCodesService.getunverifiedrecords = getunverifiedrecords;
		cbsListOfTransactionCodesService.getlist = getlist;
		cbsListOfTransactionCodesService.modify = modify;
		cbsListOfTransactionCodesService.verify = verify;
		cbsListOfTransactionCodesService.deletetransactioncodes = deletetransactioncodes;
		cbsListOfTransactionCodesService.add = add;
		cbsListOfTransactionCodesService.inquiryTorch = inquiryTorch;
		cbsListOfTransactionCodesService.verifyDeleteTorch = verifyDeleteTorch;
		cbsListOfTransactionCodesService.modifyTorch = modifyTorch;
		cbsListOfTransactionCodesService.verifySubmit = verifySubmit;
		cbsListOfTransactionCodesService.deleteSubmit = deleteSubmit;
		cbsListOfTransactionCodesService.modifySubmit = modifySubmit;

		function add(formData) {
			var parameter = formData;
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/transactioncodes/add',
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
                  url : 'gbmultra/cbs/integration/master/transactioncodes/inquiry',
                  data :requestData
              });
           }

        function verifyDeleteTorch(requestData)
          {
             return	$http({
                 method : "POST",
                 url : 'gbmultra/cbs/integration/master/transactioncodes/verifyDeleteSelect',
                 data :requestData
             });
          }
        function modifyTorch(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/transactioncodes/modifySelect',
               data :requestData
           });
        }

          function verifySubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/transactioncodes/verifySubmit',
               data :requestData
           });
        }
        function deleteSubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/transactioncodes/deleteSubmit',
               data :requestData
           });
        }

        function modifySubmit(formData) {
            var parameter = formData;
            return $http({
                method : "post",
                url : 'gbmultra/cbs/integration/master/transactioncodes/modifySubmit',
                params : {},
                data : parameter,
                headers : {
                    'Content-type' : 'application/json;charset=utf-8'
                }
            });
        }
		function modify(data) {
			var parameter = data;
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/transactioncodesupdate',
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
				url : 'gbmultra/cbs/integration/master/transactioncodes/verify',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function deletetransactioncodes(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/transactioncodes/delete',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function getrecord(id) {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/transactioncodes',
				data : {id:id}
			});
		}

		function getverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/transactioncodes/getVerifiedRecords',
				data : {}
			});
		}

		function getunverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/transactioncodes/unverifiedrecords',
				data : {}
			});
		}
		function getlist() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/transactioncodes/list',
				data : {}
			});
		}

	}

})();