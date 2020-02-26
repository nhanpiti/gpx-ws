package com.nhanpiti.gpx.ws;


import com.nhanpiti.gpx.ws.entity.GpxEntity;
import com.nhanpiti.gpx.ws.entity.MetadataEntity;
import com.nhanpiti.gpx.ws.entity.TrkptEntity;
import com.nhanpiti.gpx.ws.entity.WptEntity;
import com.nhanpiti.gpx.ws.properties.StorageProperties;
import com.nhanpiti.gpx.ws.repository.GpxRepository;
import com.nhanpiti.gpx.ws.service.FileSystemStorageService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

/**
 * @author nhanpiti
 */

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(FileSystemStorageService fileSystemStorageService) {
        return (args) -> {
            fileSystemStorageService.deleteAll();
            fileSystemStorageService.init();
        };
    }
}
