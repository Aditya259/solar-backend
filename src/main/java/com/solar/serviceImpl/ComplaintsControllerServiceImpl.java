package com.solar.serviceImpl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.solar.model.ComplaintAttachments;
import com.solar.model.Complaints;
import com.solar.repository.ComplaintAttachmentsRepository;
import com.solar.repository.ComplaintRepository;
import com.solar.service.ComplaintsService;
import com.solar.utils.PropertyValues;

@Service
public class ComplaintsControllerServiceImpl implements ComplaintsService {

	@Autowired
	ComplaintServicesImpl complaintServices;

	@Autowired
	ComplaintRepository complaintRepository;

	@Autowired
	public FileStorageServiceImpl fileStorageService;
	
	@Autowired
	ComplaintAttachmentsRepository complaintAttachmentsRepository;
	
	@Autowired
	PropertyValues propertyValues;

	@Override
	public Complaints addComplaint(String complaintsJSON, MultipartFile uploadAttachment, MultipartFile uploadRe1,
			MultipartFile uploadRe2) {
		if (complaintsJSON.isEmpty()) {
			return null;
		}
		Complaints complaints;
		complaints = complaintServices.getComplaintsJSON(complaintsJSON);
		if (complaints.getId() == 0) {
			complaints.setDateOfCreate(new Date());
			complaintRepository.save(complaints);
			int id = complaints.getId();
			String fileName = uploadAttachment == null ? ""
					: fileStorageService.storeFile(uploadAttachment, propertyValues.getUploadAttachmentComplaints() + id);
			String fileName1 = uploadRe1 == null ? "" : fileStorageService.storeFile(uploadRe1, propertyValues.getUploadRe1Complaints() + id);
			String fileName2 = uploadRe2 == null ? ""
					: fileStorageService.storeFile(uploadRe2, propertyValues.getUploadRe2Complaints() + id);
			complaints.setUploadAttachment(fileName);
			complaints.setUploadRe1(fileName1);
			complaints.setUploadRe2(fileName2);
			complaints.setComplaintStatus("New");
			Complaints complaintsSave = complaintRepository.save(complaints);
			Complaints complaintsUpdate = complaintRepository.updateCreateDate(id);
			complaints.setId(complaintsUpdate.getId());
			return complaints;
		} else {
			complaints = complaintServices.getComplaintSimple(complaints.getId());
			Complaints complaints2 = complaintServices.getComplaintsJSON(complaintsJSON);

			String fileName = uploadAttachment == null ? ""
					: fileStorageService.storeFile(uploadAttachment, propertyValues.getUploadAttachmentComplaints() + complaints.getId());
			String fileName1 = uploadRe1 == null ? ""
					: fileStorageService.storeFile(uploadRe1, propertyValues.getUploadRe1Complaints() + complaints.getId());
			String fileName2 = uploadRe2 == null ? ""
					: fileStorageService.storeFile(uploadRe2, propertyValues.getUploadRe2Complaints() + complaints.getId());

			if (fileName.isEmpty()) {
			} else {
				complaints.setUploadAttachment(fileName);
			}
			if (fileName1.isEmpty()) {
			} else {
				complaints.setUploadRe1(fileName1);
			}
			if (fileName2.isEmpty()) {
			} else {
				complaints.setUploadRe2(fileName2);
			}

			complaints.setCustomerId(complaints2.getCustomerId());
			complaints.setCustomerName(complaints2.getCustomerName());
			complaints.setDate(complaints2.getDate());
			complaints.setNameOfOrganization(complaints2.getNameOfOrganization());
			complaints.setNameOfMine(complaints2.getNameOfMine());
			complaints.setCategoryOfComplaint(complaints2.getCategoryOfComplaint());
			complaints.setNameOfProduct(complaints2.getNameOfProduct());
			complaints.setDesciptionOfProduct(complaints2.getDesciptionOfProduct());
			complaints.setNameOfContactPersonCustomer(complaints2.getNameOfContactPersonCustomer());
			complaints.setMobileNo(complaints2.getMobileNo());

			complaints.setBatchNo(complaints2.getBatchNo());
			complaints.setCaseNo(complaints2.getBatchNo());
			complaints.setDateOfManufacturing(complaints2.getDateOfManufacturing());
			complaints.setNatureOfComplaint(complaints2.getNatureOfComplaint());
			complaints.setType(complaints2.getType());
			complaints.setRemark(complaints2.getRemark());
			complaints.setInvoiceNo(complaints2.getInvoiceNo());
			complaintRepository.save(complaints);

			return complaints;

		}
	}

