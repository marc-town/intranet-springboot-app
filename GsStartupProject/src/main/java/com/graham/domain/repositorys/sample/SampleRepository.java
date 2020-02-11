package com.graham.domain.repositorys.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graham.domain.model.sample.SampleEntity;

@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Integer> {

}
