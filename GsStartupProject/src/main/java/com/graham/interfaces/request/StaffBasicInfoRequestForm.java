package com.graham.interfaces.request;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class StaffBasicInfoRequestForm {

	/** 社員ID */
	private Integer staffId;
	
	/** 名前 */
	private String name;
	
	/** なまえ */
	private String nameKana;
	
	/** 入社日 */
	private Date enteredDate;
	
	/** 種別 */
	private Integer staffTypeId;
	
	/** 誕生日 */
	private Date birthday;
	
	/** 電話番号 */
	private String telephoneNumber;

	/** 部署ID */
	private Integer departmentId;

	/** 役職ID */
	private Integer positionId;

	/** 階級ID */
	private Integer gradeId;
}