	@Override
	public Complaints complaintSalePersonUpdate(MultipartFile uploadAttachment,MultipartFile uploadRe1, MultipartFile uploadRe2,String id,String complaintsJSON) {
		Complaints complaints = complaintServices.getComplaintSimple(Integer.parseInt(id));
		Complaints complaints2 = complaintServices.getComplaintsJSON(complaintsJSON);

		String fileName = uploadAttachment == null ? ""
				: fileStorageService.storeFile(uploadAttachment, propertyValues.getUploadAttachmentComplaints() + id);
		String fileName1 = uploadRe1 == null ? ""
				: fileStorageService.storeFile(uploadRe1, propertyValues.getUploadRe1Complaints() + id);
		String fileName2 = uploadRe2 == null ? ""
				: fileStorageService.storeFile(uploadRe2, propertyValues.getUploadRe2Complaints() + id);

		if (fileName.isEmpty()) {
		} else {
			complaints.setUploadAttachment(fileName);
		}
		if (fileName1.isEmpty()) {
		} else {
			complaints.setUploadRe1(fileName1);
		}
		if (fileName2.isEmpty()) {
		} else {
			complaints.setUploadRe2(fileName2);
		}

		complaints.setCustomerId(complaints2.getCustomerId());
		complaints.setCustomerName(complaints2.getCustomerName());
		complaints.setDate(complaints2.getDate());
		complaints.setNameOfOrganization(complaints2.getNameOfOrganization());
		complaints.setNameOfMine(complaints2.getNameOfMine());
		complaints.setCategoryOfComplaint(complaints2.getCategoryOfComplaint());
		complaints.setNameOfProduct(complaints2.getNameOfProduct());
		complaints.setDesciptionOfProduct(complaints2.getDesciptionOfProduct());
		complaints.setNameOfContactPersonCustomer(complaints2.getNameOfContactPersonCustomer());
		complaints.setMobileNo(complaints2.getMobileNo());

		complaints.setBatchNo(complaints2.getBatchNo());
		complaints.setCaseNo(complaints2.getBatchNo());
		complaints.setDateOfManufacturing(complaints2.getDateOfManufacturing());
		complaints.setNatureOfComplaint(complaints2.getNatureOfComplaint());
		complaints.setType(complaints2.getType());
		complaints.setRemark(complaints2.getRemark());
		complaints.setInvoiceNo(complaints2.getInvoiceNo());
		complaintRepository.save(complaints);
		return null;
	}

	@Override
	public void complaintUpdateLevel(Complaints complaints,String id) {
		Complaints complaints2 = complaintServices.getComplaintSimple(Integer.parseInt(id));
		complaints2.setApprovalStageDepartment(complaints.getApprovalStageDepartment());
		complaints2.setApprovalStageNotes(complaints.getApprovalStageNotes());
		complaints2.setApprovalStageForwardTo(complaints.getApprovalStageForwardTo());
		complaints2.setApprovalStageStatus(complaints.getApprovalStageStatus());
		complaints2.setComplaintStatus(complaints.getApprovalStageStatus());
		complaints2.setDateOfLevel1(new Date());
		complaints2.setNameOfLevel1(complaints.getNameOfLevel1());
		complaintRepository.save(complaints2);
		complaintRepository.updateLevenOneDate(Integer.parseInt(id));
		
	}

	@Override
	public void complaintUpdateLevel(String complaintsJSON,MultipartFile uploadAttachment1,MultipartFile uploadAttachment2,MultipartFile uploadAttachment3,MultipartFile uploadAttachment4,String id) {
		Complaints complaints = complaintServices.getComplaintsJSON(complaintsJSON);
		Complaints complaints2 = complaintServices.getComplaintSimple(Integer.parseInt(id));
		complaints2.setNameOfEngineer(complaints.getNameOfEngineer());
		complaints2.setNameOfContactPersonEngineer(complaints.getNameOfContactPersonEngineer());
		complaints2.setConclusion(complaints.getConclusion());
		complaints2.setCorrectionActivity(complaints.getCorrectionActivity());
		complaints2.setClouser(complaints.getClouser());
		complaints2.setRecipient(complaints.getRecipient());
		String fileName1 = uploadAttachment1 == null ? ""
				: fileStorageService.storeFile(uploadAttachment1, propertyValues.getUploadAttachment1() + id);
		String fileName2 = uploadAttachment2 == null ? ""
				: fileStorageService.storeFile(uploadAttachment2, propertyValues.getUploadAttachment2() + id);
		String fileName3 = uploadAttachment3 == null ? ""
				: fileStorageService.storeFile(uploadAttachment3, propertyValues.getUploadAttachment3() + id);
		String fileName4 = uploadAttachment4 == null ? ""
				: fileStorageService.storeFile(uploadAttachment4, propertyValues.getUploadAttachment4() + id);
		complaints2.setUploadAttachment1(fileName1);
		complaints2.setUploadAttachment2(fileName2);
		complaints2.setUploadAttachment3(fileName3);
		complaints2.setUploadAttachment4(fileName4);
		complaints2.setNameOfLevel4(complaints.getNameOfLevel2());
		complaintRepository.save(complaints2);
		complaintRepository.updateLevenFourDate(Integer.parseInt(id));
		
	}

