(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondTurnoverCommissionController',
			rbibondTurnoverCommissionController);

	rbibondTurnoverCommissionController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope', '$sce',
			'rbibondTurnoverCommissionService', 'commonService' ];

	function rbibondTurnoverCommissionController($scope, $filter, $state,
			$stateParams, $location, $rootScope, $sce,
			rbibondTurnoverCommissionService, commonService) {

		var vm = this;

        var QUARTER_MONTH_MAP = {
            'Q1': [
                { month: 3, name: 'Apr' },
                { month: 4, name: 'May' },
                { month: 5, name: 'Jun' }
            ],
            'Q2': [
                { month: 6, name: 'Jul' },
                { month: 7, name: 'Aug' },
                { month: 8, name: 'Sep' }
            ],
            'Q3': [
                { month: 9, name: 'Oct' },
                { month: 10, name: 'Nov' },
                { month: 11, name: 'Dec' }
            ],
            'Q4': [
                { month: 0, name: 'Jan' },
                { month: 1, name: 'Feb' },
                { month: 2, name: 'Mar' }
            ]
        };

        var FULL_MONTH_NAMES = {
            'Jan': 'January',
            'Feb': 'February',
            'Mar': 'March',
            'Apr': 'April',
            'May': 'May',
            'Jun': 'June',
            'Jul': 'July',
            'Aug': 'August',
            'Sep': 'September',
            'Oct': 'October',
            'Nov': 'November',
            'Dec': 'December'
        };

        var QUARTER_ENDING_MAP = {
            'Q1': { month: 'June', day: '30th' },
            'Q2': { month: 'September', day: '30th' },
            'Q3': { month: 'December', day: '31st' },
            'Q4': { month: 'March', day: '31st' }
        };

        var QUARTER_CERT_NUMBER_MAP = {
            'Q1': '01',
            'Q2': '02',
            'Q3': '03',
            'Q4': '04'
        };

vm.getQuarterEndText = function () {
    switch (vm.quarter) {
        case 'Q1':
            return 'June';
        case 'Q2':
            return 'September';
        case 'Q3':
            return 'December';
        case 'Q4':
            return 'March';
        default:
            return '';
    }
};
vm.getFinancialYear = function () {
    if (!vm.financialYear) return "";

    var years = vm.financialYear.split("-");

    return vm.quarter === "Q4" ? years[1] : years[0];
};
        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.bankName = $rootScope.globals.currentUser.bankName;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getPrincipalRepayment = getPrincipalRepayment;
        vm.getModuleName=getModuleName;
        vm.dateValidation=dateValidation;
        vm.exportWord = exportWord;
        vm.alert=alert;
        vm.safe=safe;
        vm.exportExcel=exportExcel;
        vm.exportExcell=exportExcel;
        vm.printpage = printSection1;
        vm.printReportSection = printReportSection;
        vm.exportWordSection2 = exportWordSection2;
        vm.exportExcelSection2 = exportExcelSection2;
        vm.exportExcellSection2 = exportExcelSection2;
        vm.exportWordSection4 = exportWordSection4;
        vm.exportExcelSection4 = exportExcelSection4;
        vm.exportExcellSection4 = exportExcelSection4;
        vm.exportWordSection5 = exportWordSection5;
        vm.exportExcelSection5 = exportExcelSection5;
        vm.exportExcellSection5 = exportExcelSection5;
        vm.exportWordSection6 = exportWordSection6;
        vm.exportWordSection7 = exportWordSection7;
        vm.exportWordSection8 = exportWordSection8;
        vm.exportWordSection9 = exportWordSection9;
        vm.exportWordActiveSection = exportWordActiveSection;
        vm.exportExcelActiveSection = exportExcelActiveSection;
        vm.activeSectionIndex = 0;
        vm.setActiveSection = setActiveSection;
        vm.getTaxRedemptionRowIndexes = getTaxRedemptionRowIndexes;
        vm.getTaxRedemptionRowCells = getTaxRedemptionRowCells;
        vm.reportSections = [];
        vm.sectionPreviewHtml = {};
        vm.taxRedemptionSchemes = [];


vm.allowOnlyNumber = function (event) {

    var charCode = event.which || event.keyCode;

    if (charCode < 48 || charCode > 57) {
        event.preventDefault();
    }
};

vm.formatFinancialYear = function () {

    vm.financialYearError = "";

    if (!vm.financialYear) {
        return;
    }

    var value = vm.financialYear.replace(/\D/g, '');

    var currentYear = new Date().getFullYear();

    if (value.length >= 4) {

        var firstYear = parseInt(value.substring(0, 4));

        if (firstYear > currentYear) {

            vm.financialYearError =
                "Future financial year is not allowed";

            vm.financialYear = "";
            return;
        }

        var secondYear = firstYear + 1;

        vm.financialYear =
            firstYear + "-" + secondYear;
    } else {

        vm.financialYear = value;
    }
};





         if ($state.current.name == 'rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission.inquiry_details')
                {

             vm.moduleName= $stateParams.obj. moduleName;
             vm.financialYear= $stateParams.obj. financialYear;
             vm.quarter=$stateParams.obj.quarter;
             vm.showReport = true;
             vm.section1NoData = false;
             vm.activeSectionIndex = 0;
             if (window.scrollTo) {
                 window.scrollTo(0, 0);
             }

             if (vm.financialYear) {
                 var years = vm.financialYear.split("-");
                 vm.toYear = years[1];
             }

             vm.taxRedemptionTitle = 'Tax Redemption';
             vm.taxRedemptionList = [];
             vm.testBondList = [];
             initTaxRedemptionSchemes();
             vm.annex2aList = [];
             vm.annex2aAmount = '';
             vm.annex2aAmountWords = '';
             vm.annex2aSignatory = '';
             initExternalAuditData();
             initTaxInvoiceData();
             initBankInternalFormatData();
             initAnnexureAData();
             initAnnexureBData();
             initReportSections();
             buildSectionTitles();
             refreshSectionPreviewHtml();
             getPrincipalRepayment();

                }


   if (vm.financialYear) {
       var years = vm.financialYear.split("-");
       vm.toYear = years[1];
   }

        function getQuarterStartYear() {
            if (!vm.financialYear || !vm.quarter) {
                return null;
            }
            var startYear = parseInt(vm.financialYear.substring(0, 4), 10);
            return vm.quarter === 'Q4' ? startYear + 1 : startYear;
        }

        function getQuarterMonths() {
            var months = QUARTER_MONTH_MAP[vm.quarter];
            if (!months) {
                return [];
            }
            var year = getQuarterStartYear();
            return months.map(function(monthInfo) {
                return {
                    monthName: monthInfo.name,
                    year: year
                };
            });
        }

        function getAllocatedRowCount(dataList) {
            if (dataList && dataList.length) {
                return dataList.length;
            }
            var months = getQuarterMonths();
            return months.length > 0 ? months.length : 3;
        }

        function getClaimRowValues(item) {
            if (!item) {
                return null;
            }
            return [
                item.claimRecdDt || item.claimReceivedDate || item.claimReceivedDt,
                item.principalAmt || item.investmentAmount || item.principalAmount || item.principalAmountrepaid,
                item.principalCount || item.principalNoOfRecords || item.principalRecordCount || item.count,
                item.interestAmt || item.interestAmount || item.interestAmountpPaid,
                item.interestCount || item.interestNoOfRecords || item.interestRecordCount || item.count2,
                item.totalAmount || item.totalAmt || item.totalPayment,
                item.totalRecords || item.totalRecord
            ];
        }

        function buildClaimDataRowHtml(item) {
            var values = getClaimRowValues(item);
            var row = '<tr>';
            if (values) {
                for (var i = 0; i < values.length; i++) {
                    row += '<td>' + safe(values[i]) + '</td>';
                }
            } else {
                row += buildEmptyCells(7);
            }
            return row + '</tr>';
        }

        function buildClaimTableBodyHtml(dataList, totalLabel, includeBlankRowBeforeTotal) {
            var html = '';
            var rowCount = getAllocatedRowCount(dataList);

            for (var i = 0; i < rowCount; i++) {
                html += buildClaimDataRowHtml(dataList && dataList[i]);
            }

            if (includeBlankRowBeforeTotal) {
                html += buildEmptyRow(7);
            }

            html +=
                '<tr>' +
                '<td><b>' + totalLabel + '</b></td>' +
                buildEmptyCells(6) +
                '</tr>';

            return html;
        }

        function loadSectionTableData(result) {
            vm.taxRedemptionList = [];
            vm.testBondList = [];
            vm.annex2aList = [];
            vm.annex2aAmount = '';
            vm.annex2aAmountWords = '';
            vm.annex2aSignatory = '';

            if (!result || Array.isArray(result)) {
                syncExternalAuditFromQuarterReport();
                loadExternalAuditData(null);
                loadTaxInvoiceData(null);
                loadBankInternalFormatData(null);
                loadAnnexureAData(null);
                loadAnnexureBData(null);
                loadTaxRedemptionSchemes(null);
                syncDynamicAmountWords();
                refreshSectionPreviewHtml();
                return;
            }

            vm.taxRedemptionList = result.taxRedemptionList || result.taxRedemptionData || [];
            vm.testBondList = result.testBondList || result.preMaturityList || result.testBondData || [];
            vm.annex2aList = result.annex2aList || result.annex2aMonthList || [];
            vm.annex2aAmount = result.annex2aAmount || result.tocClaimAmount || result.agencyCommissionAmount || '';
            vm.annex2aAmountWords = result.annex2aAmountWords || result.tocClaimAmountWords || '';
            vm.annex2aSignatory = result.annex2aSignatory || result.authorizedSignatory || '';

            if (result.taxRedemptionTitle) {
                vm.taxRedemptionTitle = result.taxRedemptionTitle;
            }

            loadTaxRedemptionSchemes(result);
            loadExternalAuditData(result);
            syncExternalAuditFromQuarterReport();
            loadTaxInvoiceData(result);
            loadBankInternalFormatData(result);
            loadAnnexureAData(result);
            loadAnnexureBData(result);
            syncDynamicAmountWords();
            refreshSectionPreviewHtml();
        }

        function trustPreviewHtml(html) {
            return $sce.trustAsHtml(html || '');
        }

        function refreshSectionPreviewHtml() {
            vm.sectionPreviewHtml = {
                2: trustPreviewHtml(buildAnnex2aCertificateHtml()),
                3: trustPreviewHtml(buildExternalAuditCertificateHtml()),
                4: trustPreviewHtml(buildTaxInvoiceHtml()),
                5: trustPreviewHtml(buildBankInternalFormatHtml()),
                6: trustPreviewHtml(buildAnnexureAHtml()),
                7: trustPreviewHtml(buildAnnexureBHtml())
            };
        }

        function getDefaultTaxRedemptionSchemeTitles() {
            return [
                'Principal redemption 9% Relief Bond',
                'Interest redemption 9% Relief Bond',
                'Principal redemption 8.50% Relief Bond',
                'Interest redemption 8.50% Relief Bond',
                'Principal redemption 8% Relief Bond',
                'Interest redemption 8% Relief Bond',
                'Principal redemption 6.50% Savings Bond',
                'Interest redemption 6.50% Saving Bonds',
                'Principal prematurity of 7.75% Savings Bond',
                'Interest prematurity of 7.75% Saving Bonds',
                'Principal redemption of 7.75% Savings Bond',
                'Interest redemption of 7.75% Saving Bonds',
                'Principal redemption 8% Savings Bond',
                'Interest redemption 8% Saving Bonds',
                'Principal prematurity of 8.05% FRSB',
                'Interest prematurity of 8.05% FRSB'
            ];
        }

        function getDefaultTaxRedemptionSchemes() {
            var titles = getDefaultTaxRedemptionSchemeTitles();
            var schemes = [];

            for (var i = 0; i < titles.length; i++) {
                schemes.push({
                    key: 'scheme_' + (i + 1),
                    index: i,
                    title: titles[i],
                    dataList: []
                });
            }

            return schemes;
        }

        function initTaxRedemptionSchemes() {
            vm.taxRedemptionSchemes = getDefaultTaxRedemptionSchemes();
            return vm.taxRedemptionSchemes;
        }

        function findTaxRedemptionScheme(schemeKey, schemeTitle) {
            var schemes = vm.taxRedemptionSchemes || [];
            for (var i = 0; i < schemes.length; i++) {
                if (schemeKey && schemes[i].key === schemeKey) {
                    return schemes[i];
                }
                if (schemeTitle && schemes[i].title === schemeTitle) {
                    return schemes[i];
                }
            }
            return null;
        }

        function loadTaxRedemptionSchemes(result) {
            if (!vm.taxRedemptionSchemes || !vm.taxRedemptionSchemes.length) {
                initTaxRedemptionSchemes();
            }

            if (!result || Array.isArray(result)) {
                if (vm.taxRedemptionList && vm.taxRedemptionList.length &&
                    vm.taxRedemptionSchemes && vm.taxRedemptionSchemes.length) {
                    vm.taxRedemptionSchemes[0].dataList = vm.taxRedemptionList;
                }
                return;
            }

            var apiSchemes = result.taxRedemptionSchemes || result.taxRedemptionSchemeList || [];
            if (apiSchemes.length) {
                for (var i = 0; i < apiSchemes.length; i++) {
                    var apiScheme = apiSchemes[i];
                    var targetScheme = findTaxRedemptionScheme(
                        apiScheme.key || apiScheme.schemeKey,
                        apiScheme.title || apiScheme.schemeTitle || apiScheme.schemeName
                    );

                    if (targetScheme) {
                        targetScheme.dataList = apiScheme.dataList || apiScheme.rowsList ||
                            apiScheme.claimList || apiScheme.taxRedemptionList || [];
                    }
                }
                return;
            }

            if (vm.taxRedemptionList && vm.taxRedemptionList.length &&
                vm.taxRedemptionSchemes && vm.taxRedemptionSchemes.length) {
                vm.taxRedemptionSchemes[0].dataList = vm.taxRedemptionList;
            }
        }

        function getTaxRedemptionRowIndexes(scheme) {
            var count = getAllocatedRowCount(scheme && scheme.dataList);
            var indexes = [];
            for (var i = 0; i < count; i++) {
                indexes.push(i);
            }
            return indexes;
        }

        function getTaxRedemptionRowCells(scheme, rowIndex) {
            var item = scheme && scheme.dataList && scheme.dataList[rowIndex];
            var values = getClaimRowValues(item);
            if (values) {
                return values;
            }
            return ['', '', '', '', '', '', ''];
        }

        function buildTaxRedemptionSpacer() {
            return "<p style='line-height:18pt;mso-line-height-rule:exactly;margin:0;'>&nbsp;</p>";
        }

        function initExternalAuditData() {
            vm.externalAudit = {
                auditorFirm: 'M/s. J S A S & Associates',
                currentAccountNo: '32105001001',
                noOfTransactions: '',
                turnover: '',
                gstPercent: '18%',
                tocCommission: '',
                gstCommission: '',
                totalCommission: '',
                amountWords: '',
                totalAmountWords: '',
                firmRegNo: '014859S',
                partnerName: 'CA. Suthesh',
                membershipNo: '222454',
                udin: '',
                place: 'Chennai'
            };
            return vm.externalAudit;
        }

        function loadExternalAuditData(result) {
            if (!result || Array.isArray(result)) {
                return;
            }

            var audit = result.externalAudit || result.externalAuditCertificate || {};
            if (!vm.externalAudit) {
                initExternalAuditData();
            }

            angular.extend(vm.externalAudit, {
                auditorFirm: audit.auditorFirm || vm.externalAudit.auditorFirm,
                currentAccountNo: audit.currentAccountNo || vm.externalAudit.currentAccountNo,
                noOfTransactions: audit.noOfTransactions || audit.noOfTransections ||
                    vm.externalAudit.noOfTransactions,
                turnover: audit.turnover || vm.externalAudit.turnover,
                gstPercent: audit.gstPercent || vm.externalAudit.gstPercent,
                tocCommission: audit.tocCommission || audit.tocAmount || vm.externalAudit.tocCommission,
                gstCommission: audit.gstCommission || audit.gstAmount || vm.externalAudit.gstCommission,
                totalCommission: audit.totalCommission || audit.totalAmount || vm.externalAudit.totalCommission,
                amountWords: audit.amountWords || audit.tocClaimAmountWords || vm.externalAudit.amountWords,
                totalAmountWords: audit.totalAmountWords || vm.externalAudit.totalAmountWords,
                firmRegNo: audit.firmRegNo || vm.externalAudit.firmRegNo,
                partnerName: audit.partnerName || vm.externalAudit.partnerName,
                membershipNo: audit.membershipNo || vm.externalAudit.membershipNo,
                udin: audit.udin || vm.externalAudit.udin,
                place: audit.place || vm.externalAudit.place
            });
        }

        function getQuarterEndingInfo() {
            if (!vm.quarter || !QUARTER_ENDING_MAP) {
                return { month: 'March', day: '31st', year: vm.toYear || '' };
            }
            var ending = QUARTER_ENDING_MAP[vm.quarter];
            if (!ending || !vm.financialYear) {
                return { month: 'March', day: '31st', year: vm.toYear || '' };
            }
            return {
                month: ending.month,
                day: ending.day,
                year: getQuarterStartYear()
            };
        }

        function getQuarterEndingSubjectLabel() {
            var info = getQuarterEndingInfo();
            return info.month + ' ' + info.year;
        }

        function getQuarterEndingDateText() {
            var info = getQuarterEndingInfo();
            return info.day + ' ' + info.month + ', ' + info.year;
        }

        function getCertificateRefNo() {
            var certNo = QUARTER_CERT_NUMBER_MAP[vm.quarter] || '';
            return 'HDFC/TOC/Cert ' + certNo + '/' + vm.quarter + '/F.Y. ' + vm.financialYear;
        }

        function getExportFooterDate() {
            if (vm.currentDate) {
                return vm.currentDate.replace(/\//g, '-');
            }
            return $filter('date')(new Date(), 'dd-MM-yyyy');
        }

        function getExternalAuditAmount() {
            if (vm.externalAudit && vm.externalAudit.totalCommission) {
                return vm.externalAudit.totalCommission;
            }
            return getTocClaimAmountDisplay();
        }

        function getExternalAuditAmountWords() {
            var amount = getExternalAuditAmount();
            return getAmountWordsForDisplay(
                amount,
                vm.externalAudit && vm.externalAudit.amountWords
            );
        }

        function getExternalAuditTotalAmountWords() {
            if (vm.externalAudit && vm.externalAudit.totalAmountWords) {
                return vm.externalAudit.totalAmountWords;
            }
            var amount = getExternalAuditAmount();
            var words = getAmountWordsForDisplay(amount, '');
            if (!words) {
                return '_______________';
            }
            return '( Rupees ' + words + ' Only )';
        }

        function buildExternalAuditTableHtml() {
            var audit = vm.externalAudit || {};
            var noOfTransactions = safe(audit.noOfTransactions) || '&nbsp;';
            var turnover = safe(audit.turnover) || '&nbsp;';
            var gstPercent = safe(audit.gstPercent) || '18%';
            var tocCommission = safe(audit.tocCommission) || '&nbsp;';
            var gstCommission = safe(audit.gstCommission) || '&nbsp;';
            var totalCommission = safe(getExternalAuditAmount()) || '&nbsp;';

            return "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:100%;text-align:center;border:1px solid #000;margin:12px 0 16px 0;'>" +
                "<thead><tr>" +
                "<th style='font-weight:bold;width:25%;'>No. of Transactions</th>" +
                "<th style='font-weight:bold;width:35%;'>Turnover</th>" +
                "<th style='font-weight:bold;width:40%;'>R/O Commission (Rs.)<br/>( 7 paise per Rs. 100 on Turnover )</th>" +
                "</tr></thead><tbody>" +
                "<tr><td>" + noOfTransactions + "</td><td>" + turnover + "</td><td>" + tocCommission + "</td></tr>" +
                "<tr><td>&nbsp;</td><td>" + gstPercent + "</td><td>" + gstCommission + "</td></tr>" +
                "<tr><td>&nbsp;</td><td style='font-weight:bold;'>Total ( TOC + GST )</td><td style='font-weight:bold;'>" + totalCommission + "</td></tr>" +
                "</tbody></table>";
        }

        function buildExternalAuditCertificateHtml() {
            var audit = vm.externalAudit || {};
            var amount = getExternalAuditAmount();
            var amountText = amount ? safe(amount) : '_______________';
            var amountWords = getExternalAuditAmountWords();
            var amountWordsText = amountWords ?
                '( Rupees ' + safe(amountWords) + ' )' :
                '( Rupees _______________ )';
            var totalAmountWords = getExternalAuditTotalAmountWords();
            var accountNo = safe(audit.currentAccountNo) || '_______________';
            var auditorFirm = safe(audit.auditorFirm) || 'M/s. J S A S & Associates';
            var quarterEndingSubject = getQuarterEndingSubjectLabel();
            var quarterEndingDate = getQuarterEndingDateText();
            var certRefNo = getCertificateRefNo();
            var footerDate = getExportFooterDate();
            var html = '';

            html +=
                "<div style='text-align:center;font-weight:bold;font-size:14pt;margin-bottom:16px;'>External Audit Certificate</div>" +
                "<div style='text-align:right;font-size:12pt;margin-bottom:16px;'>Date : " + safe(vm.currentDate) + "</div>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 8px 0;text-align:left;'>To</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0;text-align:left;'>The General Manager,</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0;text-align:left;'>Reserve Bank of India,</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0;text-align:left;'>Central Accounts Section,</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 16px 0;text-align:left;'>Nagpur.</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 16px 0;text-align:center;font-weight:bold;text-decoration:underline;'>" +
                "Agency Commission Claim on Relief and Savings Bond Interest Payment &amp; Redemption for the Quarter Ending " +
                quarterEndingSubject + ".</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 12px 0;text-align:left;'>" +
                "We, " + auditorFirm + ", the External Auditors of HDFC Bank, hereby certify that the Turnover Commission Rs. " +
                "<b>" + amountText + " " + amountWordsText + "</b>" +
                " claimed by HDFC Bank for the quarter ending on on 31st March, 2026" +
                " has been checked by the undersigned and found to be correct and in order in all respects based on the verification procedure. Details are given below:</p>";

            html += buildExternalAuditTableHtml();

            html +=
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 12px 0;text-align:left;'>" +
                "Please credit HDFC Bank Current account no. " + accountNo + " with the Rs. " +
                "<b>" + amountText + "</b> Total Amount in words: <b>" + totalAmountWords + "</b>" +
                ". We also certify that no ineligible items are included &amp; claims are made as per extant Instructions of RBI, vide circular RBI/2021-22/07 DGBA. GBD. No.S-2/31.12.010/2021-22 dated on April 01, 2021.</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 20px 0;text-align:left;'>" +
                "We certify that the claim does not contain any interest payment(s) that violate Section 23 of the Government Securities Act, 2006.</p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:0;text-align:left;'>For M/S J S A S &amp; Associates</p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:0;text-align:left;'>[ Chartered Accountants]</p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:0;text-align:left;'>Firm Registration No: " + safe(audit.firmRegNo) + "</p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:12px 0 4px 0;text-align:left;'><b>Designated Partner</b></p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:0;text-align:left;'>Name of the Partner : " + safe(audit.partnerName) + "</p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:0;text-align:left;'>Membership No: " + safe(audit.membershipNo) + "</p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:0;text-align:left;'>UDIN: " + (safe(audit.udin) || '_______________') + "</p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:0;text-align:left;'>Certificate Ref No : " + certRefNo + "</p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:0;text-align:left;'>Place: " + safe(audit.place) + "</p>" +
                "<p style='font-size:12pt;line-height:1.6;margin:0;text-align:left;'>Date: " + footerDate + "</p>";

            return html;
        }

        function buildExternalAuditExportTable() {
            return buildExternalAuditCertificateHtml();
        }

        function parseIndianAmount(value) {
            if (!value) {
                return null;
            }
            var normalized = String(value).replace(/,/g, '').trim();
            var num = parseFloat(normalized);
            return isNaN(num) ? null : num;
        }

        function convertIntegerToIndianWords(num) {
            if (!num || num === 0) {
                return '';
            }

            var belowTwenty = [
                'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine', 'Ten',
                'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen'
            ];
            var tens = [
                'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'
            ];

            function convertChunk(chunk) {
                var result = '';
                if (chunk >= 100) {
                    result += belowTwenty[Math.floor(chunk / 100) - 1] + ' Hundred ';
                    chunk %= 100;
                }
                if (chunk >= 20) {
                    result += tens[Math.floor(chunk / 10) - 2] + ' ';
                    chunk %= 10;
                }
                if (chunk > 0) {
                    result += belowTwenty[chunk - 1] + ' ';
                }
                return result.trim();
            }

            var parts = [];
            var crore = Math.floor(num / 10000000);
            num %= 10000000;
            var lakh = Math.floor(num / 100000);
            num %= 100000;
            var thousand = Math.floor(num / 1000);
            num %= 1000;
            var remaining = num;

            if (crore > 0) {
                parts.push(convertChunk(crore) + ' Crore' + (crore > 1 ? 's' : ''));
            }
            if (lakh > 0) {
                parts.push(convertChunk(lakh) + ' Lakh' + (lakh > 1 ? 's' : ''));
            }
            if (thousand > 0) {
                parts.push(convertChunk(thousand) + ' Thousand');
            }
            if (remaining > 0) {
                parts.push(convertChunk(remaining));
            }

            return parts.join(' ').trim();
        }

        function convertAmountToWords(value) {
            var num = parseIndianAmount(value);
            if (num === null || isNaN(num)) {
                return '';
            }
            if (num === 0) {
                return 'Zero';
            }

            var rupees = Math.floor(num);
            var paise = Math.round((num - rupees) * 100);
            var words = '';

            if (rupees > 0) {
                words = convertIntegerToIndianWords(rupees);
            } else {
                words = 'Zero';
            }

            if (paise > 0) {
                words += (words ? ' and ' : '') + convertIntegerToIndianWords(paise) + ' Paise';
            }

            return words;
        }

        function getAmountWordsForDisplay(amount, apiWords) {
            if (apiWords) {
                return apiWords;
            }
            if (!amount) {
                return '';
            }
            return convertAmountToWords(amount);
        }

        function syncDynamicAmountWords() {
            var tocAmount = getTocClaimAmountDisplay();
            var commissionAmount = vm.externalAudit && vm.externalAudit.totalCommission ?
                vm.externalAudit.totalCommission : tocAmount;

            if (!vm.annex2aAmountWords && tocAmount) {
                vm.annex2aAmountWords = convertAmountToWords(tocAmount);
            }

            if (vm.externalAudit) {
                if (!vm.externalAudit.amountWords && commissionAmount) {
                    vm.externalAudit.amountWords = convertAmountToWords(commissionAmount);
                }
                if (!vm.externalAudit.totalAmountWords && commissionAmount) {
                    vm.externalAudit.totalAmountWords =
                        '( Rupees ' + convertAmountToWords(commissionAmount) + ' Only )';
                }
            }

            syncBankInternalFromExternalAudit();
            syncAnnexureAFromExternalAudit();
        }

        function formatIndianAmount(num) {
            if (num === null || isNaN(num)) {
                return '';
            }
            return num.toLocaleString('en-IN', {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2
            });
        }

        function formatCountForDisplay(value) {
            var num = parseIndianAmount(value);
            if (num !== null) {
                return Math.round(num).toLocaleString('en-IN');
            }
            return value ? String(value) : '';
        }

        function normalizeQuarterReportLabel(value) {
            return String(value || '').trim().toUpperCase();
        }

        function getQuarterReportSummaryData() {
            if (!vm.model || vm.model.length < 2) {
                return null;
            }

            var tocRow = null;
            var commissionRow = null;
            var totalRow = vm.model[vm.model.length - 1];
            var i;

            for (i = 0; i < vm.model.length; i++) {
                var label = normalizeQuarterReportLabel(vm.model[i].totalClaim);
                if (label.indexOf('TOC AMOUNT') >= 0 || label.indexOf('TOC AMT') >= 0) {
                    tocRow = vm.model[i];
                    break;
                }
            }

            for (i = vm.model.length - 2; i >= 0; i--) {
                var rateVal = String(vm.model[i].noOfTransections || vm.model[i].noOfTransactions || '').trim();
                var rateNum = parseFloat(rateVal);
                if (rateVal && !isNaN(rateNum) && rateNum < 20 && vm.model[i].amount) {
                    commissionRow = vm.model[i];
                    break;
                }
            }

            if (!commissionRow && vm.model.length >= 2) {
                commissionRow = vm.model[vm.model.length - 2];
            }

            return {
                tocRow: tocRow,
                commissionRow: commissionRow,
                totalRow: totalRow
            };
        }

        function syncExternalAuditFromQuarterReport() {
            var summary = getQuarterReportSummaryData();
            if (!summary) {
                return;
            }

            if (!vm.externalAudit) {
                initExternalAuditData();
            }

            var tocRow = summary.tocRow;
            var commissionRow = summary.commissionRow;
            var totalRow = summary.totalRow;

            if (tocRow) {
                if (!hasDisplayValue(vm.externalAudit.noOfTransactions)) {
                    vm.externalAudit.noOfTransactions = formatCountForDisplay(
                        tocRow.noOfTransections || tocRow.noOfTransactions
                    );
                }
                if (!hasDisplayValue(vm.externalAudit.turnover)) {
                    vm.externalAudit.turnover = formatAmountForDisplayFromValue(tocRow.amount);
                }
            }

            if (commissionRow && !hasDisplayValue(vm.externalAudit.tocCommission)) {
                vm.externalAudit.tocCommission = formatAmountForDisplayFromValue(commissionRow.amount);
            }

            if (totalRow) {
                if (!hasDisplayValue(vm.externalAudit.totalCommission)) {
                    vm.externalAudit.totalCommission = formatAmountForDisplayFromValue(totalRow.amount);
                }
                var gstLabel = String(totalRow.noOfTransections || totalRow.noOfTransactions || '').trim();
                if (gstLabel && !hasDisplayValue(vm.externalAudit.gstPercent)) {
                    vm.externalAudit.gstPercent = gstLabel.indexOf('%') >= 0 ? gstLabel : gstLabel + '%';
                }
            }

            if (!hasDisplayValue(vm.externalAudit.gstCommission) &&
                hasDisplayValue(vm.externalAudit.tocCommission) &&
                hasDisplayValue(vm.externalAudit.totalCommission)) {
                var tocNum = parseIndianAmount(vm.externalAudit.tocCommission);
                var totalNum = parseIndianAmount(vm.externalAudit.totalCommission);
                if (tocNum !== null && totalNum !== null) {
                    vm.externalAudit.gstCommission = formatIndianAmount(totalNum - tocNum);
                }
            }

            if (!hasDisplayValue(vm.annex2aAmount) && hasDisplayValue(vm.externalAudit.totalCommission)) {
                vm.annex2aAmount = vm.externalAudit.totalCommission;
            }
        }

        var ANNEX_PARTICULARS_QUARTER_INDEX_MAP = {
            '7a': '9a',
            '7b': '9b',
            '8a': '7a',
            '8b': '7b',
            '9a': '8a',
            '9b': '8b'
        };

        function isQuarterReportSummaryRow(row) {
            if (!row) {
                return true;
            }
            var label = normalizeQuarterReportLabel(row.totalClaim);
            if (label.indexOf('TOC AMOUNT') >= 0 || label.indexOf('TOC AMT') >= 0) {
                return true;
            }
            var rateVal = String(row.noOfTransections || row.noOfTransactions || '').trim();
            var rateNum = parseFloat(rateVal);
            if (rateVal && !isNaN(rateNum) && rateNum < 20 && !label) {
                return true;
            }
            if (!label) {
                return !String(row.sNo || '').trim();
            }
            return false;
        }

        function resolveQuarterReportIndex(particularsIndex) {
            if (!particularsIndex) {
                return '';
            }
            var key = String(particularsIndex).trim().toLowerCase();
            return ANNEX_PARTICULARS_QUARTER_INDEX_MAP[key] || particularsIndex;
        }

        function findQuarterReportRowBySNo(sNo) {
            if (!sNo || !vm.model || !vm.model.length) {
                return null;
            }
            var key = String(sNo).trim().toLowerCase();
            for (var i = 0; i < vm.model.length; i++) {
                var row = vm.model[i];
                if (String(row.sNo || '').trim().toLowerCase() !== key) {
                    continue;
                }
                if (isQuarterReportSummaryRow(row)) {
                    continue;
                }
                return row;
            }
            return null;
        }

        function getParticularsMatchKey(text) {
            var value = normalizeQuarterReportLabel(text);
            var key = '';

            if (value.indexOf('TOTAL INTEREST') >= 0) {
                return 'TOTINT';
            }
            if (value.indexOf('INTEREST PAYMENT') >= 0) {
                key += 'IP';
            } else if (value.indexOf('HALF YEARLY') >= 0 && value.indexOf('REJECT') >= 0) {
                return 'HYREJ';
            } else if (value.indexOf('PRINCIPAL') >= 0) {
                key += 'P';
            } else if (value.indexOf('INTEREST') >= 0) {
                key += 'I';
                if (value.indexOf('REDEMPTION') < 0 && value.indexOf('PREMATURITY') < 0 &&
                    value.indexOf('PAYMENT') < 0 &&
                    (value.indexOf('SAVINGS') >= 0 || value.indexOf('SAVING') >= 0 ||
                     value.indexOf('RELIEF') >= 0 || value.indexOf('BOND') >= 0)) {
                    key += 'RED';
                }
            }

            if (value.indexOf('PREMATURITY') >= 0 || value.indexOf('PRE MATURITY') >= 0) {
                key += 'PRE';
            } else if (value.indexOf('REDEMPTION') >= 0 || key.indexOf('RED') >= 0) {
                if (key.indexOf('RED') < 0) {
                    key += 'RED';
                }
            }

            if (value.indexOf('RELIEF') >= 0) {
                key += 'REL';
            }
            if (value.indexOf('FRSB') >= 0 || value.indexOf('FLOATING RATE') >= 0) {
                key += 'FRSB';
            } else if (value.indexOf('TAXABLE') >= 0) {
                key += 'TAX';
            } else if (value.indexOf('SAVINGS') >= 0 || value.indexOf('SAVING') >= 0) {
                key += 'SAV';
            }

            if (value.indexOf('8.05') >= 0) {
                key += '805';
            } else if (value.indexOf('7.75') >= 0) {
                key += '775';
            } else if (value.indexOf('8.50') >= 0 || value.indexOf('8.5%') >= 0 || value.indexOf('8.5 %') >= 0) {
                key += '850';
            } else if (value.indexOf('6.50') >= 0 || value.indexOf('6.5%') >= 0 || value.indexOf('6.5 %') >= 0) {
                key += '650';
            } else if (value.indexOf('9%') >= 0 || value.indexOf('9 %') >= 0) {
                key += '9';
            } else if (/[^0-9]8%/.test(value) || value.indexOf('8 %') >= 0) {
                key += '8';
            }

            return key;
        }

        function normalizeParticularsMatchKey(key) {
            return String(key || '').replace(/TAX/g, '');
        }

        function findQuarterReportRowByParticulars(particulars) {
            if (!particulars || !vm.model || !vm.model.length) {
                return null;
            }
            var targetKey = normalizeParticularsMatchKey(getParticularsMatchKey(particulars));
            if (!targetKey) {
                return null;
            }

            for (var i = 0; i < vm.model.length; i++) {
                var row = vm.model[i];
                if (isQuarterReportSummaryRow(row)) {
                    continue;
                }
                var rowKey = normalizeParticularsMatchKey(getParticularsMatchKey(row.totalClaim));
                if (rowKey && rowKey === targetKey) {
                    return row;
                }
            }
            return null;
        }

        function findQuarterReportRowForParticularsRow(particularsRow) {
            if (!particularsRow) {
                return null;
            }
            var quarterRow = findQuarterReportRowByParticulars(particularsRow.particulars);
            if (quarterRow) {
                return quarterRow;
            }
            if (particularsRow.index) {
                return findQuarterReportRowBySNo(resolveQuarterReportIndex(particularsRow.index));
            }
            return null;
        }

        function findQuarterReportTotalInterestRow() {
            if (!vm.model || !vm.model.length) {
                return null;
            }
            for (var i = 0; i < vm.model.length; i++) {
                var label = normalizeQuarterReportLabel(vm.model[i].totalClaim);
                if (label === 'TOTAL INTEREST') {
                    return vm.model[i];
                }
            }
            return null;
        }

        function hasDisplayValue(value) {
            return value !== undefined && value !== null && String(value).trim() !== '';
        }

        function applyQuarterReportToParticularsRowIfEmpty(particularsRow, quarterRow) {
            if (!particularsRow || !quarterRow) {
                return;
            }
            var trans = quarterRow.noOfTransections || quarterRow.noOfTransactions;
            var amount = quarterRow.amount;

            if (!hasDisplayValue(particularsRow.noOfTrans) &&
                trans !== undefined && trans !== null && trans !== '') {
                particularsRow.noOfTrans = formatCountForDisplay(trans);
            }

            if (!hasDisplayValue(particularsRow.amount)) {
                if (amount === '0' || amount === 0) {
                    if (!hasDisplayValue(particularsRow.noOfTrans)) {
                        particularsRow.noOfTrans = '0';
                    }
                    particularsRow.amount = '-';
                } else if (amount !== undefined && amount !== null && amount !== '') {
                    particularsRow.amount = formatAmountForDisplayFromValue(amount) || amount;
                }
            }
        }

        function applyQuarterReportToParticularsRow(particularsRow, quarterRow) {
            applyQuarterReportToParticularsRowIfEmpty(particularsRow, quarterRow);
        }

        function syncParticularsListFromQuarterReport(particularsList, options) {
            if (!particularsList || !vm.model || !vm.model.length) {
                return;
            }
            options = options || {};
            var totalInterestLabel = options.totalInterestLabel || 'Total Interest';
            var totalLabel = options.totalLabel || 'Total';
            var summary = getQuarterReportSummaryData();
            var tocRow = summary && summary.tocRow;
            var totalInterestQrRow = findQuarterReportTotalInterestRow();

            for (var i = 0; i < particularsList.length; i++) {
                var row = particularsList[i];
                var label = normalizeQuarterReportLabel(row.particulars);

                if (label === normalizeQuarterReportLabel(totalInterestLabel) ||
                    label === normalizeQuarterReportLabel(totalLabel) ||
                    label.indexOf('TOTAL (1 TO 10B)') >= 0) {
                    continue;
                }

                var quarterRow = findQuarterReportRowForParticularsRow(row);
                if (quarterRow) {
                    applyQuarterReportToParticularsRow(row, quarterRow);
                }
            }

            for (var j = 0; j < particularsList.length; j++) {
                var totalRow = particularsList[j];
                var totalRowLabel = normalizeQuarterReportLabel(totalRow.particulars);

                if (totalRowLabel === normalizeQuarterReportLabel(totalInterestLabel)) {
                    if (totalInterestQrRow) {
                        applyQuarterReportToParticularsRowIfEmpty(totalRow, totalInterestQrRow);
                    }
                }

                if (totalRowLabel === normalizeQuarterReportLabel(totalLabel) ||
                    totalRowLabel.indexOf('TOTAL (1 TO 10B)') >= 0) {
                    if (tocRow) {
                        if (!hasDisplayValue(totalRow.noOfTrans)) {
                            totalRow.noOfTrans = formatCountForDisplay(
                                tocRow.noOfTransections || tocRow.noOfTransactions
                            );
                        }
                        if (!hasDisplayValue(totalRow.amount)) {
                            totalRow.amount = formatAmountForDisplayFromValue(tocRow.amount);
                        }
                    }
                }
            }
        }

        function syncBankInternalAndAnnexureAFromQuarterReport() {
            if (!vm.model || !vm.model.length) {
                return;
            }
            if (vm.bankInternal && vm.bankInternal.particularsList) {
                syncParticularsListFromQuarterReport(vm.bankInternal.particularsList, {
                    totalInterestLabel: 'Total Interest',
                    totalLabel: 'Total'
                });
            }
            if (vm.annexureA && vm.annexureA.particularsList) {
                syncParticularsListFromQuarterReport(vm.annexureA.particularsList, {
                    totalInterestLabel: 'Total Interest',
                    totalLabel: 'Total (1 to 10b)'
                });
            }
            syncAnnexureBFromQuarterReport();
        }

        function findQuarterReportRowByMatchKey(matchKey) {
            if (!matchKey || !vm.model || !vm.model.length) {
                return null;
            }
            for (var i = 0; i < vm.model.length; i++) {
                var row = vm.model[i];
                if (isQuarterReportSummaryRow(row)) {
                    continue;
                }
                if (getParticularsMatchKey(row.totalClaim) === matchKey) {
                    return row;
                }
            }
            return null;
        }

        function getAnnexureBQuarterRowValues(quarterRow) {
            if (!quarterRow) {
                return { interest: '', trans: '' };
            }
            var trans = quarterRow.noOfTransections || quarterRow.noOfTransactions;
            var amount = quarterRow.amount;
            var values = { interest: '', trans: '' };
            if (trans !== undefined && trans !== null && trans !== '') {
                values.trans = formatCountForDisplay(trans);
            }
            if (amount !== undefined && amount !== null && amount !== '' &&
                amount !== '0' && amount !== 0) {
                values.interest = formatAmountForDisplayFromValue(amount);
            }
            return values;
        }

        function getQuarterReportRedemptionInterestTotals() {
            var totalTrans = 0;
            var totalAmt = 0;
            var hasData = false;

            if (!vm.model || !vm.model.length) {
                return { hasData: hasData, totalTrans: totalTrans, totalAmt: totalAmt };
            }

            for (var i = 0; i < vm.model.length; i++) {
                var row = vm.model[i];
                if (isQuarterReportSummaryRow(row)) {
                    continue;
                }
                var label = normalizeQuarterReportLabel(row.totalClaim);
                if (!label) {
                    continue;
                }
                if (label.indexOf('INTEREST PAYMENT') >= 0 ||
                    label.indexOf('HALF YEARLY') >= 0 ||
                    label.indexOf('TOTAL') >= 0 ||
                    label.indexOf('PRINCIPAL') >= 0) {
                    continue;
                }
                if (label.indexOf('INTEREST') < 0) {
                    continue;
                }

                var transNum = parseIndianAmount(row.noOfTransections || row.noOfTransactions);
                var amtNum = parseIndianAmount(row.amount);
                if (transNum !== null) {
                    totalTrans += transNum;
                    hasData = true;
                }
                if (amtNum !== null) {
                    totalAmt += amtNum;
                    hasData = true;
                }
            }

            return { hasData: hasData, totalTrans: totalTrans, totalAmt: totalAmt };
        }

        function setAnnexureBFieldIfEmpty(fieldName, value) {
            if (!vm.annexureB || hasDisplayValue(vm.annexureB[fieldName])) {
                return;
            }
            if (hasDisplayValue(value)) {
                vm.annexureB[fieldName] = value;
            }
        }

        function syncAnnexureBFromQuarterReport() {
            if (!vm.model || !vm.model.length || !vm.annexureB) {
                return;
            }

            vm.annexureB.periodLabel = getAnnexureBPeriodLabel();

            var goi805 = getAnnexureBQuarterRowValues(findQuarterReportRowByMatchKey('IP805'));
            var goi775 = getAnnexureBQuarterRowValues(findQuarterReportRowByMatchKey('IP775'));
            var rejects = getAnnexureBQuarterRowValues(findQuarterReportRowByMatchKey('HYREJ'));
            var totalInterestRow = findQuarterReportTotalInterestRow();
            var redemptionTotals = getQuarterReportRedemptionInterestTotals();

            setAnnexureBFieldIfEmpty('goi805Interest', goi805.interest);
            setAnnexureBFieldIfEmpty('goi805Trans', goi805.trans);
            setAnnexureBFieldIfEmpty('goi775Interest', goi775.interest);
            setAnnexureBFieldIfEmpty('goi775Trans', goi775.trans);
            setAnnexureBFieldIfEmpty('rejectsInterest', rejects.interest);
            setAnnexureBFieldIfEmpty('rejectsTrans', rejects.trans);

            if (totalInterestRow) {
                var totalB = getAnnexureBQuarterRowValues(totalInterestRow);
                setAnnexureBFieldIfEmpty('totalBInterest', totalB.interest);
                setAnnexureBFieldIfEmpty('totalBTrans', totalB.trans);
            }

            if (redemptionTotals.hasData) {
                setAnnexureBFieldIfEmpty('redemptionInterest', formatIndianAmount(redemptionTotals.totalAmt));
                setAnnexureBFieldIfEmpty('redemptionTrans', formatCountForDisplay(redemptionTotals.totalTrans));
                setAnnexureBFieldIfEmpty('totalCInterest', vm.annexureB.redemptionInterest);
                setAnnexureBFieldIfEmpty('totalCTrans', vm.annexureB.redemptionTrans);
            }

            if (!hasDisplayValue(vm.annexureB.grandTotalInterest) ||
                !hasDisplayValue(vm.annexureB.grandTotalTrans)) {
                var totalBAmount = parseIndianAmount(vm.annexureB.totalBInterest);
                var totalCAmount = parseIndianAmount(vm.annexureB.redemptionInterest);
                var totalBTrans = parseIndianAmount(vm.annexureB.totalBTrans);
                var totalCTrans = parseIndianAmount(vm.annexureB.redemptionTrans);

                if (!hasDisplayValue(vm.annexureB.grandTotalInterest) &&
                    totalBAmount !== null && totalCAmount !== null) {
                    vm.annexureB.grandTotalInterest = formatIndianAmount(totalBAmount + totalCAmount);
                }
                if (!hasDisplayValue(vm.annexureB.grandTotalTrans) &&
                    totalBTrans !== null && totalCTrans !== null) {
                    vm.annexureB.grandTotalTrans = formatCountForDisplay(totalBTrans + totalCTrans);
                }
            }
        }

        function formatAmountForDisplayFromValue(value) {
            var num = parseIndianAmount(value);
            if (num !== null) {
                return formatIndianAmount(num);
            }
            return value ? String(value) : '';
        }

        function initTaxInvoiceData() {
            vm.taxInvoice = {
                invoiceBillNo: '',
                invoiceDate: '',
                recipientAddress: 'Reserve Bank of India, Additional office Building East High Court Road Nagpur-440001',
                recipientGstn: '27AAIFR5286M1ZG',
                supplierAddress: 'HDFC Bank Ltd, HDFC Bank House, Senapati Bapat Marg, Lower Parel W',
                supplierState: 'Mumbai',
                supplierStateCode: '27',
                supplierGstn: '27AAACH2702H1Z0',
                placeOfSupplyState: 'Maharashtra',
                placeOfSupplyStateCode: '27',
                totalPayment: '',
                totalToc: '',
                gstAmount: '',
                totalTocIncludingGst: '',
                serviceDescription: 'TOC Taxable',
                hsnSacCode: '997111',
                hsnSacDescription: 'Other financial services (except investment banking, insurance services and pension services)',
                cgstRate: '9',
                sgstRate: '9',
                igstRate: '18',
                tocTaxableAmount: '',
                cgstAmount: '',
                sgstAmount: '',
                igstAmount: '',
                totalInvoiceValue: ''
            };
            return vm.taxInvoice;
        }

        function syncTaxInvoiceFromExternalAudit() {
            if (!vm.externalAudit || !vm.taxInvoice) {
                return;
            }

            if (!hasDisplayValue(vm.taxInvoice.totalPayment) && hasDisplayValue(vm.externalAudit.turnover)) {
                vm.taxInvoice.totalPayment = vm.externalAudit.turnover;
            }
            if (!hasDisplayValue(vm.taxInvoice.totalToc) && hasDisplayValue(vm.externalAudit.tocCommission)) {
                vm.taxInvoice.totalToc = vm.externalAudit.tocCommission;
            }
            if (!hasDisplayValue(vm.taxInvoice.tocTaxableAmount) && hasDisplayValue(vm.externalAudit.tocCommission)) {
                vm.taxInvoice.tocTaxableAmount = vm.externalAudit.tocCommission;
            }
            if (!hasDisplayValue(vm.taxInvoice.gstAmount) && hasDisplayValue(vm.externalAudit.gstCommission)) {
                vm.taxInvoice.gstAmount = vm.externalAudit.gstCommission;
                if (!hasDisplayValue(vm.taxInvoice.cgstAmount) && !hasDisplayValue(vm.taxInvoice.sgstAmount)) {
                    var gstNum = parseIndianAmount(vm.externalAudit.gstCommission);
                    if (gstNum !== null) {
                        var halfGst = gstNum / 2;
                        vm.taxInvoice.cgstAmount = formatIndianAmount(halfGst);
                        vm.taxInvoice.sgstAmount = formatIndianAmount(halfGst);
                    }
                }
            }
            if (!hasDisplayValue(vm.taxInvoice.cgstAmount) && hasDisplayValue(vm.externalAudit.cgstAmount)) {
                vm.taxInvoice.cgstAmount = vm.externalAudit.cgstAmount;
            }
            if (!hasDisplayValue(vm.taxInvoice.sgstAmount) && hasDisplayValue(vm.externalAudit.sgstAmount)) {
                vm.taxInvoice.sgstAmount = vm.externalAudit.sgstAmount;
            }
            if (!hasDisplayValue(vm.taxInvoice.totalTocIncludingGst) &&
                hasDisplayValue(vm.externalAudit.totalCommission)) {
                vm.taxInvoice.totalTocIncludingGst = vm.externalAudit.totalCommission;
            }
            if (!hasDisplayValue(vm.taxInvoice.totalInvoiceValue) &&
                hasDisplayValue(vm.externalAudit.totalCommission)) {
                vm.taxInvoice.totalInvoiceValue = vm.externalAudit.totalCommission;
            }
        }

        function loadTaxInvoiceData(result) {
            if (!result || Array.isArray(result)) {
                syncTaxInvoiceFromExternalAudit();
                return;
            }

            var invoice = result.taxInvoice || result.taxInvoicePart1 || {};
            if (!vm.taxInvoice) {
                initTaxInvoiceData();
            }

            angular.extend(vm.taxInvoice, {
                invoiceBillNo: invoice.invoiceBillNo || invoice.invoiceNo || vm.taxInvoice.invoiceBillNo,
                invoiceDate: invoice.invoiceDate || vm.taxInvoice.invoiceDate,
                recipientAddress: invoice.recipientAddress || vm.taxInvoice.recipientAddress,
                recipientGstn: invoice.recipientGstn || vm.taxInvoice.recipientGstn,
                supplierAddress: invoice.supplierAddress || vm.taxInvoice.supplierAddress,
                supplierState: invoice.supplierState || vm.taxInvoice.supplierState,
                supplierStateCode: invoice.supplierStateCode || vm.taxInvoice.supplierStateCode,
                supplierGstn: invoice.supplierGstn || vm.taxInvoice.supplierGstn,
                placeOfSupplyState: invoice.placeOfSupplyState || vm.taxInvoice.placeOfSupplyState,
                placeOfSupplyStateCode: invoice.placeOfSupplyStateCode || vm.taxInvoice.placeOfSupplyStateCode,
                totalPayment: invoice.totalPayment || invoice.turnover || vm.taxInvoice.totalPayment,
                totalToc: invoice.totalToc || invoice.tocCommission || vm.taxInvoice.totalToc,
                gstAmount: invoice.gstAmount || invoice.gstCommission || vm.taxInvoice.gstAmount,
                totalTocIncludingGst: invoice.totalTocIncludingGst || invoice.totalCommission || vm.taxInvoice.totalTocIncludingGst,
                serviceDescription: invoice.serviceDescription || vm.taxInvoice.serviceDescription,
                hsnSacCode: invoice.hsnSacCode || invoice.hsnCode || vm.taxInvoice.hsnSacCode,
                hsnSacDescription: invoice.hsnSacDescription || invoice.sacDescription || vm.taxInvoice.hsnSacDescription,
                cgstRate: invoice.cgstRate || vm.taxInvoice.cgstRate,
                sgstRate: invoice.sgstRate || vm.taxInvoice.sgstRate,
                igstRate: invoice.igstRate || vm.taxInvoice.igstRate,
                tocTaxableAmount: invoice.tocTaxableAmount || invoice.totalToc || vm.taxInvoice.tocTaxableAmount,
                cgstAmount: invoice.cgstAmount || vm.taxInvoice.cgstAmount,
                sgstAmount: invoice.sgstAmount || vm.taxInvoice.sgstAmount,
                igstAmount: invoice.igstAmount || vm.taxInvoice.igstAmount,
                totalInvoiceValue: invoice.totalInvoiceValue || invoice.totalTocIncludingGst || vm.taxInvoice.totalInvoiceValue
            });

            syncTaxInvoiceFromExternalAudit();
        }

        function buildTaxInvoiceSpacer() {
            return "<p style='margin:0;padding:0;font-size:12pt;line-height:24pt;mso-line-height-rule:exactly;'>" +
                "&nbsp;</p>";
        }

        function buildTaxInvoiceParticularsOfSupplyHtml(invoice) {
            var tocTaxable = safe(invoice.tocTaxableAmount || invoice.totalToc) || '&nbsp;';
            var cgstRate = safe(invoice.cgstRate) || '9';
            var sgstRate = safe(invoice.sgstRate) || '9';
            var igstRate = safe(invoice.igstRate) || '18';
            var cgstAmount = safe(invoice.cgstAmount) || '&nbsp;';
            var sgstAmount = safe(invoice.sgstAmount) || '&nbsp;';
            var totalInvoiceValue = safe(invoice.totalInvoiceValue || invoice.totalTocIncludingGst) || '&nbsp;';
            var serviceDescription = safe(invoice.serviceDescription) || 'TOC Taxable';
            var hsnSacCode = safe(invoice.hsnSacCode) || '997111';
            var hsnSacDescription = safe(invoice.hsnSacDescription) ||
                'Other financial services (except investment banking, insurance services and pension services)';
            var cellStyle = 'padding:6px;border:1px solid #000;';
            var html = '';

            html +=
                "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:100%;border:1px solid #000;margin:0;'>" +
                "<thead><tr style='background:#d9d9d9;'>" +
                "<th style='font-weight:bold;width:50%;text-align:left;" + cellStyle + "'>Particulars of Supply</th>" +
                "<th style='font-weight:bold;width:15%;text-align:center;" + cellStyle + "'>Rate</th>" +
                "<th style='font-weight:bold;width:35%;text-align:right;" + cellStyle + "'>Amount in \u20B9</th>" +
                "</tr></thead><tbody>" +
                "<tr>" +
                "<td style='vertical-align:top;text-align:left;" + cellStyle + "'>" +
                "<b>Service Description:</b> " + serviceDescription + "</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "<td style='text-align:right;" + cellStyle + "'>" + tocTaxable + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='text-align:left;" + cellStyle + "'><b>HSN/SAC Code:</b> " + hsnSacCode + "</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='text-align:left;" + cellStyle + "'>" +
                "<b>HAS/SAC Code Description:</b> " + hsnSacDescription + "</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='text-align:left;" + cellStyle + "'><b>CGST (%)</b></td>" +
                "<td style='text-align:right;" + cellStyle + "'>" + cgstRate + "</td>" +
                "<td style='text-align:right;" + cellStyle + "'>" + cgstAmount + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='vertical-align:top;text-align:left;" + cellStyle + "'>" +
                "<b>SGST/UTGST (%)</b><br/><b>Or</b><br/><b>IGST (%)</b></td>" +
                "<td style='vertical-align:top;text-align:right;" + cellStyle + "'>" +
                sgstRate + "<br/><b>Or</b><br/>Or " + igstRate + "</td>" +
                "<td style='vertical-align:top;text-align:right;" + cellStyle + "'>" +
                sgstAmount + "<br/><b>Or</b><br/>Or " + igstRate + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='text-align:right;" + cellStyle + "'><b>Total</b></td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "<td style='text-align:right;" + cellStyle + "'><b>B</b></td>" +
                "</tr>" +
                "<tr>" +
                "<td colspan='2' style='text-align:left;" + cellStyle + "'><b>Total Invoice Value</b></td>" +
                "<td style='text-align:right;" + cellStyle + "'><b>" + totalInvoiceValue + "</b></td>" +
                "</tr>" +
                "</tbody></table>";

            return html;
        }

        function buildTaxInvoiceSignatureHtml() {
            return "<p style='font-size:12pt;font-weight:bold;margin:0;text-align:left;'>" +
                "Signature, name, designation of authorized official</p>";
        }

        function buildTaxInvoiceHtml() {
            var invoice = vm.taxInvoice || initTaxInvoiceData();
            var quarterEnding = getQuarterEndingSubjectLabel();
            var invoiceBillNo = safe(invoice.invoiceBillNo) || '_______________';
            var invoiceDate = safe(invoice.invoiceDate) || getExportFooterDate();
            var totalPayment = safe(invoice.totalPayment) || '&nbsp;';
            var totalToc = safe(invoice.totalToc) || '&nbsp;';
            var gstAmount = safe(invoice.gstAmount) || '&nbsp;';
            var totalTocIncludingGst = safe(invoice.totalTocIncludingGst) || '&nbsp;';
            var cellStyle = 'padding:6px;border:1px solid #000;';
            var html = '';

            html +=
                "<div style='text-align:center;font-weight:bold;font-size:14pt;margin-bottom:8px;'>" +
                "Formats for claiming Turn Over Commission (TOC) on Bond from RBI</div>" +
                "<div style='text-align:center;font-size:12pt;margin-bottom:12px;'>" +
                "Turn Over Commission (TOC) Claim for the quarter ended " + quarterEnding + ".</div>" +
                "<p style='font-size:12pt;font-weight:bold;margin:0 0 12px 0;text-align:center;'>PART 1- Tax Invoice</p>" +
                "<p style='font-size:12pt;margin:0 0 4px 0;text-align:right;'>Invoice / Bill No : " + invoiceBillNo + "</p>" +
                "<p style='font-size:12pt;margin:0 0 16px 0;text-align:right;'>Date: " + invoiceDate + "</p>";

            html +=
                "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:100%;border:1px solid #000;margin:0;'>" +
                "<thead><tr style='background:#d9d9d9;'>" +
                "<th style='font-weight:bold;width:50%;text-align:left;" + cellStyle + "'>Reserve Bank of India (Recipient of Services)</th>" +
                "<th style='font-weight:bold;width:50%;text-align:left;" + cellStyle + "'>Name of the Agency Bank (Supplier of services):</th>" +
                "</tr></thead><tbody>" +
                "<tr>" +
                "<td style='vertical-align:top;text-align:left;" + cellStyle + "'>" +
                "<b>Address:</b> " + safe(invoice.recipientAddress) + "</td>" +
                "<td style='vertical-align:top;text-align:left;" + cellStyle + "'>" +
                "<b>Address of Registered Office of the agency bank submitting the claim:</b> " + safe(invoice.supplierAddress) +
                "<br/><b>State:</b> " + safe(invoice.supplierState) +
                "<br/><b>State Code:</b> " + safe(invoice.supplierStateCode) + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='text-align:left;" + cellStyle + "'><b>GSTN:</b> " + safe(invoice.recipientGstn) + "</td>" +
                "<td style='text-align:left;" + cellStyle + "'><b>GSTN :</b> " + safe(invoice.supplierGstn) + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='text-align:left;" + cellStyle + "'><b>PAN:</b></td>" +
                "<td style='text-align:left;" + cellStyle + "'><b>PAN:</b></td>" +
                "</tr>" +
                "<tr>" +
                "<td style='text-align:left;" + cellStyle + "'>" +
                "<b>Place of Supply</b><br/>Sate Name: " + safe(invoice.placeOfSupplyState) +
                "<br/>State Code: " + safe(invoice.placeOfSupplyStateCode) + "</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "</tr>" +
                "</tbody></table>";

            html += buildTaxInvoiceSpacer();

            html +=
                "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:100%;border:1px solid #000;margin:0;'>" +
                "<thead><tr>" +
                "<th style='width:60%;" + cellStyle + "'>&nbsp;</th>" +
                "<th style='width:40%;text-align:right;font-weight:bold;" + cellStyle + "'>Amount (\u20B9)</th>" +
                "</tr></thead><tbody>" +
                "<tr><td style='text-align:left;" + cellStyle + "'>Total Payment</td>" +
                "<td style='text-align:right;" + cellStyle + "'>" + totalPayment + "</td></tr>" +
                "<tr><td style='text-align:left;" + cellStyle + "'>Total TOC (7.00 Paise per Rs 100/- turnover)</td>" +
                "<td style='text-align:right;" + cellStyle + "'>" + totalToc + "</td></tr>" +
                "<tr><td style='text-align:left;" + cellStyle + "'>GST (@18%)</td>" +
                "<td style='text-align:right;" + cellStyle + "'>" + gstAmount + "</td></tr>" +
                "<tr><td style='text-align:left;" + cellStyle + "'><b>Total TOC (including GST)</b></td>" +
                "<td style='text-align:right;" + cellStyle + "'><b>" + totalTocIncludingGst + "</b></td></tr>" +
                "</tbody></table>";

            html += buildTaxInvoiceSpacer();
            html += buildTaxInvoiceParticularsOfSupplyHtml(invoice);

            html += buildTaxInvoiceSpacer();
            html += buildTaxInvoiceSignatureHtml();

            return html;
        }

        function buildTaxInvoiceExportTable() {
            return buildTaxInvoiceHtml();
        }

        function getBankInternalLetterDate() {
            if (vm.bankInternal && vm.bankInternal.letterDate) {
                return vm.bankInternal.letterDate;
            }
            var monthNames = [
                'January', 'February', 'March', 'April', 'May', 'June',
                'July', 'August', 'September', 'October', 'November', 'December'
            ];
            var d = new Date();
            return d.getDate() + '-' + monthNames[d.getMonth()] + '-' + d.getFullYear();
        }

        function getRbiCircularReference() {
            var fy = vm.financialYear || '';
            var startYear = fy ? fy.split('-')[0] : new Date().getFullYear();
            var circularYear = parseInt(startYear, 10) + 1;
            return 'DGBA.GBD.No.S2/31-12-010/' + fy + ', dated April 01, ' + circularYear;
        }

        function getDefaultBankInternalParticularsRows() {
            return [
                { index: '1', particulars: 'Interest Payment \u2013 8.05%', noOfTrans: '', amount: '', bold: false },
                { index: '1a', particulars: 'Interest Payment \u2013 7.75%', noOfTrans: '', amount: '', bold: false },
                { index: '1b', particulars: 'Half Yearly Interest Rejects reinitiated', noOfTrans: '', amount: '', bold: false },
                { index: '', particulars: 'Total Interest', noOfTrans: '', amount: '', bold: true },
                { index: '3a', particulars: 'Principal redemption 9% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '3b', particulars: 'Interest redemption 9% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '4a', particulars: 'Principal redemption 8.50% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '4b', particulars: 'Interest redemption 8.50% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '5a', particulars: 'Principal redemption 8% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '5b', particulars: 'Interest redemption 8% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '6a', particulars: 'Principal redemption 6.50% Savings Bond', noOfTrans: '', amount: '', bold: false },
                { index: '6b', particulars: 'Interest redemption 6.50% Savings Bond', noOfTrans: '', amount: '', bold: false },
                { index: '7a', particulars: 'Principal redemption 8% Savings Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '7b', particulars: 'Interest redemption 8% Savings Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '8a', particulars: 'Principal Prematurity 7.75% Savings Bond', noOfTrans: '', amount: '', bold: false },
                { index: '8b', particulars: 'Interest Prematurity 7.75% Saving Bonds', noOfTrans: '', amount: '', bold: false },
                { index: '9a', particulars: 'Principal redemption 7.75% Savings Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '9b', particulars: 'Interest redemption 7.75% Saving Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '10a', particulars: 'Principal Prematurity 8.05% Floating Rate Savings Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '10b', particulars: 'Interest Prematurity 8.05% Floating Rate Saving Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '', particulars: 'Total', noOfTrans: '', amount: '', bold: true }
            ];
        }

        function initBankInternalFormatData() {
            vm.bankInternal = {
                letterDate: '',
                currentAccountNo: '32105001001',
                tocRate: '0.070',
                signatory: 'Nitin Nimawat \u2013 Assistant Vice President Nagpur',
                particularsList: getDefaultBankInternalParticularsRows(),
                totalNoOfTrans: '',
                totalAmount: '',
                turnover: '',
                tocCommission: '',
                gstCommission: '',
                totalCommission: '',
                amountWords: '',
                commissionCalcDisplay: ''
            };
            syncBankInternalFromExternalAudit();
            return vm.bankInternal;
        }

        function syncBankInternalFromExternalAudit() {
            if (!vm.bankInternal) {
                return;
            }
            var audit = vm.externalAudit || {};
            if (!hasDisplayValue(vm.bankInternal.currentAccountNo) && hasDisplayValue(audit.currentAccountNo)) {
                vm.bankInternal.currentAccountNo = audit.currentAccountNo;
            }
            if (!hasDisplayValue(vm.bankInternal.totalNoOfTrans) && hasDisplayValue(audit.noOfTransactions)) {
                vm.bankInternal.totalNoOfTrans = audit.noOfTransactions;
            }
            if (!hasDisplayValue(vm.bankInternal.totalAmount) && hasDisplayValue(audit.turnover)) {
                vm.bankInternal.totalAmount = audit.turnover;
            }
            if (!hasDisplayValue(vm.bankInternal.turnover) && hasDisplayValue(audit.turnover)) {
                vm.bankInternal.turnover = audit.turnover;
            }
            if (!hasDisplayValue(vm.bankInternal.commissionCalcDisplay) && hasDisplayValue(audit.turnover)) {
                vm.bankInternal.commissionCalcDisplay = audit.turnover + ' X 0.070/100';
            }
            if (!hasDisplayValue(vm.bankInternal.tocCommission) && hasDisplayValue(audit.tocCommission)) {
                vm.bankInternal.tocCommission = audit.tocCommission;
            }
            if (!hasDisplayValue(vm.bankInternal.gstCommission) && hasDisplayValue(audit.gstCommission)) {
                vm.bankInternal.gstCommission = audit.gstCommission;
            }
            if (!hasDisplayValue(vm.bankInternal.totalCommission) && hasDisplayValue(audit.totalCommission)) {
                vm.bankInternal.totalCommission = audit.totalCommission;
            }
            if (!hasDisplayValue(vm.bankInternal.amountWords)) {
                vm.bankInternal.amountWords = getAmountWordsForDisplay(
                    audit.totalCommission || vm.bankInternal.totalCommission,
                    audit.amountWords || vm.bankInternal.amountWords
                );
            }
            if (!hasDisplayValue(vm.bankInternal.signatory) && hasDisplayValue(vm.annex2aSignatory)) {
                vm.bankInternal.signatory = vm.annex2aSignatory;
            }
        }

        function loadBankInternalFormatData(result) {
            if (!result || Array.isArray(result)) {
                syncBankInternalAndAnnexureAFromQuarterReport();
                syncBankInternalFromExternalAudit();
                return;
            }

            var data = result.bankInternalFormat || result.bankInternal || result.section7 || {};
            if (!vm.bankInternal) {
                initBankInternalFormatData();
            }

            angular.extend(vm.bankInternal, {
                letterDate: data.letterDate || vm.bankInternal.letterDate,
                currentAccountNo: data.currentAccountNo || vm.bankInternal.currentAccountNo,
                tocRate: data.tocRate || vm.bankInternal.tocRate,
                signatory: data.signatory || data.authorizedSignatory || vm.bankInternal.signatory,
                totalNoOfTrans: data.totalNoOfTrans || data.noOfTransactions || vm.bankInternal.totalNoOfTrans,
                totalAmount: data.totalAmount || data.turnover || vm.bankInternal.totalAmount,
                turnover: data.turnover || vm.bankInternal.turnover,
                tocCommission: data.tocCommission || vm.bankInternal.tocCommission,
                gstCommission: data.gstCommission || vm.bankInternal.gstCommission,
                totalCommission: data.totalCommission || vm.bankInternal.totalCommission,
                amountWords: data.amountWords || vm.bankInternal.amountWords,
                commissionCalcDisplay: data.commissionCalcDisplay || vm.bankInternal.commissionCalcDisplay
            });

            if (data.particularsList && data.particularsList.length) {
                vm.bankInternal.particularsList = data.particularsList;
            }

            syncBankInternalAndAnnexureAFromQuarterReport();
            syncBankInternalFromExternalAudit();
        }

        function formatBankInternalAmount(value) {
            if (value === null || value === undefined || value === '') {
                return '&nbsp;';
            }
            if (value === '-' || value === 0 || value === '0') {
                return '-';
            }
            return safe(value);
        }

        function buildBankInternalParticularsTableHtml(data, quarterEnding) {
            var rows = data.particularsList || getDefaultBankInternalParticularsRows();
            var cellStyle = 'padding:6px;border:1px solid #000;';
            var html =
                "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:100%;border:1px solid #000;margin:0;'>" +
                "<thead><tr>" +
                "<th colspan='3' style='font-weight:bold;text-align:center;" + cellStyle + "'>" +
                "Agency Commission claim on Relief and Savings Bond Redemption and Half Yearly Interest " +
                "for the quarter ended " + quarterEnding + ".</th>" +
                "</tr><tr style='background:#d9d9d9;'>" +
                "<th style='font-weight:bold;width:55%;text-align:left;" + cellStyle + "'>Particulars</th>" +
                "<th style='font-weight:bold;width:20%;text-align:center;" + cellStyle + "'>No of Trans.</th>" +
                "<th style='font-weight:bold;width:25%;text-align:right;" + cellStyle + "'>Amount in Rs.</th>" +
                "</tr></thead><tbody>";

            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                var isBold = row.bold || row.particulars === 'Total' || row.particulars === 'Total Interest';
                var boldStyle = isBold ? 'font-weight:bold;' : '';
                var noOfTrans = row.noOfTrans !== undefined && row.noOfTrans !== '' ?
                    safe(row.noOfTrans) : '&nbsp;';
                var amount = formatBankInternalAmount(row.amount);

                if (row.particulars === 'Total' && data.totalNoOfTrans) {
                    noOfTrans = safe(data.totalNoOfTrans);
                }
                if (row.particulars === 'Total' && data.totalAmount) {
                    amount = safe(data.totalAmount);
                }

                html +=
                    "<tr>" +
                    "<td style='text-align:left;" + boldStyle + cellStyle + "'>" + safe(row.particulars) + "</td>" +
                    "<td style='text-align:center;" + boldStyle + cellStyle + "'>" + noOfTrans + "</td>" +
                    "<td style='text-align:right;" + boldStyle + cellStyle + "'>" + amount + "</td>" +
                    "</tr>";
            }

            html += "</tbody></table>";
            return html;
        }

        function buildBankInternalCommissionTableHtml(data) {
            var cellStyle = 'padding:6px;border:1px solid #000;';
            var turnover = safe(data.turnover) || '&nbsp;';
            var calcDisplay = safe(data.commissionCalcDisplay) ||
                (data.turnover ? safe(data.turnover) + ' X 0.070/100' : '&nbsp;');
            var tocCommission = safe(data.tocCommission) || '&nbsp;';
            var gstCommission = safe(data.gstCommission) || '&nbsp;';
            var totalCommission = safe(data.totalCommission) || '&nbsp;';

            return "<p style='font-size:12pt;font-weight:bold;margin:0 0 8px 0;text-align:left;'>" +
                "1. Agency Commission Claimed:</p>" +
                "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:100%;border:1px solid #000;margin:0;'>" +
                "<tbody>" +
                "<tr><td style='text-align:left;" + cellStyle + "'>Receipts @ Rs. 40/- Per Transaction</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "<td style='text-align:right;" + cellStyle + "'>-</td></tr>" +
                "<tr><td style='text-align:left;" + cellStyle + "'>" +
                "Payment other than pension @ Rs. 7.00 Paise per Rs 100/-</td>" +
                "<td style='text-align:center;" + cellStyle + "'>" + calcDisplay + "</td>" +
                "<td style='text-align:right;" + cellStyle + "'>" + tocCommission + "</td></tr>" +
                "<tr><td style='text-align:left;" + cellStyle + "'>GST \u2013 18%</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "<td style='text-align:right;" + cellStyle + "'>" + gstCommission + "</td></tr>" +
                "<tr><td style='text-align:left;" + cellStyle + "'>Pension Payment @ Rs. 75/-</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "<td style='text-align:right;" + cellStyle + "'>-</td></tr>" +
                "<tr><td style='text-align:left;font-weight:bold;" + cellStyle + "'>Total Claim</td>" +
                "<td style='" + cellStyle + "'>&nbsp;</td>" +
                "<td style='text-align:right;font-weight:bold;" + cellStyle + "'>" + totalCommission + "</td></tr>" +
                "</tbody></table>";
        }

        function buildBankInternalFormatHtml() {
            var data = vm.bankInternal || initBankInternalFormatData();
            var quarterEnding = getQuarterEndingSubjectLabel();
            var letterDate = getBankInternalLetterDate();
            var accountNo = safe(data.currentAccountNo) || '32105001001';
            var totalNoOfTrans = safe(data.totalNoOfTrans) || '_______________';
            var totalCommission = safe(data.totalCommission) || getTocClaimAmountDisplay() || '_______________';
            var amountWords = getAmountWordsForDisplay(
                data.totalCommission || totalCommission,
                data.amountWords || getTocClaimAmountWordsDisplay()
            );
            var amountWordsText = amountWords ?
                '( Rupees ' + safe(amountWords) + ' Only )' :
                '( Rupees _______________ Only )';
            var signatory = safe(data.signatory) || 'Nitin Nimawat \u2013 Assistant Vice President Nagpur';
            var circularRef = getRbiCircularReference();
            var html = '';

            html += buildSectionCenterTitleHtml('Bank Internal Format');
            html +=
                "<p style='font-size:12pt;margin:0 0 16px 0;text-align:left;'>" + letterDate + "</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0;text-align:left;'>To</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0;text-align:left;'>The General Manager,</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0;text-align:left;'>Reserve Bank of India,</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0;text-align:left;'>Central Accounts Section,</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 16px 0;text-align:left;'>Nagpur.</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 16px 0;text-align:left;'>Respected Sir / Madam,</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 12px 0;text-align:left;'>" +
                "<b>Subject:</b> Agency Commission Claim on Relief and Savings Bond Redemption and Half yearly Interest " +
                "for the Quarter ended " + quarterEnding + " :</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 16px 0;text-align:left;'>" +
                "Please find enclosed TOC Claim on Redemption Claim of 9.00%, 8.50% 8.00%, 7.00%, and 6.50% Relief and Savings Bonds " +
                "&amp; Half Yearly Interest Claim for the quarter ended " + quarterEnding + ".</p>";

            html += buildBankInternalParticularsTableHtml(data, quarterEnding);
            html += buildTaxInvoiceSpacer();
            html += buildBankInternalCommissionTableHtml(data);

            html +=
                "<p style='font-size:12pt;line-height:1.5;margin:16px 0 12px 0;text-align:left;'>" +
                "2. Certified that the above claim has been made on the basis of payment recorded in the daily scrolls of " +
                "Government Transactions furnished to the accounting authorities of the Central Governments.</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 12px 0;text-align:left;'>" +
                "3. We certify that we have processed <b>" + totalNoOfTrans + "</b> transactions for quarter ended " + quarterEnding +
                ", and our TOC is <b>Rs. " + totalCommission + " " + amountWordsText + "</b>" +
                " calculated @ <b>Rs. " + safe(data.tocRate || '0.070') + "</b> per Rs 100.00. We request you to please credit " +
                "the amount in our current <b>account no " + accountNo + "</b>, with an advice to us. We hereby confirm and certify " +
                "that the amount of Turnover and the Commission claimed there upon has been calculated as per the Reserve Bank of India " +
                "Circular no " + circularRef + ".</p>" +
                "<p style='font-size:12pt;line-height:1.5;margin:0 0 20px 0;text-align:left;'>" +
                "4. We hereby certify that all the necessary records have been checked and verified by our internal auditor to arrive " +
                "at the number of transactions and the amount, reported in our claim for Turnover Commission for the Quarter ended " +
                quarterEnding + ". The Calculation of the commission claimed for the quarter is also checked/verified by our internal auditor.</p>" +
                "<p style='font-size:12pt;line-height:1.7;margin:32px 0 24px 0;text-align:left;'>Thanking You,</p>" +
                "<p style='font-size:12pt;line-height:1.7;margin:0 0 4px 0;text-align:left;'><u><b>FOR HDFC BANK LTD</b></u></p>" +
                "<p style='font-size:12pt;line-height:1.7;margin:0 0 36px 0;text-align:left;'><u><b>Authorized Signatory</b></u></p>" +
                "<p style='font-size:12pt;line-height:1.7;margin:0;text-align:left;'><u><b>(" + signatory + ")</b></u></p>";

            return html;
        }

        function buildBankInternalFormatExportTable() {
            return buildBankInternalFormatHtml();
        }

        function getDefaultAnnexureARows() {
            return [
                { index: '1', particulars: 'Interest Payment \u2013 8.05%', noOfTrans: '', amount: '', bold: false },
                { index: '1a', particulars: 'Interest Payment \u2013 7.75%', noOfTrans: '', amount: '', bold: false },
                { index: '1b', particulars: 'Half Yearly Interest Rejects reinitiated', noOfTrans: '', amount: '', bold: false },
                { index: '2', particulars: 'Total Interest', noOfTrans: '', amount: '', bold: true },
                { index: '3a', particulars: 'Principal redemption 9% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '3b', particulars: 'Interest redemption 9% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '4a', particulars: 'Principal redemption 8.50% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '4b', particulars: 'Interest redemption 8.50% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '5a', particulars: 'Principal redemption 8% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '5b', particulars: 'Interest redemption 8% Relief Bond', noOfTrans: '', amount: '', bold: false },
                { index: '6a', particulars: 'Principal redemption 6.50% Savings Bond', noOfTrans: '', amount: '', bold: false },
                { index: '6b', particulars: 'Interest redemption 6.50% Savings Bond', noOfTrans: '', amount: '', bold: false },
                { index: '7a', particulars: 'Principal redemption 8% Savings Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '7b', particulars: 'Interest redemption 8% Savings Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '8a', particulars: 'Principal Prematurity 7.75% Savings Bond', noOfTrans: '', amount: '', bold: false },
                { index: '8b', particulars: 'Interest Prematurity 7.75% Saving Bonds', noOfTrans: '', amount: '', bold: false },
                { index: '9a', particulars: 'Principal redemption 7.75% Savings Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '9b', particulars: 'Interest redemption 7.75% Saving Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '10a', particulars: 'Principal Prematurity 8.05% Floating Rate Savings Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '10b', particulars: 'Interest Prematurity 8.05% Floating Rate Saving Taxable Bond', noOfTrans: '', amount: '', bold: false },
                { index: '', particulars: 'Total (1 to 10b)', noOfTrans: '', amount: '', bold: true }
            ];
        }

        function initAnnexureAData() {
            vm.annexureA = {
                tocRate: '0.070',
                particularsList: getDefaultAnnexureARows(),
                totalNoOfTrans: '',
                totalAmount: '',
                turnover: '',
                totalCommission: '',
                commissionCalcDisplay: '',
                amountWords: ''
            };
            syncAnnexureAFromExternalAudit();
            return vm.annexureA;
        }

        function syncAnnexureAFromExternalAudit() {
            if (!vm.annexureA) {
                return;
            }
            var audit = vm.externalAudit || {};
            if (!hasDisplayValue(vm.annexureA.totalNoOfTrans) && hasDisplayValue(audit.noOfTransactions)) {
                vm.annexureA.totalNoOfTrans = audit.noOfTransactions;
            }
            if (!hasDisplayValue(vm.annexureA.totalAmount) && hasDisplayValue(audit.turnover)) {
                vm.annexureA.totalAmount = audit.turnover;
            }
            if (!hasDisplayValue(vm.annexureA.turnover) && hasDisplayValue(audit.turnover)) {
                vm.annexureA.turnover = audit.turnover;
            }
            if (!hasDisplayValue(vm.annexureA.commissionCalcDisplay) && hasDisplayValue(audit.turnover)) {
                vm.annexureA.commissionCalcDisplay = audit.turnover + ' X 0.070/100';
            }
            if (!hasDisplayValue(vm.annexureA.totalCommission) && hasDisplayValue(audit.totalCommission)) {
                vm.annexureA.totalCommission = audit.totalCommission;
            }
            if (!hasDisplayValue(vm.annexureA.amountWords)) {
                vm.annexureA.amountWords = getAmountWordsForDisplay(
                    audit.totalCommission || vm.annexureA.totalCommission,
                    audit.amountWords || vm.annexureA.amountWords || vm.annex2aAmountWords
                );
            }
        }

        function loadAnnexureAData(result) {
            if (!result || Array.isArray(result)) {
                syncBankInternalAndAnnexureAFromQuarterReport();
                syncAnnexureAFromExternalAudit();
                return;
            }

            var data = result.annexureA || result.annexureAList || result.section8 || {};
            if (!vm.annexureA) {
                initAnnexureAData();
            }

            angular.extend(vm.annexureA, {
                tocRate: data.tocRate || vm.annexureA.tocRate,
                totalNoOfTrans: data.totalNoOfTrans || data.noOfTransactions || vm.annexureA.totalNoOfTrans,
                totalAmount: data.totalAmount || data.turnover || vm.annexureA.totalAmount,
                turnover: data.turnover || vm.annexureA.turnover,
                totalCommission: data.totalCommission || vm.annexureA.totalCommission,
                commissionCalcDisplay: data.commissionCalcDisplay || vm.annexureA.commissionCalcDisplay,
                amountWords: data.amountWords || vm.annexureA.amountWords
            });

            if (data.particularsList && data.particularsList.length) {
                vm.annexureA.particularsList = data.particularsList;
            }

            syncBankInternalAndAnnexureAFromQuarterReport();
            syncAnnexureAFromExternalAudit();
        }

        function formatAnnexureAAmount(value) {
            if (value === null || value === undefined || value === '') {
                return '&nbsp;';
            }
            if (value === '-' || value === 0 || value === '0') {
                return '-';
            }
            return safe(value);
        }

        function buildAnnexureATableHtml(data, quarterEnding) {
            var rows = data.particularsList || getDefaultAnnexureARows();
            var cellStyle = 'padding:6px;border:1px solid #000;';
            var turnover = safe(data.turnover) || '&nbsp;';
            var calcDisplay = safe(data.commissionCalcDisplay) ||
                (data.turnover ? safe(data.turnover) + ' X 0.070/100' : '&nbsp;');
            var totalCommission = safe(data.totalCommission) || '&nbsp;';
            var html =
                "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:100%;border:1px solid #000;margin:0;'>" +
                "<thead><tr>" +
                "<th colspan='4' style='font-weight:bold;text-align:center;" + cellStyle + "'>" +
                "Agency Commission claim on Relief Bond/Taxable Bond/Savings Bond/Redemption payment and " +
                "Half yearly Interest for the quarter ended " + quarterEnding + ".</th>" +
                "</tr><tr style='background:#d9d9d9;'>" +
                "<th style='font-weight:bold;width:8%;text-align:center;" + cellStyle + "'>&nbsp;</th>" +
                "<th style='font-weight:bold;width:47%;text-align:left;" + cellStyle + "'>Particulars</th>" +
                "<th style='font-weight:bold;width:20%;text-align:center;" + cellStyle + "'>No of Trans.</th>" +
                "<th style='font-weight:bold;width:25%;text-align:right;" + cellStyle + "'>Amount in Rs.</th>" +
                "</tr></thead><tbody>";

            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                var isBold = row.bold || row.particulars === 'Total (1 to 10b)' || row.particulars === 'Total Interest';
                var boldStyle = isBold ? 'font-weight:bold;' : '';
                var noOfTrans = row.noOfTrans !== undefined && row.noOfTrans !== '' ?
                    safe(row.noOfTrans) : '&nbsp;';
                var amount = formatAnnexureAAmount(row.amount);

                if (row.particulars === 'Total (1 to 10b)' && data.totalNoOfTrans) {
                    noOfTrans = safe(data.totalNoOfTrans);
                }
                if (row.particulars === 'Total (1 to 10b)' && data.totalAmount) {
                    amount = safe(data.totalAmount);
                }

                html +=
                    "<tr>" +
                    "<td style='text-align:center;" + boldStyle + cellStyle + "'>" + safe(row.index) + "</td>" +
                    "<td style='text-align:left;" + boldStyle + cellStyle + "'>" + safe(row.particulars) + "</td>" +
                    "<td style='text-align:center;" + boldStyle + cellStyle + "'>" + noOfTrans + "</td>" +
                    "<td style='text-align:right;" + boldStyle + cellStyle + "'>" + amount + "</td>" +
                    "</tr>";
            }

            html +=
                "<tr>" +
                "<td colspan='2' style='text-align:left;font-weight:bold;" + cellStyle + "'>" +
                "TOC AMT @ Rs. " + safe(data.tocRate || '0.070') + " per Rs 100/. ( Inclusive of GST - 18% )</td>" +
                "<td style='text-align:center;" + cellStyle + "'>" + calcDisplay + "</td>" +
                "<td style='text-align:right;font-weight:bold;" + cellStyle + "'>" + totalCommission + "</td>" +
                "</tr>" +
                "</tbody></table>";

            return html;
        }

        function buildAnnexureAHtml() {
            var data = vm.annexureA || initAnnexureAData();
            var quarterEnding = getQuarterEndingSubjectLabel();
            var amountWords = getAmountWordsForDisplay(
                data.totalCommission,
                data.amountWords || getTocClaimAmountWordsDisplay()
            );
            var amountWordsText = amountWords ?
                '( Rupees ' + safe(amountWords) + ' Only )' :
                '( Rupees _______________ Only )';
            var html = '';

            html +=
                "<div style='text-align:center;font-weight:bold;font-size:14pt;margin-bottom:12px;'>ANNEXURE A</div>";

            html += buildAnnexureATableHtml(data, quarterEnding);

            html +=
                "<p style='font-size:12pt;margin:16px 0 0 0;text-align:left;'>" +
                "Amount in words: <b>" + amountWordsText + "</b></p>";

            return html;
        }

        function buildAnnexureAExportTable() {
            return buildAnnexureAHtml();
        }

        function getQuarterPeriodRangeLabel(useUpperTo) {
            var months = getQuarterMonths();
            if (!months.length) {
                return 'Jan 26 to Mar 26';
            }
            var fromMonth = months[0].monthName;
            var toMonth = months[months.length - 1].monthName;
            var fromYy = String(months[0].year).substring(2);
            var toYy = String(months[months.length - 1].year).substring(2);
            var sep = useUpperTo ? ' TO ' : ' to ';
            return fromMonth + ' ' + fromYy + sep + toMonth + ' ' + toYy;
        }

        function getAnnexureBPeriodLabel() {
            return getQuarterPeriodRangeLabel(false);
        }

        function getDefaultAnnexureBRows(periodLabel) {
            var dash = '-';
            var emDash = ' \u2013 ';
            return [
                {
                    paymentPeriod: '9% 8.5%, 8% Relief Bonds- ' + periodLabel,
                    interestAmount: dash, totalNoOfTrans: dash,
                    claimLodgmentDate: dash, claimReceivedDate: dash, bold: false
                },
                {
                    paymentPeriod: '6.5% Saving Bonds- ' + periodLabel,
                    interestAmount: dash, totalNoOfTrans: dash,
                    claimLodgmentDate: dash, claimReceivedDate: dash, bold: false
                },
                {
                    paymentPeriod: '6.5% Saving Bonds- ' + periodLabel,
                    interestAmount: dash, totalNoOfTrans: dash,
                    claimLodgmentDate: dash, claimReceivedDate: dash, bold: false
                },
                {
                    paymentPeriod: '7% Saving Bonds- ' + periodLabel,
                    interestAmount: dash, totalNoOfTrans: dash,
                    claimLodgmentDate: dash, claimReceivedDate: dash, bold: false
                },
                {
                    paymentPeriod: 'TOTAL (A) (Half yearly interest for other schemes)',
                    interestAmount: dash, totalNoOfTrans: dash,
                    claimLodgmentDate: dash, claimReceivedDate: dash, bold: true, rowKey: 'totalA'
                },
                {
                    paymentPeriod: 'GOI 7.75% Savings (taxable) Bond' + emDash + periodLabel,
                    interestAmount: '', totalNoOfTrans: '',
                    claimLodgmentDate: '', claimReceivedDate: '', bold: false, rowKey: 'goi775'
                },
                {
                    paymentPeriod: 'GOI 8.05% Floating Rate Savings Bond' + emDash + periodLabel,
                    interestAmount: '', totalNoOfTrans: '',
                    claimLodgmentDate: '', claimReceivedDate: '', bold: false, rowKey: 'goi805'
                },
                {
                    paymentPeriod: 'GOI Half Yearly Interest rejects reinitiated' + emDash + periodLabel,
                    interestAmount: '', totalNoOfTrans: '',
                    claimLodgmentDate: dash, claimReceivedDate: dash, bold: false, rowKey: 'rejects'
                },
                {
                    paymentPeriod: 'TOTAL (B) (Half yearly interest Claims for all the Schemes)',
                    interestAmount: '', totalNoOfTrans: '',
                    claimLodgmentDate: dash, claimReceivedDate: dash, bold: true, rowKey: 'totalB'
                },
                {
                    paymentPeriod: 'Interest claim ' + periodLabel,
                    interestAmount: '', totalNoOfTrans: '',
                    claimLodgmentDate: periodLabel, claimReceivedDate: periodLabel,
                    bold: false, rowKey: 'redemption'
                },
                {
                    paymentPeriod: 'TOTAL (C) (Overall Redemption Interest Amt All schemes)',
                    interestAmount: '', totalNoOfTrans: '',
                    claimLodgmentDate: periodLabel, claimReceivedDate: periodLabel,
                    bold: true, rowKey: 'totalC'
                },
                {
                    paymentPeriod: 'TOTAL (A+B+C) (Interest \u2013 Half yearly and Redemption)',
                    interestAmount: '', totalNoOfTrans: '',
                    claimLodgmentDate: periodLabel, claimReceivedDate: periodLabel,
                    bold: true, rowKey: 'grandTotal'
                }
            ];
        }

        function initAnnexureBData() {
            vm.annexureB = {
                periodLabel: getAnnexureBPeriodLabel(),
                rowsList: null,
                totalAInterest: '-',
                totalATrans: '-',
                goi775Interest: '',
                goi775Trans: '',
                goi805Interest: '',
                goi805Trans: '',
                rejectsInterest: '',
                rejectsTrans: '',
                totalBInterest: '',
                totalBTrans: '',
                totalCInterest: '',
                totalCTrans: '',
                grandTotalInterest: '',
                grandTotalTrans: '',
                redemptionInterest: '',
                redemptionTrans: ''
            };
            syncAnnexureBFromExternalAudit();
            return vm.annexureB;
        }

        function syncAnnexureBFromExternalAudit() {
            if (!vm.annexureB) {
                return;
            }
        }

        function loadAnnexureBData(result) {
            if (!result || Array.isArray(result)) {
                syncAnnexureBFromQuarterReport();
                syncAnnexureBFromExternalAudit();
                return;
            }

            var data = result.annexureB || result.section9 || {};
            if (!vm.annexureB) {
                initAnnexureBData();
            }

            angular.extend(vm.annexureB, {
                periodLabel: data.periodLabel || vm.annexureB.periodLabel,
                totalAInterest: data.totalAInterest || vm.annexureB.totalAInterest,
                totalATrans: data.totalATrans || vm.annexureB.totalATrans,
                goi775Interest: data.goi775Interest || data.goi775Amount || vm.annexureB.goi775Interest,
                goi775Trans: data.goi775Trans || data.goi775Transactions || vm.annexureB.goi775Trans,
                goi805Interest: data.goi805Interest || data.goi805Amount || vm.annexureB.goi805Interest,
                goi805Trans: data.goi805Trans || data.goi805Transactions || vm.annexureB.goi805Trans,
                rejectsInterest: data.rejectsInterest || data.rejectsAmount || vm.annexureB.rejectsInterest,
                rejectsTrans: data.rejectsTrans || data.rejectsTransactions || vm.annexureB.rejectsTrans,
                totalBInterest: data.totalBInterest || vm.annexureB.totalBInterest,
                totalBTrans: data.totalBTrans || vm.annexureB.totalBTrans,
                totalCInterest: data.totalCInterest || data.redemptionInterest || vm.annexureB.totalCInterest,
                totalCTrans: data.totalCTrans || data.redemptionTrans || vm.annexureB.totalCTrans,
                grandTotalInterest: data.grandTotalInterest || vm.annexureB.grandTotalInterest,
                grandTotalTrans: data.grandTotalTrans || vm.annexureB.grandTotalTrans,
                redemptionInterest: data.redemptionInterest || vm.annexureB.redemptionInterest,
                redemptionTrans: data.redemptionTrans || vm.annexureB.redemptionTrans
            });

            if (data.rowsList && data.rowsList.length) {
                vm.annexureB.rowsList = data.rowsList;
            }

            syncAnnexureBFromQuarterReport();
            syncAnnexureBFromExternalAudit();
        }

        function formatAnnexureBCell(value) {
            if (value === null || value === undefined || value === '') {
                return '&nbsp;';
            }
            return safe(value);
        }

        function resolveAnnexureBRowValues(row, data) {
            var interestAmount = row.interestAmount;
            var totalNoOfTrans = row.totalNoOfTrans;
            var claimLodgmentDate = row.claimLodgmentDate;
            var claimReceivedDate = row.claimReceivedDate;

            if (row.rowKey === 'totalA') {
                interestAmount = data.totalAInterest || interestAmount;
                totalNoOfTrans = data.totalATrans || totalNoOfTrans;
            } else if (row.rowKey === 'goi775') {
                interestAmount = data.goi775Interest || interestAmount;
                totalNoOfTrans = data.goi775Trans || totalNoOfTrans;
            } else if (row.rowKey === 'goi805') {
                interestAmount = data.goi805Interest || interestAmount;
                totalNoOfTrans = data.goi805Trans || totalNoOfTrans;
            } else if (row.rowKey === 'rejects') {
                interestAmount = data.rejectsInterest || interestAmount;
                totalNoOfTrans = data.rejectsTrans || totalNoOfTrans;
            } else if (row.rowKey === 'totalB') {
                interestAmount = data.totalBInterest || interestAmount;
                totalNoOfTrans = data.totalBTrans || totalNoOfTrans;
            } else if (row.rowKey === 'redemption') {
                interestAmount = data.redemptionInterest || interestAmount;
                totalNoOfTrans = data.redemptionTrans || totalNoOfTrans;
            } else if (row.rowKey === 'totalC') {
                interestAmount = data.totalCInterest || data.redemptionInterest || interestAmount;
                totalNoOfTrans = data.totalCTrans || data.redemptionTrans || totalNoOfTrans;
            } else if (row.rowKey === 'grandTotal') {
                interestAmount = data.grandTotalInterest || interestAmount;
                totalNoOfTrans = data.grandTotalTrans || totalNoOfTrans;
            }

            if (row.rowKey === 'redemption' || row.rowKey === 'totalC' || row.rowKey === 'grandTotal') {
                if (!claimLodgmentDate) {
                    claimLodgmentDate = data.periodLabel;
                }
                if (!claimReceivedDate) {
                    claimReceivedDate = data.periodLabel;
                }
            }

            return {
                interestAmount: formatAnnexureBCell(interestAmount),
                totalNoOfTrans: formatAnnexureBCell(totalNoOfTrans),
                claimLodgmentDate: formatAnnexureBCell(claimLodgmentDate),
                claimReceivedDate: formatAnnexureBCell(claimReceivedDate)
            };
        }

        function buildAnnexureBTableHtml(data) {
            var periodLabel = getAnnexureBPeriodLabel();
            data.periodLabel = periodLabel;
            var rows = data.rowsList && data.rowsList.length ?
                data.rowsList : getDefaultAnnexureBRows(periodLabel);
            var cellStyle = 'padding:6px;border:1px solid #000;';
            var html =
                "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:100%;border:1px solid #000;margin:0;'>" +
                "<thead><tr style='background:#d9d9d9;'>" +
                "<th style='font-weight:bold;width:35%;text-align:left;" + cellStyle + "'>Payment Period</th>" +
                "<th style='font-weight:bold;width:20%;text-align:right;" + cellStyle + "'>Interest Amount</th>" +
                "<th style='font-weight:bold;width:15%;text-align:center;" + cellStyle + "'>Total No Of Transaction</th>" +
                "<th style='font-weight:bold;width:15%;text-align:center;" + cellStyle + "'>Claim Lodgment Date</th>" +
                "<th style='font-weight:bold;width:15%;text-align:center;" + cellStyle + "'>Claim Received Date</th>" +
                "</tr></thead><tbody>";

            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                var values = resolveAnnexureBRowValues(row, data);
                var boldStyle = row.bold ? 'font-weight:bold;' : '';

                html +=
                    "<tr>" +
                    "<td style='text-align:left;" + boldStyle + cellStyle + "'>" + safe(row.paymentPeriod) + "</td>" +
                    "<td style='text-align:right;" + boldStyle + cellStyle + "'>" + values.interestAmount + "</td>" +
                    "<td style='text-align:center;" + boldStyle + cellStyle + "'>" + values.totalNoOfTrans + "</td>" +
                    "<td style='text-align:center;" + boldStyle + cellStyle + "'>" + values.claimLodgmentDate + "</td>" +
                    "<td style='text-align:center;" + boldStyle + cellStyle + "'>" + values.claimReceivedDate + "</td>" +
                    "</tr>";
            }

            html += "</tbody></table>";
            return html;
        }

        function buildAnnexureBHtml() {
            var data = vm.annexureB || initAnnexureBData();
            var html = '';

            html +=
                "<div style='text-align:center;font-weight:bold;font-size:14pt;margin-bottom:12px;'>ANNEXURE B</div>";

            html += buildAnnexureBTableHtml(data);

            return html;
        }

        function buildAnnexureBExportTable() {
            return buildAnnexureBHtml();
        }

        function getQuarterSerialNumberStart() {
            var serialMap = {
                'Q1': 1,
                'Q2': 4,
                'Q3': 7,
                'Q4': 10
            };
            return serialMap[vm.quarter] || 1;
        }

        function buildAnnex2aMonthRows() {
            if (vm.annex2aList && vm.annex2aList.length) {
                return vm.annex2aList;
            }

            var months = getQuarterMonths();
            var startSerial = getQuarterSerialNumberStart();
            var rows = [];

            for (var i = 0; i < months.length; i++) {
                var fullMonth = FULL_MONTH_NAMES[months[i].monthName] || months[i].monthName;
                rows.push({
                    serialNo: startSerial + i,
                    month: fullMonth + ' \u2013 ' + months[i].year,
                    noOfPensioners: 'NOT APPLICABLE'
                });
            }

            return rows;
        }

        function getTocClaimAmountDisplay() {
            if (vm.annex2aAmount) {
                return vm.annex2aAmount;
            }

            if (vm.model && vm.model.length) {
                for (var i = vm.model.length - 1; i >= 0; i--) {
                    if (vm.model[i].amount) {
                        return vm.model[i].amount;
                    }
                }
            }

            return '';
        }

        function getTocClaimAmountWordsDisplay() {
            return getAmountWordsForDisplay(
                getTocClaimAmountDisplay(),
                vm.annex2aAmountWords
            );
        }

        function buildAnnex2aAmountDisplayHtml() {
            var amount = getTocClaimAmountDisplay();
            var amountWords = getTocClaimAmountWordsDisplay();
            var amountText = amount ? safe(amount) : '_______________';
            var amountWordsText;

            if (amountWords) {
                amountWordsText = '( ' + safe(amountWords) + ' )';
            } else {
                amountWordsText = '( Rupees _______________ Only )';
            }

            return '<b>Rs. ' + amountText + ' ' + amountWordsText + '</b>';
        }

        function buildAnnex2aCertificateHtml() {
            var monthRows = buildAnnex2aMonthRows();
            var signatoryText = vm.annex2aSignatory ? safe(vm.annex2aSignatory) : 'Nitin Nimawat \u2013 Assistant Vice President Nagpur';
            var html = '';

            html +=
                "<div style='text-align:center;font-weight:bold;font-size:14pt;margin:16px 0 28px 0;'>" +
                "ANNEX 2A<br/>CERTIFICATE BY THE AGENCY BANK</div>" +
                "<p style='font-size:12pt;line-height:1.7;margin:0 0 20px 0;text-align:left;'>" +
                "1. Certified that the amount of " + buildAnnex2aAmountDisplayHtml() +
                " claimed as agency commission has been arrived at correctly taking into account the number of transactions in respect of 'receipts' and 'pension payments' and the amount of transaction in respect of 'payments other than pension' as recorded in the daily scrolls of Government transactions furnished to the accounting authorities of the Central/State Governments/RBI and other records available in the bank and that only eligible items as specified in RBI Master Circular on &quot;Conduct of Government Business by Agency Banks - Payment of Agency Commission&quot; have been considered while arriving at the said amount. It is further certified that agency commission claims for transactions included in the current claim has been made only once.</p>" +
                "<p style='font-size:12pt;line-height:1.7;margin:0 0 20px 0;text-align:left;'>" +
                "2. We also certify that the collections of Government receipts (both tax and non-tax) on behalf of Centre/State Governments have been duly scrolled and funds remitted to RBI and no transaction is pending for scroll with the bank pertaining to the period for which agency commission is being claimed \u2013 <b>NOT APPLICABLE.</b></p>" +
                "<p style='font-size:12pt;line-height:1.7;margin:0 0 24px 0;text-align:left;'>" +
                "3. Further, we certify that the month-wise breakup of eligible pensioner's account available with us is as per the table below, and the number of receipt transactions for which agency commission is claimed is exclusive of the transactions pertaining to own tax liabilities of the bank and the taxes deducted at source under various items of the Income Tax Act -- <b>NOT APPLICABLE.</b></p>";

            html +=
                "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:60%;text-align:center;border:1px solid #000;margin:8px 0 32px 0;'>";

            html +=
                "<thead><tr>" +
                "<th style='font-weight:bold;width:15%;padding:8px 10px;'>Sr. No.</th>" +
                "<th style='font-weight:bold;width:45%;padding:8px 10px;'>Month</th>" +
                "<th style='font-weight:bold;width:40%;padding:8px 10px;'>No. of Pensioners</th>" +
                "</tr></thead><tbody>";

            for (var i = 0; i < monthRows.length; i++) {
                var row = monthRows[i];
                html +=
                    "<tr>" +
                    "<td style='padding:8px 10px;'>" + safe(row.serialNo || row.srNo || row.sNo) + "</td>" +
                    "<td style='padding:8px 10px;'>" + safe(row.month || row.monthLabel) + "</td>" +
                    "<td style='padding:8px 10px;'>" + safe(row.noOfPensioners || row.pensioners || 'NOT APPLICABLE') + "</td>" +
                    "</tr>";
            }

            html += "</tbody></table>";

            html +=
                "<p style='font-size:12pt;line-height:1.7;margin:32px 0 24px 0;text-align:left;'>Thanking You,</p>" +
                "<p style='font-size:12pt;line-height:1.7;margin:0 0 4px 0;text-align:left;'><b>FOR HDFC BANK LTD</b></p>" +
                "<p style='font-size:12pt;line-height:1.7;margin:0 0 36px 0;text-align:left;'><b>Authorized Signatory</b></p>" +
                "<p style='font-size:12pt;line-height:1.7;margin:0;text-align:left;'><b>(" + signatoryText + ")</b></p>";

            return html;
        }

        function buildAnnex2aExportTable() {
            return buildAnnex2aCertificateHtml();
        }

        function initReportSections() {
            vm.reportSections = [
                { key: 'quarter', index: 0, title: 'Quarter Report' },
                { key: 'taxRedemption', index: 1, title: vm.taxRedemptionTitle || 'Tax Redemption' },
                { key: 'annex2a', index: 2, title: 'ANNEX 2A - Certificate By The Agency Bank' },
                { key: 'externalAudit', index: 3, title: 'External Audit Certificate' },
                { key: 'taxInvoice', index: 4, title: 'Formats for claiming Turn Over Commission (TOC) on Bond from RBI' },
                { key: 'bankInternal', index: 5, title: 'Bank Internal Format' },
                { key: 'annexureA', index: 6, title: 'ANNEXURE A' },
                { key: 'annexureB', index: 7, title: 'ANNEXURE B' }
            ];
        }

        function refreshReportSectionTitles() {
            if (!vm.reportSections || !vm.reportSections.length) {
                initReportSections();
                return;
            }
            vm.reportSections[1].title = vm.taxRedemptionTitle || 'Tax Redemption';
        }

        function setActiveSection(index) {
            index = parseInt(index, 10);
            if (isNaN(index) || index < 0 || index >= vm.reportSections.length) {
                return;
            }
            vm.activeSectionIndex = index;
            if ($scope && $scope.$evalAsync) {
                $scope.$evalAsync(function() {
                    var anchor = document.getElementById('appendix-section-content');
                    if (anchor) {
                        anchor.scrollIntoView({ behavior: 'smooth', block: 'start' });
                    }
                });
            }
        }

        function buildSectionTitles() {
            refreshReportSectionTitles();
        }

        function buildTableTitleBanner(title) {
            return "<table width='100%' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;border:none;mso-table-lspace:0pt;mso-table-rspace:0pt;'>" +
                "<tr><td align='left' " +
                "style='border:none;font-weight:bold;font-size:14pt;" +
                "text-align:left;padding:8px 10px;mso-shading:windowtext;mso-pattern:gray-25 auto;'>" +
                "<b>" + safe(title) + "</b></td></tr></table>";
        }

        function buildSectionCenterTitleHtml(title) {
            return "<div style='text-align:center;font-weight:bold;font-size:14pt;margin:0 0 16px 0;'>" +
                safe(title) + "</div>";
        }

        function buildTaxRedemptionExportTable() {
            return buildTaxRedemptionSectionHtml();
        }

        function buildTaxRedemptionTableHtml(dataList) {
            var table =
                "<table border='1' style='border-collapse:collapse;width:100%;text-align:center;'>";

            table +=
                "<thead><tr>" +
                "<th>Claim Received date</th>" +
                "<th>Investment Amount</th>" +
                "<th>Count</th>" +
                "<th>Interest Amount</th>" +
                "<th>Count</th>" +
                "<th>Total amount</th>" +
                "<th>Total Records.</th>" +
                "</tr></thead><tbody>";

            table += buildClaimTableBodyHtml(dataList || [], 'TOTAL', true);

            table += "</tbody></table>";
            return table;
        }

        function buildTaxRedemptionSchemeBlockHtml(scheme) {
            if (!scheme) {
                return '';
            }

            return "<div class='appendix-tax-redemption-scheme-block'>" +
                buildTableTitleBanner(scheme.title) +
                buildTaxRedemptionTableHtml(scheme.dataList) +
                '</div>';
        }

        function buildTaxRedemptionSectionHtml() {
            var html = buildSectionCenterTitleHtml(vm.taxRedemptionTitle || 'Tax Redemption');
            var schemes = vm.taxRedemptionSchemes || getDefaultTaxRedemptionSchemes();

            for (var i = 0; i < schemes.length; i++) {
                if (i > 0) {
                    html += buildTaxRedemptionSpacer();
                }
                html += buildTaxRedemptionSchemeBlockHtml(schemes[i]);
            }

            return html;
        }

        function buildTestBondExportTable() {
            return buildTestBondTableHtml();
        }

        function buildTestBondTableHtml() {
            var table = buildTableTitleBanner(vm.testBondTitle);

            table +=
                "<table class='data-table' border='1' cellspacing='0' cellpadding='0' " +
                "style='border-collapse:collapse;width:100%;text-align:center;border:1px solid #000;" +
                "mso-table-lspace:0pt;mso-table-rspace:0pt;'>";

            table +=
                "<thead><tr>" +
                "<th style='font-weight:bold;'>Claim recd dt</th>" +
                "<th style='font-weight:bold;'>Principal amt</th>" +
                "<th style='font-weight:bold;'>No of Records</th>" +
                "<th style='font-weight:bold;'>Interest Amt</th>" +
                "<th style='font-weight:bold;'>No of records</th>" +
                "<th style='font-weight:bold;'>Total amount</th>" +
                "<th style='font-weight:bold;'>Total Records</th>" +
                "</tr></thead><tbody>";

            table += buildClaimTableBodyHtml(vm.testBondList, 'Total', false);

            table += "</tbody></table>";
            return table;
        }

        function buildSectionExportContent(title, tableHtml, useHeading) {
            if (useHeading) {
                return "<h2 style='text-align:left;font-weight:bold;margin-bottom:12px;font-size:20px;'>" +
                    safe(title) + "</h2>" + tableHtml;
            }
            return tableHtml;
        }

        function buildEmptyRow(columnCount) {
            var row = "<tr>";
            for (var i = 0; i < columnCount; i++) {
                row += "<td>&nbsp;</td>";
            }
            row += "</tr>";
            return row;
        }

        function buildEmptyCells(columnCount) {
            var cells = "";
            for (var i = 0; i < columnCount; i++) {
                cells += "<td>&nbsp;</td>";
            }
            return cells;
        }

        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Appendix VIII - Statement of Charges for Turnover Commission ',
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }


 function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            rbibondTurnoverCommissionService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
          function alert(item){

                     for (let i = 0;i<vm.schemeList.length;i++){
                      if(vm.schemeList[i].subCode == item){
                            vm.schemeName = vm.schemeList[i].subCodeDescription;
                           //vm.cusName = vm.custList[i].cusName;
                      //  vm.cusAlphaCode = vm.custList[i].cusAlphaCode;
                       // vm.id = vm.custList[i].id;schemeList
                       }
                     }
                  }



         function getPrincipalRepayment(){
                vm.loader = true;
                          var requestData = {

                                                    'financialYear':vm.financialYear,
                                                    'quarter':vm.quarter,
                                                    'flag':'D',
                          }

              rbibondTurnoverCommissionService.getDetails(requestData).then(function(response) {
                  vm.loader = false;

                   var result = response.data.result;
                   if (Array.isArray(result)) {
                       vm.model = result;
                       loadSectionTableData(null);
                   } else {
                       vm.model = result.quarterReportList || [];
                       loadSectionTableData(result);
                   }
                   normalizeSerialNumbers(vm.model);
                   vm.section1NoData = !vm.model || !vm.model.length;
                   buildSectionTitles();
                   refreshSectionPreviewHtml();
//                   vm.mod1 =response.data.result.grandTotal1;
//                    vm.mod2 =response.data.result.grandTotal2;
//                    vm.mod3 =response.data.result.grandTotal3;
//                    vm.mod4 =response.data.result.total;



                 angular.forEach(vm.mod, function(item){
                                   item.grandTotal = Number(item.grandTotal).toLocaleString("en-IN");
            })
              vm.mod = Number(vm.mod).toLocaleString("en-IN");

                  configDataTable();
             }, function(error){
             vm.loader = false;
             if (error.data.message == 'No Data Found'){
                                   vm.section1NoData = true;
                                   vm.model = [];
                                 } else {
                            handleErrorResponse(error);
                      $state.go('rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission.inquiry')
                           }
                    }


           )
         }

                        function dateValidation() {
                                                           vm.toDate = '';

                                                           // Convert to Date object (if fromDate is string)
                                                                                                                              var from = new Date(vm.fromDate);

                                                                                                                              // Last day of the same month as fromDate
                                                                                                                              var lastDayOfMonth = new Date(
                                                                                                                                  from.getFullYear(),
                                                                                                                                  from.getMonth() + 1,
                                                                                                                                  0
                                                                                                                              );

                                                           vm.dateToOptions = {
                                                               minDate: vm.from,
                                                                maxDate: lastDayOfMonth
                                                           };
                                                           vm.dateOptions = {
                                                            maxDate:convertToDateObject(vm.currentDate)
                                                           }


                                                       }


                                                       function safe(val) {
                                                           return (val === null || val === undefined || val === "") ? "" : val;
                                                       }

                                                       function formatSerialNo(val) {
                                                           return safe(val).toString().toLowerCase();
                                                       }

                                                       function normalizeSerialNumbers(rows) {
                                                           if (!rows || !rows.length) {
                                                               return;
                                                           }
                                                           angular.forEach(rows, function(item) {
                                                               if (item.sNo) {
                                                                   item.sNo = formatSerialNo(item.sNo);
                                                               }
                                                           });
                                                       }


                       function downloadHtmlFile(html, filename, mimeType) {
                           var blob = new Blob([html], { type: mimeType });
                           var url = URL.createObjectURL(blob);
                           var link = document.createElement('a');
                           link.href = url;
                           link.download = filename;
                           document.body.appendChild(link);
                           link.click();
                           document.body.removeChild(link);
                       }

                       function buildWordHtml(bodyContent, title) {
                           return "<html xmlns:o='urn:schemas-microsoft-com:office:office' " +
                               "xmlns:w='urn:schemas-microsoft-com:office:word' " +
                               "xmlns='http://www.w3.org/TR/REC-html40'>" +
                               "<head><meta charset='utf-8'>" +
                               "<title>" + safe(title) + "</title>" +
                               "<!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View></w:WordDocument></xml><![endif]-->" +
                               "<style>" +
                               "body{font-family:Calibri,Arial,sans-serif;}" +
                               "table.data-table{border-collapse:collapse;width:100%;mso-table-lspace:0pt;mso-table-rspace:0pt;}" +
                               "table.data-table td,table.data-table th{border:1px solid #000;padding:5px 6px;text-align:center;}" +
                               "h2{text-align:left;font-weight:bold;font-size:20pt;margin:0 0 8px 0;}" +
                               "</style></head>" +
                               "<body>" + bodyContent + "</body></html>";
                       }

                       function buildExcelHtml(bodyContent) {
                           return "<html xmlns:x='urn:schemas-microsoft-com:office:excel'>" +
                               "<head><meta charset='UTF-8'>" +
                               "<style>" +
                               "table.data-table{border-collapse:collapse;width:100%;}" +
                               "table.data-table td,table.data-table th{border:1px solid #000;padding:5px 6px;text-align:center;}" +
                               "h2{text-align:left;font-weight:bold;font-size:16pt;}" +
                               "</style></head>" +
                               "<body>" + bodyContent + "</body></html>";
                       }

                       function buildQuarterReportTableForWord() {
                           var table =
                               "<table border='1' style='border-collapse:collapse;width:100%;table-layout:fixed;text-align:center;'>";

                           table +=
                               "<thead><tr>" +
                               "<th style='width:5%;'>S.No</th>" +
                               "<th style='width:65%;'>TOC Claim for the quarter ended March " + vm.toYear + "</th>" +
                               "<th style='width:15%;'>No Of Transactions</th>" +
                               "<th style='width:15%;'>Amount</th>" +
                               "</tr></thead><tbody>";

                           for (var i = 0; i < vm.model.length; i++) {
                               var isBold = (i === 3) || (i >= (vm.model.length - 4));
                               table += "<tr>";
                               table += "<td>" + formatSerialNo(vm.model[i].sNo) + "</td>";

                               if (i === vm.model.length - 4) {
                                   table +=
                                       "<td rowspan='2' style='" +
                                       (isBold ? "font-weight:bold;" : "") +
                                       "vertical-align:middle;'>" +
                                       safe(vm.model[i].totalClaim) + "</td>";
                               } else if (i !== vm.model.length - 3) {
                                   table +=
                                       "<td style='" + (isBold ? "font-weight:bold;" : "") + "'>" +
                                       safe(vm.model[i].totalClaim) + "</td>";
                               }

                               table +=
                                   "<td style='" + (isBold ? "font-weight:bold;" : "") + "'>" +
                                   safe(vm.model[i].noOfTransections) + "</td>";
                               table +=
                                   "<td style='" + (isBold ? "font-weight:bold;" : "") + "'>" +
                                   safe(vm.model[i].amount) + "</td>";
                               table += "</tr>";
                           }

                           table += "</tbody></table>";
                           return table;
                       }

                       function buildQuarterReportTableForExcel() {
                           var table =
                               "<table border='1' style='border-collapse:collapse;width:100%;text-align:center;'>";

                           table +=
                               "<thead><tr>" +
                               "<th>S.No</th>" +
                               "<th>TOC Claim for the quarter ended March " + vm.toYear + "</th>" +
                               "<th>No Of Transactions</th>" +
                               "<th>Amount</th>" +
                               "</tr></thead><tbody>";

                           for (var i = 0; i < vm.model.length; i++) {
                               table += "<tr>";
                               table += "<td>" + formatSerialNo(vm.model[i].sNo) + "</td>";
                               table += "<td>" + safe(vm.model[i].totalClaim) + "</td>";
                               table += "<td>" + safe(vm.model[i].noOfTransections) + "</td>";
                               table += "<td>" + safe(vm.model[i].amount) + "</td>";
                               table += "</tr>";
                           }

                           table += "</tbody></table>";
                           return table;
                       }

                       function exportWord() {
                           var body =
                               buildSectionCenterTitleHtml('Quarter Report') +
                               "<h3 style='text-align:center;'>Quarter - " + vm.quarter + "</h3>" +
                               buildQuarterReportTableForWord();
                           downloadHtmlFile(
                               buildWordHtml(body, 'TOC Quarter Report'),
                               'TOC_Quarter_Report.doc',
                               'application/msword'
                           );
                       }

                       function exportExcel() {
                           var body =
                               "<h3 style='text-align:center;'>Quarter - " + vm.quarter + "</h3>" +
                               buildQuarterReportTableForExcel();
                           downloadHtmlFile(
                               buildExcelHtml(body),
                               'TOC_Quarter_Report.xls',
                               'application/vnd.ms-excel'
                           );
                       }

                       function exportWordSection2() {
                           var body = buildTaxRedemptionExportTable();
                           downloadHtmlFile(
                               buildWordHtml(body, vm.taxRedemptionTitle),
                               'Tax_Redemption.doc',
                               'application/msword'
                           );
                       }

                       function exportExcelSection2() {
                           var body = buildTaxRedemptionExportTable();
                           downloadHtmlFile(
                               buildExcelHtml(body),
                               'Tax_Redemption.xls',
                               'application/vnd.ms-excel'
                           );
                       }

                       function exportWordSection3() {
                           var body = buildTestBondExportTable();
                           downloadHtmlFile(
                               buildWordHtml(body, vm.testBondTitle),
                               'Test_Bond.doc',
                               'application/msword'
                           );
                       }

                       function exportExcelSection3() {
                           var body = buildTestBondExportTable();
                           downloadHtmlFile(
                               buildExcelHtml(body),
                               'Test_Bond.xls',
                               'application/vnd.ms-excel'
                           );
                       }

                       function exportWordSection4() {
                           var body = buildAnnex2aExportTable();
                           downloadHtmlFile(
                               buildWordHtml(body, 'ANNEX 2A Certificate'),
                               'ANNEX_2A_Certificate.doc',
                               'application/msword'
                           );
                       }

                       function exportExcelSection4() {
                           var body = buildAnnex2aExportTable();
                           downloadHtmlFile(
                               buildExcelHtml(body),
                               'ANNEX_2A_Certificate.xls',
                               'application/vnd.ms-excel'
                           );
                       }

                       function exportWordSection5() {
                           var body = buildExternalAuditExportTable();
                           downloadHtmlFile(
                               buildWordHtml(body, 'External Audit Certificate'),
                               'External_Audit_Certificate.doc',
                               'application/msword'
                           );
                       }

                       function exportExcelSection5() {
                           var body = buildExternalAuditExportTable();
                           downloadHtmlFile(
                               buildExcelHtml(body),
                               'External_Audit_Certificate.xls',
                               'application/vnd.ms-excel'
                           );
                       }

                       function exportWordSection6() {
                           var body = buildTaxInvoiceExportTable();
                           downloadHtmlFile(
                               buildWordHtml(body, 'TOC Tax Invoice'),
                               'TOC_Tax_Invoice.doc',
                               'application/msword'
                           );
                       }

                       function exportWordSection7() {
                           var body = buildBankInternalFormatExportTable();
                           downloadHtmlFile(
                               buildWordHtml(body, 'Bank Internal Format'),
                               'Bank_Internal_Format.doc',
                               'application/msword'
                           );
                       }

                       function exportWordSection8() {
                           var body = buildAnnexureAExportTable();
                           downloadHtmlFile(
                               buildWordHtml(body, 'ANNEXURE A'),
                               'ANNEXURE_A.doc',
                               'application/msword'
                           );
                       }

                       function exportWordSection9() {
                           var body = buildAnnexureBExportTable();
                           downloadHtmlFile(
                               buildWordHtml(body, 'ANNEXURE B'),
                               'ANNEXURE_B.doc',
                               'application/msword'
                           );
                       }

                       function exportWordActiveSection() {
                           switch (parseInt(vm.activeSectionIndex, 10)) {
                               case 0:
                                   exportWord();
                                   break;
                               case 1:
                                   exportWordSection2();
                                   break;
                               case 2:
                                   exportWordSection4();
                                   break;
                               case 3:
                                   exportWordSection5();
                                   break;
                               case 4:
                                   exportWordSection6();
                                   break;
                               case 5:
                                   exportWordSection7();
                                   break;
                               case 6:
                                   exportWordSection8();
                                   break;
                               case 7:
                                   exportWordSection9();
                                   break;
                           }
                       }

                       function exportExcelActiveSection() {
                           switch (parseInt(vm.activeSectionIndex, 10)) {
                               case 0:
                                   exportExcel();
                                   break;
                               case 1:
                                   exportExcelSection2();
                                   break;
                           }
                       }

                       function getPrintDocumentStyles() {
                           return 'body{font-family:Arial,Helvetica,sans-serif;font-size:12pt;color:#000;margin:20px;}' +
                               'table{border-collapse:collapse;width:100%;margin-bottom:12px;}' +
                               'td,th{border:1px solid #000;padding:6px 8px;vertical-align:middle;}' +
                               'p{margin:0 0 10px 0;line-height:1.5;text-align:left;}' +
                               'h2,h3,h4{margin:12px 0;}';
                       }

                       function printSectionHtml(htmlContent, title) {
                           if (!htmlContent) {
                               return;
                           }

                           var printWindow = window.open('', '_blank');
                           if (!printWindow) {
                               return;
                           }

                           printWindow.document.open();
                           printWindow.document.write(
                               '<html><head><title>' + safe(title) + '</title>' +
                               '<style>' + getPrintDocumentStyles() + '</style></head><body>' +
                               htmlContent +
                               '</body></html>'
                           );
                           printWindow.document.close();
                           printWindow.focus();
                           printWindow.print();
                           printWindow.close();
                       }

                       function printSection1() {
                           var section = document.getElementById('section-1-print');
                           var html = buildSectionCenterTitleHtml('Quarter Report') +
                               '<h3 style="text-align:center;">Quarter - ' + safe(vm.quarter) + '</h3>';

                           if (section && section.innerHTML) {
                               html += section.innerHTML;
                           } else {
                               html += buildQuarterReportTableForWord();
                           }

                           printSectionHtml(html, 'TOC Quarter Report');
                       }

                       function printReportSection(sectionIndex) {
                           sectionIndex = parseInt(sectionIndex, 10);

                           switch (sectionIndex) {
                               case 0:
                                   printSection1();
                                   break;
                               case 1:
                                   printSectionHtml(
                                       buildTaxRedemptionSectionHtml(),
                                       vm.taxRedemptionTitle || 'Tax Redemption'
                                   );
                                   break;
                               case 2:
                                   printSectionHtml(
                                       buildAnnex2aExportTable(),
                                       'ANNEX 2A - Certificate By The Agency Bank'
                                   );
                                   break;
                               case 3:
                                   printSectionHtml(
                                       buildExternalAuditExportTable(),
                                       'External Audit Certificate'
                                   );
                                   break;
                               case 4:
                                   printSectionHtml(
                                       buildTaxInvoiceExportTable(),
                                       'TOC Tax Invoice'
                                   );
                                   break;
                               case 5:
                                   printSectionHtml(
                                       buildBankInternalFormatExportTable(),
                                       'Bank Internal Format'
                                   );
                                   break;
                               case 6:
                                   printSectionHtml(
                                       buildAnnexureAExportTable(),
                                       'ANNEXURE A'
                                   );
                                   break;
                               case 7:
                                   printSectionHtml(
                                       buildAnnexureBExportTable(),
                                       'ANNEXURE B'
                                   );
                                   break;
                               default:
                                   break;
                           }
                       }

	}
})();