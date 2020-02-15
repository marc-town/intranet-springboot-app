package com.graham.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "m_staff_detail_info")
@Data
@Entity
public class StaffDetailInfoEntity {

	@Id
	@Column(name = "staff_detail_info_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer staffDetailInfoId;

	@Column(name = "staff_id")
	private Integer staffId;
}
