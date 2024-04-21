package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StockUpdateRequest extends StockAddRequest implements Serializable{
	

    /**
     * ユーザーID
     */
    private Long id;
}
