package com.likelion.LionCommunity.domain.tblBoard.service;

import com.likelion.LionCommunity.domain.tblBoard.TblBoardRepository;
import com.likelion.LionCommunity.domain.tblBoard.dto.BoardResponseDTO;
import com.likelion.LionCommunity.entity.TblBoard;
import com.likelion.LionCommunity.global.response.ApiResponse;
import com.likelion.LionCommunity.global.response.resEnum.SuccessCode;
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
        log.info("게시글 저장");
        TblBoard tblBoard = TblBoard.builder()
                .title(title)
                .content(content)
                .build();
        log.info("tblBoard : "+tblBoard.getContent());
        boardRepository.save(tblBoard);
        return ApiResponse.SUCCESS(SuccessCode.SAVE_CONTENT);
    }

    /**
     * 게시글 조회
     * @return
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

}
