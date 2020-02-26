package com.nhanpiti.gpx.ws.repository;

import com.nhanpiti.gpx.ws.entity.GpxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhanpiti
 */

@Repository
public interface GpxRepository extends JpaRepository<GpxEntity, Long> {

}