package com.vone.vone.service;


import com.vone.vone.data.dto.CredentialSubject;

import java.util.List;

public interface KlaytnService {
    public boolean verify(Long vcId) throws Exception;

    public String hash( String _value1,String _value2,String _value3,String _value4,String _value5,String _value6,String _value7,String _value8) throws Exception;


}
