angular.module('gbmUltraApp').service('commonService', function ($http, $rootScope, $filter) {

	var commonService = {};
    
	/* Get Sol Date */
    commonService.getSolDate = function(){
    	return $http({
            method: "post",
            url: 'gbmultra/cbs/integration/soldate',
            data: {}
        })
    };

            commonService.textableDatePicker = function(id,valueDate){
           if(valueDate == undefined )
              {
              document.getElementById(id).value='';
                Swal.fire("Error!","Please enter valid Date", "error");

              }

            };
             commonService.futureNotAllowed = function(id,valueDate){
            var x=new Date();
                       if(valueDate.getFullYear() > x.getFullYear() )
                          {
                          document.getElementById(id).value='';
                            Swal.fire("Error!","Please don't enter future date ", "error");

                          }

                        };
/*auto slashing */
commonService.futureNotAllowedValidation = function(scope,id,valueDate){
             if (!valueDate) return;
                 // Convert dd/MM/yyyy → Date
                 let inputDate;
                 if (typeof valueDate === 'string') {
                     const parts = valueDate.split('/');
                     if (parts.length !== 3) return;
                     const [day, month, year] = parts.map(Number);
                     inputDate = new Date(year, month - 1, day);
                 } else {
                     inputDate = valueDate;
                 }
                 if (isNaN(inputDate.getTime())) return;
                 const today = new Date();
                 today.setHours(0, 0, 0, 0);
                 if (inputDate > today) {
                     // Clear via Angular binding — not DOM
                     scope.vm[id] = null;
                     Swal.fire("Error!", "Future dates are not allowed.", "error");
                 }
               };
    commonService.autoslashing = function(id){
        var x = document.getElementById(id).value;
        if(x.length == 2)
        {
        document.getElementById(id).value=x+'/';
        }else if(x.length == 5)
        {
        document.getElementById(id).value=x+'/';
        }
    };

  //validateInputAlphaWithSpaceOneApos
commonService.validateInputAlphaWithSpaceOneApos = function(event,id){
const textBox = document.getElementById(id);

      var charCode = event.which || event.keyCode;
        var charTyped = String.fromCharCode(charCode);

if(textBox)
{
 const cursorPosition = textBox.selectionStart;
    if(cursorPosition == 0 && charTyped === ' ')
    {
            event.preventDefault();
            return;

    }
    }
      var currentValue = event.target.value + charTyped;
        var pattern = /^[a-zA-Z\s]*'?([a-zA-Z\s]*'?)*$/;
if(event.key == "'"){
var count=0;
for(var i=0;i<=currentValue.length;i++){
if(currentValue[i] == "'")
{
count++;
}
}
if(count == 2)
{
  event.preventDefault();
}
}

        if (!pattern.test(currentValue)) {
            event.preventDefault();
        }
        };

    //validateInputAlphaWithSpace
        commonService.validateInputAlphaWithSpace = function(event,id){

        const textBox = document.getElementById(id);

          var charCode = event.which || event.keyCode;
            var charTyped = String.fromCharCode(charCode);

    if(textBox)
    {
     const cursorPosition = textBox.selectionStart;
        if(cursorPosition == 0 && charTyped === ' ')
        {
                event.preventDefault();
                return;

        }
        }
          var currentValue = event.target.value + charTyped;
            var pattern = /^[a-zA-Z\s]*'?([a-zA-Z\s]*'?)*$/;



            if (!pattern.test(currentValue)) {
                event.preventDefault();
            }
        };

    commonService.getMasterSolDate = function(){
    	return $http({
            method: "post",
            url: 'gbmultra/cbs/integration/master/soldate',
            data: {}
        })
    };

    commonService.getGbmSolDate = function(){
    	return $http({
            method: "post",
            url: 'cbdt/getGbmSolDate',
        })
    };

    commonService.getAllModules = function(){
    	return $http({
            method: "post",
            url: 'gbmultra/user/module/all',
            data : {}
        });
    };
    
    commonService.getTierTypes = function(){
    	return $http({
            method: "get",
            url: 'gbmultra/nps/subscriberdetailsinquiry/tier',
            params: {}
        })
    };
    
    
    
    /* Get Sol Date */
    commonService.getSolDateCallback = function(callback){
    	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/soldate',
            data: {}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    
    /* Get Tran Date */
    commonService.getTranDate = function(callback){
    	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/soldate',
            data: {}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    /* Get default Sol Details */
    commonService.getSolId = function(callback){
        /*var solDetails = [{
         solId: $rootScope.globals.currentUser.solId,
         solDesc: $rootScope.globals.currentUser.solDesc,
        }];
        return solDetails;*/
   	 	$http({
            method: "post",
            url: "gbmultra/common/loginsol",
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
            if(response.status==200){
                response.success = true;
                callback(response);
            }
        },
        function(response){
            callback(response);
        });
    };
    	

    /*Get state*/
    commonService.getStateDetails = function(callback){
   	 	$http({
            method: "get",
            url: "gbmultra/nps/statedetails",
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };   

    /*Get country*/
    commonService.getCountryDetails = function(callback){
   	 	$http({
            method: "get",
            url: "gbmultra/nps/countrydetails",
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };   
    

    /*Get Registration Number*/
    commonService.getRegistrationNumber = function(solId, callback){
        var postData = {solId: solId};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/registnumber",
            data: JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };   
    
    // Get Transaction Modes
    commonService.getTransactionModes = function(callback){
   	 	$http({
            method: "get",
            url: "nps/traninquiry/trantyperegist",
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    // Get Title
    commonService.getTitles = function(callback){
		var postData = {subCode: '003'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/subcodelist",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };

    // Get Categories
    commonService.getCategories = function(callback){
		var postData = {subCode: '002'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/subcodelist",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    
    // Get Proof Of Date Of Birth
    commonService.getProofOfDateOfBirth = function(callback){
		var postData = {subCode: '009'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/proofdetails",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    commonService.getProofOfIdentity = function(callback){
		var postData = {subCode: '005'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/proofdetails",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    commonService.getProofOfAddress = function(callback){
		var postData = {subCode: '006'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/proofdetails",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    
    commonService.getSchemeChoice = function(callback){
		var postData = {subCode: '004'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/subcodelist",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    commonService.getPfmChoice = function(callback){
		var postData = {subCode: '007'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/subcodelist",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    commonService.getOccupation = function(callback){
		var postData = {subCode: '023'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/subcodelist",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    commonService.getQualification = function(callback){
		var postData = {subCode: '024'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/subcodelist",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    commonService.getIncome = function(callback){
		var postData = {subCode: '025'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/subcodelist",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    
    
    commonService.getRelationship = function(callback){
		var postData = {subCode: '012'};
   	 	$http({
            method: "POST",
            url: "gbmultra/nps/subscriber/registration/subcodelist",
		    data : JSON.stringify(postData),
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    /*Get Tran Code*/    
    commonService.getTranCode = function(tranCode,callback){
   	 	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/trandetails',
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            },
            data : {tranCode : tranCode}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    /*Get Zone code*/
    commonService.getZoneCode = function(solId,zoneCode,callback){
        var zone = zoneCode.toUpperCase();
   	 	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/zonedetails',
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            },
            data : {solId : solId, zoneCode : zone}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };
    
    /*Get Branch Code*/    
    commonService.getBranchCode = function(bankCode,branchCode,callback){
        var branch = branchCode.toUpperCase();
   	 	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/bankbranchdetails',
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            },
            data : {bankCode : bankCode, branchCode : branch}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };   
    
    
    /*Get Bank Code*/    
    commonService.getBankCode = function(bankCode,callback){
   	 	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/bankdetails',
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            },
            data : {bankCode : bankCode}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    }; 

   /*Account Searcher API*/ 
    

    /*Scheme code*/
    commonService.getSchemeCode = function(schemeType,schemeCode,callback){
   	 	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/accountsearch/schemecode',
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            },
            data : {schemeType : schemeType, schemeCode : schemeCode}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    }; 
    
    /*GL Sub Head*/
    commonService.getGlSubHeadCode = function(schemeType,schemeCode,glSubHeadCode,callback){
   	 	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/accountsearch/glsubheadlist',
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            },
            data : {schemeType : schemeType, schemeCode : schemeCode, glSubHeadCode : glSubHeadCode}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    }; 
    
    /*Get Account List  */	    
    commonService.accountSearch = function(data,callback){
		 var parameter = data;
   	 	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/accountDtls',
            params: {}, data: parameter,
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            }
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };  
    /*Account Searcher API*/ 
    
    /*GL Sub Head*/
    commonService.getInstrumentType = function(accNum,solId,callback){
   	 	$http({
            method: "post",
            url: 'gbmultra/cbs/integration/instrumenttypes',
            headers: {
                'Content-type': 'application/json;charset=utf-8'
            },
            data : {solId : solId, foracid : accNum}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    }; 
    
    /* Get Service Type  */
    commonService.getServiceRequest = function(callback){
    	$http({
            method: "get",
            url: 'nps/receipt/servicerequest',
            params: {}
        }).then(function(response){
			if(response.status==200){
				response.success = true;
				callback(response);
			}
		},
		function(response){
			callback(response);
		});
    };

    /* Get Metadata by reference ID*/
    commonService.getMetaData = function(referenceId,successCallBack,errorCallBack) {
         $http({
                         method: 'POST',
                         url: "kvp/getReferenceData",
                         headers: {
                            'Content-Type': 'application/json'
                         },
                         data : { referenceId : referenceId }
         }).then(
          (response) =>  {
                let resultArray = [];
                response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                successCallBack(resultArray);
          },errorCallBack
         )
    }


  commonService.getMetaData = function(referenceId,successCallBack,errorCallBack) {
         $http({
                         method: 'POST',
                         url: "scss/getReferenceData",
                         headers: {
                            'Content-Type': 'application/json'
                         },
                         data : { referenceId : referenceId }
         }).then(
          (response) =>  {
                let resultArray = [];
                response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                successCallBack(resultArray);
          },errorCallBack
         )
    }
    /* Get records from table  by table name  and/or condition*/
    commonService.genericTableSelect = function(requestData) {
         requestData.sp = "SINGLE_TABLE_PLAIN_SELECT"
         return $http({
                         method: 'POST',
                         url: "cbdt/genericTableOperationsSp",
                         headers: {
                            'Content-Type': 'application/json'
                         },
                         data: requestData
         })
    }

        /* Get records from table  by table name  and/or condition*/
    commonService.genericTableSelectRbiBond = function(requestData) {
         requestData.sp = "SINGLE_TABLE_PLAIN_SELECT"
         return $http({
                         method: 'POST',
                         url: "rbiBond/genericTableOperationsSp",
                         headers: {
                            'Content-Type': 'application/json'
                         },
                         data: requestData
         })
    }

    /* Insert record into table by tableName  */
    commonService.genericTableInsert = function(table,columnValueModel) {
            var requestData = {};
            requestData.sp = "SINGLE_TABLE_PLAIN_INSERT";
            requestData.table = table;
            requestData.columnValues = convertToColumnValuesArray(columnValueModel)

            return $http({
                method: 'POST',
                url: "cbdt/genericTableOperationsSp",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
    }

    /* Modify record in table by tableName and ID*/
    commonService.genericTableModify = function(table,columnValueModel) {
            var requestData = {};
            requestData.sp = "SINGLE_TABLE_PLAIN_MODIFY";
            requestData.table = table;
            requestData.columnValues = convertToColumnValuesArray(columnValueModel)

            return $http({
                method: 'POST',
                url: "cbdt/genericTableOperationsSp",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
    }

    /*  Get unverified records from table*/
    commonService.genericTableSelectUnverifiedRecords = function(requestData) {
      requestData.sp = "SINGLE_TABLE_PLAIN_SELECT_UNVERIFIED"
      return $http({
                      method: 'POST',
                      url: "cbdt/genericTableOperationsSp",
                      headers: {
                         'Content-Type': 'application/json'
                      },
                      data: requestData
      })
    }

    /* Get verified Records from table */
    commonService.genericTableSelectVerifiedRecords = function(requestData) {
          if(!requestData.conditionList)
              requestData.conditionList = [];

          var verifiedStatusCondition = {
            colName : COLUMN_SHORT_CODES.VERIFIED_STATUS,
            value : "Y",
            condition : CONDITIONS.EQUAL,
            order : requestData.conditionList.length + 1
          }
          if(requestData.conditionList.length > 0)
            verifiedStatusCondition.conjunction = CONJUNCTIONS.AND;
          requestData.conditionList.push(verifiedStatusCondition);

          return commonService.genericTableSelect(requestData);
    }

    /* Get old/new value details before Verify or Delete  */
    commonService.genericTableSelectDetailsToVerifyOrDelete = function(requestData) {
      requestData.sp = "SINGLE_TABLE_SEL_VERIFY";

      return $http({
                      method: 'POST',
                      url: "cbdt/genericTableOperationsSp",
                      headers: {
                          'Content-Type': 'application/json'
                      },
                      data: requestData
      })
    }

    /* Verify record in table by tableName and ID */
    commonService.genericTableVerify = function(table,primaryKeyValue) {
       var requestData = {};
       requestData.sp = "SINGLE_TABLE_PLAIN_VERIFY";
       requestData.table = table;
       requestData.columnValues = convertToColumnValuesArray({ID : primaryKeyValue});

       return $http({
           method: 'POST',
           url: "cbdt/genericTableOperationsSp",
           headers: {
               'Content-Type': 'application/json'
           },
           data: requestData
       })
    }

    /* Delete actual/modified record in table by tableName and ID */
    commonService.genericTableDelete = function(table,primaryKeyValue) {
       var requestData = {};
       requestData.sp = "SINGLE_TABLE_PLAIN_DELETE";
       requestData.table = table;
       requestData.columnValues = convertToColumnValuesArray({ID : primaryKeyValue});

       return $http({
           method: 'POST',
           url: "cbdt/genericTableOperationsSp",
           headers: {
               'Content-Type': 'application/json'
           },
           data: requestData
       })
    }

    /* Inquiry actual/modified record in table by tableName and ID */
    commonService.genericTableInquiry = function(requestData) {
       requestData.sp = "SINGLE_TABLE_SEL_INQUIRY";

       return $http({
           method: 'POST',
           url: "cbdt/genericTableOperationsSp",
           headers: {
               'Content-Type': 'application/json'
           },
           data: requestData
       })
    }


    commonService.getActiveTreasuryList = function(){
       	 return	$http({
                method: "post",
                url: 'activeTreasuries',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
    };



     commonService.getLeastLevelSolsByTreasuryCodeAndSolId = function(treasuryCode,solId){
             return	$http({
                    method: "post",
                    url: 'getLeastLevelSolsByTreasuryCodeAndSolId',
                    headers: {
                        'Content-type': 'application/json;charset=utf-8'
                    },
                    data : {
                      treasuryCode : treasuryCode,
                      solId : solId
                    }
                });
            };


    commonService.getLeastLevelSolsByTreasuryCodeAndCurrentSolId = function(treasuryCode){
                 return	$http({
                        method: "post",
                        url: 'getLeastLevelSolsByTreasuryCodeAndCurrentSolId',
                        headers: {
                            'Content-type': 'application/json;charset=utf-8'
                        },
                        data : {
                          treasuryCode : treasuryCode
                        }
                    });
     };

//                commonService.getLastTwoFinancialYears = function(){
//                    	return $http({
//                            method: "get",
//                            url: 'gbmultra/common/finyear/currentFinancialyear',
//                            params: {}
//                        })
//                    };

    commonService.getLastTwoFinancialYears = function(callback){
            $http({
                method: "get",
                url: 'gbmultra/common/finyear/currentFinancialyear',
                params: {}
            }).then(function(response){
                if(response.status==200){
                    response.success = true;
                    callback(response);
                }
            },
            function(response){
                callback(response);
            });
    };

    commonService.getLevelsByTreasuryCode = function(treasuryCode,callback) {
             let requestData = {};
             requestData.table = 'T073';       // Level Master
             requestData.columnList = ['C002','C003'];
             requestData.conditionList = [
                 {
                      colName : COLUMN_SHORT_CODES.VERIFIED_STATUS,
                      value : "Y",
                      condition : CONDITIONS.EQUAL,
                      order : 1
                  },
                  {
                      colName : 'C001',
                      value : treasuryCode,
                      condition : CONDITIONS.EQUAL,
                      order : 2,
                      conjunction : CONJUNCTIONS.AND
                  }
             ]

             let resultArray=[];
             commonService.genericTableSelect(requestData).then(
                function(response) {
                  response.data.result.forEach(function(item){
                    resultArray.push({levelName : item.C003 ,levelId : item.C002})
                  })
                  callback(resultArray);
                }
             )

    }





/*** for SCSS ***/
    commonService.getAllBranches = function() {
            return  $http({
                   method: "POST",
                   url: 'scss/getAllBranches'
            });
    }

    commonService.getBranchesByTreasuryAndLevelId = function(treasuryCode,levelId) {
            return  $http({
                   method: "POST",
                   url: 'getSolsByTreasuryCodeAndLevelId',
                   data : {
                        treasuryCode : treasuryCode,
                        levelId : levelId
                   }
            });
    }

    commonService.getCategoriesByTreasury = function(treasuryCode) {
           return  $http({
                   method: "POST",
                   url: 'getCategoriesByTreasury',
                   data: {
                        treasuryCode : treasuryCode
                   }
            });
    }

    commonService.getTypesByTreasuryAndCategory = function(treasuryCode,categoryCode) {
           return  $http({
                   method: "POST",
                   url: 'getTypesByTreasuryAndCategory',
                   data: {
                        treasuryCode : treasuryCode,
                        categoryCode : categoryCode
                   }
            });
    }

    commonService.getComponentsByTreasuryAndCategoryAndType = function(treasuryCode,categoryCode,typeCode) {
           return  $http({
                   method: "post",
                   url: 'getComponentsByTreasuryAndCategoryAndType',
                   data : {
                        treasuryCode : treasuryCode,
                        categoryCode : categoryCode,
                        typeCode : typeCode
                   }
            });
    }

    commonService.getCertificatesByTreasuryAndCategoryAndType = function(treasuryCode,categoryCode,typeCode) {
           return  $http({
                   method: "post",
                   url: 'getCertificatesByTreasuryAndCategoryAndType',
                   data : {
                        treasuryCode : treasuryCode,
                        categoryCode : categoryCode,
                        typeCode : typeCode
                   }
            });
    }

    /*Sol Details*/
    commonService.getSolAllDetails = function(solid, callback){
    		if(!solid){
    			solid = '';
    		}
       	 	$http({
                method: "post",
                url: "gbmultra/cbs/integration/soldetails",
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                data : {solId : solid}
            }).then(function(response){
    			if(response.status==200){
    				response.success = true;
    				callback(response);
    			}
    		},
    		function(response){
    			callback(response);
    		});
        };

        /*Scheme type*/
        commonService.getSchemeType = function(callback){
       	 	$http({
                method: "post",
                url: 'gbmultra/cbs/integration/accountsearch/schemetype',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            }).then(function(response){
    			if(response.status==200){
    				response.success = true;
    				callback(response);
    			}
    		},
    		function(response){
    			callback(response);
    		});
        };

     /*account no search */
        commonService.getSchemeTypeListAcc = function(accNum,callback){
            $http({
                method: "post",
                url: 'gbmultra/cbs/integration/accountNumberExists',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                data : {accountNumber : accNum}
            }).then(function(response){
                if(response.status==200){
                    response.success = true;
                    callback(response);
                }
            },
            function(response){
                callback(response);
            });
        };



   /*Get state Details Pension*/
     /*commonService.getStateDetails = function(callback){
    	 	$http({
             method: "post",
             url: "gbmultra/pension/family/masters/listStateDetails",
             headers: {
                 'Content-type': 'application/json;charset=utf-8'
             }
         }).then(function(response){
 			if(response.status==200){
 				response.success = true;
 				callback(response);
 			}
 		},
 		function(response){
 			callback(response);
 		});
     };*/


     //Get Branch Id and Branch Desc from Treasury and LevelId
     commonService.getBranchIdAndBranchDescByTreasuryCodeAndLevelId = function(requestData){
              return $http({
                            method : "POST",
                            url : "gbmultra/pension/bodEodStatus/getBodEodStatus",
                            data : requestData
                          })
     };
    /*account no search */
        commonService.getSchemeTypeListAccNo = function(accNum,solId,callback){
            $http({
                method: "post",
                url: 'gbmultra/cbs/integration/accountNumberExistsSolid',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                data : {accountNumber : accNum, solId : solId}
            }).then(function(response){
                if(response.status==200){
                    response.success = true;
                    callback(response);
                }
            },
            function(response){
                callback(response);
            });
        };

    /*account no search */
        commonService.getSchemeTypeListAccNoWithAmount = function(accNum,amt,callback){
            $http({
                method: "post",
                url: 'gbmultra/cbs/integration/accountNumberWithAmount',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                data : {acctNum : accNum, amount : amt}
            }).then(function(response){
                if(response.status==200){
                    response.success = true;
                    callback(response);
                }
            },
            function(response){
                callback(response);
            });
        };
             commonService.getCustomAcctInqApi = function(accNum,callback){
            $http({
                method: "post",
                url: 'gbmultra/cbs/integration/getCustomAcctInqApi',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                data : {acctNum : accNum}
            }).then(function(response){
                if(response.status==200){
                    response.success = true;
                    callback(response);
                }
            },
            function(response){
                callback(response);
            });
        };
      /* Account Number and Cust ID Validation from CBS */
      commonService.accountNumCustIdValidate = function(customerId, accNum){
          return $http({
              method: 'post',
              url: 'gbmultra/cbs/integration/customerdetails/validate',
              data : {customerId : customerId, accountNumber : accNum}
          })
      }

      /* Account Number and Cust ID Validation from CBS */
      commonService.accountNumValidate = function(accNum){
          return $http({
              method: 'post',
              url: 'gbmultra/cbs/integration/customerdetails/accountvalidate',
              data : {accountNumber : accNum}
          })
      }

      /* Place Holder Account Number Validation from CBS */
      commonService.placeHolderAccNumValidate = function(accNum){
          return $http({
              method: 'post',
              url: 'gbmultra/cbs/integration/validateBacid',
              data : {bacid : accNum}
          })
      }
        /* shortCode number validation */
      commonService.validatesortNum = function(shortCode,callback){
              $http({
                  method: "post",
                  url: 'gbmultra/cbs/integration/mockservices/isValidSortCode',
                  headers: {
                      'Content-type': 'application/json;charset=utf-8'
                  },
                  data : {sortCode : shortCode}
              }).then(function(response){
                  if(response.status==200){
                      response.success = true;
                      callback(response);
                  }
              },
              function(response){
                  callback(response);
              });
          };

      /* instrument number validation */
      commonService.validateInstNum = function(instrumentNum, accountNum,callback){
                $http({
                  method: "post",
                  url: 'gbmultra/cbs/integration/instrumentcheck?instrumentNum='+instrumentNum+'&foracid='+accountNum,
                  headers: {
                      'Content-type': 'application/json;charset=utf-8'
                  },
                  data : {instrumentNum : instrumentNum, foracid : accountNum}
              }).then(function(response){
                if(response.status==200){
                    response.success = true;
                    callback(response);
                }
            },
            function(response){
                callback(response);
            });
          };

      /*APY GET Meta Data List */
      commonService.getApyMetaData = function(referenceId,successCallBack,errorCallBack) {
          $http({
                 method: 'POST',
                 url: "getApyMetaData",
                 headers: {
                    'Content-Type': 'application/json'
                 },
                 data : { referenceId : referenceId }
          }).then(
              (response) =>  {
                    let resultArray = [];
                    response.data.result.forEach((item) => resultArray.push({label : item.subCodeDescription,value : item.subCode}));
                    successCallBack(resultArray);
              },errorCallBack
          )
      }

      /*APY GET Meta Data List */
      commonService.getApyCommonMetaData = function(requestData,successCallBack,errorCallBack) {
          $http({
                 method: 'POST',
                 url: "getCommonApyMetaData",
                 headers: {
                    'Content-Type': 'application/json'
                 },
                 data : requestData
          }).then((response) =>  {
                    result = response.data.result;

                    commonMetaDataMap = {};
                    for(let item in result.metaDataList) {
                         commonMetaDataMap[item] = [];
                         for(let k of result.metaDataList[item].split("!")) {
                            vArr = k.split("~");
                            commonMetaDataMap[item].push({value : vArr[0],label : vArr[1]})
                         }
                    }

                    genericDataMap = {};
                    for(let item in result.genericDataList) {
                         genericDataMap[item] = [];
                         for(let k of result.genericDataList[item].split("#")) {
                            let tempObj  = {};
                            for(let j of k.split("|")){
                                vArr = j.split("~");
                                tempObj[vArr[0]] = vArr[1];
                            }
                            genericDataMap[item].push(tempObj) 	;
                         }
                    }
                    let resultDataObject = {
                        metaDataList: commonMetaDataMap,
                        genericDataList: genericDataMap
                    }
                    successCallBack(resultDataObject);
              },errorCallBack
          )
      }
      /*NPS GET Meta Data List */
      commonService.getNpsCommonMetaData = function(requestData,successCallBack,errorCallBack) {
          $http({
                 method: 'POST',
                 url: "gbmultra/nps/subscriber/registration/getCommonNpsMetaData",
                 headers: {
                    'Content-Type': 'application/json'
                 },
                 data : requestData
          }).then((response) =>  {
                    result = response.data.result;

                    commonMetaDataMap = {};
                    for(let item in result.metaDataList) {
                         commonMetaDataMap[item] = [];
                         for(let k of result.metaDataList[item].split("!")) {
                            vArr = k.split("~");
                            commonMetaDataMap[item].push({subCode : vArr[0],subCodeDescription : vArr[1]})
                         }
                    }

                    genericDataMap = {};
                    for(let item in result.genericDataList) {
                         genericDataMap[item] = [];
                         for(let k of result.genericDataList[item].split("#")) {
                            let tempObj  = {};
                            for(let j of k.split("|")){
                                vArr = j.split("~");
                                tempObj[vArr[0]] = vArr[1];
                            }
                            genericDataMap[item].push(tempObj) 	;
                         }
                    }
                    let resultDataObject = {
                        metaDataList: commonMetaDataMap,
                        genericDataList: genericDataMap
                    }
                    successCallBack(resultDataObject);
              },errorCallBack
          )
      }
      /*PENSION GET Meta Data List */
      commonService.getPensionCommonMetaData = function(requestData,successCallBack,errorCallBack) {
          $http({
                 method: 'POST',
                 url: "pensionCommonMetaData",
                 headers: {
                    'Content-Type': 'application/json'
                 },
                 data : requestData
          }).then((response) =>  {
                    result = response.data.result;

                    commonMetaDataMap = {};
                    for(let item in result.metaDataList) {
                         commonMetaDataMap[item] = [];
                         for(let k of result.metaDataList[item].split("!")) {
                            vArr = k.split("~");
                            commonMetaDataMap[item].push({value : vArr[0],label : vArr[1]})
                         }
                    }

                    genericDataMap = {};
                    for(let item in result.genericDataList) {
                         genericDataMap[item] = [];
                         for(let k of result.genericDataList[item].split("#")) {
                            let tempObj  = {};
                            for(let j of k.split("|")){
                                vArr = j.split("~");
                                tempObj[vArr[0]] = vArr[1];
                            }
                            genericDataMap[item].push(tempObj) 	;
                         }
                    }
                    let resultDataObject = {
                        metaDataList: commonMetaDataMap,
                        genericDataList: genericDataMap
                    }
                    successCallBack(resultDataObject);
              },errorCallBack
          )
      }

      /*SCSS GET Meta Data List */
      commonService.getScssCommonMetaData = function(requestData,successCallBack,errorCallBack) {
          $http({
                 method: 'POST',
                 url: "scss/getCommonMetaData",
                 headers: {
                    'Content-Type': 'application/json'
                 },
                 data : requestData
          }).then((response) =>  {
                    result = response.data.result;

                    commonMetaDataMap = {};
                    for(let item in result.metaDataList) {
                         commonMetaDataMap[item] = [];
                         for(let k of result.metaDataList[item].split("!")) {
                            vArr = k.split("~");
                            commonMetaDataMap[item].push({value : vArr[0],label : vArr[1]})
                         }
                    }

                    genericDataMap = {};
                    for(let item in result.genericDataList) {
                         genericDataMap[item] = [];
                         for(let k of result.genericDataList[item].split("#")) {
                            let tempObj  = {};
                            for(let j of k.split("|")){
                                vArr = j.split("~");
                                tempObj[vArr[0]] = vArr[1];
                            }
                            genericDataMap[item].push(tempObj) 	;
                         }
                    }
                    let resultDataObject = {
                        metaDataList: commonMetaDataMap,
                        genericDataList: genericDataMap
                    }
                    successCallBack(resultDataObject);
              },errorCallBack
          )
      }

      /*PPF GET Meta Data List */
      commonService.getPpfCommonMetaData = function(requestData,successCallBack,errorCallBack) {
          $http({
                 method: 'POST',
                 url: "ppf/commonMetaGenericData",
                 headers: {
                    'Content-Type': 'application/json'
                 },
                 data : requestData
          }).then((response) =>  {
                    result = response.data.result;

                    commonMetaDataMap = {};
                    for(let item in result.metaDataList) {
                         commonMetaDataMap[item] = [];
                         for(let k of result.metaDataList[item].split("!")) {
                            vArr = k.split("~");
                            commonMetaDataMap[item].push({value : vArr[0],label : vArr[1]})
                         }
                    }

                    genericDataMap = {};
                    for(let item in result.genericDataList) {
                         genericDataMap[item] = [];
                         for(let k of result.genericDataList[item].split("#")) {
                            let tempObj  = {};
                            for(let j of k.split("|")){
                                vArr = j.split("~");
                                tempObj[vArr[0]] = vArr[1];
                            }
                            genericDataMap[item].push(tempObj) 	;
                         }
                    }
                    let resultDataObject = {
                        metaDataList: commonMetaDataMap,
                        genericDataList: genericDataMap
                    }
                    successCallBack(resultDataObject);
              },errorCallBack
          )
      }

      /*SSA GET Meta Data List */
      commonService.getSsaCommonMetaData = function(requestData,successCallBack,errorCallBack) {
          $http({
                 method: 'POST',
                 url: "ssa/getCommonMetaData",
                 headers: {
                    'Content-Type': 'application/json'
                 },
                 data : requestData
          }).then((response) =>  {
                    result = response.data.result;

                    commonMetaDataMap = {};
                    for(let item in result.metaDataList) {
                         commonMetaDataMap[item] = [];
                         for(let k of result.metaDataList[item].split("!")) {
                            vArr = k.split("~");
                            commonMetaDataMap[item].push({value : vArr[0],label : vArr[1]})
                         }
                    }

                    genericDataMap = {};
                    for(let item in result.genericDataList) {
                         genericDataMap[item] = [];
                         for(let k of result.genericDataList[item].split("#")) {
                            let tempObj  = {};
                            for(let j of k.split("|")){
                                vArr = j.split("~");
                                tempObj[vArr[0]] = vArr[1];
                            }
                            genericDataMap[item].push(tempObj) 	;
                         }
                    }
                    let resultDataObject = {
                        metaDataList: commonMetaDataMap,
                        genericDataList: genericDataMap
                    }
                    successCallBack(resultDataObject);
              },errorCallBack
          )
      }

      /* Get Metadata by PPF reference ID*/
      commonService.getPpfMetaData = function(referenceId,successCallBack,errorCallBack) {
           $http({
               method: 'POST',
               url: "ppf/getReferenceData",
               headers: {
                  'Content-Type': 'application/json'
               },
               data : { referenceId : referenceId }
           }).then(
            (response) =>  {
                  let resultArray = [];
                  response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                  successCallBack(resultArray);
            },errorCallBack
           )
      }
      commonService.getPPFMetaData = function(referenceId,successCallBack,errorCallBack) {
           $http({
                   method: 'POST',
                   url: "ppf/getMetaData",
                   headers: {
                      'Content-Type': 'application/json'
                   },
                   data : { referenceId : referenceId }
           }).then(
            (response) =>  {
                  let resultArray = [];
                  response.data.result.forEach((item) => resultArray.push({label : item.subCodeDescription,value : item.subCode}));
                  successCallBack(resultArray);
            },errorCallBack
           )
      }

      commonService.getModuleNameDetails = function(requestData){
            return $http({
                method: 'POST',
                url: 'cbdt/getModuleDetails', //changesh
                data :requestData
            })
      }
        commonService.getSCSSModuleNameDetails = function(requestData){
                  return $http({
                      method: 'POST',
                      url: 'scss/getModuleDetails', //changesh
                      data :requestData
                  })
            }

      commonService.getBankName = function(){
            return $http({
                method: "post",
                url: 'cbdt/getBankName',
            })
      };
      /* Place Holder Account Number Validation from CBS */
      commonService.getMinorAge = function(){
            return $http({
                method: 'post',
                url: 'ppf/minorAge'
            })
      }

       /* Get SSA Metadata by reference ID*/
       commonService.getSSAMetaData = function(referenceId,successCallBack,errorCallBack) {
             $http({
                 method: 'POST',
                 url: "ssa/getMetaData",
                 headers: {
                    'Content-Type': 'application/json'
                 },
                 data : { referenceId : referenceId }
             }).then(
              (response) =>  {
                    let resultArray = [];
                    response.data.result.forEach((item) => resultArray.push({label : item.subCodeDescription,value : item.subCode}));
                    successCallBack(resultArray);
              },errorCallBack
             )
       }



        //GET META DATA FR SCSS
         commonService.getMetaDataForScss = function(requestData){
            return $http({
                method: 'POST',
                url: 'scss/getMetaDataForScss',
                data :requestData
            })
        }

        /* Get Metadata by SCSS reference ID*/
        commonService.getScssMetaDatList = function(referenceId,successCallBack,errorCallBack) {
             $http({
                             method: 'POST',
                             url: "scss/getReferenceData",
                             headers: {
                                'Content-Type': 'application/json'
                             },
                             data : { referenceId : referenceId }
             }).then(
              (response) =>  {
                    let resultArray = [];
                    response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                    successCallBack(resultArray);
              },errorCallBack
             )
        }

          /* Get Metadata by SSA reference ID*/
        commonService.getSsaMetaDatList = function(referenceId,successCallBack,errorCallBack) {
             $http({
                             method: 'POST',
                             url: "ssa/getReferenceData",
                             headers: {
                                'Content-Type': 'application/json'
                             },
                             data : { referenceId : referenceId }
             }).then(
              (response) =>  {
                    let resultArray = [];
                    response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                    successCallBack(resultArray);
              },errorCallBack
             )
        }

        /*Get Meta Data for Every lst based on the listtype*/
         commonService.getMetaDataList = function(requestData){
                 return $http({
                     method : "POST",
                     url : 'scss/getMetaDatList',
                     data : requestData
                 });
         }
        /*Get Meta Data for Every lst based on the listtype*/
         commonService.getKVPMetaData = function(requestData){
                 return $http({
                     method : "post",
                     url : 'kvp/getMetaDatList',
                     data : requestData
                 });
         }

        commonService.getMetaDataForGst = function(code,successCallBack,errorCallBack) {
             $http({
                             method: 'POST',
                             url: "getMetaDataForGst",
                             headers: {
                                'Content-Type': 'application/json'
                             },
                             data : { code : code }
             }).then(
              (response) =>  {
                    let resultArray = [];
                    response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                    successCallBack(resultArray);
              },errorCallBack
             )
        }

        /* Get KVP Metadata by reference ID*/
        commonService.getKvpMetaDataList = function(referenceId,successCallBack,errorCallBack) {
             $http({
                 method: 'POST',
                 url: "getKvpReferenceData",
                 headers: {
                    'Content-Type': 'application/json'
                 },
                 data : { referenceId : referenceId }
             }).then(
              (response) =>  {
                    let resultArray = [];
                    response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                    successCallBack(resultArray);
              },errorCallBack
             )
        }

        /*Pension Common Service*/
      /* Get Module Dropdown Values */
        commonService.getPensionModuleDetails = function(requestData){
            return $http({
                method: 'POST',
                url: 'pension/getModuleDetails',
                data: requestData
            })
        }
      /* Get Pension Tyep Values */
      commonService.getPensionType = function(requestData){
          return $http({
              method: 'POST',
              url: 'getNatureOfPension',
              data: requestData
          })
      }

    /*Get Effective Balance By Operative Account Number*/
      commonService.getEffectiveBalanceByOperativeAccNo = function(operativeAccNo) {
          return $http({
             method: "get",
             url: "gbmultra/cbs/integration/effectivebalance",
             headers: {
                 'Content-type': 'application/json'
             },
             params : {
                  accountNumber : operativeAccNo
             }
          })
      }

      /* Get CPPC Name And Address */
        commonService.getCppcNameAndAddress = function(requestData){
            return $http({
                method: 'post',
                url: 'getCPPCAddressDetails',
                //data: requestData
            })
        }
        /* Get PPO Number Search */
        commonService.getPpoNumberSearch = function(requestData){
            return $http({
                method: 'POST',
                url: 'getPPODetails',
                data: requestData
            })
        }

        /* Get Pension Type Search */
        commonService.getPensionTypeDetails = function(requestData){
            return $http({
                method: 'POST',
                url: 'getPensionTypeDetails',
                data: requestData
            })
        }

        commonService.getPensionCategory = function(requestData){
            return $http({
                method: 'POST',
                url: 'getPensionCategoryDetails',
                data: requestData
            })
        }

         commonService.getPpoNumberSearchForInquiry = function(requestData){
            return $http({
                method: 'POST',
                url: 'getPPODetailsForInquiry',
                data: requestData
            })
        }

        commonService.getMonthlyAdvisedRecoveryAmt = function(ppAcctNum){
            return $http({
                method: 'POST',
                url: 'getMonthlyAdvisedRecoveryAmt',
                data: {
                    ppAcctNum : ppAcctNum
                }
            })
        }

    /* Get Metadata by reference ID*/
     commonService.getPensionMetaData = function(referenceId,successCallBack,errorCallBack) {
          $http({
                          method: 'POST',
                          url: "pension/getReferenceData",
                          headers: {
                             'Content-Type': 'application/json'
                          },
                          data : { referenceId : referenceId }
          }).then(
           (response) =>  {
                 let resultArray = [];
                 response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                 successCallBack(resultArray);
           },errorCallBack
          )
     }

      commonService.getPensionHierarchyDetail = function(requestData){
             return	$http({
                 method : "post",
                 url : 'getPensionHierarchyDetails',
                 data :requestData
                 });
      }

        /** NPS **/

        /* Get Admin BOD Status */
        commonService.getAdminBodStatus = function(callback){
        var solDate = $filter('date')(convertToDateObject($rootScope.globals.currentUser.solDate), "dd-MM-yyyy");
        var solId = $rootScope.globals.currentUser.solId;
         return	$http({
                method: "POST",
                url: 'nps/npsbod/inquiry/bod/admin/level',
				data: JSON.stringify({bodStatus: 'C', solId: solId, date:solDate}),
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
        };


        /* Get BOD POP Status */
        commonService.getPOPBodStatus = function(callback){
            var solDate = $filter('date')(convertToDateObject($rootScope.globals.currentUser.solDate), "dd-MM-yyyy");            ;
            var solId = $rootScope.globals.currentUser.solId;
             return	$http({
                method: "POST",
                url: 'nps/npsbod/inquiry/bod/pop/level',
				data: JSON.stringify({bodStatus: 'C', solId: solId, date:solDate}),
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
        };

        /* Get BOD POP-SP Status */
        commonService.getPOPSPBodStatus = function(callback){
        var solDate = $filter('date')(convertToDateObject($rootScope.globals.currentUser.solDate), "dd-MM-yyyy");;
        var solId = $rootScope.globals.currentUser.solId;
         return	$http({
                method: "POST",
                url: 'nps/npsbod/inquiry/bod/popsp/level',
				data: JSON.stringify({bodStatus: 'C', solId: solId, date:solDate}),
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
        };

        /* Get Lite Monitoring Service Type  */
        commonService.getMonitoringLiteServiceRequest = function(callback){
            $http({
                method: "get",
                url: 'gbmultra/nps/pop/monitoringofservices/servicerequestlite',
                params: {}
            }).then(function(response){
                if(response.status==200){
                    response.success = true;
                    callback(response);
                }
            },
            function(response){
                callback(response);
            });
        };

        /* Get Lite Service Type  */
        commonService.getLiteServiceRequest = function(callback){
            $http({
                method: "post",
                url: 'nps/receipt/lite/servicerequest'
            }).then(function(response){
                if(response.status==200){
                    response.success = true;
                    callback(response);
                }
            },
            function(response){
                callback(response);
            });
        };

    /* Get Metadata by reference ID for state Tax*/
    commonService.getMetaDataForStateTax = function(code,successCallBack,errorCallBack) {
                 $http({
                                 method: 'POST',
                                 url: "getMetaDataUsingCodeStateTax",
                                 headers: {
                                    'Content-Type': 'application/json'
                                 },
                                 data : { code : code }
                 }).then(
                  (response) =>  {
                        let resultArray = [];
                        response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                        successCallBack(resultArray);
                  },errorCallBack
                 )
            }

    /* Get State Tax scheme name*/
    commonService.getStateTaxSchemeName = function() {
         return $http({
                 method: 'POST',
                 url: "getStateTaxSchemeName"
         })
    }

      /* Get Metadata by CBDT reference ID*/
    commonService.getCBDTMetaDataList = function(referenceId,successCallBack,errorCallBack) {
         $http({
                         method: 'POST',
                         url: "cbdt/getReferenceData",
                         headers: {
                            'Content-Type': 'application/json'
                         },
                         data : { referenceId : referenceId }
         }).then(
          (response) =>  {
                let resultArray = [];
                response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                successCallBack(resultArray);
          },errorCallBack
         )
    }

     commonService.getEfreightMetaDataList = function(referenceId,successCallBack,errorCallBack) {
             $http({
                             method: 'POST',
                             url: "efreight/getReferenceData",
                             headers: {
                                'Content-Type': 'application/json'
                             },
                             data : { referenceId : referenceId }
             }).then(
              (response) =>  {
                    let resultArray = [];
                    response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                    successCallBack(resultArray);
              },errorCallBack
             )
        }

             commonService.getRbiBondMetaDataList = function(referenceId,successCallBack,errorCallBack) {
                     $http({
                                     method: 'POST',
                                     url: "rbiBond/getReferenceData",
                                     headers: {
                                        'Content-Type': 'application/json'
                                     },
                                     data : { referenceId : referenceId }
                     }).then(
                      (response) =>  {
                            let resultArray = [];
                            response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                            successCallBack(resultArray);
                      },errorCallBack
                     )
                }

    /* Get Metadata by ICEGATE reference ID*/

    commonService.getICEGATEMetaDataList = function(referenceId,successCallBack,errorCallBack) {
             $http({
                             method: 'POST',
                             url: "/icegate/getReferenceData",
                             headers: {
                                'Content-Type': 'application/json'
                             },
                             data : { referenceId : referenceId }
             }).then(
              (response) =>  {
                    let resultArray = [];
                    response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                    successCallBack(resultArray);
              },errorCallBack
             )
        }

    commonService.getMetaDataForCBDT = function(referenceId,successCallBack,errorCallBack) {
         $http({
                         method: 'POST',
                         url: "cbdt/getMetaData",
                         headers: {
                            'Content-Type': 'application/json'
                         },
                         data : { referenceId : referenceId }
         }).then(
          (response) =>  {
                let resultArray = [];
                response.data.result.forEach((item) => resultArray.push({label : item.subCodeDescription,value : item.subCode}));
                successCallBack(resultArray);
          },errorCallBack
         )
    }

      commonService.getCBDTModuleNameDetails = function(requestData){
            return $http({
                method: 'POST',
                url: 'cbdt/getModuleDetails',
                data :requestData
            })
      }

         /* Get Metadata by ICEGATE reference ID*/
          commonService.getICEGATEMetaDataList = function(referenceId,successCallBack,errorCallBack) {
               $http({
                               method: 'POST',
                               url: "icegate/getReferenceData",
                               headers: {
                                  'Content-Type': 'application/json'
                               },
                               data : { referenceId : referenceId }
               }).then(
                (response) =>  {
                      let resultArray = [];
                      response.data.result.forEach((item) => resultArray.push({label : item.DESCRIPTION,value : item.SUBCODE}));
                      successCallBack(resultArray);
                },errorCallBack
               )
          }

          commonService.getMetaDataForICEGATE = function(referenceId,successCallBack,errorCallBack) {
               $http({
                               method: 'POST',
                               url: "icegate/getMetaData",
                               headers: {
                                  'Content-Type': 'application/json'
                               },
                               data : { referenceId : referenceId }
               }).then(
                (response) =>  {
                      let resultArray = [];
                      response.data.result.forEach((item) => resultArray.push({label : item.subCodeDescription,value : item.subCode}));
                      successCallBack(resultArray);
                },errorCallBack
               )
          }

            commonService.getICEGATEModuleNameDetails = function(requestData){
                  return $http({
                      method: 'POST',
                      url: 'icegate/getModuleDetails',
                      data :requestData
                  })
            }

             /* Get records from table  by table name  and/or condition*/
                commonService.icegateGenericTableSelect = function(requestData) {
                     requestData.sp = "SINGLE_TABLE_PLAIN_SELECT"
                     return $http({
                                     method: 'POST',
                                     url: "icegate/genericTableOperationsSp",
                                     headers: {
                                        'Content-Type': 'application/json'
                                     },
                                     data: requestData
                     })
                }

       commonService.getLatestWarDeploymentDetails = function(){
            return $http({
                method: 'POST',
                url: 'gbmultra/user/getWarDeploymentDetails'
            })
      }

       commonService.getTreasuries = function() {
        let requestData = {};
            requestData.fetchType = "T";
            return commonService.loginSolIdBasedDetails(requestData)
       }

        commonService.getLeastLevelBranchesByTreasury = function(treasuryCode) {
               var requestData = {
                   'treasuryCode': treasuryCode,
                   'fetchType': 'B',
                   'levelOrBranchFlag': 'B'
               };
               return commonService.loginSolIdBasedDetails(requestData);
        }

         /*  Get Login SolId Based Details Details */
         commonService.loginSolIdBasedDetails = function (requestData)
         {
            return	$http({
                            method : "post",
                            url : 'getPensionHierarchyDetails',
                            data :requestData
                            });
         }

         /*to get stCode by sending State Name*/
          commonService.getStCodeUsingStateName = function (requestData)
          {
             return	$http({
                 method : "POST",
                 url : 'st/getStCodeUsingStCodeDesc',
                 data :requestData
             });
          }

    commonService.getCommutationComponentsByTreasuryAndCategoryAndType = function(treasuryCode,categoryCode,typeCode) {
           return  $http({
                   method: "post",
                   url: 'getCommutationCompByTrsyAndCateAndType',
                   data : {
                        treasuryCode : treasuryCode,
                        categoryCode : categoryCode,
                        typeCode : typeCode
                   }
            });
    }

    commonService.getScssBankName = function(){
                return $http({
                    method: "post",
                    url: 'scss/getBankName',
                })
          };
	return commonService;
});