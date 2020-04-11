package com.graham.interfaces.request;

import lombok.Data;

@Data
public class StaffBasicInfoRequestForm {

	/** 名前 */
	private String name;
	
	/** なまえ */
	private String nameKana;
	
	/** 入社日 */
	private String enteredDate;
	
	/** 権限 */
    private Integer role;
	
	/** 誕生日 */
	private String birthday;
	
	/** 電話番号 */
	private String telephoneNumber;

	/** 部署ID */
	private Integer departmentId;

	/** 役職ID */
	private Integer positionId;

	/** 階級ID */
	private Integer gradeId;
}
