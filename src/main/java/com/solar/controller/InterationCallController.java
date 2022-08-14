package com.solar.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solar.model.CustData;
import com.solar.model.CustomerDetails;
import com.solar.model.FilterData;
import com.solar.model.RequestObject;
import com.solar.repository.CustDataPageRepository;
import com.solar.repository.CustDataRepository;
import com.solar.repository.CustomerDetailsRepository;
import com.solar.repository.ExtraDetailsRepository;
import com.solar.serviceImpl.InterationLogServiceImpl;
import com.solar.utils.PropertyValues;
import com.solar.voObject.ResponseVo;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InterationCallController {

	@Autowired
	PropertyValues propertyValues;

	@Autowired
	CustDataRepository custDataRepository;

	@Autowired
	ExtraDetailsRepository extraDetailsRepository;

	@Autowired
	CustDataPageRepository custDataPageRepository;

	@Autowired
	InterationLogServiceImpl interationLogServiceImpl;

	@Autowired
	CustomerDetailsRepository customerDetailsRepository;

	@GetMapping("/getAllCustData")
	public ResponseVo getAllCustData() {
		ResponseVo response = new ResponseVo();
		List<CustData> custData = custDataRepository.findAll();
		if (interationLogServiceImpl.isNullOrEmpty(custData)) {
			response.setData(custData);
		}
		return response;
	}

	@GetMapping("/downloadFiles")
	public File downloadFiles(@RequestParam("fileName") String fileName) {
		File dir = new File(propertyValues.getRootFilePath().trim());
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File file : directoryListing) {
				if (file.getAbsolutePath().contains(fileName)) {
					return file;
				}
			}
		}
		return null;
	}

	@GetMapping("/getAllData")
	public ResponseVo getAllData(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
			throws ParseException {
		ResponseVo response = new ResponseVo();
		List<CustData> listCustData;
		Pageable paging = PageRequest.of(page, size);
		Page<CustData> custData = custDataPageRepository.findAll(paging);
		listCustData = custData.getContent();
		if (interationLogServiceImpl.isNullOrEmpty(listCustData)) {
			response.setData(interationLogServiceImpl.formatOutputDate(listCustData));
			response.setMessage(propertyValues.getSuccessMessage());
			response.setCurrentPage(custData.getNumber());
			response.setTotalItems(custData.getTotalElements());
			response.setTotalPages(custData.getTotalPages());
			return response;
		} else {
			response.setMessage(propertyValues.getNotFound());
			return response;
		}
	}

	@GetMapping("/getAllCustId")
	public ResponseVo getAllCustId(@RequestParam("id") String id) throws ParseException {
		ResponseVo response = new ResponseVo();
		CustData custData = null;
		if (id != null && !id.isEmpty()) {
			custData = custDataRepository.findByenquiryId(id);
			custData = interationLogServiceImpl.formatOutputStructure(custData);
			if (custData != null) {
				response.setData(Arrays.asList(custData));
				response.setMessage(propertyValues.getSuccessMessage());
				return response;
			} else {
				response.setMessage(propertyValues.getNotFound());
				return response;
			}
		}
		response.setMessage(propertyValues.getNotFound());
		return response;
	}

	@GetMapping("/getAllByCustId")
	public ResponseVo getAllByCustId(@RequestParam("id") String id) throws ParseException {
		ResponseVo response = new ResponseVo();
		CustData custData = null;
		if (id != null && !id.isEmpty()) {
			id = id.substring(0, 6);
			custData = custDataRepository.findBycustomerCode(id);
			custData = interationLogServiceImpl.formatOutputStructure(custData);
			if (custData != null) {
				response.setData(Arrays.asList(custData));
				response.setMessage(propertyValues.getSuccessMessage());
				return response;
			} else {
				response.setMessage(propertyValues.getNotFound());
				return response;
			}
		}
		response.setMessage(propertyValues.getNotFound());
		return response;

	}

	@GetMapping("/getByCustName")
	public ResponseVo getByCustName(@RequestParam("custName") String name) {
		ResponseVo response = new ResponseVo();
		List<CustData> custData = custDataRepository.findBycustomerName(name);
		if (custData != null && !custData.isEmpty()) {
			response.setMessage(propertyValues.getSuccessMessage());
			response.setData(custData);
			return response;
		} else {
			response.setMessage(propertyValues.getNotFound());
			return response;
		}
	}

	@GetMapping("/getByEnquiryId")
	public ResponseVo getByEnquiryId(@RequestParam("enquiryId") String name) {
		ResponseVo response = new ResponseVo();
		CustData custData = custDataRepository.findByenquiryId(name);
		if (custData != null) {
			response.setMessage(propertyValues.getSuccessMessage());
			response.setData(Arrays.asList(custData));
			return response;
		} else {
			response.setMessage(propertyValues.getNotFound());
			return response;
		}
	}

	@GetMapping("/getOnlyCustName")
	public ResponseVo getOnlyCustName() {
		ResponseVo response = new ResponseVo();
		List<String> custNameList = new ArrayList<>();
		List<CustData> custData = custDataRepository.findAll();
		for (CustData custDa : custData) {
			custNameList.add(custDa.getCustomerName());
		}
		response.setData(custNameList);
		return response;
	}

	@GetMapping("/getOnlyEnquiryId")
	public ResponseVo getOnlyEnquiryId() {
		ResponseVo response = new ResponseVo();
		List<String> custNameList = new ArrayList<>();
		List<CustData> custData = custDataRepository.findAll();
		for (CustData custDa : custData) {
			custNameList.add(custDa.getEnquiryId());
		}
		response.setData(custNameList);
		return response;
	}

	@GetMapping("/getOnlyCustCode")
	public ResponseVo getOnlyCustCode() {
		ResponseVo response = new ResponseVo();
		List<String> custNameList = new ArrayList<>();
		List<CustData> custData = custDataRepository.findAll();
		for (CustData custDa : custData) {
			custNameList.add(custDa.getCustomerCode());
		}
		response.setData(custNameList);
		return response;
	}

	@GetMapping("/getOnlyCustCodeWithName")
	public ResponseVo getOnlyCustCodeWithName() {
		ResponseVo response = new ResponseVo();
		List<String> custNameList = new ArrayList<>();
		List<CustData> custData = custDataRepository.findAll();
		for (CustData custDa : custData) {
			custNameList.add(custDa.getCustomerCode() + "-" + custDa.getCustomerName());
		}
		response.setData(custNameList);
		return response;
	}

	@PostMapping("/getFilterdData")
	public ResponseVo getFilterdData(@RequestBody FilterData filterData) throws ParseException {
		ResponseVo response = new ResponseVo();
		List<CustData> filteredData = interationLogServiceImpl.getFilterdData(filterData);
		if (filteredData != null && !filteredData.isEmpty()) {
			response.setData(interationLogServiceImpl.getFilterdData(filterData));
			response.setMessage(propertyValues.getSuccessMessage());
		} else {
			response.setMessage(propertyValues.getNotFound());
		}
		return response;
	}

	public File save(MultipartFile file) {
		File latestFile = null;
		try {
			Path rootPath = Paths.get(propertyValues.getRootFilePath());
			Files.copy(file.getInputStream(), rootPath.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			latestFile = new File(file.getOriginalFilename());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return latestFile;
	}

	@PostMapping(value = "/fileUpload")
	public ResponseVo fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
		ResponseVo response = new ResponseVo();
		File latestFile = save(file);
		response.setMessage(latestFile.getAbsolutePath() + "|" + type + "|" + latestFile.getName());
		return response;
	}

	@GetMapping("/existingCustomer")
	public ResponseVo complaintsFilter(@RequestParam("body") String existingCustomerName,
			@RequestParam("existFlag") String existFlag) {
		ResponseVo response = new ResponseVo();
		List<String> listOfCustName = new ArrayList<>();
		existingCustomerName = existingCustomerName + "%";
		List<CustomerDetails> listOfCustomerWithName = customerDetailsRepository.customerNameList(existingCustomerName,
				existFlag);
		if (interationLogServiceImpl.isNullOrEmpty(listOfCustomerWithName)) {
			response.setMessage(propertyValues.getSuccessMessage());
			for (CustomerDetails custName : listOfCustomerWithName) {
				listOfCustName.add(custName.getCustomerName());
			}
			response.setData(listOfCustName);
		} else {
			response.setMessage(propertyValues.getNotFound());
		}
		return response;
	}

	@PostMapping("/submitData")
	public ResponseVo submitData(@RequestBody RequestObject requestObject) throws ParseException {
		ResponseVo response = new ResponseVo();
		CustData updateData = null;
		CustData saveCustData = null;
		CustData obj = interationLogServiceImpl.createObject(requestObject);
		if (obj.getExistingFlag().equals("true")) {
			CustData custDataAlreadyExist = custDataRepository.findBycustomerCode(obj.getCustomerCode());
			if (custDataAlreadyExist != null) {
				obj.setId(custDataAlreadyExist.getId());
				obj.setEnquiryId(custDataAlreadyExist.getEnquiryId());
				updateData = custDataRepository.save(obj);
				if (updateData != null) {
					response.setMessage(propertyValues.getSuccessMessage());
				} else {
					response.setMessage(propertyValues.getErrorUploading());
				}
			}
		} else {
			saveCustData = custDataRepository.save(obj);
			if (saveCustData != null) {
				response.setMessage(propertyValues.getSuccessMessage());
			} else
				response.setMessage(propertyValues.getErrorSaving());
		}
		return response;

	}

}
