package com.graham.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
	
	// ログインID変更用クエリー
	final String UPDATE_LOGGIN_ID = "update m_staff set login_id = ?1, where staff_id = ?2";
	// パスワード変更用クエリー
	final String UPDATE_PASSWORD = "update m_staff set password = ?1, where staff_id = ?2";
	
	/**
	 * ログインIDから社員情報を取得
	 * 
	 * @param loginId
	 * @return staff 検索結果に該当した社員情報
	 */
	public StaffEntity findByLoginId(String loginId);
	
	/**
	 * ログインID変更
	 * 
	 * @param loginId
	 * @param staffId
	 * @return
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = UPDATE_LOGGIN_ID, nativeQuery = true)
	public int updateLoginId(String loginId, int staffId);
	
	/**
	 * パスワード変更
	 * 
	 * @param password
	 * @param staffId
	 * @return
	 */
	@Modifying(clearAutomatically = true)
	@Query(value = UPDATE_PASSWORD, nativeQuery = true)
	public int updatePassword(String passord, int staffId);

}
