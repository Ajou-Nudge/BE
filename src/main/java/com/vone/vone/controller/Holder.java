package com.vone.vone.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/holder")
public class Holder {

    @ApiOperation(value = "채용 공고 목록", notes = "Verifier가 등록한 채용공고 목록을 확인합니다.")
    @GetMapping("/post-list")
    public String getPostList() {
        return "준비중";
    }

    @ApiOperation(value = "보유 인증서 목록", notes = "Holder가 보유중인 vc 리스트를 반환합니다.")
    @GetMapping("/vc-list/{holderId}")
    public String getVCList(@PathVariable String holderId) {
        return "준비중";
    }

    @ApiOperation(value = "인증서 제출", notes = "인증서와 인증서 내용을 제출합니다.")
    @PostMapping("/submitted-vc")
    public String submitVCs(@RequestBody Map<String, Object> postData) {
        return "준비중";
    }

    @ApiOperation(value = "제출된 인증서 목록", notes = "자기가 제출한 인증서 목록을 가져옵니다.")
    @GetMapping("/submitted-vc-list/{holderId}")
    public String submitVCsList(@PathVariable String holderId) {
        return "준비중";
    }
}
