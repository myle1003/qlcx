package com.qlcx.system.domain.dto;

import com.qlcx.common.core.domain.BaseEntity;

public class StatisticalResponse extends BaseEntity {
	private int good;
	private int kha;
	private int medium;
	private int needCare;
	private int immediately;
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getKha() {
		return kha;
	}
	public void setKha(int kha) {
		this.kha = kha;
	}
	public int getMedium() {
		return medium;
	}
	public void setMedium(int medium) {
		this.medium = medium;
	}
	public int getNeedCare() {
		return needCare;
	}
	public void setNeedCare(int needCare) {
		this.needCare = needCare;
	}
	public int getImmediately() {
		return immediately;
	}
	public void setImmediately(int immediately) {
		this.immediately = immediately;
	}
	
	
}
