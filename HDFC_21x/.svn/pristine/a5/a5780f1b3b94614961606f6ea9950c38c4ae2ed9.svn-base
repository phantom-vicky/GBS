(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('brokerageInvoiceGenerationController',
        brokerageInvoiceGenerationController);

    brokerageInvoiceGenerationController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'brokerageInvoiceGenerationService', 'commonService'
    ];

    function brokerageInvoiceGenerationController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, brokerageInvoiceGenerationService, commonService) {

		var vm = this;
		vm.moduleName = 'RBI Bond';
//		vm.moduleId = '';


        vm.getModuleName = getModuleName;
        vm.brokerAgencyCode = brokerAgencyCode;
        vm.getDetails = getDetails
        vm.generateBrokerRes = generateBrokerRes;


		vm.solDate = $rootScope.globals.currentUser.systemDate;
		vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
		vm.subsDate1 = moment(vm.solDate).format("DD-MM-YYYY");
		vm.solDate1 = $rootScope.globals.currentUser.solDate;


        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc
        vm.today=new Date();
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
		vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.fromToDateValidation = fromToDateValidation;
        vm.getAddDetails =  getAddDetails;
        vm.printpage = printpage;
        vm.alert=alert;


     if($state.current.name == 'brokerage_invoice_generation.inquiry_detail'){
          if($stateParams.obj){
          vm.loader = true;
          vm.dataList=[];
          vm.fromDate = $stateParams.obj.fromDate;
          vm.schemeName = $stateParams.obj.schemeName;
          vm.schemeCode = $stateParams.obj.schemeCode;
          vm.brokerId = $stateParams.obj.brokerId;
          vm.toDate = $stateParams.obj.toDate;
          vm.blaNo = $stateParams.obj.blaNo;
          vm.getDetails();
          console.log(vm.dataList.length);
      }
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
                      'blaFlag' : 'Y',
                      'moduleId' : vm.schemeCode,
                      'blaNo' : null,
                      'fromDate' : $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                      'toDate' : $filter('date')(vm.toDate, "dd-MMM-yyyy"),

                  };
                  brokerageInvoiceGenerationService.getAddDetails(requestData).then(function (response) {
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
                       'blaFlag' : 'N',
                       'moduleId' : vm.schemeCode,
                       'blaNo'    : vm.blaNo,
                       'fromDate' : $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                       'toDate'   : $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                      };
                     brokerageInvoiceGenerationService.getAddDetails(requestData).then(function (response) {


                     vm.dataList = response.data.result;
//                                 $state.go('rbiBond_minor_major_intimation_notice.inquiry_detail');
                     vm.loader = false;

                      }, function (error) {
                           handleErrorResponse(error);
                           $state.go('brokerage_invoice_generation.inquiry')
                           vm.loader = false;
                         });
                }

      }


       function printpage(){

         if ($state.current.name == 'brokerage_invoice_generation.inquiry_detail') {
                         document.title = 'Brokerage Invoice Generation';
                     }

                    window.print()


//                          var filename = 'Brokerage Invoice Download';
//                            var downloadLink = document.getElementById('downloadLink');
//                            downloadLink.href = 'data:application/pdf;utf-8,' + escape('');
//                            downloadLink.download = filename;
//                            downloadLink.click();
                  }

      function fromToDateValidation() {
          //if (!vm.fromDate) return;

          //let selectedDate = new Date(vm.fromDate);

          // Get the last date of the selected month
         // let lastDateOfMonth = new Date(selectedDate.getFullYear(), selectedDate.getMonth() + 1, 0);

          // Set "To Date" range within the selected month


//          // Prevent selecting past dates for "From Date"
    vm.dateFromOptions = {
              maxDate: vm.solDate
          };
          vm.dateToOptions = {
            minDate: vm.fromDate,
             maxDate: vm.solDate
          };

          vm.toDate = ''; // Reset To Date when From Date changes
      }



        function getModuleName() {
                            vm.loader = true;

                            var requestData = {
                            };
                            brokerageInvoiceGenerationService.getBrokerageInvoiceBondScheme(requestData).then(function (response) {
                                vm.loader = false;
                                vm.schemeList = response.data.result;
                                angular.element('#schemeModel').modal('toggle');
                            }, function (error) {
                                handleErrorResponse(error);
                                vm.loader = false;
                            });
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

        function brokerAgencyCode(){
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    };
                   brokerageInvoiceGenerationService.getBrokerageAgencyCode(requestData).then(function(response){
                        vm.loader = false;
                        vm.brokerTorchList = response.data.result;
                        angular.element('#brokerModal').modal('toggle');

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });
        }

        function getDetails(){
                          vm.loader = true;
                          var requestData = {

                                     'schemeCode' : vm.schemeCode,
                                       'brokerCode' : vm.brokerId,
                                     'fromDate' : $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                                     'toDate'   : $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                                            };
                          brokerageInvoiceGenerationService.getBrokerageInvoiceBondScheme(requestData).then(function (response) {

                          vm.dataList = response.data.result;
                          vm.emailBodyCount = response.data.total;
                          vm.invoiceCount = response.data.result1;
                          vm.amountInWords = [];
                          vm.dataList.forEach(function(item) {

                                  item.Brokerage_Commission_in_Words = convertNumberToWords(item.Brokerage_Commission);

                          });

                              vm.loader = false;



                          }, function (error) {
                                                        vm.loader = false;
                              handleErrorResponse(error);
                                                    $state.go('brokerage_invoice_generation.inquiry');

                          });
                         }
