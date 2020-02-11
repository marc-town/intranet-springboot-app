package com.graham.domain.repositorys;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.graham.domain.model.StaffBasicInfoEntity;

/**
 * 社員詳細情報 に関するレポジトリ
 * 
 */
@Repository
public interface StaffBasicInfoRepository extends JpaRepository<StaffBasicInfoEntity, Integer> {

	/**
	 * 社員基本情報 検索
	 * 
	 * @param staffId
	 * @return count 更新件数（基本的に1件し更新されない）
	 */
	public StaffBasicInfoEntity findByStaffId(int staffId);
	
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
	@Query("update m_staff_basic_info set "
			+ "name = :name, "
			+ "name_kana = :nameKana, "
			+ "entered_date = :enteredDate, "
			+ "staff_type_id = :staffTypeId, "
			+ "birthday = :birthday, "
			+ "telephone_number = :telephoneNumber, "
			+ "department_id = :departmentId, "
			+ "position_id = :positionId, "
			+ "grade_id = :gradeId "
			+ "where staff_id = :staffId")
	public int updateBasicInfo(
			@Param("name")String name,
			@Param("nameKana")String nameKana,
			@Param("enteredDate")Date enteredDate,
			@Param("staffTypeId")Integer staffTypeId,
			@Param("birthday")Date birthday,
			@Param("telephoneNumber")String telephoneNumber,
			@Param("departmentId")Integer departmentId,
			@Param("positionId")Integer positionId,
			@Param("gradeId")Integer gradeId,
			@Param("staffId")Integer staffId);

	/**
	 * 社員削除
	 * この処理は社員削除の際に呼び出される。
	 * 
	 * @param staffId 退職社員ID
	 */
	public void deleteByStaffId(int staffId);

}
