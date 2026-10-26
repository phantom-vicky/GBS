package com.isl.gbm.user.management.manager;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.datatable.DataTable;
import com.google.common.io.Files;
import com.isl.gbm.user.management.dto.AdminActivityReportDto;
import com.isl.gbm.user.management.dto.LoginReportDto;
import com.isl.gbm.user.management.dto.ReportSearchCriteriaDto;
import com.isl.gbm.user.management.dto.ReportType;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ReportExportBoxableManager {

    private static final String FROM_DATE = "From : ";

    private static final String TO_DATE = "To : ";

    private static final int FONT_SIZE = 12;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private PDRectangle mediabox;

    private float startTitle;

    private void init(PDDocument doc, PDPage page, ReportType reportType) {
        if (reportType.getId() == 2 || reportType.getId() == 3) {
            page.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        } else {
            page.setMediaBox(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
        }
        doc.addPage(page);
        mediabox = page.getMediaBox();
    }

    private void drawTitle(String title, PDPageContentStream contentStream, PDPage page) {
        try {
            float margin = 35;
            PDFont titleFont = PDType1Font.HELVETICA_BOLD;
            contentStream.setFont(titleFont, 12);
            float titleWidth = titleFont.getStringWidth(title) / 1000 * FONT_SIZE;
            startTitle = (page.getMediaBox().getWidth() - titleWidth) / 2;
            float startY = mediabox.getUpperRightY() - margin;
            contentStream.newLineAtOffset(startTitle, startY);
            contentStream.showText(title);
        } catch (IOException e) {

        }

    }

    public void exportUnsuccessfulLoginAtemptsReport(List<LoginReportDto> loginReports, Date fromDate, Date toDate, HttpServletResponse response) {
        try (PDDocument doc = new PDDocument();) {
            PDPage page = new PDPage();
            this.init(doc, page, ReportType.UNSUCCESSFUL_LOGIN);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            // Begin the Content stream
            contentStream.beginText();
            String title = "Report On Unsuccessful Login Attempts";
            drawTitle(title, contentStream, page);
            float leading = 1.5f * FONT_SIZE;
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(-startTitle + 10, -leading);
            contentStream.showText(FROM_DATE + " " + dateFormat.format(fromDate));
            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText(TO_DATE + " " + dateFormat.format(toDate));
            contentStream.newLineAtOffset(0, -leading);

            // Ending the content stream
            contentStream.endText();

            // Closing the content stream
            contentStream.close();

            this.generateTable(ReportType.UNSUCCESSFUL_LOGIN, loginReports, response, page, doc);
        } catch (Exception e) {

        }

    }

    public void exportLoginReport(List<LoginReportDto> loginReports, ReportSearchCriteriaDto reportSearchCriteriaDto,
                                  HttpServletResponse response) {
        try (PDDocument doc = new PDDocument();) {
            PDPage page = new PDPage();
            this.init(doc, page, ReportType.LOGIN_LOGOUT);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            // Begin the Content stream
            contentStream.beginText();
            String title = "Report On User Sign On/Sign Off";
            drawTitle(title, contentStream, page);
            float leading = 1.5f * FONT_SIZE;
            contentStream.setFont(PDType1Font.HELVETICA, 12);

            contentStream.newLineAtOffset(-startTitle + 10, -leading);
            contentStream.showText("Sol Id : " + reportSearchCriteriaDto.getSolId());

            contentStream.newLineAtOffset(startTitle + 300, 0);
            contentStream.showText(FROM_DATE + " " + dateFormat.format(reportSearchCriteriaDto.getFromDate()));

            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText(TO_DATE + " " + dateFormat.format(reportSearchCriteriaDto.getToDate()));

            contentStream.newLineAtOffset(-startTitle - 300, 0);
            contentStream.showText("Username : " + reportSearchCriteriaDto.getUsername());

            // Ending the content stream
            contentStream.endText();

            // Closing the content stream
            contentStream.close();

            this.generateTable(ReportType.LOGIN_LOGOUT, loginReports, response, page, doc);

        } catch (IOException e) {

        }
    }

    public void exportDormantUsersReport(List<LoginReportDto> loginReports, Date fromDate, Date toDate,
                                         HttpServletResponse response) {
        try (PDDocument doc = new PDDocument();) {
            PDPage page = new PDPage();
            this.init(doc, page, ReportType.DORMANT);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            // Begin the Content stream
            contentStream.beginText();
            String title = "Report On Dormant User-IDs";
            drawTitle(title, contentStream, page);
            float leading = 1.5f * FONT_SIZE;
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(-startTitle + 10, -leading);
            contentStream.showText(FROM_DATE + " " + dateFormat.format(fromDate));
            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText(TO_DATE + " " + dateFormat.format(toDate));
            contentStream.newLineAtOffset(0, -leading);

            // Ending the content stream
            contentStream.endText();

            // Closing the content stream
            contentStream.close();

            this.generateTable(ReportType.DORMANT, loginReports, response, page, doc);

        } catch (IOException e) {

        }

    }

    public void exportMultipleDeviceLogin(List<LoginReportDto> loginReports, String solId, Date date,
                                          HttpServletResponse response) {
        try (PDDocument doc = new PDDocument();) {
            PDPage page = new PDPage();
            this.init(doc, page, ReportType.MULTIPLE_IP_LOGIN);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            // Begin the Content stream
            contentStream.beginText();
            String title = "Report On Users Logged In From More Than One IP Address During The Day";
            drawTitle(title, contentStream, page);
            float leading = 1.5f * FONT_SIZE;
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(-startTitle + 10, -leading);
            contentStream.showText("Sol Id : " + solId);
            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText("Date : " + dateFormat.format(date));
            contentStream.newLineAtOffset(0, -leading);

            // Ending the content stream
            contentStream.endText();

            // Closing the content stream
            contentStream.close();

            this.generateTable(ReportType.MULTIPLE_IP_LOGIN, loginReports, response, page, doc);

        } catch (IOException e) {

        }
    }

    public void exportAdminActivitiesReport(List<AdminActivityReportDto> adminActivityReportDtos, Date date,
                                            HttpServletResponse response) {
        try (PDDocument doc = new PDDocument();) {
            PDPage page = new PDPage();
            this.init(doc, page, ReportType.ADMIN_ACTIVITIES);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            // Begin the Content stream
            contentStream.beginText();
            String title = "Report On List Of User Related Activities Carried Out On a Day";
            drawTitle(title, contentStream, page);
            float leading = 1.5f * FONT_SIZE;
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(-startTitle + 10, -leading);

            contentStream.showText("Date : " + dateFormat.format(date));
            contentStream.newLineAtOffset(0, -leading);

            // Ending the content stream
            contentStream.endText();

            // Closing the content stream
            contentStream.close();

            this.generateAdminActivitiesReportTableValues(adminActivityReportDtos, response, page, doc);

        } catch (IOException e) {

        }

    }

    private PDDocument generateAdminActivitiesReportTableValues(List<AdminActivityReportDto> adminActivityReportDtos,
                                                                HttpServletResponse response, PDPage page, PDDocument doc) throws IOException {
        // Initialize table
        float margin = 10;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin) - 60;
        float yStart = yStartNewPage;
        float bottomMargin = 0;

        // Create the data
        @SuppressWarnings("rawtypes")
        List<List> data = new ArrayList<>();
        data.add(new ArrayList<>(
                Arrays.asList("S. No", "User Id", "Username", "Emp Id", "Emp Name", "Activities carried out")));

        int i = 0;

        for (AdminActivityReportDto adminActivityReportDto : adminActivityReportDtos) {
            data.add(new ArrayList<>(Arrays.asList(++i,
                    adminActivityReportDto.getUserId() == null ? "" : adminActivityReportDto.getUserId(),
                    adminActivityReportDto.getUsername() == null ? "" : adminActivityReportDto.getUsername(),
                    adminActivityReportDto.getEmployeeCode() == null ? "" : adminActivityReportDto.getEmployeeCode(),
                    adminActivityReportDto.getFirstName() + " " + adminActivityReportDto.getLastName(),
                    adminActivityReportDto.getActivity() == null ? "" : adminActivityReportDto.getActivity())));
        }

        BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true,
                true);
        DataTable t = new DataTable(dataTable, page);
        t.addListToTable(data, DataTable.HASHEADER);
        dataTable.draw();
        return this.generateReport(doc, response);
    }

    public PDDocument generateTable(ReportType reportType, List<LoginReportDto> loginReports, HttpServletResponse response, PDPage page, PDDocument doc) throws IOException {
        // Initialize table
        float margin = 10;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin) - 60;
        float yStart = yStartNewPage;
        float bottomMargin = 0;

        BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, true);
        DataTable t = new DataTable(dataTable, page);
        t.addListToTable(getData(reportType, loginReports), DataTable.HASHEADER);
        dataTable.draw();
        return this.generateReport(doc, response);
    }

    @SuppressWarnings("rawtypes")
    private List<List> getData(ReportType reportType, List<LoginReportDto> loginReports) {    //	NOSONAR
        SimpleDateFormat tableDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        // Create the data
        List<List> data = this.getTableHeaders(reportType);

        int i = 0;

        if (reportType.getId() == 2 || reportType.getId() == 3) {
            for (LoginReportDto loginReportDto : loginReports) {
                data.add(new ArrayList<>(
                        Arrays.asList(++i, loginReportDto.getUserId() == null ? "" : loginReportDto.getUserId(),
                                loginReportDto.getUsername() == null ? "" : loginReportDto.getUsername(),
                                loginReportDto.getEmployeeCode() == null ? "" : loginReportDto.getEmployeeCode(),
                                loginReportDto.getFirstName() + " " + loginReportDto.getLastName(),
                                loginReportDto.getSolId() == null ? "" : loginReportDto.getSolId(),
                                loginReportDto.getLoginDate() == null ? ""
                                        : tableDateFormat.format(loginReportDto.getLoginDate()),
                                loginReportDto.getLogoutDate() == null ? ""
                                        : tableDateFormat.format(loginReportDto.getLogoutDate()),
                                loginReportDto.getIp() == null ? "" : loginReportDto.getIp())));
            }
        } else {
            for (LoginReportDto loginReportDto : loginReports) {
                data.add(new ArrayList<>(
                        Arrays.asList(++i, loginReportDto.getUserId() == null ? "" : loginReportDto.getUserId(),
                                loginReportDto.getUsername() == null ? "" : loginReportDto.getUsername(),
                                loginReportDto.getEmployeeCode() == null ? "" : loginReportDto.getEmployeeCode(),
                                loginReportDto.getFirstName() + " " + loginReportDto.getLastName(),
                                loginReportDto.getSolId() == null ? "" : loginReportDto.getSolId(),
                                loginReportDto.getLoginDate() == null ? ""
                                        : tableDateFormat.format(loginReportDto.getLoginDate()),
                                loginReportDto.getIp() == null ? "" : loginReportDto.getIp())));
            }
        }

        return data;
    }

    @SuppressWarnings("rawtypes")
    private List<List> getTableHeaders(ReportType reportType) {
        List<List> data = new ArrayList<>();
        if (reportType.getId() == 2 || reportType.getId() == 3) {
            data.add(new ArrayList<>(Arrays.asList("S. No", "User Id", "Username", "Emp Id", "Emp Name", "Branch Id",
                    "Date of Login", "Date of Logout", "IP Address")));
        } else {
            data.add(new ArrayList<>(Arrays.asList("S. No", "User Id", "Username", "Emp Id", "Emp Name", "Branch Id",
                    reportType.getId() == 4 ? "Date of Last Login" : "Date of Login", "IP Address")));
        }
        return data;
    }

    private PDDocument generateReport(PDDocument doc, HttpServletResponse response) throws IOException {

        File file = new File("target/" + System.currentTimeMillis() + ".pdf");
        Files.createParentDirs(file);
        doc.save(file);
        doc.close();
        try (InputStream is = new FileInputStream(file);) {
            OutputStream oos = response.getOutputStream();
            int c = 0;
            byte[] buf = new byte[8192];
            while ((c = is.read(buf, 0, buf.length)) > 0) {
                oos.write(buf, 0, c);
                oos.flush();
            }
        } catch (IOException e) {

        }

        return doc;
    }

}
