package com.enviro.assessment.gard001.seshagirisainni.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiError {
	private Integer errorCode;
	private String errorMsg;
	private Date errorDate;
}
