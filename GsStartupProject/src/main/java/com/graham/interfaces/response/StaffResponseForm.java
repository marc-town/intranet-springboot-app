package com.graham.interfaces.response;

import java.util.List;

import com.graham.domain.model.StaffEntity;

import lombok.Data;

@Data
public class StaffResponseForm {

	private List<StaffEntity> staffs;

}
