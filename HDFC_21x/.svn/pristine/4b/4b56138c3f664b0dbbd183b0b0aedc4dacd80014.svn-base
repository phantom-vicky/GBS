(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("preIntimationAdviceNoticeController", preIntimationAdviceNoticeController);

  preIntimationAdviceNoticeController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "preIntimationAdviceNoticeService", "commonService"];

  function preIntimationAdviceNoticeController($scope, $filter, $state, $stateParams, $location, $rootScope, preIntimationAdviceNoticeService, commonService) {
    var vm = this;
    vm.getDetails = getDetails;
    vm.solDate = $rootScope.globals.currentUser.systemDate;

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };

      preIntimationAdviceNoticeService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

vm.today = new Date();
vm.showLetter = false;

vm.download = function (blaNumber, panNumber) {

    vm.loader = true;

    var requestData = {

        schemeCode: vm.schemeCode,

        blaNumber: blaNumber,

        panNumber: panNumber,

        flag: "T"

    };

    preIntimationAdviceNoticeService.getDetails(requestData).then(

        function (response) {

            vm.loader = false;


            vm.printRecords = [response.data.result.list[0]];

            angular.forEach(vm.printRecords, function(item){

                item.investmentdate = new Date(item.investmentdate);

                item.maturityDate = new Date(item.maturityDate);

            });

            vm.formattedSchemeName = vm.schemeName
                ? vm.schemeName.toLowerCase().replace(/\b\w/g, function(letter) {
                    return letter.toUpperCase();
                })
                : "";

            vm.showLetter = true;

        },

        function (error) {

            vm.loader = false;

            handleErrorResponse(error);

        }

    );

};



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


vm.indianNumberFormat=indianNumberFormat;
                    function indianNumberFormat(value) {
                      if (isNaN(value)|| null == value) return value;
                      let numStr = value.toString();
                      let [whole, decimal] = numStr.split('.');
                      if (whole.length <= 3) {
                          return decimal ? whole + '.' + decimal : whole + '.00';
                        }
                      let lastThree = whole.slice(-3);  // last three digits
                      let otherNumbers = whole.slice(0, whole.length - 3);
                      if (otherNumbers !== '') {
                        lastThree = ',' + lastThree;
                      }
                      let formattedWhole = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ',') + lastThree;
                    if (decimal) {
                        return formattedWhole + '.' + decimal;
                      } else {
                        return formattedWhole+'.00';
                      }
                    }



vm.printPage = function ($event) {

    if ($event) {
        $event.preventDefault();
    }




    if (vm.showLetter) {

        setTimeout(function () {

            window.print();

            $scope.$applyAsync(function () {


            });

        }, 800);

        return;
    }


    vm.loader = true;

    var requestData = {

        schemeCode: vm.schemeCode,

        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),

        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),

        flag: "D1"

    };

    preIntimationAdviceNoticeService.getDetails(requestData).then(

        function (response) {

            vm.loader = false;

            vm.printRecords = response.data.result.list;

            angular.forEach(vm.printRecords, function(item){

                item.investmentdate = new Date(item.investmentdate);

                item.maturityDate = new Date(item.maturityDate);

            });

            vm.formattedSchemeName = vm.schemeName
                ? vm.schemeName.toLowerCase().replace(/\b\w/g, function(letter) {
                    return letter.toUpperCase();
                })
                : "";

            vm.showLetter = true;

           setTimeout(function () {

               window.print();

               $scope.$applyAsync(function () {

                   vm.showLetter = false;

               });

           }, 800);

        },

        function (error) {

            vm.loader = false;

            handleErrorResponse(error);

        }

    );

};

vm.goBack = function () {
    if (vm.showLetter) {
        $scope.$applyAsync(function () {
            vm.showLetter = false;
        });
    }
    else {
        $location.path('/Pre_Intimation_Advice_Notice_Maturity_Cases/action/inquiry');
    }
};

vm.goBackToList = vm.goBack;

    function getDetails() {
      vm.loader = true;
      if(vm.fromDate && vm.toDate){
       var requestData = {
              schemeCode: vm.schemeCode,
              fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
              toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
              flag: "D",
            };

      }
        else if(vm.blaNumber && vm.nameOfHolder &&  vm.panNumber){
         var requestData = {
                  blaNumber:vm.blaNumber,
                  nameOfHolder:vm.nameOfHolder,
                 panNumber:vm.panNumber,
                  flag:"T"
                  }
                  }


      preIntimationAdviceNoticeService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Pre_Intimation_Advice_Notice_Maturity_Cases.inquiry");
          }
        }
      );
    }


      if ($state.current.name == "Pre_Intimation_Advice_Notice_Maturity_Cases.inquiry_details") {
          vm.schemeCode = $stateParams.obj.schemeCode;
                   vm.schemeName = $stateParams.obj.schemeName;
                   vm.fromDate = $stateParams.obj.fromDate;
                   vm.toDate = $stateParams.obj.toDate;
          vm.schemeName = $stateParams.obj.schemeName;

        vm.blaNumber = $stateParams.obj.blaNumber;
                              vm. nameOfHolder = $stateParams.obj.nameOfHolder;
                                   vm.panNumber= $stateParams.obj.panNumber ;
          if (vm.schemeCode == "0012") {
            vm.schemeName = "Floating Rate Savings Bonds, 2020 (Taxable)";
          }

            getDetails();


        };




vm.fromDateOptions = {
    startingDay: 1
};

vm.toDateOptions = {
    startingDay: 1,
    minDate: null
};

vm.setToDateValidation = function () {
    vm.toDate = '';
    vm.toDateOptions.minDate = vm.fromDate;
};

vm.validateToDate = function () {

    if (vm.toDate && vm.fromDate) {

        var from = new Date(vm.fromDate);
        var to = new Date(vm.toDate);

        if (to < from) {

            alert("To Date should be greater than or equal to From Date");

            vm.toDate = '';
        }
    }
};
    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };

    vm.toDateVal = function (fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: new Date(),
        showWeeks: false,
      };
    };
  }
})();
