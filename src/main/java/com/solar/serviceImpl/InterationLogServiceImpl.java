package com.solar.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.model.ContactPersonDetails;
import com.solar.model.CustData;
import com.solar.model.CustomerDetails;
import com.solar.model.ExtraDetails;
import com.solar.model.FilterData;
import com.solar.model.RequestObject;
import com.solar.repository.ContactPersonDetailsRepository;
import com.solar.repository.CustDataRepository;
import com.solar.repository.CustomerDetailsRepository;
import com.solar.repository.ExtraDetailsRepository;
import com.solar.utils.PropertyValues;

@Service
public class InterationLogServiceImpl {

	@Autowired
	PropertyValues propertyValues;

	@Autowired
	CustDataRepository custDataRepository;

	@Autowired
	ExtraDetailsRepository extraDetailsRepository;

	@Autowired
	CustomerDetailsRepository customerDetailsRepository;

	@Autowired
	ContactPersonDetailsRepository contactPersonDetailsRepository;

	private CustData buildCustDataObject(FilterData filterData) {
		CustData custDataObject = new CustData();
		if (filterData.getEnquiryId() != null && !filterData.getEnquiryId().isEmpty()) {
			custDataObject.setEnquiryId(filterData.getEnquiryId());
		}
		if (filterData.getCustomerName() != null && !filterData.getCustomerName().isEmpty()) {
			custDataObject.setCustomerName(filterData.getCustomerName());
		}
		if (filterData.getCustomerCode() != null && !filterData.getCustomerCode().isEmpty()) {
			custDataObject.setCustomerCode(filterData.getCustomerCode());
		}
		if (filterData.getSector() != null && !filterData.getSector().isEmpty()) {
			custDataObject.setSector(filterData.getSector());
		}
		if (filterData.getSubsidary() != null && !filterData.getSubsidary().isEmpty()) {
			custDataObject.setSubsidaryCompany(filterData.getSubsidary());
		}
		if (filterData.getCompany() != null && !filterData.getCompany().isEmpty()) {
			custDataObject.setCompany(filterData.getCompany());
		}
		if (filterData.getCountry() != null && !filterData.getCountry().isEmpty()) {
			custDataObject.setCountry(filterData.getCountry());
		}
		if (filterData.getState() != null && !filterData.getState().isEmpty()) {
			custDataObject.setState(filterData.getState());
		}
		return custDataObject;
	}

	private CustData updateDateInCustDate(CustData custData) throws ParseException {
		if (custData.getExtLogDate() != null) {
			Date date = inputFormat().parse(custData.getExtLogDate().toString());
			custData.setDateToDisplay(outputFormat().format(date));
		}
		if (custData.getSelectedcalldatePicker() != null) {
			Date date = inputFormat().parse(custData.getSelectedcalldatePicker().toString());
			custData.setDateToDisplay(outputFormat().format(date));
		}
		if (custData.getSelectedSmsdatePicker() != null) {
			Date date = inputFormat().parse(custData.getSelectedSmsdatePicker().toString());
			custData.setDateToDisplay(outputFormat().format(date));
		}
		if (custData.getSelectedSvidatePicker() != null) {
			Date date = inputFormat().parse(custData.getSelectedSvidatePicker().toString());
			custData.setDateToDisplay(outputFormat().format(date));
		}
		if (custData.getSelectedOmidatePicker() != null) {
			Date date = inputFormat().parse(custData.getSelectedOmidatePicker().toString());
			custData.setDateToDisplay(outputFormat().format(date));
		}
		return custData;
	}

	private CustData updatePurposeInCustDate(CustData custData) {
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
		return custData;
	}

