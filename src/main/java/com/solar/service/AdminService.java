package com.solar.service;

import org.springframework.stereotype.Repository;

import com.solar.model.Admins;

@Repository
public interface AdminService {
	Admins getAdmin(int id);
	void addAdmin(Admins admins);
}
