package com.vone.vone.service.impl;

import com.vone.vone.data.dao.ContextDAO;
import com.vone.vone.data.dto.ContextDto;
import com.vone.vone.data.dto.CredentialSubject;
import com.vone.vone.data.entity.Context;
import com.vone.vone.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContextServiceImpl implements ContextService {
    private final ContextDAO contextDAO;

    @Autowired
    public ContextServiceImpl(ContextDAO contextDAO){
        this.contextDAO = contextDAO;
    }

    @Override
    public List<ContextDto> getAllContext(){
        List<Context> contexts = contextDAO.selectAllContext();
        List<ContextDto> contextDtos = new ArrayList<>();

        for (Context context : contexts) {
            CredentialSubject credentialSubject = new CredentialSubject(context.getContextValues());
            ContextDto contextDto = new ContextDto(context.getContext(),credentialSubject);

            contextDtos.add(contextDto);
        }

        return contextDtos;
    }

    @Override
    public ContextDto saveContext(ContextDto contextDto){
        Context context = new Context();
        context.setContext(contextDto.getContext());
        context.setContextValues(contextDto.getCredentialSubject().getValues());
        context.setUpdatedAt(LocalDateTime.now());
        context.setCreatedAt(LocalDateTime.now());

        Context savedContext = contextDAO.insertContext(context);

        return contextDto;
    }

    @Override
    public List<ContextDto> getVCsContext(List<String> contexts) {
        List<ContextDto> contextDtos = new ArrayList<>();

        for (String contextName : contexts) {
            Context context = contextDAO.selectContext(contextName);
            CredentialSubject credentialSubject = new CredentialSubject(context.getContextValues());
            ContextDto contextDto = new ContextDto(contextName,credentialSubject);

            contextDtos.add(contextDto);
        }
        return contextDtos;
    }
}
