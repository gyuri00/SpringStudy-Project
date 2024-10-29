package com.likelion.LionCommunity.entity;

import com.likelion.LionCommunity.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
public class TblBoard extends BaseEntity {
    @Id
    @Comment(value="게시글 구분자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardSeq;         //구분자
    @Column(columnDefinition = "varchar(500) null comment '내용'")
    private String content;
    @Column(columnDefinition = "varchar(50) null comment'제목'")
    private String title;

    @Builder
    public TblBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
