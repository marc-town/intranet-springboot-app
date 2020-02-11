package com.graham.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graham.domain.model.StaffBasicInfoEntity;
import com.graham.domain.model.StaffDetailInfoEntity;


@Repository
public interface StaffDetailInfoRepository extends JpaRepository<StaffDetailInfoEntity, Integer> {

	/**
	 * 社員詳細情報 検索
	 * 
	 * @param staffId
	 * @return
	 */
	public StaffDetailInfoEntity findByStaffId(int staffId);
	
	/**
	 * 社員削除
	 * この処理は社員削除の際に呼び出される。
	 * 
	 * @param staffId 退職社員ID
	 */
	public void deleteByStaffId(int staffId);
}
