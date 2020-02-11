package com.graham.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graham.domain.model.StaffEntity;

@Repository
public interface StaffRepository extends
			JpaRepository<StaffEntity, Integer> {
	
	/**
	 * 社員情報取得
	 * 
	 * @param staffId 社員ID
	 * @return staff 社員情報
	 */
	StaffEntity findByStaffId(int staffId);
	
	/**
	 * 社員削除
	 * この処理は社員削除の際に呼び出される。
	 * 
	 * @param staffId 退職社員ID
	 */
	public void deleteByStaffId(int staffId);
}
