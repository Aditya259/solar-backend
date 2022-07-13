package com.solar.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solar.model.CustData;
import com.solar.model.CustomerDetails;
import com.solar.model.FilterData;
import com.solar.model.RequestObject;
import com.solar.repository.ContactPersonDetailsRepository;
import com.solar.repository.CustDataPageRepository;
import com.solar.repository.CustDataRepository;
import com.solar.repository.CustomerDetailsRepository;
import com.solar.repository.ExtraDetailsRepository;
import com.solar.serviceImpl.InterationLogServiceImpl;
import com.solar.voObject.ResponseVo;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InterationCallController {
	
	//Path root = Paths
			//.get("C:\\Users\\aditya\\Desktop\\solarFrontend\\solar-interaction-log-latest-bckp\\solar-interaction-log\\src\\assets\\files\\");

	Path root = Paths
			.get("/usr/share/nginx/html/assets/files/");

	
	//private final Path root = Paths
		//	.get("/root/solarAttachments/");
	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository;

	@Autowired
	ContactPersonDetailsRepository contactPersonDetailsRepository;

	@Autowired
	ExtraDetailsRepository extraDetailsRepository;

	@Autowired
	CustDataPageRepository custDataPageRepository;

	@Autowired
	CustDataRepository custDataRepository;

	@Autowired
	InterationLogServiceImpl interationLogServiceImpl;

	@GetMapping("/getAllCustData")
	public ResponseVo getAllCustData() {
		ResponseVo response = new ResponseVo();
		List<CustData> custData = custDataRepository.findAll();
		response.setData(custData);
		return response;
	}
	
	@GetMapping("/downloadFiles")
	public File downloadFiles(@RequestParam("fileName") String fileName) {
		System.err.println(fileName);
		File dir = new File(this.root.toString());
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		      // Do something with child
		    	System.err.println("child="+child);
		    	if(child.getAbsolutePath().contains(fileName)) {
		    		return child;
		    	}
		    }
		  } else {
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }
		
		return null;
	}
	

	@GetMapping("/getAllData")
	public ResponseVo getAllData(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
			throws ParseException {
		ResponseVo response = new ResponseVo();
		List<CustData> tutorials = new ArrayList<CustData>();
		Pageable paging = PageRequest.of(page, size);

		Page<CustData> custData = custDataPageRepository.findAll(paging);
		tutorials = custData.getContent();
		// response.setData(tutorials);
		// response.setCurrentPage(custData.getNumber());
		// response.setTotalItems(custData.getTotalElements());
		// response.setTotalPages(custData.getTotalPages());
		if (tutorials != null && !tutorials.isEmpty()) {
			for (CustData cust : tutorials) {
				DateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
				DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
				if (cust.getExtLogDate() != null) {
					Date date = inputFormat1.parse(cust.getExtLogDate().toString());
					cust.setDateToDisplay(outputFormat.format(date));
				}
				if (cust.getSelectedcalldatePicker() != null) {
					Date date = inputFormat1.parse(cust.getSelectedcalldatePicker().toString());
					cust.setDateToDisplay(outputFormat.format(date));
				}
				if (cust.getSelectedSmsdatePicker() != null) {
					Date date = inputFormat1.parse(cust.getSelectedSmsdatePicker().toString());
					cust.setDateToDisplay(outputFormat.format(date));
				}
				if (cust.getSelectedSvidatePicker() != null) {
					Date date = inputFormat1.parse(cust.getSelectedSvidatePicker().toString());
					cust.setDateToDisplay(outputFormat.format(date));
				}
				if (cust.getSelectedOmidatePicker() != null) {
					Date date = inputFormat1.parse(cust.getSelectedOmidatePicker().toString());
					cust.setDateToDisplay(outputFormat.format(date));
				}
				if (cust.getExtPurposeOfEmail() != null) {
					cust.setExtPurposeOfEmail(cust.getExtPurposeOfEmail());
				}
				if (cust.getSelectedCallPurposeOfEmail() != null) {
					cust.setExtPurposeOfEmail(cust.getSelectedCallPurposeOfEmail());
				}
				if (cust.getSelectedSmsPurposeOfEmail() != null) {
					cust.setExtPurposeOfEmail(cust.getSelectedSmsPurposeOfEmail());
				}
				if (cust.getSelectedSviPurposeOfEmail() != null) {
					cust.setExtPurposeOfEmail(cust.getSelectedSviPurposeOfEmail());
				}
				if (cust.getSelectedOmiPurposeOfEmail() != null) {
					cust.setExtPurposeOfEmail(cust.getSelectedOmiPurposeOfEmail());
				}

			}
			response.setData(tutorials);
			response.setMessage("Success");
			response.setCurrentPage(custData.getNumber());
			response.setTotalItems(custData.getTotalElements());
			response.setTotalPages(custData.getTotalPages());
			return response;
		} else {
			response.setMessage("Not_Found");
			return response;
		}
	}

	@GetMapping("/getAllCustId")
	public ResponseVo getAllCustId(@RequestParam("id") String id) throws ParseException {
		ResponseVo response = new ResponseVo();
		CustData custData = null;
		if (!id.isEmpty() && id != null) {
			custData = custDataRepository.findByenquiryId(id);
			if (custData != null) {
				if (!custData.getExtModeOfContact().isEmpty()) {
					List<String> typeList = custData.getExtModeOfContact();
					for (String type : typeList) {
						if (type != null) {
							if (type.equals("email")) {
								custData.setModeOfContactEmail(type);
							}
							if (type.equals("call")) {
								custData.setModeOfContactCall(type);
							}
							if (type.equals("sms")) {
								custData.setModeOfContactSms(type);
							}
							if (type.equals("SiteVisitor")) {
								custData.setModeOfContactSiteVisit(type);
							}
							if (type.equals("OnlineMarketing")) {
								custData.setModeOfContactOnlineMeeting(type);
							}
							DateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
							DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
							if (custData.getExtLogDate() != null) {
								Date date = inputFormat1.parse(custData.getExtLogDate().toString());
								custData.setDateToDisplay(outputFormat.format(date));
							}
							if (custData.getSelectedcalldatePicker() != null) {
								Date date = inputFormat1.parse(custData.getSelectedcalldatePicker().toString());
								custData.setDateToDisplay(outputFormat.format(date));
							}
							if (custData.getSelectedSmsdatePicker() != null) {
								Date date = inputFormat1.parse(custData.getSelectedSmsdatePicker().toString());
								custData.setDateToDisplay(outputFormat.format(date));
							}
							if (custData.getSelectedSvidatePicker() != null) {
								Date date = inputFormat1.parse(custData.getSelectedSvidatePicker().toString());
								custData.setDateToDisplay(outputFormat.format(date));
							}
							if (custData.getSelectedOmidatePicker() != null) {
								Date date = inputFormat1.parse(custData.getSelectedOmidatePicker().toString());
								custData.setDateToDisplay(outputFormat.format(date));
							}
							if (custData.getExtPurposeOfEmail() != null) {
								custData.setExtPurposeOfEmail(custData.getExtPurposeOfEmail());
							}
							if (custData.getSelectedCallPurposeOfEmail() != null) {
								custData.setExtPurposeOfEmail(custData.getSelectedCallPurposeOfEmail());
							}
							if (custData.getSelectedSmsPurposeOfEmail() != null) {
								custData.setExtPurposeOfEmail(custData.getSelectedSmsPurposeOfEmail());
							}
							if (custData.getSelectedSviPurposeOfEmail() != null) {
								custData.setExtPurposeOfEmail(custData.getSelectedSviPurposeOfEmail());
							}
							if (custData.getSelectedOmiPurposeOfEmail() != null) {
								custData.setExtPurposeOfEmail(custData.getSelectedOmiPurposeOfEmail());
							}

						}
					}
				}
			}
			// DateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
			// DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
			// Date date = inputFormat1.parse(custData.getExtLogDate().toString());
			// custData.setDateToDisplay(outputFormat.format(date));as
		}
		if (custData != null) {
			response.setData(Arrays.asList(custData));
			response.setMessage("Success");
			return response;
		} else {
			response.setMessage("Not_Found");
			return response;
		}

	}

	@GetMapping("/getAllByCustId")
	public ResponseVo getAllByCustId(@RequestParam("id") String id) {
		System.err.println("Main");
		ResponseVo response = new ResponseVo();
		CustData custData = null;
		if (!id.isEmpty() && id != null) {
			id = id.substring(0, 6);
			custData = custDataRepository.findBycustomerCode(id);
			if (custData != null) {
				if (!custData.getExtModeOfContact().isEmpty()) {
					List<String> typeList = custData.getExtModeOfContact();
					for (String type : typeList) {
						if (type != null) {
							if (type.equals("email")) {
								custData.setModeOfContactEmail(type);
							}
							if (type.equals("call")) {
								custData.setModeOfContactCall(type);
							}
							if (type.equals("sms")) {
								custData.setModeOfContactSms(type);
							}
							if (type.equals("SiteVisitor")) {
								custData.setModeOfContactSiteVisit(type);
							}
							if (type.equals("OnlineMarketing")) {
								custData.setModeOfContactOnlineMeeting(type);
							}
						}
					}
				}
				System.err.println("==>" + custData);
				response.setData(Arrays.asList(custData));
				response.setMessage("Success");
				return response;
			} else {
				response.setMessage("Not_Found");
				return response;
			}
		}
		response.setMessage("Not_Found");
		return response;

	}

	@GetMapping("/getByCustName")
	public ResponseVo getByCustName(@RequestParam("custName") String name) {
		ResponseVo response = new ResponseVo();
		List<CustData> custData = custDataRepository.findBycustomerName(name);
		if (!custData.isEmpty() && custData != null) {
			response.setMessage("Success");
			response.setData(custData);
			return response;
		} else {
			response.setMessage("Not_Found");
			return response;
		}
	}

	@GetMapping("/getByEnquiryId")
	public ResponseVo getByEnquiryId(@RequestParam("enquiryId") String name) {
		ResponseVo response = new ResponseVo();
		CustData custData = custDataRepository.findByenquiryId(name);
		if (custData != null) {
			response.setMessage("Success");
			response.setData(Arrays.asList(custData));
			return response;
		} else {
			response.setMessage("Not_Found");
			return response;
		}
	}

	@GetMapping("/getOnlyCustName")
	public ResponseVo getOnlyCustName() {
		ResponseVo response = new ResponseVo();
		List<String> custNameList = new ArrayList<String>();
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
		List<String> custNameList = new ArrayList<String>();
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
		List<String> custNameList = new ArrayList<String>();
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
		List<String> custNameList = new ArrayList<String>();
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
			response.setMessage("Success");
		} else {
			response.setMessage("Not_Found");
		}
		return response;

	}

	

	public File save(MultipartFile file) {
		File f = null;
		FileOutputStream fos = null;
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			f = new File(file.getOriginalFilename());
			
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		return f;
	}

	@PostMapping(value = "/fileUpload")
	public ResponseVo fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("type") String type) throws ParseException {
		ResponseVo response = new ResponseVo();
		System.err.println(type);
		File ff =save(file);
		System.err.println(ff.getAbsolutePath()+"|"+type+"|"+ff.getName());
		response.setMessage(ff.getAbsolutePath()+"|"+type+"|"+ff.getName());
		return response;
	}

	@GetMapping("/existingCustomer")
	public ResponseVo complaintsFilter(@RequestParam("body") String existingCustomerName,
			@RequestParam("existFlag") String existFlag) {
		ResponseVo response = new ResponseVo();
		List<String> listOfCustName = new ArrayList<String>();
		existingCustomerName = existingCustomerName + "%";
		List<CustomerDetails> listOfCustomerWithName = customerDetailsRepository.customerNameList(existingCustomerName,
				existFlag);
		if (listOfCustomerWithName != null && !listOfCustomerWithName.isEmpty()) {
			response.setMessage("success");
			for (CustomerDetails custName : listOfCustomerWithName) {
				listOfCustName.add(custName.getCustomerName());
			}
			response.setData(listOfCustName);
		} else {
			response.setMessage("not_found");
		}
		return response;
	}

	@PostMapping("/submitData")
	public ResponseVo submitData(@RequestBody RequestObject requestObject) throws ParseException {
		ResponseVo response = new ResponseVo();
		System.err.println("requestObject == "+requestObject);
		CustData obj = interationLogServiceImpl.createObject(requestObject);
		if (obj.getExistingFlag().equals("true")) {
			// CustData custDataAlreadyExist = custDataRepository.findByid(obj.getId());
			CustData custDataAlreadyExist = custDataRepository.findBycustomerCode(obj.getCustomerCode());
			if (custDataAlreadyExist != null) {
				obj.setId(custDataAlreadyExist.getId());
				obj.setEnquiryId(custDataAlreadyExist.getEnquiryId());
				CustData saveData = custDataRepository.save(obj);
				if (saveData != null) {
					response.setMessage("Success");
				} else {
					response.setMessage("Error while updating");
				}
			}
		} else {
			CustData custDataSaved = custDataRepository.save(obj);
			if (custDataSaved != null) {
				response.setMessage("Success");
			} else
				response.setMessage("Error While Saving");
		}
		return response;

	}

}
