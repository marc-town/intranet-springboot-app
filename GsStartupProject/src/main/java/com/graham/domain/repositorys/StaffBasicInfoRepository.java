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
	
	// 社員IDで検索用クエリ
	final String FIND_ONE = "SELECT\n" + 
			"  sbi.staff_basic_info_id AS staff_basic_info_id,\n" + 
			"  sbi.staff_id AS staff_id,\n" + 
			"  sbi.name AS name,\n" + 
			"  sbi.name_kana AS name_kana,\n" + 
			"  sbi.entered_date AS entered_date,\n" + 
			"  sbi.birthday AS birthday,\n" + 
			"  sbi.telephone_number AS telephone_number,\n" + 
			"  d.department_name AS department,\n" + 
			"  p.position_name AS position,\n" + 
			"  g.grade_name AS grade,\n" + 
			"  r.role_name AS role\n" + 
			"FROM\n" + 
			"  m_staff_basic_info sbi\n" + 
			"  INNER JOIN c_staff_role sr ON sbi.staff_id = sr.staff_id\n" + 
			"  INNER JOIN m_role r ON sr.role_id = r.role_id\n" + 
			"  LEFT OUTER JOIN m_department d ON sbi.department_id = d.department_id\n" + 
			"  LEFT OUTER JOIN m_position p ON sbi.position_id = p.position_id\n" + 
			"  LEFT OUTER JOIN m_grade g ON sbi.grade_id = g.grade_id\n" + 
			"WHERE sbi.staff_id = ?1";
	
	// 基本情報更新用クエリー
	final String UPDATE = "update m_staff_basic_info set "
			+ "name = ?1, "
			+ "name_kana = ?2, "
			+ "entered_date = ?3, "
			+ "birthday = ?4, "
			+ "telephone_number = ?5, "
			+ "department_id = ?6, "
			+ "position_id = ?7, "
			+ "grade_id = ?8 "
			+ "where staff_id = ?9";

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
	@Query(value = FIND_ONE, nativeQuery = true)
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