	public boolean checkPurposeCustData(CustData custData, FilterData filterData) {
		boolean purpose = false;
		if (custData.getExtPurposeOfEmail() != null
				&& filterData.getPurpose().equals(custData.getExtPurposeOfEmail())) {
			custData.setExtPurposeOfEmail(custData.getExtPurposeOfEmail());
			purpose = true;
		}
		if (custData.getSelectedCallPurposeOfEmail() != null
				&& filterData.getPurpose().equals(custData.getSelectedCallPurposeOfEmail())) {
			custData.setExtPurposeOfEmail(custData.getSelectedCallPurposeOfEmail());
			purpose = true;
		}
		if (custData.getSelectedSmsPurposeOfEmail() != null
				&& filterData.getPurpose().equals(custData.getSelectedSmsPurposeOfEmail())) {
			custData.setExtPurposeOfEmail(custData.getSelectedSmsPurposeOfEmail());
			purpose = true;
		}
		if (custData.getSelectedSviPurposeOfEmail() != null
				&& filterData.getPurpose().equals(custData.getSelectedSviPurposeOfEmail())) {
			custData.setExtPurposeOfEmail(custData.getSelectedSviPurposeOfEmail());
			purpose = true;
		}
		if (custData.getSelectedOmiPurposeOfEmail() != null
				&& filterData.getPurpose().equals(custData.getSelectedOmiPurposeOfEmail())) {
			custData.setExtPurposeOfEmail(custData.getSelectedOmiPurposeOfEmail());
			purpose = true;
		}
		return purpose;
	}

	public boolean checkDateCustData(CustData custData, FilterData filterData) throws ParseException {
		boolean dateFilter = false;
		if (custData.getExtLogDate() != null && filterData.getFromDate() != null && filterData.getToDate() != null
				&& !filterData.getFromDate().isEmpty() && !filterData.getToDate().isEmpty()
				&& custData.getExtLogDate()
						.after(new SimpleDateFormat(propertyValues.getFormatyyMMdd()).parse(filterData.getFromDate()))
				&& custData.getExtLogDate().before(new SimpleDateFormat("yyyy-MM-dd").parse(filterData.getToDate()))) {
			dateFilter = true;
		}
		if (custData.getSelectedcalldatePicker() != null && filterData.getFromDate() != null
				&& filterData.getToDate() != null && !filterData.getFromDate().isEmpty()
				&& !filterData.getToDate().isEmpty()
				&& custData.getSelectedcalldatePicker()
						.after(new SimpleDateFormat(propertyValues.getFormatyyMMdd()).parse(filterData.getFromDate()))
				&& custData.getSelectedcalldatePicker()
						.before(new SimpleDateFormat(propertyValues.getFormatyyMMdd()).parse(filterData.getToDate()))) {
			dateFilter = true;
		}

		if (custData.getSelectedSmsdatePicker() != null && filterData.getFromDate() != null
				&& filterData.getToDate() != null && !filterData.getFromDate().isEmpty()
				&& !filterData.getToDate().isEmpty()
				&& custData.getSelectedSmsdatePicker()
						.after(new SimpleDateFormat(propertyValues.getFormatyyMMdd()).parse(filterData.getFromDate()))
				&& custData.getSelectedSmsdatePicker()
						.before(new SimpleDateFormat(propertyValues.getFormatyyMMdd()).parse(filterData.getToDate()))) {
			dateFilter = true;
		}
		if (custData.getSelectedSvidatePicker() != null && filterData.getFromDate() != null
				&& filterData.getToDate() != null && !filterData.getFromDate().isEmpty()
				&& !filterData.getToDate().isEmpty()
				&& custData.getSelectedSvidatePicker()
						.after(new SimpleDateFormat(propertyValues.getFormatyyMMdd()).parse(filterData.getFromDate()))
				&& custData.getSelectedSvidatePicker()
						.before(new SimpleDateFormat(propertyValues.getFormatyyMMdd()).parse(filterData.getToDate()))) {
			dateFilter = true;
		}
		if (custData.getSelectedOmidatePicker() != null && filterData.getFromDate() != null
				&& filterData.getToDate() != null && !filterData.getFromDate().isEmpty()
				&& !filterData.getToDate().isEmpty()
				&& custData.getSelectedOmidatePicker()
						.after(new SimpleDateFormat(propertyValues.getFormatyyMMdd()).parse(filterData.getFromDate()))
				&& custData.getSelectedOmidatePicker()
						.before(new SimpleDateFormat(propertyValues.getFormatyyMMdd()).parse(filterData.getToDate()))) {
			dateFilter = true;
		}
		return dateFilter;
	}

