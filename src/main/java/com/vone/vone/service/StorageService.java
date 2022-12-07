package com.vone.vone.service;

import com.vone.vone.data.dto.VC2IssueDto;
import com.vone.vone.data.dto.VC2ResponseDto;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	Long store(MultipartFile file, VC2IssueDto vc2IssueDto)  throws Exception;

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(Long id) throws JSONException;

	void deleteAll();

}
