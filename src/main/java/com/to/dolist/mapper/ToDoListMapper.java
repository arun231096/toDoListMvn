package com.to.dolist.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.to.dolist.model.ToDoList;

public class ToDoListMapper implements RowMapper<ToDoList>{


	@Override
	public ToDoList mapRow(ResultSet arg0, int arg1) throws SQLException {
		ToDoList list = new ToDoList();
		list.setId(arg0.getInt("id"));
		list.setTitle(arg0.getString("title"));
		list.setMessgae(arg0.getString("message"));
		list.setStatus(arg0.getString("status"));
		list.setEstimation(arg0.getInt("estimation"));
		list.setStartdate(arg0.getDate("startdate"));
		list.setDuedate(arg0.getDate("duedate"));
		return list;
	}
}
