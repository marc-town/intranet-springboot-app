package com.graham.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.StaffDetailInfoEntity;


@Repository
@Transactional //途中でメソッドが異常終了した時に処理を中断して前の状態に戻す
public interface StaffDetailInfoRepository extends JpaRepository<StaffDetailInfoEntity, Integer> {

	// 基本情報登録用クエリー
	final String INSERT = "insert into m_staff_detail_info (staff_id) values (?1)";
	
	/**
	 * 社員詳細情報 検索
	 * 
	 * @param staffId
	 * @return
	 */
	public StaffDetailInfoEntity findByStaffId(int staffId);
	
	/**
	 * 社員詳細情報 登録
	 * 
	 * @param staffId 社員ID
	 */
	@Modifying
	@Query(value = INSERT, nativeQuery = true)
	public void insertDetailInfo(Integer staffId);
	
	/**
	 * 社員削除
	 * この処理は社員削除の際に呼び出される。
	 * 
	 * @param staffId 退職社員ID
	 */
	public void deleteByStaffId(int staffId);
}
