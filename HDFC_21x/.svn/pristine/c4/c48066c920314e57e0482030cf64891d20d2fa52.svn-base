package com.isl.gbm.user.management.manager;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.line.LineStyle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import com.isl.gbm.user.management.dto.AdminActivityReportDto;
import com.isl.gbm.user.management.dto.LoginReportDto;
import com.isl.gbm.user.management.dto.ReportSearchCriteriaDto;
import com.isl.gbm.user.management.dto.ReportType;

import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ReportExportManager converted from iText -> PDFBox + Boxable
 */
@Component
public class ReportExportManager {

    private static final String TABLE_HEADER = "tableHeader";
    private static final String TABLE_VALUE = "tableValue";

    private static final String FROM_DATE = "From Date  : ";
    private static final String TO_DATE = "To Date    : ";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat tableDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    // If you have a bankName field in your original class, keep it; placeholder here:
    private String bankName = "BANK NAME"; // replace or wire via @Value if needed

    /* Helper: draw centered title near top */
    private void drawTitle(PDPageContentStream cs, PDPage page, String title, float y, float fontSize) throws IOException {
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
        // center text
        float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(title) / 1000 * fontSize;
        float startX = (page.getMediaBox().getWidth() - titleWidth) / 2;
        cs.newLineAtOffset(startX, y);
        cs.showText(title);
        cs.endText();
    }

    /* Builds a Boxable table header row given headers and relative widths */
    private Row<PDPage> buildHeaderRow(BaseTable table, String[] headers, float[] relWidths, float headerHeight) {
        Row<PDPage> headerRow = table.createRow(headerHeight);
        float sum = 0f;
        for (float w : relWidths) sum += w;
        for (int i = 0; i < headers.length; ++i) {
            float pct = (relWidths[i] / sum) * 100f;
            headerRow.createCell(pct, headers[i]);
        }
        return headerRow;
    }

    /* Builds a data row with same widths (text array must match headers length) */
    private void addDataRow(BaseTable table, String[] values, float[] relWidths, float rowHeight) {
        Row<PDPage> row = table.createRow(rowHeight);
        float sum = 0f;
        for (float w : relWidths) sum += w;
        for (int i = 0; i < values.length; ++i) {
            float pct = (relWidths[i] / sum) * 100f;
            row.createCell(pct, values[i] == null ? "" : values[i]);
        }
    }

    /*******************************
     * Public export methods below *
     *******************************/

    public void exportUnsuccessfulLoginAtemptsReport(List<LoginReportDto> loginReports, Date fromDate, Date toDate, HttpServletResponse response) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - 60;
            float bottomMargin = 50;
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

