package com.vone.vone.service;

import com.vone.vone.data.dto.VC2IssueDto;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface JsonParsingService {
    JSONObject vc2IssueDtoToJson (VC2IssueDto vc2IssueDto);
}
