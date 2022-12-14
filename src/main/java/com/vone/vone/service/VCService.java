package com.vone.vone.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vone.vone.data.dto.*;
import com.vone.vone.data.entity.Context;
import com.vone.vone.data.entity.HoldersVC;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.util.List;
import java.util.Map;

public interface VCService {
    Map<Long, String> issueVC(VC2IssueDto vc2IssueDto) throws Exception;

    List<String> getVCsContextByIssuerId(String issuerId);

    List<VC2HolderDto> getVCByHolderId(String holderId) throws JSONException;
    Long listVC(VC2IssueDto vc2IssueDto) throws Exception;
    boolean submitVC(VC2VerifyDto vc2VerifyDto) throws JSONException;

    List<VC2IssueDto> getVCByContext(String context) throws JSONException;
    String getFileNameById(Long id) throws JSONException;
    List<VC2IssueDto> getAllVC() throws JsonProcessingException, JSONException;

    List<SubmittedVCResponseDto> getSubmittedVCByHolder (String holderId);

    List<SubmittedVCResponseDto> getSubmittedVCByVerifier (String verifier, Long postId);
}
