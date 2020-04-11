package com.graham.interfaces.response;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graham.domain.model.PositionEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionResponseForm {

	@JsonProperty("positions")
	private List<PositionEntity> positions;
}
