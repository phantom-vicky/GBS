(function() {

	'use strict';

	angular.module('gbmUltraApp').service('officeAccountPlaceHolderService',
			officeAccountPlaceHolderService);

	officeAccountPlaceHolderService.$inject = [ '$http' ];

	function officeAccountPlaceHolderService($http) {

		var officeAccountPlaceHolderService = this;

		officeAccountPlaceHolderService.add = add;
		officeAccountPlaceHolderService.getlist = getlist;
		officeAccountPlaceHolderService.modify = modify;
		officeAccountPlaceHolderService.verify = verify;
		officeAccountPlaceHolderService.getrecord = getrecord;
		officeAccountPlaceHolderService.getverifiedrecords = getverifiedrecords;
		officeAccountPlaceHolderService.getunverifiedrecords = getunverifiedrecords;
		officeAccountPlaceHolderService.deleteOffice = deleteOffice;

		function getrecord(id) {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/officeaccountplaceholder',
				data : {id:id}
			});
		}

		function getverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/officeaccountplaceholder/getVerifiedRecords',
				data : {}
			});
		}

		function getunverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/officeaccountplaceholder/unverifiedrecords',
				data : {}
			});
		}

		function getlist() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/officeaccountplaceholder/list',
				data : {}
			});
		}

		function add(data) {
			var parameter = data;

			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/officeaccountplaceholder/save',
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
				url : 'gbmultra/cbs/integration/master/officeaccountplaceholderupdate',
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
				url : 'gbmultra/cbs/integration/master/officeaccountplaceholder/verify',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function deleteOffice(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/officeaccountplaceholder/delete',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		 officeAccountPlaceHolderService.inquiry = function(requestData){
             return $http({
                method: 'post',
                url: 'gbmultra/cbs/integration/master/officeaccountplaceholder/inquiry',
               data: requestData
            })
        }
          officeAccountPlaceHolderService.verDelDetails = function(requestData){
                 return $http({
                    method: 'post',
                    url: 'gbmultra/cbs/integration/master/officeaccountplaceholder/verDelDetails',
                   data: requestData
                })
            }

         officeAccountPlaceHolderService.verifySubmit = function(requestData){
                     return $http({
                        method: 'post',
                        url: 'gbmultra/cbs/integration/master/officeaccountplaceholder/verifySubmit',
                       data: requestData
                    })
                }

         officeAccountPlaceHolderService.deleteSubmit = function(requestData){
                             return $http({
                                method: 'post',
                                url: 'gbmultra/cbs/integration/master/officeaccountplaceholder/deleteSubmit',
                               data: requestData
                            })
                        }

          officeAccountPlaceHolderService.modifyDetails = function(requestData){
                     return $http({
                        method: 'post',
                        url: 'gbmultra/cbs/integration/master/officeaccountplaceholder/modifyDetails',
                       data: requestData
                    })
                }

        officeAccountPlaceHolderService.modify = function(requestData){
                 return $http({
                    method: 'post',
                    url: 'gbmultra/cbs/integration/master/officeaccountplaceholder/modify',
                   data: requestData
                })
            }
	}
})();