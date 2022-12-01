package com.vone.vone.service;


import com.vone.vone.data.dto.CredentialSubject;

import java.util.List;

public interface KlaytnService {
    public boolean verify(Long vcId) throws Exception;

    public String hash(List<String> values) throws Exception;
}
