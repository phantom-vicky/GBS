(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("rbibondCertificateInvtController", rbibondCertificateInvtController);

  rbibondCertificateInvtController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "rbibondCertificateInvtService", "commonService"];

  function rbibondCertificateInvtController($scope, $filter, $state, $stateParams, $location, $rootScope, rbibondCertificateInvtService, commonService) {
    var vm = this;

    vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.currentDate = new Date();
    vm.formatIndianCurrency = formatIndianCurrency;
    vm.currentDate = $filter("date")(vm.currentDate, "dd/MM/yyyy");
    vm.getCertificateDetail = getCertificateDetail;
    vm.getModuleName = getModuleName;
    vm.getPanNum = getPanNum;
    vm.printpage = printpage;
    vm.getSubscriptionId = getSubscriptionId;
    vm.dateValidation = dateValidation;
vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;
vm.convertNumberToWords = convertNumberToWords;

function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
 if (id === 'effToDate') {
  if (!valueDate || isNaN(new Date(valueDate))) {
         vm.toDate = null;
         swal("Missing Date!", "Please select a valid date.", "warning");
         return false;
       }else if(!isWithin7Days(valueDate)){
        vm.toDate = null;
      swal("Invalid Date!", "To Date cannot be more than 7 days after the From Date.", "error");
        return false;
       }
    }
   return true;
}
function isWithin7Days(valueDate){
if (!valueDate || !vm.fromDate) {
     return false;
   }
   const fromDate = new Date(vm.fromDate);
   const valDate = new Date(valueDate);
    const diffTime = valDate.getTime() - fromDate.getTime();
    const diffDays = diffTime / (1000 * 60 * 60 * 24);
    return diffDays >= 0 && diffDays <= 7;
}
function autoslashing(id)
{
 commonService.autoslashing(id);
}




    if ($state.current.name == "rbibond_certificate_of_investment_report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.name = $stateParams.obj.name;
      vm.panNo = $stateParams.obj.panNo;
      vm.blaNo = $stateParams.obj.blaNo;
      vm.subscriptionId = $stateParams.obj.subscriptionId;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      if (vm.schemeCode == "0012") {
        vm.schemeName = "Floating Rate Savings Bonds, 2020 (Taxable)";
      }

      getCertificateDetail();
    }

    function dateValidation() {
      vm.today = new Date();
      var maxToDate = new Date(vm.fromDate);
      maxToDate.setDate(maxToDate.getDate() + 7);
      vm.toDate = "";
      vm.dateToOptions = {
        minDate: vm.fromDate,
        maxDate: maxToDate,
        showWeeks: false,
      };
      vm.dateOptions = {
        maxDate: vm.today,
      };
      if (vm.toDate && vm.toDate > maxToDate) {
                    vm.toDate = null;
                }
    }

    function configDataTable() {
      vm.getTitleDescription = {
        title: "Certificate of Investment",
        solId: $rootScope.globals.currentUser.solId,
        solDesc: $rootScope.globals.currentUser.solDesc,
      };
      vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
    }

    function getModuleName() {
      vm.loader = true;

      var requestData = {};
      rbibondCertificateInvtService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.bondCertificateInvtReportDTOList;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    vm.getPanTorch = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        name: vm.name,
      };
      rbibondCertificateInvtService.getPanNo(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panNoList = response.data.result.torchValue;
          angular.element("#panNoModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

        vm.getBLANumber = function () {
          vm.loader = true;

          var requestData = {
            schemeCode: vm.schemeCode,
            panNo: vm.panNo,
            name: vm.name,
            blaNo: vm.blaNo,
          };

          rbibondCertificateInvtService.getBlaNo(requestData).then(
            function (response) {
              vm.loader = false;
              vm.blaAccNoList = response.data.result.torchValue;

              angular.element("#blaAccNoModal").modal("toggle");
            },
            function (error) {
              handleErrorResponse(error);
              vm.loader = false;
            }
          );
        };

    function printpage(fileName) {
       const originalTitle = document.title;
          // Set custom title temporarily
          document.title = fileName;
          // Trigger print
          window.print();
          // Restore the original title after a delay
          setTimeout(() => {
            document.title = originalTitle;
          }, 1000);
    }

    function getPanNum() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        name: vm.name,
        panNo: vm.panNo,
      };
      rbibondCertificateInvtService.getPanNo(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panNoList = response.data.result.torchValue;
          angular.element("#panNoModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getSubscriptionId() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        name: vm.name,
        panNo: vm.panNo,
        blaNo: vm.blaNo,
        subscriptionId: vm.subscriptionId,
      };

      rbibondCertificateInvtService.getSubscrId(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionIdList = response.data.result.torchValue;
          angular.element("#subScriptionIdModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }


    function getCertificateDetail() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        name: vm.name,
        panNo: vm.panNo,
        blaNo: vm.blaNo,
        subscriptionId: vm.subscriptionId,
        fromDate            : vm.fromDate ? moment(vm.fromDate).format("DD-MMM-YYYY") : "",
        toDate              : vm.toDate ? moment(vm.toDate).format("DD-MMM-YYYY") : "",
      };

      rbibondCertificateInvtService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeFlag=response.data.result.schemeFlag;
          vm.list = response.data.result.bondCertificateInvtReportDTOList;

          if (response.data.result && (response.data.result.bondCertificateInvtReportDTOList1 || response.data.result.bondCertificateInvtReportDTOList)) {
            // Check the flag to determine which list to populate
            if (response.data.result.schemeFlag === "Y") {
              vm.list = response.data.result.bondCertificateInvtReportDTOList;
            } else if (response.data.result.schemeFlag === "N") {
              vm.list1 = response.data.result.bondCertificateInvtReportDTOList1;
            }
            // Assign the totals from the top-level result object
            vm.totalInvestmentAmt = response.data.result.totalInvestmentAmt;
            vm.totalRepaymentAmt = response.data.result.totalRepaymentAmt;
            vm.totaltTransferAmt = response.data.result.totaltTransferAmt;
            vm.totalBalance = response.data.result.totalBalance;

            // Bind the first item from the list
            vm.model = vm.schemeFlag === "Y" ? response.data.result.bondCertificateInvtReportDTOList[0] : response.data.result.bondCertificateInvtReportDTOList1[0];

            // Convert amounts to words
            vm.modelAmount = convertNumberToWords(vm.model.maturityAmt || 0);
            vm.investmentAmountInWords = convertNumberToWords(vm.totalInvestmentAmt || 0);
          } else {
            vm.model = {};
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
            $state.go("rbibond_certificate_of_investment_report.inquiry");
          } else {
            handleErrorResponse(error);
            $state.go("rbibond_certificate_of_investment_report.inquiry");
          }
        }
      );
    }

