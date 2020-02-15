package com.graham.interfaces.response;

import java.util.List;

import com.graham.domain.model.AttendanceEntity;

import lombok.Data;

@Data
public class AttendanceResponseForm {
	
	private List<AttendanceEntity> attendances;

}
