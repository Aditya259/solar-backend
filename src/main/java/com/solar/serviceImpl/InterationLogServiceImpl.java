package com.solar.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.model.ContactPersonDetails;
import com.solar.model.CustData;
import com.solar.model.CustomerDetails;
import com.solar.model.ExtraDetails;
import com.solar.model.FilterData;
import com.solar.model.RequestObject;
import com.solar.repository.ContactPersonDetailsRepository;
import com.solar.repository.CustomerDetailsRepository;
import com.solar.repository.ExtraDetailsRepository;

@Service
public class InterationLogServiceImpl {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	ExtraDetailsRepository extraDetailsRepository;

	@Autowired
	CustomerDetailsRepository customerDetailsRepository;

	@Autowired
	ContactPersonDetailsRepository contactPersonDetailsRepository;

	public List<CustData> getFilterdData(FilterData filterData) throws ParseException {
		System.err.println("filterData = " + filterData);
		StringBuffer sb1 = new StringBuffer();
		Map<String, String> quer = new HashedMap<String, String>();
		sb1.append("select c from CustData c where ");

		if (filterData.getEnquiryId() != null && !filterData.getEnquiryId().isEmpty()) {
			sb1.append("c.enquiryId = :enquiryId AND ");
			quer.put("enquiryId", filterData.getEnquiryId());
		}

		if (filterData.getCustomerName() != null && !filterData.getCustomerName().isEmpty()) {
			sb1.append("c.customerName = :customerName AND ");
			quer.put("customerName", filterData.getCustomerName());
		}

		if (filterData.getCustomerCode() != null && !filterData.getCustomerCode().isEmpty()) {
			sb1.append("c.customerCode = :customerCode AND ");
			quer.put("customerCode", filterData.getCustomerCode());
		}

		if (filterData.getSector() != null && !filterData.getSector().isEmpty()) {
			sb1.append("c.sector = :sector AND ");
			quer.put("sector", filterData.getSector());
		}

		if (filterData.getSubsidary() != null && !filterData.getSubsidary().isEmpty()) {
			sb1.append("c.subsidaryCompany = :subsidaryCompany AND ");
			quer.put("subsidaryCompany", filterData.getSubsidary());
		}

		if (filterData.getCompany() != null && !filterData.getCompany().isEmpty()) {
			sb1.append("c.company = :company AND ");
			quer.put("company", filterData.getCompany());
		}

		if (filterData.getCountry() != null && !filterData.getCountry().isEmpty()) {
			sb1.append("c.country = :country AND ");
			quer.put("country", filterData.getCountry());
		}

		if (filterData.getState() != null && !filterData.getState().isEmpty()) {
			sb1.append("c.state = :state AND ");
			quer.put("state", filterData.getState());
		}
		
		String sb2 = sb1.toString().substring(0, sb1.length() - 4);

		System.err.println("sb2 =" + sb2);

		Query query1 = entityManager.createQuery(sb2);
		for (Map.Entry<String, String> entry : quer.entrySet()) {
			if (entry.getKey().equals("customerCode")) {
				query1.setParameter(entry.getKey(), entry.getValue());
			} else {
				query1.setParameter(entry.getKey(), entry.getValue());
			}
		}
		boolean type = false;
		boolean purpose = false;
		boolean dateFilter = false;
		boolean empFilter =false;
		List<CustData> resultList = query1.getResultList();
		for (CustData custData : resultList) {
			DateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
			DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
			//validation on type			
			if(custData.getExtLogDate()!=null) {
				Date date = inputFormat1.parse(custData.getExtLogDate().toString());
				custData.setDateToDisplay(outputFormat.format(date));
				}
				if(custData.getSelectedcalldatePicker()!=null) {
					Date date = inputFormat1.parse(custData.getSelectedcalldatePicker().toString());
					custData.setDateToDisplay(outputFormat.format(date));
				}
				if(custData.getSelectedSmsdatePicker()!=null) {
					Date date = inputFormat1.parse(custData.getSelectedSmsdatePicker().toString());
					custData.setDateToDisplay(outputFormat.format(date));
				}
				if(custData.getSelectedSvidatePicker()!=null) {
					Date date = inputFormat1.parse(custData.getSelectedSvidatePicker().toString());
					custData.setDateToDisplay(outputFormat.format(date));
				}
				if(custData.getSelectedOmidatePicker()!=null) {
					Date date = inputFormat1.parse(custData.getSelectedOmidatePicker().toString());
					custData.setDateToDisplay(outputFormat.format(date));
				}
				if(custData.getExtPurposeOfEmail()!=null) {
					custData.setExtPurposeOfEmail(custData.getExtPurposeOfEmail());
				}
				if(custData.getSelectedCallPurposeOfEmail()!=null) {
					custData.setExtPurposeOfEmail(custData.getSelectedCallPurposeOfEmail());
				}
				if(custData.getSelectedSmsPurposeOfEmail()!=null) {
					custData.setExtPurposeOfEmail(custData.getSelectedSmsPurposeOfEmail());
				}
				if(custData.getSelectedSviPurposeOfEmail()!=null) {
					custData.setExtPurposeOfEmail(custData.getSelectedSviPurposeOfEmail());
				}
				if(custData.getSelectedOmiPurposeOfEmail() !=null) {
					custData.setExtPurposeOfEmail(custData.getSelectedOmiPurposeOfEmail());
				}

				if(filterData.getType()!=null && !filterData.getType().isEmpty()) {
					List<String> listOfTypeFromDb = custData.getExtModeOfContact();
					if(listOfTypeFromDb.contains(filterData.getType())) {
						//entityManager.close();
						//return resultList;
						type = true;
					}else {
						//entityManager.close();
						//return new ArrayList<>();
						type = false;
					}
				}
				
				if(filterData.getPurpose()!=null && !filterData.getPurpose().isEmpty()) {
					
					if(custData.getExtPurposeOfEmail()!=null) {
						if(filterData.getPurpose().equals(custData.getExtPurposeOfEmail())) {
						custData.setExtPurposeOfEmail(custData.getExtPurposeOfEmail());
						//entityManager.close();
						//return resultList;
						purpose = true;
						}
					}
					if(custData.getSelectedCallPurposeOfEmail()!=null) {
						if(filterData.getPurpose().equals(custData.getSelectedCallPurposeOfEmail())) {
						custData.setExtPurposeOfEmail(custData.getSelectedCallPurposeOfEmail());
						//entityManager.close();
						//return resultList;
						purpose = true;
						}
					}
					if(custData.getSelectedSmsPurposeOfEmail()!=null) {
						if(filterData.getPurpose().equals(custData.getSelectedSmsPurposeOfEmail())) {
						custData.setExtPurposeOfEmail(custData.getSelectedSmsPurposeOfEmail());
						//entityManager.close();
						//return resultList;
						purpose = true;
						}
					}
					if(custData.getSelectedSviPurposeOfEmail()!=null) {
						if(filterData.getPurpose().equals(custData.getSelectedSviPurposeOfEmail())) {
						custData.setExtPurposeOfEmail(custData.getSelectedSviPurposeOfEmail());
						//entityManager.close();
						//return resultList;
						purpose = true;
						}
					}
					if(custData.getSelectedOmiPurposeOfEmail() !=null) {
						if(filterData.getPurpose().equals(custData.getSelectedOmiPurposeOfEmail())) {
						custData.setExtPurposeOfEmail(custData.getSelectedOmiPurposeOfEmail());
						//entityManager.close();
						//return resultList;
						purpose = true;
						}
					}
				}
				
				if(custData.getExtLogDate()!=null && filterData.getFromDate()!=null && filterData.getToDate()!=null && !filterData.getFromDate().isEmpty() && !filterData.getToDate().isEmpty()) {
					System.err.println(filterData.getFromDate());
					Date datefrom=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getFromDate());  
					Date dateTo=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getToDate());  
					if(custData.getExtLogDate().after(datefrom) && custData.getExtLogDate().before(dateTo)) {
						dateFilter = true;
					}
				}
				
