package com.graham.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graham.domain.model.GradeEntity;

public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
}
