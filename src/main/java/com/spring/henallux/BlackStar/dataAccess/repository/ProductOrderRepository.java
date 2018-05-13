package com.spring.henallux.BlackStar.dataAccess.repository;

import com.spring.henallux.BlackStar.dataAccess.entity.ProductOrderEntity;
import com.spring.henallux.BlackStar.dataAccess.util.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductOrderRepository extends JpaRepository<ProductOrderEntity, Integer> {
    @Query("SELECT p FROM ProductOrderEntity p WHERE p.user.username = :userName AND p.state = :state")
    public ProductOrderEntity findFirst(@Param("userName") String userName,@Param("state") OrderState state);
}
