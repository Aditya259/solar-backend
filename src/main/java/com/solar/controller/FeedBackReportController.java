package com.solar.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solar.model.Feedbacks;
import com.solar.model.FeedbacksQuarterlyTarget;
import com.solar.repository.FeedbacksQuarterlyTargetRepository;
import com.solar.serviceImpl.FeedBackServicesImpl;
import com.solar.usefullClasses.FeedbackPerformance;
import com.solar.usefullClasses.FeedbackSectorsRating;
import com.solar.usefullClasses.FeedbackSectorsRatingPercent;
import com.solar.usefullClasses.SectrorAndPercent;


@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class FeedBackReportController{
	
	@Autowired
	FeedBackServicesImpl feedBackServices;

	@Autowired
	FeedbacksQuarterlyTargetRepository feedbacksQuarterlyTargetRepo;
	
	@GetMapping("/report/feedBack/quarterly/details")
	public ResponseEntity<Resource> feedBackReportQuarterlyDetails(
			@RequestParam("months") String months,
			@RequestParam("year") String year,
			@RequestParam("companyId") String companyId,
			@RequestParam("companyName") String companyName) throws IOException {
		
		if(months.isEmpty() || year.isEmpty()) {return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
		
		String date1 = null,date2=null,date3=null;
		
		switch (months) {
		case "JANUARY TO MARCH":
			date1=year+"-01";
			date2=year+"-02";
			date3=year+"-03";
			break;
			
		case "APRIL TO JUN":
			date1=year+"-04";
			date2=year+"-05";
			date3=year+"-06";
			break;
			
		case "JULY TO SEPTEMBER":
			date1=year+"-07";
			date2=year+"-08";
			date3=year+"-09";
			break;
			
		case "OCTOBER TO DECEMBER":
			date1=year+"-10";
			date2=year+"-11";
			date3=year+"-12";
			break;
			
		default:
			break;
		}
		
		System.out.println(date1);
		System.out.println(date2);
		System.out.println(date3);
		
		
		List<Feedbacks> feedbacks = feedBackServices.getFeedback(date1, date2, date3,companyId);
		System.out.println("feedbacks>"+feedbacks);
		
		List<Feedbacks> feedbacks_INSTITUTION=feedbacks.stream().filter(p -> p.getSectorName().equals("INSTITUTION")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_TRADE=feedbacks.stream().filter(p -> p.getSectorName().equals("TRADE")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_EXPORT=feedbacks.stream().filter(p -> p.getSectorName().equals("EXPORT")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_WCL=feedbacks.stream().filter(p -> p.getSectorName().equals("WCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_MCL=feedbacks.stream().filter(p -> p.getSectorName().equals("MCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_NCL=feedbacks.stream().filter(p -> p.getSectorName().equals("NCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_SCCL=feedbacks.stream().filter(p -> p.getSectorName().equals("SCCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_BCCL=feedbacks.stream().filter(p -> p.getSectorName().equals("BCCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_CCL=feedbacks.stream().filter(p -> p.getSectorName().equals("CCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_ECL=feedbacks.stream().filter(p -> p.getSectorName().equals("ECL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_SECL=feedbacks.stream().filter(p -> p.getSectorName().equals("SECL")).collect(Collectors.toList());
		
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
		SubHeadingcellStyle.setBorderTop(BorderStyle.THICK);
		SubHeadingcellStyle.setBorderBottom(BorderStyle.THICK);
		SubHeadingcellStyle.setBorderLeft(BorderStyle.THICK);
		SubHeadingcellStyle.setBorderRight(BorderStyle.THICK);
		SubHeadingcellStyle.setFont(SHfont);
		SubHeadingcellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
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
		
//		Excellent #4
		CellStyle Excellent = wb.createCellStyle();
		Excellent.setBorderTop(BorderStyle.THICK);
		Excellent.setBorderBottom(BorderStyle.THICK);
		Excellent.setBorderLeft(BorderStyle.THICK);
		Excellent.setBorderRight(BorderStyle.THICK);
		Excellent.setFont(SHfont);
		Excellent.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		Excellent.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		Excellent.setAlignment(HorizontalAlignment.CENTER);
		
//		Good #3
		CellStyle Good = wb.createCellStyle();
		Good.setBorderTop(BorderStyle.THICK);
		Good.setBorderBottom(BorderStyle.THICK);
		Good.setBorderLeft(BorderStyle.THICK);
		Good.setBorderRight(BorderStyle.THICK);
		Good.setFont(SHfont);
		Good.setFillForegroundColor(IndexedColors.GOLD.getIndex());
		Good.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		Good.setAlignment(HorizontalAlignment.CENTER);
		
//		Satisfactory #2
		CellStyle Satisfactory = wb.createCellStyle();
		Satisfactory.setBorderTop(BorderStyle.THICK);
		Satisfactory.setBorderBottom(BorderStyle.THICK);
		Satisfactory.setBorderLeft(BorderStyle.THICK);
		Satisfactory.setBorderRight(BorderStyle.THICK);
		Satisfactory.setFont(SHfont);
		Satisfactory.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		Satisfactory.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		Satisfactory.setAlignment(HorizontalAlignment.CENTER);
		
//		Not_Satisfactory 1#
		CellStyle Not_Satisfactory = wb.createCellStyle();
		Not_Satisfactory.setBorderTop(BorderStyle.THICK);
		Not_Satisfactory.setBorderBottom(BorderStyle.THICK);
		Not_Satisfactory.setBorderLeft(BorderStyle.THICK);
		Not_Satisfactory.setBorderRight(BorderStyle.THICK);
		Not_Satisfactory.setFont(SHfont);
		Not_Satisfactory.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		Not_Satisfactory.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		Not_Satisfactory.setAlignment(HorizontalAlignment.CENTER);
		
//		border style
		CellStyle border = wb.createCellStyle();
		border.setBorderTop(BorderStyle.THICK);
		border.setBorderBottom(BorderStyle.THICK);
		border.setBorderLeft(BorderStyle.THICK);
		border.setBorderRight(BorderStyle.THICK);
		
//		MAin HEading
		Cell cell = row.createCell(0);
		cell.setCellValue(companyName +" CUSTOMER'S SATISFACTION FEEDBACK SUMMARY FROM "+months+" "+year);
		cell.setCellStyle(MainHeadingcellStyle);
		
//		Sub HEading
		row = sheet.createRow(rowCount++);
		cell = row.createCell(12);
		cell.setCellValue("Excellent - 4");
		cell.setCellStyle(Excellent);

		cell = row.createCell(13);
		cell.setCellValue("Good - 3");
		cell.setCellStyle(Good);
		
		cell = row.createCell(14);
		cell.setCellValue("Satisfactory -2");
		cell.setCellStyle(Satisfactory);
		
		cell = row.createCell(15);
		cell.setCellValue("Not Satisfactory- 1 ");
		cell.setCellStyle(Not_Satisfactory);
		
		rowCount++;
//		Table heading 1#
		row = sheet.createRow(rowCount++);
		cell = row.createCell(0);
		cell.setCellValue("Name of Orgnisation");
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(1);
		cell.setCellValue("Name of Customers");
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(2);
		cell.setCellStyle(border);

		cell = row.createCell(3);
		cell.setCellValue("Product Quality");
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(4);
		cell.setCellStyle(border);
		
		cell = row.createCell(5);
		cell.setCellValue("Delivery");
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(6);
		cell.setCellStyle(border);
		cell = row.createCell(7);
		cell.setCellStyle(border);
		
		cell = row.createCell(8);
		cell.setCellValue("Services");
		cell.setCellStyle(HeadingcellStyle);

		cell = row.createCell(9);
		cell.setCellStyle(border);
		cell = row.createCell(10);
		cell.setCellStyle(border);
		
		cell = row.createCell(11);
		cell.setCellValue("Knowledge of technical staff");
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(12);
		cell.setCellStyle(border);
		cell = row.createCell(13);
		cell.setCellStyle(border);
		
		
		cell = row.createCell(14);
		cell.setCellValue("Competitivieness");
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(15);
		cell.setCellStyle(border);
		
		cell = row.createCell(16);
		cell.setCellValue("Total Points (out of 52)");
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(17);
		cell.setCellValue("%");
		cell.setCellStyle(HeadingcellStyle);
		
		cell = row.createCell(18);
		cell.setCellValue("OVERALL PERFORMANCE");
		cell.setCellStyle(HeadingcellStyle);
		

//		Table heading 2#
		row = sheet.createRow(rowCount++);
		
		cell = row.createCell(0);
		cell.setCellStyle(border);
		
		cell = row.createCell(1);
		cell.setCellStyle(border);
		
		cell = row.createCell(2);
		cell.setCellStyle(border);
		
		cell = row.createCell(3);
		cell.setCellValue("Product meets \r\n" + 
				"Requirments/Expectations");
		cell.setCellStyle(SubHeadingcellStyle);

		cell = row.createCell(4);
		cell.setCellValue("Product\r\n" + 
				"Performance");
		cell.setCellStyle(SubHeadingcellStyle);

		cell = row.createCell(5);
		cell.setCellValue("Timeless");
		cell.setCellStyle(SubHeadingcellStyle);

		cell = row.createCell(6);
		cell.setCellValue("Packing & \r\n" + 
				"Delivery \r\n" + 
				"conditions");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("Documentation");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(8);
		cell.setCellValue("Response\r\n" + 
				"to queries/ samples");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(9);
		cell.setCellValue("Response\r\n" + 
				" to\r\n" + 
				"complaints");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(10);
		cell.setCellValue("After sales \r\n" + 
				"services");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(11);
		cell.setCellValue("Knowledge \r\n" + 
				"of\r\n" + 
				"technical \r\n" + 
				"staff");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(12);
		cell.setCellValue("Knowledge of \r\n" + 
				"commercial\r\n" + 
				"staff");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(13);
		cell.setCellValue("Easiness \r\n" + 
				"in \r\n" + 
				"reaching");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(14);
		cell.setCellValue("Value for\r\n" + 
				"money");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(15);
		cell.setCellValue("Our overall \r\n" + 
				"rating againt\r\n" + 
				"your best supplier");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(16);
		cell.setCellStyle(border);
		cell = row.createCell(17);
		cell.setCellStyle(border);
		cell = row.createCell(18);
		cell.setCellStyle(border);

//		data style
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		
//		data style
		CellStyle centercellStyle = wb.createCellStyle();
		centercellStyle.setBorderTop(BorderStyle.THIN);
		centercellStyle.setBorderBottom(BorderStyle.THIN);
		centercellStyle.setBorderLeft(BorderStyle.THIN);
		centercellStyle.setBorderRight(BorderStyle.THIN);
		centercellStyle.setAlignment(HorizontalAlignment.CENTER);
		
//		Date Style
		CellStyle cellStyleDate = wb.createCellStyle();
		cellStyleDate.setBorderTop(BorderStyle.THIN);
		cellStyleDate.setBorderBottom(BorderStyle.THIN);
		cellStyleDate.setBorderLeft(BorderStyle.THIN);
		cellStyleDate.setBorderRight(BorderStyle.THIN);
		cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("d-mmm-yyyy"));
		
//		Table Data
		int rows=0;
		float total=0;
		List<String> cat=new ArrayList<>();
		if(months.equals("JANUARY TO MARCH") || months.equals("JULY TO SEPTEMBER")) {
			cat.add("TRADE");
			cat.add("INSTITUTION");
			cat.add("EXPORT");
			cat.add("WCL");
			cat.add("MCL");
			cat.add("NCL");
			cat.add("SCCL");
		}else {
			cat.add("TRADE");
			cat.add("INSTITUTION");
			cat.add("EXPORT");
			cat.add("BCCL");
			cat.add("CCL");
			cat.add("ECL");
			cat.add("SECL");
		}
		System.out.println(cat);
		for(int k=0;k<7;k++)
		{
			List<Feedbacks> feedb=new ArrayList<Feedbacks>();
			
			if(cat.get(k).equals("TRADE")) {feedb=feedbacks_TRADE;}
			if(cat.get(k).equals("INSTITUTION")) {feedb=feedbacks_INSTITUTION;}
			if(cat.get(k).equals("EXPORT")) {feedb=feedbacks_EXPORT;}
			
			if(cat.get(k).equals("WCL")) {feedb=feedbacks_WCL;}
			if(cat.get(k).equals("MCL")) {feedb=feedbacks_MCL;}
			if(cat.get(k).equals("NCL")) {feedb=feedbacks_NCL;}
			if(cat.get(k).equals("SCCL")) {feedb=feedbacks_SCCL;}
			
			if(cat.get(k).equals("BCCL")) {feedb=feedbacks_BCCL;}
			if(cat.get(k).equals("CCL")) {feedb=feedbacks_CCL;}
			if(cat.get(k).equals("ECL")) {feedb=feedbacks_ECL;}
			if(cat.get(k).equals("SECL")) {feedb=feedbacks_SECL;}
		
		if(feedb.isEmpty()) {continue;}
		int colstart=0,colend=0;
		int i=1;
		colstart=rowCount;
		for (Feedbacks feed : feedb) {
			colend=rowCount;
			row = sheet.createRow(rowCount++);
			int columnCount = 0;

			cell = row.createCell(columnCount++);
			cell.setCellValue(cat.get(k).toString());
			cell.setCellStyle(centercellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(i++);
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getNameOfMine());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getProductQuality1());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getProductQuality2());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getDelivery1());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getDelivery2());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getDelivery3());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getService1());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getService2());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getService3());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getBehaviourOfOurStaff1());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getBehaviourOfOurStaff2());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getBehaviourOfOurStaff3());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getCompetitiveness1());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getCompetitiveness2());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getTotalPoints());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getPercent());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(columnCount++);
			cell.setCellValue(feed.getOverallPerformance());
			if(feed.getOverallPerformance().equals("Excellent")) {cell.setCellStyle(Excellent);}
			if(feed.getOverallPerformance().equals("Good")) {cell.setCellStyle(Good);}
			if(feed.getOverallPerformance().equals("Satisfactory")) {cell.setCellStyle(Satisfactory);}
			if(feed.getOverallPerformance().equals("Not_Satisfactory")) {cell.setCellStyle(Not_Satisfactory);}
			
			total=total+feed.getPercent();
			rows++;
		}
		
		row = sheet.createRow(rowCount++);
		int columnCount = 0;
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
		cell.setCellStyle(cellStyle);

		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellStyle(cellStyle);
			}
		
		rowCount++;
		row = sheet.createRow(rowCount++);
		int columnCount = 16;
		cell = row.createCell(columnCount++);
		cell.setCellValue("Average");
		cell.setCellStyle(SubHeadingcellStyle);
		
		cell = row.createCell(columnCount++);
		cell.setCellValue(total/rows);
		cell.setCellStyle(SubHeadingcellStyle);
		
		for(int j=2;j < 19;j++) {
			sheet.autoSizeColumn(j);
		}
		
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,18));
		sheet.addMergedRegion(new CellRangeAddress(3,4,0,0));
		sheet.addMergedRegion(new CellRangeAddress(3,4,1,2));
		
		sheet.addMergedRegion(new CellRangeAddress(3,3,3,4));
		sheet.addMergedRegion(new CellRangeAddress(3,3,5,7));
		sheet.addMergedRegion(new CellRangeAddress(3,3,8,10));
		sheet.addMergedRegion(new CellRangeAddress(3,3,11,13));
		sheet.addMergedRegion(new CellRangeAddress(3,3,14,15));
		
		sheet.addMergedRegion(new CellRangeAddress(3,4,16,16));
		sheet.addMergedRegion(new CellRangeAddress(3,4,17,17));
		sheet.addMergedRegion(new CellRangeAddress(3,4,18,18));
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		wb.write(os);
		wb.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		String fileName="FEEDBACK_Summary_Report_"+months+"_"+year+".xlsx";
		fileName=fileName.replace(" " , "_");
		System.out.println("fileName >"+fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName+"");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}
	
	@GetMapping("/report/feedBack/quarterly/chart")
	public ResponseEntity<Map> feedBackReportQuarterlyDetailsChart(
			@RequestParam("months") String months,
			@RequestParam("year") String year,
			@RequestParam("companyId") String companyId,
			@RequestParam("companyName") String companyName) throws IOException {
		
		if(months.isEmpty() || year.isEmpty()) {return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
		
		String date1 = null,date2=null,date3=null;
		
		switch (months) {
		case "JANUARY TO MARCH":
			date1=year+"-01";
			date2=year+"-02";
			date3=year+"-03";
			break;
			
		case "APRIL TO JUN":
			date1=year+"-04";
			date2=year+"-05";
			date3=year+"-06";
			break;
			
		case "JULY TO SEPTEMBER":
			date1=year+"-07";
			date2=year+"-08";
			date3=year+"-09";
			break;
			
		case "OCTOBER TO DECEMBER":
			date1=year+"-10";
			date2=year+"-11";
			date3=year+"-12";
			break;
			
		default:
			break;
		}
		
		List<Feedbacks> feedbacks = feedBackServices.getFeedback(date1, date2, date3,companyId);
		List<Feedbacks> feedbacks_INSTITUTION=feedbacks.stream().filter(p -> p.getSectorName().equals("INSTITUTION")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_TRADE=feedbacks.stream().filter(p -> p.getSectorName().equals("TRADE")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_EXPORT=feedbacks.stream().filter(p -> p.getSectorName().equals("EXPORT")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_WCL=feedbacks.stream().filter(p -> p.getSectorName().equals("WCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_MCL=feedbacks.stream().filter(p -> p.getSectorName().equals("MCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_NCL=feedbacks.stream().filter(p -> p.getSectorName().equals("NCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_SCCL=feedbacks.stream().filter(p -> p.getSectorName().equals("SCCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_BCCL=feedbacks.stream().filter(p -> p.getSectorName().equals("BCCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_CCL=feedbacks.stream().filter(p -> p.getSectorName().equals("CCL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_ECL=feedbacks.stream().filter(p -> p.getSectorName().equals("ECL")).collect(Collectors.toList());
		List<Feedbacks> feedbacks_SECL=feedbacks.stream().filter(p -> p.getSectorName().equals("SECL")).collect(Collectors.toList());
	
		List<String> cat=new ArrayList<>();
		if(months.equals("JANUARY TO MARCH") || months.equals("JULY TO SEPTEMBER")) {
			cat.add("TRADE");
			cat.add("INSTITUTION");
			cat.add("EXPORT");
			cat.add("WCL");
			cat.add("MCL");
			cat.add("NCL");
			cat.add("SCCL");
		}else {
			cat.add("TRADE");
			cat.add("INSTITUTION");
			cat.add("EXPORT");
			cat.add("BCCL");
			cat.add("CCL");
			cat.add("ECL");
			cat.add("SECL");
		}
		List<FeedbackSectorsRating> newfd=new ArrayList<FeedbackSectorsRating>();
		int productQuality=0,delivery=0,service=0,knowledge_and_Behavior_of_our_staff=0,competitiveness=0;
		for(int k=0;k<7;k++)
		{
			List<Feedbacks> feedb=new ArrayList<Feedbacks>();
			
			if(cat.get(k).equals("TRADE")) {feedb=feedbacks_TRADE;}
			if(cat.get(k).equals("INSTITUTION")) {feedb=feedbacks_INSTITUTION;}
			if(cat.get(k).equals("EXPORT")) {feedb=feedbacks_EXPORT;}
			
			if(cat.get(k).equals("WCL")) {feedb=feedbacks_WCL;}
			if(cat.get(k).equals("MCL")) {feedb=feedbacks_MCL;}
			if(cat.get(k).equals("NCL")) {feedb=feedbacks_NCL;}
			if(cat.get(k).equals("SCCL")) {feedb=feedbacks_SCCL;}
			
			if(cat.get(k).equals("BCCL")) {feedb=feedbacks_BCCL;}
			if(cat.get(k).equals("CCL")) {feedb=feedbacks_CCL;}
			if(cat.get(k).equals("ECL")) {feedb=feedbacks_ECL;}
			if(cat.get(k).equals("SECL")) {feedb=feedbacks_SECL;}
		
			
			int ptot=0,dtot=0,stot=0,ktot=0,ctot=0;
			int p=0,d=0,s=0,kb=0,c=0;
			FeedbackSectorsRatingPercent percent=new FeedbackSectorsRatingPercent();
			if(feedb.isEmpty()) {}
			else {
				for (Feedbacks feed : feedb) {
					ptot=ptot+feed.getProductQuality1()+feed.getProductQuality2();
					p=p+8;
					dtot=dtot+feed.getDelivery1()+feed.getDelivery2()+feed.getDelivery3();
					d=d+12;
					stot=stot+feed.getService1()+feed.getService2()+feed.getService3();
					s=s+12;
					ktot=ktot+feed.getBehaviourOfOurStaff1()+feed.getBehaviourOfOurStaff2()+feed.getBehaviourOfOurStaff3();
					kb=kb+12;
					ctot=ctot+feed.getCompetitiveness1()+feed.getCompetitiveness2();
					c=c+8;
				}
				percent.setProductQuality((ptot*100)/p);
				productQuality=(ptot*100)/p;
				percent.setDelivery((dtot*100)/d);
				delivery=(dtot*100)/d;
				percent.setService((stot*100)/s);
				service=(stot*100)/s;
				percent.setgetKnowledgeandBehaviorOfOurStaff((ktot*100)/kb);
				knowledge_and_Behavior_of_our_staff=(ktot*100)/kb;
				percent.setCompetitiveness((ctot*100)/c);
				competitiveness=(ctot*100)/c;
			}
			
			FeedbackSectorsRating feedback_Sectors_rating=new FeedbackSectorsRating();
			feedback_Sectors_rating.setSectorName(cat.get(k));
			feedback_Sectors_rating.setFeedbackSectorsRatingPercent(percent);
			newfd.add(feedback_Sectors_rating);
		}
		
		FeedbackSectorsRating feedback_Sectors_rating=new FeedbackSectorsRating();
		feedback_Sectors_rating.setSectorName("Overall Rating");
		FeedbackSectorsRatingPercent percent=new FeedbackSectorsRatingPercent();
		percent.setProductQuality(productQuality/7);
		percent.setDelivery(delivery/7);
		percent.setService(service/7);
		percent.setgetKnowledgeandBehaviorOfOurStaff(knowledge_and_Behavior_of_our_staff/7);
		percent.setCompetitiveness(competitiveness/7);
		feedback_Sectors_rating.setFeedbackSectorsRatingPercent(percent);
		newfd.add(feedback_Sectors_rating);
		
		try {
	    	Map m=new HashMap();
			m.put("data", newfd);
			m.put("status", true);
			return new ResponseEntity<Map>(m,HttpStatus.OK);
    	} catch (Exception e) {
			
			Map m=new HashMap();
			m.put("status", false);
			m.put("error",e.getMessage());
			return new ResponseEntity<Map>(m,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/report/feedBack/quarterly/chart/performanceWise")
	public ResponseEntity<Map> feedBackReportQuarterlyPerformanceWiseChart(
			@RequestParam("months") String months,
			@RequestParam("year") String year,
			@RequestParam("companyID") String companyID,
			@RequestParam("companyName") String companyName) throws IOException {
		
		if(months.isEmpty() || year.isEmpty()) {return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
		
		String date1 = null,date2=null,date3=null;
		List<FeedbackPerformance> FPData=new ArrayList<FeedbackPerformance>();
		switch (months) {
		case "JANUARY TO MARCH":
			date1=year+"-01";
			date2=year+"-02";
			date3=year+"-03";
			break;
			
		case "APRIL TO JUN":
			date1=year+"-04";
			date2=year+"-05";
			date3=year+"-06";
			break;
			
		case "JULY TO SEPTEMBER":
			date1=year+"-07";
			date2=year+"-08";
			date3=year+"-09";
			break;
			
		case "OCTOBER TO DECEMBER":
			date1=year+"-10";
			date2=year+"-11";
			date3=year+"-12";
			break;
			
		default:
			break;
		}
		
		List<Feedbacks> feedbacks = feedBackServices.getFeedback(date1, date2, date3,companyID);
		
		List<String> cat=new ArrayList<>();
		if(months.equals("JANUARY TO MARCH") || months.equals("JULY TO SEPTEMBER")) {
			cat.add("TRADE");
			cat.add("INSTITUTION");
			cat.add("EXPORT");
			cat.add("WCL");
			cat.add("MCL");
			cat.add("NCL");
			cat.add("SCCL");
		}else {
			cat.add("TRADE");
			cat.add("INSTITUTION");
			cat.add("EXPORT");
			cat.add("BCCL");
			cat.add("CCL");
			cat.add("ECL");
			cat.add("SECL");
		}
		List<String> opt=Arrays.asList("Product Quality","Delivery","Services","Knowledge & behaviour of Staff","Competitiveness");
		
		for(int k=0;k<5;k++)
		{
			List<SectrorAndPercent> list=new ArrayList<>();
			FeedbackPerformance newfp=new FeedbackPerformance();
			newfp.setId(k+1);
			newfp.setName(opt.get(k));
			for(int j=0;j<7;j++)
			{
				SectrorAndPercent sectror_and_Percent = new SectrorAndPercent();
				sectror_and_Percent.setName(cat.get(j));
				sectror_and_Percent.setPercent(FeedbackPerformance.FeedbackPerformancePercent(cat.get(j), opt.get(k), feedbacks));
				list.add(sectror_and_Percent);
			}
			newfp.setSectror_and_Percent(list);
			FPData.add(newfp);
		}
		
		try {
	    	Map m=new HashMap();
			m.put("data", FPData);
			m.put("status", true);
			return new ResponseEntity<Map>(m,HttpStatus.OK);
    	} catch (Exception e) {
			System.out.println("e >>>"+e);
			Map m=new HashMap();
			m.put("status", false);
			m.put("error",e.getMessage());
			return new ResponseEntity<Map>(m,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/report/feedBack/yearly/summery")
	public ResponseEntity<Resource> feedBackReportYearlySummery(
			@RequestParam("year") String year,
			@RequestParam("companyId") String companyId,
			@RequestParam("companyName") String companyName,HttpServletRequest request) throws IOException {
		
		if( year.isEmpty()) {return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
		
			String date1=year+"-01";
			String date2=year+"-02";
			String date3=year+"-03";
			String date4=year+"-04";
			String date5=year+"-05";
			String date6=year+"-06";
			String date7=year+"-07";
			String date8=year+"-08";
			String date9=year+"-09";
			String date10=year+"-10";
			String date11=year+"-11";
			String date12=year+"-12";
		
	
		
		FeedbacksQuarterlyTarget feedbacks_Quarterly_Target=feedbacksQuarterlyTargetRepo.findByYear(year);
		
		List<Feedbacks> FBQ1 = feedBackServices.getFeedback(date1, date2, date3,companyId);
		List<Feedbacks> FBQ2 = feedBackServices.getFeedback(date4, date5, date6,companyId);
		List<Feedbacks> FBQ3 = feedBackServices.getFeedback(date7, date8, date9,companyId);
		List<Feedbacks> FBQ4 = feedBackServices.getFeedback(date10, date11, date12,companyId);
		
		int Q1_T=0,Q2_T=0,Q3_T=0,Q4_T=0;
		int Q1_A=0,Q2_A=0,Q3_A=0,Q4_A=0;
		File file1 = new ClassPathResource("static/Feedback_report_summery.xlsx").getFile();
		FileInputStream file = new FileInputStream(file1);

		Workbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
		

		Cell cell = null;
		 
        Row row = sheet.getRow(0);
        row.getCell(1).setCellValue("CUSTOMER FEEDBACK PLAN -"+companyName);
        
        row = sheet.getRow(2);
        row.getCell(4).setCellValue("MONTHLY DETAILS OF FEEDBACK IN THE YEAR  -"+year);
        
        row = sheet.getRow(8);
	        row.getCell(5).setCellValue(feedbacks_Quarterly_Target.getTrade());
	        row.getCell(6).setCellValue(feedBackServices.CountFeedback("TRADE", FBQ1));
	        Q1_T=Q1_T+feedbacks_Quarterly_Target.getTrade();
	        Q1_A=Q1_A+feedBackServices.CountFeedback("TRADE", FBQ1);
	        
	        row.getCell(8).setCellValue(feedbacks_Quarterly_Target.getTrade());
	        row.getCell(9).setCellValue(feedBackServices.CountFeedback("TRADE", FBQ2));
	        Q2_T=Q2_T+feedbacks_Quarterly_Target.getTrade();
	        Q2_A=Q2_A+feedBackServices.CountFeedback("TRADE", FBQ2);
	        
	        row.getCell(11).setCellValue(feedbacks_Quarterly_Target.getTrade());
	        row.getCell(12).setCellValue(feedBackServices.CountFeedback("TRADE", FBQ3));
	        Q3_A=Q3_A+feedBackServices.CountFeedback("TRADE", FBQ3);
	        
	        row.getCell(14).setCellValue(feedbacks_Quarterly_Target.getTrade());
	        row.getCell(15).setCellValue(feedBackServices.CountFeedback("TRADE", FBQ4));
	        Q4_A=Q4_A+feedBackServices.CountFeedback("TRADE", FBQ4);
	        
	    row = sheet.getRow(12);
	        row.getCell(5).setCellValue(feedbacks_Quarterly_Target.getInstitution());
	        row.getCell(6).setCellValue(feedBackServices.CountFeedback("INSTITUTION", FBQ1));
	        Q1_T=Q1_T+feedbacks_Quarterly_Target.getInstitution();
	        Q1_A=Q1_A+feedBackServices.CountFeedback("INSTITUTION", FBQ1);
	        
	        row.getCell(8).setCellValue(feedbacks_Quarterly_Target.getInstitution());
	        row.getCell(9).setCellValue(feedBackServices.CountFeedback("INSTITUTION", FBQ2));
	        Q2_T=Q2_T+feedbacks_Quarterly_Target.getInstitution();
	        Q2_A=Q2_A+feedBackServices.CountFeedback("INSTITUTION", FBQ2);
	        
	        row.getCell(11).setCellValue(feedbacks_Quarterly_Target.getInstitution());
	        row.getCell(12).setCellValue(feedBackServices.CountFeedback("INSTITUTION", FBQ3));
	        Q3_A=Q3_A+feedBackServices.CountFeedback("INSTITUTION", FBQ3);
	        
	        row.getCell(14).setCellValue(feedbacks_Quarterly_Target.getInstitution());
	        row.getCell(15).setCellValue(feedBackServices.CountFeedback("INSTITUTION", FBQ4));
	        Q4_A=Q4_A+feedBackServices.CountFeedback("INSTITUTION", FBQ4);

//	Coal        
	    row = sheet.getRow(16);
	    	row.getCell(4).setCellValue("WCL");
	        row.getCell(5).setCellValue(feedbacks_Quarterly_Target.getWcl());
	        row.getCell(6).setCellValue(feedBackServices.CountFeedback("WCL", FBQ1));
	        Q1_T=Q1_T+feedbacks_Quarterly_Target.getWcl();
	        Q1_A=Q1_A+feedBackServices.CountFeedback("WCL", FBQ1);
	        
	        row.getCell(4).setCellValue("BCCL");
	        row.getCell(8).setCellValue(feedbacks_Quarterly_Target.getBccl());
	        row.getCell(9).setCellValue(feedBackServices.CountFeedback("BCCL", FBQ2));
	        Q2_T=Q2_T+feedbacks_Quarterly_Target.getBccl();
	        Q2_A=Q2_A+feedBackServices.CountFeedback("BCCL", FBQ2);
	        
	        row.getCell(4).setCellValue("WCL");
	        row.getCell(11).setCellValue(feedbacks_Quarterly_Target.getWcl());
	        row.getCell(12).setCellValue(feedBackServices.CountFeedback("WCL", FBQ3));
	        Q3_A=Q3_A+feedBackServices.CountFeedback("WCL", FBQ3);
	        
	        row.getCell(4).setCellValue("BCCL");
	        row.getCell(14).setCellValue(feedbacks_Quarterly_Target.getBccl());
	        row.getCell(15).setCellValue(feedBackServices.CountFeedback("BCCL", FBQ4));
	        Q4_A=Q4_A+feedBackServices.CountFeedback("BCCL", FBQ4);
	        
	    row = sheet.getRow(17);
	    	row.getCell(4).setCellValue("MCL");
	        row.getCell(5).setCellValue(feedbacks_Quarterly_Target.getMcl());
	        row.getCell(6).setCellValue(feedBackServices.CountFeedback("MCL", FBQ1));
	        Q1_T=Q1_T+feedbacks_Quarterly_Target.getMcl();
	        Q1_A=Q1_A+feedBackServices.CountFeedback("MCL", FBQ1);
	        
	        row.getCell(4).setCellValue("CCL");
	        row.getCell(8).setCellValue(feedbacks_Quarterly_Target.getCcl());
	        row.getCell(9).setCellValue(feedBackServices.CountFeedback("CCL", FBQ2));
	        Q2_T=Q2_T+feedbacks_Quarterly_Target.getCcl();
	        Q2_A=Q2_A+feedBackServices.CountFeedback("CCL", FBQ2);
	        
	        row.getCell(4).setCellValue("MCL");
	        row.getCell(11).setCellValue(feedbacks_Quarterly_Target.getMcl());
	        row.getCell(12).setCellValue(feedBackServices.CountFeedback("MCL", FBQ3));
	        Q3_A=Q3_A+feedBackServices.CountFeedback("MCL", FBQ3);
	        
	        row.getCell(4).setCellValue("CCL");
	        row.getCell(14).setCellValue(feedbacks_Quarterly_Target.getCcl());
	        row.getCell(15).setCellValue(feedBackServices.CountFeedback("CCL", FBQ4));
	        Q4_A=Q4_A+feedBackServices.CountFeedback("CCL", FBQ4);
	        
	   row = sheet.getRow(18);
	    	row.getCell(4).setCellValue("NCL");
	        row.getCell(5).setCellValue(feedbacks_Quarterly_Target.getNcl());
	        row.getCell(6).setCellValue(feedBackServices.CountFeedback("NCL", FBQ1));
	        Q1_T=Q1_T+feedbacks_Quarterly_Target.getNcl();
	        Q1_A=Q1_A+feedBackServices.CountFeedback("NCL", FBQ1);
	        
	        row.getCell(4).setCellValue("ECL");
	        row.getCell(8).setCellValue(feedbacks_Quarterly_Target.getEcl());
	        row.getCell(9).setCellValue(feedBackServices.CountFeedback("ECL", FBQ2));
	        Q2_T=Q2_T+feedbacks_Quarterly_Target.getEcl();
	        Q2_A=Q2_A+feedBackServices.CountFeedback("ECL", FBQ2);
	        
	        row.getCell(4).setCellValue("NCL");
	        row.getCell(11).setCellValue(feedbacks_Quarterly_Target.getNcl());
	        row.getCell(12).setCellValue(feedBackServices.CountFeedback("NCL", FBQ3));
	        Q3_A=Q3_A+feedBackServices.CountFeedback("NCL", FBQ3);
	        
	        row.getCell(4).setCellValue("ECL");
	        row.getCell(14).setCellValue(feedbacks_Quarterly_Target.getEcl());
	        row.getCell(15).setCellValue(feedBackServices.CountFeedback("ECL", FBQ4));
	        Q4_A=Q4_A+feedBackServices.CountFeedback("ECL", FBQ4);
	        
	    row = sheet.getRow(19);
	    	row.getCell(4).setCellValue("SCCL");
	        row.getCell(5).setCellValue(feedbacks_Quarterly_Target.getSccl());
	        row.getCell(6).setCellValue(feedBackServices.CountFeedback("SCCL", FBQ1));
	        Q1_T=Q1_T+feedbacks_Quarterly_Target.getSccl();
	        Q1_A=Q1_A+feedBackServices.CountFeedback("SCCL", FBQ1);
	        
	        row.getCell(4).setCellValue("SECL");
	        row.getCell(8).setCellValue(feedbacks_Quarterly_Target.getSecl());
	        row.getCell(9).setCellValue(feedBackServices.CountFeedback("SECL", FBQ2));
	        Q2_T=Q2_T+feedbacks_Quarterly_Target.getSecl();
	        Q2_A=Q2_A+feedBackServices.CountFeedback("SECL", FBQ2);
	        
	        row.getCell(4).setCellValue("SCCL");
	        row.getCell(11).setCellValue(feedbacks_Quarterly_Target.getSccl());
	        row.getCell(12).setCellValue(feedBackServices.CountFeedback("SCCL", FBQ3));
	        Q3_A=Q3_A+feedBackServices.CountFeedback("SCCL", FBQ3);
	        
	        row.getCell(4).setCellValue("SECL");
	        row.getCell(14).setCellValue(feedbacks_Quarterly_Target.getSecl());
	        row.getCell(15).setCellValue(feedBackServices.CountFeedback("SECL", FBQ4));
	        Q4_A=Q4_A+feedBackServices.CountFeedback("SECL", FBQ4);
//		end coal
	        
	   row = sheet.getRow(21);
	        row.getCell(5).setCellValue(feedbacks_Quarterly_Target.getExport());
	        row.getCell(6).setCellValue(feedBackServices.CountFeedback("EXPORT", FBQ1));
	        Q1_T=Q1_T+feedbacks_Quarterly_Target.getExport();
	        Q1_A=Q1_A+feedBackServices.CountFeedback("EXPORT", FBQ1);
	        
	        row.getCell(8).setCellValue(feedbacks_Quarterly_Target.getExport());
	        row.getCell(9).setCellValue(feedBackServices.CountFeedback("EXPORT", FBQ2));
	        Q2_T=Q2_T+feedbacks_Quarterly_Target.getExport();
	        Q2_A=Q2_A+feedBackServices.CountFeedback("EXPORT", FBQ2);
	        
	        row.getCell(11).setCellValue(feedbacks_Quarterly_Target.getExport());
	        row.getCell(12).setCellValue(feedBackServices.CountFeedback("EXPORT", FBQ3));
	        Q3_A=Q3_A+feedBackServices.CountFeedback("EXPORT", FBQ3);
	        
	        row.getCell(14).setCellValue(feedbacks_Quarterly_Target.getExport());
	        row.getCell(15).setCellValue(feedBackServices.CountFeedback("EXPORT", FBQ4));
	        Q4_A=Q4_A+feedBackServices.CountFeedback("EXPORT", FBQ4);
	        
	   row = sheet.getRow(23);
	        row.getCell(5).setCellValue(Q1_T);
	        row.getCell(6).setCellValue(Q1_A);
	        
	        row.getCell(8).setCellValue(Q2_T);
	        row.getCell(9).setCellValue(Q2_A);
	        
	        row.getCell(11).setCellValue(Q1_T);
	        row.getCell(12).setCellValue(Q3_A);
	        
	        row.getCell(14).setCellValue(Q2_T);
	        row.getCell(15).setCellValue(Q4_A);
	        
        file.close();
		
		for(int j=2;j < 19;j++) {
			sheet.autoSizeColumn(j);
		}
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		wb.write(os);
		wb.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		String fileName="FEEDBACK_Summary_Report_"+year+".xlsx";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName+"");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}
	
	
}