				if(custData.getSelectedcalldatePicker()!=null && filterData.getFromDate()!=null && filterData.getToDate()!=null && !filterData.getFromDate().isEmpty() && !filterData.getToDate().isEmpty()) {
					System.err.println(filterData.getFromDate());
					Date datefrom=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getFromDate());  
					Date dateTo=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getToDate());  
					if(custData.getSelectedcalldatePicker().after(datefrom) && custData.getSelectedcalldatePicker().before(dateTo)) {
						dateFilter = true;
					}
				}
				
				if(custData.getSelectedSmsdatePicker()!=null && filterData.getFromDate()!=null && filterData.getToDate()!=null && !filterData.getFromDate().isEmpty() && !filterData.getToDate().isEmpty()) {
					System.err.println(filterData.getFromDate());
					Date datefrom=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getFromDate());  
					Date dateTo=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getToDate());  
					if(custData.getSelectedSmsdatePicker().after(datefrom) && custData.getSelectedSmsdatePicker().before(dateTo)) {
						dateFilter = true;
					}
				}
				if(custData.getSelectedSvidatePicker()!=null && filterData.getFromDate()!=null && filterData.getToDate()!=null && !filterData.getFromDate().isEmpty() && !filterData.getToDate().isEmpty()) {
					System.err.println(filterData.getFromDate());
					Date datefrom=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getFromDate());  
					Date dateTo=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getToDate());  
					if(custData.getSelectedSvidatePicker().after(datefrom) && custData.getSelectedSvidatePicker().before(dateTo)) {
						dateFilter = true;
					}
				}
				if(custData.getSelectedOmidatePicker()!=null && filterData.getFromDate()!=null && filterData.getToDate()!=null && !filterData.getFromDate().isEmpty() && !filterData.getToDate().isEmpty()) {
					System.err.println(filterData.getFromDate());
					Date datefrom=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getFromDate());  
					Date dateTo=new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getToDate());  
					if(custData.getSelectedOmidatePicker().after(datefrom) && custData.getSelectedOmidatePicker().before(dateTo)) {
						dateFilter = true;
					}
				}
		
				//if(custData.getEmployeeName().contains(filterData.getEmployee())){
					//empFilter = true;
				//}
				
		}

		System.err.println("resultList outside =" + resultList);
		if(type && purpose && dateFilter) {
			entityManager.close();
			return resultList;
		}
		entityManager.close();
		return new ArrayList<>();

	}

	public List<CustData> getFilterdData1(FilterData filterData) throws ParseException {
		StringBuffer sb1 = new StringBuffer();
		Map<String, String> quer = new HashedMap<String, String>();
		try {
			sb1.append("select c from CustData c where ");
			if (filterData.getCompany() != null && !filterData.getCompany().isEmpty()) {
				sb1.append("c.company = :company AND ");
				quer.put("company", filterData.getCompany());
			}
			if (filterData.getCountry() != null && !filterData.getCountry().isEmpty()) {
				sb1.append("c.country = :country AND ");
				quer.put("country", filterData.getCountry());
			}
			if (filterData.getCustomerCode() != null && !filterData.getCustomerCode().isEmpty()) {
				sb1.append("c.customerCode = :customerCode AND ");
				quer.put("customerCode", filterData.getCustomerCode());
			}
			if (filterData.getCustomerName() != null && !filterData.getCustomerName().isEmpty()) {
				sb1.append("c.customerName = :customerName AND ");
				quer.put("customerName", filterData.getCustomerName());
			}
			if (filterData.getFromDate() != null && !filterData.getFromDate().isEmpty()) {
				sb1.append("c.extLogDate >= :extLogDate AND ");
				quer.put("extLogDate", filterData.getFromDate());
			}
			if (filterData.getPurpose() != null && !filterData.getPurpose().isEmpty()) {
				sb1.append("c.extPurposeOfEmail = :extPurposeOfEmail AND ");
				quer.put("extPurposeOfEmail", filterData.getPurpose());
			}
			if (filterData.getSubsidary() != null && !filterData.getSubsidary().isEmpty()) {
				sb1.append("c.subsidaryCompany = :subsidaryCompany AND ");
				quer.put("subsidaryCompany", filterData.getSubsidary());
			}
			if (filterData.getToDate() != null && !filterData.getToDate().isEmpty()) {
				sb1.append("c.extLogDate <= :extLogToDate AND ");
				quer.put("extLogToDate", filterData.getToDate());
			}
			if (filterData.getSector() != null && !filterData.getSector().isEmpty()) {
				sb1.append("c.sector = :sector AND ");
				quer.put("sector", filterData.getSector());
			}
			/**
			 * if (filterData.getType()!=null && !filterData.getType().isEmpty()) {
			 * sb1.append("c.extModeOfContact = :extModeOfContact AND "); if
			 * (filterData.getType().equals("Online Marketing")) {
			 * quer.put("extModeOfContact", "OnlineMarketing"); } else if
			 * (filterData.getType().equals("Site Visit")) { quer.put("extModeOfContact",
			 * "SiteVisitor"); } else { quer.put("extModeOfContact", filterData.getType());
			 * } }
			 **/
			if (filterData.getEnquiryId() != null && !filterData.getEnquiryId().isEmpty()) {
				sb1.append("c.enquiryId = :enquiryId AND ");
				quer.put("enquiryId", filterData.getEnquiryId());
			}

			if (filterData.getEmployee() != null && !filterData.getEmployee().isEmpty()) {
				sb1.append("c.employeeName = :employeeName AND ");
				quer.put("employeeName", filterData.getEmployee());
			}
			String sb2 = sb1.toString().substring(0, sb1.length() - 4);
			System.err.println("sb2 =" + sb2);
			Query query1 = entityManager.createQuery(sb2);
			for (Map.Entry<String, String> entry : quer.entrySet()) {
				if (entry.getKey().equals("customerCode")) {
					query1.setParameter(entry.getKey(), entry.getValue());
				} else if (entry.getKey().equals("extLogDate") || entry.getKey().equals("extLogToDate")) {
					query1.setParameter(entry.getKey(), new SimpleDateFormat("yyyy-MM-dd").parse(entry.getValue()));
				} else {
					query1.setParameter(entry.getKey(), entry.getValue());
				}
			}
			List<CustData> resultList = query1.getResultList();
			for (CustData custData : resultList) {
				if (custData.getExtLogDate() != null) {
					custData.setDateToDisplay(custData.getExtLogDate().toString().substring(0, 11));
				}
			}
			System.err.println("resultList outside =" + resultList);
			entityManager.close();
			return resultList;

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	public CustomerDetails updateExistingCustomerDetailsData(CustomerDetails customerDetails,
			RequestObject requestObject) {
		customerDetails.setCompany(requestObject.getCompany());
		customerDetails.setIndustry(requestObject.getIndustry());
		customerDetails.setParentCompany(requestObject.getParentCompany());
		customerDetails.setSegment(requestObject.getSegment());
		customerDetails.setSubsidaryArea(requestObject.getSubsidaryArea());
		customerDetails.setSubsidaryChannel(requestObject.getSubsidaryChannel());
		customerDetails.setSubsidaryCompany(requestObject.getSubsidaryCompany());
		customerDetails.setId(customerDetails.getId());
		customerDetails.setCustomerName(customerDetails.getCustomerName());
		CustomerDetails updatedObj = customerDetailsRepository.save(customerDetails);
		return updatedObj;
	}

	public ContactPersonDetails updateExistingContactPersonDetailsData(ContactPersonDetails contactPersonDetails,
			RequestObject requestObject) {
		contactPersonDetails.setCompany(requestObject.getCpdcompany());
		contactPersonDetails.setContactPerson(requestObject.getCpdcontactperson());
		contactPersonDetails.setDesignation(requestObject.getCpddesignation());
		contactPersonDetails.setEmail(requestObject.getCpdemail());
		contactPersonDetails.setId(contactPersonDetails.getId());
		ContactPersonDetails updatedObj = contactPersonDetailsRepository.save(contactPersonDetails);
		return updatedObj;
	}

	public ExtraDetails updateExistingExtraDetailsData(ExtraDetails extraDetails, RequestObject requestObject) {
		extraDetails.setAttachmet(requestObject.getExtraAttachment());
		extraDetails.setContactNumber(requestObject.getExtraContactNo());
		extraDetails.setContactPerson(requestObject.getExtraContactPerson());
		extraDetails.setDescription(requestObject.getExtraDescription());
		extraDetails.setLogDate(requestObject.getEmaildatePicker());
		extraDetails.setLogTime(requestObject.getEmailtimePicker());
		extraDetails.setPurposeOfEmail(requestObject.getExtraPurposeOfEmail());
		extraDetails.setSubject(requestObject.getExtraSubject());
		if (requestObject.getExtraEmail() != null && requestObject.getExtraEmail().equals("email")) {
			extraDetails.setModeOfContact(requestObject.getExtraEmail());
		}
		if (requestObject.getExtraCall() != null && requestObject.getExtraCall().equals("call")) {
			extraDetails.setModeOfContact(requestObject.getExtraCall());
		}
		if (requestObject.getExtraSms() != null && requestObject.getExtraSms().equals("sms")) {
			extraDetails.setModeOfContact(requestObject.getExtraSms());
		}
		if (requestObject.getExtraSiteVisitor() != null && requestObject.getExtraSiteVisitor().equals("SiteVisitor")) {
			extraDetails.setModeOfContact(requestObject.getExtraSiteVisitor());
		}
		if (requestObject.getExtraOnlineMarketing() != null
				&& requestObject.getExtraOnlineMarketing().equals("OnlineMarketing")) {
			extraDetails.setModeOfContact(requestObject.getExtraOnlineMarketing());
		}
		extraDetails.setId(extraDetails.getId());
		ExtraDetails updatedObj = extraDetailsRepository.save(extraDetails);
		return updatedObj;
	}

	public CustData createObject(RequestObject requestObject) throws ParseException {
		CustData custData = new CustData();
		if(requestObject.getFileName()!=null && requestObject.getFileName()!="") {
			//String[] fileNameSeprator = requestObject.getFileName().split("|");
			if(requestObject.getFileName().contains("emailFileName")) {
				custData.setFileNameEmail(requestObject.getFileName());
			}
		}
		if(requestObject.getFileNamecall()!=null && requestObject.getFileNamecall()!="") {
			if(requestObject.getFileNamecall().contains("callFileName")) {
				custData.setFileNameCall(requestObject.getFileNamecall());
			}
		}
		if(requestObject.getFileNamesms()!=null && requestObject.getFileNamesms()!="") {
			if(requestObject.getFileNamesms().contains("smsFileName")) {
				custData.setFileNameSms(requestObject.getFileNamesms());
			}
		}
		if(requestObject.getFileNameSiteVisitor()!=null && requestObject.getFileNameSiteVisitor()!="") {
			if(requestObject.getFileNameSiteVisitor().contains("svFileName")) {
				custData.setFileNameSv(requestObject.getFileNameSiteVisitor());
			}
		}
		if(requestObject.getFileNameOnlineMarketing()!=null && requestObject.getFileNameOnlineMarketing()!="") {
			if(requestObject.getFileNameOnlineMarketing().contains("omFileName")) {
				custData.setFileNameOm(requestObject.getFileNameOnlineMarketing());
			}
		}
			
		
		custData.setState(requestObject.getState());
		custData.setCountry(requestObject.getCountry());
		custData.setCompany(requestObject.getCompany());
		custData.setIndustry(requestObject.getIndustry());
		custData.setParentCompany(requestObject.getParentCompany());
		custData.setSegment(requestObject.getSegment());
		custData.setSubsidaryArea(requestObject.getSubsidaryArea());
		custData.setSubsidaryChannel(requestObject.getSubsidaryChannel());
		custData.setSubsidaryCompany(requestObject.getSubsidaryCompany());
		if (requestObject.getId() != null && !requestObject.getId().isEmpty()) {
			custData.setExistingFlag("true");
			custData.setCustomerCode(requestObject.getId());
			custData.setCustomerName(requestObject.getExistingCustomerName());
		} else {
			custData.setExistingFlag("false");
			Random custCode = new Random();
			int custCodeNumber = custCode.nextInt(999999);
			custData.setCustomerCode(Integer.toString(custCodeNumber));
			Random enquiryNumber = new Random();
			int intEnquiryNumber = enquiryNumber.nextInt(999999);
			custData.setEnquiryId(Integer.toString(intEnquiryNumber));
			custData.setCustomerName(requestObject.getExistingCustomerName());
		}
		custData.setCpdCompany(requestObject.getCpdcompany());
		custData.setCpdContactPerson(requestObject.getCpdcontactperson());
		custData.setCpdDesignation(requestObject.getCpddesignation());
		custData.setCpdeEail(requestObject.getCpdemail());
		custData.setExtContactNumber(requestObject.getExtraContactNo());
		custData.setExtContactPerson(requestObject.getExtraContactPerson());
		custData.setExtDescription(requestObject.getExtraDescription());

		if (requestObject.getSelectedcalldatePicker() != null) {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = formatter1.parse(requestObject.getSelectedcalldatePicker());
			custData.setSelectedcalldatePicker(date1);
		}
		// custData.setSelectedcalldatePicker(requestObject.getSelectedcalldatePicker());
		custData.setSelectedCallContactNo(requestObject.getSelectedCallContactNo());
		custData.setSelectedcallContactPerson(requestObject.getSelectedcallContactPerson());
		custData.setSelectedCallDescription(requestObject.getSelectedCallDescription());
		custData.setSelectedCallPurposeOfEmail(requestObject.getSelectedCallPurposeOfEmail());
		custData.setSelectedcallSubject(requestObject.getSelectedcallSubject());

		if (requestObject.getSelectedSmsdatePicker() != null) {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = formatter1.parse(requestObject.getSelectedSmsdatePicker());
			custData.setSelectedSmsdatePicker(date1);
		}
		// custData.setSelectedSmsdatePicker(requestObject.getSelectedSmsdatePicker());
		custData.setSelectedSmsContactNo(requestObject.getSelectedSmsContactNo());
		custData.setSelectedSmsContactPerson(requestObject.getSelectedSmsContactPerson());
		custData.setSelectedSmsDescription(requestObject.getSelectedSmsDescription());
		custData.setSelectedSmsPurposeOfEmail(requestObject.getSelectedSmsPurposeOfEmail());
		custData.setSelectedSmsSubject(requestObject.getSelectedSmsSubject());

		if (requestObject.getSelectedSvidatePicker() != null) {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = formatter1.parse(requestObject.getSelectedSvidatePicker());
			custData.setSelectedSvidatePicker(date1);
		}
		// custData.setSelectedSvidatePicker(requestObject.getSelectedSvidatePicker());
		custData.setSelectedSviContactNo(requestObject.getSelectedSviContactNo());
		custData.setSelectedSviContactPerson(requestObject.getSelectedSviContactPerson());
		custData.setSelectedSviDescription(requestObject.getSelectedSviDescription());
		custData.setSelectedSviPurposeOfEmail(requestObject.getSelectedSviPurposeOfEmail());
		custData.setSelectedSviSubject(requestObject.getSelectedSviSubject());

		if (requestObject.getSelectedOmidatePicker() != null) {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = formatter1.parse(requestObject.getSelectedOmidatePicker());
			custData.setSelectedOmidatePicker(date1);
		}
		// custData.setSelectedOmidatePicker(requestObject.getSelectedOmidatePicker());
		custData.setSelectedOmiContactNo(requestObject.getSelectedOmiContactNo());
		custData.setSelectedOmiContactPerson(requestObject.getSelectedOmiContactPerson());
		custData.setSelectedOmiDescription(requestObject.getSelectedOmiDescription());
		custData.setSelectedOmiPurposeOfEmail(requestObject.getSelectedOmiPurposeOfEmail());
		custData.setSelectedOmiSubject(requestObject.getSelectedOmiSubject());

		if (requestObject.getEmaildatePicker() != null) {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = formatter1.parse(requestObject.getEmaildatePicker());
			custData.setExtLogDate(date1);
		}
		custData.setExtLogTime(requestObject.getEmailtimePicker());
		custData.setExtPurposeOfEmail(requestObject.getExtraPurposeOfEmail());
		custData.setExtSubject(requestObject.getExtraSubject());
		custData.setEmployeeName(requestObject.getSelectedEmployee());
		List<String> modeOfContact = new ArrayList<String>();
		if (requestObject.getExtraEmail() != null && requestObject.getExtraEmail().equals("email")) {
			modeOfContact.add(requestObject.getExtraEmail());
			// custData.setExtModeOfContact(requestObject.getExtraEmail());
		}
		if (requestObject.getExtraCall() != null && requestObject.getExtraCall().equals("call")) {
			modeOfContact.add(requestObject.getExtraCall());
			// custData.setExtModeOfContact(requestObject.getExtraCall());
		}
		if (requestObject.getExtraSms() != null && requestObject.getExtraSms().equals("sms")) {
			modeOfContact.add(requestObject.getExtraSms());
			// custData.setExtModeOfContact(requestObject.getExtraSms());
		}
		if (requestObject.getExtraSiteVisitor() != null && requestObject.getExtraSiteVisitor().equals("SiteVisitor")) {
			modeOfContact.add(requestObject.getExtraSiteVisitor());
			// custData.setExtModeOfContact(requestObject.getExtraSiteVisitor());
		}
		if (requestObject.getExtraOnlineMarketing() != null
				&& requestObject.getExtraOnlineMarketing().equals("OnlineMarketing")) {
			modeOfContact.add(requestObject.getExtraOnlineMarketing());
			// custData.setExtModeOfContact(requestObject.getExtraOnlineMarketing());
		}
		custData.setExtModeOfContact(modeOfContact);
		custData.setSector(requestObject.getSector());
		System.err.println("builded custdata = " + custData);
		return custData;

	}

	private CustomerDetails extractCustomerDetails(RequestObject requestObject) {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCompany(requestObject.getCompany());
		customerDetails.setIndustry(requestObject.getIndustry());
		customerDetails.setParentCompany(requestObject.getParentCompany());
		customerDetails.setSegment(requestObject.getSegment());
		customerDetails.setSubsidaryArea(requestObject.getSubsidaryArea());
		customerDetails.setSubsidaryChannel(requestObject.getSubsidaryChannel());
		customerDetails.setSubsidaryCompany(requestObject.getSubsidaryCompany());
		if (requestObject.getNewCustomerName() != null && !requestObject.getNewCustomerName().isEmpty()) {
			customerDetails.setExistingFlag("false");
			customerDetails.setCustomerName(requestObject.getNewCustomerName());
		} else {
			customerDetails.setExistingFlag("true");
			customerDetails.setCustomerName(requestObject.getExistingCustomerName());
		}
		return customerDetails;
	}

	private ContactPersonDetails extractContactPersonDetails(RequestObject requestObject) {
		ContactPersonDetails contactPersonDetails = new ContactPersonDetails();
		contactPersonDetails.setCompany(requestObject.getCpdcompany());
		contactPersonDetails.setContactPerson(requestObject.getCpdcontactperson());
		contactPersonDetails.setDesignation(requestObject.getCpddesignation());
		contactPersonDetails.setEmail(requestObject.getCpdemail());
		return contactPersonDetails;
	}

	private ExtraDetails extractExtraDetails(RequestObject requestObject) {
		ExtraDetails extraDetails = new ExtraDetails();
		extraDetails.setAttachmet(requestObject.getExtraAttachment());
		extraDetails.setContactNumber(requestObject.getExtraContactNo());
		extraDetails.setContactPerson(requestObject.getExtraContactPerson());
		extraDetails.setDescription(requestObject.getExtraDescription());
		extraDetails.setLogDate(requestObject.getEmaildatePicker());
		extraDetails.setLogTime(requestObject.getEmailtimePicker());
		extraDetails.setPurposeOfEmail(requestObject.getExtraPurposeOfEmail());
		extraDetails.setSubject(requestObject.getExtraSubject());
		if (requestObject.getExtraEmail() != null && requestObject.getExtraEmail().equals("email")) {
			extraDetails.setModeOfContact(requestObject.getExtraEmail());
		}
		if (requestObject.getExtraCall() != null && requestObject.getExtraCall().equals("call")) {
			extraDetails.setModeOfContact(requestObject.getExtraCall());
		}
		if (requestObject.getExtraSms() != null && requestObject.getExtraSms().equals("sms")) {
			extraDetails.setModeOfContact(requestObject.getExtraSms());
		}
		if (requestObject.getExtraSiteVisitor() != null && requestObject.getExtraSiteVisitor().equals("SiteVisitor")) {
			extraDetails.setModeOfContact(requestObject.getExtraSiteVisitor());
		}
		if (requestObject.getExtraOnlineMarketing() != null
				&& requestObject.getExtraOnlineMarketing().equals("OnlineMarketing")) {
			extraDetails.setModeOfContact(requestObject.getExtraOnlineMarketing());
		}
		return extraDetails;
	}

}
