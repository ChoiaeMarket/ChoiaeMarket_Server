package com.choiaemarket.choiaemarket_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choiaemarket.choiaemarket_server.entity.BoardEntity;
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

    boolean existsByBoardNumber(Integer boardNumber);
    
    BoardEntity findByBoardNumber(Integer boardNumber);
    
    List<BoardEntity> findByWriterEmail(String email);
}
