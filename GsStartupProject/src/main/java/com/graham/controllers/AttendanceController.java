package com.graham.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.graham.interfaces.request.AttendanceRequestForm;
import com.graham.interfaces.response.AttendanceResponseForm;
import com.graham.services.AttendanceService;

/**
 * 勤怠情報管理コントローラ
 * 
 */
@RestController
@RequestMapping(value = "/attendances")
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(AttendanceController.class);
	
	/**
	 * 勤怠情報一覧取得（社員の対象月1ヶ月分）
	 * 
	 * @param staffId
	 * @param yearMonth
	 * @return attendances 勤怠情報
	 */
	@GetMapping
	@ResponseBody
	public AttendanceResponseForm index(
			@RequestParam("staffId") Integer staffId,
			@RequestParam("yearMonth") String yearMonth) {
		LOGGER.info("START AttendanceController index");
		AttendanceResponseForm attendances = attendanceService.attendanceIndex(staffId, yearMonth);
		LOGGER.info("SUCCESS get attendance list");
		return attendances;
	}
	
	@PutMapping
	@ResponseBody
	public void update(
			@RequestParam("staffId") Integer staffId,
			@RequestParam("yearMonth") String yearMonth,
			@RequestBody List<AttendanceRequestForm> requests) {
		
		LOGGER.info("CALLED AttendanceController update");
		attendanceService.updateAttendance(staffId, yearMonth, requests);
		LOGGER.info("SUCCESS update attendance");
	}
}
