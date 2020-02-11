package com.graham.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "m_staff_detail_info")
@Data
@Entity
public class StaffDetailInfoEntity {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "staff_id")
	private String staffId;
}
