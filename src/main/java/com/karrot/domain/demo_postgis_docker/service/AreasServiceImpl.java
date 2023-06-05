package com.karrot.domain.demo_postgis_docker.service;

import com.karrot.domain.demo_postgis_docker.Areas;
import com.karrot.domain.demo_postgis_docker.repository.AreasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AreasServiceImpl implements AreasService {

    @Autowired
    private AreasRepository areasRepository;

    @Override
    public Areas getAreasById(UUID id) {
        return areasRepository.findById(id).orElse(null);
    }

    @Override
    public Areas save(Areas area) {
        return areasRepository.save(area);
    }

    @Override
    public List<Areas> getAllAreas() {
        return areasRepository.findAll();
    }
}
