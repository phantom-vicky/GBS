(function () {
  "use strict";

  angular.module("gbmUltraApp").service("brokerageInvoiceConfirmationService", brokerageInvoiceConfirmationService);

  brokerageInvoiceConfirmationService.$inject = ["$http"];

  function brokerageInvoiceConfirmationService($http) {
    var brokerageInvoiceConfirmationService = this;

    brokerageInvoiceConfirmationService.getBrokerAgencyCode = getBrokerAgencyCode;
    brokerageInvoiceConfirmationService.getInvoiceConfirmationDetails = getInvoiceConfirmationDetails;
    brokerageInvoiceConfirmationService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    brokerageInvoiceConfirmationService.invoiceVerify = invoiceVerify;
    brokerageInvoiceConfirmationService.verifySubmit = verifySubmit;
    brokerageInvoiceConfirmationService.getInvoiceConfirmationVerify = getInvoiceConfirmationVerify;
    brokerageInvoiceConfirmationService.getInvoiceConfirmationInquiry = getInvoiceConfirmationInquiry;
    brokerageInvoiceConfirmationService.getRemarksList = getRemarksList;
    brokerageInvoiceConfirmationService.submitAddForm = submitAddForm;
    brokerageInvoiceConfirmationService.submitDeleteForm = submitDeleteForm;
    brokerageInvoiceConfirmationService.getDeleteSubmit = getDeleteSubmit;
    brokerageInvoiceConfirmationService.paymentConfirmation = paymentConfirmation;

    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    }

    function getBrokerAgencyCode(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/invoiceConfirmation/addTorch",
        data: requestData,
      });
    }

    function getInvoiceConfirmationDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/invoiceConfirmation/getInvoiceConfirmationDetails",
        data: requestData,
      });
    }
    function getInvoiceConfirmationVerify(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/invoiceConfirmation/getInvoiceConfirmationVerify",
        data: requestData,
      });
    }
    //                         getInvoiceConfirmationVerify

    function getRemarksList(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    }

    function getInvoiceConfirmationInquiry(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/invoiceConfirmation/getInvoiceConfirmationInquiry",
        data: requestData,
      });
    }
    function submitDeleteForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/invoiceConfirmation/submitDeleteForm",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    function getDeleteSubmit(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/invoiceConfirmation/getDeleteSubmit",
            headers: {
              "Content-Type": "application/json",
            },
            data: requestData,
          });
        }

    function invoiceVerify(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/invoiceConfirmation/invoiceVerify",
        data: requestData,
      });
    }

    function verifySubmit(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/invoiceConfirmation/verifySubmit",
            data: requestData,
          });
        }
  function paymentConfirmation() {
             return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/invoiceConfirmation/paymentConfirmation',
             })
          }
    function submitAddForm(requestData) {
      return $http.post("gbmultra/rbiBond/invoiceConfirmation/fileUpload/upload", requestData, {
        transformRequest: angular.identity,
        //           	   	transformResponse: angular.identity,
        headers: { "Content-Type": undefined, "Custom-Content-Type": "upload" },
      });
    }
  }
})();
