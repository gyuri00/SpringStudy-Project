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
    public String showBoardForm() { return "board"; }

    /**
     * 게시글 목록 조회
     * @param model
     */
    @GetMapping("/list")
    public String showBoardList(Model model) {
        List<BoardResponseDTO.searchBoard> boards = boardService.search();
        model.addAttribute("boards", boards);
        return "boardList";
    }

    /**
     * 게시글 단건 조회
     * @param id
     * @param model
     */
    @GetMapping("/list/{id}")
    public String showBoardList(@PathVariable("id") Long id, Model model) {
        BoardResponseDTO.searchBoard boards = boardService.searchDetail(id);
        model.addAttribute("board", boards);
        return "boardDetail";
    }

    /**
     * 게시글 저장
     * @param title
     * @param content
     */
    @PostMapping("/save")
    public String save(@RequestParam("title") String title,
                       @RequestParam("content") String content){
        boardService.save(title, content);
        return "redirect:/api/board/list";
    }

    /**
     * 게시글 수정
     * @param id
     * @param model
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardResponseDTO.searchBoard boards = boardService.searchDetail(id);
        model.addAttribute("board", boards);
        return "boardEdit";
    }

    /**
     * 게시글 수정 완료
     * @param id
     * @param title
     * @param content
     */
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestParam("title") String title,
                         @RequestParam("content") String content) {
        boardService.update(id, title, content);
        return "redirect:/api/board/list";
    }

    /**
     * 게시글 삭제
     * @param id
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/api/board/list";
    }
}
