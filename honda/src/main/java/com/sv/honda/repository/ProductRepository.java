package com.sv.honda.repository;

import com.sv.honda.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Integer> {

    @Query("from ProductEntity as p where p.productName like %:productName%")
    List<ProductEntity> findProductsByName(@Param("productName") String productName);
}
