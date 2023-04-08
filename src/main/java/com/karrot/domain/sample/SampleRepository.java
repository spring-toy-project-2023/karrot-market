package com.karrot.domain.sample;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<SampleVO, Long> {
    List<SampleVO> findByName(String name);
}
