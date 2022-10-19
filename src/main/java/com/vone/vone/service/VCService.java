package com.vone.vone.service;

import com.vone.vone.data.dto.*;
import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.VC;

import java.util.List;

public interface VCService {
    Long issueVC(VC2IssueDto vc2IssueDto);

    List<VC2IssueDto> getVCByIssuerId(String issuerId);

    List<VCDto> getVCByHolderId(String holderId);
    boolean submitVC(VC2VerifyDto vc2VerifyDto);

    List<SubmittedVCResponseDto> getSubmittedVCByHolder (String holderId);

    List<SubmittedVCResponseDto> getSubmittedVCByVerifier (String verifier, Long postId);
}
