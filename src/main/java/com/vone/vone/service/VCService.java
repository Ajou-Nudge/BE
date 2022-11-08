package com.vone.vone.service;

import com.vone.vone.data.dto.SubmittedVCResponseDto;
import com.vone.vone.data.dto.VC2IssueDto;
import com.vone.vone.data.dto.VC2VerifyDto;
import com.vone.vone.data.dto.VCDto;

import java.util.List;

public interface VCService {
    Long issueVC(VC2IssueDto vc2IssueDto);

    List<String> getVCsContextByIssuerId(String issuerId);

    List<VCDto> getVCByHolderId(String holderId);

    boolean submitVC(VC2VerifyDto vc2VerifyDto);

    List<VC2IssueDto> getVCByContext(String context);

    List<SubmittedVCResponseDto> getSubmittedVCByHolder (String holderId);

    List<SubmittedVCResponseDto> getSubmittedVCByVerifier (String verifier, Long postId);
}
