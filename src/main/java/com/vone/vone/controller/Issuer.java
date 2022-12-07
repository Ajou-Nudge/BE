package com.vone.vone.controller;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vone.vone.data.dto.*;
import com.vone.vone.service.ContextService;
import com.vone.vone.service.KlaytnService;
import com.vone.vone.service.VCService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/issuer")
public class Issuer {

    private final ContextService contextService;
    private final VCService vcService;
    private final KlaytnService klaytnService;

    @Autowired
    public Issuer (ContextService contextService, VCService vcService,KlaytnService klaytnService) {
        this.contextService = contextService;
        this.vcService = vcService;
        this.klaytnService = klaytnService;
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
    public ResponseEntity<List<VC2ResponseDto>> issueVC(@RequestBody ArrayList<VC2IssueDto> vcs) throws Exception {
        List<VC2ResponseDto> vc2ResponseDtos = new ArrayList<>();
        for(VC2IssueDto vc : vcs){
            VC2ResponseDto hash = vcService.issueVC(vc);
            vc2ResponseDtos.add(hash);
        }
        return ResponseEntity.status(HttpStatus.OK).body(vc2ResponseDtos);
    }

    @Operation(summary = "Context 목록", description = "현재 존재하는 모든 Context 목록을 출력합니다.")
    @GetMapping("/context-list")
    public ResponseEntity<List<ContextDto>> getContextList() {
        List<ContextDto> contextDtos = contextService.getAllContext();

        return ResponseEntity.status(HttpStatus.OK).body(contextDtos);
    }

    @Operation(summary = "발행한 VC의 Context 목록", description = "해당 Issuer가 발행한 Context 목록을 반환합니다.")
    @GetMapping("/context-list/{issuerId}")
    public ResponseEntity<List<ContextDto>> getIssuedContextList(@PathVariable String issuerId) {
        List <String> contexts = vcService.getVCsContextByIssuerId(issuerId);
        List <ContextDto> contextsDtos = contextService.getVCsContext(contexts);


        Map<String, String> result;

        return ResponseEntity.status(HttpStatus.OK).body(contextsDtos);
    }

    @Operation(summary = "발행한 VC 목록", description = "해당 Context인 VC 목록을 반환합니다.")
    @GetMapping("/vc-list")
    public ResponseEntity<List<VC2IssueDto>> getIssuedVCListByContext() throws JsonProcessingException, JSONException {
        List<VC2IssueDto> vcs = vcService.getAllVC();
        return ResponseEntity.status(HttpStatus.OK).body(vcs);
    }

    @PostMapping("/hash")
    public ResponseEntity<String> getHash(@RequestBody List<String> credentialSubject) throws Exception {
        String res = klaytnService.hash(credentialSubject);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
