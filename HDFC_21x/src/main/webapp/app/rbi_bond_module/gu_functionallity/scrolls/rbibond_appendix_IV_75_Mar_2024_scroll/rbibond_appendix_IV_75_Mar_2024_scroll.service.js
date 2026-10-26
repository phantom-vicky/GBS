(function () {
 "use strict";

 angular.module("gbmUltraApp").service("AppendixIV75MarchService", AppendixIV75MarchService);

 AppendixIV75MarchService.$inject= ["$http"];

 function AppendixIV75MarchService($http) {
  var AppendixIV75MarchService= this;

  AppendixIV75MarchService.getAppendixDetails= function(requestData) {
    return $http({
        method: "POST",
        url   : "gbmultra/rbibond/scrolls/getAppendix75Details",
        data  : requestData,
    });
  }
 }
})();