(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('systemParameterService', systemParameterService);

    systemParameterService.$inject = ['$http'];

    function systemParameterService($http) {
    	
    	var systemParameterService = this;
    	systemParameterService.addsystemParameter		= addsystemParameter;
    	systemParameterService.getSystemParameters		= getSystemParameters;
    	systemParameterService.getSystemParameterDetails= getSystemParameterDetails;
    	systemParameterService.verifysystemParameter	= verifysystemParameter;
    	systemParameterService.getSysParamModuleList    = getSysParamModuleList;
    	systemParameterService.getCommonDetailsList     =getCommonDetailsList;
    	systemParameterService.getCommonList            =getCommonList;

        function addsystemParameter(data) {
            var parameter = JSON.stringify(data);

            return $http({
                method: "post",
                url: 'gbmultra/nps/sysparams/updateSysParam',
                header: {
                	"Content-Type" : "application/json"
                },
                params: {}, data: parameter
            });
        }
        
        function verifysystemParameter(data) {
            var parameter = JSON.stringify(data);

            return $http({
                method: "post",
                url: 'gbmultra/nps/sysparams/verify',
                header: {
                	"Content-Type" : "application/json"
                },
                params: {}, data: parameter
            });
        }
        
        function getSystemParameters(){
        	return $http({
                method: "get",
                url: 'gbmultra/nps/sysparams/getSysParamList',
                params: {}
            });
        }
        
        function getSystemParameterDetails(){
        	return $http({
                method: "get",
                url: 'gbmultra/nps/sysparams/getSysParmDetailsList',
                params: {}
            });
        }

        function getSysParamModuleList(moduleId){
        return $http({
                method:"get",
                url:'gbmultra/nps/sysparams/getSysParamModuleList',
                params:{
                        moduleId:moduleId
                        }
        });
        }

         function getCommonDetailsList(){
                	return $http({
                        method: "get",
                        url: 'gbmultra/nps/sysparams/getCommonDetailsList',
                        params: {}
                    });
                }

    	 function getCommonList(){
                        	return $http({
                                method: "get",
                                url: 'gbmultra/nps/sysparams/getCommonList',
                                params: {}
                            });
                        }
    }
})();