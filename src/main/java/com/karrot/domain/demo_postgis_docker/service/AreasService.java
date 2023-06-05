package com.karrot.domain.demo_postgis_docker.service;

import com.karrot.domain.demo_postgis_docker.Areas;

import java.util.List;
import java.util.UUID;

public interface AreasService {

    public Areas getAreasById(UUID id);

    public List<Areas> getAllAreas();

    public Areas save(Areas area);
}
