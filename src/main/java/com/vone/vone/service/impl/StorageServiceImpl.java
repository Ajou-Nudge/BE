package com.vone.vone.service.impl;

import com.vone.vone.data.dto.VC2IssueDto;
import com.vone.vone.service.StorageService;
import com.vone.vone.service.VCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

	private final Path rootLocation;
	private final VCService vcService;

	@Autowired
	public StorageServiceImpl(StorageProperties properties, VCService vcService) {
		this.rootLocation = Paths.get(properties.getLocation());
		this.vcService = vcService;
	}

	@Override
	public Long store(MultipartFile file, VC2IssueDto vc2IssueDto) throws Exception {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			String fileName = file.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			if(!ext.equals("pdf") && !ext.equals("jpeg") && !ext.equals("png") && !ext.equals("jpg")){
				throw new StorageException("Failed to store file(Must Have To pdf, jpeg, jpg, png).");
			}
			Path destinationFile = this.rootLocation.resolve(
					Paths.get(fileName))
					.normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
			vc2IssueDto.getVc().getCredentialSubject().put("fileName", fileName);
			Long CertificateId = vcService.listVC(vc2IssueDto);
			return CertificateId;
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}


	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(Long id) throws JSONException {
		String filename = vcService.getFileNameById(id);
		try {

			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	private Resource loadAsResourceByName(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
