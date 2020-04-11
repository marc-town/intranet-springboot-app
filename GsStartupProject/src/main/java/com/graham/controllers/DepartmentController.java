package com.graham.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.graham.interfaces.response.DepartmentResponseForm;
import com.graham.services.DepartmentService;

/**
 * 部署管理用コントローラ
 */
@RestController
@RequestMapping(value = "/api/v1/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	/**
	 * 部署一覧取得
	 * 
	 * @return departments 部署一覧
	 */
	@GetMapping
	@ResponseBody
	public DepartmentResponseForm index() {
		LOGGER.info("BEGIN get departments list");
		DepartmentResponseForm departments = departmentService.findAll();
		LOGGER.info("FINISH get departments list {}", departments);
		return departments;
	}
}
