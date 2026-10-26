(function() {

	'use strict';

	angular.module('gbmUltraApp').service('cbsBranchesOfOtherBankService',
			cbsBranchesOfOtherBankService);

	cbsBranchesOfOtherBankService.$inject = [ '$http' ];

	function cbsBranchesOfOtherBankService($http) {

		var cbsBranchesOfOtherBankService = this;

		cbsBranchesOfOtherBankService.getrecord = getrecord;
		cbsBranchesOfOtherBankService.getverifiedrecords = getverifiedrecords;
		cbsBranchesOfOtherBankService.getunverifiedrecords = getunverifiedrecords;
		cbsBranchesOfOtherBankService.getlist = getlist;
		cbsBranchesOfOtherBankService.add = add;
		cbsBranchesOfOtherBankService.modify = modify;
		cbsBranchesOfOtherBankService.verify = verify;
		cbsBranchesOfOtherBankService.deleteBranch = deleteBranch;
		cbsBranchesOfOtherBankService.inquiryTorch = inquiryTorch;
		cbsBranchesOfOtherBankService.verifyDeleteTorch = verifyDeleteTorch;
		cbsBranchesOfOtherBankService.verifySubmit = verifySubmit;
		cbsBranchesOfOtherBankService.deleteSubmit = deleteSubmit;
		cbsBranchesOfOtherBankService.modifySubmit = modifySubmit;
		cbsBranchesOfOtherBankService.modifyTorch = modifyTorch;

    		cbsBranchesOfOtherBankService.getRemarksList = getRemarksList;



        function inquiryTorch(requestData)
           {
              return	$http({
                  method : "POST",
                  url : 'gbmultra/cbs/integration/master/otherbankbranches/inquiry',
                  data :requestData
              });
           }

        function verifyDeleteTorch(requestData)
          {
             return	$http({
                 method : "POST",
                 url : 'gbmultra/cbs/integration/master/otherbankbranches/verifyDeleteSelect',
                 data :requestData
             });
          }
      function modifyTorch(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/otherbankbranches/modifySelect',
               data :requestData
           });
        }

          function verifySubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/otherbankbranches/verifySubmit',
               data :requestData
           });
        }
        function deleteSubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/cbs/integration/master/otherbankbranches/deleteSubmit',
               data :requestData
           });
        }
		function getrecord(id) {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankbranches',
				data : {id:id}
			});
		}

		function getverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankbranches/getVerifiedRecords',
				data : {}
			});
		}

		function getunverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankbranches/unverifiedrecords',
				data : {}
			});
		}
		function getlist() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankbranches/list',
				data : {}
			});
		}

		function add(formData) {
			var parameter = formData;
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankbranches/add',
				params : {},
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function modifySubmit(formData) {
            var parameter = formData;
            return $http({
                method : "post",
                url : 'gbmultra/cbs/integration/master/otherbankbranches/modifySubmit',
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
				url : 'gbmultra/cbs/integration/master/otherbankbranchesupdate',
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
				url : 'gbmultra/cbs/integration/master/otherbankbranches/verify',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function deleteBranch(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/otherbankbranches/delete',
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
                              });
                           }

	}

})();