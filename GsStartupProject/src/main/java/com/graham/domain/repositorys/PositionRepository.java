package com.graham.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graham.domain.model.PositionEntity;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
}
