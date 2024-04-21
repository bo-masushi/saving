package com.example.demo.dto;

import lombok.Data;

@Data
public class StockSearchRequest {
	
	/**
	* ID
	*/
	private Long Id;
	/**
	* body
	*/
	private String body;
	  /**
     * title
     */
    private String title;
}
