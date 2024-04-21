package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.StockAddRequest;
import com.example.demo.dto.StockSearchRequest;
import com.example.demo.dto.StockUpdateRequest;
import com.example.demo.entity.Stock;


@Mapper
public interface StockInfoMapper {
	
	public long selectStockCnt(@Param("StockSearchRequest") StockSearchRequest form);
	public long selectStockSearchCnt(@Param("stockSearchRequest") StockSearchRequest stockSearchRequest);
	
    public List<Stock> findPage(@Param("pageable") Pageable pageable,
            @Param("searchConditionForm") StockSearchRequest form);

    /**
     * 情報全件検索
     * @param Stock 検索用リクエストデータ
     * @return 検索結果
     */
    List<Stock> findAll();

    /**
     * 情報主キー検索
     * @param id 主キー
     * @return 検索結果
     */
    Stock findById(Long id);

    /**
     * 情報検索
     * @param user 検索用リクエストデータ
     * @return 検索結果
     */
    List<Stock> search(@Param("pageable") Pageable pageable, 
    		@Param("stockSearchRequest")StockSearchRequest stockSearchRequest);

    /**
     * ユーザー情報登録
     * @param userRequest 登録用リクエストデータ
     */
    void save(StockAddRequest stockRequest);

    /**
     * ユーザー情報更新
     * @param userUpdateRequest 更新用リクエストデータ
     */
    void update(StockUpdateRequest stockUpdateRequest);

    /**
     * ユーザー情報の論理削除
     * @param id ID
     */
    void delete(Long id);
}


