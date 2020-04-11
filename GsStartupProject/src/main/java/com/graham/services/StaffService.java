package com.graham.services;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graham.common.GrahamHttpStatus;
import com.graham.common.RoleName;
import com.graham.domain.model.JwtStaffEntity;
import com.graham.domain.model.RoleEntity;
import com.graham.domain.model.StaffEntity;
import com.graham.domain.repositorys.JwtUserRepository;
import com.graham.domain.repositorys.RoleRepository;
import com.graham.domain.repositorys.StaffBasicInfoRepository;
import com.graham.domain.repositorys.StaffDetailInfoRepository;
import com.graham.domain.repositorys.StaffRepository;
import com.graham.exception.GrahamError;
import com.graham.exception.GrahamException;
import com.graham.interfaces.request.SignupRequestForm;
import com.graham.interfaces.request.StaffBasicInfoRequestForm;
import com.graham.interfaces.response.StaffBasicInfoResponseForm;
import com.graham.interfaces.response.StaffResponseForm;

/**
 * 社員情報管理を行うサービスクラス
 *
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class StaffService {

	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private StaffBasicInfoRepository staffBasicInfoRepository;
	@Autowired
	private StaffDetailInfoRepository staffDetailInfoRepository;
	@Autowired
	private JwtUserRepository jwtUserRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private MessageSource messageSource;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffService.class);
	
	/**
	 * 社員一覧取得
	 * 
	 * @return staffs 取得したDB情報
	 */
	public StaffResponseForm index() {
		List<StaffEntity> entity = staffRepository.findAllStaff();
		return new StaffResponseForm(entity);
	}
	
	/**
	 * 社員登録
	 * 
	 * @param staff 登録社員情報
	 */
	public void regist(SignupRequestForm request) {
		
		LOGGER.info("BEGIN StaffService regist");
		
		// ログインIDが被っていないか確認
		if (jwtUserRepository.existsByUsername(request.getLoginId())) {
			String message = messageSource.getMessage("error.E_GSOL0004",  new String[]{String.valueOf(request.getLoginId())}, Locale.JAPANESE);
			LOGGER.error(message);
			GrahamError err = new GrahamError(GrahamHttpStatus.BAD_REQUEST, "E_GSOL0004", message);
			throw new GrahamException(err);
		}

		// メールアドレスが被っていないか確認
		if (jwtUserRepository.existsByEmail(request.getEmail())) {
			String message = messageSource.getMessage("error.E_GSOL0005",  new String[]{String.valueOf(request.getLoginId())}, Locale.JAPANESE);
			LOGGER.error(message);
			GrahamError err = new GrahamError(GrahamHttpStatus.BAD_REQUEST, "E_GSOL0005", message);
			throw new GrahamException(err);
		}

		// password暗号化
		request.encrypt(bCryptPasswordEncoder);
		
		// Create new user's account
		JwtStaffEntity staff = new JwtStaffEntity(request.getLoginId(),request.getEmail(), request.getPassword());

		final int ADMIN  = 1;
		final int MIDDLE = 2;
		final int USER   = 3;
		int role = request.getRole();
		RoleEntity roleEntity = null;
		Set<RoleEntity> roles = new HashSet<>();

		switch (role) {
			case ADMIN:
				roleEntity = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(roleEntity);
	
				break;
			case MIDDLE:
				roleEntity = roleRepository.findByName(RoleName.ROLE_MIDDLE)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(roleEntity);
	
				break;
			case USER:
				roleEntity = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(roleEntity);
	
				break;
			default:
				RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
		}

		staff.setRoles(roles);
		// m_staff レコード作成
		LOGGER.info(messageSource.getMessage("info.I_GSOL0101", null, Locale.JAPANESE));
		staff = jwtUserRepository.save(staff);
		
		// m_staff_basic_info レコード作成
		LOGGER.info(messageSource.getMessage("info.I_GSOL0101", null, Locale.JAPANESE));
		String name = request.getName();
		String nameKana = request.getNameKana();
		String tel = request.getTelephoneNumber();
		staffBasicInfoRepository.insertBasicInfo(staff.getStaffId(), name, nameKana, tel);
		
		// m_staff_detail_info レコード作成
		LOGGER.info(messageSource.getMessage("info.I_GSOL0102", null, Locale.JAPANESE));
		staffDetailInfoRepository.insertDetailInfo(staff.getStaffId());
	}

	/**
	 * 社員削除
	 * 
	 * @param staffId 退職社員ID
	 */
	public void delete(int staffId) {
		staffBasicInfoRepository.deleteByStaffId(staffId);
		staffDetailInfoRepository.deleteByStaffId(staffId);
		jwtUserRepository.deleteByStaffId(staffId);
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
	 * 社員基本情報を更新する
	 * 
	 * @param staffId  社員ID
	 * @param request 社員基本情報
	 */
	public void updateStaffBasicInfo(int staffId, StaffBasicInfoRequestForm request) {
		
		String name = request.getName();
		String nameKana = request.getNameKana();
		String enteredDate = request.getEnteredDate();
		Integer roleId = request.getRole();
		String birthday = request.getBirthday();
		String telephoneNumber = request.getTelephoneNumber();
		int departmentId = request.getDepartmentId();
		int positionId = request.getPositionId();
		int gradeId = request.getGradeId();

		// 社員詳細情報を更新する
		LOGGER.info("start staffService.updateStaffBasicInfo.updateBasicInfo");
		staffBasicInfoRepository.updateBasicInfo(
				name, nameKana, enteredDate, birthday,
				telephoneNumber,departmentId,positionId,gradeId,staffId);
		
		// 社員と権限の紐付きを更新する
		LOGGER.info("start staffService.updateStaffBasicInfo.updateStaffRole");
		roleRepository.updateStaffRole(staffId, roleId);
	}
	
	/**
	 * トークンに含まれるログインIDから社員IDを検索し、一致するか確認する
	 * 
	 * @param loginId トークンから取得したログインID
	 * @param expectedStaffId リクエストボディから取得した社員ID
	 * @return result 判定結果
	 */
	public Boolean isCorrectStaff(String loginId, int expectedStaffId) {
		LOGGER.info("BEGIN AttendanceController isCorrectStaff");
		int actualStaffId = jwtUserRepository.findStaffIdByLoginId(loginId);
		LOGGER.info("ActualStaffId: {}, ExpectedStaffId: {}", actualStaffId, expectedStaffId);
		return actualStaffId == expectedStaffId ? true : false;
	}
}

