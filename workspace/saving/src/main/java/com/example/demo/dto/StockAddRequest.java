package com.example.demo.dto;

import java.util.Date;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StockAddRequest {
	   /**
     * 名前
     */
    //@NotEmpty(message = "名前を入力してください")
    @Size(max = 100, message = "タイトルは一目でわかるもの")
    private String title;
    /**
     * 住所
     */
    @Size(max = 255, message = "内容は簡潔にお願いします")
    private String body;
    /**
     * 電話番号
     */
    @Size(max = 100, message = "名前は100桁以内で入力してください")
    private String name;
    

    private Date createDate;
    

    private Date updateDate;

}
