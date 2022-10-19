package com.vone.vone.controller;

import com.vone.vone.data.dto.PostResponseDto;
import com.vone.vone.data.dto.SubmittedVCResponseDto;
import com.vone.vone.data.dto.VC2VerifyDto;
import com.vone.vone.data.dto.VCDto;
import com.vone.vone.data.entity.VC;
import com.vone.vone.service.PostService;
import com.vone.vone.service.VCService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/holder")
public class Holder {
    private final PostService postService;
    private final VCService vcService;
    @Autowired
    public Holder (PostService postService, VCService vcService) {
        this.postService = postService;
        this.vcService = vcService;
    }
    @ApiOperation(value = "채용 공고 목록", notes = "Verifier가 등록한 채용공고 목록을 확인합니다.")
    @GetMapping("/post-list")
    public  ResponseEntity<List<PostResponseDto>> getPostList() {
        List<PostResponseDto> postResponseDtos =  postService.getAllPost();
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDtos);
    }

    @ApiOperation(value = "보유 인증서 목록", notes = "Holder가 보유중인 vc 리스트를 반환합니다.")
    @GetMapping("/vc-list/{holderId}")
    public ResponseEntity<List<VCDto>> getVCList(@PathVariable String holderId) {
        List<VCDto> vcDtos = vcService.getVCByHolderId(holderId);
        return ResponseEntity.status(HttpStatus.OK).body(vcDtos);
    }

    @ApiOperation(value = "인증서 제출", notes = "인증서와 인증서 내용을 제출합니다.")
    @PostMapping("/submitted-vc")
    public ResponseEntity<String> submitVCs(@RequestBody VC2VerifyDto vc2VerifyDto) {
        vcService.submitVC(vc2VerifyDto);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @ApiOperation(value = "제출된 인증서 목록", notes = "자기가 제출한 인증서 목록을 가져옵니다.")
    @GetMapping("/submitted-vc-list/{holderId}")
    public ResponseEntity<List<SubmittedVCResponseDto>> submitVCsList(@PathVariable String holderId) {
        List<SubmittedVCResponseDto> submittedVCResponseDtos = vcService.getSubmittedVCByHolder(holderId);
        return ResponseEntity.status(HttpStatus.OK).body(submittedVCResponseDtos);
    }
}
