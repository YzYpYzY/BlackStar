package com.spring.henallux.BlackStar.dataAccess.repository;

import com.spring.henallux.BlackStar.dataAccess.entity.ProductEntity;
import com.spring.henallux.BlackStar.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

    public ProductEntity  findById(int id);
    public ArrayList<ProductEntity> findAll();
}
