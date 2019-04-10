package com.to.dolist.utilities;

public interface Statements {

	public final String INSERT = new StringBuilder()
													.append("INSERT INTO todolist")
													.append("(title,            ")
													.append("estimation,         ")
													.append("startdate,          ")
													.append("duedate,            ")
													.append("message,            ")
													.append("status)             ")
													.append("VALUES (?,?,?,?,?,?)")
													.toString();
	public final String UPDATE = new StringBuilder()
													.append("UPDATE todolist SET     ")
													.append("title = ?,              ")
													.append("estimation = ?,         ")
													.append("startdate = ?,          ")
													.append("duedate = ?,            ")
													.append("message = ?,            ")
													.append("status = ?              ")
													.append("WHERE id = ?            ")
													.toString();
	public final String READ_ALL = new StringBuilder()
													  .append("SELECT              ")
													  .append("id,                 ")
													  .append(" title,             ")
													  .append("estimation,         ")
													  .append("startdate,          ")
													  .append("duedate,            ")
													  .append("message,            ")
													  .append("status              ")
													  .append("FROM todolist       ")
													  .toString();
	public final String READ = new StringBuilder()
												  .append("SELECT              ")
												  .append("id,                 ")
												  .append("title,              ")
												  .append("estimation,         ")
												  .append("startdate,          ")
												  .append("duedate,            ")
												  .append("message,            ")
												  .append("status              ")
												  .append("FROM todolist       ")
												  .append("WHERE id = ?        ")
												  .toString();
	public final String DELETE = new StringBuilder()
													.append("DELETE FROM todolist ")
													.append("WHERE id = ?         ")
													.toString();
}
