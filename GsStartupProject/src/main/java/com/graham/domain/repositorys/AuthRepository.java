package com.graham.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.StaffEntity;

/**
 * ログイン情報
 *
 */
@Repository
@Transactional //途中でメソッドが異常終了した時に処理を中断して前の状態に戻す
public interface AuthRepository extends JpaRepository<StaffEntity, Integer> {
	
	/**
	 * ログインID変更
	 * 
	 * @param loginId
	 * @param staffId
	 * @return
	 */
	@Modifying
	@Query("update m_staff set login_id = :loginId where staff_id = :staffId")
	public int updateLoginId(@Param("loginId")String loginId, @Param("staffId")int staffId);
	
	/**
	 * パスワード変更
	 * 
	 * @param password
	 * @param staffId
	 * @return
	 */
	@Modifying
	@Query("update m_staff set password = :password where staff_id = :staffId")
	public int updatePassword(@Param("password")String passord, @Param("staffId")int staffId);

}
