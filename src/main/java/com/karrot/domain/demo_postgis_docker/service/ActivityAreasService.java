package com.karrot.domain.demo_postgis_docker.service;

import com.karrot.domain.demo_postgis_docker.ActivityAreas;
import com.karrot.domain.demo_postgis_docker.ActivityAreasPK;

import java.util.List;

public interface ActivityAreasService {

    public ActivityAreas getActivityAreaById(ActivityAreasPK id);

    public List<ActivityAreas> getAllActivityAreas();

    public ActivityAreas save(ActivityAreas activityArea);
}
