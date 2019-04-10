package com.to.dolist.model;

import java.sql.Date;

public class ToDoList {

	private long id;
	private String title;
	private int estimation;
	private Date startdate;
	private Date duedate;
	private String messgae;
	private String status;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getEstimation() {
		return estimation;
	}
	public void setEstimation(int estimation) {
		this.estimation = estimation;
	}
	@Override
	public String toString() {
		return "ToDoList [id=" + id + ", title=" + title + ", estimation=" + estimation + ", startdate=" + startdate
				+ ", duedate=" + duedate + ", messgae=" + messgae + ", status=" + status + "]";
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public String getMessgae() {
		return messgae;
	}
	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}
}
