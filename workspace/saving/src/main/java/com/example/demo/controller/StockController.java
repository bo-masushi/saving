package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dto.StockAddRequest;
import com.example.demo.dto.StockSearchRequest;
import com.example.demo.dto.StockUpdateRequest;
import com.example.demo.entity.Stock;
import com.example.demo.form.SearchConditionForm;
import com.example.demo.service.StockService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes(types = {SearchConditionForm.class})
public class StockController {

	  @Autowired
	  StockService stockService;
	  /**
	   * 格納情報一覧画面を表示
	   * @param model Model
	   * @return 格納ー情報一覧画面のHTML
	   */
	  @GetMapping(value = "/stock/list" )
	  public String displayList(Model model, @PageableDefault(page = 0) Pageable pageable,@Valid StockSearchRequest stockSearchRequest) {
	    
	    model.addAttribute("stocklist", stockService.searchAll(pageable,stockSearchRequest));
	    model.addAttribute("stockSearchRequest", new StockSearchRequest());
	    return "stock/search";
	  }
	  /**
	   * 格納ー情報検索
	   * @param userSearchRequest リクエストデータ
	   * @param model Model
	   * @return 格納ー情報一覧画面
	   */
	  @RequestMapping(value = "/stock/search", method = RequestMethod.POST)
	  public String search(@PageableDefault(page = 0) Pageable pageable, @ModelAttribute StockSearchRequest stockSearchRequest, Model model) {
		  
	      model.addAttribute("stocksearch", stockService.search(pageable, stockSearchRequest));
	      model.addAttribute("stockSearch", new StockSearchRequest());
	      return "stock/searchResult";
	  }
	  
	  /**
	   * 格納ー新規登録画面を表示
	   * @param model Model
	   * @return 格納ー情報一覧画面
	   */
	  @GetMapping(value = "/stock/add")
	  public String displayAdd(Model model) {
	      model.addAttribute("stockAddRequest", new StockAddRequest());
	      return "stock/add";
	  }
	    /**
	     * データの新規登録
	     * @param userRequest リクエストデータ
	     * @param model Model
	     * @return 格納ー情報一覧画面
	     */
	    @RequestMapping(value = "/stock/create", method = RequestMethod.POST)
	    public String create(@Validated @ModelAttribute StockAddRequest stockRequest, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            // 入力チェックエラーの場合
	            List<String> errorList = new ArrayList<String>();
	            for (ObjectError error : result.getAllErrors()) {
	                errorList.add(error.getDefaultMessage());
	            }
	            model.addAttribute("validationError", errorList);
	            return "stock/add";
	        }
	        // 格納情報の登録
	        stockService.save(stockRequest);
	        return "redirect:/stock/list";
	    }
	    /**
	     * 情報詳細画面を表示
	     * @param id 表示する情報のID
	     * @param model Model
	     * @return 格納情報詳細画面
	     */
	    @GetMapping("/stock/{id}/view")
	    public String displayView(@PathVariable Long id, Model model) {
	      Stock stock = stockService.findById(id);
	      model.addAttribute("stockData", stock);
	      return "stock/view";
	    }
	    
	    /**
	     * 格納編集画面を表示
	     * @param id 格納ID
	     * @param model Model
	     * @return 格納情報編集画面
	     */
	    @GetMapping("/stock/{id}/edit")
	    public String displayEdit(@PathVariable Long id, Model model) {
	        Stock stock = stockService.findById(id);
	        StockUpdateRequest stockUpdateRequest = new StockUpdateRequest();
	        stockUpdateRequest.setId(stock.getId());
	        stockUpdateRequest.setTitle(stock.getTitle());
	        stockUpdateRequest.setBody(stock.getBody());
	        stockUpdateRequest.setName(stock.getName());
	        model.addAttribute("stockUpdateRequest", stockUpdateRequest);
	        return "stock/edit";
	    }
	    /**
	     * 格納情報更新
	     * @param userRequest リクエストデータ
	     * @param model Model
	     * @return 格納情報詳細画面
	     */
	    @RequestMapping(value = "/stock/update", method = RequestMethod.POST)
	    public String update(@Validated @ModelAttribute StockUpdateRequest stockUpdateRequest, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            List<String> errorList = new ArrayList<String>();
	            for (ObjectError error : result.getAllErrors()) {
	                errorList.add(error.getDefaultMessage());
	            }
	            model.addAttribute("validationError", errorList);
	            return "stock/edit";
	        }
	        // 格納情報の更新
	        stockService.update(stockUpdateRequest);
	        return "redirect:/stock/list";
	    }
	    
//	    /**
//	     * 情報削除（論理削除）
//	     * @param id 格納情報ID
//	     * @param model Model
//	     * @return 格納情報一覧画面
//	     */
	    @GetMapping("/stock/{id}/delete")
	    public String delete(@PathVariable Long id, Model model) {
	        // ユーザー情報の削除
	        stockService.delete(id);
	        return "redirect:/stock/list";
	    }
	

}
