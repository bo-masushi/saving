package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Stock;
import com.example.demo.form.SearchConditionForm;

public interface StockServiceInterface {
	public Page<Stock> findPage(Pageable pageable, SearchConditionForm form);

}
