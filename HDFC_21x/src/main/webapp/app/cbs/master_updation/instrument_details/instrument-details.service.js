(function() {

	'use strict';

	angular.module('gbmUltraApp').service('instrumentDetailsService',
			instrumentDetailsService);

	instrumentDetailsService.$inject = [ '$http' ];

	function instrumentDetailsService($http) {

		var instrumentDetailsService = this;

		instrumentDetailsService.addinstrumentDetails = addinstrumentDetails;
		instrumentDetailsService.modify = modify;
		instrumentDetailsService.verify = verify;
		instrumentDetailsService.deleteinstrument = deleteinstrument;
		instrumentDetailsService.getrecord = getrecord;
		instrumentDetailsService.getverifiedrecords = getverifiedrecords;
		instrumentDetailsService.getunverifiedrecords = getunverifiedrecords;
		instrumentDetailsService.getlist = getlist;
		instrumentDetailsService.getInstrumentTypes = getInstrumentTypes;

		function addinstrumentDetails(data) {
			var parameter = data;

			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/instruments/save',
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
				url : 'gbmultra/cbs/integration/master/instrumentsupdate',
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
				url : 'gbmultra/cbs/integration/master/instruments/verify',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function deleteinstrument(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/instruments/delete',
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function getrecord(id) {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/instruments',
				data : {id:id}
			});
		}

		function getverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/instruments/getVerifiedRecords',
				data : {}
			});
		}

		function getunverifiedrecords() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/instruments/unverifiedrecords',
				data : {}
			});
		}

		function getlist() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/instruments/list',
				data : {}
			});
		}

		function getInstrumentTypes() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/instruments/instrumenttypes',
				data : {}
			});
		}
         instrumentDetailsService.inquiry = function(requestData){
                 return $http({
                    method: 'post',
                    url: 'gbmultra/cbs/integration/master/instruments/inquiry',
                   data: requestData
                })
            }
          instrumentDetailsService.verDelDetails = function(requestData){
                 return $http({
                    method: 'post',
                    url: 'gbmultra/cbs/integration/master/instruments/verDelDetails',
                   data: requestData
                })
            }

         instrumentDetailsService.verifySubmit = function(requestData){
                     return $http({
                        method: 'post',
                        url: 'gbmultra/cbs/integration/master/instruments/verifySubmit',
                       data: requestData
                    })
                }

         instrumentDetailsService.deleteSubmit = function(requestData){
                             return $http({
                                method: 'post',
                                url: 'gbmultra/cbs/integration/master/instruments/deleteSubmit',
                               data: requestData
                            })
                        }

          instrumentDetailsService.modifyDetails = function(requestData){
                     return $http({
                        method: 'post',
                        url: 'gbmultra/cbs/integration/master/instruments/modifyDetails',
                       data: requestData
                    })
                }

        instrumentDetailsService.modify = function(requestData){
                 return $http({
                    method: 'post',
                    url: 'gbmultra/cbs/integration/master/instruments/modify',
                   data: requestData
                })
            }


	}

})();