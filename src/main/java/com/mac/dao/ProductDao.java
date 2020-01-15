package com.mac.dao;

import com.mac.vo.dto.ProductDto;
import com.mac.vo.param.ProductAddParam;
import com.mac.vo.param.ProductUpdateParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yog
 */

@Mapper
@Repository
public interface ProductDao {

    /**
     * 查询全部产品
     * @return 产品
     */
    List<ProductDto> findAll();


    /**
     * 添加产品
     * @param productParam 产品传参
     * @return 插入行数
     */
    int add(ProductAddParam productParam);

    /**
     * 修改产品
     * @param productParam 产品传参
     * @return 更新行数
     */
    int update(ProductUpdateParam productParam);

    /**
     * 删除产品
     * @param id id
     * @return 删除行数
     */
    int delete(Long id);
}
