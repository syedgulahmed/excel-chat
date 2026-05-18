package com.example.excel_chat.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;

@Service
public class ExcelParser {
    
    public String excelToString(MultipartFile file) throws IOException {
        StringBuilder out = new StringBuilder();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        DataFormatter formatter = new DataFormatter();

        for (Sheet sheet : workbook) {
            for (Row row : sheet) {
                for (Cell cell : row) {
                    out.append(formatter.formatCellValue(cell)).append("\t");
                }
                out.append("\n");
            }
        }
        workbook.close();
        return out.toString();
    }

}
