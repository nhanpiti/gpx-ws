package com.nhanpiti.gpx.ws;

import com.nhanpiti.gpx.ws.entity.GpxEntity;
import com.nhanpiti.gpx.ws.entity.MetadataEntity;
import com.nhanpiti.gpx.ws.entity.TrkptEntity;
import com.nhanpiti.gpx.ws.entity.WptEntity;
import com.nhanpiti.gpx.ws.repository.GpxRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;

/**
 * @author nhanpiti
 */

@AutoConfigureMockMvc
@SpringBootTest
public class LatestTrackingPointIT {

    @Autowired
    private GpxRepository gpxRepository;

    @Autowired
    private MockMvc mvc;

    private GpxEntity createGpxEntity(){
        long currentTime = System.currentTimeMillis();

        GpxEntity gpxEntity = new GpxEntity();
        gpxEntity.setUserId(0);
        gpxEntity.setCreatedTime(currentTime);

        MetadataEntity metadataEntity = new MetadataEntity();
        metadataEntity.setTime(new Date());
        metadataEntity.setText("My Text");
        metadataEntity.setLink("http://google.com");
        metadataEntity.setAuthor("My Author");
        metadataEntity.setDesc("My Desc");
        metadataEntity.setName("My Name");
        metadataEntity.setGpx(gpxEntity);

        gpxEntity.setGpxMetadata(metadataEntity);


        TrkptEntity trkptEntity1 = new TrkptEntity();
        trkptEntity1.setLat(42.2208895d);
        trkptEntity1.setLon(-1.4580696d);
        trkptEntity1.setEle(315.86d);
        trkptEntity1.setTime(new Date());
        trkptEntity1.setGpx(gpxEntity);
        trkptEntity1.setCreatedTime(currentTime);

        TrkptEntity trkptEntity2 = new TrkptEntity();
        trkptEntity2.setLat(42.2208895d);
        trkptEntity2.setLon(-1.4580696d);
        trkptEntity2.setEle(315.86d);
        trkptEntity2.setTime(new Date());
        trkptEntity2.setGpx(gpxEntity);
        trkptEntity2.setCreatedTime(currentTime);

        gpxEntity.setTrkpts(Arrays.asList(trkptEntity1, trkptEntity2));

        WptEntity wptEntity1 = new WptEntity();
        wptEntity1.setLat(42.2205377d);
        wptEntity1.setLon(-1.4564538d);
        wptEntity1.setName("Sorteamos por arriba");
        wptEntity1.setSym("/static/wpt/Waypoint");
        wptEntity1.setGpx(gpxEntity);
        wptEntity1.setCreatedTime(currentTime);

        WptEntity wptEntity2 = new WptEntity();
        wptEntity2.setLat(42.2205377d);
        wptEntity2.setLon(-1.4564538d);
        wptEntity2.setName("Sorteamos por arriba");
        wptEntity2.setSym("/static/wpt/Waypoint");
        wptEntity2.setGpx(gpxEntity);
        wptEntity2.setCreatedTime(currentTime);

        gpxEntity.setWpts(Arrays.asList(wptEntity1, wptEntity2));

        return gpxEntity;
    }

    @Test
    public void whenFindLatestTrackingPoint_thenReturnListingTrackingPointOf2ndGpx() throws Exception {

        gpxRepository.save(createGpxEntity());
        GpxEntity last = gpxRepository.save(createGpxEntity());

        mvc.perform(MockMvcRequestBuilders.get("/trkpt/latestTrack").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trkptId", Is.is((int)last.getTrkpts().get(0).getTrkptId())));
    }
}
