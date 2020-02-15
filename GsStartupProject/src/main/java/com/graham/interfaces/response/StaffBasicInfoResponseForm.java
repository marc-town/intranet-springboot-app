package com.graham.interfaces.response;

import com.graham.domain.model.StaffBasicInfoEntity;

import lombok.Data;

@Data
public class StaffBasicInfoResponseForm {

	/** 社員詳細情報エンティティ */
	private StaffBasicInfoEntity basicInfo;

}
