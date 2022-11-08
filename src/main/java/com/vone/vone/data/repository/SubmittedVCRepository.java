package com.vone.vone.data.repository;

import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.SubmittedVC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmittedVCRepository extends JpaRepository<SubmittedVC,Long> {
    List<SubmittedVC> findByHolderId(String holderId);

    List<SubmittedVC> findByVerifierIdAndPostId(String verifierId, Long postId);
}
