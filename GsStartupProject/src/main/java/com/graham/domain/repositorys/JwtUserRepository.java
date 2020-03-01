package com.graham.domain.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	
	// ログインIDから社員ID取得クエリ
	final String FIND_STAFFID_BY_LOGIN_ID = "SELECT staff_id FROM m_staff WHERE login_id = ?1";

	public Optional<JwtStaffEntity> findByEmail(String email);

	public Optional<JwtStaffEntity> findByUsernameOrEmail(String username, String email);

	public List<JwtStaffEntity> findByStaffIdIn(List<Long> userIds);
    
	public Optional<JwtStaffEntity> findByUsername(String username);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);
	
	/**
	 * ログインIDから社員IDを取得
	 * 
	 * @param loginId ログインID
	 * @return staffId 社員ID
	 */
	@Query(value = FIND_STAFFID_BY_LOGIN_ID, nativeQuery = true)
	public int findStaffIdByLoginId(String loginId);
	
	/**
	 * 社員削除
	 * この処理は社員削除の際に呼び出される。
	 * 
	 * @param staffId 退職社員ID
	 */
	public void deleteByStaffId(int staffId);
}
