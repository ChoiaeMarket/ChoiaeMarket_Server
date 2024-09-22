package com.choiaemarket.choiaemarket_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choiaemarket.choiaemarket_server.entity.FavoriteEntity;
import com.choiaemarket.choiaemarket_server.entity.primaryKey.FavoritePk;

import jakarta.transaction.Transactional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk>{
    
    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber, String userEmail);

    @Transactional
    void deleteByBoardNumber(Integer boardNumber);

    List<FavoriteEntity> findByUserEmail(String userEmail);

}
