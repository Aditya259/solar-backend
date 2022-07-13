package com.solar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.solar.model.CustData;

@Repository
@Transactional
public interface CustDataPageRepository extends PagingAndSortingRepository<CustData, Integer>{
	Page<CustData> findAll(Pageable pageable);
}
