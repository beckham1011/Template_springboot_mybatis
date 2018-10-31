package cn.bjjoy.bms.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SuppressWarnings("deprecation")
public class PoiUtil {

	private final static String excel2003L =".xls";    //2003- 版本的excel  
	private final static String excel2007U =".xlsx";   //2007+ 版本的excel  
	
	
	public static SXSSFRow newHeader(SXSSFRow headerRow , String[] headers){
		for(int hh = 0; hh < headers.length; hh ++){
			headerRow.createCell(hh).setCellValue(headers[hh]);
		}
		return headerRow ;
	}
	
	public static Row newXHeader(Row headerRow , String[] headers){
		for(int hh = 0; hh < headers.length; hh ++){
			headerRow.createCell(hh).setCellValue(headers[hh]);
		}
		return headerRow ;
	}
	
	public static Sheet createManyRow(SXSSFSheet sheet , String[] headers, int begin){
		Row row = sheet.createRow(begin) ;
		for(int hh = 0; hh < headers.length; hh ++){
			row.createCell(hh).setCellValue(headers[hh]);
		}
		return sheet ;
	}
	
	public static Sheet generateMoreSheet(SXSSFSheet sheet ,String[]... strings){
		int begin = 1 ;
		for(int kk = 0 ;kk< strings.length;kk++){
			createManyRow(sheet, strings[kk],begin) ;
		}
		return sheet;
	}
	
	public static Sheet generateMoreSheet(SXSSFSheet sheet ,List<String[]> strings){
		int begin = 1 ;
		for(int kk = 0 ;kk< strings.size();kk++){
			createManyRow(sheet, strings.get(kk),begin) ;
		}
		return sheet;
	}
	
	public static String getValue(Row row, int cellId){
		return row.getCell(cellId) == null? "" : row.getCell(cellId).getStringCellValue() ;
	}
	
	public static String getNullValue(Row row, int cellId){
		return row.getCell(cellId) == null? "Y" : row.getCell(cellId).getStringCellValue() ;
	}
	
	public static String getIntegerNullValue(Row row, int cellId){
		return row.getCell(cellId) == null? "0" : row.getCell(cellId).getStringCellValue() ;
	}
	
	public static String getDoubleNullValue(Row row, int cellId){
		return row.getCell(cellId) == null? "0.0" : row.getCell(cellId).getStringCellValue() ;
	}
	
	public static String toString(Integer[] pots){
		if (pots == null){
			return "" ;
		}
		StringBuffer buf = new StringBuffer(500);
		for(int pp = 0; pp < pots.length ; pp ++){
			buf.append(pots[pp].intValue()) ;
			if(pp < pots.length - 1){
				buf.append(",") ;
			}
		}
		return buf.toString() ;
	}
	
