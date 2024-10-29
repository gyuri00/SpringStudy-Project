package com.likelion.LionCommunity.domain.tblBoard;

import com.likelion.LionCommunity.domain.tblBoard.dto.BoardResponseDTO;
import com.likelion.LionCommunity.domain.tblBoard.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardServiceImpl boardService;

    @GetMapping("/form")
    public String showBoardForm() {
        return "board";
    }

    @GetMapping("/list")
    public String showBoardList(Model model) {
        List<BoardResponseDTO.searchBoard> boards = boardService.search();
        model.addAttribute("boards", boards);
        return "boardList";
    }

    @GetMapping("/list/{id}")
    public String showBoardList(@PathVariable("id") Long id, Model model) {
        BoardResponseDTO.searchBoard boards = boardService.searchDetail(id);
        log.info("boards : " + boards.getBoardSeq());
        model.addAttribute("board", boards);
        return "boardDetail";
    }

    @PostMapping("/save")
    public String save(@RequestParam("title") String title,
                       @RequestParam("content") String content){
        log.info("title : " + title);
        log.info("content : " + content);
        boardService.save(title, content);
        return "redirect:/api/board/list";
    }
}