function generateBrokerRes() {
             Swal.fire({
                title : "Are you sure?",
                text : "Do you want to generate this records?",
                type : "warning",
                showCancelButton : true,
                cancelButtonClass : "btn-cancel",
                confirmButtonClass : "btn-primary",
                confirmButtonText : "Yes",
                closeOnConfirm : false
            }). then((result) => {
                 if (result.isConfirmed) {
            vm.loader= true;
            vm.brokerDetailsList=[];
                    angular.forEach(vm.dataList, function(item){
                 vm.brokerDetailsList.push({
                     brokerCode: item.broker_agency_code,
                     invoiceNum: item.invoice_no
                 });
            })
             var requestData = {
                "schemeCode": vm.schemeCode,
                "fromDate": $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                "toDate": $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                "brokerResList": vm.brokerDetailsList

             };

              brokerageInvoiceGenerationService.generateBrokerRes(requestData).then( (response) => {
                    vm.loader=false;
                    vm.addResultResponse = response.data.result;
                    Swal.fire("Success!", "Brokerage Generated Successfully", "success");
                    location.href = '#!/brokerage_invoice_generation/action/inquiry';
                                                             }
                 ,function(error) {
                   vm.loader = false;
                      handleErrorResponse(error);
                      $state.go('brokerage_invoice_generation.inquiry');
                    });

                }
            });

    }
function convertNumberToWords(amount) {
                        var words = new Array();
                        words[0] = 'Zero';
                        words[1] = 'One';
                        words[2] = 'Two';
                        words[3] = 'Three';
                        words[4] = 'Four';
                        words[5] = 'Five';
                        words[6] = 'Six';
                        words[7] = 'Seven';
                        words[8] = 'Eight';
                        words[9] = 'Nine';
                        words[10] = 'Ten';
                        words[11] = 'Eleven';
                        words[12] = 'Twelve';
                        words[13] = 'Thirteen';
                        words[14] = 'Fourteen';
                        words[15] = 'Fifteen';
                        words[16] = 'Sixteen';
                        words[17] = 'Seventeen';
                        words[18] = 'Eighteen';
                        words[19] = 'Nineteen';
                        words[20] = 'Twenty';
                        words[30] = 'Thirty';
                        words[40] = 'Forty';
                        words[50] = 'Fifty';
                        words[60] = 'Sixty';
                        words[70] = 'Seventy';
                        words[80] = 'Eighty';
                        words[90] = 'Ninety';
                        amount = amount.toString();
                        var atemp = amount.split(".");
                        var number = atemp[0].split(",").join("");
                        var n_length = number.length;
                        var words_string = "";
                        var paisa_string = "";
                        if(atemp.length>1){
                            var paisa = atemp[1].split(",").join("");
                            var paisa_length = paisa.length;
                        } else {
                           paisa_string += words[0] + " ";
                        }

                        if (n_length <= 9) {
                            var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
                            var received_n_array = new Array();
                            for (var i = 0; i < n_length; i++) {
                                received_n_array[i] = number.substr(i, 1);
                            }
                            for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
                                n_array[i] = received_n_array[j];
                            }
                            for (var i = 0, j = 1; i < 9; i++, j++) {
                                if (i == 0 || i == 2 || i == 4 || i == 7) {
                                    if (n_array[i] == 1) {
                                        n_array[j] = 10 + parseInt(n_array[j]);
                                        n_array[i] = 0;
                                    }
                                }
                            }
                            let value = "";
                            for (var i = 0; i < 9; i++) {
                                if (i == 0 || i == 2 || i == 4 || i == 7) {
                                    value = n_array[i] * 10;
                                } else {
                                    value = n_array[i];
                                }
                                if (value != 0) {
                                    words_string += words[value] + " ";
                                }
//                                if (value == '0' && i == 8){
//                                  words_string += words[value] + " ";
//                                }
                                if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
                                    words_string += "Crores ";
                                }
                                if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
                                    words_string += "Lakhs ";
                                }
                                if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
                                    words_string += "Thousand ";
                                }
                                if (i == 6 && value != 0 && (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
                                    words_string += "Hundred and ";
                                } else if (i == 6 && value != 0) {
                                    words_string += "Hundred ";
                                }
                            }
                            words_string = words_string.split("  ").join(" ");

                        }
                        if(paisa_length < 3){
                        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
                         var received_n_array = new Array();
                            for (var i = 0; i < paisa_length; i++) {
                                received_n_array[i] = paisa.substr(i, 1);
                            }
                            for (var i = 9 - paisa_length, j = 0; i < 9; i++, j++) {
                                n_array[i] = received_n_array[j];
                            }
                            for (var i = 0, j = 1; i < 9; i++, j++) {
                                if (i == 0 || i == 2 || i == 4 || i == 7) {
                                    if (n_array[i] == 1) {
                                        n_array[j] = 10 + parseInt(n_array[j]);
                                        n_array[i] = 0;
                                    }

                                }
                            }
                            let value = "";
                            for (var i = 0; i < 9; i++) {
                                if (i == 0 || i == 2 || i == 4 || i == 7) {
                                    value = n_array[i] * 10;
                                } else {
                                    value = n_array[i];
                                }
                                if (value != 0) {
                                    paisa_string += words[value] + " ";
                                }
                                /*else {
                                  paisa_string += words[value] + " ";
                                }*/

                            }
                            paisa_string = paisa_string.split("  ").join(" ");
                        }
                            vm.netPaymentInWords = "Rupees  "+words_string+"and Paise "+paisa_string+" Only";
                           return vm.netPaymentInWords;
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