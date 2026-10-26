(function () {

	'use strict';

	angular.module('gbmUltraApp').controller('auditReportAtDcLevelForAnySolController',
		auditReportAtDcLevelForAnySolController);

	auditReportAtDcLevelForAnySolController.$inject = ['$scope', '$filter', '$state',
		'$stateParams', '$location', '$rootScope', 'auditReportAtDcLevelForAnySolService',
		'commonService'
	];

	function auditReportAtDcLevelForAnySolController($scope, $filter, $state, $stateParams,
		$location, $rootScope, auditReportAtDcLevelForAnySolService, commonService) {

		var vm = this;
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;

		vm.auditReportAtDcLevelForAnySol = {};
		vm.getSolDetails = getSolDetails;

		vm.submit = submit;
		vm.operationVal = operationVal;

		vm.getLinkName = getLinkName;
		vm.getRecordTypes = getRecordTypes;
		vm.getOperation = getOperation;
		vm.getReportAtDCLevel = getReportAtDCLevel;
		vm.fromToDateValidation = fromToDateValidation;
		vm.today = new Date();

		vm.cancel = cancel;
		vm.getModuleName = getModuleName;
		vm.moduleChange = moduleChange;
		vm.getStateCode = getStateCode;
		vm.getTreasuryCode = getTreasuryCode;
        vm.showBranchRow = true;
        vm.showBranchRow1 = false;
        vm.getBankName = getBankName;

         function getBankName()  {
                            commonService.getBankName().then(
                             (response) => {
                               vm.bankName=response.data.result.bankName;
                             }
                            )
                      }

		function moduleChange(){
		    if(vm.inputState == 'PB'){
		        vm.linkName = vm.linkCode = '' ;
		        vm.showBranchRow = true;
		        vm.showBranchRow1 = false;
		    }else{
		        vm.linkName = vm.branch = vm.branchId = vm.branchName = vm.linkCode = vm.treasuryCode = vm.stateCode = '' ;
		        if(vm.inputState == 'DC' || vm.inputState == 'CPPC'){
		            if((vm.auditReportAtDcLevelForAnySol.moduleId == 'ST') || (vm.auditReportAtDcLevelForAnySol.moduleId == 'PENSION')){
		                vm.showBranchRow1 = true;
		                vm.showBranchRow = false;
                    }
                    else{
                        vm.showBranchRow1 = false;
                        vm.showBranchRow = true;
                    }
		        }
		    }
		}


		if ($state.current.name == 'audit_report_dc.list') {
		    vm.inputState = 'DC';
			vm.nextState = 'audit_report_dc.list_detail'
		} else if ($state.current.name == 'audit_report_cppc.list') {
		     vm.inputState = 'CPPC';
			 vm.nextState = 'audit_report_cppc.list_detail';
		}else if($state.current.name == 'audit_report_for_dc_parameterizations.list'){
		     vm.nextState = 'audit_report_for_dc_parameterizations.list_detail';
		     vm.inputState = 'DC_PAR';
		} else if ($state.current.name == 'audit_report_pb.list') {
		     vm.inputState = 'PB';
             vm.nextState = 'audit_report_pb.list_detail';
             vm.branch = vm.loginSolId + '-' + vm.loginSolDesc;
             vm.branchId = vm.loginSolId;
             vm.branchName = vm.loginSolDesc;
		} else if ($state.current.name == 'audit_report_for_dc_parameterizations.list_detail' || $state.current.name == 'audit_report_dc.list_detail' || $state.current.name == 'audit_report_cppc.list_detail' || $state.current.name == 'audit_report_pb.list_detail') {

			if ($stateParams.obj) {
				vm.moduleId = $stateParams.obj.moduleId;
				vm.linkName = $stateParams.obj.linkName;
				vm.linkCode = $stateParams.obj.linkCode;
				vm.tableShortName = $stateParams.obj.tableShortName;
				vm.includeVerifiedRecords = $stateParams.obj.includeVerifiedRecords;
				console.log(vm.includeVerifiedRecords);
				vm.fromDate = $stateParams.obj.fromDate;
				vm.toDate = $stateParams.obj.toDate;
				vm.branch = $stateParams.obj.branch;
				vm.branchId = $stateParams.obj.branchId;
				vm.branchName = $stateParams.obj.branchName;
				vm.enteredBy = $stateParams.obj.createdBy;
				vm.modifiedBy = $stateParams.obj.modifiedBy;
				vm.verifiedBy = $stateParams.obj.verifiedBy;
				vm.deletedBy = $stateParams.obj.deletedBy;
				vm.operation = $stateParams.obj.operation;
				vm.stateCode = $stateParams.obj.stateCode;
				vm.stateName = $stateParams.obj.stateName;
				vm.treasuryCode = $stateParams.obj.treasuryCode;
				vm.treasuryName = $stateParams.obj.treasuryName;
				vm.recordStatus = $stateParams.obj.recordStatus;

				if (vm.includeVerifiedRecords && vm.fromDate && vm.toDate) {
				    getBankName();
					submit();
				} else {
					if ($state.current.name == 'audit_report_dc.list_detail') {
						$state.go('audit_report_dc.list');
					} else if ($state.current.name == 'audit_report_cppc.list_detail') {
						$state.go('audit_report_cppc.list');
					} else if ($state.current.name == 'audit_report_pb.list_detail') {
						$state.go('audit_report_pb.list');
					}else if($state.current.name == 'audit_report_for_dc_parameterizations.list_detail'){
					    $state.go('audit_report_for_dc_parameterizations.list');
					}

				}
			} else {
				if ($state.current.name == 'audit_report_dc.list_detail') {
					$state.go('audit_report_dc.list');
				} else if ($state.current.name == 'audit_report_cppc.list_detail') {
					$state.go('audit_report_cppc.list');
				} else if ($state.current.name == 'audit_report_pb.list_detail') {
					$state.go('audit_report_pb.list');
				}else if($state.current.name == 'audit_report_for_dc_parameterizations.list_detail'){
                    $state.go('audit_report_for_dc_parameterizations.list');
                }
			}
		}


		vm.goNextState = function (stateObj) {
			$state.go(vm.nextState, {
				obj: stateObj
			})
		}

		vm.goPreviousState = function () {
			if ($state.current.name == 'audit_report_dc.list_detail') {
				$state.go('audit_report_dc.list');
			} else if ($state.current.name == 'audit_report_cppc.list_detail') {
				$state.go('audit_report_cppc.list');
			} else if ($state.current.name == 'audit_report_pb.list_detail') {
				$state.go('audit_report_pb.list');
			}else if($state.current.name == 'audit_report_for_dc_parameterizations.list_detail'){
                $state.go('audit_report_for_dc_parameterizations.list');
            }
		}

		function operationVal() {
			vm.auditReportAtDcLevelForAnySol.includeVerifiedRecords = '';
		}

		function cancel() {
			if ($state.current.name == 'audit_report_dc.list') {
				location.href = '#!/report_menu_page';
			} else if ($state.current.name == 'audit_report_cppc.list') {
				location.href = '#!/report_menu_page';
			} else if ($state.current.name == 'audit_report_pb.list') {
				location.href = '#!/report_menu_page';
			}else if ($state.current.name == 'audit_report_for_dc_parameterizations.list') {
                location.href = '#!/report_menu_page';
            }
		}

		vm.reportName = '';
		if ($state.current.name == 'audit_report_dc.list_detail' || $state.current.name == 'audit_report_dc.list') {
			vm.reportName = "Audit Report at DC Level for Any Sol";
		} else if ($state.current.name == 'audit_report_cppc.list_detail' || $state.current.name == 'audit_report_cppc.list') {
			vm.reportName = "Audit Report at GU Level (Higher level branches)";
		} else if ($state.current.name == 'audit_report_pb.list_detail' || $state.current.name == 'audit_report_pb.list') {
			vm.reportName = "Audit Report at GU Level (at Branch Level)";
		}else if ($state.current.name == 'audit_report_for_dc_parameterizations.list_detail' || $state.current.name == 'audit_report_for_dc_parameterizations.list') {
            vm.reportName = "Audit Report for DC Parameterizations at DC Level";
        }


		/* Get Sol Details */
		function getSolDetails() {
			vm.loader = true;
			if(vm.inputState == 'DC' || vm.inputState == 'CPPC'){
			    vm.screenType = 'DC'
			}
			var requestData = {
			    'moduleId' : vm.auditReportAtDcLevelForAnySol.moduleId,
			    'screenType' : vm.screenType,
			    'treasuryCode' : vm.treasuryCode,
			    'stateCode' : vm.stateCode
			}
            auditReportAtDcLevelForAnySolService.getSolDetails(requestData)
                .then(
                    function(response) {
                        angular.element('#branchModal').modal('toggle');
                        vm.branchIdList = response.data.result;
                        vm.loader = false;
                    }, function(error){
                        vm.loader = false;
                        handleErrorResponse(error);
                    }
                );
		}

		function getTreasuryCode() {
			vm.loader = true;
			var requestData = {
			    'moduleId' : vm.auditReportAtDcLevelForAnySol.moduleId
			}
            auditReportAtDcLevelForAnySolService.getTreasuryCode(requestData)
                .then(
                    function(response) {
                        angular.element('#treasuryCodeModal').modal('toggle');
                        vm.treasuryCodeList = response.data.result;
                        vm.loader = false;
                    }, function(error){
                        vm.loader = false;
                        handleErrorResponse(error);
                    }
                );
		}

		function getStateCode() {
			vm.loader = true;
			var requestData = {
			    'moduleId' : vm.auditReportAtDcLevelForAnySol.moduleId
			}
            auditReportAtDcLevelForAnySolService.getStateCode(requestData)
                .then(
                    function(response) {
                        angular.element('#stateTaxCodeModal').modal('toggle');
                        vm.stateCodeList = response.data.result;
                        vm.loader = false;
                    }, function(error){
                        vm.loader = false;
                        handleErrorResponse(error);
                    }
                );
		}

		function fromToDateValidation() {
            vm.toDate = '';
            vm.dateToOptions = {
                minDate: vm.fromDate,
                maxDate: (vm.today < moment(vm.fromDate).endOf('month').toDate())?(vm.today) : (moment(vm.fromDate).add(1, 'M').toDate()),
            };
            vm.dateOptions = {
                maxDate: vm.today,
            }
        }

		function getLinkName() {
			vm.loader = true;
			var requestData = {
				'moduleId': vm.auditReportAtDcLevelForAnySol.moduleId
			}
			auditReportAtDcLevelForAnySolService.getLinkName(requestData).then(function (response) {
				vm.loader = false;
				angular.element('#linkNameModal').modal('toggle');
				vm.linkNameList = response.data.result;
			}, function (error) {
				vm.loader = false;
				handleErrorResponse(error);
			});
		}

		/*Record Types*/
		function getRecordTypes() {
			auditReportAtDcLevelForAnySolService.getRecordTypes().then(function (response) {
				vm.includeVerifiedRecordsList = response.data.result;
			}, function (error) {
				handleErrorResponse(error);
			});
		}

		/*Record Types*/
		function getOperation() {
			var requestData = {
				'moduleId': vm.auditReportAtDcLevelForAnySol.moduleId
			}
			auditReportAtDcLevelForAnySolService.getOperation(requestData).then(function (response) {
				vm.operationList = response.data.result;
			}, function (error) {
				handleErrorResponse(error);
			});
		}


		function getReportAtDCLevel() {
			vm.loader = true;
			var postdata = {};
			postdata.moduleId = vm.moduleId;
			if(!vm.branchId){
			    postdata.branchId = 'ALL';
			}else{
			    postdata.branchId = vm.branchId;
			}
			if(!vm.linkCode){
			    postdata.linkName = 'ALL';
			}else{
			    postdata.linkName = vm.linkCode;
			}
			if(!vm.tableShortName){
			    postdata.tableShortName = 'ALL';
			}else{
			    postdata.tableShortName = vm.tableShortName;
			}
			postdata.treasuryCode = vm.treasuryCode;
			postdata.stateCode = vm.stateCode;
			postdata.operation = vm.operation;
			postdata.recordStatus = vm.includeVerifiedRecords;
			postdata.fromDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
			postdata.toDate = $filter('date')(vm.toDate, "dd-MMM-yyyy");
            if(vm.operation == 'ADD')
                postdata.creModVerBy = vm.enteredBy;
            else if(vm.operation == 'MODIFY')
                postdata.creModVerBy = vm.modifiedBy;
            else if(vm.operation == 'VERIFY')
                postdata.creModVerBy = vm.verifiedBy;
            else if(vm.operation == 'DELETE')
                postdata.creModVerBy = vm.deletedBy;
            else if(vm.operation == 'ALL'){
                postdata.creModVerBy = "ALL";
            }

			auditReportAtDcLevelForAnySolService.getReportsAtDCLevelForAnySol(postdata)
				.then(
					function (response) {
						vm.auditReportList = response.data.result.auditReportResponseDTOS;

						if(vm.includeVerifiedRecords == 'V'){
						        vm.status = "VERIFIED";
						} else if(vm.includeVerifiedRecords == 'U'){
						        vm.status = "UN-VERIFIED";
						} else if(vm.includeVerifiedRecords =='A'){
						         vm.status = "AUTHORZIED"
						}
						else
					    {
						        vm.status = "VERIFIED AND UN-VERIFIED";
						}

                        vm.tableNameDescription = (vm.tableShortName) ? (vm.tableShortName) : ('ALL TABLES');
                        vm.linkNameDescription  = (vm.linkName) ? (vm.linkName) : ('ALL LINKS');

                        var tableDesc = 'Module Id:' +vm.moduleId+ '\n Table Name: ' +vm.tableNameDescription+
                        '\n Link Name: ' +vm.linkNameDescription+'\n Branch: ' +vm.branch+'\n Operation: ' +vm.operation+'\n Record Status: ' +vm.status;

                        vm.getTitleDescription = {
                            title: vm.reportName,
                            solId: $rootScope.globals.currentUser.solId,
                            solDesc: $rootScope.globals.currentUser.solDesc,
                            bankName: vm.bankName,
                            tableDesc : tableDesc
                        }

                        vm.dataTableOption = configDataTableColSpanExcel(vm.getTitleDescription);

						vm.loader = false;
					},
					function (error) {
						vm.loader = false;
						if (error.data.message == 'No Records Founds for the Given Input') {
							vm.NoDataFound = error.data.message;
						}else{
						    handleErrorResponse(error);
						    vm.goPreviousState();
						}
					}
				);
		}


		function submit() {
			getReportAtDCLevel();
		}

		//Get Module Name
		function getModuleName() {
			auditReportAtDcLevelForAnySolService.getModuleDetails().then(function (response) {
				vm.moduleNameList = response.data.result;
			}, function (error) {
				handleErrorResponse(error);
			});
		}


	}
})();