package com.solar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solar.model.CompanyMaster;
import com.solar.model.CountryMaster;
import com.solar.model.EmployeeMaster;
import com.solar.model.IndustryMaster;
import com.solar.model.ParaentCompanyMaster;
import com.solar.model.SegmentMaster;
import com.solar.model.StateMaster;
import com.solar.model.SubAreaMaster;
import com.solar.model.SubChannelMaster;
import com.solar.model.SubCompanyMaster;
import com.solar.repository.CompanyMasterRepository;
import com.solar.repository.CountryMasterRepository;
import com.solar.repository.EmployeeMasterRepository;
import com.solar.repository.IndustryMasterRepository;
import com.solar.repository.ParaentCompanyMasterRepository;
import com.solar.repository.SegmentMasterRepository;
import com.solar.repository.StateMasterRepository;
import com.solar.repository.SubAreaMasterRepository;
import com.solar.repository.SubChannelRepository;
import com.solar.repository.SubCompanyMasterRepository;
import com.solar.voObject.ResponseVo;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MasterDataController {
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	IndustryMasterRepository industryMasterRepository;
	
	@Autowired
	SubChannelRepository subChannelRepository;
	
	@Autowired
	SubCompanyMasterRepository subCompanyMasterRepository;
	
	@Autowired
	CountryMasterRepository countryMasterRepository;
	
	@Autowired
	StateMasterRepository stateMasterRepository;
	
	@Autowired
	ParaentCompanyMasterRepository paraentCompanyMasterRepository;
	
	@Autowired
	SegmentMasterRepository segmentMasterRepository;
	
	@Autowired
	SubAreaMasterRepository subAreaMasterRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@GetMapping("/getAllSubsidoryList")
	public ResponseVo getAllSubsidoryList() {
		ResponseVo response = new ResponseVo();
		List<SubAreaMaster> subAreaMaster = subAreaMasterRepository.findAll();
		response.setData(subAreaMaster);
		return response;
	}
	
	@GetMapping("/getAllEmployeeList")
	public ResponseVo getAllEmployeeList() {
		ResponseVo response = new ResponseVo();
		List<EmployeeMaster> employeeMaster = employeeMasterRepository.findAll();
		response.setData(employeeMaster);
		return response;
	}
	
	
	@GetMapping("/getAllSegmentList")
	public ResponseVo getAllSegmentList() {
		ResponseVo response = new ResponseVo();
		List<SegmentMaster> segmentMaster = segmentMasterRepository.findAll();
		response.setData(segmentMaster);
		return response;
	}
	
	@GetMapping("/getAllParentComanyList")
	public ResponseVo getAllParentComanyList() {
		ResponseVo response = new ResponseVo();
		List<ParaentCompanyMaster> paraentCompanyMaster = paraentCompanyMasterRepository.findAll();
		response.setData(paraentCompanyMaster);
		return response;
	}
	
	@GetMapping("/getAllStateList")
	public ResponseVo getAllStateList() {
		ResponseVo response = new ResponseVo();
		List<StateMaster> stateMaster = stateMasterRepository.findAll();
		response.setData(stateMaster);
		return response;
	}
	
	
	@GetMapping("/getAllCountryList")
	public ResponseVo getAllCountryList() {
		ResponseVo response = new ResponseVo();
		List<CountryMaster> countryMaster = countryMasterRepository.findAll();
		response.setData(countryMaster);
		return response;
	}
	
	@GetMapping("/getAllSubCompanyList")
	public ResponseVo getAllSubCompanyList() {
		ResponseVo response = new ResponseVo();
		List<SubCompanyMaster> subCompanyMaster = subCompanyMasterRepository.findAll();
		response.setData(subCompanyMaster);
		return response;
	}
	
	@GetMapping("/getAllCompanyList")
	public ResponseVo getAllCompanyList() {
		ResponseVo response = new ResponseVo();
		List<CompanyMaster> companyMaster = companyMasterRepository.findAll();
		response.setData(companyMaster);
		return response;
	}
	
	@GetMapping("/getAllIndustryList")
	public ResponseVo getAllIndustryList() {
		ResponseVo response = new ResponseVo();
		List<IndustryMaster> industryMaster = industryMasterRepository.findAll();
		response.setData(industryMaster);
		return response;
	}
	
	@GetMapping("/getAllSubChannelList")
	public ResponseVo getAllSubChannelList() {
		ResponseVo response = new ResponseVo();
		List<SubChannelMaster> subChannelMaster = subChannelRepository.findAll();
		response.setData(subChannelMaster);
		return response;
	}


}
