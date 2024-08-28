package com.choiaemarket.choiaemarket_server.service;

import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.dto.response.search.GetPopularListResponseDto;

public interface SearchService {
    
    ResponseEntity<? super GetPopularListResponseDto> getPopularList();

}
