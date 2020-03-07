package com.graham.domain.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.StaffEntity;

@Repository
@Transactional //途中でメソッドが異常終了した時に処理を中断して前の状態に戻す
public interface StaffRepository extends
			JpaRepository<StaffEntity, Integer> {
	
	// 社員一覧取得用クエリ
	final String FIND_ALL = "SELECT\n" + 
			"  sbi.staff_id AS staff_id,\n" + 
			"  sbi.name AS name,\n" + 
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
			"  LEFT OUTER JOIN m_grade g ON sbi.grade_id = g.grade_id";
	
	@Query(value = FIND_ALL, nativeQuery = true)
	public List<StaffEntity> findAllStaff();
	
}
