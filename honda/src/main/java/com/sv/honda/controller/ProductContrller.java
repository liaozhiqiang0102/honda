package com.sv.honda.controller;

import com.google.gson.Gson;
import com.sv.honda.entity.ProductEntity;
import com.sv.honda.service.ProductService;
import com.sv.honda.util.DataSourceResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ProductContrller {
    @Resource
    private ProductService productService;

    /**
     * 根据给定的产品名称模糊查询产品
     * @param name 给定的产品名称
     * @return 产品列表
     */
    @GetMapping(value = "/findProductsByName/{name}")
    public @ResponseBody
    List<ProductEntity> findProductsByName(@PathVariable("name") String name) {
        List<ProductEntity> productEntityList = this.productService.findProductsByName(name);
        System.out.println(productEntityList.toString());
        return productEntityList;
    }

    /**
     * 分页读取所有产品
     * @param page 当前开始页码（代码中从0开始）
     * @param pageSize 页号
     * @return
     */
    @GetMapping(value = "/product/readAll")
    public @ResponseBody
    String findAllProducts(@Param("page") String page, @Param("pageSize") String pageSize) {
        PageRequest pageRequest = new PageRequest(Integer.parseInt(page) - 1, Integer.parseInt(pageSize));
        DataSourceResult<ProductEntity> productEntityDataSourceResult = new DataSourceResult<>();
        Page<ProductEntity> productEntityPage = this.productService.findAllProducts(pageRequest);
        productEntityDataSourceResult.setData(productEntityPage.getContent());
        productEntityDataSourceResult.setTotal(productEntityPage.getTotalPages());
        Gson gson = new Gson();
        return gson.toJson(productEntityPage);
    }

    /**
     * 创建新产品
     * @param productEntity
     */
    @PostMapping(value = "/product/create")
    public void createNewProduct(ProductEntity productEntity){

        System.out.println(productEntity);
    }

    /**
     * 不分页查询所有产品
     * @return 所有产品列表
     */
    @GetMapping(value = "/product/readAll2")
    public @ResponseBody
    List<ProductEntity> findAllProducts() {

        return this.productService.findAllProducts();
    }

    /**
     * 根据给定的ID查询某个确定的产品
     * @param id 给定的ID（主键）
     * @return 产品
     */
    @GetMapping(value = "/product/find/{id}")
    public @ResponseBody ProductEntity findProductById(@PathVariable("id") Integer id){
        return this.productService.findProductById(id);
    }

}
