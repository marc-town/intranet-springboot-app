package com.graham.interfaces.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AttendanceRequestForm {

	/** 日付 */
	private String day;
	
	/** 始業時間 */
	private String startTime;
	
	/** 終業時間 */
	private String endTime;
	
	/** 休憩時間 */
	private BigDecimal restTime;
	
	/** 欠勤種別 */
	private Integer absenceTypeId;
	
	/** 欠勤理由 */
	private String absenceReason;
	
	/** 労働時間 */
	private BigDecimal workingTime;
	
	/** 夜間時間 */
	private BigDecimal nightTime;
	
	/** 営業費用 */
	private Integer operatingExpenses;
	
	/** 営業区間 */
	private String section;
	
	/** 備考 */
	private String remarks;
	
}
