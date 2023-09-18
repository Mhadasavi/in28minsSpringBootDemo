package com.uc.service;

import com.uc.bean.Quote;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

    private XSSFWorkbook readExcelFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:test.xlsx");
        InputStream inputStream = resource.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        return workbook;
    }

    public void updateExcelFile(Quote quote) throws IOException {
        try {
            XSSFWorkbook workbook = readExcelFile();
            if(workbook !=null) {
                // Access the desired sheet
                XSSFSheet sheet = workbook.getSheetAt(0); // Assuming you want the first sheet
                // Access the specific cell (e.g., Row 1, Column 1)
                Row row = sheet.getRow(11);
                Cell cell = row.getCell(0);

                // Update the cell value
                cell.setCellValue(quote.getCustomerName());
                row.getCell(3).setCellValue(LocalDate.now());
                // Save the changes back to the Excel file
                FileOutputStream outputStream = new FileOutputStream(new File("F:\\Repo\\Resources\\file.xlsx"));
                workbook.write(outputStream);
                outputStream.close();

                // Close the workbook and input stream
                workbook.close();
//                fileInputStream.close();

                System.out.println("Cell updated successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCellValue(int sheet, int row, int column, Quote quote){

    }

}
