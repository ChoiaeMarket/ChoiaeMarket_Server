package com.choiaemarket.choiaemarket_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choiaemarket.choiaemarket_server.entity.SearchLogEntity;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer>{
    
}
