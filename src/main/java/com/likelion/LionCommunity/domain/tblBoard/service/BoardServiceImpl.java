package com.likelion.LionCommunity.domain.tblBoard.service;

import com.likelion.LionCommunity.domain.tblBoard.TblBoardRepository;
import com.likelion.LionCommunity.domain.tblBoard.dto.BoardResponseDTO;
import com.likelion.LionCommunity.entity.TblBoard;
import com.likelion.LionCommunity.global.response.ApiResponse;
import com.likelion.LionCommunity.global.response.resEnum.SuccessCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {
    private final TblBoardRepository boardRepository;

    /**
     * 게시글 저장
     */
    @Override
    @Transactional
    public ApiResponse<?> save(String title, String content) {
        TblBoard tblBoard = TblBoard.builder()
                .title(title)
                .content(content)
                .build();
        boardRepository.save(tblBoard);
        return ApiResponse.SUCCESS(SuccessCode.SAVE_CONTENT);
    }

    /**
     * 게시글 목록 조회
     * @return 게시글 목록 데이터
     */
    @Override
    public List<BoardResponseDTO.searchBoard> search() {
        return boardRepository.findAll().stream()
                .map(board -> new BoardResponseDTO.searchBoard(
                        board.getBoardSeq(),
                        board.getTitle(),
                        board.getContent()
                ))
                .collect(Collectors.toList());
    }

    /**
     * 게시글 단건 조회
     * @param id
     * @return 게시글 단건 데이터
     */
    @Override
    public BoardResponseDTO.searchBoard searchDetail(Long id) {
        return boardRepository.findById(id)
                .map(board -> new BoardResponseDTO.searchBoard(
                        board.getBoardSeq(),
                        board.getTitle(),
                        board.getContent()
                ))
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }

    /**
     * 게시글 수정
     */
    @Override
    @Transactional
    public ApiResponse<?> update(Long id, String title, String content) {
        TblBoard tblBoard = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. ID: " + id));

        // 필드 업데이트
        tblBoard.updateBoard(title, content);
        boardRepository.save(tblBoard);
        return ApiResponse.SUCCESS(SuccessCode.SAVE_CONTENT);
    }

    /**
     * 게시글 삭제
     * @param id
     */
    @Override
    @Transactional
    public ApiResponse<?> delete(Long id) {
        boardRepository.deleteById(id);
        return ApiResponse.SUCCESS(SuccessCode.DELETE_BOARD);
    }
}
