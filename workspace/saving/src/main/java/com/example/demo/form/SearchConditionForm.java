package com.example.demo.form;

import jakarta.validation.constraints.Size;
import lombok.Data;
 
/**
*
* 検索条件フォーム
*
* @version   1.0.0
*/
@Data
public class SearchConditionForm {
 
    @Size(max = 10, message = "{error.maxSize}")
    private String fName;
 
}
