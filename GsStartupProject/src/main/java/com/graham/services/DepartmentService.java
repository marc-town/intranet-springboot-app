package com.graham.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.DepartmentEntity;
import com.graham.domain.repositorys.DepartmentRepository;
import com.graham.interfaces.response.DepartmentResponseForm;

/**
 * 部署の管理を行うサービスクラス
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);
	
	/**
	 * 部署一覧取得
	 * 
	 * @return Departments 取得したDB情報
	 */
	public DepartmentResponseForm findAll() {
		LOGGER.info("BEGIN DepartmentService findAll");
		List<DepartmentEntity> entity = departmentRepository.findAll();
		return new DepartmentResponseForm(entity);
	}
}
