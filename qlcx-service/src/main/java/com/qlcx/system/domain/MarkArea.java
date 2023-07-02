package com.qlcx.system.domain;

import java.util.List;

public class MarkArea {
	private Long id;
	private String description;
	private String lineColor;
	private String fillColor;
	private int status;
	private List<MarkAreaDetail> listDetail;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLineColor() {
		return lineColor;
	}
	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}
	public String getFillColor() {
		return fillColor;
	}
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<MarkAreaDetail> getListDetail() {
		return listDetail;
	}
	public void setListDetail(List<MarkAreaDetail> listDetail) {
		this.listDetail = listDetail;
	}
	
}
