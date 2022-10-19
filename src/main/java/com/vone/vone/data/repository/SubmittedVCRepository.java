package com.vone.vone.data.repository;

import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.SubmittedVC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmittedVCRepository extends JpaRepository<SubmittedVC,Long> {
    List<SubmittedVC> findByHolderId(String holderId);

    List<SubmittedVC> findByVerifierIdAndPostId(String verifierId, Long postId);
}
