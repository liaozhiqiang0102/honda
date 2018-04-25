package com.sv.honda.service;

import com.sv.honda.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    /**
     * 根据一个给定的产品名称查询出所有符合条件的产品
     * @param productName 产品名称（模糊查询）
     * @return 所有符合条件的产品
     */
    List<ProductEntity> findProductsByName(String productName);

    /**
     * 分页查询所有产品
     * @return 所有产品
     */
    Page<ProductEntity> findAllProducts(Pageable pageable);

    /**
     * 不分页查询所有产品
     * @return
     */
    List<ProductEntity> findAllProducts();

    /**
     * 保存产品
     */
    void saveProduct();

    /**
     * 根据ID查询产品
     * @param id
     * @return
     */
    ProductEntity findProductById(Integer id);
}
