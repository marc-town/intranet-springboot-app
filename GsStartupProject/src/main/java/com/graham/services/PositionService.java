package com.graham.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.PositionEntity;
import com.graham.domain.repositorys.PositionRepository;
import com.graham.interfaces.response.PositionResponseForm;

/**
 * 役職の管理を行うサービスクラス
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class PositionService {

	@Autowired
	private PositionRepository positionRepository;
	@Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionService.class);
	
	/**
	 * 役職一覧取得
	 * 
	 * @return positions 取得したDB情報
	 */
	public PositionResponseForm findAll() {
		LOGGER.info("BEGIN PositionService findAll");
		List<PositionEntity> positions = positionRepository.findAll();
		return new PositionResponseForm(positions);
	}
}
