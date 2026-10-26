(function () {

    'use strict';

    angular.module('gbmUltraApp').controller('acknowledgementController',
        acknowledgementController);

    acknowledgementController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'acknowledgementService', 'commonService'
    ];

    function acknowledgementController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, acknowledgementService, commonService) {

        var vm = this;

        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $filter('date')($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");
        vm.getModuleName = getModuleName;
        vm.acknowledgementDetails = acknowledgementDetails;



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




        function getModuleName() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                flag: "S"
            };
            acknowledgementService.getDetails(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result.list;
                    console.log("vm.schemeList", vm.schemeList);
                    angular.element("#schemeModel").modal("toggle");
                },
                function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                }
            );
        }

        //Date validation
        vm.fromDateVal = function () {
            vm.fromDateOptions = {
                maxDate: new Date(),
                showWeeks: false,
            };
        }

        vm.toDateVal = function (fromDate) {
            vm.toDateOptions = {
                minDate: fromDate,
                maxDate: new Date(),
                showWeeks: false,
            };
        }

        function acknowledgementDetails() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                fromDate: $filter('date')(vm.fromdate, "dd-MMM-yyyy"),
                toDate: $filter('date')(vm.todate, "dd-MMM-yyyy"),
                flag: "T"
            };

            acknowledgementService.getDetails(requestData)
                .then(function (response) {
                    vm.loader = false;
                    vm.details = response.data.result.list;
                    vm.records = response.data.result.list;

                    // ✅ Call download PDF only after details are successfully fetched
                    if (vm.details.length > 0) {
                        downloadPdf(vm.details);
                    } else {
                        Swal.fire("No Records Found", "There are no records available to download.", "warning");
                    }

                    // Clear input fields
                    vm.schemeName = '';
                    vm.fromdate = '';
                    vm.todate = '';

                }, function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    Swal.fire("No Records Found", "There are no records available to download.", "warning");
                    $state.go('acknowledgement_of_appointment_of_nomination.inquiry');
                });
        }

