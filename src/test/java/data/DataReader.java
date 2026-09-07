package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class DataReader {
	public Object[][] getJsonData(String filePath) throws IOException
	{
		//Import Apache CommonsIO
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//Import Jackson Databinder
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		
		List<String> keys = new ArrayList<>(data.get(0).keySet()); //dynamic
		//Object[][] result = new Object[data.size()][2]]; //manual
		Object[][] result = new Object[data.size()][keys.size()]; //dynamic
		
		for (int i = 0; i < data.size(); i++) {
			HashMap<String, String> row = data.get(i);
		    //result[i][0] = row.get("username"); //manual
		    //result[i][1] = row.get("password"); //manual
			for (int j = 0; j<keys.size(); j++)
			{
				result[i][j] = row.get(keys.get(j));
			}
		}
		return result;
	}
	public Object[][] getExcelData(String filePath, int SheetNumber) throws IOException
	{
		//Import Apache POI and POI-ooxml
		DataFormatter formatter = new DataFormatter();
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(SheetNumber);
		XSSFRow row = sheet.getRow(0);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		int columnCount = row.getLastCellNum();
		
		//Object data[][] = new Object[rowCount - 1][2]; //manual
		Object data[][] = new Object[rowCount - 1][columnCount]; //dynamic
		
		for (int i = 0; i<rowCount - 1; i++)
		{
			row = sheet.getRow(i + 1);
			//data[i][0] = formatter.formatCellValue(row.getCell(0)); //manual
			//data[i][1] = formatter.formatCellValue(row.getCell(1)); //manual
			
			for (int j = 0; j<columnCount; j++) //dynamic
			{
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
			
		}
		return data;
	}
}