	@Override
	public void complaintUpdatelevelReport(String complaintsJSON, String id, MultipartFile hodSignature,
			MultipartFile inChargeSignature, MultipartFile complaintReportAttachment) {
		
		Complaints complaints = complaintServices.getComplaintsJSON(complaintsJSON);
		Complaints complaints2 = complaintServices.getComplaintSimple(Integer.parseInt(id));
		complaints2.setCompanyId(complaints.getCompanyId());
		complaints2.setCompanyName(complaints.getCompanyName());
		complaints2.setPlantId(complaints.getPlantId());
		complaints2.setPlantName(complaints.getPlantName());
		complaints2.setReportDocumentNo(complaints.getReportDocumentNo());
		complaints2.setDateOfRecieptOfComplaint(complaints.getDateOfRecieptOfComplaint());
		complaints2.setNatureComplaint(complaints.getNatureComplaint());
		complaints2.setQtyDispatched(complaints.getQtyDispatched());
		complaints2.setDateOfDispatch(complaints.getDateOfDispatch());
		complaints2.setQtyDefective(complaints.getQtyDefective());
		complaints2.setComplaintInvestigationFindingRemark(complaints.getComplaintInvestigationFindingRemark());
		complaints2.setCorrectiveActionIfAny(complaints.getCorrectiveActionIfAny());
		complaints2.setVerificationOfEffectivenessOfCorrectionActions(
				complaints.getVerificationOfEffectivenessOfCorrectionActions());
		complaints2.setComplaintValidatedBy(complaints.getComplaintValidatedBy());
		complaints2.setComplaintAttendedByIfAny(complaints.getComplaintAttendedByIfAny());
		complaints2.setComplaintJustifiedNotJustified(complaints.getComplaintJustifiedNotJustified());
		String fileName1 = complaintReportAttachment == null ? ""
				: fileStorageService.storeFile(complaintReportAttachment, propertyValues.getComplaintReportAttachment()+ id);
		String fileName2 = inChargeSignature == null ? ""
				: fileStorageService.storeFile(inChargeSignature, propertyValues.getInChargeSignature() + id);
		String fileName3 = hodSignature == null ? ""
				: fileStorageService.storeFile(hodSignature, propertyValues.getHodSignature()+ id);
		complaints2.setComplaintReportAttachment(fileName1);
		complaints2.setInChargeSignature(fileName2);
		complaints2.setHodSignature(fileName3);
		complaints2.setDateOfLevel2(new Date());
		complaints2.setNameOfLevel2(complaints.getNameOfLevel2());
		complaintRepository.save(complaints2);
		complaintRepository.updateLevenTwoDate(Integer.parseInt(id));
		
	}

	@Override
	public void complaintUpdateAssignSellPerson(Complaints complaints, String id) {
		Complaints complaints2 = complaintServices.getComplaintSimple(Integer.parseInt(id));
		complaints2.setApprovalStageSellPersonId(complaints.getApprovalStageSellPersonID());
		complaints2.setApprovalStageNotesSellPerson(complaints.getApprovalStageNotesSellPerson());
		complaints2.setApprovalStageForwardToSellPerson(complaints.getApprovalStageForwardToSellPerson());
		complaints2.setApprovalStageStatusSellPerson(complaints.getApprovalStageStatusSellPerson());
		complaints2.setNameOfLevel3(complaints.getNameOfLevel3());
		complaintRepository.save(complaints2);
		complaintRepository.updateLevenThreeDate(Integer.parseInt(id));
	}

