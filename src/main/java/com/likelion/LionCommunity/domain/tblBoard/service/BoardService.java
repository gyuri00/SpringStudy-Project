package com.likelion.LionCommunity.domain.tblBoard.service;

import com.likelion.LionCommunity.domain.tblBoard.dto.BoardRequestDTO;
import com.likelion.LionCommunity.domain.tblBoard.dto.BoardResponseDTO;
import com.likelion.LionCommunity.global.response.ApiResponse;

import java.util.List;

public interface BoardService {
    ApiResponse<?> save(String title, String content); // 게시글 저장

    List<BoardResponseDTO.searchBoard> search();

    BoardResponseDTO.searchBoard searchDetail(Long id);
}
