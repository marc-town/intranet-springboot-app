package com.graham.interfaces.response;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graham.domain.model.DepartmentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentResponseForm {

	@JsonProperty("departments")
	private List<DepartmentEntity> departmentss;
}
