package com.graham.interfaces.request;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 勤怠提出情報
 */
@Data
public class AttendanceSubmitRequestForm {

	/** 客先常駐時間 */
	private BigDecimal customerResidentTime;
	
	/** 総労働時間 */
	private BigDecimal totalWorkingTime;
	
	/** 総深夜勤務時間 */
	private BigDecimal totalNightTime;
	
	/** 総残業時間 */
	private BigDecimal totalOverTime;
}
