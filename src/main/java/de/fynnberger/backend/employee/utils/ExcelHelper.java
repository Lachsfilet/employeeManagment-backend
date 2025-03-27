package de.fynnberger.backend.employee.utils;

import de.fynnberger.backend.employee.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import de.fynnberger.backend.employee.EmployeeDTO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelHelper {
    public List<EmployeeDTO> parseExcelFile(MultipartFile file) throws Exception {
        List<EmployeeDTO> employees = new ArrayList<>();
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setFirstName(row.getCell(0).getStringCellValue());
                employee.setLastName(row.getCell(1).getStringCellValue());
                employee.setEmail(row.getCell(2).getStringCellValue());
                employees.add(employee);
            }
        }
        return employees;
    }

    public byte[] writeEmployeesToExcel(List<Employee> employees) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employees");
            for (int i = 0; i < employees.size(); i++) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(employees.get(i).getFirstName());
                row.createCell(1).setCellValue(employees.get(i).getLastName());
                row.createCell(2).setCellValue(employees.get(i).getEmail());
            }
            for (int i = 0; i < employees.size(); i++) {
                sheet.autoSizeColumn(i);
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to write employees to Excel file");
        }
    }
}