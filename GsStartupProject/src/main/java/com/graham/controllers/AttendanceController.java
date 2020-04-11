package com.graham.controllers;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.graham.common.GrahamHttpStatus;
import com.graham.exception.GrahamError;
import com.graham.exception.GrahamException;
import com.graham.interfaces.request.AttendanceRequestForm;
import com.graham.interfaces.request.AttendanceSubmitRequestForm;
import com.graham.interfaces.response.AttendanceResponseForm;
import com.graham.services.AttendanceService;
import com.graham.services.StaffService;

/**
 * 勤怠情報管理コントローラ
 * 
 */
@RestController
@RequestMapping(value = "/api/v1/attendances")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private StaffService staffService;
	@Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER=LoggerFactory.getLogger(AttendanceController.class);
	
	/**
	 * 勤怠情報一覧取得（社員の対象月1ヶ月分）
	 * 
	 * @param authentication 認証情報
	 * @param yearMonth 対象年月
	 * @return attendances 勤怠情報
	 */
	@GetMapping("/{staffId}")
	@ResponseBody
	public AttendanceResponseForm index(
			Authentication authentication,
			@PathVariable("staffId") int staffId,
			@RequestParam("yearMonth") String yearMonth) {
		
		LOGGER.info("BEGIN AttendanceController index");
		// 別社員の情報だった場合
		if (!staffService.isCorrectStaff(authentication.getName(), staffId)) {
			String message = messageSource.getMessage(
					"error.E_GSOL0006", new String[]{authentication.getName()}, Locale.JAPANESE);
			LOGGER.error(message);
			GrahamError err = new GrahamError(GrahamHttpStatus.NOT_FOUND, "GSOL0001", message);
			throw new GrahamException(err);
		}
		AttendanceResponseForm attendances = attendanceService.attendanceIndex(staffId, yearMonth);
		LOGGER.info("SUCCESS get attendance list");
		return attendances;
	}
	
	/**
	 * 勤怠情報提出（当月分の勤怠情報をファイル出力する）
	 * 
	 * @param staffId
	 * @param yearMonth
	 * @param requests
	 */
	@PostMapping("/{staffId}/submit")
	@ResponseBody
	public void submit(
			@PathVariable("staffId") int staffId,
			@RequestParam("yearMonth") String yearMonth,
			@RequestBody AttendanceSubmitRequestForm request) {
		
		LOGGER.info("BEGIN AttendanceController submit staffId: {}, yearMonth: {}, request: {}", staffId, yearMonth, request);
		attendanceService.exportAttendance(staffId, yearMonth, request);
		LOGGER.info("SUCCESS update attendance");
	}
	
	@PutMapping("/{staffId}")
	@ResponseBody
	public void update(
			@PathVariable("staffId") int staffId,
			@RequestParam("yearMonth") String yearMonth,
			@RequestBody List<AttendanceRequestForm> requests) {
		
		LOGGER.info("BEGIN AttendanceController update");
		attendanceService.updateAttendance(staffId, yearMonth, requests);
		LOGGER.info("SUCCESS update attendance");
	}
}
