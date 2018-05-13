package com.spring.henallux.BlackStar.dataAccess.repository;

import com.spring.henallux.BlackStar.dataAccess.entity.LanguageEntity;
import com.spring.henallux.BlackStar.dataAccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer>{

    public LanguageEntity findById(int id);

    public LanguageEntity findByIsocode(String isocode);

}