	public List<CustData> getFilterdData(FilterData filterData) throws ParseException {
		boolean type = false;
		boolean purpose = false;
		boolean dateFilter = false;
		CustData custDataObject = buildCustDataObject(filterData);
		List<CustData> resultList = custDataRepository.filterCustomData(custDataObject);
		for (CustData custData : resultList) {
			updateDateInCustDate(custData);
			updatePurposeInCustDate(custData);
			if (filterData.getType() != null && !filterData.getType().isEmpty()) {
				List<String> listOfTypeFromDb = custData.getExtModeOfContact();
				if (listOfTypeFromDb.contains(filterData.getType())) {
					type = true;
				} else {
					type = false;
				}
			}
			if (filterData.getPurpose() != null && !filterData.getPurpose().isEmpty()
					&& checkPurposeCustData(custData, filterData)) {
				purpose = true;
			}
			if (checkDateCustData(custData, filterData)) {
				dateFilter = true;
			}
		}
		if (type && purpose && dateFilter) {
			return resultList;
		} else {
			return new ArrayList<>();
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
		return customerDetailsRepository.save(customerDetails);
	}

	public ContactPersonDetails updateExistingContactPersonDetailsData(ContactPersonDetails contactPersonDetails,
			RequestObject requestObject) {
		contactPersonDetails.setCompany(requestObject.getCpdcompany());
		contactPersonDetails.setContactPerson(requestObject.getCpdcontactperson());
		contactPersonDetails.setDesignation(requestObject.getCpddesignation());
		contactPersonDetails.setEmail(requestObject.getCpdemail());
		contactPersonDetails.setId(contactPersonDetails.getId());
		return contactPersonDetailsRepository.save(contactPersonDetails);
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
		if (requestObject.getExtraEmail() != null && requestObject.getExtraEmail().equals(propertyValues.getEmail())) {
			extraDetails.setModeOfContact(requestObject.getExtraEmail());
		}
		if (requestObject.getExtraCall() != null && requestObject.getExtraCall().equals(propertyValues.getCall())) {
			extraDetails.setModeOfContact(requestObject.getExtraCall());
		}
		if (requestObject.getExtraSms() != null && requestObject.getExtraSms().equals(propertyValues.getSms())) {
			extraDetails.setModeOfContact(requestObject.getExtraSms());
		}
		if (requestObject.getExtraSiteVisitor() != null
				&& requestObject.getExtraSiteVisitor().equals(propertyValues.getSiteVisitor())) {
			extraDetails.setModeOfContact(requestObject.getExtraSiteVisitor());
		}
		if (requestObject.getExtraOnlineMarketing() != null
				&& requestObject.getExtraOnlineMarketing().equals(propertyValues.getOnlineMarketing())) {
			extraDetails.setModeOfContact(requestObject.getExtraOnlineMarketing());
		}
		extraDetails.setId(extraDetails.getId());
		return extraDetailsRepository.save(extraDetails);
	}

	public CustData createObject(RequestObject requestObject) throws ParseException {
		CustData custData = new CustData();
		if (requestObject.getFileName() != null && !requestObject.getFileName().isEmpty()
				&& requestObject.getFileName().contains(propertyValues.getEmailFileName())) {
			custData.setFileNameEmail(requestObject.getFileName());
		}
		if (requestObject.getFileNamecall() != null && !requestObject.getFileNamecall().isEmpty()
				&& requestObject.getFileNamecall().contains(propertyValues.getCallFileName())) {
			custData.setFileNameCall(requestObject.getFileNamecall());
		}
		if (requestObject.getFileNamesms() != null && !requestObject.getFileNamesms().isEmpty()
				&& requestObject.getFileNamesms().contains(propertyValues.getSmsFileName())) {
			custData.setFileNameSms(requestObject.getFileNamesms());
		}
		if (requestObject.getFileNameSiteVisitor() != null && !requestObject.getFileNameSiteVisitor().isEmpty()
				&& requestObject.getFileNameSiteVisitor().contains(propertyValues.getSvFileName())) {
			custData.setFileNameSv(requestObject.getFileNameSiteVisitor());
		}
		if (requestObject.getFileNameOnlineMarketing() != null && !requestObject.getFileNameOnlineMarketing().isEmpty()
				&& requestObject.getFileNameOnlineMarketing().contains(propertyValues.getOmFileName())) {
			custData.setFileNameOm(requestObject.getFileNameOnlineMarketing());
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
			custData.setExistingFlag(propertyValues.getTrueValue());
			custData.setCustomerCode(requestObject.getId());
			custData.setCustomerName(requestObject.getExistingCustomerName());
		} else {
			custData.setExistingFlag(propertyValues.getFalseValue());
			int custCodeNumber = new Random().nextInt(999999);
			custData.setCustomerCode(Integer.toString(custCodeNumber));
			int intEnquiryNumber = new Random().nextInt(999999);
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
			SimpleDateFormat formatter1 = new SimpleDateFormat(propertyValues.getFormatyyMMdd());
			Date date1 = formatter1.parse(requestObject.getSelectedcalldatePicker());
			custData.setSelectedcalldatePicker(date1);
		}
		custData.setSelectedCallContactNo(requestObject.getSelectedCallContactNo());
		custData.setSelectedcallContactPerson(requestObject.getSelectedcallContactPerson());
		custData.setSelectedCallDescription(requestObject.getSelectedCallDescription());
		custData.setSelectedCallPurposeOfEmail(requestObject.getSelectedCallPurposeOfEmail());
		custData.setSelectedcallSubject(requestObject.getSelectedcallSubject());
		if (requestObject.getSelectedSmsdatePicker() != null) {
			SimpleDateFormat formatter1 = new SimpleDateFormat(propertyValues.getFormatyyMMdd());
			Date date1 = formatter1.parse(requestObject.getSelectedSmsdatePicker());
			custData.setSelectedSmsdatePicker(date1);
		}
		custData.setSelectedSmsContactNo(requestObject.getSelectedSmsContactNo());
		custData.setSelectedSmsContactPerson(requestObject.getSelectedSmsContactPerson());
		custData.setSelectedSmsDescription(requestObject.getSelectedSmsDescription());
		custData.setSelectedSmsPurposeOfEmail(requestObject.getSelectedSmsPurposeOfEmail());
		custData.setSelectedSmsSubject(requestObject.getSelectedSmsSubject());
		if (requestObject.getSelectedSvidatePicker() != null) {
			SimpleDateFormat formatter1 = new SimpleDateFormat(propertyValues.getFormatyyMMdd());
			Date date1 = formatter1.parse(requestObject.getSelectedSvidatePicker());
			custData.setSelectedSvidatePicker(date1);
		}
		custData.setSelectedSviContactNo(requestObject.getSelectedSviContactNo());
		custData.setSelectedSviContactPerson(requestObject.getSelectedSviContactPerson());
		custData.setSelectedSviDescription(requestObject.getSelectedSviDescription());
		custData.setSelectedSviPurposeOfEmail(requestObject.getSelectedSviPurposeOfEmail());
		custData.setSelectedSviSubject(requestObject.getSelectedSviSubject());
		if (requestObject.getSelectedOmidatePicker() != null) {
			SimpleDateFormat formatter1 = new SimpleDateFormat(propertyValues.getFormatyyMMdd());
			Date date1 = formatter1.parse(requestObject.getSelectedOmidatePicker());
			custData.setSelectedOmidatePicker(date1);
		}
		custData.setSelectedOmiContactNo(requestObject.getSelectedOmiContactNo());
		custData.setSelectedOmiContactPerson(requestObject.getSelectedOmiContactPerson());
		custData.setSelectedOmiDescription(requestObject.getSelectedOmiDescription());
		custData.setSelectedOmiPurposeOfEmail(requestObject.getSelectedOmiPurposeOfEmail());
		custData.setSelectedOmiSubject(requestObject.getSelectedOmiSubject());
		if (requestObject.getEmaildatePicker() != null) {
			SimpleDateFormat formatter1 = new SimpleDateFormat(propertyValues.getFormatyyMMdd());
			Date date1 = formatter1.parse(requestObject.getEmaildatePicker());
			custData.setExtLogDate(date1);
		}
		custData.setExtLogTime(requestObject.getEmailtimePicker());
		custData.setExtPurposeOfEmail(requestObject.getExtraPurposeOfEmail());
		custData.setExtSubject(requestObject.getExtraSubject());
		custData.setEmployeeName(requestObject.getSelectedEmployee());
		List<String> modeOfContact = new ArrayList<>();
		if (requestObject.getExtraEmail() != null && requestObject.getExtraEmail().equals(propertyValues.getEmail())) {
			modeOfContact.add(requestObject.getExtraEmail());
		}
		if (requestObject.getExtraCall() != null && requestObject.getExtraCall().equals(propertyValues.getCall())) {
			modeOfContact.add(requestObject.getExtraCall());
		}
		if (requestObject.getExtraSms() != null && requestObject.getExtraSms().equals(propertyValues.getSms())) {
			modeOfContact.add(requestObject.getExtraSms());
		}
		if (requestObject.getExtraSiteVisitor() != null
				&& requestObject.getExtraSiteVisitor().equals(propertyValues.getSiteVisitor())) {
			modeOfContact.add(requestObject.getExtraSiteVisitor());
		}
		if (requestObject.getExtraOnlineMarketing() != null
				&& requestObject.getExtraOnlineMarketing().equals(propertyValues.getOnlineMarketing())) {
			modeOfContact.add(requestObject.getExtraOnlineMarketing());
		}
		custData.setExtModeOfContact(modeOfContact);
		custData.setSector(requestObject.getSector());
		return custData;
	}

	public List<CustData> formatOutputDate(List<CustData> listCustData) throws ParseException {
		if (isNullOrEmpty(listCustData)) {
			for (CustData cust : listCustData) {
				if (cust.getExtLogDate() != null) {
					Date date = inputFormat().parse(cust.getExtLogDate().toString());
					cust.setDateToDisplay(outputFormat().format(date));
				}
				if (cust.getSelectedcalldatePicker() != null) {
					Date date = inputFormat().parse(cust.getSelectedcalldatePicker().toString());
					cust.setDateToDisplay(outputFormat().format(date));
				}
				if (cust.getSelectedSmsdatePicker() != null) {
					Date date = inputFormat().parse(cust.getSelectedSmsdatePicker().toString());
					cust.setDateToDisplay(outputFormat().format(date));
				}
				if (cust.getSelectedSvidatePicker() != null) {
					Date date = inputFormat().parse(cust.getSelectedSvidatePicker().toString());
					cust.setDateToDisplay(outputFormat().format(date));
				}
				if (cust.getSelectedOmidatePicker() != null) {
					Date date = inputFormat().parse(cust.getSelectedOmidatePicker().toString());
					cust.setDateToDisplay(outputFormat().format(date));
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
		}
		return listCustData;
	}

	public CustData formatOutputStructure(CustData custData) throws ParseException {
		if (custData != null && !custData.getExtModeOfContact().isEmpty()) {
			List<String> typeList = custData.getExtModeOfContact();
			for (String type : typeList) {
				if (type != null) {
					if (type.equals(propertyValues.getEmail())) {
						custData.setModeOfContactEmail(type);
					}
					if (type.equals(propertyValues.getCall())) {
						custData.setModeOfContactCall(type);
					}
					if (type.equals(propertyValues.getSms())) {
						custData.setModeOfContactSms(type);
					}
					if (type.equals(propertyValues.getSiteVisitor())) {
						custData.setModeOfContactSiteVisit(type);
					}
					if (type.equals(propertyValues.getOnlineMarketing())) {
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
		return custData;
	}

	public boolean isNullOrEmpty(final Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public DateFormat inputFormat() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	}

	public DateFormat outputFormat() {
		return new SimpleDateFormat("dd-MM-yyyy");
	}
}
