package com.solar.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solar.model.Admins;
import com.solar.repository.AdminRepository;
import com.solar.serviceImpl.AdminServicesImpl;
import com.solar.utils.PropertyValues;
import com.solar.voObject.ResponseVo;
import com.solar.voObject.ResponseVoObj;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UsersController {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	PropertyValues propertyValues;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AdminServicesImpl adminServices;
	
	@PostMapping("/addAdmin")
	public ResponseVo addAdmin(@RequestBody Admins admin) {
		ResponseVo response = new ResponseVo();
		Admins a = null;
		try {
			a = adminRepository.findByuser(admin.getUser());
		} catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.info("User Not found!!!");
		}
		if (a == null) {
			admin.setCreatedDate(new Date());
			adminRepository.save(admin);
			response.setStatus(true);
			response.setMessage(propertyValues.getSuccessMessage());
			return response;
		}else {
			response.setStatus(false);
			response.setMessage(propertyValues.getUserAlreadyAvailable());
			return response;
		}
	}
	
	@GetMapping("/admins")
	public ResponseVo admins() {
		ResponseVo response = new ResponseVo();
		response.setStatus(true);
		response.setAdminList(adminRepository.findAllByOrderByIdDesc());
		return response;
	}
	
	@GetMapping("/adminsByRole")
	public ResponseVo adminsByRole() {
		ResponseVo response = new ResponseVo();
		String role="sell-person";
		List<Admins> admin=adminRepository.findAllByOrderByIdDesc();
		admin=admin.stream().filter(a -> a.getRole().equals(role)).collect(Collectors.toList());
		response.setStatus(true);
		response.setAdminList(admin);
		return response;
	}
	
	 @GetMapping("/admins/{id}")
	    public ResponseVoObj AdminById(@PathVariable String id){
		 ResponseVoObj response = new ResponseVoObj();
	    	try {
		    	response.setData(adminServices.getAdmin(Integer.parseInt(id)));
		    	response.setStatus(true);
				return response;
	    	} catch (Exception e) {
	    		response.setStatus(false);
	    		response.setError(e.getMessage());
				return response;
			}
	    }
	 
	@DeleteMapping("adminsDelete")
	public ResponseVo adminsDelete(@RequestParam String id) {
		ResponseVo response = new ResponseVo();
		try {
			adminRepository.deleteById(Integer.parseInt(id));
		}catch (Exception e) {
			response.setStatus(false);
			response.setMessage(propertyValues.getDeleteSucess());
			return response;
		}
		response.setStatus(true);
		response.setMessage(propertyValues.getDeleteSucess());
		return response;
	}		
	
	@PutMapping("updateAdmin/{id}")
	public ResponseVo updateAdmin(@RequestBody Admins admins,@PathVariable String id) {
		ResponseVo response = new ResponseVo();
		Admins admins2=adminServices.getAdmin(Integer.parseInt(id));		
		admins2.setName(admins.getName());
		admins2.setRole(admins.getRole());
		admins2.setUser(admins.getUser());
		admins2.setPass(admins.getPass());
		admins2.setDepartment(admins.getDepartment());
		adminServices.addAdmin(admins2);	
		response.setStatus(true);
		response.setMessage(propertyValues.getUpdateSucess());
		return response;
	}
	
	@PostMapping("/login")
	public ResponseVoObj login(@RequestBody Admins admins) {
		ResponseVoObj response = new ResponseVoObj();
		String user=admins.getUser();
		String pass=admins.getPass();
		Admins a = null;
		Admins admin=new Admins();
		try {
			a = adminRepository.findByuser(user);			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("User Not found!!!");

		}
		if (a != null && a.getPass().equals(pass)) {
			a.setStatus("success");
			response.setStatus(true);
			response.setMessage(propertyValues.getLoginSuccess());
			response.setData(a);
			return response;
		}
		admin.setStatus("Not success");
		response.setStatus(false);
		response.setMessage("Invalide UserName and Password");
		response.setData(admin);
		return response;
	}
}
