package com.graham.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.StaffBasicInfoEntity;

/**
 * 社員詳細情報 に関するレポジトリ
 * 
 */
@Repository
@Transactional //途中でメソッドが異常終了した時に処理を中断して前の状態に戻す
public interface StaffBasicInfoRepository extends JpaRepository<StaffBasicInfoEntity, Integer> {
	
	// 基本情報更新用クエリー
	final String UPDATE = "update m_staff_basic_info set "
			+ "name = ?1, "
			+ "name_kana = ?2, "
			+ "entered_date = ?3, "
			+ "staff_type_id = ?4, "
			+ "birthday = ?5, "
			+ "telephone_number = ?6, "
			+ "department_id = ?7, "
			+ "position_id = ?8, "
			+ "grade_id = ?9 "
			+ "where staff_id = ?10";

	// 基本情報登録用クエリー
	final String INSERT = "INSERT INTO\n" + 
			"  m_staff_basic_info (\n" + 
			"    staff_id\n" + 
			"    , name\n" + 
			"    , name_kana\n" + 
			"    , telephone_number\n" + 
			"  )\n" + 
			"VALUES\n" + 
			"  (?1, ?2, ?3, ?4)";
	
	/**
	 * 社員基本情報 検索
	 * 
	 * @param staffId 社員ID
	 * @return staff 社員基本情報
	 */
	public StaffBasicInfoEntity findByStaffId(Integer staffId);
	
	/**
	 * 社員基本情報 登録
	 * 
	 * @param staffId 社員ID
	 * @param name 名前
	 * @param nameKana なまえ
	 * @param tel 電話番号
	 */
	@Modifying
	@Query(value = INSERT, nativeQuery = true)
	public void insertBasicInfo(Integer staffId, String name, String nameKana, String tel);
	
	/**
	 * 社員基本情報 更新
	 * 
	 * @param nameKana 名前
	 * @param enteredDate なまえ
	 * @param staffTypeId 社員種別
	 * @param birthday 誕生日
	 * @param telephoneNumber 電話番号
	 * @param departmentId 部署ID
	 * @param positionId 役職ID
	 * @param gradeId 階級ID
	 * @param staffId 社員ID(検索条件)
	 * @return count 更新件数(社員IDに紐づく1件しか更新されない)
	 */
	@Modifying
	@Query(value = UPDATE, nativeQuery = true)
	public int updateBasicInfo(
			String name,
			String nameKana,
			String enteredDate,
			Integer staffTypeId,
			String birthday,
			String telephoneNumber,
			Integer departmentId,
			Integer positionId,
			Integer gradeId,
			Integer staffId);

	/**
	 * 社員削除
	 * この処理は社員削除の際に呼び出される。
	 * 
	 * @param staffId 退職社員ID
	 */
	public void deleteByStaffId(int staffId);

}
