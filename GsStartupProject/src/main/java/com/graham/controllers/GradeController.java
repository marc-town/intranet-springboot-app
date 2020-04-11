package com.graham.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.graham.interfaces.response.GradeResponseForm;
import com.graham.services.GradeService;

/**
 * 階級管理用コントローラ
 */
@RestController
@RequestMapping(value = "/api/v1/grades")
public class GradeController {

	@Autowired
	private GradeService gradeService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GradeController.class);
	
	/**
	 * 階級一覧取得
	 * 
	 * @return positions 階級一覧
	 */
	@GetMapping
	@ResponseBody
	public GradeResponseForm index() {
		LOGGER.info("BEGIN get positions list");
		GradeResponseForm positions = gradeService.findAll();
		LOGGER.info("FINISH get positions list {}", positions);
		return positions;
	}
}
