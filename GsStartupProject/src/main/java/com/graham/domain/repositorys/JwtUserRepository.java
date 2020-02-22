package com.graham.domain.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.JwtStaffEntity;

/**
 * ログイン情報
 *
 */
@Repository
@Transactional //途中でメソッドが異常終了した時に処理を中断して前の状態に戻す
public interface JwtUserRepository extends JpaRepository<JwtStaffEntity, Integer> {

	public Optional<JwtStaffEntity> findByEmail(String email);

	public Optional<JwtStaffEntity> findByUsernameOrEmail(String username, String email);

	public List<JwtStaffEntity> findByStaffIdIn(List<Long> userIds);
    
	public Optional<JwtStaffEntity> findByUsername(String username);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);
}
