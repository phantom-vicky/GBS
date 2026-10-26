(function() {

	'use strict';

	angular.module('gbmUltraApp').service('schemeCodesService',
			schemeCodesService);

	schemeCodesService.$inject = [ '$http' ];

	function schemeCodesService($http) {

		var schemeCodesService = this;

		schemeCodesService.addschemeCodes = addschemeCodes;
		schemeCodesService.modify = modify;
		schemeCodesService.verify = verify;
		schemeCodesService.deleteschemeCodes = deleteschemeCodes;
		schemeCodesService.getrecord = getrecord;
		schemeCodesService.getverifiedrecords = getverifiedrecords;
		schemeCodesService.getunverifiedrecords = getunverifiedrecords;
		schemeCodesService.getlist = getlist;

		function addschemeCodes(data) {
			var parameter = data;

			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/schemecodes/save',
				params : {},
				data : parameter
			});

		}

		function modify(data) {
			var parameter = data;
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/schemecodesupdate',
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
				url : 'gbmultra/cbs/integration/master/schemecodes/verify',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function deleteschemeCodes(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/schemecodes/delete',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function getrecord(id) {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/schemecodes',
				data : {id:id}
			});
		}

		function getverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/schemecodes',
				data : {}
			});
		}

		function getunverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/schemecodes/unverifiedrecords',
				data : {}
			});
		}
		function getlist() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/schemecodes/list',
				data : {}
			});
		}
		  schemeCodesService.inquiry = function(requestData){
                         return $http({
                            method: 'post',
                            url: 'gbmultra/cbs/integration/master/schemecodes/inquiry',
                           data: requestData
                        })
                    }
          schemeCodesService.verDelDetails = function(requestData){
                 return $http({
                    method: 'post',
                    url: 'gbmultra/cbs/integration/master/schemecodes/verDelDetails',
                   data: requestData
                })
            }

         schemeCodesService.verifySubmit = function(requestData){
                     return $http({
                        method: 'post',
                        url: 'gbmultra/cbs/integration/master/schemecodes/verifySubmit',
                       data: requestData
                    })
                }

         schemeCodesService.deleteSubmit = function(requestData){
                             return $http({
                                method: 'post',
                                url: 'gbmultra/cbs/integration/master/schemecodes/deleteSubmit',
                               data: requestData
                            })
                        }

          schemeCodesService.modifyDetails = function(requestData){
                     return $http({
                        method: 'post',
                        url: 'gbmultra/cbs/integration/master/schemecodes/modifyDetails',
                       data: requestData
                    })
                }

        schemeCodesService.modify = function(requestData){
                 return $http({
                    method: 'post',
                    url: 'gbmultra/cbs/integration/master/schemecodes/modify',
                   data: requestData
                })
            }

	}

})();