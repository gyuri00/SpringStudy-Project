package com.likelion.LionCommunity.domain.tblBoard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardResponseDTO {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class searchBoard{
        private Long boardSeq;
        private String title;
        private String content;
    }
}
