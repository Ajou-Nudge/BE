package com.vone.vone.service;

import com.vone.vone.data.dao.ContextDAO;
import com.vone.vone.data.dto.ContextDto;
import com.vone.vone.data.dto.CredentialSubject;
import com.vone.vone.data.entity.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContextService {

    private ContextDAO contextDAO;

    @Transactional
    public ContextDto saveContext(ContextDto contextDto)
    {
        Context context = new Context();
        context.setContext(contextDto.getContext());
        context.setValue1(contextDto.getCredentialSubject().getValue1());
        context.setValue2(contextDto.getCredentialSubject().getValue2());
        context.setValue3(contextDto.getCredentialSubject().getValue3());
        context.setValue4(contextDto.getCredentialSubject().getValue4());
        context.setValue5(contextDto.getCredentialSubject().getValue5());
        context.setValue6(contextDto.getCredentialSubject().getValue6());
        context.setValue7(contextDto.getCredentialSubject().getValue7());
        context.setValue8(contextDto.getCredentialSubject().getValue8());
        context.setUpdatedAt(LocalDateTime.now());
        context.setCreatedAt(LocalDateTime.now());

        Context savedContext = contextDAO.insertContext(context);

        return contextDto;
    }

    @Transactional
    public List<ContextDto> getAllContext()
    {
        List<Context> contexts = contextDAO.selectAllContext();
        List<ContextDto> contextDtos = new ArrayList<>();

        for (Context context : contexts) {
            CredentialSubject credentialSubject = new CredentialSubject(context.getValue1(),context.getValue2(),context.getValue3(),context.getValue4(),context.getValue5(),context.getValue6(),context.getValue7(),context.getValue8());
            ContextDto contextDto = new ContextDto(context.getContext(),credentialSubject);

            contextDtos.add(contextDto);
        }

        return contextDtos;
    }
}
