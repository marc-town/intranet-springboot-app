package com.graham.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.graham.interfaces.response.RoleResponseForm;
import com.graham.services.RoleService;
import com.graham.services.StaffService;

/**
 * 権限管理用コントローラ
 */
@RestController
@RequestMapping(value = "/api/v1/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	/**
	 * 権限一覧取得（管理者専用）
	 * 
	 * @return roles 権限一覧
	 */
	@GetMapping
	@ResponseBody
	public RoleResponseForm index() {
		LOGGER.info("BEGIN get role list");
		RoleResponseForm roles = roleService.findAll();
		LOGGER.info("FINISH get role list");
		return roles;
	}
}
