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
			// 既に勤怠情報が登録されているか確認する
			Integer existsAttendances = attendanceRepository.existsByStaffIdAndYearMonthDay(staffId, yearMonth, day);
			
			// 登録されていなかった場合（月初）
			if (existsAttendances == 0) {
				LOGGER.info("Iterate AttendanceService.attendanceIndex insert new record");
				attendanceRepository.insertAttendance(yearMonth, day, staffId, startTime, endTime, restTime, absenceTypeId, absenceReason, workingTime, nightTime, operatingExpenses, section, remarks);
			// 登録済みだった場合
			} else {
				LOGGER.info("Iterate AttendanceService.attendanceIndex update record");
				attendanceRepository.updateAttendance(staffId, yearMonth, day, startTime, endTime, restTime, absenceTypeId, absenceReason, workingTime, nightTime, operatingExpenses, section, remarks);				
			}
		}
	}
}
