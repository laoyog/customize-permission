package com.mac.service;

import com.mac.dao.ProductDao;
import com.mac.vo.dto.ProductDto;
import com.mac.vo.Result;
import com.mac.vo.param.ProductAddParam;
import com.mac.vo.param.ProductUpdateParam;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yog
 */

@Service
public class ProductService {

    @Resource
    private ProductDao productDao;

    public Result<List<ProductDto>> findAll() {
        Result<List<ProductDto>> result = new Result<>();
        result.setCode("400");

        List<ProductDto> productDtoList = productDao.findAll();

        if(CollectionUtils.isEmpty(productDtoList)){
            result.setMsg("查询数据为空");
            return result;
        }

        result.setCode("200");
        result.setMsg("查询成功");
        result.setT(productDtoList);

        return result;
    }

    public Result<Boolean> add(ProductAddParam productParam){
        Result<Boolean> result = new Result<>();
        result.setCode("400");

        int row = productDao.add(productParam);
        if(row < 1){
            result.setT(false);
            result.setMsg("添加失败！请稍后重试！");
            return result;
        }

        result.setCode("200");
        result.setMsg("添加成功");
        result.setT(true);

        return result;
    }


    public Result<Boolean> update(ProductUpdateParam productParam) {
        Result<Boolean> result = new Result<>();
        result.setCode("400");

        if(productParam.getId() == null){
            result.setT(false);
            result.setMsg("修改时id不能为空！");
            return result;
        }

        int row = productDao.update(productParam);
        if(row < 1){
            result.setT(false);
            result.setMsg("修改失败！请稍后重试！");
            return result;
        }

        result.setCode("200");
        result.setMsg("修改成功");
        result.setT(true);

        return result;
    }

    public Result<Boolean> delete(Long id) {
        Result<Boolean> result = new Result<>();
        result.setCode("400");

        int row = productDao.delete(id);
        if(row < 1){
            result.setT(false);
            result.setMsg("删除失败！请稍后重试！");
            return result;
        }

        result.setCode("200");
        result.setMsg("删除成功");
        result.setT(true);

        return result;
    }
}
