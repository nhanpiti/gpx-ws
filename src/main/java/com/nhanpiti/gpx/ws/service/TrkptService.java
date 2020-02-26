package com.nhanpiti.gpx.ws.service;

import com.nhanpiti.gpx.ws.entity.TrkptEntity;
import com.nhanpiti.gpx.ws.repository.TrkptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nhanpiti
 */

@Service
public class TrkptService {

    final
    TrkptRepository repository;

    @Autowired
    public TrkptService(TrkptRepository repository) {
        this.repository = repository;
    }

    public List<TrkptEntity> getLatestTrack(){
        List<TrkptEntity> latestTrack = repository.getLatestTrack();
        return (latestTrack.size() > 0) ? latestTrack : new ArrayList<>();
    }

}
