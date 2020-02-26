package com.nhanpiti.gpx.ws.controller;

import com.nhanpiti.gpx.ws.entity.TrkptEntity;
import com.nhanpiti.gpx.ws.service.TrkptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nhanpiti
 */

@RestController
@RequestMapping("/trkpt")
public class TrkptController {

    final TrkptService service;

    @Autowired
    public TrkptController(TrkptService trkptService) {
        this.service = trkptService;
    }

    @GetMapping("/latestTrack")
    public ResponseEntity<List<TrkptEntity>> getLatestTrack() {
        List<TrkptEntity> trkptList = service.getLatestTrack();
        return new ResponseEntity<>(trkptList, new HttpHeaders(), HttpStatus.OK);
    }
}
