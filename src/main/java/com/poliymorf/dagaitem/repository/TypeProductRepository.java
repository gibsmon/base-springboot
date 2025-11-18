package com.poliymorf.dagaitem.repository;

import com.poliymorf.dagaitem.data.entity.Selling;
import com.poliymorf.dagaitem.data.entity.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TypeProductRepository extends JpaRepository<TypeProduct, UUID>, JpaSpecificationExecutor<TypeProduct> {
}
