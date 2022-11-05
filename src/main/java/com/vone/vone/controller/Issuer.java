package com.vone.vone.controller;

import com.vone.vone.data.dto.ContextDto;
import com.vone.vone.data.dto.CredentialSubject;
import com.vone.vone.data.dto.VC2IssueDto;
import com.vone.vone.data.dto.VCDto;
import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.repository.VCRepository;
import com.vone.vone.service.ContextService;
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
@RequestMapping("/issuer")
public class Issuer {

    private final ContextService contextService;
    private final VCService vcService;

    @Autowired
    public Issuer (ContextService contextService, VCService vcService) {
        this.contextService = contextService;
        this.vcService = vcService;
    }


    @Operation(summary = "VC Context 생성", description = "새로운 context를 생성합니다.")
    @PostMapping("/context")
    public ResponseEntity<ContextDto> createContext(@RequestBody ContextDto contextdto) {
        // 1. noSQL 생성
        contextService.saveContext(contextdto);

        return ResponseEntity.status(HttpStatus.OK).body(contextdto);
    }

    @Operation(summary = "VC 발행", description = "새로운 vc를 발행합니다.")
    @PostMapping("/vc")
    public ResponseEntity<List<Long>> issueVC(@RequestBody ArrayList<VC2IssueDto> vcs) {
        List<Long> vcIds = new ArrayList<>();
        for(VC2IssueDto vc : vcs){
            Long vcId = vcService.issueVC(vc);
            vcIds.add(vcId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(vcIds);
    }

    @Operation(summary = "Context 목록", description = "현재 존재하는 모든 Context 목록을 출력합니다.")
    @GetMapping("/context-list")
    public ResponseEntity<List<ContextDto>> getContextList() {
        // 1. 모든 context 출력
        List<ContextDto> contextDtos = contextService.getAllContext();

        return ResponseEntity.status(HttpStatus.OK).body(contextDtos);
    }

    // 이거 고쳐야됨. 지금 그냥 상수값임
    @Operation(summary = "발행한 Context 목록", description = "해당 Issuer가 발행한 Context 목록을 반환합니다.")
    @GetMapping("/context-list/{issuerId}")
    public ResponseEntity<List<String>> getIssuedContextList(@PathVariable String issuerId) {
        List<String> testResponse = new ArrayList<>();
        testResponse.add("graduate-certificate");
        return ResponseEntity.status(HttpStatus.OK).body(testResponse);
    }

    // 얘도 고쳐야됨. 얘도 상수값임
    @Operation(summary = "발행한 VC 목록", description = "해당 Context인 VC 목록을 반환합니다.")
    @GetMapping("/vc-list/{context}")
    public ResponseEntity<List<VC2IssueDto>> getIssuedVCListByContext(@PathVariable String context) {

        String testHolder = "dongjae712";
        String testIssuer = "ajouUniv";
        String testContext = "graduate-certificate";
        VCDto testVC = new VCDto(testContext,testIssuer,new CredentialSubject("Ajou-univ-graduate-certificate","DONGJAE-OH","2022-02-10","software","","","",""));

        List<VC2IssueDto> vcs = new ArrayList<>();
        VC2IssueDto vc2Issue = new VC2IssueDto(testHolder,testVC);
        vcs.add(vc2Issue);

        return ResponseEntity.status(HttpStatus.OK).body(vcs);
    }

//    @ApiOperation(value = "발행한 VC 목록", notes = "해당 Issuer가 발행한 VC 목록을 반환합니다.")
//    @GetMapping("/vc-list/{issuerId}")
//    public ResponseEntity<List<VC2IssueDto>> getIssuedVCList(@PathVariable String issuerId) {
//        List<VC2IssueDto> vcs = vcService.getVCByIssuerId(issuerId);
//
//
//        return ResponseEntity.status(HttpStatus.OK).body(vcs);
//    }
}
