package com.karrot.domain.demo_postgis_docker.repository;

import com.karrot.domain.demo_postgis_docker.ActivityAreas;
import com.karrot.domain.demo_postgis_docker.ActivityAreasPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ActivityAreasRepository extends JpaRepository<ActivityAreas, ActivityAreasPK> {
    public List<ActivityAreas> findAll();

}