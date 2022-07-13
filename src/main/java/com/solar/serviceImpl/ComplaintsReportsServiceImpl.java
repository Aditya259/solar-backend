package com.solar.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.model.Complaints;
import com.solar.repository.ComplaintRepository;
import com.solar.service.ComplaintsReportsService;
import com.solar.usefullClasses.ComplaintDetails;
import com.solar.usefullClasses.DateFormat;
import com.solar.utils.PropertyValues;
@Service
public class ComplaintsReportsServiceImpl implements ComplaintsReportsService{
	
	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	ComplaintServicesImpl complaintServices;
	
	
	@Autowired
	PropertyValues propertyValues;

	@Override
	public Workbook compliantReportYearly(String complaintsJSON,Complaints complaint) {

		
		List<Complaints> complaints = complaintRepository.findByCompanyIdAndDateOfCreateBetweenOrderByIdDesc(complaint.getCompanyId(), complaint.getFromDate(), complaint.getToDate());
		
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		
		CreationHelper createHelper = wb.getCreationHelper();  

		int rowCount = 0;
		Row row = sheet.createRow(rowCount++);

		Font MHfont = wb.createFont();
		MHfont.setFontHeightInPoints((short)16);
		
		Font SHfont = wb.createFont();
		SHfont.setFontHeightInPoints((short)12);
		
		Font font = wb.createFont();
		font.setBold(true);


		CellStyle MainHeadingcellStyle = wb.createCellStyle();
		MainHeadingcellStyle.setFont(MHfont);
		MainHeadingcellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		MainHeadingcellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		MainHeadingcellStyle.setAlignment(HorizontalAlignment.CENTER);
		
//		Sub Heading style
		CellStyle SubHeadingcellStyle = wb.createCellStyle();
		SubHeadingcellStyle.setFont(SHfont);
		SubHeadingcellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		SubHeadingcellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		SubHeadingcellStyle.setAlignment(HorizontalAlignment.CENTER);
		
//		Heading style
		CellStyle HeadingcellStyle = wb.createCellStyle();
		HeadingcellStyle.setBorderTop(BorderStyle.THICK);
		HeadingcellStyle.setBorderBottom(BorderStyle.THICK);
		HeadingcellStyle.setBorderLeft(BorderStyle.THICK);
		HeadingcellStyle.setBorderRight(BorderStyle.THICK);
		HeadingcellStyle.setFont(font);
		HeadingcellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		HeadingcellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		HeadingcellStyle.setAlignment(HorizontalAlignment.CENTER);
		
//		MAin HEading
		Cell cell = row.createCell(0);
		cell.setCellValue(complaint.getCompanyName());
		cell.setCellStyle(MainHeadingcellStyle);
		
//		Sub HEading
		row = sheet.createRow(rowCount++);
		cell = row.createCell(0);
		cell.setCellValue(propertyValues.getCustomerComplaintFrom()+DateFormat.DateFormat(complaint.getFromDate())+ " to "+DateFormat.DateFormat(complaint.getToDate()));
		cell.setCellStyle(SubHeadingcellStyle);
		
//		Table heading
		row = sheet.createRow(rowCount++);
		cell = row.createCell(0);
		cell.setCellValue(propertyValues.getSlNo());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(1);
		cell.setCellValue(propertyValues.getReportIDN0());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(2);
		cell.setCellValue(propertyValues.getProduct());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(3);
		cell.setCellValue(propertyValues.getCustomersName());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(4);
		cell.setCellValue(propertyValues.getDateofReceiptofcomplaint());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(5);
		cell.setCellValue(propertyValues.getBatchNoDOM());
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(6);
		cell.setCellValue(propertyValues.getNatureofComplaint());
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(7);
		cell.setCellValue(propertyValues.getRemarksfromPlant());
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(8);
		cell.setCellValue(propertyValues.getRemarksFromTechnicalServices());
		cell.setCellStyle(HeadingcellStyle);

//		data style
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		
//		Date Style
		CellStyle cellStyleDate = wb.createCellStyle();
		cellStyleDate.setBorderTop(BorderStyle.THIN);
		cellStyleDate.setBorderBottom(BorderStyle.THIN);
		cellStyleDate.setBorderLeft(BorderStyle.THIN);
		cellStyleDate.setBorderRight(BorderStyle.THIN);
		cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("d-mmm-yyyy"));
		
//		Table Data
		int i=1;
		for (Complaints com : complaints) {
			row = sheet.createRow(rowCount++);

			int columnCount = 0;

			cell = row.createCell(columnCount++);
			cell.setCellValue(i++);
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getId());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(Arrays.toString(com.getNameOfProduct()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getCustomerName());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getDate());
			cell.setCellStyle(cellStyleDate);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getBatchNo()!=null?com.getBatchNo():""+
			" "+ com.getDateOfManufacturing()!=null?com.getDateOfManufacturing():"");
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getNatureOfComplaint());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getClouserRemark1()!=null?com.getClouserRemark1():""+
			" "+com.getClouserRemark2()!=null?com.getClouserRemark2():""+
			" "+com.getClouserRemark3()!=null?com.getClouserRemark3():""+
			" "+com.getClouserRemark4()!=null?com.getClouserRemark4():"");
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getConclusion());
			cell.setCellStyle(cellStyle);
			
			
		}
		for(int j=1;j<9;j++) {
			sheet.autoSizeColumn(j);
		}

		sheet.addMergedRegion(new CellRangeAddress(0,0,0,8));
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,8));
		return wb;
	}

	@Override
	public Workbook compliantReportYearlyDetails(String complaintsJSON, Complaints complaint) {
		List<Complaints> complaints = complaintRepository.findByCompanyIdAndDateOfCreateBetweenOrderByIdDesc(complaint.getCompanyId(), complaint.getFromDate(), complaint.getToDate());
		List<ComplaintDetails> complaint_Details=complaintServices.getComplaintsDetails(complaint);
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		
		CreationHelper createHelper = wb.getCreationHelper();  

		int rowCount = 0;
		Row row = sheet.createRow(rowCount++);

//		MAin Heading Font
		Font MHfont = wb.createFont();
		MHfont.setFontHeightInPoints((short)16);
		
//		Sub HEading Font
		Font SHfont = wb.createFont();
		SHfont.setFontHeightInPoints((short)12);
		
//		Heading Font
		Font font = wb.createFont();
		font.setBold(true);

//		MAin Heading style
		CellStyle MainHeadingcellStyle = wb.createCellStyle();
		MainHeadingcellStyle.setFont(MHfont);
		MainHeadingcellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		MainHeadingcellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		MainHeadingcellStyle.setAlignment(HorizontalAlignment.CENTER);
		
//		Sub Heading style
		CellStyle SubHeadingcellStyle = wb.createCellStyle();
		SubHeadingcellStyle.setFont(SHfont);
		SubHeadingcellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		SubHeadingcellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		SubHeadingcellStyle.setAlignment(HorizontalAlignment.CENTER);
		
//		Heading style
		CellStyle HeadingcellStyle = wb.createCellStyle();
		HeadingcellStyle.setBorderTop(BorderStyle.THICK);
		HeadingcellStyle.setBorderBottom(BorderStyle.THICK);
		HeadingcellStyle.setBorderLeft(BorderStyle.THICK);
		HeadingcellStyle.setBorderRight(BorderStyle.THICK);
		HeadingcellStyle.setFont(font);
		HeadingcellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		HeadingcellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		HeadingcellStyle.setAlignment(HorizontalAlignment.CENTER);
		
//		MAin HEading
		Cell cell = row.createCell(1);
		cell.setCellValue(complaint.getCompanyName());
		cell.setCellStyle(MainHeadingcellStyle);
		
//		Sub HEading
		row = sheet.createRow(rowCount++);
		cell = row.createCell(1);
		cell.setCellValue(propertyValues.getComplaintDetailFrom()+DateFormat.DateFormat3(complaint.getFromDate())+ " to "+DateFormat.DateFormat3(complaint.getToDate()));
		cell.setCellStyle(SubHeadingcellStyle);
		
//		Table heading
		row = sheet.createRow(rowCount++);
		cell = row.createCell(1);
		cell.setCellValue(propertyValues.getSlNo());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(2);
		cell.setCellValue(propertyValues.getProduct());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(3);
		cell.setCellValue(propertyValues.getPhysicalCondition());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(4);
		cell.setCellValue(propertyValues.getStrengthPerformance());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(5);
		cell.setCellValue(propertyValues.getMisfire());
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(6);
		cell.setCellValue(propertyValues.getBoxes());
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(7);
		cell.setCellValue(propertyValues.getLabelling());
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(8);
		cell.setCellValue(propertyValues.getSafety());
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(9);
		cell.setCellValue(propertyValues.getTotal());
		cell.setCellStyle(HeadingcellStyle);
		
//		data style
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		
//		Date Style
		CellStyle cellStyleDate = wb.createCellStyle();
		cellStyleDate.setBorderTop(BorderStyle.THIN);
		cellStyleDate.setBorderBottom(BorderStyle.THIN);
		cellStyleDate.setBorderLeft(BorderStyle.THIN);
		cellStyleDate.setBorderRight(BorderStyle.THIN);
		cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("d-mmm-yyyy"));
		
