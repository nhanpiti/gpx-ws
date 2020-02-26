package com.nhanpiti.gpx.ws.service;

import com.nhanpiti.gpx.ws.entity.GpxEntity;
import com.nhanpiti.gpx.ws.repository.GpxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GpxService {

    final GpxRepository repository;

    @Autowired
    public GpxService(GpxRepository repository) {
        this.repository = repository;
    }

    public List<GpxEntity> getAllGpxs() {
        List<GpxEntity> gpxList = repository.findAll();
        return (gpxList.size() > 0) ? gpxList : new ArrayList<>();
    }

    public Optional<GpxEntity> findById(long id) {
        return repository.findById(id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

}
