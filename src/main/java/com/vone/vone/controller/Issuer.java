package com.vone.vone.controller;

import com.vone.vone.data.dto.ContextDto;
import com.vone.vone.data.dto.PostResponseDto;
import com.vone.vone.data.entity.Context;
import com.vone.vone.data.entity.VC2Issue;
import com.vone.vone.service.ContextService;
import com.vone.vone.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/issuer")
public class Issuer {

    private final ContextService contextService;

    @Autowired
    public Issuer (ContextService contextService) {
        this.contextService = contextService;
    }


    @ApiOperation(value = "VC Context 생성", notes = "새로운 context를 생성합니다.")
    @PostMapping("/context")
    public ResponseEntity<ContextDto> createContext(@RequestBody ContextDto contextdto) {
        // 1. noSQL 생성
        contextService.saveContext(contextdto);

        return ResponseEntity.status(HttpStatus.OK).body(contextdto);
    }

    @ApiOperation(value = "VC 발행", notes = "새로운 vc를 발행합니다.")
    @PostMapping("/vc")
    public ArrayList<VC2Issue> issueVC(@RequestBody ArrayList<VC2Issue> postData) {
        // 1. noSQL에 VC 생성
        // 2. HolderVCs 테이블에 칼럼 추가
        return postData;
    }

    @ApiOperation(value = "Context 목록", notes = "현재 존재하는 모든 Context 목록을 출력합니다.")
    @GetMapping("/context-list")
    public ResponseEntity<List<ContextDto>> getContextList() {
        // 1. 모든 context 출력
        List<ContextDto> contextDtos = contextService.getAllContext();

        return ResponseEntity.status(HttpStatus.OK).body(contextDtos);
    }

//    @ApiOperation(value = "발행한 Context 목록", notes = "해당 Issuer가 발행한 Context 목록을 반환합니다.")
//    @GetMapping("/context-list/{issuerId}")
//    public String getIssuedContextList(@PathVariable String issuerId) {
//        return issuerId;
//    }

    @ApiOperation(value = "발행한 VC 목록", notes = "해당 Context인 VC 목록을 반환합니다.")
    @GetMapping("/vc-list/{context}")
    public String getIssuedVCList(@PathVariable String context) {
        return context;
    }
}