function downloadPdf(details) {

    loadImageAsBase64("assets/images/HDFC-Logo.png", function (logoData) {

        loadImageAsBase64("assets/images/nageswaranBg.png", function (signData) {

            if (!window.jspdf || !window.jspdf.jsPDF) {
                alert("jsPDF is not loaded. Please check the script reference.");
                return;
            }

            const pdf = new window.jspdf.jsPDF({
                orientation: "p",
                unit: "mm",
                format: [300, 350]
            });

            pdf.setFontSize(15);

            const blaOrder = [];
            const blaGroupMap = {};

            (details || []).forEach(function (rec) {
                const key = rec.blaNo || "";

                if (!blaGroupMap[key]) {
                    blaGroupMap[key] = [];
                    blaOrder.push(key);
                }

                blaGroupMap[key].push(rec);
            });

            const blaGroups = blaOrder.map(function (key) {
                return blaGroupMap[key];
            });

           function renderHeader(pdfDoc) {
               /* Logo */
               if (logoData) {
                   const logoX = 12;
                   const logoY = 10;
                   const logoWidth = 63;
                   const logoHeight = 13;

                   pdfDoc.addImage(
                       logoData,
                       "PNG",
                       logoX,
                       logoY,
                       logoWidth,
                       logoHeight
                   );
               }

               pdfDoc.setFontSize(15);
               pdfDoc.setFont("helvetica", "normal");

               const addressLines = [
                   "HDFC Bank Ltd,",
                   "CMS-GOI Bonds - 2nd Floor,",
                   "GKS Technology Park,",
                   "No-94, SP Estate Bus Stand,",
                   "Wavin Main Road, Mogappair,",
                   "Chennai – 600058.",
                   "Tel : 044-69129917",
                    " "
               ];
               let x = 215;
               let y = 30;

               addressLines.forEach(function (line) {
                   pdfDoc.text(line, x, y);
                   y += 6;
               });
           }

            blaGroups.forEach(function (groupRecords, index) {
                const record = groupRecords[0];

                if (index !== 0) {
                    pdf.addPage();
                }

                renderHeader(pdf);
                let yPosition = 65;

                function ensurePageSpace(requiredHeight) {
                    const pageHeight = pdf.internal.pageSize.getHeight();
                    const bottomMargin = 8;

                    if (yPosition + requiredHeight > pageHeight - bottomMargin) {
                        pdf.addPage();
                        renderHeader(pdf);
                        yPosition = 75;
                    }
                }

                function addText(text) {
                    ensurePageSpace(6);
                    pdf.text(text || "", 10, yPosition);
                    yPosition += 6;
                }

                function formatDate(dateStr) {
                    if (!dateStr) return "";
                    const parts = dateStr.split("/");
                    if (parts.length !== 3 || !parts[0] || !parts[1] || !parts[2]) return "";
                    const date = new Date(parts[2], parts[1] - 1, parts[0]);
                    if (isNaN(date.getTime())) return "";
                    return $filter("date")(date, "dd-MMM-yyyy") || "";
                }

                function formatBankName(name) {
                    if (!name) return "";
                    return name
                        .toLowerCase()
                        .split(" ")
                        .map(function (word) {
                            if (word === "hdfc") return "HDFC";
                            return word.charAt(0).toUpperCase() + word.slice(1);
                        })
                        .join(" ");
                }

                var achFieldColonX = null;

                function getAchFieldColonX() {
                    if (achFieldColonX !== null) {
                        return achFieldColonX;
                    }

                    var achFieldLabels = [
                        "CUSTOMER NAME",
                        "BANK NAME",
                        "ACCOUNT NUMBER",
                        "MICR CODE / IFSC CODE"
                    ];

                    pdf.setFont("helvetica", "bold");
                    var maxLabelWidth = 0;

                    achFieldLabels.forEach(function (label) {
                        var labelWidth = pdf.getTextWidth(label);
                        if (labelWidth > maxLabelWidth) {
                            maxLabelWidth = labelWidth;
                        }
                    });

                    achFieldColonX = 10 + maxLabelWidth + 6;
                    return achFieldColonX;
                }

                function addAlignedAchField(label, value) {
                    ensurePageSpace(6);
                    pdf.setFont("helvetica", "bold");

                    var leftMargin = 10;
                    var colonX = getAchFieldColonX();
                    var displayValue = value || "";

                    pdf.text(label, leftMargin, yPosition);
                    pdf.text(":", colonX, yPosition);
                    pdf.text(
                        displayValue,
                        colonX + pdf.getTextWidth(":") + 4,
                        yPosition
                    );

                    yPosition += 6;
                }
yPosition += 18;

pdf.setFont("helvetica", "bold");
addText("Date: " + formattedDate);
addText(" ");

addText(record.holderName || "");
pdf.setFont("helvetica", "normal");

if (record.address) {
    addText(record.address + ",");
}
var address = [];

if (record.addressTwo) {
    address.push(record.addressTwo);
}

if (record.addressThree) {
    address.push(record.addressThree);
}

if (address.length > 0) {
    addText(address.join(", ") + ",");
}
if (record.city) {
    addText(record.city + ","+ record.pincode);
}
if (record.state) {
    addText(record.state + ","+ "INDIA " );
}
/*if (record.pincode) {
    addText("INDIA " );
}*/

                addText(" ");
                addText("Dear Customer,");
                addText(" ");
function addFullyUnderlinedRow(label, value) {
    ensurePageSpace(6);

    pdf.setFont("helvetica", "bold");
const fullText = label + (value || "");
pdf.text(fullText, 10, yPosition, { underline: true });
    const totalWidth = pdf.getTextWidth(fullText);

    pdf.setLineWidth(0.6);
    pdf.setDrawColor(0, 0, 0);
    pdf.line(10, yPosition + 1.5, 10 + totalWidth, yPosition + 2);

    yPosition += 6;
}

addFullyUnderlinedRow(
    "Ref: ",
    "BLA No. " + (record.blaNo || "") + ", Transaction Date: " + formatDate(record.transactionDate)+ "  "
);
    addText("");

addFullyUnderlinedRow(
    "Sub: ",
    "Confirmation on Registration of Nomination & ACH Mandate"
);
    addText("");
                yPosition += 6;

                pdf.setFont("helvetica", "normal");
                addText(
                    "We acknowledge receipt of your request for registering of Nomination/ACH mandate made in favour of your"
                );

                let currentLineX = 10;
                const textPart1 = "investment in ";
                pdf.text(textPart1, currentLineX, yPosition);
                currentLineX += pdf.getTextWidth(textPart1);

                pdf.setFont("helvetica", "bold");
                const textPart2 = "Floating Rate Savings Bonds, 2020 (Taxable)";
                pdf.text(textPart2, currentLineX, yPosition);
                currentLineX += pdf.getTextWidth(textPart2);

                pdf.setFont("helvetica", "normal");
                const textPart3 = " scheme under section 9B(4) of the Public Debt Act,1944.";
                pdf.text(textPart3, currentLineX, yPosition);
                yPosition += 6;

                const toCamelCase = function (text) {
                    return text
                        ? text.toLowerCase().replace(/\b\w/g, function (c) {
                            return c.toUpperCase();
                        })
                        : "";
                };
    addText("");

                currentLineX = 10;
                pdf.setFont("helvetica", "normal");

                const midText = "The Certificate of Holding is issued for Bond ledger account no ";
                pdf.text(midText, currentLineX, yPosition);
                currentLineX += pdf.getTextWidth(midText);

                pdf.setFont("helvetica", "bold");
                const blaText = record.blaNo || "N/A";
                pdf.text(blaText, currentLineX, yPosition);
                currentLineX += pdf.getTextWidth(blaText);

                const bondTypeText = toCamelCase(record.bondType);
                const pageWidth = pdf.internal.pageSize.getWidth() - 20;

                pdf.setFont("helvetica", "normal");
                const underText = " under ";

                if (currentLineX + pdf.getTextWidth(underText) > pageWidth) {
                    yPosition += 6;
                    currentLineX = 10;
                }

                pdf.text(underText, currentLineX, yPosition);
                currentLineX += pdf.getTextWidth(underText);

                if (bondTypeText) {
                    pdf.setFont("helvetica", "bold");

                    if (currentLineX + pdf.getTextWidth(bondTypeText) > pageWidth) {
                        yPosition += 6;
                        currentLineX = 10;
                    }

                    pdf.text(bondTypeText, currentLineX, yPosition);
                    currentLineX += pdf.getTextWidth(bondTypeText);
                }

                pdf.setFont("helvetica", "normal");
                const schemeWord = " scheme.";

                if (currentLineX + pdf.getTextWidth(schemeWord) > pageWidth) {
                    yPosition += 6;
                    currentLineX = 10;
                }

                pdf.text(schemeWord, currentLineX, yPosition);
                yPosition += 6;

                addText("We have registered the nomination as per details below:");

                const tableHeaders = [
                    "SR. NO.",
                    "Branch Ref No.",
                    "Certificate No",
                    "NOMINEE NAME",
                    "NOMINATION ID",
                    "GUARDIAN NAME",
                    "Nominee NRI(Y/N)"
                ];

                function getValue(value) {
                    return value && value.toString().trim() !== "" ? value : "N/A";
                }

                function addAligned(label, value) {
                    const formattedLabel = label.padEnd(22, " ");
                    addText(formattedLabel + ":- " + (value || ""));
                }

                const tableRows = [];

                groupRecords.forEach(function (nominee) {
                    if (
                        nominee.name ||
                        nominee.nominationUnder ||
                        nominee.cohNo ||
                        nominee.branchRefno ||
                        nominee.guardianName
                      //  nominee. status
                    ) {
                        tableRows.push([
                            String(tableRows.length + 1),
                            getValue(nominee.branchRefno),
                            getValue(nominee.cohNo),
                            getValue(nominee.name),
                            getValue(nominee.nominationUnder),
                            getValue(nominee.guardianName),
                            getValue(nominee.status)
                        ]);
                    }
                });

                const isSingleNomination = tableRows.length === 1;

                if (tableRows.length > 0) {
                    pdf.autoTable({
                        head: [tableHeaders],
                        body: tableRows,
                        startY: yPosition,
                        tableWidth: 'auto',
                        margin: {
                            top: 65,
                            left: 10,
                            right: 10,
                            bottom: 15
                        },
                        theme: "grid",
                        pageBreak: "auto",
                        rowPageBreak: "avoid",
                        showHead: "everyPage",
                        didDrawPage: function (data) {
                            if (data.pageNumber > 1) {
                                renderHeader(pdf);
                            }
                        },
                        styles: {
                            fontSize: isSingleNomination ? 12 : 12,
                            font: "helvetica",
                            textColor: [0, 0, 0],
                            overflow: "linebreak",
                            cellPadding: 2.5,
                            valign: "middle",
                            halign: "center",
                            lineWidth: 0.2,
                            lineColor: [0, 0, 0]
                        },
                        headStyles: {
                            fillColor: [255, 255, 255],
                            textColor: [0, 0, 0],
                            fontStyle: "bold",
                            lineWidth: 0.2,
                            lineColor: [0, 0, 0]
                        },
                        bodyStyles: {
                            fillColor: [255, 255, 255],
                            textColor: [0, 0, 0],
                            lineWidth: 0.2,
                            lineColor: [0, 0, 0],
                            fontSize: 13
                        },
                        columnStyles: {
                            0: { cellWidth: 20 }
                        }
                    });
                    if (pdf.lastAutoTable && pdf.lastAutoTable.finalY) {
                        yPosition = pdf.lastAutoTable.finalY + (isSingleNomination ? 12 : 10);
                    } else {
                        yPosition += 40;
                    }

                } else {
                    yPosition += 6;
                }


pdf.setFont("helvetica", "bold");
pdf.setTextColor(0, 0, 0);
pdf.setDrawColor(0, 0, 0);
pdf.setLineWidth(0.6);

const guidelineText = "Revised Guidelines on Nomination:-";

pdf.text(guidelineText, 10, yPosition);

const guidelineWidth = pdf.getTextWidth(guidelineText);

pdf.line(
    10,
    yPosition + 2,
    10 + guidelineWidth,
    yPosition + 2
);

yPosition += 6;
pdf.setFont("helvetica", "normal");


                addText(" ");

                addText("1. An OCI cannot be appointed as a nominee in FRSB Bonds.");
                addText(" ");

                addText("2. Only Resident Indians / NRI’s are permitted to be a nominee in FRSB.");
                addText(" ");

                addText("3. In the event a Non-Resident Indian (NRI) nominee (at the time of investment) subsequently changes his/her citizenship");
                addText("   status to OCI, the investor is required to change his nomination to an eligible individual, i.e., Resident Indian / NRI.");
                addText("   In case, the investor did not change or cancel his/her nomination in his lifetime, the existing");
                addText("   nomination would become null & void.");
                addText(" ");

                addText("4. If an OCI nominee is the sole legal heir of the investor, his/her claim for transfer of the GOI Bonds can be considered,");
                addText("   provided, the bonds are held till maturity and the legal heir agrees to forego the interest from the date of transfer.");
                addText("   The repatriation of maturity proceeds will be subject to extant FEMA guidelines");
                addText(" ");

                addText(
                    "We would also like to confirm that the ACH details for the captioned Bond ledger account registered with us are as follows:"
                );

                pdf.setFont("helvetica", "bold");
                addText(" ");

                addAlignedAchField("CUSTOMER NAME", record.holderName || "N/A");
                addAlignedAchField("BANK NAME", record.bankName || "N/A");
                addAlignedAchField("ACCOUNT NUMBER", record.accNo || "N/A");
                addAlignedAchField("MICR CODE / IFSC CODE", record.micrCode || "N/A");

                pdf.setFont("helvetica", "normal");
                addText("");

                addText(
                    "In case of any mismatch in the above mentioned details and in the enclosed Bond ledger certificate, we request you to"
                );

                addText(
                    "kindly revert within 10 days from the receipt of this letter."
                );

                addText("");

                addText(
                    "To help us to serve you better, please ensure that you quote your Bond ledger account number in all your communication"
                );

                addText(
                    "with us."
                );

                addText("");

                addText(
                    "Thanking you and assuring best of our services at all times."
                );

                if (isSingleNomination) {
                    yPosition += 2;
                } else {
                    addText(" ");
                }

                addText("Yours sincerely,");
                addText("For HDFC Bank Ltd");

             if (signData) {
                 const signX = 5;
                 const signWidth = isSingleNomination ? 70 : 85;
                 const signHeight = isSingleNomination ? 35 : 50;

                 const lineSpacing = 5;
                 ensurePageSpace(signHeight + 8 + lineSpacing);

                 const signY = yPosition;

                 pdf.setFillColor(255, 255, 255);
                 pdf.rect(
                     signX - 2,
                     signY - 2,
                     signWidth + 4,
                     signHeight + 4,
                     "F"
                 );

                 pdf.addImage(
                     signData,
                     "PNG",
                     signX,
                     signY,
                     signWidth,
                     signHeight
                 );
                              yPosition = signY + signHeight + (isSingleNomination ? 2 : 4) + lineSpacing;
             }

                if (isSingleNomination) {
                    pdf.setFontSize(13);
                } else {
                    pdf.setFontSize(15);
                }

             addText("GOI Bonds Operations.");

                addText(
                    "In case of any further clarifications, please contact us on rrb.chennai@hdfc.bank.in"
                );

            });

            pdf.save("Acknowledgement.pdf");

            Swal.fire(
                "Success",
                "Acknowledgement of Appointment of Nomination generated successfully.",
                "success"
            );

        });

    });
}

                // Utility: convert image URL to Base64
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

                const date = new Date();
                const months = [
                    'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
                ];

                const formattedDate =
                    String(date.getDate()).padStart(2, '0') + '-' +
                    months[date.getMonth()] + '-' +
                    date.getFullYear();
                        }
})();