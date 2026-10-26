(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'rbibondTrailRunBrokerageService',
			rbibondTrailRunBrokerageService);

	rbibondTrailRunBrokerageService.$inject = [ '$http' ];

	function rbibondTrailRunBrokerageService($http) {
		var rbibondTrailRunBrokerageService = this;

        rbibondTrailRunBrokerageService.getRemarksList = getRemarksList;
		rbibondTrailRunBrokerageService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		rbibondTrailRunBrokerageService.getReferenceData = getReferenceData;
		rbibondTrailRunBrokerageService.getTrailRun = getTrailRun;
		rbibondTrailRunBrokerageService.addSubmit = addSubmit;
		rbibondTrailRunBrokerageService.verifySelect = verifySelect;
		rbibondTrailRunBrokerageService.deleteSubmit = deleteSubmit;
		rbibondTrailRunBrokerageService.verifySubmit = verifySubmit;
        rbibondTrailRunBrokerageService.transactionValidation=transactionValidation;
        rbibondTrailRunBrokerageService.getAgencyCodeAddTor=getAgencyCodeAddTor;
        rbibondTrailRunBrokerageService.getAgencyCodeVerTor=getAgencyCodeVerTor;
        rbibondTrailRunBrokerageService.getTrailRunVerDetail=getTrailRunVerDetail;
        rbibondTrailRunBrokerageService.download = download;

		        function getRbiBondModuleNameDetails(requestData){
                    return $http({
                        method: 'POST',
                        url: 'rbiBond/getModuleDetails',
                        data :requestData
                    })
              }

       function getRemarksList(requestData){
                               return $http({
                                    method:'POST',
                                    url:'rbiBond/getRemarksList',
                                    data:requestData
                               })
                  }

                function transactionValidation(requestData){
                       return	$http({
                           method : "POST",
                           url : 'gbmultra/rbiBond/trailRunBrokerage/tranValidation',
                           data :requestData
                       });
                    }

		        function getReferenceData(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'rbiBond/getReferenceData',
                       data :requestData
                   });
                }
              function getTrailRun(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/trailRunBrokerage/getTrailRun',
                       data :requestData
                   });
                }
                function getTrailRunVerDetail(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/trailRunBrokerage/getTrailRunVerDetail',
                                       data :requestData
                                   });
                                }
               function addSubmit(requestData){
                       return	$http({
                           method : "POST",
                           url : 'gbmultra/rbiBond/trailRunBrokerage/add',
                           data :requestData
                       });
                    }


           function getAgencyCodeAddTor(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/trailRunBrokerage/getAgencyCodeAddTor',
                   data :requestData
               });
            }
            function getAgencyCodeVerTor(requestData){
                           return	$http({
                               method : "POST",
                               url : 'gbmultra/rbiBond/trailRunBrokerage/getAgencyCodeVerTor',
                               data :requestData
                           });
                        }
          function verifySelect(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/trailRunBrokerage/verifySelect',
                   data :requestData
               });
            }

          function deleteSubmit(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/trailRunBrokerage/delete',
                   data :requestData
               });
            }

         function verifySubmit(requestData){
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/trailRunBrokerage/verify',
               data: requestData
           });
        }

        function download(requestData){
                                             return $http({
                                                 method : "POST",
                                                 url : 'gbmultra/rbiBond/trailRunBrokerage/download',
                                                 data : requestData
                                             })
                                         }




		}

		})();