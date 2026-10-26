(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'bondInwardOutwardService',
			bondInwardOutwardService);

	bondInwardOutwardService.$inject = [ '$http' ];

	function bondInwardOutwardService($http) {
		var bondInwardOutwardService = this;

		bondInwardOutwardService.addInward = addInward;
		bondInwardOutwardService.addOutward = addOutward;
		bondInwardOutwardService.getFtsDetails = getFtsDetails;
		bondInwardOutwardService.getRemarksList = getRemarksList;
		bondInwardOutwardService.deleteSubmit = deleteSubmit;

		bondInwardOutwardService.getMetaData = getMetaData
        bondInwardOutwardService.getPanTorchDetails=getPanTorchDetails;
        bondInwardOutwardService.getAddDetailsBla=getAddDetailsBla;
        bondInwardOutwardService.getAddDetailsSubsId=getAddDetailsSubsId;

        bondInwardOutwardService.inrRefNum=inrRefNum;
        bondInwardOutwardService.getAddInRefNo = getAddInRefNo;

   bondInwardOutwardService.getFtsFileService = function (ftsDetailFetch) {
      return $http({
        method: "POST",
        url: "external-service/fts-hcp-integrated-service?applicationNumber="+ftsDetailFetch
        });
    };
  function getFtsDetails(requestData)
                                                                {
                                                                   return	$http({
                                                                       method : "POST",
                                                                       url : '/external-service/fts/process-ci-data                                                                                                                                                                  ',
                                                                       data :requestData
                                                                   });
                                                                }

    function getMetaData(requestData)
    {
       return	$http({
           method : "POST",
           url : 'rbiBond/getReferenceData',
           data :requestData
       });
    }
                  function addInward(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/inwardOutward/addInward',
                                       data :requestData
                                   });
                                }
                 function getRemarksList(requestData){
                         return $http({
                              method:'POST',
                              url:'rbiBond/getRemarksList',
                              data:requestData
                         })
                        }
                function deleteSubmit(requestData)
                {
                   return $http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/inwardOutward/rejectOutward',
                       data :requestData
                   });
                }
                  function addOutward(requestData)
                                                  {
                                                     return	$http({
                                                         method : "POST",
                                                         url : 'gbmultra/rbiBond/inwardOutward/addOutward',
                                                         data :requestData
                                                     });
                                                  }
function getPanTorchDetails(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/inwardOutward/getPanNoTor',
                                       data :requestData
                                   });
                                }
function getAddDetailsBla(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/inwardOutward/getBlaNoTor',
                                       data :requestData
                                   });
                                }
 function getAddDetailsSubsId(requestData)
                                 {
                                    return	$http({
                                        method : "POST",
                                        url : 'gbmultra/rbiBond/inwardOutward/getBlaSubsIdTor',
                                        data :requestData
                                    });
                                 }


 function inrRefNum(requestData)
                                 {
                                    return	$http({
                                        method : "POST",
                                        url : 'gbmultra/rbiBond/inwardOutward/inrRefNum',
                                        data :requestData
                                    });
                                 }
  function getAddInRefNo(requestData)
                                   {
                                      return	$http({
                                          method : "POST",
                                          url : 'gbmultra/rbiBond/inwardOutward/getAddInRefNo',
                                          data :requestData
                                      });
                                   }

}
})();