	@Override
	public void complaintUpdateLevel(MultipartFile clouserAttachment, String complaintsJSON, String id) {
		Complaints complaints = complaintServices.getComplaintsJSON(complaintsJSON);
		Complaints complaints2 = complaintServices.getComplaintSimple(Integer.parseInt(id));
		complaints2.setClouserRemark1(complaints.getClouserRemark1());
		complaints2.setClouserRemark2(complaints.getClouserRemark2());
		complaints2.setClouserRemark3(complaints.getClouserRemark3());
		complaints2.setClouserRemark4(complaints.getClouserRemark4());
		String fileName1 = clouserAttachment == null ? ""
				: fileStorageService.storeFile(clouserAttachment, propertyValues.getClouserAttachment() + id);
		complaints2.setClouserAttachment(fileName1);
		complaints2.setDateOfClosed(new Date());
		complaints2.setNameOfClosed(complaints.getNameOfClosed());
		complaints2.setComplaintStatus("Closed");
		complaintRepository.save(complaints2);
		complaintRepository.updateLevenCloseDate(Integer.parseInt(id));
	}

	@Override
	public void complaintAddAttachments(MultipartFile attachment, String complaintsAttachmentJSON) {
		ComplaintAttachments complaint_Attachments = complaintServices
				.getComplaintsAttachmentJSON(complaintsAttachmentJSON);
		complaint_Attachments.setCreatedDate(new Date());
		complaintAttachmentsRepository.save(complaint_Attachments);
		int id = complaint_Attachments.getId();
		String fileName1 = fileStorageService.storeFile(attachment, propertyValues.getComAttAttachments()+ id);
		complaint_Attachments.setAttachment(fileName1);
		complaintAttachmentsRepository.save(complaint_Attachments);
	}

	@Override
	public Workbook generateExcelReport() {
		List<Complaints> complaints = complaintRepository.findAllByOrderByIdDesc();
		Field[] fields = Complaints.class.getDeclaredFields();

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

		for (Complaints complaint : complaints) {
			row = sheet.createRow(rowCount++);

			int columnCount = 0;

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getId());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getCustomerId());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getCustomerName());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDate());
			cell.setCellStyle(cellStyleDate);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNameOfOrganization());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNameOfMine());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getCategoryOfComplaint());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(Arrays.toString(complaint.getNameOfProduct()));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDesciptionOfProduct());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNameOfContactPersonCustomer());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getMobileNo());
			cell.setCellStyle(cellStyle);

//			10

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getBatchNo());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getCaseNo());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDateOfManufacturing());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNatureOfComplaint());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getType());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getRemark());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getInvoiceNo());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getUploadAttachment());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getUploadRe1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getUploadRe2());
			cell.setCellStyle(cellStyle);

//			20

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getApprovalStageDepartment());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getApprovalStageNotes());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getApprovalStageForwardTo());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getApprovalStageStatus());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNameOfEngineer());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNameOfContactPersonEngineer());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getConclusion());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getCorrectionActivity());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getClouser());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getRecipient());
			cell.setCellStyle(cellStyle);

//			30

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getUploadAttachment1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getUploadAttachment2());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getUploadAttachment3());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getUploadAttachment4());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getCompanyId());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getCompanyName());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getPlantId());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getPlantName());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getReportDocumentNo());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDateOfRecieptOfComplaint());
			cell.setCellStyle(cellStyle);

//			40

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNatureComplaint());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getQtyDispatched());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDateOfDispatch());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getQtyDefective());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getComplaintInvestigationFindingRemark());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getCorrectiveActionIfAny());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getVerificationOfEffectivenessOfCorrectionActions());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getComplaintValidatedBy());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getComplaintAttendedByIfAny());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getComplaintJustifiedNotJustified());
			cell.setCellStyle(cellStyle);

//			50

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getComplaintReportAttachment());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getInChargeSignature());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getHodSignature());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getClouserRemark1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getClouserRemark2());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getClouserRemark3());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getClouserRemark4());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getClouserAttachment());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDateOfCreate());
			cell.setCellStyle(cellStyleDate);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDateOfLevel1());
			cell.setCellStyle(cellStyleDate);

//			60

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDateOfLevel2());
			cell.setCellStyle(cellStyleDate);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDateOfLevel3());
			cell.setCellStyle(cellStyleDate);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getDateOfClosed());
			cell.setCellStyle(cellStyleDate);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNameOfSalesPerson());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNameOfLevel1());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNameOfLevel2());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getNameOfLevel3());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(complaint.getComplaintStatus());
			cell.setCellStyle(cellStyle);

		}
		for (int j = 0; j < fields.length; j++) {
			sheet.autoSizeColumn(j);
		}

		return wb;
	}

}
