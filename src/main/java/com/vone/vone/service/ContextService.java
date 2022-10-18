package com.vone.vone.service;

import com.vone.vone.data.dto.ContextDto;
import com.vone.vone.data.dto.PostResponseDto;
import com.vone.vone.data.entity.PostDto;

import java.util.List;

public interface ContextService {
    ContextDto saveContext(ContextDto ContextDto);

    List<ContextDto> getAllContext();
}
