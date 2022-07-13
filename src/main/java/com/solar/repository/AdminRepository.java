package com.solar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.Admins;

@Repository
@Transactional
public interface AdminRepository  extends JpaRepository<Admins, Integer>{

	Admins findByuser(String user);
	
	List<Admins> findAllByOrderByIdDesc();
}
