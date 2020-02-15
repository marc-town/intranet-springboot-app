package com.graham.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 社員詳細情報 を表現するエンティティ
 * 
 */
@Table(name = "m_staff_basic_info")
@Data
@Entity
public class StaffBasicInfoEntity {

	/** サロゲートキー */
	@Id
	@Column(name = "staff_basic_info_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer staffBasicInfoId;

	/** 社員ID */
	@Column(name = "staff_id")
	private Integer staffId;
	
	/** 名前 */
	@Column(name = "name")
	private String name;
	
	/** なまえ */
	@Column(name = "name_kana")
	private String nameKana;
	
	/** 入社日 */
	@Column(name = "entered_date")
	private String enteredDate;
	
	/** 種別 */
	@Column(name = "staff_type_id")
	private Integer staffTypeId;
	
	/** 誕生日 */
	@Column(name = "birthday")
	private String birthday;
	
	/** 電話番号 */
	@Column(name = "telephone_number")
	private String telephoneNumber;

	/** 部署ID */
	@Column(name = "department_id")
	private Integer departmentId;

	/** 役職ID */
	@Column(name = "position_id")
	private Integer positionId;

	/** 階級ID */
	@Column(name = "grade_id")
	private Integer gradeId;
}