//    function convertNumberToWords(subscriptionAmt) {
//      var words = new Array();
//      words[0] = "Zero";
//      words[1] = "One";
//      words[2] = "Two";
//      words[3] = "Three";
//      words[4] = "Four";
//      words[5] = "Five";
//      words[6] = "Six";
//      words[7] = "Seven";
//      words[8] = "Eight";
//      words[9] = "Nine";
//      words[10] = "Ten";
//      words[11] = "Eleven";
//      words[12] = "Twelve";
//      words[13] = "Thirteen";
//      words[14] = "Fourteen";
//      words[15] = "Fifteen";
//      words[16] = "Sixteen";
//      words[17] = "Seventeen";
//      words[18] = "Eighteen";
//      words[19] = "Nineteen";
//      words[20] = "Twenty";
//      words[30] = "Thirty";
//      words[40] = "Forty";
//      words[50] = "Fifty";
//      words[60] = "Sixty";
//      words[70] = "Seventy";
//      words[80] = "Eighty";
//      words[90] = "Ninety";
//      subscriptionAmt = subscriptionAmt.toString();
//      var atemp = subscriptionAmt.split(".");
//      var number = atemp[0].split(",").join("");
//      var n_length = number.length;
//      var words_string = "";
//      var paisa_string = "";
//      if (atemp.length > 1) {
//        var paisa = atemp[1].split(",").join("");
//        var paisa_length = paisa.length;
//      } else {
//        paisa_string += words[0] + " ";
//      }
//
//      if (n_length <= 9) {
//        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
//        var received_n_array = new Array();
//        for (var i = 0; i < n_length; i++) {
//          received_n_array[i] = number.substr(i, 1);
//        }
//        for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
//          n_array[i] = received_n_array[j];
//        }
//        for (var i = 0, j = 1; i < 9; i++, j++) {
//          if (i == 0 || i == 2 || i == 4 || i == 7) {
//            if (n_array[i] == 1) {
//              n_array[j] = 10 + parseInt(n_array[j]);
//              n_array[i] = 0;
//            }
//          }
//        }
//        let value = "";
//        for (var i = 0; i < 9; i++) {
//          if (i == 0 || i == 2 || i == 4 || i == 7) {
//            value = n_array[i] * 10;
//          } else {
//            value = n_array[i];
//          }
//          if (value != 0) {
//            words_string += words[value] + " ";
//          }
//          if (value == "0" && i == 8) {
//            words_string += words[value] + " ";
//          }
//          if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
//            words_string += "Crores ";
//          }
//          if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
//            words_string += "Lakhs ";
//          }
//          if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
//            words_string += "Thousand ";
//          }
//          if (i == 6 && value != 0 && n_array[i + 1] != 0 && n_array[i + 2] != 0) {
//            words_string += "Hundred and ";
//          } else if (i == 6 && value != 0) {
//            words_string += "Hundred ";
//          }
//        }
//        words_string = words_string.split("  ").join(" ");
//      }
//      if (paisa_length < 3) {
//        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
//        var received_n_array = new Array();
//        for (var i = 0; i < paisa_length; i++) {
//          received_n_array[i] = paisa.substr(i, 1);
//        }
//        for (var i = 9 - paisa_length, j = 0; i < 9; i++, j++) {
//          n_array[i] = received_n_array[j];
//        }
//        for (var i = 0, j = 1; i < 9; i++, j++) {
//          if (i == 0 || i == 2 || i == 4 || i == 7) {
//            if (n_array[i] == 1) {
//              n_array[j] = 10 + parseInt(n_array[j]);
//              n_array[i] = 0;
//            }
//          }
//        }
//        let value = "";
//        for (var i = 0; i < 9; i++) {
//          if (i == 0 || i == 2 || i == 4 || i == 7) {
//            value = n_array[i] * 10;
//          } else {
//            value = n_array[i];
//          }
//          if (value != 0) {
//            paisa_string += words[value] + " ";
//          } else {
//            paisa_string += words[value] + " ";
//          }
//        }
//        paisa_string = paisa_string.split("  ").join(" ");
//      }
//      vm.netPaymentInWords = "Rupees  " + words_string + " ONLY";
//      return vm.netPaymentInWords;
//    }
         function convertNumberToWords (num) {
               if (num === 0) return "Rupees zero ONLY";

               const belowTwenty = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                                    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"];
               const tens = ["twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"];
               const denominations = ["", "thousand", "lakh", "crore"];

               function convertChunk(num) {
                   let result = "";
                   if (num >= 100) {
                       result += belowTwenty[Math.floor(num / 100) - 1] + " hundred ";
                       num %= 100;
                   }
                   if (num >= 20) {
                       result += tens[Math.floor(num / 10) - 2] + " ";
                       num %= 10;
                   }
                   if (num > 0) {
                       result += belowTwenty[num - 1] + " ";
                   }
                   return result.trim();
               }

 vm.indianFormat = function (input) {
        if (!input) return "";

         // Convert input to a number
         var num = parseFloat(input);
         if (isNaN(num)) return input;
         // Format number in Indian style
       return num.toLocaleString('en-IN', {
         minimumFractionDigits: 2,
         maximumFractionDigits: 2
     });
         };
               function convertToIndianFormat(num) {
                   if (num === 0) return "";
                   const parts = [];
                   let counter = 0;

                   // Extract crore, lakh, thousand, and remaining part
                   const crore = Math.floor(num / 10000000);
                   num %= 10000000;
                   const lakh = Math.floor(num / 100000);
                   num %= 100000;
                   const thousand = Math.floor(num / 1000);
                   num %= 1000;
                   const remaining = num;

                   if (crore > 0) parts.push(convertChunk(crore) + " crore");
                   if (lakh > 0) parts.push(convertChunk(lakh) + " lakh");
                   if (thousand > 0) parts.push(convertChunk(thousand) + " thousand");
                   if (remaining > 0) parts.push(convertChunk(remaining));

                   return parts.join(" ").trim();
               }

               const result = convertToIndianFormat(num);
               vm.netPaymentInWords = "Rupees " + result + " ONLY";
               return vm.netPaymentInWords;
           };

        function formatIndianCurrency(amount) {
            if (amount === undefined) {
                return null;
            }

            return new Intl.NumberFormat('en-IN', {
                                                        minimumFractionDigits: 2,
                                                        maximumFractionDigits: 2
                                                    }).format(amount);
        }

  }
})();
