package com.vone.vone.service;

import com.vone.vone.data.dto.*;
import com.vone.vone.data.entity.Context;
import com.vone.vone.data.entity.HoldersVC;

import java.util.List;

public interface VCService {
    VC2ResponseDto issueVC(VC2IssueDto vc2IssueDto) throws Exception;

    List<String> getVCsContextByIssuerId(String issuerId);

    List<VCDto> getVCByHolderId(String holderId);
    Long listVC(VC2IssueDto vc2IssueDto) throws Exception;
    boolean submitVC(VC2VerifyDto vc2VerifyDto);

    List<VC2IssueDto> getVCByContext(String context);
    String getFileNameById(Long id);
    List<VC2IssueDto> getAllVC();

    List<SubmittedVCResponseDto> getSubmittedVCByHolder (String holderId);

    List<SubmittedVCResponseDto> getSubmittedVCByVerifier (String verifier, Long postId);
}
