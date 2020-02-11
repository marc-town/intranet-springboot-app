package com.graham.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graham.domain.model.StaffBasicInfoEntity;
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
		System.out.println(query);
		
		// m_staff_basic_info レコード作成
//		StaffBasicInfoEntity detailQuery = new StaffBasicInfoEntity();
//		detailQuery.setStaffId(query.getStaffId());
//		staffDetailRepository.save(detailQuery);
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
	 * @param sessionStaffId セッションスタッフID
	 * @return 社員詳細情報フォーム
	 */
	public StaffBasicInfoResponseForm findStaffBasicInfo(int staffId) {

		// 社員詳細情報フォーム
		StaffBasicInfoResponseForm response = new StaffBasicInfoResponseForm();

		// 社員詳細情報の検索結果を取得する
		response.setBasicInfo(staffBasicInfoRepository.findByStaffId(staffId));

		return response;
	}

	/**
	 * 社員基本情報 を更新する
	 * 
	 * @param staffId  社員ID
	 * @param staffDetailForm 社員基本情報
	 */
	public void updateStaffBasicInfo(int staffId, StaffBasicInfoRequestForm request) {
		
		// 名前
		String name = request.getName();
		// なまえ
		String nameKana = request.getNameKana();
		// 入社日
		Date enteredDate = request.getEnteredDate();
		// 種別
		int staffTypeId = request.getStaffTypeId();
		// 誕生日
		Date birthday = request.getBirthday();
		// 電話番号
		String telephoneNumber = request.getTelephoneNumber();
		// 部署ID
		int departmentId = request.getDepartmentId();
		// 役職ID
		int positionId = request.getPositionId();
		// 階級ID
		int gradeId = request.getGradeId();

		// 社員詳細情報 を更新する
//		staffBasicInfoRepository.updateBasicInfo(
//				name, nameKana, enteredDate, staffTypeId, birthday,
//				telephoneNumber,departmentId,positionId,gradeId,staffId);
		StaffBasicInfoEntity query = new StaffBasicInfoEntity();
		staffBasicInfoRepository.save(query);

	}

	private StaffEntity setStaffQuery(StaffRequestForm request) {
		StaffEntity query = new StaffEntity();
		query.setMailAddress(request.getMailAddress());
		query.setLoginId(request.getLoginId());
		query.setPassword(request.getPassword());
		return query;
	}
}

