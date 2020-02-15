package com.graham.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "m_absence_type")
@Data
@Entity
public class AbsenceTypeEntity {
	
	/** 欠勤種別ID */
	@Id
	@Column(name = "absence_type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer absenceTypeId;
	
	/** 欠勤種別
	 * 1: 欠勤、2: 代休、3: 有給、4: 忌引 
	 */
	@Column(name = "absence_type_name")
	private String absenceTypeName;

}
