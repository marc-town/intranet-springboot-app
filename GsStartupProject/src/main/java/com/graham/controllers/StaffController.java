package com.graham.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.graham.interfaces.request.SignupRequestForm;
import com.graham.interfaces.request.StaffBasicInfoRequestForm;
import com.graham.interfaces.response.StaffBasicInfoResponseForm;
import com.graham.interfaces.response.StaffResponseForm;
import com.graham.services.StaffService;

/**
 * 社員アカウント用コントローラ
 * 
 */
@RestController
@RequestMapping(value = "/api/v1/staffs")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffController.class);
	
	/**
	 * 社員一覧取得（管理者専用）
	 * 
	 * @return staffs 社員一覧
	 */
	@GetMapping
	@ResponseBody
	@PreAuthorize("hasRole('MIDDLE') or hasRole('ADMIN')")
	public StaffResponseForm index() {
		LOGGER.info("START get staff list");
		StaffResponseForm staffs = staffService.index();
		LOGGER.info("SUCCESS get staff list");
		return staffs;
	}

	/**
	 * 社員登録（管理者専用）
	 * 
	 * @param request 社員の登録情報
	 */
	@PostMapping
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public void createStaff(@Valid @RequestBody SignupRequestForm request) {
		LOGGER.info("START regist staff with inputs {}", request);
		staffService.regist(request);
		LOGGER.info("SUCCESS regist staff");
	}
	
	/**
	 * 社員削除（管理者専用）
	 * 
	 * @param id 退職社員ID
	 */
	@DeleteMapping("/{staffId}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteStaff(@PathVariable("staffId") int staffId) {
		LOGGER.info("START delete staff with staffId = {}", staffId);
		staffService.delete(staffId);
		LOGGER.info("SUCCESS delete staff with staffId = {}", staffId);
	}
	
	/**
	 * 社員基本情報 取得
	 * 
	 * @param staffId 社員ID
	 * @return 社員詳細情報
	 */
	@ResponseBody
	@GetMapping("/{staffId}/basic_info")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public StaffBasicInfoResponseForm findStaffBasicInfo(
			@PathVariable("staffId") int staffId) {
		StaffBasicInfoResponseForm basicInfo = staffService.findStaffBasicInfo(staffId);
		return basicInfo;
	}

	/**
	 * 社員基本情報 更新
	 * 
	 * @param staffId 社員ID
	 * @param staffDetailRequestForm 社員詳細情報フォーム
	 */
	@ResponseBody
	@PutMapping("/{staffId}/basic_info")
	@PreAuthorize("hasRole('ADMIN')")
	public void updateStaffDetailInfo(
			@PathVariable("staffId") int staffId,
			@RequestBody StaffBasicInfoRequestForm request) {
		
		LOGGER.info("START update staff_basic_info with staff_id = {} and request {}", staffId, request);
		staffService.updateStaffBasicInfo(staffId, request);
		LOGGER.info("SUCCESS update staff_basic_info");
	}
}
