(function () {
  "use strict";
  angular.module("gbmUltraApp").controller("CertificateOfHoldingController", CertificateOfHoldingController);

  CertificateOfHoldingController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "CertificateOfHoldingService", "commonService"];

  function CertificateOfHoldingController($scope, $filter, $state, $stateParams, $location, $rootScope, CertificateOfHoldingService, commonService) {
    var vm = this;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.currentDate = new Date();
    vm.currentDate = $filter("date")(vm.currentDate, "dd-MMM-yyyy");
    vm.formatIndianCurrency = formatIndianCurrency;
//    vm.groupCustomersByBlaNo = groupCustomersByBlaNo;
    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
      };

      CertificateOfHoldingService.getDetails(requestData).then(
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
vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;

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




    vm.getPanNumber = function () {
      var requestData = {
        schemeCode  : vm.schemeCode,
        holderName  : vm.bondHolderName,
        panNumber   : vm.panNum,
        flag        : "P",
      };
      CertificateOfHoldingService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.PanTorch = response.data.result.list;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getBLANumber = function () {
      vm.loader = true;
      var requestData = {
        schemeCode  : vm.schemeCode,
        holderName  : vm.bondHolderName,
        panNumber   : vm.panNum,
        blaNo       : vm.blaNo,
        flag        : "B",
      };
      CertificateOfHoldingService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaAccNoList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getSubscriptionID = function () {
      vm.loader = true;
      var requestData = {
        schemeCode          : vm.schemeCode,
        holderName          : vm.bondHolderName,
        panNumber           : vm.panNum,
        blaNo               : vm.blaNo,
        flag                : "S",
        bondSubscriptionId  : vm.subscriptionID,
      };
      CertificateOfHoldingService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionIdList = response.data.result.list;
          angular.element("#subscriptionIDModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.getCertificateOfHoldingDetails = function () {
      vm.loader = true;
      var requestData = {
        schemeCode          : vm.schemeCode,
        holderName          : vm.bondHolderName,
        panNumber           : vm.panNum,
        blaNo               : vm.blaNo,
        bondSubscriptionId  : vm.subscriptionID,
        fromDate            : vm.fromDate ? moment(vm.fromDate).format("DD-MMM-YYYY") : "",
        toDate              : vm.toDate ? moment(vm.toDate).format("DD-MMM-YYYY") : "",
        flag                : "T",
      };

      CertificateOfHoldingService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("rbibond_certificate_investment_hold_report.inquiry");
          }
        }
      );
    };

    if ($state.current.name == "rbibond_certificate_investment_hold_report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.bondHolderName = $stateParams.obj.bondHolderName;
      vm.panNum = $stateParams.obj.panNum;
      vm.blaNo = $stateParams.obj.blaNo;
      vm.subscriptionID = $stateParams.obj.subscriptionID;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.schemeName = $stateParams.obj.schemeName;
      if (vm.schemeCode == "0012") {
        vm.schemeName = "Floating Rate Savings Bonds, 2020 (Taxable)";
      }
      vm.getCertificateOfHoldingDetails();
    }

    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate     : new Date(),
        showWeeks   : false,
      };
    };

    vm.toDateVal = function (fromDate) {
      var maxToDate = new Date(fromDate);
          maxToDate.setDate(maxToDate.getDate() + 7); // Add one month to the fromDate
          vm.toDateOptions = {
              minDate: fromDate,
              maxDate: maxToDate,
              showWeeks: false,
          };
          if (vm.toDate && vm.toDate > maxToDate) {
              vm.toDate = null;
          }
    };

   vm.convertNumberToWords = function (num) {
       if (num === 0) return "RUPEES ZERO ONLY";

       const belowTwenty = ["ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN",
                            "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"];
       const tens = ["TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY"];
       const denominations = ["", "THOUSAND", "LAKH", "CRORE"];

       function convertChunk(num) {
           let result = "";
           if (num >= 100) {
               result += belowTwenty[Math.floor(num / 100) - 1] + " HUNDRED ";
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

       function convertToIndianFormat(num) {
           if (num === 0) return "";
           const parts = [];

           // Extract crore, lakh, thousand, and remaining part
           const crore = Math.floor(num / 10000000);
           num %= 10000000;
           const lakh = Math.floor(num / 100000);
           num %= 100000;
           const thousand = Math.floor(num / 1000);
           num %= 1000;
           const remaining = num;

           if (crore > 0) parts.push(convertChunk(crore) + " CRORE" + (crore > 1 ? "S" : ""));
           if (lakh > 0) parts.push(convertChunk(lakh) + " LAKH" + (lakh > 1 ? "S" : ""));
           if (thousand > 0) parts.push(convertChunk(thousand) + " THOUSAND");
           if (remaining > 0) parts.push(convertChunk(remaining));

           return parts.join(" ").trim();
       }

       const result = convertToIndianFormat(num);
       vm.netPaymentInWords = "RUPEES " + result + " ONLY";
       return vm.netPaymentInWords;
   };

  vm.downloadTorch = function () {
     vm.loader = true;
      var requestData = {
        schemeCode          : vm.schemeCode,
        holderName          : vm.holderName,
        panNumber           : vm.panNumber,
        blaNo               : vm.blaNo,
        toDate              : vm.toDate ? moment(vm.toDate).format("DD-MMM-YYYY") : "",
           bondSubscriptionId  : vm.bondSubscriptionId,
        flag                : "D",
      };

      CertificateOfHoldingService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadList = response.data.result.list;
          vm.printCustomerRecord(vm.downloadList);
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("rbibond_certificate_investment_hold_report.inquiry");
          }
        }
      );
    };

      if("rbibond_certificate_investment_hold_report.print_details" == $state.current.name){

              vm.schemeCode = $stateParams.obj.schemeCode;
              vm.holderName = $stateParams.obj.holderName;
              vm.panNumber = $stateParams.obj.panNumber;
              vm.blaNo = $stateParams.obj.blaNo;
              vm.bondSubscriptionId = $stateParams.obj.bondSubscriptionId;
              vm.fromDate = $stateParams.obj.fromDate;
              vm.toDate = $stateParams.obj.toDate;
              vm.schemeName = $stateParams.obj.schemeName;
              vm.downloadTorch();
              }