//		Table Data
		int i=1;
		int total=0;
		for (ComplaintDetails com : complaint_Details) {
			row = sheet.createRow(rowCount++);

			int columnCount = 1;

			cell = row.createCell(columnCount++);
			cell.setCellValue(i++);
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getProduct());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getPhysicalCondition());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getStrenthPerformance());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getMisfire());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getBoxes());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getLabelling());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getSafety());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getTotal());
			cell.setCellStyle(cellStyle);
			
			total=total+com.getTotal();
		}
		
		row = sheet.createRow(rowCount++);
		int columnCount = 1;
		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);

		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellValue("Total");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellValue(total);
		cell.setCellStyle(cellStyle);
		
		
//		second table headings
		rowCount=rowCount+2;
		
		row = sheet.createRow(rowCount++);
		cell = row.createCell(1);
		cell.setCellValue("SL No");
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(2);
		cell.setCellValue("Customer Name");
		cell.setCellStyle(HeadingcellStyle);
		
		
		i=1;
		for(Complaints com: complaints) {
			row = sheet.createRow(rowCount++);

			columnCount = 1;

			cell = row.createCell(columnCount++);
			cell.setCellValue(i++);
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(com.getCustomerName());
			cell.setCellStyle(cellStyle);
		}
		
		
		for(int j=2;j<11;j++) {
			sheet.autoSizeColumn(j);
		}

		sheet.addMergedRegion(new CellRangeAddress(0,0,1,9));
		sheet.addMergedRegion(new CellRangeAddress(1,1,1,9));
		
		return wb;
	}

}
