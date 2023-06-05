package com.karrot.domain.demo_postgis_docker.repository;

import com.karrot.domain.demo_postgis_docker.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface GoodsRepository extends JpaRepository<Goods, UUID> {

    public List<Goods> findAll();

}