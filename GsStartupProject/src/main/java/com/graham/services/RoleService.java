package com.graham.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.RoleEntity;
import com.graham.domain.repositorys.RoleRepository;
import com.graham.interfaces.response.RoleResponseForm;

/**
 * 権限の管理を行うサービスクラス
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
	
	/**
	 * 権限一覧取得
	 * 
	 * @return roles 取得したDB情報
	 */
	public RoleResponseForm findAll() {
		LOGGER.info("BEGIN RoleService findAll	");
		List<RoleEntity> entity = roleRepository.findAll();
		return new RoleResponseForm(entity);
	}
}
