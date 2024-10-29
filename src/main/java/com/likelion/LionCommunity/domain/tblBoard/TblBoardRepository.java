package com.likelion.LionCommunity.domain.tblBoard;

import com.likelion.LionCommunity.entity.TblBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblBoardRepository extends JpaRepository<TblBoard, Long> {
}
