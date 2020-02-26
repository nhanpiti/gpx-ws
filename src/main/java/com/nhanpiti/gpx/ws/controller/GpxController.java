package com.nhanpiti.gpx.ws.controller;

import com.nhanpiti.gpx.ws.entity.GpxEntity;
import com.nhanpiti.gpx.ws.service.GpxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author nhanpiti
 */

@RestController
@RequestMapping("/gpx")
public class GpxController {

    private final GpxService service;

    @Autowired
    public GpxController(GpxService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GpxEntity>> getAllEmployees() {
        List<GpxEntity> gpxList = service.getAllGpxs();
        return new ResponseEntity<>(gpxList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GpxEntity> findById(@PathVariable Long id) {
        Optional<GpxEntity> gpxEntity = service.findById(id);
        if (!gpxEntity.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(gpxEntity.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        service.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
