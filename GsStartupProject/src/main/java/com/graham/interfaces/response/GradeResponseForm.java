package com.graham.interfaces.response;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graham.domain.model.GradeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GradeResponseForm {

	@JsonProperty("grades")
	private List<GradeEntity> grades;
}
