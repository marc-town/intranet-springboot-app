package com.graham.domain.repositorys;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.AttendanceEntity;

@Repository
@Transactional //途中でメソッドが異常終了した時に処理を中断して前の状態に戻す
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer>{

	// 勤怠情報取得クエリー
	final String SELECT = "SELECT\n" + 
			"  *\n" + 
			"FROM\n" + 
			"  t_attendance t1\n" + 
			"WHERE\n" + 
			"  t1.staff_id = ?1\n" + 
			"  AND t1.year_month = ?2";
	
	final String INSERT = "INSERT INTO t_attendance(\n" + 
			"  `year_month`,\n" + 
			"  `day`,\n" + 
			"  staff_id,\n" + 
			"  start_time,\n" + 
			"  end_time,\n" + 
			"  rest_time,\n" + 
			"  absence_type_id,\n" + 
			"  absence_reason,\n" + 
			"  working_time,\n" + 
			"  night_time,\n" + 
			"  operating_expenses,\n" + 
			"  section,\n" + 
			"  remarks\n" + 
			")\n" + 
			"VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13);";
	
	// 勤怠情報更新クエリー
	final String UPDATE = "UPDATE\n" + 
			"  t_attendance\n" + 
			"SET\n" + 
			"  start_time = ?4,\n" + 
			"  end_time = ?5,\n" + 
			"  rest_time = ?6,\n" + 
			"  absence_type_id = ?7,\n" + 
			"  absence_reason = ?8,\n" + 
			"  working_time = ?9,\n" + 
			"  night_time = ?10,\n" + 
			"  operating_expenses = ?11,\n" + 
			"  section = ?12,\n" + 
			"  remarks = ?13\n" + 
			"WHERE\n" + 
			"  staff_id = ?1\n" + 
			"  AND `year_month` = ?2\n" + 
			"  AND `day` = ?3";
	
	final String EXISTS = "SELECT\n" + 
			"  EXISTS (\n" + 
			"    SELECT\n" + 
			"      attendance_id\n" + 
			"    FROM\n" + 
			"      t_attendance\n" + 
			"    WHERE\n" + 
			"      staff_id = ?1\n" + 
			"      AND `year_month` = ?2\n" + 
			"      AND `day` = ?3\n" + 
			"  );";
	
	/**
	 * 1ヶ月分の勤怠情報を取得する
	 * 
	 * @param <Integer> staffId 社員ID
	 * @param <String> yearMonth 取得対象年月
	 * @return attendaceInfo 対象年月の勤怠情報
	 */
	@Query(value = SELECT, nativeQuery = true)
	public List<AttendanceEntity> findByStaffIdAndYearMonth(Integer staffId, String yearMonth);
	
	/**
	 * 指定された年月日の勤怠情報を登録する
	 * 
	 * @param <String> yearMonth 更新対象年月
	 * @param <String> day 対象日付
	 * @param <Integer> staffId 社員ID
	 * @param <String> startTime 始業時間
	 * @param <String> endTime 終業時間
	 * @param <BigDecimal> restTime 休憩時間
	 * @param <Integer> absenceTypeId 欠勤種別ID
	 * @param <String> absenceReason 欠勤理由
	 * @param <BigDecimal> workingTime 労働時間
	 * @param <BigDecimal> nightTime 夜間時間
	 * @param <Integer> operatingExpenses 営業費用
	 * @param <String> section 営業区間
	 * @param <String> remarks 備考
	 */
	@Modifying
	@Query(value = INSERT, nativeQuery = true)
	public void insertAttendance(String yearMonth, String day, Integer staffId,
			String startTime, String endTime, BigDecimal restTime, Integer absenceTypeId, String absenceReason,
			BigDecimal workingTime,BigDecimal nightTime, Integer operatingExpenses, String section, String remarks);
	
	/**
	 * 指定された年月日の勤怠情報を更新する
	 * 
	 * @param <Integer> staffId 社員ID
	 * @param <String> yearMonth 更新対象年月
	 * @param <String> day 対象日付
	 * @param <String> startTime 始業時間
	 * @param <String> endTime 終業時間
	 * @param <BigDecimal> restTime 休憩時間
	 * @param <Integer> absenceTypeId 欠勤種別ID
	 * @param <String> absenceReason 欠勤理由
	 * @param <BigDecimal> workingTime 労働時間
	 * @param <BigDecimal> nightTime 夜間時間
	 * @param <Integer> operatingExpenses 営業費用
	 * @param <String> section 営業区間
	 * @param <String> remarks 備考
	 */
	@Modifying
	@Query(value = UPDATE, nativeQuery = true)
	public void updateAttendance(Integer staffId, String yearMonth, String day,
			String startTime, String endTime, BigDecimal restTime, Integer absenceTypeId, String absenceReason,
			BigDecimal workingTime,BigDecimal nightTime, Integer operatingExpenses, String section, String remarks);
	
	/**
	 * 指定された社員の対象年月分の勤怠情報が存在するか確認する
	 * 
	 * @param staffId 社員ID
	 * @param yearMonth 対象年月
	 * @param day 対象日付
	 * @return 0or1 確認結果(0: 存在しない, 1:存在する)
	 */
	@Query(value = EXISTS, nativeQuery = true)
	public Integer existsByStaffIdAndYearMonthDay(Integer staffId, String yearMonth, String day);
}
