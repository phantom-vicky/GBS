(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("acknowledgementconfirmationController", acknowledgementconfirmationController);

  acknowledgementconfirmationController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "$timeout", "acknowledgementConfirmationService", "commonService"];

  function acknowledgementconfirmationController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, acknowledgementConfirmationService, commonService) {
    var vm = this;
    $scope.vm = vm;

    vm.moduleName = "RBI Bond";
    vm.getModuleName = getModuleName;
    vm.getDetails = getDetails;

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
    vm.subsDate1 = moment(vm.solDate).format("DD-MM-YYYY");
    vm.solDate1 = $rootScope.globals.currentUser.solDate;

       // Pagination
       vm.filteredRecords = [];
       vm.currentPage = 1;
       vm.maxPagesToShow = 5;
       vm.searchText = "";
       vm.loading = true;
       vm.showExportOptions = false;
       vm.itemsPerPage = "5";
       vm.showNext = false;
       vm.showPrevious = false;
       $scope.sortReverse = false;
       $scope.sort = function (key) {
         $scope.sortKey = key;
         $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
         $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
         vm.sortField = key;
         vm.getDetails();
       };

       vm.pageNo = 1;
       vm.pageSize = 5;

       vm.goPreviousPage = function () {
         vm.pageNo = parseInt(vm.pageNo) - 1;
         vm.getDetails();
       };

       vm.goNextPage = function () {
         vm.pageNo = parseInt(vm.pageNo) + 1;
         vm.getDetails();
       };

       vm.paginationControls = function () {
         vm.pages = [];
         for (var i = 1; i <= parseInt(vm.maxPages); i++) {
           vm.pages.push(parseInt(i));
         }
         if (vm.pageNo != 1) {
           vm.showPrevious = true;
         } else {
           vm.showPrevious = false;
         }
         if (vm.pageNo != vm.maxPages) {
           vm.showNext = true;
         } else {
           vm.showNext = false;
         }
       };

    if ($state.current.name == "acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection.inquiry_detail") {
      vm.loader = true;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.toDate = $stateParams.obj.toDate;
      vm.statusIO = $stateParams.obj.statusIO;

      if (vm.statusIO == "S001") {
        vm.status = "ACCEPTED";
      } else if (vm.statusIO == "S002") {
        vm.status = "REJECTED";
      }
      vm.getDetails();
    }

    function getModuleName() {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };

      acknowledgementConfirmationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        statusIO: vm.statusIO,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "AR",
        pageNo: vm.pageNo,
                pageSize: vm.pageSize,
                searchValue: vm.searchText,
                sortOrder: vm.sortOrder,
                sortField: vm.sortField,
      };
      acknowledgementConfirmationService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.filteredRecords = response.data.result;
          vm.detailsList = response.data.result;
          vm.records = response.data.result;
          vm.total = response.data.total;
                    vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                    vm.paginationControls();
                    if (vm.detailsList.length === 0) {
                      vm.noDataFoundInSearch = true;
                    } else {
                      vm.noDataFoundInSearch = false;
                    }
        },
        function (error) {
        vm.loader = false;
                  if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchText) {


                             vm.pageNo ='0';
                             vm.maxPages ='0';
                             vm.total = '0';
                              vm.showNext = false;
                    vm.noDataFoundInSearch = true;
                  } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                    vm.noDataFound = error.data.message;
                  } else {
                    handleErrorResponse(error);
                    $state.go("acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection.inquiry");
                  }
        }
      );
    }

    function fromDateVal() {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    function toDateVal(fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: new Date(),
        showWeeks: false,
      };
    }
    vm.formatDate = function (inputDate) {
      const date = new Date(inputDate);
      const day = String(date.getDate()).padStart(2, "0"); // Ensure two digits
      const month = date.toLocaleString("en-GB", { month: "short" }).toUpperCase(); // Get short month in uppercase
      const year = date.getFullYear(); // Get year

      return `${day}-${month}-${year}`;
    };

    vm.downloadInvoice = function (detailsList) {
      if (!detailsList) {
        alert("No record selected for download.");
        return;
      }

      vm.loader = true;
      if (vm.statusIO === "S001") {
        var requestData = {
          blaNo: detailsList.blaNo,
          schemeCode: vm.schemeCode,
          statusIO: vm.statusIO,
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          flag: "A",
          inwardRefNo: detailsList.inwardRefNo,
          pageNo: "1",
                  pageSize: vm.total,
                  searchValue: vm.searchText,
        };

        acknowledgementConfirmationService.getDetails(requestData).then(
          function (response) {
            vm.loader = false;
            vm.downloadDetails = response.data.result;
            generateAcceptedPDF(vm.downloadDetails);
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      }
      if (vm.statusIO === "S002") {
        var requestData = {
          blaNo: detailsList.blaNo,
          schemeCode: vm.schemeCode,
          statusIO: vm.statusIO,
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          flag: "R",
          pageNo: "1",
                  pageSize: vm.total,
                  searchValue: vm.searchText,
        };
        acknowledgementConfirmationService.getDetails(requestData).then(
          function (response) {
            vm.loader = false;
            vm.downloadDetails = response.data.result;
            generateRejectionPDF(vm.downloadDetails);
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      }

function generateAcceptedPDF(x) {

  // Load the HDFC logo first...
  loadImageAsBase64("assets/images/HDFC-Logo.png", function (logoData) {

    // ...then load the signature image...
    loadImageAsBase64("assets/images/nageswarannew.png", function (imgData) {

      if (!vm.downloadDetails) {
        alert("No details available to generate PDF.");
        return;
      }

      const pdf = new window.jspdf.jsPDF({
        orientation: "p",
        unit: "mm",
        format: [210, 400],
      });

      let yPosition = 10;
      pdf.setFontSize(10);

      const pageHeight = pdf.internal.pageSize.getHeight();
      const bottomMargin = 15;
      const lineHeight = 5;
      const usablePageHeight = pageHeight - bottomMargin - 10;

      const addressLines = ["HDFC Bank Ltd.,", "CMS-GOI Bonds - 2nd Floor,", "GKS Technology Park,", "Wavin Main Road, Mogappair,", "Chennai – 600058,", "Tel: 044-69129917"];

      let maxTextWidth = Math.max(...addressLines.map((text) => pdf.getTextWidth(text)));
      let rightMargin = pdf.internal.pageSize.getWidth() - maxTextWidth - 10;

      // Height the logo + address block occupies at the top of any page
      const logoWidth = 63;
      const logoHeight = 13;
      const headerBlockHeight = Math.max(10 + logoHeight, 10 + addressLines.length * lineHeight) + 4;

function drawPageHeader() {
    if (logoData) {
        pdf.addImage(logoData, "PNG", 12, 10, logoWidth, logoHeight);
    }

      const prevFont = pdf.getFont();

      pdf.setFont("helvetica", "normal");

    // Address beside the HDFC logo
    let addrY = 12;

      addressLines.forEach((line) => {
          pdf.text(line, rightMargin, addrY);
          addrY += lineHeight;
      });

    pdf.setFont(prevFont.fontName, prevFont.fontStyle);

    yPosition = Math.max(headerBlockHeight, addrY + 4);
}
      // Wrapper so every new page automatically gets the header
      function newPage() {
        pdf.addPage();
        drawPageHeader();
      }

      function ensureSpace(neededHeight = lineHeight) {
        if (yPosition + neededHeight > pageHeight - bottomMargin) {
          newPage();
        }
      }

      function reserveBlock(neededHeight) {
        const remaining = pageHeight - bottomMargin - yPosition;
        if (neededHeight > remaining && neededHeight <= usablePageHeight) {
          newPage();
        }
      }

      function addText(text) {
        ensureSpace(lineHeight);
        pdf.text(text, 10, yPosition);
        yPosition += lineHeight;
      }

      function addTextBlock(lines) {
        reserveBlock(lines.length * lineHeight);
        lines.forEach(addText);
      }

      // ---- Draw header on the first page ----
      drawPageHeader();

      addHeader(x);

      function addHeader(x) {
        let inwardRefNo = x[0].inwardRefNo || "N/A";
let inwardDate = x[0].inwardDate || "N/A";

if (inwardDate !== "N/A") {
    const parts = inwardDate.split("-");
    inwardDate = parts[0] + "-" +
                 parts[1].charAt(0).toUpperCase() +
                 parts[1].slice(1).toLowerCase() + "-" +
                 parts[2];
}



        let pageWidth = pdf.internal.pageSize.getWidth();
        let refMargin = pageWidth - 67;

        ensureSpace(lineHeight);
        pdf.setFont("helvetica", "bold");
        pdf.text(`Inward Ref No :`, refMargin, yPosition);
        pdf.setFont("helvetica", "normal");
        pdf.text(inwardRefNo, 170, yPosition);
        yPosition += lineHeight;

        ensureSpace(lineHeight);
        pdf.setFont("helvetica", "bold");
        pdf.text(`Inward Ref Date :`, refMargin, yPosition);
        pdf.setFont("helvetica", "normal");
        pdf.text(inwardDate, refMargin + 30, yPosition);
        yPosition += 5;

        yPosition += 5;

        pdf.setFont("helvetica", "bold");

const date = new Date();
const months = [
    'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
];

const formattedDate =
    String(date.getDate()).padStart(2, '0') + '-' +
    months[date.getMonth()] + '-' +
    date.getFullYear();

addText("Date: " + formattedDate);        addText(" ");
        addText(`${x[0].holderName || "N/A"}`);
        pdf.setFont("helvetica", "normal");
const addressParts = [
    x[0].addResssOne,
    x[0].addResssTwo,
    x[0].addResssThree
].filter(value => value != null && String(value).trim() !== "");
addressParts.forEach(value => {
    addText(String(value).trim() + ",");
});
const cityPin = [
    x[0].city,
    x[0].pinCode
].filter(value => value != null && String(value).trim() !== "");
if (cityPin.length > 0) {
    addText(cityPin.join(" - ") + ",");
}
if (x[0].state != null && String(x[0].state).trim() !== "") {
    addText(String(x[0].state).trim() + ",");
}
addText("INDIA");
        addText(" ");
        addText("Dear Customer,");
        addText(" ");

       const refText = `Ref : Confirmation letter for Change of Address/ECS/Nomination for Bond Ledger Account No. ${x[0].blaNo || "N/A"}`;

       const lines = pdf.splitTextToSize(refText, 190);
       addTextBlock(lines);
        addText(" ");

        addTextBlock([
          "With reference to your captioned letter received by us on  " + inwardDate + " .we wish to inform you that we have",
          "made necessary changes in our records.The details now appending in your account are given below:",
        ]);
        addText(" ");
        addText("1. Your address in our records is as mentioned above.");
                addText(" ");
        addText(`2. PAN : ${x[0].panNo || "N/A"}`);
        addText(" ");

      addText("3.  The Bank details for receiving credit of interest is :");

      ensureSpace(lineHeight);

      pdf.setFont("helvetica", "normal");

      pdf.text("A/c No.", 20, yPosition);
      pdf.text(":", 50, yPosition);
      pdf.text(String(x[0].accNo || "N/A"), 55, yPosition);
      yPosition += lineHeight;

      pdf.text("Micr Code", 20, yPosition);
      pdf.text(":", 50, yPosition);
      pdf.text(String(x[0].micrCode || "N/A"), 55, yPosition);
      yPosition += lineHeight;

      pdf.text("Bank Name", 20, yPosition);
      pdf.text(":", 50, yPosition);
      pdf.text(String(x[0].bankName || "N/A"), 55, yPosition);
      yPosition += lineHeight;
       addText(" ");

        addText("4. Nomination Details below:");

        pdf.setFont("helvetica", "bold");

        const col1 = 10;
        const col2 = 60;
        const col3 = 120;

        ensureSpace(lineHeight);

        pdf.text("Branch Ref No.", col1, yPosition);
        pdf.text("Nominee Ref No.", col2, yPosition);
        pdf.text("Nominee Name", col3, yPosition);

        yPosition += lineHeight + 2;

      pdf.setFont("helvetica", "normal");

      const suffixes = ["", "Two", "Three", "Four"];

      let nomineeRowShown = false;
x.forEach((record) => {

    ensureSpace(lineHeight);

    pdf.text(String(record.branchRefNo || "N/A"), col1, yPosition);

    pdf.text(
        String(record.nominationRefNo || "N/A"),
        col2,
        yPosition
    );

    pdf.text(
        String(record.nomineeName || "N/A"),
        col3,
        yPosition
    );

    yPosition += lineHeight;
    nomineeRowShown = true;
});
      if (!nomineeRowShown) {

          ensureSpace(lineHeight);

          pdf.text("N/A", col1, yPosition);
          pdf.text("N/A", col2, yPosition);
          pdf.text("N/A", col3, yPosition);

          yPosition += lineHeight;
      }
addText(" ");
pdf.setFont("helvetica", "bold");
addText("Revised Guidelines on Nomination:-");
pdf.setFont("helvetica", "normal");

addText("-   An OCI cannot be appointed as a nominee in FRSB Bonds.");
addText(" ");

addText("-  Only Resident Indians / NRI’s are permitted to be a nominee in FRSB.");
addText(" ");

addText("-   In the event a Non-Resident Indian (NRI) nominee (at the time of investment) subsequently changes his/her citizenship");
addText("    status to OCI, the investor is required to change his nomination to an eligible individual, i.e., Resident Indian / NRI.");
/*addText("   ");*/
addText("    In case, the investor did not change or cancel his/her nomination in his lifetime,  the existing nomination would");
addText("    become null & void.");
addText(" ");

addText("-  If an OCI nominee is the sole legal heir of the investor, his/her claim for  transfer of the GOI Bonds can be");
addText("   considered, provided, the bonds are held till maturity and the legal heir agrees to forego the interest.");

        yPosition += 5;
        pdf.setFont("helvetica", "normal");


      /* addTextBlock([
           "5. Tax Waiver Document details:",
           `   Tax exemption document submitted : ${x[0].documentSubmitted || "N/A"}`,
           `   Valid Upto                       : ${x[0].validUp ? x[0].validUp.split(" ")[0] : "N/A"}`,
       ]);*/
        pdf.setFont("helvetica", "bold");
/*
        addText(" ");
*/

        addTextBlock([
          "Note: Tax waiver Form121 would be applicable, only if the total interest payable on the inverstment(s) to the",
          "first holder for the financial year does not exceed the prevailing Income Tax threshold limits.",
        ]);
        addText(" ");
        pdf.setFont("helvetica", "normal");

        addTextBlock([
          "In case of any discrepancies/exceptions. we request you to intimate us within 10 days from the date of receipt",
          "of this letter.Non-receipt of any correspondence from you will be deemed as your confirmation by us.",
        ]);

        addText(" ");
        addText("Assuring you of our best service at all times.");
        addText(" ");
const imageWidth = 50;
const imageHeight = 25;
const imageX = 10;

// Calculate the space required for the sign-off section
const signBlockHeight =
    (lineHeight * 4) + imageHeight + 8;

// Move to next page only if the entire sign-off section won't fit
if (yPosition + signBlockHeight > pageHeight - bottomMargin) {
    newPage();
}

addText("Thanking You");
addText("Yours Sincerely,");
/*
addText(" ");
*/

const imageY = yPosition;

if (imgData) {
    pdf.addImage(imgData, "PNG", imageX, imageY, imageWidth, imageHeight);
}

yPosition = imageY + imageHeight + 2;

addText(" ");

addText("FIG Operations.");
addText("In case of any further clarifications, please contact us on corporatehelp@hdfcbank.com");

        pdf.save("Accepted.pdf");
      }
    });
  });
}


      function generateRejectionPDF(x) {

     loadImageAsBase64("assets/images/nageswarannew.png", function (imgData) {
        if (!vm.downloadDetails) {
          alert("No details available to generate PDF.");
          return;
        }

        const pdf = new window.jspdf.jsPDF();
        let yPosition = 10;
        pdf.setFontSize(10);

        function addText(text) {
          pdf.text(text, 10, yPosition);
          yPosition += 6;
        }

        const addressLines = [
//          "HDFC Bank Ltd.",
//          "GOI Bond Operation,5th Flr",
//          "115,Dr.Radhakrishnan Salai",
//          "Opp, CSI Kalyani Hospital",
//          "Mylapore, Chennai-600004",
//          "Tel: 004 28477033",
//          " ",
//          "Fax No : 044-28477035",
//          " ",
//          "E-mail: rrb.chennai@hdfcbank.com",
//          "CTN No",




          "HDFC Bank Ltd.",
          "CMS-GOI Bonds - 2nd Floor,",
          "GKS Technology Park,",
          "No-94, SP Estate Bus Stand,",
          "Wavin Main Road,",
          "Mogappair, Chennai – 600058,",
          "Tel: 044-69129917"


        ];

        // Calculate the widest text width for alignment
        let maxTextWidth = Math.max(...addressLines.map((text) => pdf.getTextWidth(text)));
        let rightMargin = pdf.internal.pageSize.getWidth() - maxTextWidth - 10; // Fixed right-aligned x-position

        // Function to add right-aligned text
        function addRightAlignedText(text) {
          pdf.text(text, rightMargin, yPosition);
          yPosition += 6; // Move to next line
        }

        function addPartialUnderlinedText(underlinedText, normalText, x, y) {
          pdf.setFont("helvetica", "bold"); // Set font to bold
          pdf.text(underlinedText, x, y); // Print underlined part
          let textWidth = pdf.getTextWidth(underlinedText);
          pdf.line(x, y + 1, x + textWidth, y + 1); // Draw underline

          pdf.setFont("helvetica", "normal"); // Reset to normal font
          pdf.text(normalText, x + textWidth + 2, y); // Print remaining text
          yPosition += 6; // Move to next line
        }
        addHeader(x);
        function addHeader(x) {
          let name = x[0].holderName || "N/A";
//         let address = x[0].holderAddress || "N/A";
          addressLines.forEach(addRightAlignedText);
          let bla = x[0].blaNo || "N/A";
          yPosition += 1;
          addText("Date: " + new Date().toLocaleDateString("en-GB"));
          addText("HDFC/REJ/13923010");
          addText(" ");
          pdf.setFont("helvetica", "bold");
          addText(`${name}`);


          pdf.setFont("helvetica", "normal");
          addText(`${x[0].addResssOne || ""}`);
            addText(`${x[0].addResssTwo || ""}`);
            addText(`${x[0].addResssThree || ""}`);
             addText(`${x[0].city || ""}`);
            addText(`${x[0].state || ""}`);
          addText(`${x[0].pinCode || ""}`);

          addText(" ");
          addText("Dear Sir/Madam,");
          addText(" ");
          addPartialUnderlinedText("Sub:", " Regarding NOMINEE Updation", 10, yPosition);
          addPartialUnderlinedText("Ref:", ` Bond Ledger No ${bla}`, 10, yPosition);
          addText(" ");
          addText("With reference to your letter regarding the request for Nominee Updation for bonds.We");
          addText("regret to inform you that the above request could not be processed due to reason(s).mentioned");
          addText("below.");
          addText(" ");
          pdf.setFont("helvetica", "bold");
          addText("All the bond Holders requried in the Nomination form.");
          pdf.setFont("helvetica", "normal");
          addText(" ");
          addText("We would be glad to provide you with any additional information with regard to the subject");
          addText("matter and assuring you the best of services at all times.");
          addText(" ");
          addText("Thanking You,");
          addText("Yours sincerely,");
                const imageY = yPosition; // current yPosition after previous text
                                    const imageX = 10;
                                    const imageWidth = 50;
                                    const imageHeight = 25;
                    //                var niceImage = new Image();
                    //               // niceImage.src = '/assets/images/nageswaran.jpg';
                    //               niceImage.src = '/nageswarann.png';
                    //            pdf.addImage(niceImage, 'PNG', imageX, imageY, imageWidth, imageHeight);

                                     if (imgData) {
                                                pdf.addImage(imgData, "PNG",imageX, imageY, imageWidth, imageHeight); // Adding the image
                                              }
                                    yPosition = imageY + imageHeight + 2;
          addText(" ");
          pdf.setFont("helvetica", "bold");
//          addText("Karthikeyan.M");
           addText(" ");
          pdf.setFont("helvetica", "normal");
          addText("GOI Bond Operations");
          addText("Phone:  044-69129917");
          addText(" ");
          addText("Encl: Nil.");



//          const imageY = yPosition; // current yPosition after previous text
//                          const imageX = 10;
//                          const imageWidth = 50;
//                          const imageHeight = 25;
//          //                var niceImage = new Image();
//          //               // niceImage.src = '/assets/images/nageswaran.jpg';
//          //               niceImage.src = '/nageswarann.png';
//          //            pdf.addImage(niceImage, 'PNG', imageX, imageY, imageWidth, imageHeight);
//
//                           if (imgData) {
//                                      pdf.addImage(imgData, "PNG",imageX, imageY, imageWidth, imageHeight); // Adding the image
//                                    }
//                          yPosition = imageY + imageHeight + 2;
//
                         pdf.save("Rejection.pdf");

        }

       });
      }
    };



    function loadImageAsBase64(url, callback) {
                  const img = new Image();
                  img.crossOrigin = "Anonymous";
                  img.src = url;

                  img.onload = function () {
                    const canvas = document.createElement("canvas");
                    canvas.width = img.width;
                    canvas.height = img.height;
                    const ctx = canvas.getContext("2d");
                    ctx.drawImage(img, 0, 0);
                    const dataURL = canvas.toDataURL("image/png");
                    callback(dataURL);
                  };

                  img.onerror = function () {
                    console.error("Error loading image:", url);
                    callback(null);
                  };
                }
  }
})();
