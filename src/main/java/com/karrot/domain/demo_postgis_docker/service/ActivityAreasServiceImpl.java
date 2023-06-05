package com.karrot.domain.demo_postgis_docker.service;

import com.karrot.domain.demo_postgis_docker.ActivityAreas;
import com.karrot.domain.demo_postgis_docker.ActivityAreasPK;
import com.karrot.domain.demo_postgis_docker.repository.ActivityAreasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityAreasServiceImpl implements ActivityAreasService {
    @Autowired
    private ActivityAreasRepository activityAreasRepository;

    @Override
    public ActivityAreas getActivityAreaById(ActivityAreasPK id) {
        return activityAreasRepository.findById(id).orElse(null);
    }

    @Override
    public List<ActivityAreas> getAllActivityAreas() {
        return null;
    }

    @Override
    public ActivityAreas save(ActivityAreas activityArea) {
        return null;
    }
}
