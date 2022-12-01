package com.vone.vone.service.impl;

import com.vone.vone.data.dto.VC2IssueDto;
import com.vone.vone.data.entity.Context;
import com.vone.vone.data.repository.ContextRepository;
import com.vone.vone.service.JsonParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonParsingServiceImpl implements JsonParsingService {
    private ContextRepository contextRepository;

    @Autowired
    public JsonParsingServiceImpl(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    @Override
    public String vc2IssueDtoToJson(VC2IssueDto vc2IssueDto) {
        Context context = contextRepository.findByContext(vc2IssueDto.getVc().getContext());
    return "VC2IssueDto{" +
            "holderId='" + vc2IssueDto.getHolderId() + '\'' +
            ", VCDto{" +
                "context='" + vc2IssueDto.getVc().getContext() + '\'' +
                ", issuer='" + vc2IssueDto.getVc().getIssuer() + '\'' +
                ", CredentialSubject{" +
                    vc2IssueDto.getVc().getCredentialSubject().getValues() +
                    context.getContextValues().get(0) +"=" + vc2IssueDto.getVc().getCredentialSubject().getValues().get(0) +
                    context.getContextValues().get(1) + "=" + vc2IssueDto.getVc().getCredentialSubject().getValues().get(1) +
                    context.getContextValues().get(2) + "=" + vc2IssueDto.getVc().getCredentialSubject().getValues().get(2) +
                    context.getContextValues().get(3) + "=" + vc2IssueDto.getVc().getCredentialSubject().getValues().get(3) +
                    context.getContextValues().get(4) + "=" + vc2IssueDto.getVc().getCredentialSubject().getValues().get(4) +
                    context.getContextValues().get(5) + "=" + vc2IssueDto.getVc().getCredentialSubject().getValues().get(5) +
                    context.getContextValues().get(6) + "=" + vc2IssueDto.getVc().getCredentialSubject().getValues().get(6) +
                    context.getContextValues().get(7) + "=" + vc2IssueDto.getVc().getCredentialSubject().getValues().get(7) +
                '}';
    }
}
