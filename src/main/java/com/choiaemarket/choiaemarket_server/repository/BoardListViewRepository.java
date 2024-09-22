package com.choiaemarket.choiaemarket_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choiaemarket.choiaemarket_server.entity.BoardListViewEntity;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer>{
    List<BoardListViewEntity> findByOrderByWriteDatetimeDesc();
    List<BoardListViewEntity> findByIdolContainsOrNameContainsOrTitleContainsOrContentContainsOrderByWriteDatetimeDesc(String idol, String name, String title, String content);
    List<BoardListViewEntity> findByBoardNumberInOrderByWriteDatetimeDesc(List<Integer> boardNumbers);
    List<BoardListViewEntity> findByWriterEmailOrderByWriteDatetimeDesc(String writerEmail);
}
