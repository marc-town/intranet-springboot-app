package com.graham.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graham.domain.model.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
