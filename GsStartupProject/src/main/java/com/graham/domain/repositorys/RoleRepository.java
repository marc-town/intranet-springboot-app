package com.graham.domain.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.graham.common.RoleName;
import com.graham.domain.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	
	// 権限紐付け更新用クエリー
	final String UPDATE_STAFF_ROLE = "UPDATE c_staff_role SET role_id = ?2 WHERE staff_id = ?1";

	/**
	 * 社員と権限の紐付け更新
	 * 
	 * @param staffId 社員ID
	 * @param roleId 権限ID
	 */
	@Modifying
	@Query(value = UPDATE_STAFF_ROLE, nativeQuery = true)
	public int updateStaffRole(Integer staffId, Integer roleId);

	Optional<RoleEntity> findByName(RoleName name);
}
