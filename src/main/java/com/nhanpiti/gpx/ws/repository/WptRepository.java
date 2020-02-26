package com.nhanpiti.gpx.ws.repository;

import com.nhanpiti.gpx.ws.entity.WptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhanpiti
 */

@Repository
public interface WptRepository extends JpaRepository<WptEntity, Long> {

}
