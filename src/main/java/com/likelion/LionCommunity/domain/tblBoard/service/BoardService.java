package com.likelion.LionCommunity.domain.tblBoard.service;

import com.likelion.LionCommunity.domain.tblBoard.dto.BoardRequestDTO;
import com.likelion.LionCommunity.domain.tblBoard.dto.BoardResponseDTO;
import com.likelion.LionCommunity.global.response.ApiResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardService {
    ApiResponse<?> save(String title, String content); // 게시글 저장

    List<BoardResponseDTO.searchBoard> search(); // 게시글 목록 조회

    BoardResponseDTO.searchBoard searchDetail(Long id); // 게시글 단건 조회

    @Transactional
    ApiResponse<?> update(Long id, String title, String content); // 게시글 수정

    @Transactional
    ApiResponse<?> delete(Long id); // 게시글 삭제
}
