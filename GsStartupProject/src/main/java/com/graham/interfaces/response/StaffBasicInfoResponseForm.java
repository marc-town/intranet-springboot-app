package com.graham.interfaces.response;

import com.graham.domain.model.StaffBasicInfoEntity;

import lombok.Data;

/**
 * 社員詳細情報 に関するフォーム
 * 
 */
@Data
public class StaffBasicInfoResponseForm {

	/** 社員詳細情報エンティティ */
	private StaffBasicInfoEntity basicInfo;

}
