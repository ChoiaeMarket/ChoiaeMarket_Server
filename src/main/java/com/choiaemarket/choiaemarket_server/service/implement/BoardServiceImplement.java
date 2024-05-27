package com.choiaemarket.choiaemarket_server.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choiaemarket.choiaemarket_server.dto.request.board.PostBoardRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetBoardResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.PostBoardResponseDto;
import com.choiaemarket.choiaemarket_server.entity.BoardEntity;
import com.choiaemarket.choiaemarket_server.entity.ImageEntity;
import com.choiaemarket.choiaemarket_server.entity.ProductEntity;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;
import com.choiaemarket.choiaemarket_server.repository.BoardRepository;
import com.choiaemarket.choiaemarket_server.repository.ImageRepository;
import com.choiaemarket.choiaemarket_server.repository.ProductRepository;
import com.choiaemarket.choiaemarket_server.repository.UserRepository;
import com.choiaemarket.choiaemarket_server.service.BoardService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService{
    
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {

        BoardEntity boardEntity = null;
        List<ImageEntity> imageEntities = new ArrayList<>();
        
        try {
            boardEntity = boardRepository.findByBoardNumber(boardNumber);   // board 정보 가져오기
            if (boardEntity == null) return GetBoardResponseDto.notExistBoard(); // 해당 boardNuber의 board가 없으면 notExistBoard 반환

            imageEntities = imageRepository.findByBoardNumber(boardNumber); // 이미지 가져오기

            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardResponseDto.success(boardEntity, imageEntities);
    }

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
        try {
            boolean existedEmail = userRepository.existsByEmail(email);
            if (!existedEmail) return PostBoardResponseDto.notExistUser();

            // 이메일을 이용해 사용자 정보를 조회
            UserEntity user = userRepository.findByEmail(email);
            if (user == null) return PostBoardResponseDto.notExistUser();

            String nickname = user.getNickname();   // 작성자의 닉네임을 가져오기
            String profileImage = user.getProfileImage();   // 작성자의 프로필사진을 가져오기

            // Product 정보를 가져오기
            ProductEntity product = productRepository.findByName(dto.getName());
            if (product == null) return PostBoardResponseDto.notExistProduct();

            int productNumber = product.getProductNumber();

            BoardEntity boardEntity = new BoardEntity(dto, email, nickname, profileImage, productNumber);  // 게시물 엔티티 만들기
            boardRepository.save(boardEntity);  // 저장하면 boardNumber가 나옴
        
            int boardNumber = boardEntity.getBoardNumber(); // 나온 boardNumber을 가지고

            List<String> boardImageList = dto.getBoardImageList();  // 리스트를 만들기
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image: boardImageList){
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities); // 한번에 저장

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }

}