            // Draw report title and date range
            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                drawTitle(cs, page, "Report On Unsuccessful Login Attempts", yStart, 14);

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin, yStart - 25);
                cs.showText(FROM_DATE + dateFormat.format(fromDate));
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin + 250, yStart - 25);
                cs.showText(TO_DATE + dateFormat.format(toDate));
                cs.endText();
            }

            // Configure columns
            ReportType t = ReportType.UNSUCCESSFUL_LOGIN;
            int cols = (t.getId() == 2 || t.getId() == 3) ? 9 : 8;

            String[] headers;
            float[] relWidths;

            if (cols == 9) {
                headers = new String[] {
                        "S. No", "User Id", "Username", "Emp Id", "Emp Name",
                        "Branch Id", "Date of Login", "Date of Logout", "IP Address"
                };
                relWidths = new float[] {0.5f, 0.8f, 1f, 0.8f, 1.0f, 0.8f, 1.2f, 1.2f, 0.8f};
            } else {
                headers = new String[] {
                        "S. No", "User Id", "Username", "Emp Id", "Emp Name",
                        "Branch Id", "Date of Login", "IP Address"
                };
                relWidths = new float[] {0.5f, 0.8f, 1f, 0.8f, 1.0f, 0.8f, 1.2f, 0.8f};
            }

            // Create table
            BaseTable table = new BaseTable(yStart - 60, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

            // Add header
            buildHeaderRow(table, headers, relWidths, 18);
            // ✅ Removed redundant table.addHeaderRow(...)

            // Populate table rows
            int count = 1;
            for (LoginReportDto dto : loginReports) {
                String loginDate = dto.getLoginDate() != null ? tableDateFormat.format(dto.getLoginDate()) : "";
                String logoutDate = dto.getLogoutDate() != null ? tableDateFormat.format(dto.getLogoutDate()) : "NA";

                if (cols == 9) {
                    addDataRow(table, new String[] {
                            String.valueOf(count++),
                            String.valueOf(dto.getUserId()),
                            dto.getUsername(),
                            dto.getEmployeeCode(),
                            dto.getFirstName() + " " + dto.getLastName(),
                            dto.getSolId(),
                            loginDate,
                            logoutDate,
                            dto.getIp()
                    }, relWidths, 16);
                } else {
                    addDataRow(table, new String[] {
                            String.valueOf(count++),
                            String.valueOf(dto.getUserId()),
                            dto.getUsername(),
                            dto.getEmployeeCode(),
                            dto.getFirstName() + " " + dto.getLastName(),
                            dto.getSolId(),
                            loginDate,
                            dto.getIp()
                    }, relWidths, 16);
                }
            }

            table.draw();

            // Output PDF
            response.setContentType("application/pdf");
            document.save(response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace(); // Consider proper logging instead
        }
    }

    public void exportLoginReport(List<LoginReportDto> loginReports,
                                  ReportSearchCriteriaDto reportSearchCriteriaDto,
                                  HttpServletResponse response) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - 60;
            float bottomMargin = 50;
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

            // Draw report title and metadata (SolId, Username, From/To Dates)
            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                drawTitle(cs, page, "Report On User Sign On/Sign Off", yStart, 14);

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin, yStart - 25);
                cs.showText("Sol Id : " + reportSearchCriteriaDto.getSolId());
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin + 250, yStart - 25);
                cs.showText("Username : " + reportSearchCriteriaDto.getUsername());
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin, yStart - 40);
                cs.showText(FROM_DATE + dateFormat.format(reportSearchCriteriaDto.getFromDate()));
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin + 250, yStart - 40);
                cs.showText(TO_DATE + dateFormat.format(reportSearchCriteriaDto.getToDate()));
                cs.endText();
            }

            // Define table structure
            ReportType t = ReportType.LOGIN_LOGOUT;
            int cols = (t.getId() == 2 || t.getId() == 3) ? 9 : 8;

            String[] headers;
            float[] relWidths;
            if (cols == 9) {
                headers = new String[] {
                        "S. No", "User Id", "Username", "Emp Id", "Emp Name",
                        "Branch Id", "Date of Login", "Date of Logout", "IP Address"
                };
                relWidths = new float[] {0.5f, 0.8f, 1f, 0.8f, 1.0f, 0.8f, 1.2f, 1.2f, 0.8f};
            } else {
                headers = new String[] {
                        "S. No", "User Id", "Username", "Emp Id", "Emp Name",
                        "Branch Id", "Date of Login", "IP Address"
                };
                relWidths = new float[] {0.5f, 0.8f, 1f, 0.8f, 1.0f, 0.8f, 1.2f, 0.8f};
            }

            // Create table
            BaseTable table = new BaseTable(yStart - 80, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

            // Build the header row (no need to addHeaderRow())
            buildHeaderRow(table, headers, relWidths, 18);

            // Add data rows
            int count = 1;
            for (LoginReportDto dto : loginReports) {
                String loginDate = dto.getLoginDate() != null ? tableDateFormat.format(dto.getLoginDate()) : "";
                String logoutDate = dto.getLogoutDate() != null ? tableDateFormat.format(dto.getLogoutDate()) : "NA";

                if (cols == 9) {
                    addDataRow(table, new String[]{
                            String.valueOf(count++),
                            String.valueOf(dto.getUserId()),
                            dto.getUsername(),
                            dto.getEmployeeCode(),
                            dto.getFirstName() + " " + dto.getLastName(),
                            dto.getSolId(),
                            loginDate,
                            logoutDate,
                            dto.getIp()
                    }, relWidths, 16);
                } else {
                    addDataRow(table, new String[]{
                            String.valueOf(count++),
                            String.valueOf(dto.getUserId()),
                            dto.getUsername(),
                            dto.getEmployeeCode(),
                            dto.getFirstName() + " " + dto.getLastName(),
                            dto.getSolId(),
                            loginDate,
                            dto.getIp()
                    }, relWidths, 16);
                }
            }

            table.draw();
            response.setContentType("application/pdf");
            document.save(response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace(); // better than swallowing silently
        }
    }


    public void exportMultipleDeviceLogin(List<LoginReportDto> loginReportDtos, String solId, Date date, HttpServletResponse response) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat tableDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - 50;
            float bottomMargin = 50;
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

            // --- Title & SolId / Date Info ---
            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
                cs.newLineAtOffset(margin, yStart);
                cs.showText("Report On Users Logged In From More Than One IP Address During The Day");
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin, yStart - 25);
                cs.showText("Sol Id : " + safe(solId));
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin + 250, yStart - 25);
                cs.showText("Date : " + dateFormat.format(date));
                cs.endText();
            }

            // --- Table Header Configuration ---
            ReportType t = ReportType.MULTIPLE_IP_LOGIN;
            int cols = (t.getId() == 2 || t.getId() == 3) ? 9 : 8;

            String[] headers;
            float[] relWidths;

            if (cols == 9) {
                headers = new String[]{
                        "S. No", "User Id", "Username", "Emp Id", "Emp Name",
                        "Branch Id", "Date of Login", "Date of Logout", "IP Address"
                };
                relWidths = new float[]{0.5f, 0.8f, 1f, 0.8f, 1.0f, 0.8f, 1.2f, 1.2f, 0.8f};
            } else {
                headers = new String[]{
                        "S. No", "User Id", "Username", "Emp Id", "Emp Name",
                        "Branch Id", "Date of Login", "IP Address"
                };
                relWidths = new float[]{0.5f, 0.8f, 1f, 0.8f, 1.0f, 0.8f, 1.2f, 0.8f};
            }

            // --- Table Setup ---
            BaseTable table = new BaseTable(yStart - 80, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

            // Header Row
            Row<PDPage> headerRow = table.createRow(18f);
            for (String header : headers) {
                Cell<PDPage> cell = headerRow.createCell(100f / headers.length, header);
                cell.setFont(PDType1Font.HELVETICA_BOLD);
                cell.setFontSize(10);
                cell.setFillColor(new Color(220, 220, 220));
                cell.setBottomBorderStyle(new LineStyle(Color.BLACK, 0.75f));
            }
            table.addHeaderRow(headerRow);

            // --- Data Rows ---
            int count = 1;
            for (LoginReportDto dto : loginReportDtos) {
                Row<PDPage> row = table.createRow(16f);
                String loginDate = dto.getLoginDate() != null ? tableDateFormat.format(dto.getLoginDate()) : "";
                String logoutDate = dto.getLogoutDate() != null ? tableDateFormat.format(dto.getLogoutDate()) : "NA";

                if (cols == 9) {
                    addCells(row, relWidths, new String[]{
                            String.valueOf(count++),
                            safe(dto.getUserId()),
                            safe(dto.getUsername()),
                            safe(dto.getEmployeeCode()),
                            safe(dto.getFirstName()) + " " + safe(dto.getLastName()),
                            safe(dto.getSolId()),
                            loginDate,
                            logoutDate,
                            safe(dto.getIp())
                    });
                } else {
                    addCells(row, relWidths, new String[]{
                            String.valueOf(count++),
                            safe(dto.getUserId()),
                            safe(dto.getUsername()),
                            safe(dto.getEmployeeCode()),
                            safe(dto.getFirstName()) + " " + safe(dto.getLastName()),
                            safe(dto.getSolId()),
                            loginDate,
                            safe(dto.getIp())
                    });
                }
            }

            table.draw();

            // --- Write PDF to HTTP Response ---
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=MultipleDeviceLoginReport.pdf");
            document.save(response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDormantUsersReport(List<LoginReportDto> loginReports, Date fromDate, Date toDate, HttpServletResponse response) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat tableDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - 50;
            float bottomMargin = 50;
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

            // --- Title and Date Range ---
            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
                cs.newLineAtOffset(margin, yStart);
                cs.showText("Report On Dormant User-IDs");
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin, yStart - 25);
                cs.showText("From Date : " + dateFormat.format(fromDate));
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin + 250, yStart - 25);
                cs.showText("To Date : " + dateFormat.format(toDate));
                cs.endText();
            }

            // --- Columns & Headers ---
            ReportType t = ReportType.DORMANT;
            int cols = (t.getId() == 2 || t.getId() == 3) ? 9 : 8;

            String[] headers;
            float[] relWidths;

            if (cols == 9) {
                headers = new String[]{"S. No", "User Id", "Username", "Emp Id", "Emp Name", "Branch Id", "Date of Login", "Date of Logout", "IP Address"};
                relWidths = new float[]{0.5f, 0.8f, 1f, 0.8f, 1.0f, 0.8f, 1.2f, 1.2f, 0.8f};
            } else {
                headers = new String[]{"S. No", "User Id", "Username", "Emp Id", "Emp Name", "Branch Id", "Date of Login", "IP Address"};
                relWidths = new float[]{0.5f, 0.8f, 1f, 0.8f, 1.0f, 0.8f, 1.2f, 0.8f};
            }

            // --- Table Setup ---
            BaseTable table = new BaseTable(yStart - 80, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

            // Header Row
            Row<PDPage> headerRow = table.createRow(18f);
            for (String header : headers) {
                Cell<PDPage> cell = headerRow.createCell(100f / headers.length, header);
                cell.setFont(PDType1Font.HELVETICA_BOLD);
                cell.setFontSize(10);
                cell.setFillColor(new Color(220, 220, 220));
                cell.setBottomBorderStyle(new LineStyle(Color.BLACK, 0.75f));
            }
            table.addHeaderRow(headerRow);

            // --- Data Rows ---
            int count = 1;
            for (LoginReportDto dto : loginReports) {
                Row<PDPage> row = table.createRow(16f);
                String loginDate = dto.getLoginDate() != null ? tableDateFormat.format(dto.getLoginDate()) : "";
                String logoutDate = dto.getLogoutDate() != null ? tableDateFormat.format(dto.getLogoutDate()) : "NA";

                if (cols == 9) {
                    addCells(row, relWidths, new String[]{
                            String.valueOf(count++),
                            safe(dto.getUserId()),
                            safe(dto.getUsername()),
                            safe(dto.getEmployeeCode()),
                            safe(dto.getFirstName()) + " " + safe(dto.getLastName()),
                            safe(dto.getSolId()),
                            loginDate,
                            logoutDate,
                            safe(dto.getIp())
                    });
                } else {
                    addCells(row, relWidths, new String[]{
                            String.valueOf(count++),
                            safe(dto.getUserId()),
                            safe(dto.getUsername()),
                            safe(dto.getEmployeeCode()),
                            safe(dto.getFirstName()) + " " + safe(dto.getLastName()),
                            safe(dto.getSolId()),
                            loginDate,
                            safe(dto.getIp())
                    });
                }
            }

            table.draw();

            // --- Output PDF ---
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=DormantUsersReport.pdf");
            document.save(response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportAdminActivitiesReport(List<AdminActivityReportDto> adminActivityReportDtos, Date date, HttpServletResponse response) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - 50;
            float bottomMargin = 50;
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

            // --- Draw title and date text ---
            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
                cs.newLineAtOffset(margin, yStart);
                cs.showText("Report On List Of User Related Activities Carried Out On a Day");
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 10);
                cs.newLineAtOffset(margin, yStart - 20);
                cs.showText("Date : " + dateFormat.format(date));
                cs.endText();
            }

            // --- Table headers ---
            String[] headers = {"S. No", "User Id", "Username", "Emp Id", "Emp Name", "Activities Carried Out"};
            float[] relWidths = {0.5f, 0.8f, 1.0f, 0.8f, 1.2f, 2.0f};

            // --- Create table ---
            BaseTable table = new BaseTable(yStart - 60, yStart, bottomMargin, tableWidth, margin, document, page, true, true);
            Row<PDPage> headerRow = table.createRow(18f);

            for (String header : headers) {
                Cell<PDPage> cell = headerRow.createCell(100f / headers.length, header);
                cell.setFont(PDType1Font.HELVETICA_BOLD);
                cell.setFontSize(10);
                cell.setFillColor(new Color(220, 220, 220));
                cell.setBottomBorderStyle(new LineStyle(Color.BLACK, 0.75f));
            }
            table.addHeaderRow(headerRow);

            // --- Table Data Rows ---
            int count = 1;
            for (AdminActivityReportDto dto : adminActivityReportDtos) {
                Row<PDPage> row = table.createRow(16f);
                row.createCell(relWidths[0] * 100 / sum(relWidths), String.valueOf(count++));
                row.createCell(relWidths[1] * 100 / sum(relWidths), safe(dto.getUserId()));
                row.createCell(relWidths[2] * 100 / sum(relWidths), safe(dto.getUsername()));
                row.createCell(relWidths[3] * 100 / sum(relWidths), safe(dto.getEmployeeCode()));
                row.createCell(relWidths[4] * 100 / sum(relWidths), safe(dto.getFirstName()) + " " + safe(dto.getLastName()));
                row.createCell(relWidths[5] * 100 / sum(relWidths), safe(dto.getActivity()));
            }

            table.draw();

            // --- Write to HTTP Response ---
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=AdminActivitiesReport.pdf");
            document.save(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addCells(Row<PDPage> row, float[] relWidths, String[] values) {
        float total = sum(relWidths);
        for (int i = 0; i < values.length; i++) {
            Cell<PDPage> cell = row.createCell(relWidths[i] * 100 / total, values[i]);
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(9);
        }
    }

    private static String safe(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    private static float sum(float[] arr) {
        float total = 0;
        for (float f : arr) total += f;
        return total;
    }


}
