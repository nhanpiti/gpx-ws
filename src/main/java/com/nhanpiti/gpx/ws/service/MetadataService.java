package com.nhanpiti.gpx.ws.service;

import com.nhanpiti.gpx.ws.entity.MetadataEntity;
import com.nhanpiti.gpx.ws.exception.RecordNotFoundException;
import com.nhanpiti.gpx.ws.repository.MetadataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MetadataService {

    final MetadataRepository repository;

    public MetadataService(MetadataRepository repository) {
        this.repository = repository;
    }

    public List<MetadataEntity> getAllMetadatas() {
        List<MetadataEntity> metadataList = repository.findAll();

        return (metadataList.size() > 0) ? metadataList : new ArrayList<>();

    }

    public MetadataEntity createOrUpdateMetadata(MetadataEntity entity) throws RecordNotFoundException {
        Optional<MetadataEntity> metadata = repository.findById(entity.getMetadataId());

        if (metadata.isPresent()) {

            MetadataEntity newMetadata = metadata.get();
            newMetadata.setName(entity.getName());
            newMetadata.setDesc(entity.getDesc());
            newMetadata.setAuthor(entity.getAuthor());
            newMetadata.setLink(entity.getLink());
            newMetadata.setText(entity.getText());
            newMetadata.setTime(entity.getTime());
            newMetadata.setGpx(entity.getGpx());

            return repository.save(newMetadata);
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }
}
