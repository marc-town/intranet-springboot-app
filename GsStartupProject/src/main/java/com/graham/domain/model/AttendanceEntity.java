package com.graham.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Table(name = "t_attendance")
@Data
@Entity
public class AttendanceEntity {
	
	/** 勤怠ID */
	@Id
	@Column(name = "attendance_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attendanceId;
	
	/** 年月 */
	@Column(name = "year_month", length = 6)
	@NotNull(message="対象年月は必須です。")
	private String yearMonth;

	/** 日付 */
	@Column(name = "day", length = 2)
	@NotNull(message="対象日付は必須です。")
	private String day;
	
	/** 社員ID */
	@Column(name = "staff_id")
	@NotNull(message="社員IDは必須です。")
	private Integer staffId;
	
	/** 始業時間 */
	@Column(name = "start_time", length = 5)
	private String startTime;
	
	/** 終業時間 */
	@Column(name = "end_time", length = 5)
	private String endTime;
	
	/** 休憩時間 */
	@Column(name = "rest_time")
	private BigDecimal restTime;
	
	/** 欠勤種別 */
	@Column(name = "absence_type_id")
	private Integer absenceTypeId;
	
	/** 欠勤理由 */
	@Column(name = "absence_reason")
	private String absenceReason;
	
	/** 労働時間 */
	@Column(name = "working_time")
	private BigDecimal workingTime;
	
	/** 夜間時間 */
	@Column(name = "night_time")
	private BigDecimal nightTime;
	
	/** 営業費用 */
	@Column(name = "operating_expenses")
	private Integer operatingExpenses;
	
	/** 営業区間 */
	@Column(name = "section")
	private String section;
	
	/** 備考 */
	@Column(name = "remarks")
	private String remarks;

}
