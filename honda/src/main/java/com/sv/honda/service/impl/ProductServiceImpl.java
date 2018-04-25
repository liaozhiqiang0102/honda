package com.sv.honda.service.impl;

import com.sv.honda.entity.ProductEntity;
import com.sv.honda.repository.ProductRepository;
import com.sv.honda.service.ProductService;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 根据一个给定的产品名称查询出所有符合条件的产品
     *
     * @param productName 产品名称（模糊查询）
     * @return 所有符合条件的产品
     */
    @Override
    public List<ProductEntity> findProductsByName(String productName) {
        return this.productRepository.findProductsByName(productName);
    }

    /**
     * 分页查询所有产品
     * @return 所有产品
     */
    @Override
    public Page<ProductEntity> findAllProducts(org.springframework.data.domain.Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void saveProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName("New Product 1");
        productEntity.setProductPrice(new BigDecimal("200.01"));
        this.productRepository.save(productEntity);
    }

    /**
     * 不分页查询所有产品
     * @return 所有产品
     */
    @Override
    public List<ProductEntity> findAllProducts() {
        return IteratorUtils.toList(this.productRepository.findAll().iterator());
    }

    /**
     * 根据ID查询产品
     *
     * @param id
     * @return
     */
    @Override
    public ProductEntity findProductById(Integer id) {
        Optional<ProductEntity> productEntity = this.productRepository.findById(id);
        return productEntity.get();
    }
}
