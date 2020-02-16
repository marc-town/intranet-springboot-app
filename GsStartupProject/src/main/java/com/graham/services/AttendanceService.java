package com.graham.services;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.AttendanceEntity;
import com.graham.domain.repositorys.AttendanceRepository;
import com.graham.interfaces.request.AttendanceRequestForm;
import com.graham.interfaces.response.AttendanceResponseForm;

/**
 * 勤怠情報の管理を行うサービスクラス
 *
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceService.class);
	
	/**
	 * 1ヶ月分の勤怠情報を取得する
	 * 
	 * @param <Integer> staffId 社員ID
	 * @param <String> yearMonth 対象年月
	 * 
	 * @return attendances 対象年月の勤怠情報
	 */
	public AttendanceResponseForm attendanceIndex(int staffId, String yearMonth) {
		
		LOGGER.info("called AttendanceService.attendanceIndex staffId = {}, yearMonth = {}", staffId, yearMonth);
		List<AttendanceEntity> attendaceInfo = attendanceRepository.findByStaffIdAndYearMonth(staffId, yearMonth);
		AttendanceResponseForm attendances = new AttendanceResponseForm();
		attendances.setAttendances(attendaceInfo);
		LOGGER.info("SUCCESS AttendanceService.attendanceIndex");
		return attendances;
	}
	
	/**
	 * 指定された年月日の勤怠情報を更新する
	 * 
	 * @param staffId
	 * @param yearMonth
	 * @param requests
	 */
	public void updateAttendance(int staffId, String yearMonth, List<AttendanceRequestForm> requests) {
		
		LOGGER.info("called AttendanceService.attendanceIndex staffId = {}, yearMonth = {}, requestBody = {}", staffId, yearMonth, requests);
		// 更新対象月の日数分繰り返し更新
		for (AttendanceRequestForm request: requests) {
			String day = request.getDay();
			String startTime = request.getStartTime();
			String endTime = request.getEndTime();
			BigDecimal restTime = request.getRestTime();
			Integer absenceTypeId = request.getAbsenceTypeId();
			String absenceReason = request.getAbsenceReason();
			BigDecimal workingTime = request.getWorkingTime();
			BigDecimal nightTime = request.getNightTime();
			Integer operatingExpenses = request.getOperatingExpenses();
			String section = request.getSection();
			String remarks = request.getRemarks();
			
			LOGGER.info("Iterate AttendanceService.attendanceIndex day = {}, request = {}", day, request);
			attendanceRepository.updateAttendance(staffId, yearMonth, day, startTime, endTime, restTime, absenceTypeId, absenceReason, workingTime, nightTime, operatingExpenses, section, remarks);
		}
	}
	
	/**
	 * 始業時間と終業時間から労働時間を算出する
	 * やっぱり画面で計算させる。時間入力したタイミンで労働時間が表示されたほうが自然
	 * 
	 * @param startTime 始業時間
	 * @param endTime 終業時間
	 * @param restTime 休憩時間
	 * @return workingTime 労働時間
	 */
	private BigDecimal calculateStartTimeAndEndTimeFromWorkingTime(String startTime, String endTime, double restTime) {
		
		// TODO
		LOGGER.info("called calculateStartTimeAndEndTimeFromWorkingTime startTime = {}, endTime = {}", startTime, endTime);
		
		String regex = ":";
		String[] startHourAndMinutes = startTime.split(regex);
		String[] endHourAndMinutes = endTime.split(regex);
		
		// 労働時間を分単位で求めるための前処理
		// 例) startHour = 9 * 60 = 540
		// 例) startMinutes = 0
		int startHour = Integer.parseInt(startHourAndMinutes[0]) * 60;
		int startMinutes = Integer.parseInt(startHourAndMinutes[1]);
		int endHour = Integer.parseInt(endHourAndMinutes[0]) * 60;
		int endMinutes = Integer.parseInt(endHourAndMinutes[1]);
		
		// 時間部分と分の合計値
		// 例) start = 540 + 0 = 540
		int start = startHour + startMinutes;
		int end = endHour + endMinutes;
		
		BigDecimal workingTime = new BigDecimal(0.0);
		// 始業時間が終業時間より遅い時間帯の場合（夜勤）
		// 例）22:00 - 6:00
		if (startHour > endHour) {
			// 終業時間を深夜時間に調整
			// 例) 6:00 -> 30:00
			end += 24 * 60;
		}
		// 終業時間 - 始業時間 - 休憩時間 (/60で分から時間単位に戻している)
		// 例) 30:00 - 22:00 - 1 = 8h
		double tmp = (end - start) / 60 - restTime;
		workingTime = BigDecimal.valueOf(tmp);
		
		return workingTime;
	}
	
	private BigDecimal calculateStartTimeAndEndTimeFromNightTime() {
		return null;
	}
}
