package com.nhanpiti.gpx.ws.controller;

import com.nhanpiti.gpx.ws.entity.MetadataEntity;
import com.nhanpiti.gpx.ws.exception.RecordNotFoundException;
import com.nhanpiti.gpx.ws.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nhanpiti
 */

@RestController
@RequestMapping("/metadata")
public class MetadataController {

    final MetadataService service;

    @Autowired
    public MetadataController(MetadataService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MetadataEntity>> getAllMetadatas() {
        List<MetadataEntity> list = service.getAllMetadatas();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MetadataEntity> createOrUpdateMetadata(MetadataEntity metadata)
            throws RecordNotFoundException {
        MetadataEntity updated = service.createOrUpdateMetadata(metadata);

        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }
}
