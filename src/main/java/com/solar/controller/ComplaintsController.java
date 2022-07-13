package com.solar.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.solar.model.Complaints;
import com.solar.repository.AdminRepository;
import com.solar.repository.ComplaintAttachmentsRepository;
import com.solar.repository.ComplaintRepository;
import com.solar.repository.DepartmentRepository;
import com.solar.repository.FeedbackRepository;
import com.solar.repository.FeedbacksQuarterlyTargetRepository;
import com.solar.repository.SectorRepository;
import com.solar.serviceImpl.AdminServicesImpl;
import com.solar.serviceImpl.ComplaintServicesImpl;
import com.solar.serviceImpl.ComplaintsControllerServiceImpl;
import com.solar.serviceImpl.DepartmentServicesImpl;
import com.solar.serviceImpl.FeedBackServicesImpl;
import com.solar.serviceImpl.FileStorageServiceImpl;
import com.solar.utils.PropertyValues;
import com.solar.voObject.ResponseVo;
import com.solar.voObject.ResponseVoObj;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComplaintsController {

	@Autowired
	ComplaintsControllerServiceImpl complaintsControllerServiceImpl;
	
	@Autowired
	PropertyValues propertyValues;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AdminServicesImpl adminServices;

	@Autowired
	public FileStorageServiceImpl fileStorageService;
	
	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	ComplaintServicesImpl complaintServices;
	
	@Autowired
	ComplaintAttachmentsRepository complaintAttachmentsRepository;
	
	@Autowired
	FeedBackServicesImpl feedBackServices;
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DepartmentServicesImpl departmentServices;
	
	@Autowired
	SectorRepository sectorRepository;
	
	@Autowired
	FeedbacksQuarterlyTargetRepository feedbacksQuarterlyTargetRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(ComplaintsController.class);

	@PostMapping("/addComplaint")
	public ResponseVo addComplaint(
			@RequestParam(value = "uploadAttachment", required = false) MultipartFile uploadAttachment,
			@RequestParam(value = "uploadRe1", required = false) MultipartFile uploadRe1,
			@RequestParam(value = "uploadRe2", required = false) MultipartFile uploadRe2,
			@RequestParam("complaintsJSON") String complaintsJSON)
			throws JsonMappingException, JsonProcessingException {
		ResponseVo response = new ResponseVo();
		try {
			Complaints complaints = complaintsControllerServiceImpl.addComplaint(complaintsJSON, uploadAttachment,
					uploadRe1, uploadRe2);
			if (complaints == null) {
				response.setStatus(false);
				response.setMessage(propertyValues.getErrorMessage());
				return response;
			} else {
				response.setStatus(true);
				response.setMessage(propertyValues.getSuccessMessage());
				response.setId(complaints.getId());
				return response;
			}
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}

	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
			LOGGER.info(propertyValues.getFieTypeError());
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("/complaints")
	public ResponseVo complaints() {
		ResponseVo response = new ResponseVo();
		try {
			response.setData(complaintRepository.findAllByOrderByIdDesc());
			response.setStatus(true);
			return response;
		} catch (Exception e) {
			response.setError(e.getMessage());
			response.setStatus(false);
			return response;
		}
	}

	@GetMapping("/complaintsByDepartments")
	public ResponseVo complaintsByDepartments(@RequestParam("department") String department) {
		ResponseVo response = new ResponseVo();
		try {
			response.setData(complaintRepository.findByApprovalStageDepartmentOrderByIdDesc(department));
			response.setStatus(true);
			return response;
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@GetMapping("/complaintsBySellsPerson")
	public ResponseVo complaintsBySellsPerson(@RequestParam("approvalStageSellPersonId") String sId) {
		ResponseVo response = new ResponseVo();
		try {
			response.setData(complaintRepository.findByapprovalStageSellPersonIdOrderByIdDesc(Integer.parseInt(sId)));
			response.setStatus(true);
			return response;
		} catch (Exception e) {
			response.setError(e.getMessage());
			response.setStatus(false);
			return response;
		}
	}

	@GetMapping("/complaints/{id}")
	public ResponseVoObj complaint(@PathVariable String id) {
		ResponseVoObj response = new ResponseVoObj();
		try {
			response.setData(complaintServices.getComplaint(Integer.parseInt(id)));
			response.setStatus(true);
			response.setAttachments(complaintServices.getComplaintAttachments(id));
			return response;
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@PutMapping("/complaint/salesPerson/update/{id}")
	public ResponseVo complaintSalePersonUpdate(
			@RequestParam(value = "uploadAttachment", required = false) MultipartFile uploadAttachment,
			@RequestParam(value = "uploadRe1", required = false) MultipartFile uploadRe1,
			@RequestParam(value = "uploadRe2", required = false) MultipartFile uploadRe2,
			@RequestParam("complaintsJSON") String complaintsJSON, @PathVariable String id)
			throws JsonMappingException, JsonProcessingException {
		ResponseVo response = new ResponseVo();
		try {
			if (complaintsJSON.isEmpty()) {
				response.setStatus(false);
				response.setMessage(propertyValues.getErrorMessage());
				return response;
			}
			complaintsControllerServiceImpl.complaintSalePersonUpdate(uploadAttachment,uploadRe1,uploadRe2,id,complaintsJSON);
			response.setStatus(true);
			response.setMessage(propertyValues.getSuccessMessage());
			response.setId(Integer.parseInt(id));
			return response;
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@PutMapping("/complaints/level1/update/{id}")
	public ResponseVo complaintUpdateLevel(@RequestBody Complaints complaints, @PathVariable String id) {
		ResponseVo response = new ResponseVo();
		try {
			complaintsControllerServiceImpl.complaintUpdateLevel(complaints,id);
			response.setStatus(true);
			response.setMessage(propertyValues.getSuccessMessage());
			return response;
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@PutMapping("/complaints/level2/update/{id}")
	public ResponseVo complaintUpdateLevel(
			@RequestParam(value = "uploadAttachment1", required = false) MultipartFile uploadAttachment1,
			@RequestParam(value = "uploadAttachment2", required = false) MultipartFile uploadAttachment2,
			@RequestParam(value = "uploadAttachment3", required = false) MultipartFile uploadAttachment3,
			@RequestParam(value = "uploadAttachment4", required = false) MultipartFile uploadAttachment4,
			@RequestParam("complaintsJSON") String complaintsJSON, @PathVariable String id) {
		ResponseVo response = new ResponseVo();
		try {
			complaintsControllerServiceImpl.complaintUpdateLevel(complaintsJSON, uploadAttachment1, uploadAttachment2, uploadAttachment3, uploadAttachment4, id);
			response.setStatus(true);
			response.setMessage(propertyValues.getSuccessMessage());
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	//plant complaint investigation report
	@PutMapping("/complaints/level2/update/PCIR/{id}")
	public ResponseVo complaintUpdatelevelReport(
			@RequestParam(value = "complaintReportAttachment", required = false) MultipartFile complaintReportAttachment,
			@RequestParam(value = "inChargeSignature", required = false) MultipartFile inChargeSignature,
			@RequestParam(value = "hodSignature", required = false) MultipartFile hodSignature,
			@RequestParam("complaintsJSON") String complaintsJSON, @PathVariable String id) {
		ResponseVo response = new ResponseVo();
		try {
			complaintsControllerServiceImpl.complaintUpdatelevelReport(complaintsJSON, id, hodSignature, inChargeSignature, complaintReportAttachment);			
			response.setStatus(true);
			response.setMessage(propertyValues.getSuccessMessage());
			return response;
		} catch (Exception e) {
			e.printStackTrace();			
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@PutMapping("/complaints/level3/assignSellPerson/{id}")
	public ResponseVo complaintUpdateAssignSellPerson(@RequestBody Complaints complaints,
			@PathVariable String id) {
		ResponseVo response = new ResponseVo();
		try {
			complaintsControllerServiceImpl.complaintUpdateAssignSellPerson(complaints, id);
			response.setStatus(true);
			response.setMessage(propertyValues.getSuccessMessage());
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@PutMapping("/complaints/level3/update/{id}")
	public ResponseVo complaintUpdateLevel(
			@RequestParam(value = "clouserAttachment", required = false) MultipartFile clouserAttachment,
			@RequestParam("complaintsJSON") String complaintsJSON, @PathVariable String id) {
		ResponseVo response = new ResponseVo();
		try {
			complaintsControllerServiceImpl.complaintUpdateLevel(clouserAttachment, complaintsJSON, id);
			response.setStatus(true);
			response.setMessage(propertyValues.getSuccessMessage());
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@PostMapping("/complaints/attachments/add")
	public ResponseVo complaintAddAttachments(@RequestParam("attachment") MultipartFile attachment,
			@RequestParam("complaintsAttachmentJSON") String complaintsAttachmentJSON) {
		ResponseVo response = new ResponseVo();
		try {
			if (complaintsAttachmentJSON.isEmpty()) {
				response.setStatus(false);
				response.setError(propertyValues.getFieldIsEmpty());
				return response;
			}
			if (attachment.getSize() == 0) {
				response.setStatus(false);
				response.setError(propertyValues.getAttachmentNotSelected());
				return response;
			}
			complaintsControllerServiceImpl.complaintAddAttachments(attachment, complaintsAttachmentJSON);
			response.setStatus(true);
			response.setMessage(propertyValues.getSuccessMessage());
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@GetMapping("/report/complaint/")
	public ResponseEntity<Resource> generateExcelReport() throws IOException {
		Workbook wb = complaintsControllerServiceImpl.generateExcelReport();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		wb.close();
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ComaplintExcelReport.xlsx");
		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);
		return response;
	}
}
