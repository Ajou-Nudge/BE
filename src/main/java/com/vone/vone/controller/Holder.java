package com.vone.vone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vone.vone.data.dto.*;
import com.vone.vone.data.entity.VC;
import com.vone.vone.service.PostService;
import com.vone.vone.service.StorageService;
import com.vone.vone.service.VCService;
import com.vone.vone.service.impl.StorageFileNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/holder")
public class Holder {
    private final PostService postService;
    private final VCService vcService;
    private final StorageService storageService;

    private final ObjectMapper objectMapper;
    @Autowired
    public Holder (PostService postService, VCService vcService, StorageService storageService, ObjectMapper objectMapper) {
        this.postService = postService;
        this.vcService = vcService;
        this.storageService=storageService;
        this.objectMapper = objectMapper;
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

    @ApiOperation(value = "인증서 셀프 등록", notes = "인증서를 등록합니다.")
    @PostMapping("/vc")
    public ResponseEntity<Long> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("data") String data) throws Exception{
        VC2IssueDto vcForSelfIssueDto = null;
        vcForSelfIssueDto = objectMapper.readValue(data,VC2IssueDto.class);
        System.out.println(vcForSelfIssueDto);
        Long res = storageService.store(file, vcForSelfIssueDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @ApiOperation(value = "셀프등록한 인증서 다운", notes = "구현예정")
    @GetMapping("/file/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable Long id) {
        Resource file = storageService.loadAsResource(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
