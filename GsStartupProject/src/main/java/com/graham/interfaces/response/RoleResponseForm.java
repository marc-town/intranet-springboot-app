package com.graham.interfaces.response;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graham.domain.model.RoleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleResponseForm {

	@JsonProperty("roles")
	private List<RoleEntity> roles;
}
