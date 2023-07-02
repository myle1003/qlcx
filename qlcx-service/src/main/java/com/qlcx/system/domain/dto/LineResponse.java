package com.qlcx.system.domain.dto;

import com.qlcx.common.core.domain.BaseEntity;

public class LineResponse extends BaseEntity {
	private int day;
	private int qtt;
	private int sumTree;
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getQtt() {
		return qtt;
	}
	public void setQtt(int qtt) {
		this.qtt = qtt;
	}
	public int getSumTree() {
		return sumTree;
	}
	public void setSumTree(int sumTree) {
		this.sumTree = sumTree;
	}
	
}
