package com.graham.domain.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class StaffEntity {
	
	/** 社員ID */
	@Id
	@Column(name = "staff_id")
	private Integer staffId;
	
	/** 名前 */
	@Column(name = "name")
    private String name;
	
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
	@Column(name = "role", length = 60)
    private String role;

}