vm.printCustomerRecord = function (customer) {
    vm.selectedCustomers = angular.copy(customer);

    // Convert search text to lowercase for case-insensitive filtering
    var searchText = vm.searchText ? vm.searchText.toLowerCase() : "";

    // Apply manual filtering to ensure partial matches are considered
    var filteredRecords = searchText
        ? vm.selectedCustomers.filter(record =>
            Object.values(record).some(value =>
                value && value.toString().toLowerCase().includes(searchText)
            )
        )
        : vm.selectedCustomers;

    // Reset groupedCustomers
    vm.groupedCustomers = {};

    // Group by blaNo
    filteredRecords.forEach(function (record) {
        if (!vm.groupedCustomers[record.blaNo]) {
            vm.groupedCustomers[record.blaNo] = [];
        }
        vm.groupedCustomers[record.blaNo].push(record);
    });

    // Convert object to array
    vm.groupedCustomers = Object.values(vm.groupedCustomers);

    vm.calculateTotals(vm.groupedCustomers);

};

$scope.toTitleCase = function(str) {
    if (!str) return '';

    return str.toLowerCase().replace(/\b\w/g, function(c) {
        return c.toUpperCase();
    });
};

vm.printSingleRecord = function () {

  const printContainer = document.getElementById("printContainer");
    if (printContainer) {
        printContainer.style.display = "block";
        setTimeout(() => {
            document.title = "Certificate of Holding";
            window.print();
            printContainer.style.display = "block";
        }, 100);
    }

}


    vm.printPage = function () {
      vm.loader = true;
      var requestData = {};
      if (vm.fromDate && vm.toDate && (vm.bondHolderName || vm.panNum || vm.blaNo)) {
        requestData = {
          schemeCode            : vm.schemeCode,
          holderName            : vm.bondHolderName,
          panNumber             : vm.panNum,
          blaNo                 : vm.blaNo,
          bondSubscriptionId    : vm.subscriptionID,
          fromDate              : moment(vm.fromDate).format("DD-MMM-YYYY") || "",
          toDate                : moment(vm.toDate).format("DD-MMM-YYYY") || "",
          flag                  : "D",
        };
      } else if (vm.bondHolderName || vm.panNum || vm.blaNo || vm.subscriptionID) {
        requestData = {
          schemeCode            : vm.schemeCode,
          holderName            : vm.bondHolderName,
          panNumber             : vm.panNum,
          blaNo                 : vm.blaNo,
          bondSubscriptionId    : vm.subscriptionID,
          flag                  : "D",
        };
      } else if (vm.fromDate && vm.toDate) {
        requestData = {
          schemeCode    : vm.schemeCode,
          fromDate      : moment(vm.fromDate).format("DD-MMM-YYYY") || "",
          toDate        : moment(vm.toDate).format("DD-MMM-YYYY") || "",
          flag          : "F",
        };
      } else if (vm.fromDate) {
        requestData = {
          schemeCode    : vm.schemeCode,
          fromDate      : moment(vm.fromDate).format("DD-MMM-YYYY") || "",
          flag          : "D",
        };
      }

      CertificateOfHoldingService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadList = response.data.result.list;
          vm.downloadCustomerRecord(vm.downloadList);

        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("rbibond_certificate_investment_hold_report.inquiry");
          }
        }
      );
    };


    vm.formatDate = function (dateStr) {
      const months = {
        JAN: "January",
        FEB: "February",
        MAR: "March",
        APR: "April",
        MAY: "May",
        JUN: "June",
        JUL: "July",
        AUG: "August",
        SEP: "September",
        OCT: "October",
        NOV: "November",
        DEC: "December",
      };
      const [day, month, year] = (dateStr?.split("/") ?? ["", "", ""]);
      return `${months[month.toUpperCase()]} ${day}, ${year}`;
    };

