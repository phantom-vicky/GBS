(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondMinorToMajorIntimationNoticeController',
        bondMinorToMajorIntimationNoticeController);

    bondMinorToMajorIntimationNoticeController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'bondMinorToMajorIntimationNoticeService', 'commonService'
    ];

    function bondMinorToMajorIntimationNoticeController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, bondMinorToMajorIntimationNoticeService, commonService) {

		var vm = this;
		vm.moduleName = 'RBI Bond';
//		vm.moduleId = '';

vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;

function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
}
function autoslashing(id)
{
 commonService.autoslashing(id);
}




		vm.solDate = $rootScope.globals.currentUser.systemDate;
		vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
		vm.subsDate1 = moment(vm.solDate).format("DD-MM-YYYY");
		vm.solDate1 = $rootScope.globals.currentUser.solDate;
        vm.getModuleName = getModuleName;

        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc
        vm.today=new Date();
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
		vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.fromToDateValidation = fromToDateValidation;
        vm.getAddDetails =  getAddDetails;
        vm.printpage = printpage;
        vm.alert=alert;


     if($state.current.name == 'rbiBond_minor_major_intimation_notice.inquiry_detail'){
          if($stateParams.obj){
          vm.loader = true;
          vm.dataList=[];
          vm.fromDate = $stateParams.obj.fromDate;
          vm.schemeName = $stateParams.obj.schemeName;
          vm.schemeCode = $stateParams.obj.schemeCode;
          vm.toDate = $stateParams.obj.toDate;
          vm.blaNo = $stateParams.obj.blaNo;
          vm.getAddDetails('det');
          console.log(vm.dataList.length);
      }
     }


        function getModuleName() {
                vm.loader = true;
                vm.referenceId = 'TAX';
                var requestData = {
                'referenceId': vm.referenceId
                };
               bondMinorToMajorIntimationNoticeService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                   vm.loader = false;
                   vm.schemeList = response.data.result;
                      angular.element("#schemeModel").modal("toggle");
               }, function (error) {
                   handleErrorResponse(error);
                   vm.loader = false;
               });
         }


 function alert(item){

                                 for (let i = 0 ;i < vm.schemeList.length;i++){
                                  if(vm.schemeList[i].subCode == item){
                                        vm.schemeName = vm.schemeList[i].subCodeDescription;
                                   }
                                 }
                              }
              function getAddDetails(flag){
                  vm.loader = true;

                 if(flag == "bla"){
                  var requestData = {
                      'blaFlag' : 'B',
                      'moduleId' : vm.schemeCode,
                      'blaNo' : null,
                      'fromDate' : $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                      'toDate' : $filter('date')(vm.toDate, "dd-MMM-yyyy"),

                  };
                  bondMinorToMajorIntimationNoticeService.getAddDetails(requestData).then(function (response) {
                      vm.loader = false;
                      vm.blaDataList = response.data.result;
                      angular.element('#blaModel').modal('toggle');

                  }, function (error) {
                      handleErrorResponse(error);
                      vm.loader = false;
                  });
                 }

                if(flag == "det"){

                     var requestData = {
                       'blaFlag' : 'D',
                       'moduleId' : vm.schemeCode,
                       'blaNo'    : vm.blaNo,
                       'fromDate' : $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                       'toDate'   : $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                      };
                     bondMinorToMajorIntimationNoticeService.getAddDetails(requestData).then(function (response) {


                     vm.dataList = response.data.result;
//                                 $state.go('rbiBond_minor_major_intimation_notice.inquiry_detail');
                     vm.loader = false;

                      }, function (error) {
                        if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                                      vm.noDataFound = error.data.message;
                                }
                                else{
                           handleErrorResponse(error);
                           $state.go('rbiBond_minor_major_intimation_notice.inquiry')
                           vm.loader = false;
                           }
                         });
                }

      }

      vm.fromStateCodeToDesc = fromStateCodeToDesc;

      function fromStateCodeToDesc(code) {
        if (code != null) {

          if (code == "S037") { return "ANDHRA PRADESH"; }
          if (code == "S012") { return "ARUNACHAL PRADESH"; }
          if (code == "S018") { return "ASSAM"; }
          if (code == "S010") { return "BIHAR"; }
          if (code == "S035") { return "ANDAMAN AND NICOBAR"; }
          if (code == "S004") { return "CHANDIGARH"; }
          if (code == "S026") { return "DADRA AND NAGAR HAVELI"; }
          if (code == "S025") { return "DAMAN AND DIU"; }
          if (code == "S007") { return "DELHI"; }
          if (code == "S030") { return "GOA"; }
          if (code == "S024") { return "GUJARAT"; }
          if (code == "S006") { return "HARYANA"; }
          if (code == "S002") { return "HIMACHAL PRADESH"; }
          if (code == "S001") { return "JAMMU AND KASHMIR"; }
          if (code == "S029") { return "KARNATAKA"; }
          if (code == "S032") { return "KERALA"; }
          if (code == "S031") { return "LAKSHADWEEP"; }
          if (code == "S023") { return "MADHYA PRADESH"; }
          if (code == "S027") { return "MAHARASHTRA"; }
          if (code == "S014") { return "MANIPUR"; }
          if (code == "S017") { return "MEGHALAYA"; }
          if (code == "S015") { return "MIZORAM"; }
          if (code == "S013") { return "NAGALAND"; }
          if (code == "S021") { return "ORISSA"; }
          if (code == "S034") { return "PUDUCHERRY"; }
          if (code == "S003") { return "PUNJAB"; }
          if (code == "S008") { return "RAJASTHAN"; }
          if (code == "S011") { return "SIKKIM"; }
          if (code == "S033") { return "TAMIL NADU"; }
          if (code == "S016") { return "TRIPURA"; }
          if (code == "S005") { return "UTTARAKHAND"; }
          if (code == "S009") { return "UTTAR PRADESH"; }
          if (code == "S019") { return "WEST BENGAL"; }
          if (code == "S022") { return "CHHATTISGARH"; }
          if (code == "S020") { return "JHARKHAND"; }
          if (code == "S097") { return "OTHER TERRITORY"; }
          if (code == "S096") { return "OTHER COUNTRY"; }

        }
        return "";
      }


       function printpage(){
                    window.print()
//                            var filename = 'default_name.pdf';
//                            var downloadLink = document.getElementById('downloadLink');
//                            downloadLink.href = 'data:application/pdf;utf-8,' + escape('');
//                            downloadLink.download = filename;
//                            downloadLink.click();
                  }

        function fromToDateValidation() {
            vm.toDate = '';
            vm.dateToOptions = {
                minDate: vm.fromDate,

            };
             vm.dateOptions = {
                maxDate: vm.solDate,
            }
        }




       } })();