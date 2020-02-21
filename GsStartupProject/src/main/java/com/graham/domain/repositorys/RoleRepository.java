package com.graham.domain.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graham.common.RoleName;
import com.graham.domain.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByName(RoleName name);
}
