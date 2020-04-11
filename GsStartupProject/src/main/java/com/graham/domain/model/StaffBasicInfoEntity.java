package com.graham.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * 社員詳細情報 を表現するエンティティ
 * 
 */
@Data
@Entity
public class StaffBasicInfoEntity {

	/** サロゲートキー */
	@Id
	@Column(name = "staff_basic_info_id")
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
	
	/** 誕生日 */
	@Column(name = "birthday")
	private String birthday;
	
	/** 電話番号 */
	@Column(name = "telephone_number")
	private String telephoneNumber;

	/** 部署 */
	@Column(name = "department")
    private String department;
	
	/** 役職 */
	@Column(name = "position")
    private String position;
	
	/** 階級 */
	@Column(name = "grade")
    private String grade;
	
	/** 権限 */
	@Column(name = "role")
    private String role;
}
