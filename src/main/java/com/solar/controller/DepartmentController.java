package com.solar.controller;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solar.model.Departments;
import com.solar.repository.DepartmentRepository;
import com.solar.serviceImpl.DepartmentServicesImpl;
import com.solar.utils.PropertyValues;
import com.solar.voObject.ResponseVo;
import com.solar.voObject.ResponseVoObj;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartmentController  {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	PropertyValues propertyValues;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DepartmentServicesImpl departmentServices;

	@PostMapping("/addDepartment")
	public ResponseVo addDepartment(@Valid @RequestBody Departments departments) {
		ResponseVo response = new ResponseVo();
		Departments a = null;
		try {
			a = departmentRepository.findByName(departments.getName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(propertyValues.getDepartmentNotFound());
		}
		if (a == null) {
			Date current = new Date();
			departments.setCreateAt(current);
			Departments d = departmentServices.addDepartment(departments);
			departmentRepository.updateDate(d.getId());
			response.setStatus(true);
			response.setMessage(propertyValues.getSuccessMessage());
			return response;
		} else {
			response.setStatus(false);
			response.setMessage(propertyValues.getDepartmentsAlreadyAvailable());
			return response;
		}
	}

	@GetMapping("/departments")
	public ResponseVo departments() {
		ResponseVo response = new ResponseVo();
		try {
			response.setData(departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
			response.setStatus(true);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage(e.getMessage());
			return response;
		}
	}

	@GetMapping("/departments/{id}")
	public ResponseVoObj departmentByID(@PathVariable String id) {
		ResponseVoObj response = new ResponseVoObj();
		try {
			response.setData(departmentServices.getDepartment(Integer.parseInt(id)));
			response.setStatus(true);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

	@DeleteMapping("departmentDelete")
	public ResponseVo departmentDelete(@RequestParam String id) {
		ResponseVo response = new ResponseVo();
		try {
			departmentRepository.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage(propertyValues.getDeleteNotSucess());
			response.setError(e.getMessage());
			return response;
		}
		response.setStatus(false);
		response.setMessage(propertyValues.getDeleteSucess());
		return response;
	}

	@PutMapping("updateDepartment/{id}")
	public ResponseVo updateDepartment(@RequestBody Departments departments, @PathVariable String id) {
		ResponseVo response = new ResponseVo();
		try {
			Departments departments2 = departmentServices.getDepartment(Integer.parseInt(id));
			departments2.setName(departments.getName());
			departments2.setDecription(departments.getDecription());
			departmentServices.addDepartment(departments2);
			response.setStatus(true);
			response.setMessage(propertyValues.getUpdateSucess());
			return response;
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
			return response;
		}
	}

}
