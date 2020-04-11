package com.graham.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.graham.interfaces.response.PositionResponseForm;
import com.graham.services.PositionService;

/**
 * 役職管理用コントローラ
 */
@RestController
@RequestMapping(value = "/api/v1/positions")
public class PositionController {

	@Autowired
	private PositionService positionService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionController.class);
	
	/**
	 * 役職一覧取得
	 * 
	 * @return positions 役職一覧
	 */
	@GetMapping
	@ResponseBody
	public PositionResponseForm index() {
		LOGGER.info("BEGIN get positions list");
		PositionResponseForm positions = positionService.findAll();
		LOGGER.info("FINISH get positions list {}", positions);
		return positions;
	}
}
