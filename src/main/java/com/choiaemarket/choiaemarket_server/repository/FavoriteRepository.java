package com.choiaemarket.choiaemarket_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choiaemarket.choiaemarket_server.entity.FavoriteEntity;
import com.choiaemarket.choiaemarket_server.entity.primaryKey.FavoritePk;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk>{
    
}
