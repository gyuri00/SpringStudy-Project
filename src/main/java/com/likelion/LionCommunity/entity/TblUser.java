package com.likelion.LionCommunity.entity;

import com.likelion.LionCommunity.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.Comment;

@Entity
public class TblUser extends BaseEntity {
    @Id
    @Comment(value="사용자 구분자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;         //구분자
}
