package com.graham.interfaces.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graham.domain.model.StaffEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffResponseForm {

	@JsonProperty("staffs")
	private List<StaffEntity> staffs;
}
