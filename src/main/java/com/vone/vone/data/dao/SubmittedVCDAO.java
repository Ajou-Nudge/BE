package com.vone.vone.data.dao;

import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.SubmittedVC;

import java.util.List;

public interface SubmittedVCDAO {
    SubmittedVC insertSubmittedVC(SubmittedVC submittedVC);

    List<SubmittedVC> getSubmittedVCByHolderId(String holder);
    List<SubmittedVC> getSubmittedVCByVerifierId(String verifer, Long postId);
}
