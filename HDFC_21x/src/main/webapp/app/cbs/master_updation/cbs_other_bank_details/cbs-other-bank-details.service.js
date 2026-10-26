(function() {

	'use strict';

	angular.module('gbmUltraApp').service('cbsOtherBankDetailsService',
			cbsOtherBankDetailsService);

	cbsOtherBankDetailsService.$inject = [ '$http' ];

	function cbsOtherBankDetailsService($http) {

		var cbsOtherBankDetailsService = this;

		cbsOtherBankDetailsService.getrecord = getrecord;
		cbsOtherBankDetailsService.getverifiedrecords = getverifiedrecords;
		cbsOtherBankDetailsService.getunverifiedrecords = getunverifiedrecords;
		cbsOtherBankDetailsService.getlist = getlist;
		cbsOtherBankDetailsService.add = add;
		cbsOtherBankDetailsService.modify = modify;
		cbsOtherBankDetailsService.verify = verify;
		cbsOtherBankDetailsService.deleteOtherBank = deleteOtherBank;
		cbsOtherBankDetailsService.inquiryTorch = inquiryTorch;
		cbsOtherBankDetailsService.verifyDeleteTorch = verifyDeleteTorch;
		cbsOtherBankDetailsService.modifyTorch = modifyTorch;
		cbsOtherBankDetailsService.verifySubmit = verifySubmit;
		cbsOtherBankDetailsService.deleteSubmit = deleteSubmit;
		cbsOtherBankDetailsService.modifySubmit = modifySubmit;
		cbsOtherBankDetailsService.getRemarksList = getRemarksList;

        function inquiryTorch(requestData)
           {
              return	$http({
                  method : "POST",
                  url : 'gbmultra/cbs/integration/master/otherbankdetails/inquiry',
                  data :requestData
              });
           }

        function verifyDeleteTorch(requestData)
          {
             return	$http({
                 method : "POST",
                 url : 'gbmultra/cbs/integration/master/otherbankdetails/verifyDeleteSelect',
                 data :requestData
             });
          }
      function modifyTorch(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/otherbankdetails/modifySelect',
               data :requestData
           });
        }

          function verifySubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/otherbankdetails/verifySubmit',
               data :requestData
           });
        }
        function deleteSubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/otherbankdetails/deleteSubmit',
               data :requestData
           });
        }
        function modifySubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/otherbankdetails/modifySubmit',
               data :requestData
           });
        }
		function getrecord(id) {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankdetails',
				data : {id:id}
			});
		}

		function getverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankdetails/getVerifiedRecords',
				data : {}
			});
		}

		function getunverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankdetails/unverifiedrecords',
				data : {}
			});
		}
		function getlist() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankdetails/list',
				data : {}
			});
		}

		function add(data) {
			var parameter = data;

			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankdetails/add',
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
				url : 'gbmultra/cbs/integration/master/otherbankdetailsupdate',
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
				url : 'gbmultra/cbs/integration/master/otherbankdetails/verify',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function deleteOtherBank(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankdetails/delete',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function getRemarksList(requestData){
             return $http({
                  method:'POST',
                  url:'rbiBond/getRemarksList',
                  data:requestData
             })
        }

	}

})();