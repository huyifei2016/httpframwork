package cn.utils;

import static org.testng.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.poifs.filesystem.NPOIFSDocument;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.bouncycastle.crypto.ec.ECNewPublicKeyTransform;
import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.noneDSA;

public class ExecelUtils {
	
	public static boolean createWorkBook(String str) throws IOException
	{
		
		boolean flage=false;
		Workbook workbook=new HSSFWorkbook();
		OutputStream outputStream=new FileOutputStream(str);
		workbook.write(outputStream);
		outputStream.close();
		File file=new File(str);
		flage=file.exists();
		return flage;
		
	}
	
	public static void createSheet(String sheet,String xlsstring) throws IOException
	{
		boolean flage=createWorkBook(xlsstring);
		NPOIFSFileSystem fSystem=new NPOIFSFileSystem(new File(xlsstring));
		Workbook wk=new HSSFWorkbook(fSystem.getRoot(),true);
		Sheet sheet1=wk.createSheet(sheet);
		FileOutputStream outputStream=new FileOutputStream(xlsstring);
		wk.write(outputStream);
		fSystem.close();
		outputStream.close();
		
	}
	
	
	
	public static List<String> getCell()
	{
		NPOIFSFileSystem fSystem;
		DataFormatter formatter = new DataFormatter();
		try {
			fSystem = new NPOIFSFileSystem(new File("1.xls"));
			Workbook wk=new HSSFWorkbook(fSystem.getRoot(),true);
			Sheet sheet1=wk.getSheet("123");
			for (Row row : sheet1) {
				for(Cell cell:row)
				{
					CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
		            System.out.print(cellRef.formatAsString());
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static List<String> getCell(String str)
	{
		
		
		
		
		return null;
		
	}
	
	public static void main(String[] args) {
		
		
		NPOIFSFileSystem fSystem;
		DataFormatter formatter = new DataFormatter();
		try {
			fSystem = new NPOIFSFileSystem(new File("1.xls"));
			Workbook wk=new HSSFWorkbook(fSystem.getRoot(),true);
			Sheet sheet1=wk.getSheet("123");
			Row row=sheet1.getRow(0);
				for(Cell cell:row)
				{
					 String text = formatter.formatCellValue(cell);
			         System.out.println(text);
				}
				
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
