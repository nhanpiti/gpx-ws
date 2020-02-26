package com.nhanpiti.gpx.ws.repository;

import com.nhanpiti.gpx.ws.entity.TrkptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nhanpiti
 */

@Repository
public interface TrkptRepository extends JpaRepository<TrkptEntity, Long> {

    @Query(value = "SELECT * FROM tbl_trkpt v WHERE v.gpx = (SELECT MAX(gpx_id) FROM tbl_gpx) ORDER BY trkpt_time DESC", nativeQuery = true)
    List<TrkptEntity> getLatestTrack();
}
