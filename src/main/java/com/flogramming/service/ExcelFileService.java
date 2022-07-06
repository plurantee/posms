package com.flogramming.service;

import com.flogramming.domain.LazadaOrder;
import com.flogramming.repository.LazadaOrderRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelFileService {

    @Autowired
    private LazadaOrderRepository lazadaOrderRepository;

    public List<LazadaOrder> processLazadaExcelFile(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, List<String>> data = new HashMap<>();
        Map<Integer, HashMap<String, String>> excelHashMap = new HashMap<>();
        List<String> columnNames = new ArrayList<>();
        Row columnNamesRow = sheet.getRow(0);
        for(Cell cell: columnNamesRow) {
            switch (cell.getCellType()) {
                case STRING:
                    columnNames.add(cell.getStringCellValue());
                    System.out.println(cell.getStringCellValue());
                    break;
            }
        }
        List<LazadaOrder> result = new ArrayList<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<>());
            excelHashMap.put(i, new HashMap<>());
            for (int j=0; j < columnNames.size(); j++) {
                switch (row.getCell(j).getCellType()) {
                    case STRING:
                        excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                            excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getDateCellValue() + "");
                        } else {
                            excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getNumericCellValue() + "" + "");
                        }
                        break;
                    case BOOLEAN:
                        excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getBooleanCellValue() + "" + "");
                        break;
                    case FORMULA:
                        excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getCellFormula() + "");
                        break;
                    default:
                        excelHashMap.get(i).put(columnNames.get(j), " ");
                }
                excelHashMap.get(i).put(columnNames.get(j), row.getCell(j).getStringCellValue());
            }

            i++;
        }
        return result;
    }
}
