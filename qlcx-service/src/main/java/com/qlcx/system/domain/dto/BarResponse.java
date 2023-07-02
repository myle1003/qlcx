package com.qlcx.system.domain.dto;

import com.qlcx.common.core.domain.BaseEntity;

public class BarResponse extends BaseEntity {

	private String wardName;
	private int type0;
	private int type1;
	private int type2;
	private int type3;
	
	
	
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public int getType0() {
		return type0;
	}
	public void setType0(int type0) {
		this.type0 = type0;
	}
	public int getType1() {
		return type1;
	}
	public void setType1(int type1) {
		this.type1 = type1;
	}
	public int getType2() {
		return type2;
	}
	public void setType2(int type2) {
		this.type2 = type2;
	}
	public int getType3() {
		return type3;
	}
	public void setType3(int type3) {
		this.type3 = type3;
	}
	
	
}
