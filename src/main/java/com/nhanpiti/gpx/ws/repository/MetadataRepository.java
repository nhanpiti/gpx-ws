package com.nhanpiti.gpx.ws.repository;

import com.nhanpiti.gpx.ws.entity.MetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhanpiti
 */

@Repository
public interface MetadataRepository extends JpaRepository<MetadataEntity, Long> {

}