vm.downloadCustomerRecord = function (customer) {
    vm.selectedCustomers = angular.copy(customer);

    // Convert search text to lowercase for case-insensitive filtering
    var searchText = vm.searchText ? vm.searchText.toLowerCase() : "";

    // Apply manual filtering to ensure partial matches are considered
    var filteredRecords = searchText
        ? vm.selectedCustomers.filter(record =>
            Object.values(record).some(value =>
                value && value.toString().toLowerCase().includes(searchText)
            )
        )
        : vm.selectedCustomers;

    // Reset groupedCustomers
    vm.groupedCustomers = {};

    // Group by blaNo
    filteredRecords.forEach(function (record) {
        if (!vm.groupedCustomers[record.blaNo]) {
            vm.groupedCustomers[record.blaNo] = [];
        }
        vm.groupedCustomers[record.blaNo].push(record);
    });

    // Convert object to array
    vm.groupedCustomers = Object.values(vm.groupedCustomers);

    vm.calculateTotals(vm.groupedCustomers);

    // Print
    const printContainer = document.getElementById("printContainer");
    if (printContainer) {
        printContainer.style.display = "block";
        setTimeout(() => {
            document.title = "Certificate of Holding";
            window.print();
            printContainer.style.display = "none";
        }, 100);
    }
};
vm.calculateTotals = function (groupedCustomers) {
    vm.customerTotals = {}; // Reset totals

    if (!groupedCustomers || !groupedCustomers.length) {
        return;
    }

    groupedCustomers.forEach(group => {
        if (!group.length) return; // Skip empty groups

        let blaNo = group[0].blaNo; // Use blaNo as the key
        vm.customerTotals[blaNo] = {
            totalInvestAmount: group.reduce((sum, item) => sum + (parseFloat(item.investAmount) || 0), 0),
            totalTransferAmount: group.reduce((sum, item) => sum + (parseFloat(item.transferAmount) || 0), 0),
            totalRepaymentAmount: group.reduce((sum, item) => sum + (parseFloat(item.repaymentAmount) || 0), 0),
            totalBalance: group.reduce((sum, item) => sum + (parseFloat(item.balance) || 0), 0),
        };
    });

};

vm.formatDateDDMM = function (dateStr) {
  return formatDate1(dateStr, "dd/MM");
};

    // Split an array into chunks of a fixed size (e.g., 25 per page)
    vm.chunkArray = function (array, size) {
      if (!Array.isArray(array) || size <= 0) {
        return [array || []];
      }
      var chunks = [];
      for (var i = 0; i < array.length; i += size) {
        chunks.push(array.slice(i, i + size));
      }
      return chunks;
    };

vm.getBondSubscriptionIds = function (x) {
    return x.map(function (item) {
        return item.bondSubscriptionId;
    }).join(", ");
};

//vm.getNomineeIds = function (x) {
//    return x.map(function (item) {
//        return item.nomineeID;
//    }).join(", ");
//};


vm.getNomineeIds = function (x) {
    if (!x || !x.length) return "";
    const ids = x
        .map(item => item.nomineeID)
        .filter(id => id); // remove null/undefined/empty values

    return ids.length ? ids.join(", ") : "";
};



function formatDate1(dateStr, format) {
  const months = {
    "01": "January", "02": "February", "03": "March", "04": "April",
    "05": "May", "06": "June", "07": "July", "08": "August",
    "09": "September", "10": "October", "11": "November", "12": "December"
  };
$scope.formatGovtNo = function(val) {
  return val.replace("F.NO", "F.No");
};
  function getSuffix(day) {
    if (day >= 11 && day <= 13) return "th";
    switch (day % 10) {
      case 1: return "st";
      case 2: return "nd";
      case 3: return "rd";
      default: return "th";
    }
  }

  let day, month;

  if (format === "MM/dd") {
    [month, day] = dateStr.split("/");
  } else {
    [day, month] = dateStr.split("/");
  }

  const dayNum = parseInt(day, 10);
  const suffix = getSuffix(dayNum);

  return `${day.padStart(2, '0')}${suffix} ${months[month]}`;
}
     function formatIndianCurrency(amount) {
         if (!amount) return '';
            return new Intl.NumberFormat('en-IN', {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2
            }).format(amount);
            }

            vm.getInquiryParams = function () {
              var params = {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,

              };

              if (!vm.blaNo) {
                params.fromDate = vm.fromDate;
                params.toDate = vm.toDate;
              }

              if (vm.fromDate && vm.toDate&&vm.blaNo) {

                             params.fromDate = vm.fromDate;
                                             params.toDate = vm.toDate;
                            }

                             if (vm.fromDate && vm.toDate) {

                                            params.fromDate = vm.fromDate;
                                            params.toDate = vm.toDate;
                                                        }

                    if (!vm.fromDate && !vm.toDate&&vm.blaNo) {
                                                 params.blaNo = vm.blaNo;
                                                 }

              return params;
            };
  }
})();
