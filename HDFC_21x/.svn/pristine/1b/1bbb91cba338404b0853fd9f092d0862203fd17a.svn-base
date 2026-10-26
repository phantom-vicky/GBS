(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('userParamsService', userParamsService);

    userParamsService.$inject = ['$http'];

    function userParamsService($http) {

        var userParamsService = this;
        userParamsService.getUserParams = getUserParams;
        userParamsService.saveUserParams = saveUserParams;

        userParamsService.verifySubmit = verifySubmit;
        userParamsService.deleteSubmit = deleteSubmit;
        userParamsService.getModifiedParams = getModifiedParams;
        userParamsService.getverDelParam = getverDelParam;




        function getUserParams() {          
            return $http({
                method: "POST",
                url: "gbmultra/admin/userparams/getUserParams"
            });
        }

       function getModifiedParams() {
                    return $http({
                        method: "POST",
                        url: "gbmultra/admin/userparams/getModifiedParams",

                  });
       }

         function getverDelParam() {
                   return $http({
                       method: "POST",
                       url: "gbmultra/admin/userparams/getverDetailsParams"
                   });
               }



       function saveUserParams(requestData)
                        {
                           return	$http({
                           method : "POST",
                           url : 'gbmultra/admin/userparams/modifyUserParams',
                           data :requestData
                           });
                        }


         function verifySubmit(){
                     return $http({
                        method: 'POST',
                        url: "gbmultra/admin/userparams/verifyUserParams",

                               });
                        }

function deleteSubmit(requestData) {
    return $http({
        method: "POST",
        url: "gbmultra/admin/userparams/deleteUserParams",
        data: requestData
    });
}


  userParamsService.getRemarksList = function (requestData) {
                            return $http({
                              method: "POST",
                              url: "rbiBond/getRemarksList",
                              data: requestData,
                            });
                          };

    }

})();