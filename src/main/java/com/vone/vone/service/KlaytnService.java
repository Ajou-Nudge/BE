package com.vone.vone.service;


import java.util.List;

public interface KlaytnService {
    boolean verify(Long vcId) throws Exception;

    String hash(List<String> values) throws Exception;
}
