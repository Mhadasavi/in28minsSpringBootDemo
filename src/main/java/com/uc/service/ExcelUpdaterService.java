package com.uc.service;

import com.uc.bean.Item;
import com.uc.bean.Quote;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@Configuration
public class ExcelUpdaterService {
    private final ResourceLoader resourceLoader;

    public ExcelUpdaterService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void updateExcelFile(Quote quote) {
        try {
            XSSFWorkbook workbook = readExcelFile();
            if (workbook != null) {
                // Access the desired sheet
                updateCellValue(workbook, quote);
                // Save the changes back to the Excel file
                FileOutputStream outputStream = new FileOutputStream(new File("F:\\Repo\\Resources\\file.xlsx"));
                workbook.write(outputStream);
                outputStream.close();

                // Close the workbook and input stream
                workbook.close();
                System.out.println("Cell updated successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XSSFWorkbook readExcelFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:test.xlsx");
        InputStream inputStream = resource.getInputStream();
        return new XSSFWorkbook(inputStream);
    }

    private void updateCellValue(XSSFWorkbook workbook, Quote quote) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row customerNameAndDateRow = sheet.getRow(11);
        customerNameAndDateRow.getCell(0).setCellValue(quote.getCustomerName());
        customerNameAndDateRow.getCell(3).setCellValue(LocalDate.now());

        // Calculate the number of rows needed based on the number of items
        int numRowsToAdd = quote.getItems().size() - 1;
        // Shift existing rows down to make space for the new rows
        int startShiftRow = 16; // Start shifting from row 16
        sheet.shiftRows(startShiftRow, startShiftRow + 28, numRowsToAdd);
        // Insert new rows with formatting from the baseRow
        for (int i = 0; i < numRowsToAdd; i++) {
            int newRowNum = 16 + i; // Start from row 16 and increment for each new row
            sheet.copyRows(startShiftRow - 1, startShiftRow - 1, newRowNum, new CellCopyPolicy());
        }

        // Rest of your code for populating data in the newly added rows goes here
        int count = 1;
        int currentItemRow = 15; // Update this to start from the first newly added row

        double totalPrice = 0d;
        double totalArea = 0;
        for (Item item : quote.getItems()) {
            if (item != null) {
                XSSFRow itemRow = sheet.getRow(currentItemRow);

                // Update the cell values in the new row as needed
                itemRow.getCell(0).setCellValue(count++);
                itemRow.getCell(1).setCellValue("Glass type");
                itemRow.getCell(2).setCellValue(item.getName());
                itemRow.getCell(4).setCellValue(item.getLength());
                itemRow.getCell(5).setCellValue(item.getBreadth());
                itemRow.getCell(6).setCellValue(item.getQuantity());

                double area = calculateArea(item.getLength(), item.getBreadth());
                itemRow.getCell(7).setCellValue(area);
                itemRow.getCell(9).setCellValue(item.getRate());
                double price = item.getRate() * area;
                itemRow.getCell(10).setCellValue(price);


                totalArea += area;
                totalPrice += price;
                currentItemRow++; // Move to the next newly added row
            }
        }
//        XSSFRow areaAndPriceRow = sheet.getRow(currentItemRow);
//        areaAndPriceRow.getCell(7).setCellValue(totalArea);
//        areaAndPriceRow.getCell(10).setCellValue(totalPrice);
        updateQuoteAmount(quote, sheet, currentItemRow, totalArea, totalPrice);
    }

    private double calculateArea(Double width, Double height) {
        return width * height * 8 / 92903;
    }

    private void updateQuoteAmount(Quote quote, XSSFSheet sheet, int currentItemRow, double totalArea, double totalPrice){
        XSSFRow areaAndPriceRow = sheet.getRow(currentItemRow);
        areaAndPriceRow.getCell(7).setCellValue(totalArea);
        areaAndPriceRow.getCell(10).setCellValue(totalPrice);

        sheet.getRow(currentItemRow+1).getCell(10).setCellValue(quote.getInstallationCharge());
        double gstAmount = (totalPrice * quote.getGstCharge()) / 100;
        sheet.getRow(currentItemRow+2).getCell(10).setCellValue(gstAmount);
        double amountWithoutCartage = gstAmount + totalPrice;
        sheet.getRow(currentItemRow+3).getCell(10).setCellValue(amountWithoutCartage);
        sheet.getRow(currentItemRow+3).getCell(4).setCellValue(amountWithoutCartage/totalArea);
        sheet.getRow(currentItemRow+4).getCell(10).setCellValue(quote.getCartage());
        sheet.getRow(currentItemRow+5).getCell(10).setCellValue(amountWithoutCartage+quote.getCartage());




    }
}
