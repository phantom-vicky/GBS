(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'cumToNonCumChangeOfBondTypeService',
			cumToNonCumChangeOfBondTypeService);

	cumToNonCumChangeOfBondTypeService.$inject = [ '$http' ];

	function cumToNonCumChangeOfBondTypeService($http) {
		var cumToNonCumChangeOfBondTypeService = this;
		cumToNonCumChangeOfBondTypeService.getReferenceData = getReferenceData;
		cumToNonCumChangeOfBondTypeService.addSubmit = addSubmit;
//		cumToNonCumChangeOfBondTypeService.getTdsApplecableRate = getTdsApplecableRate;
		cumToNonCumChangeOfBondTypeService.fetchCbsCustId = fetchCbsCustId;
		cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetailsfetchBlaAccNoDetails = fetchBlaAccNoDetails;
		cumToNonCumChangeOfBondTypeService.fetchGuardianDetails = fetchGuardianDetails;
		cumToNonCumChangeOfBondTypeService.getCommonMetaDatList = getCommonMetaDatList;
		cumToNonCumChangeOfBondTypeService.modifySelect = modifySelect;
		cumToNonCumChangeOfBondTypeService.modifySubmit = modifySubmit;
		cumToNonCumChangeOfBondTypeService.verifySelect = verifySelect;
		cumToNonCumChangeOfBondTypeService.verifySubmit = verifySubmit;
		cumToNonCumChangeOfBondTypeService.submitVerifyForm = submitVerifyForm;
				cumToNonCumChangeOfBondTypeService.getsubscriptionIdVer = getsubscriptionIdVer;
						cumToNonCumChangeOfBondTypeService.getblaNoVer = getblaNoVer;
								cumToNonCumChangeOfBondTypeService.getRbiBondModuleNameDetailsVer = getRbiBondModuleNameDetailsVer;
										cumToNonCumChangeOfBondTypeService.getPantorchVer = getPantorchVer;
		cumToNonCumChangeOfBondTypeService.inquirySelect = inquirySelect;
		cumToNonCumChangeOfBondTypeService.addDet = addDet;
		cumToNonCumChangeOfBondTypeService.addcustomerMaster = addcustomerMaster;
		cumToNonCumChangeOfBondTypeService.blaTorch = blaTorch;
		cumToNonCumChangeOfBondTypeService.getPantorch = getPantorch;
				cumToNonCumChangeOfBondTypeService.getPantorchInq = getPantorchInq;
		cumToNonCumChangeOfBondTypeService.modifyPanTorch = modifyPanTorch;
		cumToNonCumChangeOfBondTypeService.getBrokerTorch = getBrokerTorch;
		cumToNonCumChangeOfBondTypeService.deleteSubmit = deleteSubmit;
		cumToNonCumChangeOfBondTypeService.getAddDashBoard = getAddDashBoard;
		cumToNonCumChangeOfBondTypeService.validatePan = validatePan;
		cumToNonCumChangeOfBondTypeService.blaNo = blaNo;
		cumToNonCumChangeOfBondTypeService.getblaNoInq = getblaNoInq;
		cumToNonCumChangeOfBondTypeService.getsubscriptionIdMod = getsubscriptionIdMod;
		cumToNonCumChangeOfBondTypeService.getsubscriptionIdInq = getsubscriptionIdInq;
				cumToNonCumChangeOfBondTypeService.getsubscriptionId = getsubscriptionId;
		cumToNonCumChangeOfBondTypeService.getFtsDetails = getFtsDetails;
		cumToNonCumChangeOfBondTypeService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		cumToNonCumChangeOfBondTypeService.getRbiBondModuleNameDetailsInq = getRbiBondModuleNameDetailsInq;
    cumToNonCumChangeOfBondTypeService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };



        function getRbiBondModuleNameDetails(requestData){
                   return $http({
                       method: 'POST',
                       url: 'gbmultra/rbiBond/cumToNonCumChange/getModuleDetails',
                       data :requestData
                   })
             }
        function getRbiBondModuleNameDetailsInq(requestData){
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/cumToNonCumChange/getModuleDetailsInq',
                        data :requestData
                    })
              }
                function getRbiBondModuleNameDetailsVer(requestData){
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/cumToNonCumChange/getModuleDetailsVer',
                                data :requestData
                            })
                      }
        function getPantorch(requestData){
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/cumToNonCumChange/getPantorch',
                data :requestData
            })
      }
              function getPantorchInq(requestData){
                  return $http({
                      method: 'POST',
                      url: 'gbmultra/rbiBond/cumToNonCumChange/getPantorchInq',
                      data :requestData
                  })
            }
                          function getPantorchVer(requestData){
                              return $http({
                                  method: 'POST',
                                  url: 'gbmultra/rbiBond/cumToNonCumChange/getPantorchVer',
                                  data :requestData
                              })
                        }

		        function getReferenceData(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'rbiBond/getReferenceData',
                       data :requestData
                   });
                }

                   function validatePan(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'external-service/validatePan/validate-pan                                                                                                                                                                  ',
                                       data :requestData
                                   });
                                }

                                   function getFtsDetails(requestData)
                                                                {
                                                                   return	$http({
                                                                       method : "POST",
                                                                       url : '/external-service/fts/process-ci-data                                                                                                                                                                  ',
                                                                       data :requestData
                                                                   });
                                                                }

  function getBrokerTorch(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/getBrokerDetails',
                       data :requestData
                   });
                }



                  function addDet(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/cumToNonCumChange/addVal',
                                       data :requestData
                                   });
                                }
                  function addcustomerMaster(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/cumToNonCumChange/addcustomerMaster',
                                       data :requestData
                                   });
                                }

                function addSubmit(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/add',
                       data :requestData
                   });
                }

        function blaNo(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/blaNo',
                       data :requestData
                   });
                }

                function getblaNoInq(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/cumToNonCumChange/getblaNoInq',
                                       data :requestData
                                   });
                                }
                function getblaNoVer(requestData)
                                                {
                                                   return	$http({
                                                       method : "POST",
                                                       url : 'gbmultra/rbiBond/cumToNonCumChange/getblaNoVer',
                                                       data :requestData
                                                   });
                                                }
                                    function getsubscriptionIdMod(requestData)
                                                                {
                                                                   return	$http({
                                                                       method : "POST",
                                                                       url : 'gbmultra/rbiBond/cumToNonCumChange/getsubscriptionIdMod',
                                                                       data :requestData
                                                                   });
                                                                }

                                        function getsubscriptionIdInq(requestData)
                                                                {
                                                                   return	$http({
                                                                       method : "POST",
                                                                       url : 'gbmultra/rbiBond/cumToNonCumChange/getsubscriptionIdInq',
                                                                       data :requestData
                                                                   });
                                                                }

                                        function getsubscriptionId(requestData)
                                                                {
                                                                   return	$http({
                                                                       method : "POST",
                                                                       url : 'gbmultra/rbiBond/cumToNonCumChange/getsubscriptionId',
                                                                       data :requestData
                                                                   });
                                                                }
                  function getsubscriptionIdVer(requestData)
                                                                                {
                                                                                   return	$http({
                                                                                       method : "POST",
                                                                                       url : 'gbmultra/rbiBond/cumToNonCumChange/getsubscriptionIdVer',
                                                                                       data :requestData
                                                                                   });
                                                                                }
                function blaTorch(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/blaTorch',
                       data :requestData
                   });
                }

                function modifyPanTorch(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/cumToNonCumChange/ModifyPanTorch',
                                       data :requestData
                                   });
                                }
                function getAddDashBoard(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/getAddDashBoard',
                       data: requestData
                   });
                }
                function fetchCbsCustId(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/getBcifApi',
                       data: requestData
                   });
                }
                function fetchBlaAccNoDetails(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/getBlaNumDetails',
                       data: requestData
                   });
                }
                function fetchGuardianDetails(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/getGuardianDetails',
                       data: requestData
                   });
                }
                function getCommonMetaDatList(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : '/rbiBond/getReferenceData',
                       data: requestData
                   });
                }
                //Modify Select
                function modifySelect(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/modifySelect',
                       data :requestData
                   });
                }
                //Modify submit
                function modifySubmit(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/modify',
                       data :requestData
                   });
                }
                //Verify Select
                function verifySelect(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/verifySelect',
                       data :requestData
                   });
                }
                //Verify submit
                function verifySubmit(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/verify',
                       data :requestData
                   });
                }
                //Delete submit
                function submitVerifyForm(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/submitVerifyForm',
                       data :requestData
                   });
                }
                //Inquiry submit
                function inquirySelect(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/cumToNonCumChange/inquiry',
                       data :requestData
                   });
                }
                /* getValidations */
                function deleteSubmit(requestData) {
                    return $http({
                         method: "POST",
                         url : 'gbmultra/rbiBond/cumToNonCumChange/deleteSubmit',
                         data: requestData
                    });
                }

		}

		})();