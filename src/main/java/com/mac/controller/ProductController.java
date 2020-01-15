package com.mac.controller;

import com.mac.annotation.MyPermission;
import com.mac.annotation.PermissionConsts;
import com.mac.service.ProductService;
import com.mac.vo.dto.ProductDto;
import com.mac.vo.Result;
import com.mac.vo.param.ProductAddParam;
import com.mac.vo.param.ProductUpdateParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yog
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping(value = "/findAll")
    @MyPermission(value = PermissionConsts.R)
    public Result<List<ProductDto>> findAll(){
        return productService.findAll();
    }

    @PostMapping(value = "/add")
    @MyPermission(value = PermissionConsts.C)
    public Result<Boolean> add(@RequestBody ProductAddParam productParam){
        return productService.add(productParam);
    }

    @PostMapping(value = "/update")
    @MyPermission(value = PermissionConsts.U)
    public Result<Boolean> update(@RequestBody ProductUpdateParam productParam){
        return productService.update(productParam);
    }

    @GetMapping(value = "/delete")
    @MyPermission(value = PermissionConsts.D)
    public Result<Boolean> delete(@RequestParam Long id){
        return productService.delete(id);
    }

}
