package com.karrot.domain.demo_postgis_docker.repository;

import com.karrot.domain.demo_postgis_docker.Areas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface AreasRepository extends JpaRepository<Areas, UUID> {

    public List<Areas> findAll();

}