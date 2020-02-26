package com.nhanpiti.gpx.ws.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author nhanpiti
 */

public interface StorageRepository {

	void init();

	void store(MultipartFile file);

	String getPathFile(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