	public static byte[] generateBytesFromWorkBook(Workbook wb){
		byte[] bt = null;
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			wb.write(output);
			bt = output.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bt;
	}
			
			
	public static String formatRealTime(Long realTime){
		long period = realTime / 1000;
		long hour = period / 3600;
		long min = period / 60;
		if(min >= 60){
			min=min%60;
		}
		long lastsecs = period % 60;
		String realPeriod=(hour<10?("0"+hour):hour) + ":" + (min<10?("0"+min):min )+ ":" +(lastsecs<10?("0"+lastsecs):lastsecs);
		
		return realPeriod ;
	}
	
    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
    	Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));        
        if(excel2003L.equalsIgnoreCase(fileType)){
        	wb = new HSSFWorkbook(inStr);  //2007+
        }else if(excel2007U.equalsIgnoreCase(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");  
        }
        return wb;  
    }
    
	public static StringBuffer generateValueBuffer(Sheet sheet, int cellNum) {
		int rowNum = sheet.getLastRowNum() ;
		
		//初始化StringBuffer
		StringBuffer valueBuf = new StringBuffer( rowNum * cellNum * 20) ;
		for( int index = 0; index < rowNum ; index ++){
			Row row = sheet.getRow(index);
			for( int colIndex = 0; colIndex < cellNum ; colIndex ++){
				valueBuf.append("\""+row.getCell(colIndex)+"\"") ;
				if(colIndex<cellNum - 1){
					valueBuf.append(" , ") ;
				}
			}
		}
		return valueBuf;
	}

	@SuppressWarnings({ "static-access" })
	public static String checkCellNull(Cell cell){
		if(cell == null)
			return "" ;
		if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			  Object inputValue = null;// 单元格值
	          Long longVal = Math.round(cell.getNumericCellValue());
	          Double doubleVal = cell.getNumericCellValue();
	          if(Double.parseDouble(longVal + ".0") == doubleVal){   //判断是否含有小数位.0
	              inputValue = longVal;
	          }
	          else{
	              inputValue = doubleVal;
	          }
	          DecimalFormat df = new DecimalFormat("#.####");    //格式化为四位小数，按自己需求选择；
	          return String.valueOf(df.format(inputValue));      //返回String类
		}
		return cell.toString() ;
	}
	
	public static String createGoodsHelper(Row row){
		StringBuffer buf = new StringBuffer("") ;
		if(row.getCell(11)!=null){
			buf.append(row.getCell(11).toString()).append(",");
		}
		if(row.getCell(12)!=null){
			buf.append(row.getCell(12).toString()).append(",");			
		}
		if(row.getCell(13)!=null){
			buf.append(row.getCell(13).toString()).append(",");
		}
		if(row.getCell(14)!=null){
			buf.append(row.getCell(14).toString()).append(",");
		}
		if(row.getCell(15)!=null){
			buf.append(row.getCell(15).toString());
		}
		return buf.toString();
	}
	
	
	public static String createForwarding(String good){
		String[] forwardingsArray = good.split(","); 
		
		StringBuffer buf = new StringBuffer("[") ;
		if(forwardingsArray.length!=0){
			for(int i=0;i<forwardingsArray.length;i++){
				if(i==forwardingsArray.length-1){
					buf.append("\""+forwardingsArray[i]+"\"");
				}else{
					buf.append("\""+forwardingsArray[i]+"\",");
				}
			}
		}
		buf.append("]");
		return buf.toString();
	}
	
	public static String checkIfNull(String input){
		if(input == null ){
			return "" ;
		}
		return "" ;
	}
	
	public static String convertPhoneNumFromPOI(Cell memberCell){
		DecimalFormat df = new DecimalFormat("#");  
		if(memberCell == null)
			return "" ;
		// 手机  
		String mobile = memberCell.toString();  
	    // mobile = memberCell.toString();  
		if(memberCell.toString().length() > 0)
		    switch (memberCell.getCellType())  
		    {  
		        case Cell.CELL_TYPE_NUMERIC:// 数字  
		            mobile = df.format(memberCell.getNumericCellValue());  
		            break;  
		        case Cell.CELL_TYPE_STRING:// 字符串  
		            mobile = df.format(Double.parseDouble(memberCell.toString()));  
		            break;  
		        default:  
		            mobile = memberCell.toString();  
		            break;  
		    }
	    return mobile ;
	}
	
	public static String convertPhoneNumFromPOIInt(Cell memberCell ){
		DecimalFormat df = new DecimalFormat("#");  
		if(memberCell == null)
			return "0" ;
		// 手机  
		String mobile = memberCell.toString();  
	    // mobile = memberCell.toString();  
		if(memberCell.toString().length() > 0)
		    switch (memberCell.getCellType())  
		    {  
		        case Cell.CELL_TYPE_NUMERIC:// 数字  
		            mobile = df.format(memberCell.getNumericCellValue());  
		            break;  
		        case Cell.CELL_TYPE_STRING:// 字符串  
		            mobile = df.format(Double.parseDouble(memberCell.toString()));  
		            break;  
		        default:  
		            mobile = memberCell.toString();  
		            break;  
		    }
	    return mobile ;
	}
	
	
	public static String convertPhoneNumFromPOIDouble(Cell memberCell){
		DecimalFormat df = new DecimalFormat("#");  
		if(memberCell == null)
			return "0.0" ;
		// 手机  
		String mobile = memberCell.toString();  
	    // mobile = memberCell.toString();  
		if(memberCell.toString().length() > 0)
		    switch (memberCell.getCellType())  
		    {  
		        case Cell.CELL_TYPE_NUMERIC:// 数字  
		            mobile = df.format(memberCell.getNumericCellValue());  
		            break;  
		        case Cell.CELL_TYPE_STRING:// 字符串  
		            mobile = df.format(Double.parseDouble(memberCell.toString()));  
		            break;  
		        default:  
		            mobile = memberCell.toString();  
		            break;  
		    }
	    return mobile ;
	}
	
	
	public static String escapeString(String text){
		if(text == null) return null;
		text = StringEscapeUtils.escapeHtml4(text) ;
		text = StringEscapeUtils.escapeEcmaScript(text) ;
		text = StringEscapeUtils.escapeXml(text);
		text = StringEscapeUtils.escapeJava(text);
		return text;
	}

	
	public static ResponseEntity<byte[]> getPOIEntity(Workbook wb , String excelName){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
		httpHeaders.add("Content-Disposition", String.format("attachment; filename=\"%s\"", excelName + ".xlsx"));
		httpHeaders.add("Pragma", "no-cache");
		httpHeaders.add("Expires", "0");
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();  
	    try {
			wb.write(outByteStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				wb.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<byte[]>(outByteStream.toByteArray(), httpHeaders, HttpStatus.OK);
	}
	
	public static ResponseEntity<byte[]> getPOIEntityxls(Workbook wb , String excelName){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
		httpHeaders.add("Content-Disposition", String.format("attachment; filename=\"%s\"", excelName + ".xls"));
		httpHeaders.add("Pragma", "no-cache");
		httpHeaders.add("Expires", "0");
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();  
	    try {
			wb.write(outByteStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				wb.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<byte[]>(outByteStream.toByteArray(), httpHeaders, HttpStatus.OK);
	}

	public static Double getDouble(List<String> row,int i){
		String obj = "";
		// 判断当前游标是否在范围内
		if(row.size() > i){
			obj = row.get(i);
			if(obj == null){
				return 0.0d;
			}else{
				try {
					Double s = Double.valueOf(obj) ;
					return s;
				} catch (Exception e) {
					// TODO: handle exception
					return 0.0d;
				}
			}
		}else{
			return 0.0d;	
		}
	}
	
	public static int getInt(List<String> row,int i){
		String obj = "";
		if(row.size() > i){
			obj = row.get(i);
			if(obj == null){
				return 0;
			}else{
				try {
					int s = Integer.parseInt(obj) ;
					return s;
				} catch (Exception e) {
					// TODO: handle exception
					return 0;
				}
				
			}
		}else{
			return 0;
		}
	}
	
	public static String getString(List<String> row,int i){
		String obj = "";
		// 判断当前游标是否在范围内
		if(row.size() > i){
			obj = row.get(i);
			if(obj == null){
				return "";
			}else{
				return obj;
			}
		}else{
			return "";	
		}
	}
	
	public static Double getDouble(String[] row,int i){
		try {
			if(row.length > i && row[i] != null && row[i].length() > 0){
				return Double.parseDouble(row[i]) == 0 ? null : Double.parseDouble(row[i]);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Integer getInt(String[] row,int i){
		try {
			if(row.length > i && row[i] != null && row[i].length() > 0){
				return Integer.parseInt(row[i]) == 0 ? null : Integer.parseInt(row[i]);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getString(String[] row,int i){
		String obj = "";
		// 判断当前游标是否在范围内
		if(row.length > i){
			obj = row[i];
			if(obj == null){
				return "";
			}else{
				return obj.trim();
			}
		}else{
			return "";	
		}
	}
}
