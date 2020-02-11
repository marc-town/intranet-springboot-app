package com.graham.domain.model.sample;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "m_staff")
@Data
@Entity
public class SampleEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "staff_id")
	private String staffId;
	
	@Column(name = "position_id")
	private String positionId;
	
	@Column(name = "grade")
	private String grade;
	
	@Column(name = "staff_type_id")
	private String staffTypeId;
	
	@Column(name = "staff_name")
	private String staffName;
	
	@Column(name = "staff_name_kana")
	private String staffNameKana;
	
	@Column(name = "enterd_date")
	private Date enterdDate;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "tel")
	private String telNum;
	
	@Column(name = "gmail_address")
	private String gmailAddress;
	
	@Column(name = "biz_address")
	private String bizAddress;

	@Column(name = "password")
	private String password;
}