package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StockInfoMapper;
import com.example.demo.dto.StockAddRequest;
import com.example.demo.dto.StockSearchRequest;
import com.example.demo.dto.StockUpdateRequest;
import com.example.demo.entity.Stock;

@Service
public class StockService {

    @Autowired
    StockInfoMapper stockInfoMapper;
    
//    @Autowired
//    private StockRepository stockRepository;

    //mybatis
    public Page<Stock> searchAll(Pageable pageable, StockSearchRequest form) {
    	long cnt = stockInfoMapper.selectStockCnt(form);
    	 
        List<Stock> stockList = Collections.emptyList();
 
        if (cnt > 0) {
            stockList = stockInfoMapper.findPage(pageable, form);
        }
        // ユーザーTBLの内容を全検索
        return new PageImpl<>(stockList, pageable, cnt);
    }

    
    /**
     * 情報主キー検索
     * @return 検索結果
     */
    public Stock findById(Long id) {
        return stockInfoMapper.findById(id);
    }
    /**
     * 情報検索
     * @param userSearchRequest リクエストデータ
     * @return 検索結果
     */
    public Page<Stock> search(Pageable pageable, StockSearchRequest stockSearchRequest) {
    	long cnt = stockInfoMapper.selectStockSearchCnt(stockSearchRequest);
       List<Stock> stockList = Collections.emptyList();

       if (cnt > 0) {
           stockList = stockInfoMapper.search(pageable, stockSearchRequest);
       }
       //TBLの内容を全検索
       return new PageImpl<>(stockList, pageable, cnt);
    }
    /**
     * 情報登録
     * @param userAddRequest リクエストデータ
     */
    public void save(StockAddRequest stockAddRequest) {
        stockInfoMapper.save(stockAddRequest);
    }
    
    /**
     * 情報更新
     * @param userEditRequest リクエストデータ
     */
    public void update(StockUpdateRequest userUpdateRequest) {
        stockInfoMapper.update(userUpdateRequest);
    }
    /**
     * 格納情報論理削除
     * @param id
     */
    public void delete(Long id) {
        stockInfoMapper.delete(id);
    }

}
