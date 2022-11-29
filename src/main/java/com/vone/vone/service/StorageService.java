package com.vone.vone.service;

import com.vone.vone.data.dto.VCForSelfIssueDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	Long store(MultipartFile file, VCForSelfIssueDto vcForSelfIssueDto);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
