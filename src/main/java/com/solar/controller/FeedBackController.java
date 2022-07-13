package com.solar.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solar.model.Feedbacks;
import com.solar.model.FeedbacksQuarterlyTarget;
import com.solar.repository.FeedbackRepository;
import com.solar.repository.FeedbacksQuarterlyTargetRepository;
import com.solar.serviceImpl.FeedBackServicesImpl;
import com.solar.serviceImpl.FileStorageServiceImpl;
import com.solar.utils.PropertyValues;
import com.solar.voObject.ResponseVo;
import com.solar.voObject.ResponseVoObj;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeedBackController {

	private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);
	
	@Autowired
	FeedBackServicesImpl feedBackServices;
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	public FileStorageServiceImpl fileStorageService;
	
	@Autowired
	FeedbacksQuarterlyTargetRepository feedbacksQuarterlyTargetRepository;
	
	@Autowired
	PropertyValues propertyValues;

	@PostMapping("/addFeedBack")
	public ResponseVo addFeedBack(@RequestParam(value = "signature", required = false) MultipartFile signature,
			@RequestParam(value = "uploadAttachment", required = false) MultipartFile uploadAttachment,
			@RequestParam("feedBackJSON") String feedBackJSON) {
		ResponseVo response = new ResponseVo();
		try {
			Feedbacks feedbacks = feedBackServices.getFeedbacksJSON(feedBackJSON);
			if (feedbacks.getCompanyId() == null) {
				response.setStatus(false);
				response.setMessage("Inavlid Fields!!");
				response.setId(feedbacks.getId());
				return response;
			}
			if (feedbacks.getId() == 0) {
				feedbacks.setDateOfCreate(new Date());
				feedbacks.setFeedBackStatus("New");
				feedbackRepository.save(feedbacks);
				int id = feedbacks.getId();

				String fileName1 = signature == null ? ""
						: fileStorageService.storeFile(signature, "FBsignature_" + id);
				String fileName2 = uploadAttachment == null ? ""
						: fileStorageService.storeFile(uploadAttachment, "FBuploadAttachment" + id);

				feedbacks.setSignature(fileName1);
				feedbacks.setUploadAttachment(fileName2);

				feedbackRepository.save(feedbacks);
				feedbackRepository.updateCreateDate(id);

				response.setStatus(true);
				response.setMessage("Success");
				response.setId(id);
				return response;
			} else {

				Feedbacks feedbacks2 = feedBackServices.getFeedbacksSimple(feedbacks.getId());
				System.out.println("feedbacks2 >" + feedbacks2);
				if (feedbacks2 == null) {
					response.setStatus(false);
					response.setMessage("Inavlid ID !!");
					response.setId(feedbacks.getId());
					return response;
				}

				String fileName1 = signature == null ? ""
						: fileStorageService.storeFile(signature, "FBsignature_" + feedbacks.getId());
				String fileName2 = uploadAttachment == null ? ""
						: fileStorageService.storeFile(uploadAttachment, "FBupload_attachment" + feedbacks.getId());

				if (fileName1.isEmpty()) {
				} else {
					feedbacks2.setSignature(fileName1);
				}
				if (fileName2.isEmpty()) {
				} else {
					feedbacks2.setUploadAttachment(fileName2);
				}

				feedbacks2.setCompanyId(feedbacks.getCompanyId());
				feedbacks2.setCompanyName(feedbacks.getCompanyName());
				feedbacks2.setDate(feedbacks.getDate());
				feedbacks2.setNameOfMine(feedbacks.getNameOfMine());
				feedbacks2.setContactPersonName(feedbacks.getContactPersonName());
				feedbacks2.setDesignation(feedbacks.getDesignation());
				feedbacks2.setDepartment(feedbacks.getDepartment());
				feedbacks2.setProductUsed(feedbacks.getProductUsed());

				feedbacks2.setProductQuality1(feedbacks.getProductQuality1());
				feedbacks2.setProductQuality2(feedbacks.getProductQuality2());
				feedbacks2.setDelivery1(feedbacks.getDelivery1());
				feedbacks2.setDelivery2(feedbacks.getDelivery2());
				feedbacks2.setDelivery3(feedbacks.getDelivery3());
				feedbacks2.setService1(feedbacks.getService1());
				feedbacks2.setService2(feedbacks.getService2());
				feedbacks2.setService3(feedbacks.getService3());
				feedbacks2.setBehaviourOfOurStaff1(feedbacks.getBehaviourOfOurStaff1());
				feedbacks2.setBehaviourOfOurStaff2(feedbacks.getBehaviourOfOurStaff2());
				feedbacks2.setBehaviourOfOurStaff3(feedbacks.getBehaviourOfOurStaff3());
				feedbacks2.setCompetitiveness1(feedbacks.getCompetitiveness1());
				feedbacks2.setCompetitiveness2(feedbacks.getCompetitiveness2());
				feedbacks2.setAnyOtherComments1(feedbacks.getAnyOtherComments1());
				feedbacks2.setAnyOtherComments2(feedbacks.getAnyOtherComments2());
				feedbacks2.setAnyOtherComments3(feedbacks.getAnyOtherComments3());
				feedbacks2.setAnyOtherComments4(feedbacks.getAnyOtherComments4());
				feedbacks2.setAnyOtherComments5(feedbacks.getAnyOtherComments5());
				feedbacks2.setUploadedBy(feedbacks.getUploadedBy());
				feedbacks2.setSectorId(feedbacks.getSectorId());
				feedbacks2.setSectorName(feedbacks.getSectorName());
				feedbackRepository.save(feedbacks2);
				
				response.setStatus(true);
				response.setMessage(propertyValues.getSuccessMessage());
				response.setId(feedbacks.getId());
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@GetMapping("/feedBacks")
	public ResponseVo feedBacks() {
		ResponseVo response = new ResponseVo();
		try {
			response.setData(feedbackRepository.findAllByOrderByIdDesc());
			response.setStatus(true);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@GetMapping("/feedBacks/{id}")
	public ResponseVoObj feedBacks(@PathVariable String id) {
		ResponseVoObj response = new ResponseVoObj();
		try {
			response.setData(feedBackServices.getFeedbacks(Integer.parseInt(id)));
			response.setStatus(true);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@PutMapping("/feedBacks/update/{id}")
	public ResponseVo feedBacksStatus(@PathVariable String id, @RequestBody Feedbacks feedbacks) {
		ResponseVo response = new ResponseVo();
		try {
			Feedbacks feedbacks2 = feedBackServices.getFeedbacksSimple(Integer.parseInt(id));
			feedbacks2.setFeedBackStatus(feedbacks.getFeedBackStatus());
			feedbacks2.setNameOfLevel1(feedbacks.getNameOfLevel1());
			feedbacks2.setDateOfLevel1(new Date());
			feedbackRepository.save(feedbacks2);
			feedbackRepository.updateLevenOneDate(Integer.parseInt(id));
			response.setStatus(true);
			response.setMessage("Success");
			return response;
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@PostMapping("/feedbacksQuarterlyTarget/add")
	public ResponseVo feedbacksQuarterlyTarget(
			@RequestBody FeedbacksQuarterlyTarget feedbacks_Quarterly_Target) {
		ResponseVo response = new ResponseVo();
		FeedbacksQuarterlyTarget a = null;
		Map m = new HashMap();
		try {
			a = feedbacksQuarterlyTargetRepository.findByYear(feedbacks_Quarterly_Target.getYear());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Date Not found!!!");
		}
		if (a == null) {
			feedbacks_Quarterly_Target.setCreateAt(new Date());
			feedbacksQuarterlyTargetRepository.save(feedbacks_Quarterly_Target);
			response.setStatus(true);
			response.setMessage("Success");
			return response;
		} else {
			response.setStatus(false);
			response.setMessage("Year Already Available")	;
			return response;
		}
	}

	@GetMapping("/feedbacksQuarterlyTarget/list")
	public ResponseVoObj feedbacksQuarterlyTargetList() {
		ResponseVoObj response = new ResponseVoObj();
		response.setData(feedbacksQuarterlyTargetRepository.findAll());
		response.setStatus(true);
		return response;
	}

	@GetMapping("/feedbacksQuarterlyTarget/{id}")
	public ResponseVoObj feedbacksQuarterlyTargetById(@PathVariable String id) {
		ResponseVoObj response = new ResponseVoObj();
		try {
			response.setData(feedbacksQuarterlyTargetRepository.findById(Integer.parseInt(id)));
			response.setStatus(true);
			return response;
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@PutMapping("feedbacksQuarterlyTarget/{id}")
	public ResponseVo updateAdmin(@RequestBody FeedbacksQuarterlyTarget feedbacksQuarterlyTargetRequestObj,
			@PathVariable String id) {
		ResponseVo response = new ResponseVo();
		Optional<FeedbacksQuarterlyTarget> opt = feedbacksQuarterlyTargetRepository.findById(Integer.parseInt(id));
		if (opt.isPresent()) {
			FeedbacksQuarterlyTarget feedbacksQuarterlyTarget = opt.get();
			feedbacksQuarterlyTarget.setInstitution(feedbacksQuarterlyTargetRequestObj.getInstitution());
			feedbacksQuarterlyTarget.setTrade(feedbacksQuarterlyTargetRequestObj.getTrade());
			feedbacksQuarterlyTarget.setExport(feedbacksQuarterlyTargetRequestObj.getExport());
			feedbacksQuarterlyTarget.setWcl(feedbacksQuarterlyTargetRequestObj.getWcl());
			feedbacksQuarterlyTarget.setMcl(feedbacksQuarterlyTargetRequestObj.getMcl());
			feedbacksQuarterlyTarget.setNcl(feedbacksQuarterlyTargetRequestObj.getNcl());
			feedbacksQuarterlyTarget.setSccl(feedbacksQuarterlyTargetRequestObj.getSccl());
			feedbacksQuarterlyTarget.setBccl(feedbacksQuarterlyTargetRequestObj.getBccl());
			feedbacksQuarterlyTarget.setCcl(feedbacksQuarterlyTargetRequestObj.getCcl());
			feedbacksQuarterlyTarget.setEcl(feedbacksQuarterlyTargetRequestObj.getEcl());
			feedbacksQuarterlyTarget.setSecl(feedbacksQuarterlyTargetRequestObj.getSecl());
			feedbacksQuarterlyTargetRepository.save(feedbacksQuarterlyTarget);
			response.setStatus(true);
			response.setMessage(propertyValues.getUpdateSucess());
			return response;
		} else {
			response.setStatus(false);
			response.setMessage(propertyValues.getError());
			return response;
		}

	}

	@GetMapping("/report/feedBacks/")
	public ResponseEntity<Resource> generateExcelReport() throws IOException {
		List<Feedbacks> feedbacks = feedbackRepository.findAllByOrderByIdDesc();
		Field[] fields = Feedbacks.class.getDeclaredFields();
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();

		CreationHelper createHelper = wb.getCreationHelper();

		int rowCount = 0;
		Row row = sheet.createRow(rowCount++);

		Font font = wb.createFont();
		font.setBold(true);

		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THICK);
		cellStyle.setBorderBottom(BorderStyle.THICK);
		cellStyle.setBorderLeft(BorderStyle.THICK);
		cellStyle.setBorderRight(BorderStyle.THICK);
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		Cell cell;
		for (int i = 0; i < fields.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(fields[i].getName().toString());
			cell.setCellStyle(cellStyle);

		}

		cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);

		CellStyle cellStyleDate = wb.createCellStyle();
		cellStyleDate.setBorderTop(BorderStyle.THIN);
		cellStyleDate.setBorderBottom(BorderStyle.THIN);
		cellStyleDate.setBorderLeft(BorderStyle.THIN);
		cellStyleDate.setBorderRight(BorderStyle.THIN);
		cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("d-mmm-yyyy"));

		for (Feedbacks feedback : feedbacks) {
			row = sheet.createRow(rowCount++);

			int columnCount = 0;

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getId());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getCompanyId());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getCompanyName());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getDate());
			cell.setCellStyle(cellStyleDate);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getNameOfMine());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getContactPersonName());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getDesignation());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getDepartment());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getSignature());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(Arrays.toString(feedback.getProductUsed()));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getProductQuality1());
			cell.setCellStyle(cellStyle);

//				10

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getProductQuality2());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getDelivery1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getDelivery2());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getDelivery3());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getService1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getService2());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getService3());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getBehaviourOfOurStaff1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getBehaviourOfOurStaff2());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getBehaviourOfOurStaff3());
			cell.setCellStyle(cellStyle);

//				20

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getCompetitiveness1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getCompetitiveness2());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getAnyOtherComments1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getAnyOtherComments2());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getAnyOtherComments3());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getAnyOtherComments4());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getAnyOtherComments5());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getUploadAttachment());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getUploadedBy());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getDateOfCreate());
			cell.setCellStyle(cellStyleDate);

//				30

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getDateOfLevel1());
			cell.setCellStyle(cellStyleDate);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getNameOfSalesPerson());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getNameOfLevel1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(feedback.getFeedBackStatus());
			cell.setCellStyle(cellStyle);

		}
		for (int j = 0; j < fields.length; j++) {
			sheet.autoSizeColumn(j);
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		wb.write(os);
		wb.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=FeedBackExcelReport.xlsx");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}
}
