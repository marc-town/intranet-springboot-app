package com.graham.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.GradeEntity;
import com.graham.domain.repositorys.GradeRepository;
import com.graham.interfaces.response.GradeResponseForm;

/**
 * 階級の管理を行うサービスクラス
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER = LoggerFactory.getLogger(GradeService.class);
	
	/**
	 * 階級一覧取得
	 * 
	 * @return grades 取得したDB情報
	 */
	public GradeResponseForm findAll() {
		LOGGER.info("BEGIN GradeService findAll");
		List<GradeEntity> grades = gradeRepository.findAll();
		return new GradeResponseForm(grades);
	}
}
