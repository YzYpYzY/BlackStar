package com.spring.henallux.BlackStar.dataAccess.repository;

import com.spring.henallux.BlackStar.dataAccess.entity.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Integer> {
}
