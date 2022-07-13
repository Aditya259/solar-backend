package com.solar.service;

import org.springframework.stereotype.Repository;

import com.solar.model.Departments;

@Repository
public interface DepartmentService {
	Departments getDepartment(int id);
	Departments addDepartment(Departments departments);
}
