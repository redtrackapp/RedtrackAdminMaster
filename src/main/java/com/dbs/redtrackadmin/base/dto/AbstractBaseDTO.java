package com.dbs.redtrackadmin.base.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



public class AbstractBaseDTO implements Serializable {

	private static final long serialVersionUID = 2689431372302304782L;

	
	@Override
	public String toString(){
		return ReflectionToStringBuilder.toString(this);
	}

}
