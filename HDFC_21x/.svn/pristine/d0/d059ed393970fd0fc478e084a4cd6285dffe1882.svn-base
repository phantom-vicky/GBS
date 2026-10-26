angular.module('gbmUltraApp').service('rbiBondLevelMasterMappingService', function($http, $rootScope) {

 var rbiBondLevelMasterMappingService = {};
 rbiBondLevelMasterMappingService.fetchLevelID = fetchLevelID;
 rbiBondLevelMasterMappingService.fetchParentID = fetchParentID;
 rbiBondLevelMasterMappingService.fetchParentSolIDList = fetchParentSolIDList;
 rbiBondLevelMasterMappingService.fetchParentLevelIDList = fetchParentLevelIDList;

 rbiBondLevelMasterMappingService.fetchParentSolIDListModify = fetchParentSolIDListModify;
 rbiBondLevelMasterMappingService.mappedSolIds = mappedSolIds;
 rbiBondLevelMasterMappingService.fetchSolDetails = fetchSolDetails;
 rbiBondLevelMasterMappingService.fetchSolDetailsVerify = fetchSolDetailsVerify;
 rbiBondLevelMasterMappingService.fetchSolDetailsInquiry = fetchSolDetailsInquiry;

 rbiBondLevelMasterMappingService.inquiryLevelMasterMap = inquiryLevelMasterMap;
 rbiBondLevelMasterMappingService.insertLevelMasterMap = insertLevelMasterMap;
 rbiBondLevelMasterMappingService.levelMasterInsert = levelMasterInsert;
 rbiBondLevelMasterMappingService.verifySubmitForm = verifySubmitForm;
 rbiBondLevelMasterMappingService.submitModifyForm = submitModifyForm;
// rbiBondLevelMasterMappingService.deleteSubmit = deleteSubmit;
 rbiBondLevelMasterMappingService.getTreasuryCodesModify = getTreasuryCodesModify;
 rbiBondLevelMasterMappingService.getTreasuryCodesVerify = getTreasuryCodesVerify;
 rbiBondLevelMasterMappingService.getModuleDetails = getModuleDetails;
 rbiBondLevelMasterMappingService.deleteDetailsSubmit = deleteDetailsSubmit;

     /* Get Module Details */
     function getModuleDetails(requestData)
     {
        return	$http({
            method : "POST",
            url : 'rbiBond/getModuleDetails',
            data :requestData
        });
     }

 //New
 rbiBondLevelMasterMappingService.getAddDetails = getAddDetails;
 /* Insert record into table by tableName  */
 function levelMasterInsert(postData) {
  return $http({
   method: 'post',
   url: "gbmultra/dc/rbiBond/levelMaster/mapping/add",
   headers: {
    'Content-Type': 'application/json'
   },
   data: postData
  })
 }


 function fetchLevelID(trsyCode) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/treasuryDetails?trsyCode=' + trsyCode,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }

 function insertLevelMasterMap(trsyCode, levelId, parentLevelId, parentSolId) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/insertmappedSols?trsyCode=' + trsyCode + '&levelId=' + levelId + '&parentLevelId=' + parentLevelId + '&parentSolId=' + parentSolId,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }

 function fetchSolDetails(trsyCode, levelId, parentLevelId, parentSolId) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/unmappedsolsforadd?trsyCode=' + trsyCode + '&levelId=' + levelId + '&parentLevelId=' + parentLevelId + '&parentSolId=' + parentSolId,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }

 function fetchSolDetailsVerify(trsyCode, levelId, parentLevelId, parentSolId) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/mappedSols?trsyCode=' + trsyCode + '&levelId=' + levelId + '&parentLevelId=' + parentLevelId + '&parentSolId=' + parentSolId,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }

 function fetchSolDetailsInquiry(trsyCode, levelId, parentLevelId, parentSolId) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/inquirymappedSols?trsyCode=' + trsyCode + '&levelId=' + levelId + '&parentLevelId=' + parentLevelId + '&parentSolId=' + parentSolId,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }

 function fetchParentID(trsyCode) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/treasuryDetails?trsyCode=' + trsyCode,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }


 function fetchParentSolIDList(trsyCode, parentId) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/parentSolIdList?trsyCode=' + trsyCode + '&levelId=' + parentId,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }


 function fetchParentSolIDListModify(trsyCode, levelId, parentLevelId) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/parentSolIdListModify?trsyCode=' + trsyCode + '&levelId=' + levelId + '&parentLevelId=' + parentLevelId,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }


 function mappedSolIds(trsyCode, levelId, parentLevelId, parentSolId) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/mappedSols?trsyCode=' + trsyCode + '&levelId=' + levelId + '&parentLevelId=' + parentLevelId + '&parentSolId=' + parentSolId,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }



 function fetchParentLevelIDList(trsyCode, levelId) {
  return $http({
   method: 'get',
   url: 'gbmultra/pension/levelmaster/parentLevelIdList?trsyCode=' + trsyCode + '&levelId=' + levelId,
   headers: {
    'Content-Type': 'application/json'
   },
  })
 }



 //Get Add Input Screen Details
 function getAddDetails(postData) {
  return $http({
   method: 'POST',
   url: "gbmultra/dc/rbiBond/levelMaster/mapping/getAddDetails",
   headers: {
    'Content-Type': 'application/json'
   },
   data: postData
  })
 }
 //Get Modify Screen Details
 function getTreasuryCodesModify(postData) {
  return $http({
   method: 'POST',
   url: 'gbmultra/dc/rbiBond/levelMaster/mapping/getModifyDetails',
   headers: {
    'Content-Type': 'application/json'
   },
   data: postData
  })
 }
 //Modify Submit
 function submitModifyForm(postData) {
  return $http({
   method: 'POST',
   url: "gbmultra/dc/rbiBond/levelMaster/mapping/modify",
   headers: {
    'Content-Type': 'application/json'
   },
   data: postData
  })
 }
 //Get Verify Details
 function getTreasuryCodesVerify(postData) {
  return $http({
   method: 'POST',
   url: 'gbmultra/dc/rbiBond/levelMaster/mapping/getVerifyDeleteDetails',
   headers: {
    'Content-Type': 'application/json'
   },
   data: postData
  })
 }
 //Verify Submit
 function verifySubmitForm(postData) {
  return $http({
   method: 'POST',
   url: "gbmultra/dc/rbiBond/levelMaster/mapping/verify",
   headers: {
    'Content-Type': 'application/json'
   },
   data: postData
  })
 }
  //DELETE Submit
  function deleteDetailsSubmit(requestData) {
   return $http({
    method: 'POST',
    url: "gbmultra/dc/rbiBond/levelMaster/mapping/delete",
    headers: {
     'Content-Type': 'application/json'
    },
    data: requestData
   })
  }
 //Get Inquiry Screen Details
 function inquiryLevelMasterMap(postData) {
  return $http({
   method: 'POST',
   url: 'gbmultra/dc/rbiBond/levelMaster/mapping/inquiry',
   headers: {
    'Content-Type': 'application/json'
   },
   data: postData
  })
 }

 return rbiBondLevelMasterMappingService;

});