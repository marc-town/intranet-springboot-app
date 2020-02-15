package com.graham.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.StaffEntity;
import com.graham.domain.repositorys.StaffBasicInfoRepository;
import com.graham.domain.repositorys.StaffDetailInfoRepository;
import com.graham.domain.repositorys.StaffRepository;
import com.graham.interfaces.request.StaffBasicInfoRequestForm;
import com.graham.interfaces.request.StaffRequestForm;
import com.graham.interfaces.response.StaffBasicInfoResponseForm;
import com.graham.interfaces.response.StaffResponseForm;

@Service
@Transactional(rollbackFor = Throwable.class)
public class StaffService {

	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private StaffBasicInfoRepository staffBasicInfoRepository;
	@Autowired
	private StaffDetailInfoRepository staffDetailInfoRepository;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(StaffService.class);
	
	/**
	 * 社員一覧取得
	 * 
	 * @return 取得したDB情報
	 */
	public StaffResponseForm index() {
		StaffResponseForm response = new StaffResponseForm();
		response.setStaffs(staffRepository.findAll());
		return response;
	}
	
	/**
	 * 社員情報取得
	 * 
	 * @param staffId 社員ID
	 * @return
	 */
	public StaffResponseForm show(int staffId) {
		StaffResponseForm response = new StaffResponseForm();
		List<StaffEntity> staff = new ArrayList<StaffEntity>(){
			{add(staffRepository.findByStaffId(staffId));}
		};
		response.setStaffs(staff);
		return response;
	}

	/**
	 * 社員登録
	 * 
	 * @param staff 登録社員情報
	 */
	public void create(StaffRequestForm request) {
		
		// m_staff レコード作成
		StaffEntity query = setStaffQuery(request);
		// TODO loginId password生成処理
		query = staffRepository.save(query);
		
		int staffId = query.getStaffId();
		
		// m_staff_basic_info レコード作成
		LOGGER.info("insert m_staff_basic_info as staff id = {}", staffId);
		staffBasicInfoRepository.insertBasicInfo(staffId);
		
		// m_staff_detail_info レコード作成
		LOGGER.info("insert m_staff_detail_info as staff id = {}", staffId);
		staffDetailInfoRepository.insertDetailInfo(staffId);
	}

	/**
	 * 社員情報更新
	 * 
	 * @param staffId 社員ID
	 * @param request 更新情報
	 */
	public void update(int staffId, StaffRequestForm request) {
		StaffEntity query = setStaffQuery(request);
		query.setStaffId(staffId);
		staffRepository.save(query);
	}
	
	/**
	 * 社員削除
	 * 
	 * @param staffId 退職社員ID
	 */
	public void delete(int staffId) {
		staffRepository.deleteByStaffId(staffId);
		staffBasicInfoRepository.deleteByStaffId(staffId);
		staffDetailInfoRepository.deleteByStaffId(staffId);
	}
	
	/**
	 * 社員基本情報 を取得する
	 * 
	 * @param staffId 社員ID
	 * @return basicInfo 社員基本情報
	 */
	public StaffBasicInfoResponseForm findStaffBasicInfo(int staffId) {

		// 社員詳細情報フォーム
		StaffBasicInfoResponseForm basicInfo = new StaffBasicInfoResponseForm();

		// 社員詳細情報の検索結果を取得する
		basicInfo.setBasicInfo(staffBasicInfoRepository.findByStaffId(staffId));

		return basicInfo;
	}

	/**
	 * 社員基本情報 を更新する
	 * 
	 * @param staffId  社員ID
	 * @param request 社員基本情報
	 */
	public void updateStaffBasicInfo(int staffId, StaffBasicInfoRequestForm request) {
		
		String name = request.getName();
		String nameKana = request.getNameKana();
		String enteredDate = request.getEnteredDate();
		int staffTypeId = request.getStaffTypeId();
		String birthday = request.getBirthday();
		String telephoneNumber = request.getTelephoneNumber();
		int departmentId = request.getDepartmentId();
		int positionId = request.getPositionId();
		int gradeId = request.getGradeId();

		// 社員詳細情報 を更新する
		LOGGER.info("start staffService.updateStaffBasicInfo.updateBasicInfo");
		staffBasicInfoRepository.updateBasicInfo(
				name, nameKana, enteredDate, staffTypeId, birthday,
				telephoneNumber,departmentId,positionId,gradeId,staffId);
	}

	/**
	 * リクエストデータからインサートクエリー用パラメータ生成
	 * 
	 * @param request
	 * @return query
	 */
	private StaffEntity setStaffQuery(StaffRequestForm request) {
		StaffEntity query = new StaffEntity();
		query.setMailAddress(request.getMailAddress());
		query.setLoginId(request.getLoginId());
		query.setPassword(request.getPassword());
		return query;
	}
}

