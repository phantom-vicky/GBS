package com.isl.gbm.rbibond.util;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.reports.NetBankingTransectionPdfDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class NetBankingTransectionPdfGenerationHelper {


    public List<String> wrapText(String text, PDType1Font font, float fontSize, float maxWidth) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return lines;
        }

        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            String testLine = currentLine.length() > 0 ? currentLine.toString() + " " + word : word;
            float testWidth = 0;
            try {
                testWidth = font.getStringWidth(testLine) / 1000f * fontSize;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (testWidth > maxWidth && currentLine.length() > 0) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            } else {
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            }
        }

        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines;
    }

    public byte[] generatePdfFile(ResponseDataDTO netBankingTransectionPdfDTO1) throws IOException {

        NetBankingTransectionPdfDTO netBankingTransectionPdfDTO3 = (NetBankingTransectionPdfDTO) netBankingTransectionPdfDTO1.getResult();

        String brokerCode = "";
        String branchCode = "";
        String bankCode = "";
        String bankName = "";
        String addressOfBranch = "";
        String bankBranchStamp = "";
        String bondDate ="";
        String nameInFullOne = "";
        String genderOne = "";
        String dobOne = "";
        String panOne = "";
        String motherNameOne = "";
        String communicationAddressOne = "";
        String mobileNumberOne = "";
        String emailOne = "";
        String applicationNumber = "";
        String nameInFullTwo = "";
        String genderTwo = "";
        String dobTwo = "";
        String panTwo = "";
        String motherNameTwo = "";
        String communicationAddressTwo = "";
        String mobileNumberTwo = "";
        String emailTwo = "";

        String nameInFullThree = "";
        String genderThree = "";
        String dobThree = "";
        String panThree = "";
        String motherNameThree = "";
        String communicationAddressThree = "";
        String mobileNumberThree = "";
        String emailThree = "";

        String nameInFullGaurd = "";
        String dobGaurd = "";
        String relationWithMinor = "";
        String gaurdAddress = "";
        String gaurdMobileNumber = "";
        String gaurdEmailId = "";

        String accountHolderName = "";
        String bankNameOtherDetails = "";
        String branchAddress = "";
        String bankMicrCode = "";
        String branchMicrCode = "";
        String accountNumber = "";
        String ifscCode = "";
        String accountType = "";

        String applicationStatus = "";
        String modeOfHolding = "";
        String modeOfInvest = "";
        String investMentAmount = "";

        String amount = "";
        String name = "";
        String addressOfNominee = "";
        String relationWithHolder = "";
        String statusResident = "";
        String dateOfIssue = "";

        List<HashMap<String, String>> branchDetailss = netBankingTransectionPdfDTO3.getBranchDetails();

        if (branchDetailss != null) {

            HashMap<String, String> branchDetailsList = branchDetailss.get(0);

            brokerCode = branchDetailsList.get("brokerCode");
            branchCode = branchDetailsList.get("branchCode");
            bankCode = branchDetailsList.get("bankCode");
            bankName = branchDetailsList.get("bankName");
            addressOfBranch = branchDetailsList.get("addressOfBranch");
            bankBranchStamp = branchDetailsList.get("bankBranchStamp");
            bondDate  =branchDetailsList.get("bondDate");

        }

        List<HashMap<String, String>> applicationDetailss = netBankingTransectionPdfDTO3.getApplicationDetails();

        if (!applicationDetailss.isEmpty()) {
            HashMap<String, String> applicationDetailssOne = applicationDetailss.get(0);

            nameInFullOne = applicationDetailssOne.get("nameInFull");
            genderOne = applicationDetailssOne.get("gender");
            dobOne = applicationDetailssOne.get("dob");
            panOne = applicationDetailssOne.get("pan");
            motherNameOne = applicationDetailssOne.get("motherName");
            communicationAddressOne = applicationDetailssOne.get("communicationAddress");
            mobileNumberOne = applicationDetailssOne.get("mobileNumber");
            emailOne = applicationDetailssOne.get("email");
            applicationNumber = applicationDetailssOne.get("applicationNumber");

        }

        if (!applicationDetailss.isEmpty() && applicationDetailss.size() > 1) {
            HashMap<String, String> applicationDetailssTwo = applicationDetailss.get(1);
            nameInFullTwo = applicationDetailssTwo.get("nameInFull");
            genderTwo = applicationDetailssTwo.get("gender");
            dobTwo = applicationDetailssTwo.get("dob");
            panTwo = applicationDetailssTwo.get("pan");
            motherNameTwo = applicationDetailssTwo.get("motherName");
            communicationAddressTwo = applicationDetailssTwo.get("communicationAddress");
            mobileNumberTwo = applicationDetailssTwo.get("mobileNumber");
            emailTwo = applicationDetailssTwo.get("email");

        }

        if (!applicationDetailss.isEmpty() && applicationDetailss.size() > 2) {

            HashMap<String, String> applicationDetailssThree = applicationDetailss.get(2);
            nameInFullThree = applicationDetailssThree.get("nameInFull");
            genderThree = applicationDetailssThree.get("gender");
            dobThree = applicationDetailssThree.get("dob");
            panThree = applicationDetailssThree.get("pan");
            motherNameThree = applicationDetailssThree.get("motherName");
            communicationAddressThree = applicationDetailssThree.get("communicationAddress");
            mobileNumberThree = applicationDetailssThree.get("mobileNumber");
            emailThree = applicationDetailssThree.get("email");


        }

        List<HashMap<String, String>> guardianDetailss = netBankingTransectionPdfDTO3.getGaurdianDetails();


        if (!guardianDetailss.isEmpty()) {
            HashMap<String, String> guardianDetailsss = guardianDetailss.get(0);

            nameInFullGaurd = guardianDetailsss.get("nameInFull");
            dobGaurd = guardianDetailsss.get("dob");
            relationWithMinor = guardianDetailsss.get("relationWithMinor");
            gaurdAddress = guardianDetailsss.get("gaurdAddress");
            gaurdMobileNumber = guardianDetailsss.get("gaurdMobileNumber");
            gaurdEmailId = guardianDetailsss.get("gaurdEmailId");

        }

        List<HashMap<String, String>> getHolderOtherDetails = netBankingTransectionPdfDTO3.getHolderOtherDetails();

        if (!getHolderOtherDetails.isEmpty()) {
            HashMap<String, String> getHolderOtherDetailss = getHolderOtherDetails.get(0);

            accountHolderName = getHolderOtherDetailss.get("accountHolderName");
            bankNameOtherDetails = getHolderOtherDetailss.get("bankName");
            branchAddress = getHolderOtherDetailss.get("branchAddress");
            bankMicrCode = getHolderOtherDetailss.get("bankMicrCode");
            branchMicrCode = getHolderOtherDetailss.get("branchMicrCode");
            accountNumber = getHolderOtherDetailss.get("accountNumber");
            ifscCode = getHolderOtherDetailss.get("ifscCode");
            accountType = getHolderOtherDetailss.get("accountType");

        }

        List<HashMap<String, String>> getHolderParticulerDetails = netBankingTransectionPdfDTO3.getParticulerBankDetails();

        if (!getHolderParticulerDetails.isEmpty()) {

            HashMap<String, String> getHolderParticulerDetailss = getHolderParticulerDetails.get(0);

            applicationStatus = getHolderParticulerDetailss.get("applicationStatus");
            modeOfHolding = getHolderParticulerDetailss.get("modeOfHolding");
            modeOfInvest = getHolderParticulerDetailss.get("modeOfInvest");
            investMentAmount = getHolderParticulerDetailss.get("investMentAmount");

        }
        List<HashMap<String, String>> nomineeDetails = netBankingTransectionPdfDTO3.getNomineeDetails();

        if (!nomineeDetails.isEmpty()) {
            HashMap<String, String> nomineeDetailss = nomineeDetails.get(0);

            amount = nomineeDetailss.get("amount");
            name = nomineeDetailss.get("name");
            addressOfNominee = nomineeDetailss.get("addressOfNominee");
            relationWithHolder = nomineeDetailss.get("relationWithHolder");
            statusResident = nomineeDetailss.get("statusResident");
            dateOfIssue = nomineeDetailss.get("dateOfIssue");

        }

        if (modeOfHolding != null) {

            if (modeOfHolding.equalsIgnoreCase("MH001")) {
                modeOfHolding = "Single";

            } else if (modeOfHolding.equalsIgnoreCase("MH002")) {
                modeOfHolding = "Joint – Jointly";
            } else if (modeOfHolding.equalsIgnoreCase("MH003")) {
                modeOfHolding = "Joint - Anyone or Survivor";
            } else if (modeOfHolding.equalsIgnoreCase("MH004")) {
                modeOfHolding = "HUF";
            } else if (modeOfHolding.equalsIgnoreCase("MH005")) {
                modeOfHolding = "Legal Guardian";
            }

        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();
        try {
            // Create A4 page
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float topMargin = 20f;
            float bottomMargin = 40f;
            float leftMargin = 20f;
            float rightMargin = 20f;
            float pageWidth = PDRectangle.A4.getWidth();
            float pageHeight = PDRectangle.A4.getHeight();
            float contentWidth = pageWidth - leftMargin - rightMargin;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // --------------------------------------------------
            //                LOGO (Positioned at top right)
            // --------------------------------------------------
            float imageWidth = 240f;
            float imageHeight = 60f;
            float logoTopMargin = 20f;
            float logoRightMargin = 20f;

            try {
                ClassPathResource resource = new ClassPathResource("static/HDFC-logo.png");
                PDImageXObject image = PDImageXObject.createFromFile(resource.getFile().getAbsolutePath(), document);

                // Position logo at top right corner
                float imageX = pageWidth - imageWidth - logoRightMargin;
                float imageY = pageHeight - imageHeight - logoTopMargin;

                contentStream.drawImage(image, imageX, imageY, imageWidth, imageHeight);
            } catch (Exception e) {
                System.out.println("Logo image not found, continuing without logo");
            }

            // =====================================================
            //              HEADER 1 — Centered (Below Logo)
            // =====================================================
            // Position header below the logo with proper spacing
            // Logo bottom is at: pageHeight - imageHeight - logoTopMargin
            // Add spacing of 30f below logo
            float headerStartY = pageHeight - imageHeight - logoTopMargin - 30f;
            float yPosition = headerStartY;

            String header1 = "APPLICATION FORM FOR";
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);

            float textWidth1 = PDType1Font.HELVETICA_BOLD.getStringWidth(header1) / 1000f * 13;
            float centerX1 = (pageWidth - textWidth1) / 2;

            contentStream.newLineAtOffset(centerX1, yPosition);
            contentStream.showText(header1);
            contentStream.endText();

            yPosition -= 22;

            // =====================================================
            //              HEADER 2 — Centered (Fixed)
            // =====================================================
            String header2 = "FLOATING RATE SAVINGS BONDS, 2020 (TAXABLE)";
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);

            float textWidth2 = PDType1Font.HELVETICA_BOLD.getStringWidth(header2) / 1000f * 13;
            float centerX2 = (pageWidth - textWidth2) / 2;

            contentStream.newLineAtOffset(centerX2, yPosition);
            contentStream.showText(header2);
            contentStream.endText();


            yPosition -= 40;

            // Create a table with 2 columns for branch name and application number using BaseTable
            yPosition -= 20;
            BaseTable tablee3 = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> rowBranch = tablee3.createRow(15);
            rowBranch.createCell(50f, "NAME OF THE BRANCH :" + netBankingTransectionPdfDTO3.getBranchName());
            rowBranch.createCell(50f, "Application Number :: " + applicationNumber);
            yPosition = tablee3.draw() - 10;

            // Add "For Office Use Only" header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("FOR OFFICE USE ONLY");
            contentStream.endText();
            yPosition -= 20;

            // Define column widths and add the main table using BaseTable
            yPosition -= 10;
            BaseTable table = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> headerRow = table.createRow(15);
            headerRow.createCell(16.67f, "Broker's Name & Code");
            headerRow.createCell(16.67f, "Sub Broker's Name & Code");
            headerRow.createCell(16.67f, "Bank Branch Stamp");
            headerRow.createCell(16.67f, "Branch Name & Code");
            headerRow.createCell(16.67f, "BLA No.");
            headerRow.createCell(16.67f, "Verify By");

            be.quodlibet.boxable.Row<PDPage> dataRow1 = table.createRow(15);
            dataRow1.createCell(16.67f, brokerCode != null ? brokerCode : "");
            dataRow1.createCell(16.67f, "");
            dataRow1.createCell(16.67f, "NETBK");
            dataRow1.createCell(16.67f, "NETBK");
            dataRow1.createCell(16.67f, "");
            dataRow1.createCell(16.67f, "");

            be.quodlibet.boxable.Row<PDPage> dataRow2 = table.createRow(15);
            dataRow2.createCell(16.67f, "Date of receipt of Application (DD/MM/YYYY)");
            dataRow2.createCell(16.67f, "Date of realization of funds (DD/MM/YYYY)");
            dataRow2.createCell(16.67f, "Date of transfer to Link Cell (DD/MM/YYYY)");
            dataRow2.createCell(33.33f, "Full Address of the Branch");
            dataRow2.createCell(16.67f, "Any other information");

            be.quodlibet.boxable.Row<PDPage> dataRow3 = table.createRow(15);
            dataRow3.createCell(16.67f, "");
            dataRow3.createCell(16.67f, "");
            dataRow3.createCell(16.67f, "");
            dataRow3.createCell(33.33f, "NETBK");
            dataRow3.createCell(16.67f, "");

            yPosition = table.draw() - 15;

            // Add text with bold font
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Information regarding Floating Rate Savings Bonds, 2020 (Taxable) -");
            contentStream.endText();
            yPosition -= 20;

            // Information regarding bonds with text wrapping
            String infoText1 = "(i) The interest on the bonds is payable semi-annually with resetting of coupon rate every 01 January and 01 July.";
            String infoText2 = "(ii) The bonds will be issued subject to the terms and conditions laid down in Government of India Notification No.F.No.4(10)- B (W&M)/2020 dated June 26, 2020.";

            java.util.List<String> infoLines1 = wrapText(infoText1, PDType1Font.HELVETICA, 10, contentWidth);
            java.util.List<String> infoLines2 = wrapText(infoText2, PDType1Font.HELVETICA, 10, contentWidth);

            for (int i = 0; i < infoLines1.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    // Close current contentStream if it exists
                    if (contentStream != null) {
                        try {
                            contentStream.close();
                        } catch (Exception e) {
                            // Ignore if already closed
                        }
                    }
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                }
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText(infoLines1.get(i));
                contentStream.endText();
                yPosition -= 15;
            }

            // Add spacing between information items
            yPosition -= 5;

            for (int i = 0; i < infoLines2.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    // Close current contentStream if it exists
                    if (contentStream != null) {
                        try {
                            contentStream.close();
                        } catch (Exception e) {
                            // Ignore if already closed
                        }
                    }
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                }
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText(infoLines2.get(i));
                contentStream.endText();
                yPosition -= 15;
            }
            yPosition -= 5;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Instructions regarding filling up of the form ");
            contentStream.endText();
            yPosition -= 20;

            // Instructions with text wrapping
            String[] instructionTexts = {
                    "(i) Please fill the application form in all respects. Incomplete form is liable to be rejected.",
                    "(ii) In fields with * mark, strike out whichever is not applicable.",
                    "(iii) It is strongly recommended that the Nomination Form may also be filled in along with the Application Form, in order to avoid inconvenience later."
            };

            for (int textIdx = 0; textIdx < instructionTexts.length; textIdx++) {
                String text = instructionTexts[textIdx];
                java.util.List<String> wrappedLines = wrapText(text, PDType1Font.HELVETICA, 10, contentWidth);

                for (int i = 0; i < wrappedLines.size(); i++) {
                    if (yPosition < bottomMargin + 20) {
                        // Close current contentStream if it exists
                        if (contentStream != null) {
                            try {
                                contentStream.close();
                            } catch (Exception e) {
                                // Ignore if already closed
                            }
                        }
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        yPosition = pageHeight - topMargin;
                    }
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                    contentStream.showText(wrappedLines.get(i));
                    contentStream.endText();
                    yPosition -= 15;
                }
                // Add spacing between instruction items (except after the last one)
                if (textIdx < instructionTexts.length - 1) {
                    yPosition -= 5;
                }
            }
            yPosition -= 5;

            // Applicant(s) Details header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Applicant(s) Details [in block letters]");
            contentStream.endText();
            yPosition -= 30;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("1st Applicant / Sole Applicant Details");
            contentStream.endText();
            yPosition -= 20;


            // First Applicant table
            BaseTable table1 = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> row1 = table1.createRow(18);
            row1.createCell(16.67f, "Name in full :");
            row1.createCell(50f, nameInFullOne != null ? nameInFullOne : "");
            row1.createCell(16.67f, "Gender :");
            row1.createCell(16.67f, genderOne != null ? genderOne : "");

            be.quodlibet.boxable.Row<PDPage> row2 = table1.createRow(18);
            row2.createCell(16.67f, "DOB :");
            row2.createCell(16.67f, dobOne != null ? dobOne : "");
            row2.createCell(16.67f, "Pan No:");
            row2.createCell(16.67f, panOne != null ? panOne : "");
            row2.createCell(16.67f, "Mother maiden name:");
            row2.createCell(16.67f, "");

            be.quodlibet.boxable.Row<PDPage> row3 = table1.createRow(35);
            row3.createCell(16.67f, "Communication Address :");
            row3.createCell(83.33f, communicationAddressOne != null ? communicationAddressOne : "");

            be.quodlibet.boxable.Row<PDPage> row4 = table1.createRow(30);
            row4.createCell(16.67f, "Telephones (Mobile/Residence):");
            row4.createCell(83.33f, mobileNumberOne != null ? mobileNumberOne : "");

            be.quodlibet.boxable.Row<PDPage> row5 = table1.createRow(25);
            row5.createCell(16.67f, "Email Address :");
            row5.createCell(83.33f, emailOne != null ? emailOne : "");

            yPosition = table1.draw() - 30;

            // Check for page break before 2nd Applicant Details
            if (yPosition < bottomMargin + 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // 2nd Applicant Details
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("2nd Applicant Details");
            contentStream.endText();
            yPosition -= 20;

            // Check if we have enough space for 2nd Applicant table
            if (yPosition < bottomMargin + 60) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // 2nd Applicant table
            BaseTable table2 = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> row2_1 = table2.createRow(15);
            row2_1.createCell(20f, "Name in full :");
            row2_1.createCell(50f, nameInFullTwo != null ? nameInFullTwo : "");
            row2_1.createCell(15f, "Gender :");
            row2_1.createCell(15f, genderTwo != null ? genderTwo : "");

            be.quodlibet.boxable.Row<PDPage> row2_2 = table2.createRow(15);
            row2_2.createCell(25f, "DOB :");
            row2_2.createCell(25f, dobTwo != null ? dobTwo : "");
            row2_2.createCell(25f, "Pan No:");
            row2_2.createCell(25f, panTwo != null ? panTwo : "");

            yPosition = table2.draw() - 20;

            // Check for page break before 3rd Applicant Details
            if (yPosition < bottomMargin + 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // 3rd Applicant Details
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("3rd Applicant Details");
            contentStream.endText();
            yPosition -= 20;

            // Check if we have enough space for 3rd Applicant table
            if (yPosition < bottomMargin + 60) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // 3rd Applicant table
            BaseTable table3 = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> row3_1 = table3.createRow(15);
            row3_1.createCell(20f, "Name in full :");
            row3_1.createCell(50f, nameInFullThree != null ? nameInFullThree : "");
            row3_1.createCell(15f, "Gender :");
            row3_1.createCell(15f, genderThree != null ? genderThree : "");

            be.quodlibet.boxable.Row<PDPage> row3_2 = table3.createRow(15);
            row3_2.createCell(25f, "DOB :");
            row3_2.createCell(25f, dobThree != null ? dobThree : "");
            row3_2.createCell(25f, "Pan No:");
            row3_2.createCell(25f, panThree != null ? panThree : "");

            yPosition = table3.draw() - 20;

            // Check for page break before Guardian Details
            // Guardian Details table needs approximately 200 points of space
            if (yPosition < bottomMargin + 200) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // Guardian Details header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Guardian Details (To be filled in case of minor)");
            contentStream.endText();
            yPosition -= 25;


            // Guardian Details table
            // Calculate approximate table height: 18 + 30 + 35 + 30 + 25 = 138 points
            // Add extra padding for cell padding and borders: ~180 points total needed
            // Check if we have enough space, if not, create new page
            if (yPosition < bottomMargin + 180) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            BaseTable table4 = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> gRow1 = table4.createRow(18);
            gRow1.createCell(16.67f, "Name in full :");
            gRow1.createCell(50f, nameInFullGaurd != null ? nameInFullGaurd : "");
            gRow1.createCell(16.67f, "Gender :");
            gRow1.createCell(16.67f, "");

            be.quodlibet.boxable.Row<PDPage> gRow2 = table4.createRow(30);
            gRow2.createCell(20f, "Relationship with minor:");
            gRow2.createCell(20f, relationWithMinor != null ? relationWithMinor : "");
            gRow2.createCell(20f, "DOB:");
            gRow2.createCell(40f, dobGaurd != null ? dobGaurd : "");

            be.quodlibet.boxable.Row<PDPage> gRow3 = table4.createRow(35);
            gRow3.createCell(16.67f, "Communication Address :");
            gRow3.createCell(83.33f, gaurdAddress != null ? gaurdAddress : "");

            be.quodlibet.boxable.Row<PDPage> gRow4 = table4.createRow(30);
            gRow4.createCell(16.67f, "Telephones (Mobile/Residence):");
            gRow4.createCell(83.33f, gaurdMobileNumber != null ? gaurdMobileNumber : "");

            be.quodlibet.boxable.Row<PDPage> gRow5 = table4.createRow(25);
            gRow5.createCell(16.67f, "Email Address :");
            gRow5.createCell(83.33f, gaurdEmailId != null ? gaurdEmailId : "");

            yPosition = table4.draw() - 20;

            // Check for page break before Other Details
            if (yPosition < bottomMargin + 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // Other Details header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Other Details");
            contentStream.endText();
            yPosition -= 25;

            // Applicant Status with conditional underlining and text wrapping
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Applicant Status               :   ");
            float statusX = leftMargin + PDType1Font.HELVETICA.getStringWidth("Applicant Status               :   ") / 1000f * 12;
            contentStream.endText();

            // Calculate available width for status text
            float statusAvailableWidth = pageWidth - statusX - rightMargin;

            String statusText1 = "(i) RESIDENT INDIVIDUAL ";
            String statusText2 = "(ii) HUF ";
            String statusText3 = "(iii) ON BEHALF OF MINOR ";
            String statusText4 = "(iv) POWER OF ATTORNY HOLDER";

            String fullStatusText = statusText1 + statusText2 + statusText3 + statusText4;
            String textToUnderline = "";
            String textToUnderlineWithSpace = "";

            // Determine which text needs to be underlined based on application status
            if (applicationStatus != null) {
                if (applicationStatus.equalsIgnoreCase("HT003")) {
                    textToUnderline = statusText1.trim();
                    textToUnderlineWithSpace = statusText1;
                } else if (applicationStatus.equalsIgnoreCase("HT002")) {
                    textToUnderline = statusText2.trim();
                    textToUnderlineWithSpace = statusText2;
                } else if (applicationStatus.equalsIgnoreCase("HT001")) {
                    textToUnderline = statusText4.trim();
                    textToUnderlineWithSpace = statusText4;
                } else {
                    // Default case (ON BEHALF OF MINOR)
                    textToUnderline = statusText3.trim();
                    textToUnderlineWithSpace = statusText3;
                }
            }

            // Wrap the status text
            java.util.List<String> wrappedStatusLines = wrapText(fullStatusText, PDType1Font.HELVETICA_BOLD, 14, statusAvailableWidth);

            float currentY = yPosition;
            float currentX = statusX;
            float lineHeight = 18f;

            for (int lineIdx = 0; lineIdx < wrappedStatusLines.size(); lineIdx++) {
                String line = wrappedStatusLines.get(lineIdx);

                // Check if we need a new page
                if (currentY < bottomMargin + 20) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    currentY = pageHeight - topMargin;
                }

                // Draw the line text
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.newLineAtOffset(currentX, currentY);
                contentStream.showText(line);
                contentStream.endText();

                // Handle underlining if needed
                if (!textToUnderline.isEmpty()) {
                    // Check if this line contains the text to underline
                    // Try to find the text in the line (try both with and without trailing space)
                    int underlineStartInLine = -1;
                    String foundText = "";

                    // First try with space (as it appears in fullStatusText)
                    if (!textToUnderlineWithSpace.isEmpty()) {
                        underlineStartInLine = line.indexOf(textToUnderlineWithSpace);
                        if (underlineStartInLine >= 0) {
                            foundText = textToUnderlineWithSpace;
                        }
                    }

                    // If not found, try without trailing space
                    if (underlineStartInLine < 0) {
                        underlineStartInLine = line.indexOf(textToUnderline);
                        if (underlineStartInLine >= 0) {
                            foundText = textToUnderline;
                        }
                    }

                    if (underlineStartInLine >= 0) {
                        // Found the complete text to underline in this line
                        String beforeUnderline = line.substring(0, underlineStartInLine);
                        String underlineText = line.substring(underlineStartInLine, underlineStartInLine + foundText.length());

                        // Calculate X position for underline
                        float beforeWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(beforeUnderline) / 1000f * 14;
                        float underlineWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(underlineText) / 1000f * 14;

                        // Draw underline
                        contentStream.moveTo(currentX + beforeWidth, currentY - 2);
                        contentStream.lineTo(currentX + beforeWidth + underlineWidth, currentY - 2);
                        contentStream.stroke();
                    } else {
                        // Text might be split across lines, check for partial matches
                        // Check if line ends with part of the text or starts with part of it
                        for (int i = 1; i < textToUnderline.length(); i++) {
                            String textStart = textToUnderline.substring(0, i);
                            String textEnd = textToUnderline.substring(i);

                            // Check if line ends with start of text to underline
                            if (line.endsWith(textStart) && lineIdx < wrappedStatusLines.size() - 1) {
                                String nextLine = wrappedStatusLines.get(lineIdx + 1);
                                if (nextLine.startsWith(textEnd)) {
                                    // Underline from the start position to end of line
                                    int underlineStart = line.length() - textStart.length();
                                    String beforeUnderline = line.substring(0, underlineStart);
                                    String underlineText = line.substring(underlineStart);

                                    float beforeWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(beforeUnderline) / 1000f * 14;
                                    float underlineWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(underlineText) / 1000f * 14;

                                    contentStream.moveTo(currentX + beforeWidth, currentY - 2);
                                    contentStream.lineTo(currentX + beforeWidth + underlineWidth, currentY - 2);
                                    contentStream.stroke();
                                    break;
                                }
                            }

                            // Check if line starts with end of text to underline
                            if (line.startsWith(textEnd) && lineIdx > 0) {
                                String prevLine = wrappedStatusLines.get(lineIdx - 1);
                                if (prevLine.endsWith(textStart)) {
                                    // Underline from start of line to end position
                                    String underlineText = line.substring(0, textEnd.length());
                                    float underlineWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(underlineText) / 1000f * 14;

                                    contentStream.moveTo(currentX, currentY - 2);
                                    contentStream.lineTo(currentX + underlineWidth, currentY - 2);
                                    contentStream.stroke();
                                    break;
                                }
                            }
                        }
                    }
                }

                currentY -= lineHeight;
                currentX = statusX; // Reset X for next line
            }

            yPosition = currentY;


            // Invest Details
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Invest Details                   : CREDIT TO EXISTING BLA No. / OPEN A NEW BLA @");
            contentStream.endText();
            yPosition -= 20;

            // Mode of Holding
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Mode of Holding              :      ");
            float modeHoldingX = leftMargin + PDType1Font.HELVETICA.getStringWidth("Mode of Holding              :      ") / 1000f * 12;
            contentStream.endText();
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
            contentStream.newLineAtOffset(modeHoldingX, yPosition);
            contentStream.showText(modeOfHolding != null ? modeOfHolding : "");
            contentStream.endText();
            yPosition -= 20;

            // Mode of Invest with conditional underlining and text wrapping
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Mode of Invest                 :     ");
            float modeInvestX = leftMargin + PDType1Font.HELVETICA.getStringWidth("Mode of Invest                 :     ") / 1000f * 12;
            contentStream.endText();

            // Calculate available width for invest text
            float modeInvestAvailableWidth = pageWidth - modeInvestX - rightMargin;

            String investText1 = "(i) CASH (up to Rs.20,000/- only)";
            String investText2 = "(ii)CHEQUE ";
            String investText3 = "(iii) DEMAND DRAFT ";
            String investText4 = "(iv) ELECTRONIC CREDIT";

            String fullInvestText = investText1 + investText2 + investText3 + investText4;
            textToUnderline = "";
            textToUnderlineWithSpace = "";

            // Determine which text needs to be underlined based on mode of invest
            if (modeOfInvest != null) {
                if (modeOfInvest.equalsIgnoreCase("C")) {
                    textToUnderline = investText1.trim();
                    textToUnderlineWithSpace = investText1;
                } else if (modeOfInvest.equalsIgnoreCase("T") || modeOfInvest.equalsIgnoreCase("CL")) {
                    textToUnderline = investText2.trim();
                    textToUnderlineWithSpace = investText2;
                } else if (modeOfInvest.equalsIgnoreCase("DD")) {
                    textToUnderline = investText3.trim();
                    textToUnderlineWithSpace = investText3;
                } else {
                    // Default case (ELECTRONIC CREDIT)
                    textToUnderline = investText4.trim();
                    textToUnderlineWithSpace = investText4;
                }
            }

            // Wrap the invest text
            java.util.List<String> wrappedInvestLines = wrapText(fullInvestText, PDType1Font.HELVETICA_BOLD, 14, modeInvestAvailableWidth);

            currentY = yPosition;
            currentX = modeInvestX;
            lineHeight = 18f;

            for (int lineIdx = 0; lineIdx < wrappedInvestLines.size(); lineIdx++) {
                String line = wrappedInvestLines.get(lineIdx);

                // Check if we need a new page
                if (currentY < bottomMargin + 20) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    currentY = pageHeight - topMargin;
                }

                // Draw the line text
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.newLineAtOffset(currentX, currentY);
                contentStream.showText(line);
                contentStream.endText();

                // Handle underlining if needed
                if (!textToUnderline.isEmpty()) {
                    // Check if this line contains the text to underline
                    // Try to find the text in the line (try both with and without trailing space)
                    int underlineStartInLine = -1;
                    String foundText = "";

                    // First try with space (as it appears in fullInvestText)
                    if (!textToUnderlineWithSpace.isEmpty()) {
                        underlineStartInLine = line.indexOf(textToUnderlineWithSpace);
                        if (underlineStartInLine >= 0) {
                            foundText = textToUnderlineWithSpace;
                        }
                    }

                    // If not found, try without trailing space
                    if (underlineStartInLine < 0) {
                        underlineStartInLine = line.indexOf(textToUnderline);
                        if (underlineStartInLine >= 0) {
                            foundText = textToUnderline;
                        }
                    }

                    if (underlineStartInLine >= 0) {
                        // Found the complete text to underline in this line
                        String beforeUnderline = line.substring(0, underlineStartInLine);
                        String underlineText = line.substring(underlineStartInLine, underlineStartInLine + foundText.length());

                        // Calculate X position for underline
                        float beforeWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(beforeUnderline) / 1000f * 14;
                        float underlineWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(underlineText) / 1000f * 14;

                        // Draw underline
                        contentStream.moveTo(currentX + beforeWidth, currentY - 2);
                        contentStream.lineTo(currentX + beforeWidth + underlineWidth, currentY - 2);
                        contentStream.stroke();
                    } else {
                        // Text might be split across lines, check for partial matches
                        // Check if line ends with part of the text or starts with part of it
                        for (int i = 1; i < textToUnderline.length(); i++) {
                            String textStart = textToUnderline.substring(0, i);
                            String textEnd = textToUnderline.substring(i);

                            // Check if line ends with start of text to underline
                            if (line.endsWith(textStart) && lineIdx < wrappedInvestLines.size() - 1) {
                                String nextLine = wrappedInvestLines.get(lineIdx + 1);
                                if (nextLine.startsWith(textEnd)) {
                                    // Underline from the start position to end of line
                                    int underlineStart = line.length() - textStart.length();
                                    String beforeUnderline = line.substring(0, underlineStart);
                                    String underlineText = line.substring(underlineStart);

                                    float beforeWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(beforeUnderline) / 1000f * 14;
                                    float underlineWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(underlineText) / 1000f * 14;

                                    contentStream.moveTo(currentX + beforeWidth, currentY - 2);
                                    contentStream.lineTo(currentX + beforeWidth + underlineWidth, currentY - 2);
                                    contentStream.stroke();
                                    break;
                                }
                            }

                            // Check if line starts with end of text to underline
                            if (line.startsWith(textEnd) && lineIdx > 0) {
                                String prevLine = wrappedInvestLines.get(lineIdx - 1);
                                if (prevLine.endsWith(textStart)) {
                                    // Underline from start of line to end position
                                    String underlineText = line.substring(0, textEnd.length());
                                    float underlineWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(underlineText) / 1000f * 14;

                                    contentStream.moveTo(currentX, currentY - 2);
                                    contentStream.lineTo(currentX + underlineWidth, currentY - 2);
                                    contentStream.stroke();
                                    break;
                                }
                            }
                        }
                    }
                }

                currentY -= lineHeight;
                currentX = modeInvestX; // Reset X for next line
            }

            yPosition = currentY;

            NumberToWordConverter numberToWordConverterr = new NumberToWordConverter();

            // Cheque/DD details
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("For Cheque/DD Number :                                                   Dated :");
            yPosition -= 15;
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Drawn on Bank/branch : ");
            contentStream.endText();
            yPosition -= 20;

            // Investment Amount
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Invest AMT : (In fig.) " + (investMentAmount != null ? investMentAmount : ""));
            yPosition -= 15;
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("(In words) " + (investMentAmount != null ? numberToWordConverterr.convertAmountToWordsString(investMentAmount) : ""));
            contentStream.endText();
            yPosition -= 25;

            // Bank Particulars header with text wrapping
            String bankParticularsHeader = "BANK PARTICULARS of the 1st Applicant FOR PAYMENT OF INTEREST / REDEMPTION AMOUNT";
            java.util.List<String> bankHeaderLines = wrapText(bankParticularsHeader, PDType1Font.HELVETICA_BOLD, 13, contentWidth);

            for (int i = 0; i < bankHeaderLines.size(); i++) {
                // Check if we need a new page
                if (yPosition < bottomMargin + 20) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                }

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText(bankHeaderLines.get(i));
                contentStream.endText();
                yPosition -= 18;
            }


            // Bank Particulars table
            BaseTable table5 = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> bankRow1 = table5.createRow(20);
            bankRow1.createCell(20f, "Account Holder Name::");
            bankRow1.createCell(80f, accountHolderName != null ? accountHolderName : "");

            be.quodlibet.boxable.Row<PDPage> bankRow2 = table5.createRow(20);
            bankRow2.createCell(20f, "Bank Name: :");
            bankRow2.createCell(80f, bankNameOtherDetails != null ? bankNameOtherDetails : "");

            be.quodlibet.boxable.Row<PDPage> bankRow3 = table5.createRow(20);
            bankRow3.createCell(20f, "Branch Name / Address::");
            bankRow3.createCell(80f, branchAddress != null ? branchAddress : (netBankingTransectionPdfDTO3.getBranchName() != null ? netBankingTransectionPdfDTO3.getBranchName() : ""));

            be.quodlibet.boxable.Row<PDPage> bankRow4 = table5.createRow(20);
            bankRow4.createCell(20f, "MICR Code of Bank & Branch::");
            bankRow4.createCell(80f, bankMicrCode != null ? bankMicrCode : "");

            be.quodlibet.boxable.Row<PDPage> bankRow5 = table5.createRow(20);
            bankRow5.createCell(20f, "Account No.: :");
            bankRow5.createCell(80f, accountNumber != null ? accountNumber : "");

            be.quodlibet.boxable.Row<PDPage> bankRow6 = table5.createRow(20);
            bankRow6.createCell(20f, "IFSC Code:");
            bankRow6.createCell(80f, ifscCode != null ? ifscCode : "");

            be.quodlibet.boxable.Row<PDPage> bankRow7 = table5.createRow(20);
            bankRow7.createCell(20f, "Account Type :");
            String accountTypeText = "";
            if(accountType != null && accountType.equalsIgnoreCase("SB002")){
                accountTypeText = "(i) SB (ii) Current ";
            } else if(accountType != null && accountType.equalsIgnoreCase("SB003")){
                accountTypeText = "(i) SB (ii) Current ";
            } else {
                accountTypeText = "(i) SB (ii) Current ";
            }
            bankRow7.createCell(80f, accountTypeText);

            yPosition = table5.draw() - 15;

            // Note about cheque with text wrapping
            String chequeNoteText = "(Please attach a photocopy of the Cheque leaf or a cancelled cheque issued to you by the bank for verification of the Code number)";
            java.util.List<String> chequeNoteLines = wrapText(chequeNoteText, PDType1Font.HELVETICA, 9, contentWidth);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);

            for (int i = 0; i < chequeNoteLines.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                }
                contentStream.showText(chequeNoteLines.get(i));
                yPosition -= 15;
                if (i < chequeNoteLines.size() - 1 && yPosition >= bottomMargin + 20) {
                    contentStream.newLineAtOffset(0, -15);
                }
            }
            contentStream.endText();
            yPosition -= 5;

            // Nominee details with text wrapping
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Nominee details :");
            float nomineeX = leftMargin + PDType1Font.HELVETICA_BOLD.getStringWidth("Nominee details :") / 1000f * 9;
            contentStream.endText();

            // Nominee details text with text wrapping
            String nomineeText = "Nomination Form (as applicable) may be filled and submitted along with the application form.";
            float nomineeAvailableWidth = pageWidth - nomineeX - rightMargin;
            java.util.List<String> nomineeLines = wrapText(nomineeText, PDType1Font.HELVETICA, 9, nomineeAvailableWidth);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(nomineeX, yPosition);

            for (int i = 0; i < nomineeLines.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(nomineeX, yPosition);
                }
                contentStream.showText(nomineeLines.get(i));
                yPosition -= 15;
                if (i < nomineeLines.size() - 1 && yPosition >= bottomMargin + 20) {
                    contentStream.newLineAtOffset(0, -15);
                }
            }
            contentStream.endText();
            yPosition -= 5;

            // Declaration
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Declaration :");
            float declX = leftMargin + PDType1Font.HELVETICA_BOLD.getStringWidth("Declaration :") / 1000f * 9;
            contentStream.endText();
            // Declaration text with text wrapping - combine into one continuous text
            String declText = "I/We hereby declare and undertake that (i) the information furnished in this application form is correct and complete. If the transaction  is delayed at all for reasons of incomplete or incorrect information, I would not hold the user institution responsible. (ii) I have read and understood the details of information for the investors as well as rights and duties of investors (copy attached).The agent/bank has explained the features of the Scheme. (iii) I hereby agree to discharge the responsibility expected of me as a participant under the  scheme.";

            // Calculate available width for declaration text (from declX to right margin)
            float declAvailableWidth = pageWidth - declX - rightMargin;

            java.util.List<String> declLines = wrapText(declText, PDType1Font.HELVETICA, 9, declAvailableWidth);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(declX, yPosition);

            for (int i = 0; i < declLines.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(declX, yPosition);
                }
                contentStream.showText(declLines.get(i));
                yPosition -= 15;
                if (i < declLines.size() - 1 && yPosition >= bottomMargin + 20) {
                    contentStream.newLineAtOffset(0, -15);
                }
            }
            contentStream.endText();
            yPosition -= 5;

            // Income Tax declaration with header wrapping
            String taxHeaderText = "Declaration regarding Income Tax exemption, if any :";
            java.util.List<String> taxHeaderLines = wrapText(taxHeaderText, PDType1Font.HELVETICA_BOLD, 9, contentWidth);

            for (int i = 0; i < taxHeaderLines.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                }
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText(taxHeaderLines.get(i));
                contentStream.endText();
                yPosition -= 15;
            }

            // Calculate taxX - if header wrapped, use leftMargin, otherwise calculate from first line
            float taxX;
            if (taxHeaderLines.size() > 1) {
                // Header wrapped, align content with leftMargin
                taxX = leftMargin;
            } else {
                // Header on single line, calculate from header width
                taxX = leftMargin + PDType1Font.HELVETICA_BOLD.getStringWidth(taxHeaderLines.get(0)) / 1000f * 9;
            }
            // Income Tax declaration text with wrapping
            float taxAvailableWidth = pageWidth - taxX - rightMargin;
            // Combine all tax text into one continuous text for proper wrapping
            String taxText = "(Please strike out if not applicable) I have obtained Income Tax exemption from Income Tax Authorities under the provision_______ of the Income Tax Act, 1961 and am submitting a true copy of the certificate along with the application form.";

            java.util.List<String> taxLines = wrapText(taxText, PDType1Font.HELVETICA, 9, taxAvailableWidth);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(taxX, yPosition);

            for (int i = 0; i < taxLines.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(taxX, yPosition);
                }
                contentStream.showText(taxLines.get(i));
                yPosition -= 15;
                if (i < taxLines.size() - 1 && yPosition >= bottomMargin + 20) {
                    contentStream.newLineAtOffset(0, -15);
                }
            }
            contentStream.endText();
            yPosition -= 5;

            // Check for page break before signatures table
            if (yPosition < bottomMargin + 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // Signatures table
            BaseTable table10 = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            Row<PDPage> sigRow1 = table10.createRow(60);

// Applicant 1
            Cell<PDPage> cell1 = sigRow1.createCell(33.33f,
                    (nameInFullOne != null ? nameInFullOne : "") +
                            "    Signature / Thumb Impression - 1st Applicant");
            cell1.setTopPadding(35);   // creates spacing
            cell1.setLeftPadding(10);
            cell1.setFont(PDType1Font.HELVETICA);

// Applicant 2
            Cell<PDPage> cell2 = sigRow1.createCell(33.33f,
                    (nameInFullTwo != null ? nameInFullTwo : "") +
                            "    Signature / Thumb Impression - 2nd Applicant");
            cell2.setTopPadding(35);
            cell2.setLeftPadding(10);
            cell2.setFont(PDType1Font.HELVETICA);

// Applicant 3
            Cell<PDPage> cell3 = sigRow1.createCell(33.33f,
                    (nameInFullThree != null ? nameInFullThree : "") +
                            "    Signature / Thumb Impression - 3rd Applicant");
            cell3.setTopPadding(35);
            cell3.setLeftPadding(10);
            cell3.setFont(PDType1Font.HELVETICA);

// Bank stamp row
            Row<PDPage> sigRow2 = table10.createRow(60);
            Cell<PDPage> cell4 = sigRow2.createCell(100f,
                    "Date, Bank Stamp & Signature of the authorized official of the Receiving Office");
            cell4.setTopPadding(35);
            cell4.setLeftPadding(15);
            cell4.setFont(PDType1Font.HELVETICA);

            yPosition = table10.draw() - 20;


            // Date and Place
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("DATE :  " + (bondDate != null ? bondDate : ""));
            contentStream.endText();
            yPosition -= 20;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("PLACE :        NETBK");
            contentStream.endText();
            yPosition -= 25;

            // Witness attestation note
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);
            float witnessTextWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("In case of thumb impression, attestation by two witnesses") / 1000f * 13;
            contentStream.newLineAtOffset((pageWidth - witnessTextWidth) / 2, yPosition);
            contentStream.showText("In case of thumb impression, attestation by two witnesses");
            contentStream.endText();
            yPosition -= 25;

            // Witnesses table
            BaseTable table7 = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> witRow1 = table7.createRow(20);
            witRow1.createCell(50f, "          1st Witness");
            witRow1.createCell(50f, "           2nd Witness");

            be.quodlibet.boxable.Row<PDPage> witRow2 = table7.createRow(30);
            witRow2.createCell(25f, "Name");
            witRow2.createCell(25f, "");
            witRow2.createCell(25f, "Name");
            witRow2.createCell(25f, "");

            be.quodlibet.boxable.Row<PDPage> witRow3 = table7.createRow(30);
            witRow3.createCell(25f, "Address");
            witRow3.createCell(25f, "");
            witRow3.createCell(25f, "Address");
            witRow3.createCell(25f, "");

            be.quodlibet.boxable.Row<PDPage> witRow4 = table7.createRow(30);
            witRow4.createCell(25f, "Signature");
            witRow4.createCell(25f, "");
            witRow4.createCell(25f, "Signature");
            witRow4.createCell(25f, "");

            yPosition = table7.draw() - 15;



            // HUF Declaration
            // H.U.F. declaration with text wrapping
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("H.U.F. declaration :");
            float hufX = leftMargin + PDType1Font.HELVETICA_BOLD.getStringWidth("H.U.F. declaration :") / 1000f * 9;
            contentStream.endText();
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(hufX, yPosition);
            contentStream.showText("(mandatory, if applicant is Karta of HUF)");
            contentStream.endText();
            yPosition -= 15;

            // HUF declaration text with wrapping
            String hufText1 = "I,_______________________________,residing at the address given against First Applicant, do solemnly affirm that I am the Karta of the Hindu Undivided Family and as such have full powers to deal in the Floating Rate Savings Bonds 2020 (Taxable), standing in the name of the HUF.";
            String hufText2 = "Specimen signature for and on behalf of the HUF (name of the HUF)_________________________";

            float hufAvailableWidth = contentWidth; // Use full content width for HUF text

            java.util.List<String> hufLines1 = wrapText(hufText1, PDType1Font.HELVETICA, 9, hufAvailableWidth);
            java.util.List<String> hufLines2 = wrapText(hufText2, PDType1Font.HELVETICA, 9, hufAvailableWidth);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);

            for (int i = 0; i < hufLines1.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                }
                contentStream.showText(hufLines1.get(i));
                yPosition -= 15;
                if (i < hufLines1.size() - 1 && yPosition >= bottomMargin + 20) {
                    contentStream.newLineAtOffset(0, -15);
                }
            }

            for (int i = 0; i < hufLines2.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                }
                contentStream.showText(hufLines2.get(i));
                yPosition -= 15;
                if (i < hufLines2.size() - 1 && yPosition >= bottomMargin + 20) {
                    contentStream.newLineAtOffset(0, -15);
                }
            }
            contentStream.endText();
            yPosition -= 15;

            // HUF Signature table (right aligned)
            BaseTable table12 = new BaseTable(
                    yPosition,
                    bottomMargin,
                    bottomMargin,
                    contentWidth * 0.5f,
                    pageWidth - rightMargin - (contentWidth * 0.5f),
                    document,
                    page,
                    true,
                    true
            );

            Row<PDPage> hufSigRow = table12.createRow(60);

            Cell<PDPage> hufCell = hufSigRow.createCell(
                    100f,
                    "(Signature of the Karta with seal of HUF)"
            );

            hufCell.setTopPadding(45);      // replaces the 5 newline spacing
            hufCell.setLeftPadding(80);     // for right alignment spacing
            hufCell.setFont(PDType1Font.HELVETICA);

            yPosition = table12.draw() - 15;


            // Date and Place
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Date :   " + (bondDate != null ? bondDate : "") + "                              Place :");
            contentStream.endText();
            yPosition -= 25;

            // Check for page break before nomination form
            if (yPosition < bottomMargin + 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // Annexure header (right aligned)
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            float annexureTextWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Annexure 4. Form - C") / 1000f * 15;
            contentStream.newLineAtOffset(pageWidth - rightMargin - annexureTextWidth, yPosition);
            contentStream.showText("Annexure 4. Form - C");
            contentStream.endText();
            yPosition -= 30;

            // Nomination Form header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            float nomTitleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Floating Rate Savings Bonds, 2020 (Taxable)") / 1000f * 15;
            contentStream.newLineAtOffset((pageWidth - nomTitleWidth) / 2, yPosition);
            contentStream.showText("Floating Rate Savings Bonds, 2020 (Taxable)");
            contentStream.endText();
            yPosition -= 20;

            float nomFormWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("FORM OF NOMINATION") / 1000f * 15;
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            contentStream.newLineAtOffset((pageWidth - nomFormWidth) / 2, yPosition);
            contentStream.showText("FORM OF NOMINATION");
            contentStream.endText();
            yPosition -= 25;

            float paraWidth = PDType1Font.HELVETICA.getStringWidth("[See Para 11(i)]") / 1000f * 9;
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset((pageWidth - paraWidth) / 2, yPosition);
            contentStream.showText("[See Para 11(i)]");
            contentStream.endText();
            yPosition -= 25;

            // Nomination instructions with text wrapping
            String nomInstText1 = "Instructions ±The Nomination and its Cancellation shall be governed by the provisions of the Government Securities Act, 2006 (38 of 2006) and the Government Securities Regulation, 2007, published in Part III, Section 4 of the Gazette of India dated December 1, 2007.";
            // Combine nomInstText2 and nomInstText3 into one continuous text for proper wrapping
            // Ensure proper spacing and all text parts are included
            String namePart = (nameInFullOne != null && !nameInFullOne.trim().isEmpty()) ? nameInFullOne.trim() + " " : "";
            String nomInstText2And3 = "I/We " + namePart + "(Name and address), the holder/s of BLA No ____________________ for the investment in Floating Rate Savings Bonds, 2020 (Taxable) hereby nominate the following person/s who shall on my/our death have the right to all the investments under the BLA / investments specified in the table below, to receive payment of the interest/amount for the time being due on the said BLA / investments.";

            java.util.List<String> nomInstLines1 = wrapText(nomInstText1, PDType1Font.HELVETICA, 9, contentWidth);
            java.util.List<String> nomInstLines2And3 = wrapText(nomInstText2And3, PDType1Font.HELVETICA, 9, contentWidth);

            // Display first instruction paragraph
            for (int i = 0; i < nomInstLines1.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                }
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 9);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText(nomInstLines1.get(i));
                contentStream.endText();
                yPosition -= 15;
            }

            // Display second instruction paragraph (with name)
            for (int i = 0; i < nomInstLines2And3.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                }
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 9);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText(nomInstLines2And3.get(i));
                contentStream.endText();
                yPosition -= 15;
            }
            yPosition -= 5;


            // Nomination table
            BaseTable table8 = new BaseTable(yPosition, bottomMargin, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> nomHeaderRow = table8.createRow(25);
            nomHeaderRow.createCell(37.5f, "PARTICULARS OF INVESTMENT");
            nomHeaderRow.createCell(62.5f, "PARTICULARS OF NOMINEE");

            be.quodlibet.boxable.Row<PDPage> nomSubHeaderRow = table8.createRow(20);
            nomSubHeaderRow.createCell(12.5f, "Date of Issue");
            nomSubHeaderRow.createCell(12.5f, "Amount");
            nomSubHeaderRow.createCell(12.5f, "Date Of Repayment");
            nomSubHeaderRow.createCell(12.5f, "Full name with expanded initials and address of the nominee");
            nomSubHeaderRow.createCell(12.5f, "Date of birth");
            nomSubHeaderRow.createCell(12.5f, "Relationship to holder");
            nomSubHeaderRow.createCell(12.5f, "STATUS Resident / NRI");
            nomSubHeaderRow.createCell(12.5f, "Particulars of Bank Account");

            be.quodlibet.boxable.Row<PDPage> nomDataRow = table8.createRow(20);
            nomDataRow.createCell(12.5f, dateOfIssue != null ? dateOfIssue : "");
            nomDataRow.createCell(12.5f, amount != null ? amount : "");
            nomDataRow.createCell(12.5f, "");
            nomDataRow.createCell(12.5f, name != null ? name : "");
            nomDataRow.createCell(12.5f, "");
            nomDataRow.createCell(12.5f, relationWithHolder != null ? relationWithHolder : "");
            nomDataRow.createCell(12.5f, statusResident != null ? statusResident : "");
            nomDataRow.createCell(12.5f, "");

            yPosition = table8.draw() - 15;

            // Minor nominee text with text wrapping
            String minorNomText1 = "To be filled in case if nominee is minor: As________________________________________________________ the sole nominee above is a minor on this date, I/We appoint Shri/Smt./Kum _______________________________________to receive the amount for the time being due to the above";
            String minorNomText2 = "BLA/specified investment in the event of my/our death during the minority of the said nominee_____________________________________________";

            java.util.List<String> minorNomLines1 = wrapText(minorNomText1, PDType1Font.HELVETICA, 9, contentWidth);
            java.util.List<String> minorNomLines2 = wrapText(minorNomText2, PDType1Font.HELVETICA, 9, contentWidth);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);

            for (int i = 0; i < minorNomLines1.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                }
                contentStream.showText(minorNomLines1.get(i));
                yPosition -= 15;
                if (i < minorNomLines1.size() - 1 && yPosition >= bottomMargin + 20) {
                    contentStream.newLineAtOffset(0, -15);
                }
            }

            for (int i = 0; i < minorNomLines2.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                }
                contentStream.showText(minorNomLines2.get(i));
                yPosition -= 15;
                if (i < minorNomLines2.size() - 1 && yPosition >= bottomMargin + 20) {
                    contentStream.newLineAtOffset(0, -15);
                }
            }
            contentStream.endText();
            yPosition -= 5;

            // Substitution text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("If the nomination is in substitution of the one already made: ");
            float subX = leftMargin + PDType1Font.HELVETICA_BOLD.getStringWidth("If the nomination is in substitution of the one already made: ") / 1000f * 9;
            contentStream.endText();
            // Substitution text with wrapping
            float subAvailableWidth = pageWidth - subX - rightMargin;
            String subText = "This nomination is in substitution of the nomination dated_____________________ made by me/us and registered on your books at_____________________which shall stand cancelled on registration of this nomination.";
            java.util.List<String> subLines = wrapText(subText, PDType1Font.HELVETICA, 9, subAvailableWidth);

            if (!subLines.isEmpty()) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 9);
                contentStream.newLineAtOffset(subX, yPosition);

                for (int i = 0; i < subLines.size(); i++) {
                    if (yPosition < bottomMargin + 20) {
                        contentStream.endText();
                        contentStream.close();
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        yPosition = pageHeight - topMargin;
                        contentStream.beginText();
                        contentStream.setFont(PDType1Font.HELVETICA, 9);
                        contentStream.newLineAtOffset(subX, yPosition);
                    }
                    try {
                        contentStream.showText(subLines.get(i));
                    } catch (Exception e) {
                        // Handle encoding issues by replacing problematic characters
                        String safeText = subLines.get(i).replace("«", "_").replace("»", "_");
                        contentStream.showText(safeText);
                    }
                    yPosition -= 15;
                    if (i < subLines.size() - 1 && yPosition >= bottomMargin + 20) {
                        contentStream.newLineAtOffset(0, -15);
                    }
                }
                contentStream.endText();
            }
            yPosition -= 10;

            // Place and Date
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Place : NETBK                                    Date :  " + (bondDate != null ? bondDate : ""));
            contentStream.endText();
            yPosition -= 40;

            // BLA holder signature table
            BaseTable table13 = new BaseTable(
                    yPosition,
                    bottomMargin,
                    bottomMargin,
                    contentWidth * 0.65f,
                    leftMargin,
                    document,
                    page,
                    true,
                    true
            );

            Row<PDPage> blaSigRow = table13.createRow(60);

            Cell<PDPage> blaCell = blaSigRow.createCell(
                    99.9f,
                    "(Signature(s)/ Thumb impression of the BLA holder(s))"
            );

            blaCell.setTopPadding(45);     // replaces 5 newline space
            blaCell.setLeftPadding(20);    // small indent like your original text
            blaCell.setFont(PDType1Font.HELVETICA);

            yPosition = table13.draw() - 15;


            // Witnesses signature
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Signature with name and address of witnesses");
            yPosition -= 20;
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("1.____________________________");
            yPosition -= 20;
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("2.____________________________");
            contentStream.endText();
            yPosition -= 30;

            // Check for page break before acknowledgment
            if (yPosition < bottomMargin + 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // ACKNOWLEDGMENT header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            float ackWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("ACKNOWLEDGMENT") / 1000f * 15;
            contentStream.newLineAtOffset((pageWidth - ackWidth) / 2, yPosition);
            contentStream.showText("ACKNOWLEDGMENT");
            contentStream.endText();
            yPosition -= 30;

            // Acknowledgment text with text wrapping
            String ackText = "We acknowledge having registered the nomination made by _____________________________________ in respect of FRSB 2020 (T) in favour of________________________ (Name of nominees) for the BLA No____________________for the all the investments under the BLA / specified investment ________________________ and the nomination registration no. is_________________________. Please quote the above nomination registration no. in all communication pertaining to change or cancellation of nomination.";
            java.util.List<String> ackLines = wrapText(ackText, PDType1Font.HELVETICA, 9, contentWidth);

            for (int i = 0; i < ackLines.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    if (contentStream != null) {
                        try {
                            contentStream.close();
                        } catch (Exception e) {
                            // Ignore if already closed
                        }
                    }
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                }
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 9);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText(ackLines.get(i));
                contentStream.endText();
                yPosition -= 15;
            }
            yPosition -= 5;

            // Date line
            if (yPosition < bottomMargin + 20) {
                if (contentStream != null) {
                    try {
                        contentStream.close();
                    } catch (Exception e) {
                        // Ignore if already closed
                    }
                }
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Date :                ____________________________");
            contentStream.endText();
            yPosition -= 15;

            // Receiving Office line
            if (yPosition < bottomMargin + 20) {
                if (contentStream != null) {
                    try {
                        contentStream.close();
                    } catch (Exception e) {
                        // Ignore if already closed
                    }
                }
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Receiving Office : _________________________                 Authorize Official : _________________________");
            contentStream.endText();
            yPosition -= 15;

            // Branch line
            if (yPosition < bottomMargin + 20) {
                if (contentStream != null) {
                    try {
                        contentStream.close();
                    } catch (Exception e) {
                        // Ignore if already closed
                    }
                }
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Branch :             ____________________________");
            contentStream.endText();
            yPosition -= 30;

            // Check for page break before information section
            if (yPosition < bottomMargin + 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // Information for Investors header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            float infoWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("INFORMATION FOR INVESTORS IN Floating Rate Savings Bonds, 2020 (Taxable)") / 1000f * 15;
            contentStream.newLineAtOffset((pageWidth - infoWidth) / 2, yPosition);
            contentStream.showText("INFORMATION FOR INVESTORS IN Floating Rate Savings Bonds, 2020 (Taxable)");
            contentStream.endText();
            yPosition -= 30;

            // Introduction text with text wrapping
            String introText = "Government of India had introduced Floating Rate Savings Bonds, 2020 (Taxable)vide their notification No. F.No.4(10)-B (W&M)/2020 dated June 26, 2020. The main features of the Scheme are as under:";
            java.util.List<String> introLines = wrapText(introText, PDType1Font.HELVETICA, 10, contentWidth);

            for (int i = 0; i < introLines.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    if (contentStream != null) {
                        try {
                            contentStream.close();
                        } catch (Exception e) {
                            // Ignore if already closed
                        }
                    }
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                }
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText(introLines.get(i));
                contentStream.endText();
                yPosition -= 18;
            }
            yPosition -= 7;

            // Information table
            float yStartNewPage = pageHeight - topMargin;
            BaseTable table9 = new BaseTable(yPosition, yStartNewPage, bottomMargin, contentWidth, leftMargin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> infoHeaderRow = table9.createRow(20);
            infoHeaderRow.createCell(20f, "item");
            infoHeaderRow.createCell(40f, "Floating Rate Savings Bonds, 2020 (Taxable)");
            infoHeaderRow.createCell(40f, "Remarks");

            be.quodlibet.boxable.Row<PDPage> infoRow1 = table9.createRow(25);
            infoRow1.createCell(20f, "1. Category Of Invester");
            infoRow1.createCell(40f, "Resident Individual, HUF.");
            infoRow1.createCell(40f, "Non-Resident Indians (NRI)s are not eligible to invest in these bonds.");

            be.quodlibet.boxable.Row<PDPage> infoRow2 = table9.createRow(25);
            infoRow2.createCell(20f, "2. Limit Of Investment");
            infoRow2.createCell(40f, "Date of receipt of subscription in cash (up to `20,000/- only), or date of realization of cheque /draft/ funds.");
            infoRow2.createCell(40f, "No maximum limit");

            be.quodlibet.boxable.Row<PDPage> infoRow3 = table9.createRow(30);
            infoRow3.createCell(20f, "3. Date Of Issue Of Bonds");
            infoRow3.createCell(40f, "Date of receipt of subscription in cash (up to `20,000/- only), or date of realization of cheque /draft/ funds.");
            infoRow3.createCell(40f, "----");

            be.quodlibet.boxable.Row<PDPage> infoRow4 = table9.createRow(25);
            infoRow4.createCell(20f, "4. Forms Of Bonds");
            infoRow4.createCell(40f, "Electronic form held in the Bond Ledger Account.");
            infoRow4.createCell(40f, "Bond Ledger Account will be opened by the Receiving Office in the name of investor/s.");

            be.quodlibet.boxable.Row<PDPage> infoRow5 = table9.createRow(40);
            infoRow5.createCell(20f, "5. Interest");
            infoRow5.createCell(40f, "(i) Interest is payable semi-annually from the date of issue of bonds, up to 30th June / 31st December as the case may be, and thereafter half-yearly for period ending 30th June and 31st December on 1st July and 1st January respectively. (ii) The coupon rate payable for next half-year would be reset on 1st January 2021 and thereafter, every 1st July and 1st January.");
            infoRow5.createCell(40f, "Half-yearly interest is payable on 1st January / 1st July. The coupon on 1st January 2021 shall be paid at 7.15%.");

            be.quodlibet.boxable.Row<PDPage> infoRow6 = table9.createRow(25);
            infoRow6.createCell(20f, "6. Post Maturity Interest");
            infoRow6.createCell(40f, "Post Maturity Interest is not payable.");
            infoRow6.createCell(40f, "----");

            be.quodlibet.boxable.Row<PDPage> infoRow7 = table9.createRow(25);
            infoRow7.createCell(20f, "7. Bank account");
            infoRow7.createCell(40f, "It is mandatory for the investor/s to provide bank account details to facilitate payment of interest /maturity value directly to his/her/their bank account.");
            infoRow7.createCell(40f, "----");

            be.quodlibet.boxable.Row<PDPage> infoRow8 = table9.createRow(35);
            infoRow8.createCell(20f, "8. Tax benefits");
            infoRow8.createCell(40f, "Income from the bonds is taxable.");
            infoRow8.createCell(40f, "Tax will be deducted at source while interest is paid. If an exemption under the relevant provisions of the Income Tax Act,1961 is obtained, it may be declared in the Application Form.");

            be.quodlibet.boxable.Row<PDPage> infoRow9 = table9.createRow(30);
            infoRow9.createCell(20f, "9. Nomination Facility");
            infoRow9.createCell(40f, "The sole Holder or all the joint holders may nominate one or more persons as nominee in accordance with the provisions of the Government Securities Act, 2006 (38 of 2006) and the Government Securities Regulation, 2007, published in Part III, Section 4 of the Gazette of India dated December 1, 2007.");
            infoRow9.createCell(40f, "---");

            be.quodlibet.boxable.Row<PDPage> infoRow10 = table9.createRow(20);
            infoRow10.createCell(20f, "10. Maturity period");
            infoRow10.createCell(40f, "7 years from the date of issuance.");
            infoRow10.createCell(40f, "---");

            be.quodlibet.boxable.Row<PDPage> infoRow11 = table9.createRow(30);
            infoRow11.createCell(20f, "11. Premature redemption");
            infoRow11.createCell(40f, "Facility is available to the eligible investors after Lock in period of 4, 5, and 6 years in the age bracket of 80 years and above, between 70 to 80 years and 60 to 70 years respectively");
            infoRow11.createCell(40f, "Penalty charges @ 50% of last coupon payment");

            be.quodlibet.boxable.Row<PDPage> infoRow12 = table9.createRow(25);
            infoRow12.createCell(20f, "12. Transferability");
            infoRow12.createCell(40f, "Transferability is limited to nominee(s)/legal heir in case of death of holder.");
            infoRow12.createCell(40f, "Transferability is limited to nominee(s)/legal heir in case of death of holder.");

            be.quodlibet.boxable.Row<PDPage> infoRow13 = table9.createRow(25);
            infoRow13.createCell(20f, "13. Tradability / Advances");
            infoRow13.createCell(40f, "The bonds are not tradable in the secondary market and also not eligible as collateral for availing loans.");
            infoRow13.createCell(40f, "---");

            be.quodlibet.boxable.Row<PDPage> infoRow14 = table9.createRow(20);
            infoRow14.createCell(20f, "14. Application forms");
            infoRow14.createCell(40f, "Available at designated branches of SBI, 11 Nationalised Banks and 4 Private Sector Banks.");
            infoRow14.createCell(40f, "---");

            // Draw the table and get the final yPosition
            yPosition = table9.draw();

            // Close the contentStream that the table was using (if it created a new one)
            // The table.draw() may have created new pages and contentStreams
            // We need to ensure we have a valid contentStream for the next section
            if (contentStream != null) {
                try {
                    contentStream.close();
                } catch (Exception e) {
                    // Ignore if already closed
                }
            }

            // Get the last page from the document (table may have added new pages)
            page = document.getPage(document.getNumberOfPages() - 1);
            contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

            // Set yPosition with proper spacing after the table
            yPosition -= 40;

            // Check for page break before duties section - ensure enough space for the header
            if (yPosition < bottomMargin + 150) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // DUTIES OF INVESTOR/ APPLICANTS
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("DUTIES OF INVESTOR/ APPLICANTS");
            contentStream.endText();
            yPosition -= 25;

            // Duties text with automatic page breaks and text wrapping
            String[] dutiesTexts = {
                    "(A) Please complete the application in all respects.",
                    "(B) Incomplete applications are liable to result in delay of issue of the bonds (at the cost of the applicant).",
                    "(C) In case the application is submitted by a Power of Attorney (POA) holder, please submit original POA for verification, along with an attested copy for record.",
                    "(D) In case the application is on behalf of a minor, please submit the original birth certificate from the School or Municipal Authorities for Verification, together with an attested copy for record.",
                    "(E) Please note that nomination facility is available to a Sole Holder or all the joint holders (investors) of the bonds. F) In case nominee is a minor, please indicate the date of birth of the minor and a guardian can be appointed.",
                    "(G) Nomination facility is not available in case the investment is in the name of a minor.",
                    "(H) Please notify the change of address to Receiving Office immediately.",
                    "(I) POST MATURITY INTEREST IS NOT PAYABLE ON THESE BONDS. The interest and redemption proceeds will be credited on the due date as per bank details registered with us.",
                    "(J) Indicate your date of birth / age.",
                    "(K) Provide your correct bank account details for receiving payment through electronic mode. In case of closure/transfer of the bank account,the fresh details may be immediately intimated to the Receiving Office to avoid any inconvenience.",
                    "(L) Any information regarding tax applicability may be provided to the bank/branch."
            };

            for (String text : dutiesTexts) {
                // Wrap text into multiple lines based on content width
                java.util.List<String> wrappedLines = wrapText(text, PDType1Font.HELVETICA, 9, contentWidth);

                for (int i = 0; i < wrappedLines.size(); i++) {
                    // Check if we need a new page before each line
                    if (yPosition < bottomMargin + 20) {
                        // Close current contentStream if it exists
                        if (contentStream != null) {
                            try {
                                contentStream.close();
                            } catch (Exception e) {
                                // Ignore if already closed
                            }
                        }
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        yPosition = pageHeight - topMargin;
                    }
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                    contentStream.showText(wrappedLines.get(i));
                    contentStream.endText();
                    yPosition -= 15;
                }
            }
            yPosition -= 15;

            // Check for page break before RIGHTS section
            if (yPosition < bottomMargin + 100) {
                if (contentStream != null) {
                    try {
                        contentStream.close();
                    } catch (Exception e) {
                        // Ignore if already closed
                    }
                }
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // RIGHTS OF THE INVESTOR
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("RIGHTS OF THE INVESTOR");
            contentStream.endText();
            yPosition -= 25;

            // Rights text with automatic page breaks and text wrapping
            String[] rightsTexts = {
                    "(a) The Certificate of Holding will be issued in electronic form within 7 working days from the date of tender of application.",
                    "(b) The interest on the bond accrues from the date of receipt of funds/realization of cheque/draft and will be credited to the bank account of the holder directly, as per the details provided by him/her in the application form.",
                    "(c) The interest will be paid semi-annually and credited to the investors account every 01 January and 01 July with last installment on date of redemption.",
                    "(d) Maturity intimation advice will be issued one month before the due date of the bond.",
                    "(e) Application forms for investments, redemption, nomination etc., in respect of Savings Bonds shall be available on the websites of Receiving Offices.",
                    "(f) A sole holder or all the joint holders may nominate one or more nominees to the rights of the bonds.Nonresident Indians can also be nominated.",
                    "(g) The investor(s) can make separate nomination for each investment held under the BLA.",
                    "(h) The nomination will be registered at the Office of Issue and an acknowledgement of Registration will be issued to the holder.",
                    "(i) The nomination can be varied by registering a fresh nomination.",
                    "(j) The existing nomination can be cancelled by a request to the Office of Issue.",
                    "(k) The redemption is due on expiry of seven years from the date of investment, unless applied for premature redemption as applicable.",
                    "(l) Premature redemption facility is allowed with certain conditions.",
                    "(m) Investors are entitled for compensation for delayed payments at the applicable coupon rate."
            };

            for (String text : rightsTexts) {
                // Wrap text into multiple lines based on content width
                java.util.List<String> wrappedLines = wrapText(text, PDType1Font.HELVETICA, 9, contentWidth);

                for (int i = 0; i < wrappedLines.size(); i++) {
                    // Check if we need a new page before each line
                    if (yPosition < bottomMargin + 20) {
                        // Close current contentStream if it exists
                        if (contentStream != null) {
                            try {
                                contentStream.close();
                            } catch (Exception e) {
                                // Ignore if already closed
                            }
                        }
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        yPosition = pageHeight - topMargin;
                    }
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                    contentStream.showText(wrappedLines.get(i));
                    contentStream.endText();
                    yPosition -= 15;
                }
            }
            yPosition -= 15;

            // Check for page break before Complaint text
            if (yPosition < bottomMargin + 100) {
                if (contentStream != null) {
                    try {
                        contentStream.close();
                    } catch (Exception e) {
                        // Ignore if already closed
                    }
                }
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // Complaint text with page break check and text wrapping

            String complaintText = "In case the issuing bank does not comply with the above, you may lodge a complaint in writing in the form provided at the counter of the bank and address the same to the nearest office of Reserve Bank of India, as under";
            java.util.List<String> complaintLines = wrapText(complaintText, PDType1Font.HELVETICA, 9, contentWidth);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);

            for (int i = 0; i < complaintLines.size(); i++) {
                String line = complaintLines.get(i);
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                }
                contentStream.showText(line);
                yPosition -= 15;
                if (yPosition >= bottomMargin + 20 && i < complaintLines.size() - 1) {
                    contentStream.newLineAtOffset(0, -15);
                }
            }
            contentStream.endText();
            yPosition -= 10;

            // RBI address with automatic page breaks
            String[] rbiAddressLines = {
                    "THE REGIONAL DIRECTOR,",
                    "RESERVE BANK OF INDIA,",
                    "CONSUMER EDUCATION AND PROTECTION DEPARTMENT/ BANKING OMBUDSMAN  (LOCATION)",
                    "----------------------------------------------------------------------------",
                    "You may also address your complaint to:",
                    "THE CHIEF GENERAL MANAGER",
                    "INTERNAL DEBT MANAGEMENT DEPARTMENT",
                    "RESERVE BANK OF INDIA, 23rd Floor",
                    "CENTRAL OFFICE, Shahid Bhagat Singh Marg,",
                    "MUMBAI-400 001",
                    "MAHARASHTRA",
                    "E- mail ID ±cgmidmd@rbi.org.in"
            };

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);

            for (int i = 0; i < rbiAddressLines.length; i++) {
                String line = rbiAddressLines[i];
                // Check if we need a new page before each line
                if (yPosition < bottomMargin + 20) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(leftMargin, yPosition);
                }
                contentStream.showText(line);
                yPosition -= (i == 3 || i == 4) ? 20 : 15; // Extra space after separator and "You may also"
                if (yPosition >= bottomMargin + 20 && i < rbiAddressLines.length - 1) {
                    contentStream.newLineAtOffset(0, (i == 3 || i == 4) ? -20 : -15);
                }
            }
            contentStream.endText();
            yPosition -= 10;

            // Disclaimer with text wrapping
            String disclaimerText = "Disclaimer: I have read and understood the details of information for the investors as well as rights and duties of investors. The agent/bank has explained the features of the scheme to me.";
            java.util.List<String> disclaimerLines = wrapText(disclaimerText, PDType1Font.HELVETICA, 9, contentWidth);

            for (int i = 0; i < disclaimerLines.size(); i++) {
                if (yPosition < bottomMargin + 20) {
                    if (contentStream != null) {
                        try {
                            contentStream.close();
                        } catch (Exception e) {
                            // Ignore if already closed
                        }
                    }
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = pageHeight - topMargin;
                }
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 9);
                contentStream.newLineAtOffset(leftMargin, yPosition);
                contentStream.showText(disclaimerLines.get(i));
                contentStream.endText();
                yPosition -= 15;
            }
            yPosition -= 15;

            // Applicant signature table
            BaseTable table14 = new BaseTable(
                    yPosition,
                    bottomMargin,
                    bottomMargin,
                    contentWidth * 0.50f,
                    leftMargin,
                    document,
                    page,
                    true,
                    true
            );

            be.quodlibet.boxable.Row<PDPage> appSigRow = table14.createRow(60);
            appSigRow.createCell(
                    100f,
                    "Signature/s of the applicant/s"
            );

            yPosition = table14.draw() - 20;


            // Check for page break before acknowledgment
            if (yPosition < bottomMargin + 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // Acknowledgement header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Acknowledgement of Application Form");
            contentStream.endText();
            yPosition -= 25;

            // Application Number (centered)
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            float appNumWidth = PDType1Font.HELVETICA.getStringWidth("Application Number :") / 1000f * 10;
            contentStream.newLineAtOffset((pageWidth - appNumWidth) / 2, yPosition);
            contentStream.showText("Application Number :");
            contentStream.endText();
            yPosition -= 25;

            // Receipt text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Bank branch.................................................................................................. Date ............/............../......................");
            yPosition -= 15;
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Received from Mr/Mrs/Ms.........................................................................................................................Cash/Draft/Pay");
            yPosition -= 15;
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Order/Cheque No./electronic credit ««««««««««««««««««Dated «««««««««««««");
            yPosition -= 15;
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("(Bank and branch)««««««««««««««««««««««««««««««««««««« for««««««(Rupees«««««««««««««««««««");
            yPosition -= 15;
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Savings Bonds, 2020 (Taxable) for a period of 7 years, of the nominal value of «««««");
            yPosition -= 15;
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("(Rupees«««««««««««««««««««««««««««««");
            yPosition -= 15;
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("in the form of Bond Ledger Account.");
            contentStream.endText();
            yPosition -= 25;

            // Check if we have enough space for the signature table and note
            if (yPosition < bottomMargin + 120) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // Bank official signature table (right aligned)
            float yStartNewPage15 = pageHeight - topMargin;
            BaseTable table15 = new BaseTable(
                    yPosition,
                    yStartNewPage15,
                    bottomMargin,
                    contentWidth * 0.60f,
                    pageWidth - rightMargin - (contentWidth * 0.60f),
                    document,
                    page,
                    true,
                    true
            );

            be.quodlibet.boxable.Row<PDPage> bankSigRow = table15.createRow(60);
            bankSigRow.createCell(
                    100f,
                    "Date, Bank Stamp & Signature of the authorized official of the Receiving Office"
            );

            // Draw the table and get the final yPosition
            yPosition = table15.draw();

            // Close the contentStream that the table was using
            if (contentStream != null) {
                try {
                    contentStream.close();
                } catch (Exception e) {
                    // Ignore if already closed
                }
            }

            // Get the last page from the document (table may have added new pages)
            page = document.getPage(document.getNumberOfPages() - 1);
            contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

            // Set yPosition with proper spacing after the table
            yPosition -= 20;

            // Check if we have enough space for the note
            if (yPosition < bottomMargin + 50) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = pageHeight - topMargin;
            }

            // Final note
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(leftMargin, yPosition);
            contentStream.showText("Note : (i) This receipt is valid subject to realisation of the payment instrument.");
            yPosition -= 15;
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("(ii) The \"Certificate of Holding\" will be issued in electronic form by __________________________ (date).");
            contentStream.endText();

            // Close content stream and save document
            contentStream.close();
            document.save(byteArrayOutputStream);
            document.close();
            System.out.println("PDF with table created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}

