package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static Map<String, String> getTestData(String filePath, String sheetName, int rowNum) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rowNum);
        Map<String, String> dataMap = new HashMap<>();

        for (Cell cell : row) {
            String key = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
            //String value = cell.getStringCellValue();
            
            
            String data = "";
            if (cell.getCellType() == CellType.STRING) {
			    data = cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
			    data = String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == CellType.BLANK) {
			    // For blank cells, you might want to set it to an empty string
			    data = "";
			} else if (cell.getCellType() == CellType.BOOLEAN) {
			    data = String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == CellType.FORMULA) {
			    data = cell.getCellFormula(); // Or evaluate the formula if needed
			}
        
        dataMap.put(key, data);
        }
        workbook.close();
        return dataMap;
    }
}
