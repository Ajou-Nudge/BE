package com.vone.vone.controller;

import com.vone.vone.data.dto.*;
import com.vone.vone.service.KlaytnService;
import com.vone.vone.service.PostService;
import com.vone.vone.service.VCService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/verifier")
public class Verifier {

    private final PostService postService;

    private final VCService vcService;

    private final KlaytnService klaytnService;

    @Autowired
    public Verifier (PostService postService, VCService vcService,KlaytnService klaytnService) {
        this.postService = postService;
        this.vcService = vcService;
        this.klaytnService = klaytnService;
    }

    @Operation(summary = "채용 공고 등록", description = "채용공고를 등록합니다.")
    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> post(@RequestBody PostDto postDto) {

        // 1. Posts 테이블에 항목 추가
        PostResponseDto postResponseDto = postService.savePost(postDto);

        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
        // 2. PostRequired 테이블 추가
     }

    @Operation(summary = "채용 공고 목록", description = "내가 등록한 채용 공고 목록을 확인합니다.")
    @GetMapping("/post-list/{verifierId}")
    public ResponseEntity<List<PostResponseDto>> getPostList(@PathVariable String verifierId) {

        // 1. Posts 테이블에서 내가 등록한 채용공고목록을 가져옴
        List<PostResponseDto> postList = postService.getAllPostByVerifier(verifierId);
        //System.out.println(postList);
        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }

    @Operation(summary = "제출받은 인증서 목록", description = "holder로부터 제출받은 인증서 목록을 가져옵니다.")
    @GetMapping("/submitted-vc-list/{verifierId}/{postId}")
    public ResponseEntity<List<SubmittedVCResponseDto>> getSubmittedVCs(@PathVariable String verifierId, @PathVariable Long postId) {

        // 1. SubmittedVCs 테이블에서 내가 받은 인증서 목록을 가져옴
        List<SubmittedVCResponseDto> submittedVCResponseDtos = vcService.getSubmittedVCByVerifier(verifierId, postId);
        return ResponseEntity.status(HttpStatus.OK).body(submittedVCResponseDtos);
    }

    @Operation(summary = "인증서 검증", description = "인증서 내용의 진위여부를 검증합니다.")
    @PostMapping("/verify")
    public ResponseEntity<List<String>> verify(@RequestBody VC2VerifyDto vc2VerifyDto) throws Exception {
        // 1. 검증
        List<String> result = new ArrayList<>();
        for (Long vcId : vc2VerifyDto.getVcIds()) {
            if (klaytnService.verify(vcId)) {
                result.add("true");
                continue;
            }
            result.add("false");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
