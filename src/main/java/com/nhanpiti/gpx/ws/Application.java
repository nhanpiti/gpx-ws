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

//    @Bean
//    public CommandLineRunner commandLineRunner(GpxRepository gpxRepository) {
//        return args -> {
//
//            long currentTime = System.currentTimeMillis();
//
//            MetadataEntity metadataEntity = new MetadataEntity();
//            metadataEntity.setTime(new Date());
//            metadataEntity.setText("My Text");
//            metadataEntity.setLink("http://google.com");
//            metadataEntity.setAuthor("My Author");
//            metadataEntity.setDesc("My Desc");
//            metadataEntity.setName("My Name");
//
//
//            GpxEntity gpxEntity = new GpxEntity();
//            gpxEntity.setUserId(123456);
//            gpxEntity.setGpxMetadata(metadataEntity);
//            gpxEntity.setCreatedTime(currentTime);
//            metadataEntity.setGpx(gpxEntity);
//
//
//            TrkptEntity trkptEntity1 = new TrkptEntity();
//            trkptEntity1.setLat(42.2208895d);
//            trkptEntity1.setLon(-1.4580696d);
//            trkptEntity1.setEle(315.86d);
//            trkptEntity1.setTime(new Date());
//            trkptEntity1.setGpx(gpxEntity);
//            trkptEntity1.setCreatedTime(currentTime);
//
//            TrkptEntity trkptEntity2 = new TrkptEntity();
//            trkptEntity2.setLat(42.2208895d);
//            trkptEntity2.setLon(-1.4580696d);
//            trkptEntity2.setEle(315.86d);
//            trkptEntity2.setTime(new Date());
//            trkptEntity2.setGpx(gpxEntity);
//            trkptEntity2.setCreatedTime(currentTime);
//
//            gpxEntity.setTrkpts(Arrays.asList(trkptEntity1, trkptEntity2));
//
//            WptEntity wptEntity1 = new WptEntity();
//            wptEntity1.setLat(42.2205377d);
//            wptEntity1.setLon(-1.4564538d);
//            wptEntity1.setName("Sorteamos por arriba");
//            wptEntity1.setSym("/static/wpt/Waypoint");
//            wptEntity1.setGpx(gpxEntity);
//            wptEntity1.setCreatedTime(currentTime);
//
//            WptEntity wptEntity2 = new WptEntity();
//            wptEntity2.setLat(42.2205377d);
//            wptEntity2.setLon(-1.4564538d);
//            wptEntity2.setName("Sorteamos por arriba");
//            wptEntity2.setSym("/static/wpt/Waypoint");
//            wptEntity2.setGpx(gpxEntity);
//            wptEntity2.setCreatedTime(currentTime);
//
//            gpxEntity.setWpts(Arrays.asList(wptEntity1, wptEntity2));
//
//            gpxRepository.save(gpxEntity);
//
//        };
//    }
}
