package com.vone.vone.controller;

import com.vone.vone.data.dto.ContextDto;
import com.vone.vone.data.dto.CredentialSubject;
import com.vone.vone.data.dto.VC2IssueDto;
import com.vone.vone.data.dto.VCDto;
import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.repository.VCRepository;
import com.vone.vone.service.ContextService;
import com.vone.vone.service.KlaytnService;
import com.vone.vone.service.VCService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<ContextDto> contextDtos = contextService.getAllContext();

        return ResponseEntity.status(HttpStatus.OK).body(contextDtos);
    }

    @Operation(summary = "발행한 VC의 Context 목록", description = "해당 Issuer가 발행한 Context 목록을 반환합니다.")
    @GetMapping("/context-list/{issuerId}")
    public ResponseEntity<List<String>> getIssuedContextList(@PathVariable String issuerId) {
        List <String> contexts = vcService.getVCsContextByIssuerId(issuerId);

        return ResponseEntity.status(HttpStatus.OK).body(contexts);
    }

    @Operation(summary = "발행한 VC 목록", description = "해당 Context인 VC 목록을 반환합니다.")
    @GetMapping("/vc-list/{context}")
    public ResponseEntity<List<VC2IssueDto>> getIssuedVCListByContext(@PathVariable String context) {
        List<VC2IssueDto> vcs = vcService.getVCByContext(context);

        return ResponseEntity.status(HttpStatus.OK).body(vcs);
    }

    @PostMapping("/hash")
    public ResponseEntity<String> getHash(@RequestBody  CredentialSubject credentialSubject) throws Exception {
        String res = klaytnService.hash(credentialSubject.getValue1(),credentialSubject.getValue2(),credentialSubject.getValue3(),credentialSubject.getValue4(),credentialSubject.getValue5(),credentialSubject.getValue6(),credentialSubject.getValue7(),credentialSubject.getValue8());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
