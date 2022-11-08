package com.vone.vone.service;

import com.vone.vone.data.dto.ContextDto;

import java.util.List;

public interface ContextService {
    ContextDto saveContext(ContextDto ContextDto);

    List<ContextDto> getAllContext();

}
