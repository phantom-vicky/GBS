(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('certificateOfPrintingController',
        certificateOfPrintingController);

    certificateOfPrintingController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'certificateOfPrintingService', 'commonService'
    ];

    function certificateOfPrintingController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, certificateOfPrintingService, commonService) {

		var vm = this;
		vm.moduleName = 'RBI Bond';
//		vm.moduleId = '';


        vm.getModuleName = getModuleName;

        vm.getDetails = getDetails



		vm.solDate = $rootScope.globals.currentUser.systemDate;
		vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
		vm.subsDate1 = moment(vm.solDate).format("DD-MM-YYYY");
		vm.solDate1 = $rootScope.globals.currentUser.solDate;


        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc
        vm.today=new Date();
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
		vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;





     if($state.current.name == 'Certificate_Printing.inquiry_details'){
          if($stateParams.obj){
          vm.loader = true;

          vm.valueDate = $stateParams.obj.valueDate;
          vm.schemeName = $stateParams.obj.schemeName;
          vm.schemeCode = $stateParams.obj.schemeCode;

          vm.getDetails("T");

      }
     }

    vm.dateFromOptions = {
              maxDate: vm.today
          };

        function getModuleName() {
                            vm.loader = true;

                            var requestData = {
                            'flag':'C'
                            };
                            certificateOfPrintingService.getCpTotalDetails(requestData).then(function (response) {
                                vm.loader = false;
                                vm.schemeList = response.data.result;
                                angular.element('#schemeModel').modal('toggle');
                            }, function (error) {
                                handleErrorResponse(error);
                                vm.loader = false;
                            });
                        }




        function getDetails(flag){
                          vm.loader = true;

                          if(flag =='T'){
                          var requestData = {

                                     'schemeCode' : vm.schemeCode,
                                      'valueDate' : $filter('date')(vm.valueDate, "dd-MMM-yyyy"),
                                     'flag'      : flag,

                                    };

                                  }

                                    else{

                                       var requestData = {

                                                                         'schemeCode' : vm.schemeCode,
                                                                         'schemeName' : vm.schemeName,



                                                                           'valueDate' : $filter('date')(vm.valueDate, "dd-MMM-yyyy"),
                                                                            'valueDateFormat' : $filter('date')(vm.valueDate, "dd-MM-yyyy"),


                                                                         'flag'      : flag,

                                                                        };
//
                                  }
                          certificateOfPrintingService.getCpTotalDetails(requestData).then(function (response) {
                                vm.loader = false;
                                if(flag=='T'){
                          vm.dataList = response.data.total;
                          }
                          else if(flag=='D'){
                           vm.fileData = response.data.result.fileData;
                                                  vm.fileName = response.data.result.fileName;
                                                  saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)


                                                  if(vm.fileName!= null){

                                                    Swal.fire("Downloded!", "File Downloded Successfully", "success");
                                                  }
                                              }


                                     else{

                                       Swal.fire("Uploded!", "File Uploded Successfully in Sftp", "success");
                                     }


                          }, function (error) {
                                                        vm.loader = false;
                              handleErrorResponse(error);
                                                    $state.go('Certificate_Printing.inquiry');

                          });
                         }












                    vm.textableDatePicker=textableDatePicker;
                            vm.autoslashing=autoslashing;
                            vm.futureNotAllowed=futureNotAllowed;


                    function textableDatePicker(id,valueDate)
                    {
                     commonService.textableDatePicker(id,valueDate);
                    }


                    function autoslashing(id)

                    {
                     commonService.autoslashing(id)
                    ;
                    }



                    function futureNotAllowed(id,valueDate)
                    {
                     commonService.futureNotAllowed(id,valueDate);
                    }

              }




       })();